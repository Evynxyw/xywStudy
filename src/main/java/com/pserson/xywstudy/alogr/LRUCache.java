package com.pserson.xywstudy.alogr;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
    private int capacity;
    private Map<Integer, CacheNode> cacheMap = new HashMap<>(capacity);

    private CacheNode head = new CacheNode(-1, -1);

    private CacheNode tail = new CacheNode(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)){
            return -1;
        }
        //获取当前节点
        CacheNode current = cacheMap.get(key);
        //维护当前节点前后节点的关系
        CacheNode preCurrent = current.pre;
        CacheNode postCurrent = current.next;
        preCurrent.next = postCurrent;
        postCurrent.pre = preCurrent;
        //移动当前节点到尾部
        moveToTail(current);

        return cacheMap.get(key).value;
    }



    private void moveToTail(CacheNode current) {
        CacheNode preTail = tail.pre;
        preTail.next = current;
        current.pre = preTail;
        current.next = tail;
        tail.pre = current;
    }

    public void put(int key, int value) {
        if(get(key) != -1){
            cacheMap.get(key).value = value;
            return;
        }

        if(cacheMap.size() == capacity){
            //将缓存中的最久未使用的淘汰，头结点的下一个节点删除
            CacheNode deleteNode = head.next;
            head.next = deleteNode.next;
            deleteNode.next.pre = head;
            cacheMap.remove(deleteNode.key);
        }
        CacheNode insertNode = new CacheNode(key, value);
        cacheMap.put(key, insertNode);
        moveToTail(insertNode);

    }



    static class CacheNode{
        int key;
        int value;
        CacheNode next;
        CacheNode pre;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
