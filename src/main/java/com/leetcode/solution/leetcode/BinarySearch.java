package com.leetcode.solution.leetcode;

import java.util.Arrays;

/**
 * @author: Shawn
 * @date: 2021/3/8 2:03 PM
 */
public class BinarySearch {

    /**
     * 704
     * @return
     */
    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;

        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target ){
                return mid;
            }else if (nums[mid] > target){
                right = mid -1;
            }else {
                left =  mid +1;
            }
        }

        return -1;

    }

    /**
     * 704
     * @return
     */
    public static int leftBinarySearch(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length -1;

        while(left < right){
            int mid = left + (right-left >>>1);
            if(nums[mid] == target ){
                right = mid;
            }else if (nums[mid] > target){
                right = mid ;
            }else {
                left =  mid +1;
            }
        }
        if(left >= nums.length){
            return  -1;
        }
        return nums[left]== target? left:-1;

    }

    /**
     * 704
     * @return
     */
    public static int rightBinarySearch(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = left + (right-left>>>1);
            if(nums[mid] == target ){
               left = mid +1 ;
            }else if (nums[mid] > target){
                right = mid ;
            }else {
                left =  mid +1;
            }
        }

        if(left ==0){
            return  -1;
        }
        return nums[left -1]== target? left-1:-1;

    }

    /**
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     * 34
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int[] r = new int[]{-1,-1};
        while(left <= right){
            int mid = (left+right)/2;
            if(nums[mid] == target){
                int start = left, end = mid;
                while(start <= end){
                    int midL = (start + end)/2;
                    if(nums[start] == target) {
                        r[0] = start;
                        break;
                    }else if(nums[midL] == target){
                        r[0] = midL;
                        end = midL-1;
                    }
                    else if(nums[midL] < target){
                        start = midL+1;
                    }else if(nums[midL] > target){
                        end = midL-1;
                    }
                }
                start = mid;
                end = right ;
                while (start <= end){
                    int midL = (start + end)/2;
                    if(nums[end]== target){
                        r[1] = end;
                        return r;
                    }else if(nums[midL] == target){
                        r[1] = midL;
                        start = midL+1;
                    }
                    else if(nums[midL] < target){
                        start = midL+1;
                    }else if(nums[midL] > target){
                        end = midL-1;
                    }
                }
                return r;
            }
            else  if (nums[mid] < target){
                left = mid+1;
            }
            else  if (nums[mid] > target){
                right = mid-1;
            }

        }
        return r;
    }

    public static void main(String[] args) {

        System.out.println(rightBinarySearch(new int[]{0,0,1,2,3,4,4},4));
        System.out.println(rightBinarySearch(new int[]{0,0,1,2,3,4,4},3));
        System.out.println(rightBinarySearch(new int[]{0,0,1,2,3,4,4},-1));
        System.out.println(rightBinarySearch(new int[]{0,0,1,2,3,4,4},0));
        System.out.println(rightBinarySearch(new int[]{0,1,1,2,3,4},5));


        System.out.println(leftBinarySearch(new int[]{0,0,1,2,3,4},1));
        System.out.println(leftBinarySearch(new int[]{0,0,1,2,3,4},5));
        System.out.println(leftBinarySearch(new int[]{0,0,1,2,3,4},-1));
        System.out.println(leftBinarySearch(new int[]{0,0,1,2,3,4},0));
        System.out.println(leftBinarySearch(new int[]{0,1,1,2,3,4},1));
//        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
//        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
//        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
//        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 5)));
//        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10)));
    }
}
