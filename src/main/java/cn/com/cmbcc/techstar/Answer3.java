package cn.com.cmbcc.techstar;

import java.util.Scanner;

/**
 * Created by tinyspace on 2018/3/6.
 */
public class Answer3 {

    /*public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try{
            int N = in.nextInt();
            if(N < 0 || N > 20){
                System.out.println("输入不符合规范则");
            }else{
                int total = 2 * N + 1;
                for (int i = 0; i<=N; ++i){

                    //左边空格的输出
                    for (int j = 0;j<(N-i);j++){
                        System.out.print(" ");
                    }
                    //输出数字的个数
                    int num = 2*i+1;
                    for (int j = 0;j<num;j++){
                        if(N < 10){
                            System.out.print(i);
                        }else{
                            if(i<10){
                                System.out.print(i+" ");
                            }else{
                                System.out.print(i);
                            }
                        }
                    }
                    System.out.println();

                }

            }

        }catch (Exception e){
            System.out.println("输入不符合规范则");
        }

    }*/

    public static void print(String s) {
        int N = 0;
        try {
            N = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("输入不符合规范则");
        }

        if (N < 0 || N > 20) {
            System.out.println("输入不符合规范则");
        } else {
            int total = 2 * N + 1;
            for (int i = 0; i <= N; ++i) {

                //左边空格的输出
                for (int j = 0; j < (N - i); j++) {
                    System.out.print(" ");
                }
                //输出数字的个数
                int num = 2 * i + 1;
                for (int j = 0; j < num; j++) {
                    if (N < 10) {
                        System.out.print(i);
                    } else {
                        if (i < 10) {
                            System.out.print(i + " ");
                        } else {
                            System.out.print(i);
                        }
                    }
                }
                System.out.println();

            }

        }
    }

}
