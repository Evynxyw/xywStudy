package com.pserson.xywstudy.alogr;

public class ArraySearchBinarySearchAlgo {

    public static void main(String[] args) {
        testWoodCut();
    }



    /**
     * 二分查找 基本方法
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static boolean binarySearch(int start, int end, int target){
        if(start < end){
            return false;
        }
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(mid == target){
                return true;
            }else if(mid < target){
                start = mid;
            }else if(mid > target){
                end = target;
            }
        }
        if(start == target || end == target){
            return true;
        }
        return false;
    }


    /**
     * 有序数组二分查找 leetcode 704
     * @param nums
     * @param target
     * @return
     */
    public int lt_704_binarySearchOrderNums(int[] nums, int target) {
        if (nums == null && nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length -1;
        int mid;
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(target < nums[mid]){
                end = mid;
            }else if(target > nums[mid]){
                start = mid;
            }
        }
        if(nums[start] == target){
            return  start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    /**
     * 旋转数组中二分查找目标值的下标 leetcode 33
     * @param nums
     * @param target
     * @return
     */
    public static int lt_33_binaryRatioSearch(int[] nums, int target){
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(nums[start] < nums[mid]){
                if(target >= nums[start] && target <= nums[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else{
                if(target >= nums[mid] && target <= nums[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        if(nums[start] == target){
            return start;
        }
        if(nums[end] == target){
            return end;
        }
        return -1;
    }

    /**
     * 寻找旋转数组中最小值 leetcode 153
     * @param nums
     * @return
     */
    public static int lt_153_binaryRatioMin(int[] nums){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int minTarget = nums[end];
        int mid;
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(nums[mid] <= minTarget){
                end = mid;
            }else{
                start = mid;
            }
        }
        return Math.min(nums[start], nums[end]);
    }

    /**
     * 砍树
     * 给定数组[232,124,456]
     * k = 7
     * 输出114
     * 给定数组[1,2,3]
     * k = 7
     * 输出0
     * @return
     */
    public static int woodCut(int[] L, int k){
        if(L == null || L.length == 0){
            return 0;
        }

        if(k < 0){
            return 0;
        }
        int start = 1;
        int end = 0;
        for (int length : L) {
            end = Math.max(end, length);
        }
        //表示切的的最大长度
        int mid;
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(cutWoodCount(L, mid) >= k){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(cutWoodCount(L, start) >= k){
            return start;
        }
        if (cutWoodCount(L, end) >= k) {
            return end;
        }
        return 0;
    }

    /**
     * 根据长度对木头进行分割，计算根数
     * @param woods
     * @param length
     * @return
     */
    private static int cutWoodCount(int[] woods, int length) {
        int sumCount = 0;
        for(int i = 0; i < woods.length; i++){
            sumCount += woods[i] / length;
        }
        return sumCount;
    }

    private static void testWoodCut() {
        int[] L1 = {232,124,456};
        int k = 7;
        System.out.println(woodCut(L1, k));
        int[] L2 = {1,2,3};
        System.out.println(woodCut(L2, k));
    }
}
