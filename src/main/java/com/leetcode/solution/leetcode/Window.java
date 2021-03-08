package com.leetcode.solution.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Shawn
 * @date: 2021/3/4 2:41 PM
 */
public class Window {

    /**
     * 1423
     * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public static int maxScore(int[] cardPoints, int k) {
        int start = 0;
        for (int i = 0; i < k; i++) {
            start += cardPoints[i];
        }
        int max = start;
        for (int i = cardPoints.length - 1; i >= cardPoints.length - k; i--) {
            start = start - cardPoints[k - cardPoints.length + i] + cardPoints[i];
            max = Math.max(max, start);
        }
        return max;
    }

    /**
     * 76. Minimum Window Substring
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> maps = new HashMap<>();
        for (Character b : t.toCharArray()) {
            maps.compute(b, (k, v) -> v == null ? 1 : ++v);
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> _maps = new HashMap<>();
        int i = 0;
        int j = t.length();
        for (; ; ) {
            if (!maps.containsKey(chars[i])) {
                i++;
            }
        }
//        return "";
    }

    /**
     * * https://leetcode.com/problems/longest-substring-without-repeating-characters/ 3
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Integer[] pos = new Integer[127];
        for (int i = 0; i < 127; i++) {
            pos[i] = -1;
        }
        int i = 0;
        int j = 0;
        int max = 1;
        while (j < s.length()) {
            char sc = s.charAt(j);
            Integer _pos = pos[sc];
            if (_pos >= 0) {
                max = Math.max(max, j - i);
                for (int k = i; k <= _pos; k++) {
                    pos[s.charAt(k)] = -1;
                }
                i = _pos + 1;
            }
            pos[sc] = j;
            j++;
        }
        return Math.max(max, j - i);
    }


    /**
     * https://leetcode-cn.com/problems/permutation-in-string/ 567
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] s1Chars = new int[127];
        for (char c : s1.toCharArray()) {
            s1Chars[c] ++;
        }
        int i=0;
        int j=0;
        int[] windowChars = new int[127];
        while (j < s2.length()) {
            char c = s2.charAt(j);
            int count = s1Chars[c];
            if(count >=0 ) {
                windowChars[c]++;
                if(windowChars[c] == count && (j - i) == s1.length()-1){
                    return true;
                }
                while(windowChars[c] > count ){
                    windowChars[s2.charAt(i++)]--;
                }
                j++;
            }else{
                for(int k = i;k<j;k++){
                    windowChars[s2.charAt(k)] = 0;
                }
                i =j+1;
            }
        }
        return false;
    }


    /**
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/ 438
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }
        Map<Character, Integer> maps = new HashMap<>();
        for (Character b : p.toCharArray()) {
            maps.compute(b, (k, v) -> v == null ? 1 : ++v);
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char _char = s.charAt(i);
            if (maps.containsKey(_char)) {
                map.compute(_char, (k, v) -> v == null ? 1 : ++v);
            }
        }
        boolean equal = false;
        if (compare(maps, map)) {
            result.add(0);
            equal = true;
        }

        for (int j = 1; j <= s.length() - p.length(); j++) {
            char before = s.charAt(j - 1);
            char after = s.charAt(j + p.length() - 1);
            if (before == after) {
                if (equal) {
                    result.add(j);
                }
            } else {
                map.computeIfPresent(before, (k, v) -> v <= 1 ? null : --v);
                if (maps.containsKey(after)) {
                    map.compute(after, (k, v) -> v == null ? 1 : ++v);
                    if (equal) {
                        equal = false;
                    } else {
                        if (compare(maps, map)) {
                            result.add(j);
                            equal = true;
                        }
                    }
                } else {
                    equal = false;
                }

            }
        }


        return result;
    }

    public static <K, V> boolean compare(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null || map2 == null) {
            return false;
        }
        for (Map.Entry entry : map1.entrySet()) {
            if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        testcheckInclusion();
    }


    public static void testcheckInclusion() {
        System.out.println(checkInclusion("ab", "ab"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
        System.out.println(checkInclusion("ab", "eidbaooo"));

    }

    public static void testLengthOfLongestSubstring() {
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(""));
    }

    public static void testFindAnagrams() {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static void maxScore() {
        System.out.println(maxScore(new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30}, 8));
        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(maxScore(new int[]{2, 2, 2}, 3));
        System.out.println(maxScore(new int[]{100, 40, 17, 9, 73, 75}, 3));
        System.out.println(maxScore(new int[]{11, 49, 100, 20, 86, 29, 72}, 4));
        System.out.println(maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }
}
