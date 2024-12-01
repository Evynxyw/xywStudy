package com.pserson.xywstudy.demo;


import java.util.*;

public class Demo {
    public static void main(String[] args) {
        /*int[] nums = {1,2,3};
        for (List<Integer> subset : lt_46_permute(nums)) {
            System.out.println(Arrays.toString(subset.toArray()));
        }*/

//        int n = new Random().nextInt(1) + 10;
        int n = 4;
        System.err.println(n + "维");
        ListNode node = generatorArr(n);
        printListNode(node);

    }

    private static void printListNode(ListNode node) {
        ListNode row = node;
        while (row != null) {
            ListNode cur = row;
            while (cur != null) {
                ListNode down = cur.getDown();
                System.out.print(cur.val + " " + "up node:" + (cur.up == null ? null : cur.up.val) + "   ");
                cur = cur.right;
            }
            System.out.println();
            row = row.down;
        }

    }

    public static ListNode<Integer> generatorArr(int n) {
        if (n <= 0) {
            return null;
        }

        // 创建第一个节点
        ListNode<Integer> head = new ListNode<>();
        head.val = 1;
        // 创建第一维链表
        ListNode<Integer> current = head;
        for (int i = 2; i <= n; i++) {
            ListNode<Integer> newNode = new ListNode<>();
            newNode.setVal(i);
            current.setRight(newNode);
            newNode.setLeft(current);
            current = newNode;
        }
        // 创建剩余维度的链表
        ListNode<Integer> prevRowHead = head;
        ListNode<Integer> prevRowCurrent = prevRowHead;
        for (int row = 2; row <= n; row++) {
            ListNode<Integer> newRowHead = new ListNode<>();
            newRowHead.setVal((row - 1) * n + 1);
            prevRowCurrent.setDown(newRowHead);
            newRowHead.setUp(prevRowCurrent);
            current = newRowHead;
            for (int col = 2; col <= n; col++) {
                ListNode<Integer> newNode = new ListNode<>();
                newNode.setVal((row - 1) * n + col);
                current.setRight(newNode);
                newNode.setLeft(current);
                prevRowCurrent = prevRowCurrent.getRight();
                prevRowCurrent.setDown(newNode);
                newNode.setUp(prevRowCurrent);
                current = newNode;
            }
            prevRowHead = newRowHead;
            prevRowCurrent = prevRowHead;
        }
        return head;
    }


}
class ListNode<T> {
    T val;
    ListNode<T> up;
    ListNode<T> down;
    ListNode<T> left;
    ListNode<T> right;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public ListNode<T> getUp() {
        return up;
    }

    public void setUp(ListNode<T> up) {
        this.up = up;
    }

    public ListNode<T> getDown() {
        return down;
    }

    public void setDown(ListNode<T> down) {
        this.down = down;
    }

    public ListNode<T> getLeft() {
        return left;
    }

    public void setLeft(ListNode<T> left) {
        this.left = left;
    }

    public ListNode<T> getRight() {
        return right;
    }

    public void setRight(ListNode<T> right) {
        this.right = right;
    }
}