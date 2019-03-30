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
            int count = 1;
            while (sum + 1 < n){

                //正向查数
                for (int j = 1; j<n;j++){
                    //没有剔除
                    if (!ary[j] ){
                        count ++;
                        if (count == 3){
                            ary[j] = true;
                            count = 0;
                            sum ++;
                            if (sum + 1 == n){
                                break;
                            }
                        }
                    }
                }
                //反向查数
                for (int k = n-2; k>=0;k--){
                    //没有剔除
                    if (!ary[k] ){
                        count ++;
                        if (count == 3){
                            ary[k] = true;
                            count = 0;
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
