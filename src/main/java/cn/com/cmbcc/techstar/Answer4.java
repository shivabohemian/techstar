package cn.com.cmbcc.techstar;

import java.util.ArrayList;

/**
 * Answer4 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer4 {
    public static void print(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("1 ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            list.add(Integer.parseInt(args[i]));
        }
        for (long num = 2; num < 100; num++) {
            boolean isAllIn = true;
            long temp = num;
            for (int i = 2; i <= temp; ) {
                if (temp % i == 0) {
                    //是它的质数因子
                    if (!list.contains(i)) {
                        isAllIn = false;
                        break;
                    }
                    temp /= i;
                } else {
                    i++;
                }
            }
            //质数因子全在给定的质数集合中
            if (isAllIn) {
                sb.append(num + " ");
            }
        }
        System.out.println(sb.toString());
    }
}
