package cn.com.cmbcc.techstar;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Answer2 {

    private static final String FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            String startDate = sc.next();
            String endDate = sc.next();

            int i=getSundayNum(startDate, endDate);
            if(i != -1){
                System.out.println(i);
            }

        }
    }

    /**
     * 计算两个日期之间Sunday天数
     * 参数format传 yyyy-MM-dd
     */
    public static int getSundayNum(String startDate, String endDate) {
        List yearMonthDayList = new ArrayList();
        Date start = null, end = null;
        try {
            start = new SimpleDateFormat(FORMAT).parse(startDate);
            end = new SimpleDateFormat(FORMAT).parse(endDate);
        } catch (ParseException e) {
            System.out.println("日期格式错误");
            return -1;
        }
        if (start.after(end)) {
            Date tmp = start;
            start = end;
            end = tmp;
        }
        //将起止时间中的所有时间加到List中
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime() <= end.getTime()) {
            yearMonthDayList.add(new SimpleDateFormat(FORMAT)
                    .format(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        Collections.sort(yearMonthDayList);
        int total = 0;//周日的总天数
        int week = 0;
        for (int i = 0; i < yearMonthDayList.size(); i++) {
            String day = (String)yearMonthDayList.get(i);
            week = getWeek(day, FORMAT);
            if (week == 0) {//周日
                total++;
            }
        }
        System.out.println("总天数： " + total);
        return total;
    }
    /**
     * 获取某个日期是星期几
     */
    public static int getWeek(String date, String format) {
        Calendar calendarTemp = Calendar.getInstance();
        try {
            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = calendarTemp.get(Calendar.DAY_OF_WEEK);
        int value=i-1;//0-星期日
        return value;
    }

}