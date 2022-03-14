package com.c02_linkedlist;

public class C04_Josepfu {
    public static void main(String[] args) {
        // 测试环形链表：新增、展示
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(4);
        circleSingleLinkedList.showBoy();
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first 节点，当前没有编号
    private Boy first = null;
    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for 来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                boy.setNext(boy);
                first = boy;
                curBoy = boy;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("环形链表为空");
            return;
        }
        // 因为first 不能动，因此我们仍然使用一个辅助指标完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy 编号[%d] \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 说明已经遍历完成
                break;
            }
            curBoy = curBoy.getNext(); // curBoy 后移
        }
    }
}

// 创建一个Boy 类,  表示一个节点
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点， 默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
