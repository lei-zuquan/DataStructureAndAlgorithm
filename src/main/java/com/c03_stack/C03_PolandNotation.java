package com.c03_stack;

public class C03_PolandNotation {
    public static void main(String[] args) {
        // 先定义个逆波兰表达式
        // (3+4)*5 - 6 => 3 4 + 5 * 6 -
        // 说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        // 思路
        // 1. 先将"3 4 + 5 * 6 -" => 放到ArrayList 中
        // 2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算
    }
}
