package com.foxconn.mcebg.portal.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();
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

    public static void test2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");

        Set<String> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }

        System.out.println("set.size() = " + set.size());
        int num = 0;
        for (String s : set) {
            num++;
            System.out.println("num = " + num + ", value = " + s);
        }
    }

    public static void test3() {
        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("2");
        listA.add("3");
        listA.add("4");

        List<String> listB = new ArrayList<>();
        listB.add("1");
        listB.add("4");
        listB.add("5");
        listB.add("6");

        List<String> listC = new ArrayList<>(listA);
        listC.retainAll(listB);
        System.out.println("listA与listB的交集:" + listC);

        List<String> listD = new ArrayList<>(listA);
        listD.removeAll(listC);
        System.out.println("listA对listB的补集:" + listD);
    }

    public static void test4() {
        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("2");
        listA.add("3");
        listA.add("4");

        List<String> listB = new ArrayList<>();
        listB.add("1");
        listB.add("2");
        listB.add("3");
        listB.add("4");
        listB.add("5");
        listB.add("6");
        listB.add("7");

        listB.removeAll(listA);
        System.out.println("listA对listB的补集:" + listB);
    }

    public static void test5() {
        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("1");
        listA.add("1");
        listA.add("1");
        listA.add("1");
        listA.add("1");
        listA.add("2");
        listA.add("3");
        listA.add("4");

        List<String> listB = new ArrayList<>();
        listB.add("1");
        listB.add("2");
        listB.add("3");
        listB.add("4");
        listB.add("4");
        listB.add("4");
        listB.add("4");
        listB.add("4");
        listB.add("5");
        listB.add("6");
        listB.add("7");

        Set<String> setA = new HashSet<>(listA);
        listA = new ArrayList<>(setA);
        Set<String> setB = new HashSet<>(listB);
        listB = new ArrayList<>(setB);

        System.out.println("setA:" + setA);
        System.out.println("listA:" + listA);

        System.out.println("setB:" + setB);
        System.out.println("listB:" + listB);

    }

    public static void test6() {
        String str = "Bearer xxxabc";

        int index = "Bearer ".length();
        String substr = str.substring(index);


        System.out.println("str = " + str);
        System.out.println("substr = " + substr);
    }
}
