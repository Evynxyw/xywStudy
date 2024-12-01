package com.pserson.xywstudy.alogr;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs 广度搜索
 */
public class BfsAlgorithm {
    public static void main(String[] args) {

    }

    /**
     * 岛屿数量 leetcode 200
     * @param grid
     * @return
     */
    public static int lt_200_numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        if(grid[0] == null || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int num = 0;
        //记录小岛是否被遍历过
        boolean[][] visited = new boolean[row][column];

        for(int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                if(grid[i][j] == '1' && !visited[i][j]){
                    bfsSearchIsland(grid, i, j, visited);
                    num++;
                }
            }
        }
        return num;
    }

    private static void bfsSearchIsland(char[][] grid, int i, int j, boolean[][] visited) {
        //当前小岛的上下左右进行查看
        int[] kx = {1, -1, 0, 0};
        int[] ky = {0, 0, 1, -1};
        visited[i][j] = true;
        //对当前位置grid[i][j]进行周边位置查找看是否是小岛
        //存放当前小岛的坐标
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        //循环遍历小岛的周边位置
        while(!queue.isEmpty()){
            //获取当前小岛位置
            int[] current = queue.poll();
            int xCurrent = current[0];
            int yCurrent = current[1];
            for (int k = 0; k < kx.length; k++){
                //同步进行坐标移动
                int newX = xCurrent + kx[k];
                int newY = yCurrent + ky[k];
                //判断新坐标满足条件 不超限制  没有遍历过 是小岛
                if(newX >= 0 && newX < grid.length && newY >= 0
                        && newY < grid[0].length && !visited[newX][newY]
                        && grid[newX][newY] == '1'){
                    visited[newX][newY] = true;
                    //添加到队列中
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }
}
