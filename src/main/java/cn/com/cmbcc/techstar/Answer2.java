package cn.com.cmbcc.techstar;

import java.util.*;

public class Answer2 {

    /**
     * 入口方法
     * @param args
     */
    public static void resultMethod(String[] args) {
        if(args == null || args.length<4) {
            System.out.println("输入有误");
            return;
        }
        String[] args_ = new String[args.length-1];
        for(int i=1; i<args.length; i++) {
            args_[i-1] = args[i];
        }
        int[] intArr = stringArrToIntArr(args_);
        findThreeNums(intArr);
        return;
    }

    /**
     * 将String[]转换成int[]
     * @param args
     * @return
     */
    private static int[] stringArrToIntArr(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("请输入正确的参数！");
            return null;
        }
        int[] intArr = new int[args.length];
        int index = 0;
        try {
            for (String str : args) {
                intArr[index++] = Integer.parseInt(str);
            }
        } catch(NumberFormatException e) {
            System.out.println("请输入整型数！");
            return null;
        }
        return intArr;
    }

    /**
     * 从int[]中找出所有符合a+b+c=0的三元组
     * @param nums
     * @return
     */
    private static List<int[]> findThreeNums(int[] nums) {
        List<int[]> resultList = new ArrayList<>();
        if(nums == null || nums.length<3) {
            System.out.println("请输入合理的整形数组！");
            return null;
        }
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                int total = nums[i] + nums[j];
                for(int k=j+1; k<nums.length; k++) {
                    if(total + nums[k] == 0) {
                        resultList.add(new int[]{nums[i], nums[j], nums[k]});
                    }
                }
            }
        }

        Map<String,String> map = new HashMap<String,String>();
        for(int[] innerArr : resultList) {
            Arrays.sort(innerArr);
            //for(int i=0; i<innerArr.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(innerArr[0]).append(innerArr[1]).append(innerArr[2]);
            if (!map.containsKey(sb.toString())){
                map.put(sb.toString(),sb.toString());
                System.out.println(innerArr[0] + "," + innerArr[1] + ","+innerArr[2]);
            }
            //}
            //System.out.println();
        }
        return resultList;
    }

}