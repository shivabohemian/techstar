package cn.com.cmbcc.techstar;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Answer5 class
 *
 * @author wangqiang
 * @date 2018/3/17
 */
public class Answer5 {
    private MappedByteBuffer[] mappedBufArray;
    private int count = 0;
    private int number;
    private FileInputStream fileIn;
    private long fileLength;
    private int arraySize;
    private static byte[] array;

    public Answer5(String fileName, int arraySize) throws IOException {
        this.fileIn = new FileInputStream(fileName);
        FileChannel fileChannel = fileIn.getChannel();
        this.fileLength = fileChannel.size();
        //number为映射文件个数
        this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
        this.mappedBufArray = new MappedByteBuffer[number]; //内存文件映射数组
        long preLength = 0;
        long regionSize = (long) Integer.MAX_VALUE; //连续区域的大小
        for (int i = 0; i < number; i++) {
            //将文件的连续区域映射到内存文件数组中
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength; //最后一片区域的大小
            }
            mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);
            preLength += regionSize; //下一片区域的开始
        }
        this.arraySize = arraySize;
    }

    public int read() throws IOException {
        if (count >= number) {
            return -1;
        }
        int limit = mappedBufArray[count].limit();
        int position = mappedBufArray[count].position();
        if (limit - position > arraySize) {
            array = new byte[arraySize];
            mappedBufArray[count].get(array);
            return arraySize;
        } else { //本内存文件映射最后一次读取数据
            array = new byte[limit - position];
            mappedBufArray[count].get(array);
            if (count < number) {
                count++; //转换到下一个内存文件映射
            }
            return limit - position;
        }
    }

    public void close() throws IOException {
        fileIn.close();
        array = null;
    }

    public static byte[] getArray() {
        return array;
    }

   /* public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = 0;
            String filePath = sc.next();
            String word = sc.next();
            Answer5 reader = new Answer5(filePath, 65536);
            long start = System.nanoTime();
            while (reader.read() != -1) {
                List<String> strList = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(new String(getArray()), ",.!\n");
                while (st.hasMoreTokens()) {
                    strList.add(st.nextToken());
                }
                for (String s : strList) {
                    StringTokenizer st2 = new StringTokenizer(s, " ");
                    while (st2.hasMoreTokens()) {
                        if (word.equals(st2.nextToken())) {
                            count++;
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }*/

    public static void print(String filePath,String word) throws IOException {
        int count = 0;
        Answer5 reader = new Answer5(filePath, 65536);
        long start = System.nanoTime();
        while (reader.read() != -1) {
            List<String> strList = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(new String(getArray()), ",.!\n");
            while (st.hasMoreTokens()) {
                strList.add(st.nextToken());
            }
            for (String s : strList) {
                StringTokenizer st2 = new StringTokenizer(s, " ");
                while (st2.hasMoreTokens()) {
                    if (word.equals(st2.nextToken())) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
