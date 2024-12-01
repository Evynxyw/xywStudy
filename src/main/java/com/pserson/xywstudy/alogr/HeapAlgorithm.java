package com.pserson.xywstudy.alogr;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAlgorithm {
    public static void main(String[] args) {
        int[] nums = {2,5,7,9,2,1};
        System.out.println(Arrays.toString(topK(nums, 3)));
        System.out.println(Arrays.toString(topK(nums, 3)));
        int[][] points = {{1,3},{-2,2}};
        System.out.println(Arrays.toString(lt_973_kClosest(points, 1)[0]));
    }

    /**
     * 堆的基本操作
     * @return
     */
    public static void priorityQueueO(){
        PriorityQueue<Integer> minxPriorityQueue = new PriorityQueue<>(3, (o1, o2) -> o2 - o1);
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(3, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        minxPriorityQueue.add(9);
        minxPriorityQueue.add(1);
        minxPriorityQueue.add(3);
        minxPriorityQueue.add(0);
        minxPriorityQueue.add(6);
        System.out.println(minxPriorityQueue);
        maxPriorityQueue.add(9);
        maxPriorityQueue.add(1);
        maxPriorityQueue.add(3);
        maxPriorityQueue.add(0);
        maxPriorityQueue.add(6);
        System.out.println(maxPriorityQueue);
    }

    /**
     * 前K个最大数字
     * @param array
     * @param k
     * @return
     */
    public static int[] topK(int[] array, int k){
        int[] result = new int[k];
        if(array == null || array.length == 0){
            return result;
        }

        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>(k, (o1, o2) -> o1 - o2);
        for(int i = 0; i < array.length; i++){
            minPriorityQueue.add(array[i]);
            if(minPriorityQueue.size() > k){
                minPriorityQueue.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            result[k - i - 1] = minPriorityQueue.poll();
        }
        return result;
    }

    /**
     * 最接近原点的K个点 leetcode 973
     * @param points
     * @param k
     * @return
     */
    public static int[][] lt_973_kClosest(int[][] points, int k) {
        int[][] result = new int[k][2];
        if(points == null){
            return result;
        }
        PriorityQueue<int[]> maxPriorityQueue = new PriorityQueue<>(k + 1, (o1, o2) -> {
            int distance = 0;
            int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
            int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
            distance = distance2 - distance1;
            if(distance == 0){
                distance = o2[0] - o1[0];
            }
            if(distance == 0){
                distance = o2[1] - o1[1];
            }
            return distance;
        });

        for (int[] point : points) {
            maxPriorityQueue.add(point);
            if (maxPriorityQueue.size() > k) {
                maxPriorityQueue.poll();
            }
        }
        for(int i = 0; i < k; i ++){
            result[i] = maxPriorityQueue.poll();
        }
        return result;

    }
}
