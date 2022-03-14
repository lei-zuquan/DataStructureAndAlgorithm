package com.c02_linkedlist;

public class C03_DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");

        PeopleNode2 p1 = new PeopleNode2(1, "张三", 10);
        PeopleNode2 p2 = new PeopleNode2(2, "李四", 20);
        PeopleNode2 p3 = new PeopleNode2(3, "王五", 30);

        // 创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(p1);
        doubleLinkedList.add(p2);
        doubleLinkedList.add(p3);

        doubleLinkedList.list();

        // 修改
        PeopleNode2 newP2 = new PeopleNode2(2, "四哥", 24);
        doubleLinkedList.update(newP2);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.delete(5);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}


// 定义DoubleLinkedList 管理我们的人物
class DoubleLinkedList {

    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private PeopleNode2 head = new PeopleNode2(0, "", 0);

    public PeopleNode2 getHead() {
        return head;
    }

    public void add(PeopleNode2 peopleNode) {
        // 因为head 节点不能动，因此我们需要一个辅助遍历的temp
        PeopleNode2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if(temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp 后移
            temp = temp.next;
        }
        // 当退出while 循环时，temp 就是最后一个节点
        temp.next = peopleNode;
        peopleNode.prev = temp;
    }


    public void update(PeopleNode2 newPeopleNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no 编号
        PeopleNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("没有找到节点信息");
                break;
            }
            if (temp.no == newPeopleNode.no) {
                // 找到
                temp.name = newPeopleNode.name;
                temp.age = newPeopleNode.age;
                System.out.println("找到，并完成信息的更新");
                break;
            }
            temp = temp.next;
        }

    }

    // 删除节点
    public void delete(int no) {
        PeopleNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到数据，无法进行删除");
                break;
            }
            // head 1   2   3
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                break;
            }
            temp = temp.next;
        }
    }
    // 显示链表【遍历】
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        PeopleNode2 temp = head.next;
        while (true) {
            // 判断是否到链表的最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp 后移，否则会死循环
            temp = temp.next;
        }
    }
    // 获取到单向链表的节点的个数
    public int getLength() {
        PeopleNode2 temp = head;
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
}


// 定义PeopleNode, 每个PeopleNode 对象就是一个节点
class PeopleNode2 {
    public int no;
    public String name;
    public int age;
    public PeopleNode2 next; // 指向下一个节点
    public PeopleNode2 prev; // 指向前一个节点

    // 构造器
    public PeopleNode2(int no, String name, int age){
        this.no = no;
        this.name = name;
        this.age = age;
    }

    // 为了显示方便，重写toString
    @Override
    public String toString() {
        return "PeopleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}