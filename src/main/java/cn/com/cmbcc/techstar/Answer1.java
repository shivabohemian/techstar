package cn.com.cmbcc.techstar;

public class Answer1 {

    public static void sum(String str) {
        int sum = 0;
        ;
        try {
            str = str.replaceAll(" ", "");
            String str1 = str.split("\\+")[0];
            String str2 = str.split("\\+")[1];

            //去括号
            str1 = str1.replace("(", "").replace(")", "");
            str2 = str2.replace("(", "").replace(")", "");

            String[] arr1 = str1.split("->");
            String[] arr2 = str2.split("->");
            for (int i = 0; i < arr1.length; i++) {
                int temp = Integer.parseInt(arr1[i]);
                sum += temp * Math.pow(10, i);
            }
            for (int j = 0; j < arr2.length; j++) {
                int temp = Integer.parseInt(arr2[j]);
                sum += temp * Math.pow(10, j);
            }

            String printNum = String.valueOf(sum);
            StringBuilder sb = new StringBuilder();
            for (int i = printNum.length() - 1; i >= 0; i--) {
                sb.append(printNum.charAt(i));
                if (i != 0) {
                    sb.append(" -> ");
                }
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println("您输入的表达式有误，请重新输入");
        }
    }
}