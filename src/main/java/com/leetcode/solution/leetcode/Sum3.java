package com.leetcode.solution.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author forthehell
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Sum3 {


    /**
     *https://leetcode-cn.com/problems/subarray-sum-equals-k/
     * @return
     */
    public static int subarraySum(int[] nums,int k){
        int count = 0;
        int[] values = new int[nums.length];

        values[0] = nums[0];
        if( nums[0] == k){
            count++;
        }
        for(int i =1;i < nums.length;i++){
            values[i] = nums[i];
            if(nums[i] == k){
                count++;
            }
            for(int j = i-1 ; j >= 0 ;j--){
                values[j] = nums[i]+values[j];
                if(values[j] == k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums,int k){
        int count = 0;
        int[] sum = new int[nums.length+1];

        sum[0] = 0;
        for(int i = 0;i< nums.length;i++){
            sum[i+1] = nums[i]+sum[i];
        }
        for(int i = 1;i < sum.length;i++){
            for(int j = 0; j <i ;j++){
                if(sum[i] - sum[j] == k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum3(int[] nums,int k){
        int count = 0;
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        for(int i = 0;i< nums.length;i++){
            sum[i+1] = nums[i]+sum[i];
        }
        Map<Integer,Integer> cache = new HashMap<>(sum.length);
        for(int i = 0;i < sum.length;i++){
            count += cache.getOrDefault(sum[i] - k,0);
            cache.compute(sum[i],(key,v)->v ==null?1:++v);
        }
        return count;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int l = nums.length;
        int[][] two2 = new int[l * (l - 1)][2];
        int pt = 0;

        if (nums.length < 3) {
            return result;
        }
        for (int i = 1; i < l; i++) {
            for (int i3 = 0; i3 < pt; i3++) {
                int[] values = two2[i3];
                if (values[0] + values[1] + nums[i] == 0) {
                    result.add(Arrays.asList(values[0], values[1], nums[i]));
                }
            }
            int _tmp = nums[i - 1];
            if (_tmp == nums[i]) {
                if (i == 1 || nums[i - 2] != _tmp) {
                    two2[pt++] = new int[]{nums[i], _tmp};
                }
            } else {
                for (int k = i - 1; k >= 0; ) {
                    int tmp = nums[k--];
                    two2[pt++] = new int[]{nums[i], tmp};
                    while (k >= 0 && nums[k] == tmp) {
                        k--;
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        if (nums.length < 3) {
            return result;
        }

        if (nums[0] + nums[1] + nums[2] == 0) {
            result.add(Arrays.asList(nums[0], nums[1], nums[2]));
        }
        for (int i = 3; i < nums.length; i++) {
            if (nums[i] < 0) {
                continue;
            } else if (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                continue;
            } else if (nums[i] == nums[i - 1]) {
                for (int j = i - 2; j >= 0; j--) {
                    int tmp = nums[i] + nums[i - 1] + nums[j];
                    if (tmp == 0) {
                        result.add(Arrays.asList(nums[i], nums[i - 1], nums[j]));
                        break;
                    } else if (tmp <= 0) {
                        break;
                    }
                }
            } else {


                for (int j = i - 1; j > 0; j--) {
                    int numj = nums[j];
                    for (int k = j - 1; k >= 0; k--) {
                        int numk = nums[k];
                        int tmp = nums[i] + numj + numk;

                        if (nums[j] == numk) {
                            while (k > 0 && nums[k] == numk) {
                                k--;
                            }
                            j = k + 1;
                        }

                        if (tmp == 0) {
                            result.add(Arrays.asList(nums[i], numj, numk));
                            break;
                        } else if (tmp < 0) {
                            break;
                        }
                    }

                }
            }
        }

        return result;

    }

    public static void main(String[] args) {

        System.out.println(subarraySum3(new int[]{1,-1,0},0));
        System.out.println(subarraySum3(new int[]{1,1,1},2));
        System.out.println(subarraySum3(new int[]{2,1,3,-1},3));

//        System.out.println(JSON.toJSONString(threeSum1(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})));

    }
}
