package com.pserson.xywstudy.alogr;

import java.util.Arrays;

/**
 * 排序的几种算法
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] numArr = new int[10];
        for(int i = 0; i < numArr.length; i++){
            numArr[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(numArr));
//        bubbleSort(numArr);
//        selectSort(numArr);
//        insertSort(numArr);
//        quickSortEx(numArr);
        mergeSort(numArr);
        System.out.println(Arrays.toString(numArr));

//        System.out.println(lt_215_findKthLargest(numArr, 2));
    }

    /**
     * 插入排序
     * @param numArr
     */
    public static void insertSort(int[] numArr){
        int insertNode;
        int j;
        for(int i = 1; i < numArr.length; i++){
            //第2个元素为第一个插入元素
            insertNode = numArr[i];
            j = i - 1;
            while(j >= 0 && insertNode < numArr[j]){
                //如果要插入的元素小于第j个元素，则将第j个元素后移一位
                numArr[j + 1] = numArr[j];
                j --;
            }
            //直到要插入的元素小于第j个元素，则将要插入的元素插入到第j个元素的后面
            numArr[j + 1] = insertNode;
        }
    }

    /**
     * 选择排序
     * @param numArr
     */
    public static void selectSort(int[] numArr){
        for(int i = 0; i < numArr.length - 1; i++){
            int pos = i;
            for(int j = i + 1; j < numArr.length; j++){
                if(numArr[pos] > numArr[j]){
                    pos = j;
                }
            }
            int temp = numArr[i];
            numArr[i] = numArr[pos];
            numArr[pos] = temp;
        }
    }

    /**
     * 冒泡排序
     * @param numArr
     */
    public static void bubbleSort(int[] numArr){
        for(int i = 0; i < numArr.length - 1; i++){
            for(int j = 1; j < numArr.length - i; j++){
                if(numArr[j - 1] > numArr[j]){
                    int temp = numArr[j -1];
                    numArr[j - 1] = numArr[j];
                    numArr[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * @param numArr
     */
    public static void quickSortEx(int[] numArr){
        quickSort(numArr, 0, numArr.length - 1);
    }

    private static void quickSort(int[] numArr, int start, int end) {
        if(start >= end){
            return;
        }
        int pivot = numArr[start];
        int left = start;
        int right = end;
        while(left <= right){
            while(left <= right && numArr[left] < pivot){
                left ++;
            }
            while(left <= right && numArr[right] > pivot){
                right --;
            }
            if(left <= right){
                int temp = numArr[left];
                numArr[left] = numArr[right];
                numArr[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(numArr, start, right);
        quickSort(numArr, left, end);
    }

    /**
     * 归并排序
     * @param nums
     */
    public static void mergeSort(int[] nums){
        int[] tempArr = new int[nums.length];
        mergeSortImpl(nums, 0, nums.length - 1, tempArr);
    }

    private static void mergeSortImpl(int[] nums, int start, int end, int[] tempArr) {
        if(start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSortImpl(nums, start, mid, tempArr);
        mergeSortImpl(nums, mid + 1, end, tempArr);
        mergeArr(nums, start, mid, end, tempArr);
    }

    private static void mergeArr(int[] nums, int start, int mid, int end, int[] tempArr) {
        int left = start;
        int right = mid + 1;
        int index = start;
        while(left <= mid && right <= end){
            if(nums[left] < nums[right]){
                tempArr[index++] = nums[left++];
            }else{
                tempArr[index++] = nums[right++];
            }
        }
        while(left <= mid){
            tempArr[index++] = nums[left++];
        }
        while(right <= end){
            tempArr[index++] = nums[right++];
        }
        for(index = start; index <= end; index++){
            nums[index] = tempArr[index];
        }
    }
    /**
     * 数组中的第K个最大元素 leetcode 215
     * @param nums
     * @param k
     * @return
     */
    public static int lt_215_findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int partition(int[] nums, int start, int end, int k) {
        if(start >= end){
            return nums[k];
        }
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while(left <= right){
            while(left <= right && nums[left] < pivot){
                left++;
            }
            while(left <= right && nums[right] > pivot){
                right--;
            }
            if(left <= right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left ++;
                right --;
            }
        }
        if(k <= right){
            partition(nums, start, right, k);
        }
        if(k >= left){
            partition(nums, left, end, k);
        }
        return nums[k];
    }


}
