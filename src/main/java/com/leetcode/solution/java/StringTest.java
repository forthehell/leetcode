package com.leetcode.solution.java;

public class StringTest {

    public static void main(String[] args) {

        String str = "abc";
        String str3 = "abc";
        char data[] = {'a', 'b', 'c'};
        String str1 = new String(data);
        String str2 = new String("abc");

        System.out.println(str == str1);
        System.out.println(str == str2);
        System.out.println(str1 == str2);

        System.out.println(str == str3);

        System.out.println(str.equals(str1));
        System.out.println(str.equals(str2));
        System.out.println(str1.equals(str2));
    }
}
