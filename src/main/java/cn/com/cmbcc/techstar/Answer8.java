package cn.com.cmbcc.techstar;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.com.cmbcc.utils.SocketService;


class ReceiveThread implements Runnable{
    private ServerSocket server;
    private String responseXml;
    private String encoding;
    private String flag;
    public ReceiveThread(ServerSocket server,String responseXml,String encoding,String flag){
        this.server=server;
        this.responseXml=responseXml;
        this.encoding=encoding;
        this.flag=flag;
    }

    public ServerSocket getSocket()
    {
        return server;
    }


    public String getResponseXml()
    {
        return responseXml;
    }

    public void setResponseXml(String responseXml)
    {
        this.responseXml = responseXml;
    }

    @Override
    public void run()
    {
        SocketService socketService=new SocketService();
        while(true) {
            InputStream in;
            try {
                Socket socket=server.accept();
                in = socket.getInputStream();
                byte[] result=new byte[4];
                in.read(result, 0, 4);
//				String strlen = new String(result);
//		        int bodyLength = Integer.parseInt(strlen);
                socketService.responseXml(responseXml, encoding,socket );
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(flag.equals("false")) {
                System.out.println("服务端中断连接");
                break;
            }
        }


    }

}
public class Answer8 {
    private int port;
    private String encoding;
    private String flag;
    public Answer8(int port,String encoding,String flag){
        this.port=port;
        this.encoding=encoding;
        this.flag=flag;
    }
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


    /**
     * 请在这里补充您的代码
     */
    public void startListen(){
        //开始监听，补充您的代码
        try {
            ServerSocket server=new ServerSocket(port);
            ReceiveThread thread=new ReceiveThread(server,returnXml,encoding,flag);
            Thread start=new Thread(thread);
            start.start();
//			ExecutorService service=Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
