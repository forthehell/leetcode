package com.leetcode.solution.leetcode;

import java.util.Arrays;

/**
 * @author: Shawn
 * @date: 2021/3/3 11:37 AM
 */
public class Bit {

    /**
     * https://leetcode-cn.com/problems/decode-xored-array/ 1720
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int[] result =new int[encoded.length+1];
        result[0] = first;

        for(int  i = 0;i < encoded.length;i++){
            result[i+1] = result[i]^ encoded[i];
        }
        return result;
    }

    //1734. Decode XORed Permutation
    public static int[] decode(int[] encoded) {
        int[] result = new int[encoded.length+1];
        int v =0;
        for(int i = 1; i <= result.length ; i++){
            v ^= i;
        }

        int v1 = 0;
        for(int i =1 ;i < encoded.length;i += 2){
            v1 ^=  encoded[i];
        }
        result[0] = v^v1;
        for(int i= 0 ;i < encoded.length;i++){
            result[i+1] = encoded[i] ^ result[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(decode(new int[]{3, 1})));
        System.out.println(Arrays.toString(decode(new int[]{6, 5, 4, 6})));
    }
}
