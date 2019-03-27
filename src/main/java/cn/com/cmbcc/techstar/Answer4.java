package cn.com.cmbcc.techstar;

import java.util.Scanner;

/**
 * Answer4 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer4 {
    public static void print(String str) {
        int ascllA = (int) 'A';
        int asallX = (int) 'X';
        int length = str.length();
        int total = 0; //计算结果
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            //计算当前字母代表的数字
            int num;
            if (asallX - (int) c > 0) {
                //X左边的字母
                num = 24 - (int) c + ascllA;
            } else {
                //X右边的字母
                num = 25 - (int) c + ascllA;
            }
            total += num * Math.pow(25, length - i - 1);
        }
        System.out.println(total);
    }
}
