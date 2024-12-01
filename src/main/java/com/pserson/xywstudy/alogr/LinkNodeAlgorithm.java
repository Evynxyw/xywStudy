package com.pserson.xywstudy.alogr;

import com.pserson.xywstudy.alogr.dto.ListNode;

import java.util.*;

/**
 * 链表相关算法
 */
public class LinkNodeAlgorithm {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = node3;
        node3.next = node4;
        System.out.println(lt_203_removeElements(listNode1, 2));

    }


    /**
     * 两个链表相加 leetcode 2
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode lt_2_addTwoNumber1(ListNode head1, ListNode head2) {
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        int carry = 0;//大于10的数需要进1
        while(head1 != null && head2 != null){
            int total = head1.val + head2.val + carry;
            int number = total % 10;
            carry = total / 10;
            ListNode listNode = new ListNode(number);
            dummy.next = listNode;
            dummy = dummy.next;
            head1 = head1.next;
            head2 = head2.next;
        }

        while(head1 != null){
            int total = head1.val + carry;
            int number = total % 10;
            carry = total / 10;
            ListNode listNode = new ListNode(number);
            dummy.next = listNode;
            dummy = dummy.next;
            head1 = head1.next;
        }
        while(head2 != null){
            int total = head2.val + carry;
            int number = total % 10;
            carry = total / 10;
            ListNode listNode = new ListNode(number);
            dummy.next = listNode;
            dummy = dummy.next;
            head2 = head2.next;
        }
        if(carry > 0){
            ListNode listNode = new ListNode(carry);
            dummy.next = listNode;
        }
        return pre.next;
    }

    private static void outPutListNode(ListNode head) {
        while(head != null){
            System.out.print(head.val);
            head = head.next;
            if(head != null){
                System.out.print("->");
            }
        }
        System.out.println();
    }

    /**
     * 深度拷贝带随机指针的链表
     * @param head
     * @return
     */
    public ListNode copyLinkNode(ListNode head){
        if(head == null){
            return head;
        }
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode newHead = head;
        ListNode dummy = new ListNode(-1);
        while(newHead != null){
            ListNode listNodeTemp;
            if(!map.containsKey(newHead)){
                listNodeTemp = new ListNode(newHead.val);
                map.put(newHead, listNodeTemp);
            }else{
                listNodeTemp = map.get(newHead);
            }
            dummy.next = listNodeTemp;
            ListNode random = newHead.random;
            if(random != null){
                if(map.containsKey(random)){
                    ListNode newRandom = map.get(random);
                    listNodeTemp.random = newRandom;
                }else{
                    ListNode newRandom = new ListNode(random.val);
                    map.put(random, newRandom);
                    listNodeTemp.random = newRandom;
                }
            }
            newHead = newHead.next;
            dummy = listNodeTemp;
        }
        return map.get(head);
    }

    public ListNode copyLinkNode2(ListNode head){
        if(head == null){
            return head;
        }
        copyNode(head);
        copyRandom(head);
        return split(head);
    }

    private void copyNode(ListNode head) {
        while(head != null){
            ListNode copyListNode = new ListNode(head.val);
            ListNode originHeadNext = head.next;
            head.next = copyListNode;
            copyListNode.next = originHeadNext;
            head = originHeadNext;
        }
    }

    private void copyRandom(ListNode head) {
        while(head != null){
            if(head.random != null){
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private ListNode split(ListNode head) {
        ListNode newHead = head.next;
        while(head != null){
            ListNode copyListNode = head.next;
            head.next = copyListNode.next;
            head = copyListNode.next;
            if(copyListNode.next != null){
                copyListNode.next = head.next;
            }
        }
        return newHead;
    }

    /**
     * 链表反转  完全反转 leetcode 206
     * @param head
     * @return
     */
    public static ListNode lt_206_transNode1(ListNode head){
        if(head == null || head.next == null){
            return  head;
        }

        ListNode pre = head;
        ListNode current = head.next;
        pre.next = null;
        while(current != null){
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 链表反转  反转2 到 4 leetcode 92
     * 1->2->3->4->5
     * 1->4->3->2->5
     * @param head
     * @return
     */
    public static ListNode lt_92_transNode2(ListNode head, int m, int n){
        if(head == null || m >= n){
            return  head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        for(int i = 1; i < m; i ++){
            head = head.next;
        }
        ListNode preM = head;
        ListNode mListNode = head.next;
        ListNode nListNode = mListNode;
        ListNode postN = nListNode.next;

        for(int i = m; i < n; i++){
            ListNode next = postN.next;
            postN.next = nListNode;
            nListNode = postN;
            postN = next;

        }
        mListNode.next = postN;
        preM.next = nListNode;
        return dummy.next;
    }

    public static ListNode mergeAndSort(ListNode lead11, ListNode lead21) {
        if(lead11 == null){
            return lead21;
        }

        if(lead21 == null){
            return lead11;
        }


        //计算哪个链表头结点小
        if(lead11.val >= lead21.val){
            ListNode temp = lead11;
            lead11 = lead21;
            lead21 = temp;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = lead11;
        ListNode lead11Pos = lead11;
        while(lead21 != null){
            int nodeVal = lead21.val;
            while(lead11Pos != null){
                ListNode next = lead11Pos.next;
                if(next == null){
                    ListNode listNode = new ListNode(nodeVal);
                    lead11Pos.next = listNode;
                    break;
                }
                if(nodeVal <= next.val){
                    ListNode listNode = new ListNode(nodeVal);
                    lead11Pos.next = listNode;
                    listNode.next = next;
                    lead11Pos = listNode;
                    break;
                }
                lead11Pos = lead11Pos.next;
            }
            lead21 = lead21.next;

        }
        return dummy.next;
    }

    /**
     * 回文链表 leetcode 234
     * @param head
     * @return
     */
    public static boolean lt_234_isPalindrome(ListNode head) {
        if(head == null){
            return false;
        }
        //寻找链表的中间节点
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //当前slow所在的节点位中间节点，将后半部分链表反转
        slow = reverseNodeNew(slow);
        boolean isPalindrome = true;
        //比较前半部分链表和后半部分链表是否相等
        while(slow != null){
            if(head.val != slow.val){
                isPalindrome = false;
                break;
            }
            slow = slow.next;
            head = head.next;
        }
        return isPalindrome;
    }

    private static ListNode reverseNodeNew(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode preListNode = head;
        ListNode currentListNode = head.next;
        preListNode.next = null;
        while(currentListNode != null){
            ListNode next = currentListNode.next;
            currentListNode.next = preListNode;
            preListNode = currentListNode;
            currentListNode = next;
        }
        return preListNode;
    }

    /**
     * 160 相交链表 leetcode 160
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode lt_160_getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode pointA = headA;
        ListNode pointB = headB;
        while(pointA != pointB){
            if(pointA != null){
                pointA = pointA.next;
            }else{
                pointA = headB;
            }
            if(pointB != null){
                pointB = pointB.next;
            }else{
                pointB = headA;
            }
        }

        return pointB;
    }

    /**
     * 141 环形链表 leetcode 141
     * @param head
     * @return
     */
    public static boolean lt_141_hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 142 环形链表 II leetcode 142
     * @param head
     * @return
     */
    public static ListNode lt_142_hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                ListNode point = head;
                while(point != slow){
                    point = point.next;
                    slow = slow.next;
                }
                return point;
            }
        }
        return null;
    }

    /**
     * 19 删除链表的倒数第N个节点 leetcode 19
     * @param head
     * @param n
     * @return
     */
    public static ListNode lt_19_removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode firstNode = head;
        ListNode secondNode = head;
        for(int i = 0; i < n + 1; i ++){
            firstNode = firstNode.next;
        }
        while(firstNode != null){
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        secondNode.next = secondNode.next.next;
        return dummy.next;
    }

    /**
     * 148 对链表进行插入排序 leetcode 148
     * @param head
     * @return
     */
    public static ListNode lt_148_sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newNode = mergeAndSortImpl(head);
        return newNode;
    }

    private static ListNode mergeAndSortImpl(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //寻找链表的中间节点和尾节点
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode leftHead = head;
        ListNode rightHead = slow.next;
        slow.next = null;
        ListNode leftNode = mergeAndSortImpl(leftHead);
        ListNode rightNode = mergeAndSortImpl(rightHead);
        ListNode mergeNode = mergeNode(leftNode, rightNode);
        return mergeNode;
    }

    private static ListNode mergeNode(ListNode leftHead, ListNode rightHead) {
        if (leftHead == null){
            return rightHead;
        }
        if (rightHead == null){
            return leftHead;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(leftHead != null && rightHead != null){
            if(leftHead.val > rightHead.val){
                current.next = rightHead;
                rightHead = rightHead.next;
            } else{
                current.next = leftHead;
                leftHead = leftHead.next;
            }
            current = current.next;
        }
        if(leftHead != null){
            current.next = leftHead;
        }
        if(rightHead != null){
            current.next = rightHead;
        }
        return dummy.next;
    }

    /**
     * 203 移除链表元素 leetcode 203
     * @param head
     * @param val
     * @return
     */
    public static ListNode lt_203_removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            if(cur.val == val){
                pre.next = next;
            }else{
                pre = cur;
            }
            cur = next;
        }
        return dummy.next;
    }
}

