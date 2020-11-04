package com.leetcode.solution.algorithm;

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
public class DP {


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
        int maxL = n[l-1];

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
        for (int i = 1; i < l-1; i++) {
             max =  x[i]+y[i+1] >max ?(x[i]+y[i+1]):max;
        }


        return max;
    }


    public static void main(String[] args) {
        System.out.println(stock2(new int[]{8, 10,11,8,12}));
        System.out.println(stock2(new int[]{1,2,4 ,8,3,9,6,7}));

    }
}
