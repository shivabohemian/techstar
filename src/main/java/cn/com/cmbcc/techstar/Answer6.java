package cn.com.cmbcc.techstar;

import java.io.File;
import java.util.Scanner;

/**
 * Answer6 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String fileName = sc.next();
            File dir = new File(fileName);
            System.out.println(getFileLength(dir));
        }
    }

    /**
     * 统计该文件夹大小
     */
    public static long getFileLength(File dir) {
        long len = 0;
        File[] subFiles = dir.listFiles();
        if (subFiles != null && subFiles.length != 0) {
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    len += subFile.length();
                } else {
                    len += getFileLength(subFile);
                }
            }
            return len;
        } else {
            return 0;
        }

    }

    public static void print(String fileName){
        File dir = new File(fileName);
        System.out.println(getFileLength(dir));
    }

}