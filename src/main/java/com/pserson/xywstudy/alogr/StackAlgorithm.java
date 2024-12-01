package com.pserson.xywstudy.alogr;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 栈 相关算法
 */
public class StackAlgorithm {

    public static void main(String[] args) {
        int[] array = new int[]{4,2,5,3};
        System.out.println(lt_84_largestRectangleArea(array));
    }

    /**
     * 验证括号是否匹配 leetcode 20
     * @param s
     * @return
     */
    public static boolean lt_20_isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }

            if(c == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }
            if(c == '}'){
                if(stack.isEmpty() || stack.pop() != '{'){
                    return false;
                }
            }
            if(c == ']'){
                if(stack.isEmpty() || stack.pop() != '['){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 矩形最大面积 leetcode 84
     * @param heights
     * @return
     */
    public static int lt_84_largestRectangleArea(int[] heights) {
        int maxArea = 0;
        if(heights == null || heights.length == 0){
            return maxArea;
        }
        //使用哨兵
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        heights = newHeights;
        //存放矩形块的索引下标
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for(int i = 1; i < heights.length; i++){
            while(heights[i] < heights[stack.peek()]){
                int h = heights[stack.pop()];
                int w = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     * 解码字符串 leetcode 394
     * @param s
     * @return
     */
    public static String lt_394_decodeString(String s) {
        if(s == null || s.isEmpty()){
            return null;
        }
        int multi = 0;
        Stack<StringBuilder[]> stack = new Stack<>();
        stack.push(new StringBuilder[]{new StringBuilder("1"), new StringBuilder()});

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(Character.isAlphabetic(c)){
                stack.peek()[1].append(c);
            }
            if(Character.isDigit(c)){
                multi = multi * 10 + Integer.parseInt(c.toString());
            }
            if(c == '['){
                StringBuilder[] numBuild = new StringBuilder[]{new StringBuilder(multi + ""), new StringBuilder()};
                stack.push(numBuild);
                multi = 0;
            }
            if (c == ']') {
                //表述找到重复的字符串
                //弹出栈顶的字符串
                StringBuilder[] orignlBuild = stack.pop();
                int repeatNum = Integer.parseInt(orignlBuild[0].toString());
                String repeatStr = orignlBuild[1].toString();
                StringBuilder newStrBuild = new StringBuilder();
                while(repeatNum-- > 0){
                    newStrBuild.append(repeatStr);
                }
                //将新的字符串压入栈，拼接到下一个数组的字符串中
                stack.peek()[1].append(newStrBuild);
            }

        }
        return stack.pop()[1].toString();
    }

    /**
     * 栈中元素出栈顺序与入栈顺序相同，找出第一个不相同的元素
     * 每日温度 leetcode 739
     * @param temperatures
     * @return
     */
    public static int[] lt_739_dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0){
            return null;
        }
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int currentTemperature = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peek()] < currentTemperature){
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            result[stack.pop()] = 0;
        }
        return result;
    }

}

/**
 * 用两个栈实现队列 leetcode 232
 */
class Lt_232_MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public Lt_232_MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        return stack2.pop();
    }

    public int peek() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack2.isEmpty() && stack1.empty();
    }
}

/**
 * 两个队列实现栈 leetcode 225
 */
class Lt_225_MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public Lt_225_MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue2.add(x);
        while(!queue1.isEmpty()){
            queue2.add(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public Integer pop() {
        if(queue1.isEmpty()){
            return null;
        }
        return queue1.poll();
    }

    public Integer top() {
        if(queue1.isEmpty()){
            return null;
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * 最小栈 leetcode 155
 */
class Lt_155_MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public Lt_155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty()){
            minStack.push(x);
        }else{
            if(minStack.peek() < x){
                minStack.push(minStack.peek());
            }else{
                minStack.push(x);
            }
        }
    }

    public Integer pop() {
        if(stack == null){
            return null;
        }
        minStack.pop();
        return stack.pop();
    }

    public Integer top() {
        if(stack.isEmpty()){
            return null;
        }
        return stack.peek();
    }

    public Integer getMin() {
        if(minStack.isEmpty()){
            return null;
        }
        return minStack.peek();
    }
}
