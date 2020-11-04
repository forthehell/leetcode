package com.leetcode.solution.leetcode;

/**
 * @author: Shawn
 * @date: 2020-10-26 10:20
 */
public class Stock {

    /**
     * f(n) = f(n-1) > (n-min(n-1))? f(n-1):(n-min(n-1))
     * <p>
     * <p>
     * 一个N的数组，每个n位置代表 n天的价格，股票只能一次买入/卖出，求一个最优的收益
     *
     * @return
     */
    public static int base(int[] n) {
        int l = n.length;
        if (l == 0 || l == 1) {
            return 0;
        }

        int min = n[0];
        int best = 0;

        for (int i = 1; i < l; i++) {
            int temp = n[i];
            best = best > (temp - min) ? best : (temp - min);
            if(temp < min){
                min = temp;
            }

        }
        return best;

    }


    /**
     * f(n) = f(n-1) + (n > last(n-1)? (n -last(n-1)):0)
     *
     * @param n
     * @return
     */
    public static int all(int[] n) {

        int l = n.length;
        if (l == 0 || l == 1) {
            return 0;
        }

        int min = n[0];
        int profit = 0;

        for (int i = 1; i < l; i++) {
            profit += n[i] > min ? (n[i] - min) : 0;
            min = n[i];
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(base(new int[]{8, 10, 9, 7, 1, 5}));
        System.out.println(base(new int[]{8, 2, 4, 6, 1, 5}));
        System.out.println(all(new int[]{5, 1, 6, 3, 8, 2, 4, 2}));


    }
}
