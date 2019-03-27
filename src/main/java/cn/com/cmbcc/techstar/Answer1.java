package cn.com.cmbcc.techstar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Answer1 {
    private Integer mutex = 1;
    private static long total = 1;//保存质数和值
    private static final int MAXVALUE = 1000000;//求MAXVALUE范围内的质数和值
    private static final int THREADAMOUNT = 10;//线程数量
    private static final int SPAN = MAXVALUE/THREADAMOUNT;//每个线程统计数据的跨度
    private static final CountDownLatch countDownLatch = new CountDownLatch(THREADAMOUNT);
    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        sumPrime();
        System.out.println("计算耗时为： " + (System.currentTimeMillis() - startTime)/1000);
    }

    public static long sumPrime() {
        Answer1 an = new Answer1();
        List<MyThread> threadList = new ArrayList<>();
        for(int i=1; i<=THREADAMOUNT; i++) {
            threadList.add(an.new MyThread((i-1)*SPAN, i*SPAN, countDownLatch));
        }
        for(int i=0; i<threadList.size(); i++) {
            threadList.get(i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("和值为： " + total);
        return total;
    }

    class MyThread extends Thread {
        private int start;
        private int end;
        private CountDownLatch countDownLatch;
        public MyThread(int start, int end, CountDownLatch countDownLatch) {
            this.start = start;
            this.end = end;
            this.countDownLatch = countDownLatch;
        }
        public void run() {
            for (int i = start + 1; i < end; i++) {
                synchronized (mutex) {
                    boolean flag = true;
                    for (int j = 2; j <= Math.ceil(Math.sqrt(i)); j++) {
                        if (i % j == 0) {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        total += i;
                        //System.out.println(i + "是素数");
                    }
                }
            }
            countDownLatch.countDown();
        }
    }
}