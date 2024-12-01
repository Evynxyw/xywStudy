package com.pserson.xywstudy.od;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Test {


    public static void main(String[] args) {
        // 构建数字和字母之间的映射关系
        HashMap<String, Character> dic = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dic.put(Integer.toString(i), (char) ('a' + i - 1));
        }

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        // 构建一个栈
        Stack<String> stack = new Stack<>();

        // 遍历s中所有字符
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 如果是*号，说明*前面的两个数字要合并为一个两位数一起考虑
            if (ch == '*') {
                // 弹出栈顶两个元素，分别是个位和十位的字符串
                // 先弹出个位，再弹出10位
                String digit_1 = stack.pop();
                String digit_10 = stack.pop();
                // 将合并后的两位数重新压回栈顶
                stack.push(digit_10 + digit_1);
            }
            // 如果不是*号，而是数字，则直接入栈
            else {
                stack.push(Character.toString(ch));
            }
        }

        // 最后栈中的所有元素即为数字字符串，使用dic进行从数字到字母的映射之后再合并即可
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            String numStr = stack.pop();
            result.append(dic.get(numStr));
        }

        System.out.println(result.reverse().toString());
    }

}
