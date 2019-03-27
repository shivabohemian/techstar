package cn.com.cmbcc.techstar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liudianbing on 2018/3/6.
 */
public class Answer9 {

    private static List<String> getFileData(String file) throws Exception {
        List<String> datas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            datas.add(line);
        }
        if (reader != null) reader.close();
        return datas;
    }

    public static List<String> SelectData(String file, String cardNo) throws Exception {

        cardNo = cardNo.replace("*", "[\\d]");
        List<String> datas = getFileData(file);
        List<String> results = new ArrayList<>();
        int count = 0;
        Pattern p = Pattern.compile(cardNo);
        for (String data : datas) {
            Matcher m = p.matcher(data);
            if (m.find()) {
                count++;
                results.add(data);
                if (count >= 30) break;
            }
        }
        return results;
    }

    /*public static void main(String[] args) {

        //生产一批已经使用的数据
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cardNo = sc.next();
            if (cardNo.length() > 4 && cardNo.length() < 7) {
                try {
                    List<String> datas = SelectData("src\\main\\data\\Q9\\used.txt", cardNo);
                    if (datas.size() == 0) {
                        System.out.println("未找到满足条件的");
                    } else {
                        for (String data : datas) {
                            System.out.println("6226 0208 8" + data);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("文件读取异常");
                }
            } else {
                System.out.println("只接受输入5位或者6位");
            }

        }

    }*/

    public static void print(String cardNo) {
        if (cardNo.length() > 4 && cardNo.length() < 7) {
            try {
                List<String> datas = SelectData("src\\main\\data\\Q9\\used.txt", cardNo);
                if (datas.size() == 0) {
                    System.out.println("未找到满足条件的");
                } else {
                    for (String data : datas) {
                        System.out.println("6226 0208 8" + data);
                    }
                }

            } catch (Exception e) {
                System.out.println("文件读取异常");
            }
        } else {
            System.out.println("只接受输入5位或者6位");
        }
    }
}
