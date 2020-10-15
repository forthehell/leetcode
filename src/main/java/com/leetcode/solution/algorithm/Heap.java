package com.leetcode.solution.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Date: 2019-11-08 10:41
 */
public class Heap {

    private static int[] heap = {10, 8, 7, 5, 6, 1, 5};


    public static int[] removeFirst(int[] heap) {

        int k = 0;
        heap[0] = heap[heap.length - 1];
        int[] result = new int[heap.length - 1];

        if (heap.length == 1) {
            return result;
        }

        for (; ; ) {
            int l = k * 2 + 1;
            int r = k * 2 + 2;


            int temp = heap[k];

            if (r >= heap.length - 1) {
                break;
            }
            if (r == heap.length - 2) {
                if (heap[l] > temp) {
                    heap[k] = heap[l];
                    heap[l] = temp;
                }
                break;
            }
            if (heap[l] < heap[r] && heap[k] < heap[r]) {
                heap[k] = heap[r];
                heap[r] = temp;
                k = r;
            } else if (heap[r] < heap[l] && heap[k] < heap[l]) {
                heap[k] = heap[l];
                heap[l] = temp;
                k = l;
            } else {
                break;
            }
        }
        System.arraycopy(heap, 0, result, 0, heap.length - 1);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(removeFirst(heap)));
    }

}
