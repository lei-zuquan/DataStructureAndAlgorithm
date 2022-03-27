package com.c03_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class C03_PolandNotation {
    public static void main(String[] args) {
        // 完成将一个中缀表达式转成后缀表达式的功能
        // 说明
        // 1.   1+((2+3)*4)-5 => 转成 1 2 3 4 * + 5 -
        // 2.   因为直接对str 进行操作，不方便，因此先将“1+((2+3)*4)-5” =》中缀的表达式对应的List
        //      即“1+((2+3)*4)-5”  =》 ArrayList [1,+,(,(,2,+,3,),*,4),-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4),-,5]

        /*
        // 先定义个逆波兰表达式
        // (3+4)*5 - 6 => 3 4 + 5 * 6 -
        // 4 * 5 - 8 + 60 + 8 / 2 = 4 5 * 8 - 60 + 8 2 / +
        // 说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        // String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
        // 思路
        // 1. 先将"3 4 + 5 * 6 -" => 放到ArrayList 中
        // 2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("List:" + list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);
        */
    }

    // 方法，将中缀表达式转成对应的list
    // s="1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List, 存放中缀表达式 对应的内容
        ArrayList<String> ls = new ArrayList<>();
        int i = 0; // 这时是一个指针，用于遍历中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            // 如果c 是一个非数字，就需要加入到ls
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; // i 需要后移
            } else { // 如果是一个数，需要考虑多位数
                str = ""; // 先将str 置成"" '0'[48] --> '9'[57]
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c; // 拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }


    // 将一个逆波兰表达式，依次将数据和运算符放入到ArrayList 中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele: split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的运算
    /*
        1) 从左到右扫描，将3和4 压入堆栈；
        2）遇到+ 运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算3+4的值，得7，再将7入栈；
        3）将5入栈；
        4）接下来是x 运算符，因此弹出5和7，计算出7*5=35，将35入栈；
        5）将6入栈；
        6）最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        // 创建一个栈，后缀表达式只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item: list) {
            // 这里使用正则表达式来取数据
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop 出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw  new RuntimeException("运算符有误");
                }
                // 把res 入栈
                stack.push("" + res);
            }
        }
        // 最后留在stack 中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
