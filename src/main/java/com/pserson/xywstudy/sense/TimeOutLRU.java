package com.pserson.xywstudy.sense;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：fqg
 * @ Date       ：Created in 12:12 2020/8/25
 */
public class TimeOutLRU {
    static class Node implements Comparable<Node>{
        String key;
        Object value;
        Long expireTime;
        public Node(){}
        public Node(String key, Object value, long expireTime){
            this.key = key;
            this.value = value;
            this.expireTime = expireTime;
        }
        //内比较器的重写
        @Override
        public int compareTo(Node o) {
            long l = this.expireTime - o.expireTime;
            if(l < 0) return -1;
            if(l > 0) return 1;
            return 0;
        }
    }
    //线程池中的worker线程
    static class ExpireThread implements Runnable{

        @Override
        public void run() {
            long now = System.currentTimeMillis();
            while (true) {
                Node n = queue.peek();
                if(n == null || n.expireTime > now) return;
                queue.remove(n);
                map.remove(n.key);
                System.out.println("清除成功" + n.key);
            }
        }
    }
    //内部容量,保证线程安全使用concurrentHashMap同时将queue设置成单例模式
    private static ConcurrentHashMap<String, Node> map;
    //使用优先队列，在队列内将Node进行排序
    private static volatile PriorityQueue<Node> queue;
    //线程池,这里使用定时任务线程池
    private static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
    //双从检查锁
    private PriorityQueue<Node> getInstance(){
        if(queue == null){
            synchronized(TimeOutLRU.class){
                if(queue == null){
                    queue = new PriorityQueue<>(1024);
                }
            }
        }
        return queue;
    }
    //constructor
    public TimeOutLRU(){
        map = new ConcurrentHashMap<>();
        queue = getInstance();
        //每隔三秒获取queue的头结点，查看是否过期。
        pool.scheduleWithFixedDelay(new ExpireThread(), 0, 3, TimeUnit.SECONDS);
    }
    //expose method
    public Object set(String key, Object value, long time){
        //获取过期时间
        long timeout = System.currentTimeMillis() + time;
        Node newNode = new Node(key, value, timeout);
        Node old = map.put(key, newNode);
        queue.offer(newNode);
        if(old != null){
            queue.remove(old);
            return old.value;
        }
        return null;
    }

    public Object get(String key){
        Node n = map.get(key);
        return n == null ? null : n.value;
    }

    public static void main(String[] args) throws InterruptedException {
        TimeOutLRU timeOutLRU = new TimeOutLRU();
        timeOutLRU.set("1","fqg",1000);
        timeOutLRU.set("2","qqq",2000);
        Thread.sleep(1000);
        Object str = timeOutLRU.get("1");
        System.out.println(str);
        Object str1 = timeOutLRU.get("2");
        System.out.println(str1);
    }
}