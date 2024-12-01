package com.pserson.xywstudy.lz;

public class ListNode<T> {
    T val;
    ListNode<T> left;

    ListNode<T> right;

    ListNode<T> parrent;



    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
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

    public ListNode<T> getParrent() {
        return parrent;
    }

    public void setParrent(ListNode<T> parrent) {
        this.parrent = parrent;
    }
}
