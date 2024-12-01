package com.pserson.xywstudy.alogr;

import java.util.*;

/**
 * 单词阶梯
 */
public class BfsWordLadderDemo {

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        Set<String> set = new HashSet<>();
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        System.out.println(ladderLength(start, end, set));
    }

    public static int ladderLength(String start, String end, Set<String> dict) {
        int step = 1;
        if(dict == null){
            return 0;
        }
        dict.add(end);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> duplicate = new HashSet<>();
        duplicate.add(start);
        while (!queue.isEmpty()){
            int size = queue.size();//下一步有多少个
            step ++;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                List<String> nextWords = getNext(word, dict);
                for (String nextWord : nextWords) {
                    if(duplicate.contains(nextWord)){
                        continue;
                    }
                    if(nextWord.equals(end)){
                        return step;
                    }
                    duplicate.add(nextWord);
                    queue.offer(nextWord);
                }
            }

        }
        return -1;
    }

    private static List<String> getNext(String word, Set<String> dict) {
        List<String> next = new ArrayList<>();
        for(char i = 'a'; i < 'z'; i++){
            for (int j = 0; j < word.length(); j++) {
                String mayNext = changeWord(word, i, j);//可能的下一个
                if(dict.contains(mayNext)){
                    next.add(mayNext);
                }
            }
        }
        return next;
    }

    private static String changeWord(String word, char c, int i) {
        char[] words = word.toCharArray();
        words[i] = c;
        return new String(words);
    }
}
