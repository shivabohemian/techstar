package cn.com.cmbcc.techstar;

import cn.com.cmbcc.utils.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by tinyspace on 2018/3/6.
 */
public class Answer {

    private static final Logger logger = LoggerFactory.getLogger(Answer.class);

    public static void main(String[] args) throws IOException {


        if (args == null || args.length == 0) {
            System.out.println("usage cn.com.cmbcc.techstar.Answer [1-10] other params");
            return;
        }
        int num = 0;

        try {
            num = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        if (num <= 0 || num > 10) {
            System.out.println("first param is invalid");
            return;
        }
        switch (num) {
            case 1:
                //call
                System.out.println("verify No." + num);
                Answer1.sum(args[1]);
                break;
            case 2:
                System.out.println("verify No." + num);
                Answer2.resultMethod(args);
                break;

            case 3:
                System.out.println("verify No." + num);
                Answer3.print(args[1]);
                break;

            case 4:
                System.out.println("verify No." + num);
                Answer4.print(args);
                break;

            case 5:
                int[] result=new int[8];
                System.out.println("verify No." + num);
                Answer5.calculateResult(0,new int[10],140);
                break;

            case 6:
                System.out.println("verify No." + num);
                Answer6.print(args[1]);
                break;

            case 7:
                System.out.println("verify No." + num);
                break;

            case 8:
                System.out.println("verify No." + num);
                int port = 0;
                if (args.length < 2) {
                    System.out.println("please define listen port");
                    return;
                }
                try {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                }


                Answer8 answer8 = new Answer8(port,"GB18030",args[2]);
                answer8.startListen();

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

                SocketService socketService = new SocketService();
                String returnXml = socketService.sendXml(sendXml, "GBK", "127.0.0.1", port);
                System.out.println("返回结果：" + returnXml);
                break;

            case 9:
                System.out.println("verify No." + num);
                Answer9.startRun(args[1]);
                break;

            case 10:
                System.out.println("verify No." + num);
                Answer10.print(args[1]);
                break;
            default:
                System.out.println("verify No." + num);
                break;

        }
    }
}
