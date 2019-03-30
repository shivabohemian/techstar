package cn.com.cmbcc.techstar;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Answer5 class
 *
 * @date 2018/3/17
 */
public class Answer5 {

    //使用递归求出5^8种情况
    public static void getResult(int index,int[] result){
        if(index==8){
            showResult(result);//根据数组的取值转换成表达式，且求值，这方法有待改进，写的很乱
            return;
        }
        //每个空有五种可能，0,1,2，3，4
        for(int i=0;i<5;i++){
            result[index]=i;
            getResult(index+1,result);
            result[index]=0; //恢复原来的状态
        }
    }
    public static void showResult(int[] result){

        int sum=0;
        String[] source = new String[]{"1","2","3","4","5","6","7","8","9"};
        //最终的表达式，最好用StringBuilder，在非多线程的情况下，字符串拼接的性能，StringBuilder最好，
        //当然用StringBuffer或者单纯的String也可以
        StringBuilder expression=new StringBuilder();
        expression.append(source[0]);

        //先合并空格
        for(int i=0;i<result.length;i++){
            if(result[i]==0){//如果为0，表示数字合并
                //number.append(source[i+1]);
                expression.append(source[i+1]);
            } else if(result[i]==1){
                //sum=calc(operateChar,sum,number);
                //operateChar='+';
                //number.append(source[i+1]);
                expression.append("+").append(source[i+1]);
            }else if(result[i]==2){
                /*sum=calc(operateChar,sum,number);
                operateChar='-';
                number.append(source[i+1]);*/
                expression.append("-").append(source[i+1]);
            }if(result[i]==3){
                /*sum=calc(operateChar,sum,number);
                operateChar='*';
                number.append(source[i+1]);*/
                expression.append("*").append(source[i+1]);
            }else if(result[i]==4){
                /*sum=calc(operateChar,sum,number);
                operateChar='/';
                number.append(source[i+1]);*/
                expression.append("/").append(source[i+1]);
            }
        }

        sum = calcExpression(expression);
        if(sum==140){
            System.out.print(expression.toString()+"=140");
            System.out.println();
        }
    }

    public static int calcExpression(StringBuilder expression){

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

        try {
            String result = String.valueOf(scriptEngine.eval(expression.toString()));
            //System.out.println(result);
            if (result.contains(".")){
                return 0;
            }
            return Integer.parseInt(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
