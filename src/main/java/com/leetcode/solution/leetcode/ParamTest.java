package com.leetcode.solution.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

public class ParamTest {

    static void f(){
        List<String>  test = Lists.newArrayList("A","B");
        f(test);
        System.out.println(JSON.toJSONString(test));
    }

    static void f(List<String> t){
        t = Lists.newArrayList("C");
        System.out.println(JSON.toJSONString(t));

    }

    public static void main(String[] args) {
        f();
    }
}
