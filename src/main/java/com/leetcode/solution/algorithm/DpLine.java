package com.leetcode.solution.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @author: Shawn
 * @date: 2020-11-03 13:57
 * <p>
 * 原始问题划分一些列子问题
 * 求解子问题一次，保存结果，直接存取
 * 自底向上
 * <p>
 * <p>
 * 优化子结构 正确性
 * 重叠子问题 有效性
 * <p>
 * <p>
 * 1.分析优化解的结构
 * 2.递归的定义最优解的代价
 * 3.自底向上的计算最优解的代价保存之，并获取构造最优解的信息
 * 4.根据构造最优解的信息构造优化解
 */
public class DpLine {


    /**
     * 二次股票最高收益
     * <p>
     * f(n) = f(1,i)+f(j,n) (j>i)
     * <p>
     * f(1,i) =  f(i-1) > (i-min(i-1))? f(i-1):(i-min(i-1))
     * f(j,n) =
     *
     * @param n
     * @return
     */
    static int stock2(int[] n) {

        int l = n.length;
        if (l == 0 || l == 1) {
            return 0;
        }

        int minL = n[0];
        int[] x = new int[l];

        int[] y = new int[l];
        int maxL = n[l - 1];

        for (int i = 1; i < l; i++) {
            x[i] = x[i - 1] > (n[i] - minL) ? x[i - 1] : (n[i] - minL);
            if (n[i] < minL) {
                minL = n[i];
            }
        }
        for (int j = l - 2; j > 1; j--) {
            y[j] = (maxL - n[j]) > y[j + 1] ? (maxL - n[j]) : y[j + 1];
            if (maxL < n[j]) {
                maxL = n[j];
            }
        }

        if (l <= 2) {
            return x[1];
        }

        int max = 0;
        for (int i = 1; i < l - 1; i++) {
            max = x[i] + y[i + 1] > max ? (x[i] + y[i + 1]) : max;
        }


        return max;
    }

    /**
     *
     *  f(m,n) = min(f(m-1,n),f(n,m-1)))
     * @param n
     * @param m
     * @param n
     * @return
     */
    static int[] sumMin(int[][] array , int m, int n){

        int[][] result = new int[m][n];

        for(int i = 0;i < m ;i ++){

        }

        return null;
    }


    /**
     * 楼梯  1，2，3 三种
     * <p>
     * n= 1 return 1;
     * <p>
     * <p>
     * f(n) = f(n-1)+f(n-2)+f(n-3);
     *
     * @param n
     * @return
     */
    static int step(int n, int mod) {
        int[] step = new int[n+1];
        step[1] = 1;
        for (int i = 2; i <= n; i++) {
            step[i] += (i-3)>=0?step[i-3]:0;
            step[i] += (i-2)>=0?step[i-2]:0;
            step[i] += step[i-1];
        }
        return step[n]%mod;
    }

    /**
     * 最大连续和区间
     * f(n) =max(f(0),f(1)..f(n-1 ))
     * f[i] = (f(i-1) <= 0? f[i] : f[i-1]+ i )
     */
    static int[] maxSum(int[] n) {
        if (n == null || n.length <= 1) {
            return n;
        }

        int[][] maxSum = new int[n.length][];
        maxSum[0] = new int[]{n[0]};
        int[] sum = new int[n.length];
        sum[0] = n[0];
        int maxIndex = 0;

        for (int i = 1; i < n.length; i++) {
            if (sum[i - 1] <= 0) {
                sum[i] = n[i];
                maxSum[i] = new int[]{n[i]};
            } else {
                sum[i] = n[i] + sum[i - 1];
                int l = maxSum[i - 1].length + 1;
                maxSum[i] = new int[l];
                maxSum[i][l - 1] = n[i];
                System.arraycopy(maxSum[i - 1], 0, maxSum[i], 0, l - 1);
            }
            if (sum[i] > sum[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxSum[maxIndex];
    }

//    /**
//     * 最大连续乘区间
//     * f(n) =max(f(0),f(1)..f(n-1 ))
//     * f[i] = (f(i-1) <= 0? f[i] : f[i-1]+ i )
//     */
//    static int[] maxMulti(int[] n) {
//        if (n == null || n.length <= 1) {
//            return n;
//        }
//
//        int[][] maxSum = new int[n.length][];
//        maxSum[0] = new int[]{n[0]};
//        int[] sum = new int[n.length];
//        sum[0] = n[0];
//        int maxIndex = 0;
//
//        for (int i = 1; i < n.length; i++) {
//            if (sum[i - 1] <= 0) {
//                sum[i] = n[i];
//                maxSum[i] = new int[]{n[i]};
//            } else {
//                sum[i] = n[i] + sum[i - 1];
//                int l = maxSum[i - 1].length + 1;
//                maxSum[i] = new int[l];
//                maxSum[i][l - 1] = n[i];
//                System.arraycopy(maxSum[i - 1], 0, maxSum[i], 0, l - 1);
//            }
//            if (sum[i] > sum[maxIndex]) {
//                maxIndex = i;
//            }
//        }
//        return maxSum[maxIndex];
//    }

    /**
     * 无序数组，最长上升子序列长度(LIS)
     * <p>
     * f(n) = max(f(0),f(1)....f(n-1))+1
     *
     * @param n
     * @return
     */
    static int[] lis(int[] n) {

        if (n == null || n.length <= 1) {
            return n;
        }
        int[][] lis = new int[n.length][];
        lis[0] = new int[]{n[0]};
        int maxIndex = 0;
        for (int i = 1; i < n.length; i++) {
            int ni = n[i];
            int _maxIndex = -1;

            for (int j = 0; j < i; j++) {
                if (ni > n[j] && (_maxIndex < 0 || lis[j].length > lis[_maxIndex].length)) {
                    _maxIndex = j;
                }
            }
            int len = _maxIndex < 0 ? 1 : (lis[_maxIndex].length + 1);
            lis[i] = new int[len];
            lis[i][len - 1] = n[i];
            if (_maxIndex > 0) {
                System.arraycopy(lis[_maxIndex], 0, lis[i], 0, lis[_maxIndex].length);
            }

            if (len > lis[maxIndex].length) {
                maxIndex = i;
            }
        }
        return lis[maxIndex];
    }


    public static void main(String[] args) {


        System.out.println(JSON.toJSONString(step(4,100007)));
        System.out.println(JSON.toJSONString(step(6,100007)));

        System.out.println(JSON.toJSONString(maxSum(new int[]{1})));
        System.out.println(JSON.toJSONString(maxSum(new int[]{-2, 1})));
        System.out.println(JSON.toJSONString(maxSum(new int[]{1, 2, 3, -2, -1, 8, -10, 15, 1 - 4, -3, 1, 2})));

        System.out.println(stock2(new int[]{8, 10, 11, 8, 12}));
        System.out.println(stock2(new int[]{1, 2, 4, 8, 3, 9, 6, 7}));

        System.out.println(JSON.toJSONString(lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18})));
        System.out.println(JSON.toJSONString(lis(new int[]{2, 1})));
        System.out.println(JSON.toJSONString(lis(new int[]{1, 2, 3, -2, -1, -5, -4, -3, 1, 2})));

    }
}
