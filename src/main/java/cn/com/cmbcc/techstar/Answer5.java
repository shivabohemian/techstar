package cn.com.cmbcc.techstar;

import java.util.*;

/**
 * Answer5 class
 *
 * @date 2018/3/17
 */
public class Answer5 {

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }
        int num = Integer.valueOf(args[0]);
        calculateResult(0, new int[10], 5);

    }

    //0,1,2,3,4  "" + - *  /
    static private char parse(int op) {
        switch (op) {
            case 1:
                return '+';
            case 2:
                return '-';
            case 3:
                return '*';
            default:
                return '/';
        }
    }

    static private void systemResult(int ret[], int sum) {

        Integer nums[] = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int retSum = 0;

        StringBuilder result = new StringBuilder();
        result.append(1);

        int midNum = 1;
        Stack<Integer> num = new Stack<>();
        Stack<Integer> ops = new Stack<>();

        for (int i = 0; i < 8; i++) {
            int operation = ret[i];
            switch (operation) {
                case 0:
                    midNum = midNum * 10 + nums[i + 1];
                    result.append(nums[i + 1]);
                    break;
                default:
                    num.push(midNum);
                    ops.push(operation);
                    result.append(parse(operation));
                    midNum = nums[i + 1];
                    result.append(nums[i + 1]);

            }
        }
        num.push(midNum);
        int multi = 1;
        int div = 1;
        while (!num.empty()) {
            int singleNum = num.pop();
            int op = ops.isEmpty() ? 0 : ops.pop();

            if (op == 3) {
                multi *= singleNum;
                continue;
            }
            if (op == 4) {
                div *= singleNum;
                continue;
            }
            if (div != 1 || multi != 1) {
                if (singleNum * multi % div != 0) {
                    return;
                }
                singleNum = singleNum * multi / div;
                multi = 1;
                div = 1;
            }

            if (op == 1) {
                retSum += singleNum;
            }
            if (op == 2) {
                retSum -= singleNum;
            }
            if (op == 0) {
                retSum += singleNum;
            }
        }
        if (retSum == sum) {
            System.out.println(result + "=" + sum);
        }

    }

    static public void calculateResult(int index, int ret[], int sum) {
        if (index > 8) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            ret[index] = i;
            calculateResult(index + 1, ret, sum);
            if (index == 8) {
                systemResult(ret, sum);
                return;
            }
        }
        return;
    }

}
