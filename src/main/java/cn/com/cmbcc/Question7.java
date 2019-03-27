package cn.com.cmbcc;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @author janko
 * @date 2018/3/17
 * @desc
 */
public class Question7 {

    public static void main(String arg[]) throws Exception {

        String projectPath = System.getProperty("user.dir");
        String originalImgPath = projectPath + "\\src\\main\\data\\Q7\\2-sec.png";

        //需要修改一下
       // String originalImgPath = "D:/zhouxiujie/techstar/src/main/data/Q7/1-sec";

        byte[] startByte = new byte[4];
        startByte[0] = (byte) Integer.parseInt("89", 16);
        startByte[1] = (byte) Integer.parseInt("50", 16);
        startByte[2] = (byte) Integer.parseInt("4E", 16);
        startByte[3] = (byte) Integer.parseInt("47", 16);

        File file = new File(originalImgPath);
        File[] originalImgs = file.listFiles();
        for (int i = 1; i <= originalImgs.length; i++) {
            byte[] bytes = readContent(originalImgs[i - 1].getPath());
            String targetPath = originalImgPath + "/" + i + ".png";
            if (bytes != null && bytes.length != 0) {
                byte[] unCompressRet = uncompress(bytes);
                byte[] result = new byte[4 + unCompressRet.length];
                System.arraycopy(startByte, 0, result, 0, 4);
                System.arraycopy(unCompressRet, 0, result, 4, unCompressRet.length);
                writeContent(result, targetPath);
            }

        }

    }

    public static byte[] uncompress(byte[] bytes) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
        }
        return out.toByteArray();
    }


    public static void writeContent(byte[] bytes, String path) throws Exception {

        FileOutputStream output = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                return;
            }
            output = new FileOutputStream(file);
            output.write(bytes);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
    public static byte[] readContent(String filePath) throws Exception{
        FileInputStream fi = null;
        try {
            File file = new File(filePath);
            long fileSize = file.length();
            fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                return null;
            }
            return buffer;
        }finally {
            if (fi != null) {
                fi.close();
            }
        }
    }
}