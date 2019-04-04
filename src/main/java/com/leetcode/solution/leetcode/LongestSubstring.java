package com.leetcode.solution.leetcode;

import java.util.Scanner;

/**
 * @author forthehell
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0, end = 0, max = 1;
        for (int i = 1; i < s.length(); i++) {
            char now = s.charAt(i);
            for (int j = start; j <= end; j++) {
                char jchar = s.charAt(j);
                if (jchar == now) {
                    start = j + 1;
                }
            }
            end = i;
            if (end - start + 1 > max) {
                max = end - start + 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(lengthOfLongestSubstring(scanner.nextLine()));
        }


    }
}
