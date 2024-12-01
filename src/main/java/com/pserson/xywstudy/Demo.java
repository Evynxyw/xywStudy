package com.pserson.xywstudy;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static int calMaxSubStrLength(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Set<Character> uniqueChars = new HashSet<>();

        while (right < s.length()) {
            if (!uniqueChars.contains(s.charAt(right))) {
                uniqueChars.add(s.charAt(right));
                maxLength = Math.max(maxLength, uniqueChars.size());
                right++;
            } else {
                uniqueChars.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }

    public static String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            char currentChar = (char) ('A' + columnNumber % 26);
            result.append(currentChar);
            columnNumber /= 26;
        }

        return result.reverse().toString();
    }


    public static void main(String[] args) {
//        String s = "abcabcbb";
//        System.out.println(calMaxSubStrLength(s));

        int columnNumber = 28;
        System.out.println(convertToTitle(columnNumber));
    }

}
