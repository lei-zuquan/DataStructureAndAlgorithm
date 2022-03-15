package com.c03_stack;

public class C02_Calculator {
    public static void main(String[] args) {
        // 完成表达式
        String expression = "3+2*6-2";
        // 创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到的char 保存到ch
        // 开始while 循环的扫描expression
        while (true) {
            // 依次得到experssion 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch 是什么，然后做相应的处理
            if (operStack.isOper(ch)) { // 如果是运算符
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop 出两个数，
                    // 在从符号栈中pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);

                    }

                } else {
                    // 如果为空直接入栈
                }
            }
        }
    }
}

// 先创建一个栈，直接使用前面创建好
// 定义一个ArrayStack2 表示栈，需要扩展功能
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1; // top 表示栈顶，初始化为-1

    // 构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 增加一个方法，可以返回当前栈顶的值，但不是真正的pop
    public int peek() {
        return stack[top];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈-push
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈-pop，将栈顶的数据返回
    public int pop() {
        // 先判断栈是否空
        if (isEmpty()) {
            // 抛出异常， throw 本身带返回
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈【遍历栈】，遍历时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    // 数字越大，侧优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有+、-、*、/
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存储计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
