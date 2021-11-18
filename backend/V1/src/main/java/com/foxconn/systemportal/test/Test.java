package com.foxconn.systemportal.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Date date = new Date();
        Date theDate = new Date(date.getTime() - 1000 * 60 * 60 * 24);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate nowDate = LocalDate.parse("2020-04-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        nowDate = nowDate.minusMonths(0);
        LocalDate nextDate = LocalDate.parse("2020-04-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(nextDate.compareTo(nowDate));
        System.out.println(nowDate.getMonthValue());
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(nowDate));
        System.out.println(sdf.format(date));
        System.out.println(sdf.format(theDate));
    }
}
