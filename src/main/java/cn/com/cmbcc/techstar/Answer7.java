package cn.com.cmbcc.techstar;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by tinyspace on 2018/3/6.
 */
public class Answer7 {
    public static final int BUFFER = 1024;

    public static void main(String[] args) throws IOException {
        decompress(new File("src\\main\\data\\Q7\\1-sec.png"));
        decompress(new File("src\\main\\data\\Q7\\2-sec.png"));
        decompress(new File("src\\main\\data\\Q7\\3-sec.png"));
        decompress(new File("src\\main\\data\\Q7\\4-sec.png"));
        decompress(new File("src\\main\\data\\Q7\\5-sec.png"));

    }

    public static void decompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte[] data = new byte[BUFFER];

        byte[] bs = new byte[4];
        bs[0] = (byte) Integer.parseInt("89", 16);
        bs[1] = (byte) Integer.parseInt("50", 16);
        bs[2] = (byte) Integer.parseInt("4E", 16);
        bs[3] = (byte) Integer.parseInt("47", 16);
        os.write(bs);
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    public static void decompress(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath().replace("-sec", ""));
        decompress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();
    }

}
