package com.pserson.xywstudy.alogr;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划
 */
public class DpAlgorithm {

    public static void main(String[] args) {
        /*System.out.println(climbStairs(6));
        System.out.println(climbStairs2(6));
        System.out.println(climbStairs3(6));*/


    }


    /**
     * 爬楼梯  动态规划
     * @param n
     * @return
     */
    public static int climbStairs2(int n){
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }

        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 2;
        for (int i = 2; i < n; i++) {
            steps[i] = steps[i - 1] + steps[i - 2];

        }
        return steps[n - 1];
    }

    /**
     * 爬楼梯  动态规划  改进 利用滚动数组
     * @param n
     * @return
     */
    public static int climbStairs3(int n){
        /*if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }*/

        if(n < 3){
            return n;
        }

        int[] steps = new int[3];
        steps[0] = 1;
        steps[1] = 2;
        for (int i = 2; i < n; i++) {
            steps[i % 3] = steps[(i - 1) % 3] + steps[(i - 2) % 3];

        }
        return steps[(n - 1) % 3];
    }

    /**
     * 爬楼梯  递归实现
     * @param n
     * @return
     */
    public static int climbStairs(int n){
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }
        return climbStairs(n - 1) + climbStairs( n - 2);

    }



}
