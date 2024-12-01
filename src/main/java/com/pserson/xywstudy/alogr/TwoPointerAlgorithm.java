package com.pserson.xywstudy.alogr;

import java.util.*;

/**
 * 双指针相关算法
 */
public class TwoPointerAlgorithm {
    public static void main(String[] args) {
        int[] numArr = {3,4,6,7};
        int target = 23;
        //计算哪两个数字和为23，并打印下标
        int[] result = twoSum1(numArr, target);
        System.out.println(Arrays.toString(result));
        result = twoSum2(numArr, target);
        System.out.println(Arrays.toString(result));
        result = twoSum3(numArr, target);
        System.out.println(Arrays.toString(result));

        System.out.println(calTriangleCount(numArr));
    }

    /**
     * 两数之和
     * @param array
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] array, int target){
        int[] result = new int[2];
        if(array == null || array.length == 0){
            return result;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if(array[i] + array[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 此方法有一定问题，部分题解不出来，因为每个元素没有都有被遍历过，所以不能用此方法，
     * 此处只作为一种双指针的应用
     * @param array
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] array, int target){
        int[] result = new int[2];
        if(array == null || array.length == 0){
            return result;
        }
        int start = 0;
        int end = array.length - 1;
        while(start < end){
            int tempSum = array[start] + array[end];
            if(tempSum < target){
                start ++;
            }else if (tempSum > target){
                end --;
            }else{
                result[0] = start;
                result[1] = end;
                break;
            }
        }
        return result;
    }

    public static int[] twoSum3(int[] array, int target){
        int[] result = new int[2];
        if(array == null || array.length == 0){
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if(map.containsKey(target - array[i])){
                result[0] = map.get(target - array[i]);
                result[1] = i;
                break;
            }else{
                map.put(array[i], i);
            }
        }
        return result;
    }

    /**
     * 3数之和 leetcode 15
     * @param nums
     * @return
     */
    public static List<List<Integer>> lt_15_threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return result;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < len - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                if(nums[i] + nums[left] + nums[right]  == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left ++;
                    right --;
                    while(left < right && nums[left] == nums[left - 1]){
                        left ++;
                    }
                    while(left < right && nums[right] == nums[right + 1]){
                        right --;
                    }
                }else if (nums[i] + nums[left] + nums[right]  > 0){
                    right --;
                }else {
                    left ++;
                }
            }
        }

        return result;
    }

    /**
     * 计算数组中的数可以组成多少个三角形
     * @param array
     * @return
     */
    public static int calTriangleCount(int[] array){
        if(array == null || array.length < 3){
            return 0;
        }
        int count = 0;
        Arrays.sort(array);
        for (int i = array.length - 1; i >= 2; i--) {
            int start = 0;
            int end = i -1;
            while(start < end){
                if(array[start] + array[end] > array[i]){
                    count += end - start;
                    end -- ;
                }else{
                    start ++;
                }
            }
        }

        return count;
    }

    /**
     * 接雨水 leetcode 42
     * @param height
     * @return
     */
    public static int lt_42_trap(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int sum = 0;
        int left = 0;
        int right = height.length - 1;
        int leftHeight = height[left];
        int rightHeight = height[right];
        while(left < right){
            if(leftHeight < rightHeight){
                if(leftHeight > height[left + 1]){
                    sum += leftHeight - height[left + 1];
                }else{
                    leftHeight = height[left + 1];
                }
                left ++;
            }else{
                if(rightHeight > height[right - 1]){
                    sum += rightHeight - height[right - 1];
                }else{
                    rightHeight =height[right - 1];
                }
                right --;
            }

        }
        return sum;
    }
}
