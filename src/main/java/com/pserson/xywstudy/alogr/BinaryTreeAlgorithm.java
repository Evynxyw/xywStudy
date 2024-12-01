package com.pserson.xywstudy.alogr;

import com.pserson.xywstudy.alogr.dto.ListNode;
import com.pserson.xywstudy.alogr.dto.TreeNode;
import org.springframework.beans.factory.config.ListFactoryBean;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 树的算法
 */
public class BinaryTreeAlgorithm {

    public static void main(String[] args) {
        /*TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode5;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode5.left = treeNode6;*/
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = node3;
        node3.next = node4;
        lt_109_sortedListToBST(listNode1);
    }

    /**
     * 前序遍历树 leetCode 144
     * 原理: 根 左 右
     * @param root
     * @return
     */
    public static List<Integer> lt_144_DgPreOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        result.add(root.val);
        result.addAll(lt_144_DgPreOrderTree(root.left));
        result.addAll(lt_144_DgPreOrderTree(root.right));
        return result;
    }

    /**
     * 前序遍历树 leetCode 144
     * 栈实现
     * 原理: 根 左 右
     * @param root
     * @return
     */
    public static List<Integer> lt_144_StackPreOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 递归实现
     * 中序遍历  左 中 右 leetCode 94
     * @param root
     * @return
     */
    public static List<Integer> lt_94_DgInOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        result.addAll(lt_94_DgInOrderTree(root.left));
        result.add(root.val);
        result.addAll(lt_94_DgInOrderTree(root.right));
        return result;
    }

    /**
     * 栈实现
     * 中序遍历  左 中 右 leetCode 94
     * @param root
     * @return
     */
    public static List<Integer> lt_94_StackInOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(!stack.isEmpty() || current != null){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    /**
     * 递归实现
     * 后序遍历 左 右 中 leetCode 145
     * @param root
     * @return
     */
    public static List<Integer> lt_145_DgPostOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        result.addAll(lt_145_DgPostOrderTree(root.left));
        result.addAll(lt_145_DgPostOrderTree(root.right));
        result.add(root.val);
        return result;
    }

    /**
     * 栈实现
     * 后序遍历 左 右 中 leetCode 145
     * @param root
     * @return
     */
    public static List<Integer> lt_145_StackPostOrderTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node.right == null && node.left == null){
                result.add(stack.pop().val);
            }
            if(node.right != null){
                stack.push(node.right);
                node.right = null;
            }
            if(node.left != null){
                stack.push(node.left);
                node.left = null;
            }
        }
        return result;
    }

    /**
     * 树的序列化
     *
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (node == null) {
                continue;
            }
            list.add(node.left);
            list.add(node.right);
        }
        //结果 1 2 3 null null 4 5 null null null null
        //去除最后的null
        while (list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }
        //结果  1 2 3 null null 4 5
        List<Integer> result = new ArrayList<>();
        list.forEach(item -> {
            result.add(item == null ? null : item.val);
        });
        return Arrays.toString(result.toArray());
    }

    /**
     * 树反序列化
     *
     * @param data
     * @return
     */
    public static TreeNode deserialize(String data) {
        if (data == null || data.equals("[]")) {
            return null;
        }
        String[] datas = data.substring(1, data.length() - 1).split(",");
        boolean isLeft = true;
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        List<TreeNode> result = new ArrayList<>();
        result.add(root);
        int index = 0;
        for (int i = 1; i < datas.length; i++) {
            if (!datas[i].trim().equals("null")) {
                TreeNode treeNode = new TreeNode(Integer.parseInt(datas[i].trim()));
                if (isLeft) {
                    result.get(index).left = treeNode;
                } else {
                    result.get(index).right = treeNode;
                }
                result.add(treeNode);
            }
            if (!isLeft) {
                index++;
            }
            isLeft = !isLeft;
        }
        return root;
    }

    /**
     * 树的右视图打印 leetCode 199
     * 思想：按照层来打印
     * @param root
     * @return
     */
    public static List<Integer> lt_199_rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            result.add(queue.peek().val);
            for (int i = 0; i < level; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
            }
        }
        return result;
    }


    /**
     * 二叉树最短路径和 leetCode 124
     * @param root
     * @return
     */
    public static int lt_124_maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = lt_124_maxPathSum(root.left);
        int rightMax = lt_124_maxPathSum(root.right);
        return Math.max(0, Math.max(leftMax, rightMax)) + root.val;
    }


    /**
     * 二叉树展开为单项列表 leetcode 114
     * @param root
     */
    public static void lt_114_treeToList(TreeNode root) {
        if(root == null){
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        preOrderTree(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    private static void preOrderTree(TreeNode root, List<TreeNode> list) {
        if(root == null){
            return;
        }
        list.add(root);
        preOrderTree(root.left, list);
        preOrderTree(root.right, list);
    }

    /**
     * 将二叉搜索树转化为排序的双向链表 leetcode 155
     * @param root
     * @return
     */
    public static TreeNode lt_155_treeToDoublyList(TreeNode root) {
        if(root == null){
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        help(root, list);
        TreeNode head = list.get(0);
        TreeNode tail = list.get(list.size() - 1);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private static void help(TreeNode root, List<TreeNode> list) {
        if(root == null){
            return;
        }
        help(root.left, list);
        list.add(root);
        if(list.size() >= 2){
            int n = list.size();
            TreeNode preNode = list.get(n - 2);
            TreeNode currentNode = list.get(n - 1);
            preNode.right = currentNode;
            currentNode.left = preNode;
        }
        help(root.right, list);
    }

    /**
     * 将素组转化为二叉搜索树 leetcode 108
     * @param nums
     * @return
     */
    private static TreeNode listNodeToTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        TreeNode treeNode = arrayToTree(nums, 0, nums.length - 1);
        return treeNode;
    }

    private static TreeNode arrayToTree(int[] nums, int start, int end) {
        if(start > end){
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = arrayToTree(nums, start, mid - 1);
        root.right = arrayToTree(nums, mid + 1, end);
        return root;
    }

    /**
     * 链表转化为二叉搜索树 leetcode 109
     * @param head
     * @return
     */
    private static TreeNode lt_109_sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        TreeNode treeNode = listToTree(head, null);
        return treeNode;
    }

    private static TreeNode listToTree(ListNode leftNode, ListNode rightNode) {
        if(leftNode == rightNode){
            return null;
        }
        //寻找中间节点
        ListNode slow = leftNode;
        ListNode fast = leftNode;
        while(fast != rightNode && fast.next != rightNode){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = listToTree(leftNode, slow);
        root.right = listToTree(slow.next, rightNode);
        return root;
    }
}

