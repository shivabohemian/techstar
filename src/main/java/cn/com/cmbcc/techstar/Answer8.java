package cn.com.cmbcc.techstar;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

public class Answer8 {

    public static void startRun(String host){
        URL url = null;
        try {
            url = new URL(host);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.connect();

             for (Certificate cert : connection.getServerCertificates()) {
            Date date = new Date();
            X509Certificate certificate = (X509Certificate) cert;
            System.out.println("颁发机构：" + certificate.getIssuerDN());
            System.out.println("有效开始时间:" + certificate.getNotBefore());
            System.out.println("有效结束时间:" + certificate.getNotAfter());
            System.out.println(
                    "是否在有效期内:" + (date.after(certificate.getNotBefore()) && date.before(certificate.getNotAfter()) ?
                            "有效期内" :
                            "有效期外"));
             }
        }catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
