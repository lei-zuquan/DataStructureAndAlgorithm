package com.c02_linkedlist;

import java.util.Stack;

public class C02_TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("111");
        stack.add("222");
        stack.add("333");
        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
