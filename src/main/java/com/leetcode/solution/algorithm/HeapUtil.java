package com.leetcode.solution.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.StringJoiner;

/**
 * @Date: 2019-11-08 10:41
 */
public class HeapUtil {


    private static Integer[] heap = {10, 8, 7, 5, 6, 1, 5};


    /**
     * 堆 根 1
     *
     * @param <T>
     */
    public static class Heap<T extends Comparable> {

        Object[] nodes;
        int top;
        int max;

        public Heap(int max, T[] nodes) {
            this.nodes = new Object[max + 1];
            System.arraycopy(nodes, 0, this.nodes, 1, nodes.length);
            this.top = nodes.length;
            this.max = max;
            heapify();
        }

        public Heap(T[] nodes, int max) {
            this.nodes = new Object[max + 1];
            System.arraycopy(nodes, 0, this.nodes, 1, nodes.length);

            this.top = nodes.length;
            this.max = max;
        }

        /**
         *
         */
        public void heapify() {
            for (int j = top / 2; j >= 1; j--) {
                int i = j;
                for (; ; ) {
                    int li = i << 1;
                    int ri = li + 1;
                    T parent = (T) nodes[i];
                    T right = ri <= top ? (T) nodes[ri] : null;
                    T left = li <= top ? (T) nodes[li] : null;
                    if (left == null) {
                        break;
                    }
                    T max = right == null ? left : (left.compareTo(right) >= 0 ? left : right);
                    if (max.compareTo(parent) <= 0) {
                        break;
                    }
                    nodes[i] = max;
                    if (max == left) {
                        i=li;
                        nodes[i] = parent;
                    } else {
                        i=ri;
                        nodes[i] = parent;
                    }
                }
            }
        }

        public T max() {
            if (top == 0) {
                return null;
            }
            T result = (T) nodes[1];
            int i = 1;
            nodes[i] = nodes[top];
            nodes[top] = null;
            --top;
            for (; ; ) {
                int li = i << 1;
                int ri = li + 1;

                T parent = (T) nodes[i];
                T right = ri <= top ? (T) nodes[ri] : null;
                T left = li <= top ? (T) nodes[li] : null;
                if (left == null) {
                    break;
                }
                T max = right == null ? left : (left.compareTo(right) >= 0 ? left : right);
                if (max.compareTo(parent) <= 0) {
                    break;
                }
                nodes[i] = max;
                if (max == left) {
                    i = li;
                    nodes[i] = parent;
                } else {
                    i = ri;
                    nodes[i] = parent;
                }
            }

            return result;
        }

        /**
         * @param value
         */
        public boolean add(T value) {
            if (top >= max || value == null) {
                return false;
            }
            if (top == 0) {
                nodes[1] = value;
                return true;
            }
            int hole = top + 1;
            for (; ; ) {
                int parent = hole / 2;
                if (parent < 1 || ((T) nodes[parent]).compareTo(value) >= 0) {
                    break;
                }
                nodes[hole] = nodes[parent];
                hole = parent;
            }
            nodes[hole] = value;
            ++top;

            return true;
        }

        @Override
        public String toString() {
            Object[] obj = new Object[top];
            System.arraycopy(nodes, 1, obj, 0, top);

            return new StringJoiner(", ", Heap.class.getSimpleName() + "[", "]")
                    .add("nodes=" + JSON.toJSONString(obj))
                    .add("top=" + top)
                    .add("max=" + max)
                    .toString();
        }

    }


    public static void main(String[] args) {

        Integer[] _heap = {0, 7, 6, 5, 6, 1, 5,8,9,4,11};
        Heap heap1 = new Heap(100, _heap);
        System.out.println(heap1);


        System.out.println("max=" + heap1.max());
        System.out.println(heap1);
        System.out.println("max=" + heap1.max());
        System.out.println(heap1);
//
//        Heap _heap = new Heap<>(heap, 100);
//        System.out.println(_heap);
//        System.out.println("max=" + _heap.max());
//        System.out.println(_heap);
//        System.out.println("max=" + _heap.max());
//        System.out.println(_heap);
//
//
//        _heap.add(4);
//        System.out.println(_heap);
//
//        _heap.add(3);
//        System.out.println(_heap);
//
//        _heap.add(9);
//        System.out.println(_heap);
//
//        _heap.add(8);
//        System.out.println(_heap);
    }


}
