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

        PeopleNode newP = new PeopleNode(2, "李哥", 25);
        singleLinkedList.update(newP);

        System.out.println("update after");
        singleLinkedList.list();

        System.out.println("delete node test");
        singleLinkedList.delete(3);
        singleLinkedList.list();

        System.out.println("有效节点数：" + singleLinkedList.getLength());
        System.out.println(getBackTopKNode(singleLinkedList.getHead(), 1));
        System.out.println("反转链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }

    // 查找单链表中的倒数第K个结点【新浪面试题】
    // 思路：单向链表只能顺序访问，先获取链表的数量，使用：链表的数量 - 倒数第K个
    public static PeopleNode getBackTopKNode(PeopleNode head, int backTopK) {
        int count = 0;
        PeopleNode temp = head;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        if (backTopK > count) {
            System.out.printf("无法获取，链表数量为：[%d], 获取倒数[%d]\n", count, backTopK);
            return null;
        }

        temp = head.next;
        for (int i = 0; i < count - backTopK; i++) {
            temp = temp.next;
        }
        // 数据示例：head 1  2  3
        // count:3,  backTopK:1, count - backTopK = 2
        return temp;
    }

    // 将单链表反转
    // 思路：1.创建一个新的链表 2.将原来的链表遍历 每次插入到新链表的头部
    public static void reverseList(PeopleNode head) {
        PeopleNode newHead = new PeopleNode(0, "", 0);
        PeopleNode temp = head;
        while (temp.next != null) {
            // 1.首先将需要移动的节点记录起来
            PeopleNode needMove = temp.next;
            // 2.将需要移动的节点从老的单向链表移除掉
            temp.next = temp.next.next;

            // 3.将需要移动的节点移动至新的单向链表中
            // new: head --> 1
            // old: head --> 1 --> 2 --> 3
            if (newHead.next != null) {
                needMove.next = newHead.next;
            } else {
                needMove.next = null;
            }
            newHead.next = needMove;
        }
        // 将老的链表的头节点指向新的单向链表的第一个有效节点
        head.next = newHead.next;
        // 最后将新的链表头指向空，及时释放内存
        newHead.next = null;
    }

}

// 定义SingleLinkedList 管理我们的人物
class SingleLinkedList {

    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private PeopleNode head = new PeopleNode(0, "", 0);

    public PeopleNode getHead() {
        return head;
    }

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

    // 修改节点的信息，根据no编号来修改，即No 编号不能修改
    // 说明
    // 1.根据 newPeopleNode 的 no 来修改即可
    public void update(PeopleNode newPeopleNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no 编号
        PeopleNode temp = head.next;
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
        PeopleNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到数据，无法进行删除");
                break;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
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
    // 获取到单向链表的节点的个数
    public int getLength() {
        PeopleNode temp = head;
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
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
