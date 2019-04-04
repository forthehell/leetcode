package com.leetcode.solution.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author forthehell
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Sum3 {


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

        System.out.println(JSON.toJSONString(threeSum1(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})));

    }
}
