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
                Answer1.sum(args);
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
                Answer6.print(args);
                break;

            case 7:
                System.out.println("verify No." + num);
                break;

            case 8:
                System.out.println("verify No." + num);
                Answer8.startRun(args[1]);
                break;

            case 9:
                System.out.println("verify No." + num);
                Answer9.startRun(args[1]);
                break;

            default:
                System.out.println("verify No." + num);
                break;

        }
    }
}
