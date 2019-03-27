package cn.com.cmbcc.techstar;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer10 {

    /*public static void main(String args[]) throws Exception {

        String url = "s.cmbccdn.cn";

//		args = new String[1];
//		args[0] = "/Users/janko/Desktop/img/question10In.txt";


        File file = new File("D:\\zhouxiujie\\techstar\\src\\main\\data\\ipfile.txt");

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        List<String> inAdd = new ArrayList<String>();
        String line;
        while ((line = in.readLine()) != null) {
            inAdd.add(line);
        }
        in.close();

        try {
            Map<String, Boolean> validUrl = new HashMap();
            for (int i=0;i < inAdd.size(); i++){
                if(conn("https" + inAdd.get(i) + ":443/test.txt","s.cmbccdn.cn")){
                    validUrl.put(inAdd.get(i), true);
                }
            }

            for (String add : inAdd) {
                if (validUrl.containsKey(add) && validUrl.get(add)) {
                    System.out.println(add+" 服务正常");
                }else{
                    System.out.println(add+" 不正常");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public static boolean conn(String strUrl, String host) {
        URL url;
        try {
            url = new URL(strUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);// 设置允许输出
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Host", host);

            int code = conn.getResponseCode();
            if (code == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String retData = null;
                String responseData = "";
                while ((retData = in.readLine()) != null) {
                    responseData += retData;
                }
                in.close();
                if ("OK".equals(responseData)) {
                    return true;
                }

            }
        } catch (Exception e) {
        }
        return false;
    }

    public static void print(String filePath) throws IOException {
        String url = "s.cmbccdn.cn";

        File file = new File(filePath);

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        List<String> inAdd = new ArrayList<String>();
        String line;
        while ((line = in.readLine()) != null) {
            inAdd.add(line);
        }
        in.close();

        try {
            Map<String, Boolean> validUrl = new HashMap();
            for (int i = 0; i < inAdd.size(); i++) {
                if (conn("https" + inAdd.get(i) + ":443/test.txt", "s.cmbccdn.cn")) {
                    validUrl.put(inAdd.get(i), true);
                }
            }

            for (String add : inAdd) {
                if (validUrl.containsKey(add) && validUrl.get(add)) {
                    System.out.println(add + " 服务正常");
                } else {
                    System.out.println(add + " 不正常");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
