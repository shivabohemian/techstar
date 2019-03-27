package cn.com.cmbcc.utils;

/**
 * Created by tinyspace on 2018/3/6.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by liudianbing on 2016/11/29.
 */
@Service("socketService")
public class SocketService {
    private static final Logger logger = LoggerFactory.getLogger(SocketService.class);
    private static final String ENCODING = "GB18030";
    public static final String EMPTY_STRING = "";


    private byte[] readBytesWithLength(InputStream is, int readLength) throws IOException {
        byte[] byteLen = new byte[readLength];
        int total = 0, len = 0;
        byte[] tmpBuf = new byte[readLength];

        //因为网络状况不好，有可能四个字节也不是一次tcp包送出
        while (total < readLength) {
            int maxLen = Math.min(readLength - total, 1024); //一次最多读取1024个字节
            len = is.read(tmpBuf, 0, maxLen);
            if (len == -1) {
                logger.error("Get nothing from the input stream ...");
                return null;
            }
            System.arraycopy(tmpBuf, 0, byteLen, total, len);
            total += len;
        }
        return byteLen;
    }

    public String sendXml(String xml, String encoding, String host, int port) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        InetSocketAddress address = new InetSocketAddress(host, port);
        try {
            socket = new Socket();
            socket.setSoTimeout(50000);//5秒超时
            socket.connect(address);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            byte[] body = xml.getBytes(encoding);
            int len = body.length;
            byte[] head = String.format("%04d", len).getBytes(encoding);
            byte[] allBuf = new byte[4 + len];
            System.arraycopy(head, 0, allBuf, 0, 4);
            System.arraycopy(body, 0, allBuf, 4, len);
            os.write(allBuf);
            os.flush();

            byte[] byteLen = readBytesWithLength(is, 4);
            if (byteLen == null) {
                return EMPTY_STRING;
            }
            String strlen = new String(byteLen);
            int rtlen = Integer.parseInt(strlen);
            if (rtlen > 0) {
                byte[] buf = readBytesWithLength(is, rtlen);
                String response = new String(buf, encoding);
                return response;
            }
        } catch (Exception ex) {
            logger.error("error:", ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception ex) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();

                } catch (Exception ex) {
                }
            }
        }
        return EMPTY_STRING;
    }
    public String responseXml(String xml, String encoding,Socket socket) {
        OutputStream os = null;
        try {
            socket.setSoTimeout(50000);//5秒超时
//            is = socket.getInputStream();
            os = socket.getOutputStream();
            byte[] body = xml.getBytes(encoding);
            int len = body.length;
            byte[] head = String.format("%04d", len).getBytes(encoding);
            byte[] allBuf = new byte[4 + len];
            System.arraycopy(head, 0, allBuf, 0, 4);
            System.arraycopy(body, 0, allBuf, 4, len);
            os.write(allBuf);
            os.flush();
        } catch (Exception ex) {
            logger.error("error:", ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception ex) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();

                } catch (Exception ex) {
                }
            }
        }
        return EMPTY_STRING;
    }

    public static void main(String[] args) {

        String sendXml = "<?xml version=\"1.0\" encoding=\"GB18030\" standalone=\"yes\"?>" +
                "<REQUEST>" +
                "<HEAD><TRCD>CCXB</TRCD>" +
                "<TRDT>20180306</TRDT><TRTM>134117</TRTM>" +
                "<MSCOPRSEQ>14877023</MSCOPRSEQ>" +
                "<TRTP></TRTP><SOUR>ZMXY</SOUR>" +
                "</HEAD>" +
                "<BODY>" +
                "<cStlFlag>1</cStlFlag><sKeyType>01</sKeyType>" +
                "<sCustId>432322197801010001</sCustId>" +
                "<sBusiDept></sBusiDept><BpFlag>P</BpFlag>" +
                "<cPinFlag>0</cPinFlag><sNewPinData></sNewPinData>" +
                "<COUT>1</COUT><FOUT>10</FOUT></BODY></REQUEST>";

        String returnXml = "<?xml version=\"1.0\" encoding=\"GB18030\" standalone=\"yes\"??>\n" +
                "<RESPONSE>\n" +
                " <HEAD>\n" +
                " <TRCD>CCXB</TRCD>\n" +
                " <TRDT>20180306</TRDT>\n" +
                " <TRTM>134117</TRTM>\n" +
                " <MSCOPRSEQ>14877023</MSCOPRSEQ>\n" +
                " <TRSQ>E000022</TRSQ>\n" +
                " <ERTX>动态密码验证错误</ERTX>\n" +
                " <MSCFESEQ>193455</MSCFESEQ>\n" +
                " <MSCFEDT>20170511</MSCFEDT>\n" +
                " </HEAD>\n" +
                " <BODY>\n" +
                " <CardNo>8888888888888888</CardNo>\n" +
                " <CPNO>18613818526</CPNO>\n" +
                " </BODY>\n" +
                "</RESPONSE>";


    }


}
