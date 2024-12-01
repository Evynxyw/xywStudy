package com.pserson.xywstudy.alogr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * dfs 深度搜索
 */
public class DfsAlgorithm {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        for (List<Integer> list : lt_79_subsets(nums)) {
            System.out.println(Arrays.toString(list.toArray()));
        }

        List<List<String>> chessboard = lt_51_solveNQueens(4);
        for (int i = 0; i < chessboard.size(); i++) {
            for (String item : chessboard.get(i)) {
                System.out.println(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * 子集 leetcode 79
     * @param nums
     * @return
     */
    public static List<List<Integer>> lt_79_subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        dfsSearchSubsets(nums, result, list, 0);
        return result;
    }

    private static void dfsSearchSubsets(int[] nums, List<List<Integer>> result, List<Integer> list, int pos) {
        result.add(new ArrayList<>(list));
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            dfsSearchSubsets(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 全排列 leetcode 46
     * @param nums
     * @return
     */
    public static List<List<Integer>> lt_46_permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        dfsPermute(result, list, 0);
        return result;
    }
    private static void dfsPermute(List<List<Integer>> result, List<Integer> list, int pos) {
        if(pos == list.size()){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < list.size(); i++) {
            Collections.swap(list, pos, i);
            dfsPermute(result, list, pos + 1);
            Collections.swap(list, pos, i);
        }
    }

    /**
     * N皇后 leetcode 51
     * @param n
     * @return
     */
    public static List<List<String>> lt_51_solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        if(n == 0){
            return list;
        }
        List<Integer> cols = new ArrayList<>();
        search(list, cols, n);
        return list;
    }

    /**
     *
     * @param list
     * @param cols 表示每一行对对应的列
     * @param n
     */
    private static void search(List<List<String>> list, List<Integer> cols, int n) {
        if(cols.size() == n){
            list.add(drawChessboard(cols));
            return;
        }
        for (int colsIndex = 0; colsIndex < n; colsIndex++) {
            //判断当前colsIndex是否可以放皇后 不能放继续，判断下个列的位置，能放跳出循环
            //每一行只能放一个皇后
            if(!isValid(cols,colsIndex)){
                continue;
            }
            cols.add(colsIndex);
            search(list, cols, n);
            cols.remove(cols.size() - 1);
        }
    }


    private static boolean isValid(List<Integer> cols, int colsum) {
        int rowNum = cols.size();
        for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
            //如果是同一行
            if(cols.get(rowIndex) == colsum){
                return false;
            }
            //从左到右的斜线
            if(rowIndex - cols.get(rowIndex) == rowNum - colsum){
                return false;
            }
            //从右到左的斜线
            if (rowIndex + cols.get(rowIndex) == rowNum + colsum){
                return false;
            }
        }
        return true;
    }


    private static List<String> drawChessboard(List<Integer> cols) {
        List<String> chessboard = new ArrayList<>();
        for (int row = 0; row < cols.size(); row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < cols.size(); col++) {
                if(cols.get(row) == col){
                    sb.append("Q");
                }else{
                    sb.append(".");
                }
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }

    /**
     * 单词接龙 leetcode 108
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int lt_108_ladderLength(String beginWord, String endWord, List<String> wordList) {
        return -1;
    }
}
