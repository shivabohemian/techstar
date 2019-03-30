package cn.com.cmbcc.techstar;

import java.util.ArrayList;

/**
 * Answer4 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer4 {
    private static int MAX_SIZE = 1000000;
    public static void print(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        try {
            for (int i = 1; i < args.length; i++) {
                //list.add(Integer.parseInt(args[i]));
                int n = Integer.parseInt(args[i]);
                if (!isPrime(n)){
                    System.out.println("集合里"+n+"不是质数");
                    return;
                }
                list.add(n);
            }
        }catch (Exception e){
            System.out.println("请输入正确的质数集合");
            return;
        }
        if (list.size() > 100){
            System.out.println("质数集合个数不能超过100");
            return;
        }

        System.out.println("1");
        for (long num = 2; num < MAX_SIZE; num++) {
            boolean isAllIn = true;
            //判断数字num 能否被质数集合整除
            long k = num;
            for (int i = 0; i < list.size(); i++){

                while (k % list.get(i) == 0){
                    k = k / list.get(i);
                }
            }
            if (k == 1){
                System.out.println( num );
            }

            //long temp = num;
            /*for (int i = 2; i <= temp; ) {
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
            }*/
            //质数因子全在给定的质数集合中
            /*if (isAllIn) {
                System.out.printf(num + " ");
                sb.append(num + " ");
            }*/
        }
    }

    public static boolean isPrime(long num){

        if (num <=1 ){
            return false;
        }
        boolean isprime = true;
        int len = (int )Math.sqrt(num);
        for(int i=3;i<=len;i+=2){
            if(num%i==0){
                isprime=false;
                break;
            }
        }
        return isprime;

    }
}
