package com.c02_linkedlist;

public class C01_SingleLinkedList {
    public static void main(String[] args) {
        // 测试数据
        // 先创建节点
        PeopleNode p1 = new PeopleNode(1, "张三", 10);
        PeopleNode p2 = new PeopleNode(2, "李四", 20);
        PeopleNode p3 = new PeopleNode(3, "王五", 30);

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 加入
//        singleLinkedList.add(p1);
//        singleLinkedList.add(p2);
//        singleLinkedList.add(p3);
        singleLinkedList.addByOrder(p2);
        singleLinkedList.addByOrder(p3);
        singleLinkedList.addByOrder(p1);
        singleLinkedList.addByOrder(p1);
        singleLinkedList.addByOrder(p3);


        // 显示打印
        singleLinkedList.list();
    }
}

// 定义SingleLinkedList 管理我们的人物
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private PeopleNode head = new PeopleNode(0, "", 0);

    // 添加节点到单向链表
    // 思路，当不考虑编号顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add(PeopleNode peopleNode) {
        // 因为head 节点不能动，因此我们需要一个辅助遍历的temp
        PeopleNode temp = head;
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
        // 将最后这个节点的next 指向新的节点
        temp.next = peopleNode;
    }

    // 根据排名将人物插入到指定位置（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(PeopleNode peopleNode) {
        PeopleNode temp = head;
        while (true) {
            // 如果遍历到了链表的尾部，则直接插入
            if (temp.next == null) {
                temp.next = peopleNode;
                peopleNode.next = null;
                break;
            }
            // 如果在遍历期间，发现数据已在，不再插入
            if (peopleNode.no == temp.next.no) {
                System.out.println("数据重复，不再插入");
                break;
            }
            // 如果在遍历期间，发现需要在中间插入则插入
            if (peopleNode.no < temp.next.no) {
                peopleNode.next = temp.next;
                temp.next = peopleNode;
                break;
            }

            temp = temp.next; // 后移，遍历
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
        PeopleNode temp = head.next;
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

}

// 定义PeopleNode, 每个PeopleNode 对象就是一个节点
class PeopleNode {
    public int no;
    public String name;
    public int age;
    public PeopleNode next; // 指向下一个节点

    // 构造器
    public PeopleNode(int no, String name, int age){
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
