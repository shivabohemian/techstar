package cn.com.cmbcc.techstar;

import java.util.Scanner;

/**
 * Created by tinyspace on 2018/3/6.
 */
public class Answer3 {


    public static void print(String str ) {

        try{
            int n = Integer.parseInt(str);
            if (n <=0){
                System.out.println("输入不符合规范");
            }
            boolean []ary = new boolean[n];
            for(int i = 0 ; i < n ; i++) {
                ary[i] = false ;
            }

            int sum = 0;
            int count = 0;
            //flag = true 正向报数， false = 反向报数
            boolean flag = true;
            while (sum + 1 < n){

                for (int j = 0; j<n;j++){
                    //没有剔除
                    if (!ary[j] ){
                        if (flag){
                            count++;
                        }else {
                            count -- ;
                            if (count == 1){
                                flag = true;
                            }
                        }
                        if (count == 3){
                            flag = !flag;
                            ary[j] = true;
                            sum ++;
                            if (sum + 1 == n){
                                break;
                            }
                        }
                    }
                }

            }

            for(int m = 0 ; m < n ; m++) {
                if (!ary[m]){
                    System.out.println(m+1);
                    break;
                }
            }

        }catch (Exception e){
            System.out.println("输入不符合规范");
        }
    }

}
