package com.leetcode.solution.java;

import java.util.Objects;

/**
 * @author: Shawn
 * @date: 2020-11-13 10:27
 */
public class Sync {


    public int i =10;

    public Object object = new Object();

    public  synchronized  int test(){

        synchronized (object){
            i++;

            try{
                if(i>5){
                    return i;
                }
            }finally {
                i=-1;
            }

        }
        return i;

    }

    public static void main(String[] args) {
        System.out.println(new Sync().test());
    }
}
