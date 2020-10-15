package com.leetcode.solution.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Date: 2019-11-08 10:36
 */
public class Merge {

    public static int[] merge(int[] a, int[] b) {

        int i = a.length - 1;
        int j = b.length - 1;

        int r[] = new int[a.length + b.length];
        int k = 0;

        for (; ; ) {
            if (i >= 0 && j >= 0) {
                r[k++] = ( a[i] >= b[j] ? a[i--] : b[j--]);
            } else if (i > 0) {
                r[k++] = a[i--];
            } else if (j >0){
                r[k++] = b[j--];
            } else {
                break;
            }
        }

        return r;

    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(merge(new int[]{1, 2, 8, 9,11,15}, new int[]{2, 5, 7, 10})));

    }
}
