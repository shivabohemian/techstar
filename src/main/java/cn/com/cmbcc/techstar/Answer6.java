package cn.com.cmbcc.techstar;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Answer6 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer6 {
    private static final CloseableHttpClient httpClient = createSSLClientDefault();
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public static void print(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(args.length - 1);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("nWidth的值依次为：");
            for (int i = 1; i < args.length; i++) {
                Future<String> future = executorService.submit(new PostThread(args[i]));
                sb.append(future.get());
            }
            System.err.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }

    /**
     * 解析xml文件
     *
     * @param entity
     * @return
     */
    static private String parseElement(HttpEntity entity) {
        String nWidth;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析xml文档，先获取
            Document doc = builder.parse(entity.getContent());
            NodeList nodeList = doc.getElementsByTagName("PerspectiveTransform");
            //获取PerspectiveTransform节点的nWidth属性
            nWidth = nodeList.item(0).getAttributes().getNamedItem("nWidth").getNodeValue();
        } catch (Exception e) {
            nWidth = "-1 ";
        }
        if ("".equals(nWidth) || nWidth == null) {
            nWidth = "-1 ";
        }
        return nWidth + " ";
    }

    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        //信任所有
                        public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 发送请求线程
     */
    static class PostThread implements Callable<String> {
        String numStr;

        public PostThread(String numStr) {
            this.numStr = numStr;
        }

        @Override
        public String call() {
            String str;
            try {
                int num = Integer.parseInt(numStr);
                if (num < 1 || num > 10) {
                    return "-1 ";
                }
                String url = "https://exam.cmbccdn.cn/" + num + ".xml";
                //执行post请求，获得对应的响应实例
                HttpPost httpPost = new HttpPost(url);
                CloseableHttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return "-1 ";
                }
                HttpEntity entity = response.getEntity();
                str = parseElement(entity);
            } catch (IOException e) {
                str = "-1 ";
            }
            return str;
        }
    }

}