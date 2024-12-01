package com.pserson.xywstudy.lz;


public class Demo {

    public static void main(String[] args) {
        int n = 3;
        int nums = 2;
        for(int i = 0; i < n; i ++){
            nums = nums * 2;
        }
        ListNode<Integer> parrent = arrayToTree(0, nums - 1);
        System.out.println(12);
    }

    private static ListNode<Integer> arrayToTree(int start, int end) {
        if(start > end){
            return null;
        }
        int mid = start + (end - start) / 2;
        ListNode<Integer> root = new ListNode<>();
        root.left = arrayToTree(start, mid - 1);
        root.parrent = root;
        root.right = arrayToTree(mid + 1, end);
        root.right = root;
        return root;
    }


}
