package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * 二分查找--非递归写法
 */
public class LC0035_SearchInsert {

    public static void main(String[] args){
        /*int[] arr1 = {15,20,25,27,31};
        System.out.println(searchInsert(arr1, 17));*/
        for(int i = 0; i < 50_0000; i++) {
            int[] arr = getRandomArray();
            int target = (int)(Math.random() * 100);
            if(searchInsert(arr, target) != compare(arr, target)) {
                ArrayUtils.showArray(arr);
                System.out.println("target : " + target);
                System.out.println(searchInsert(arr, target));
                System.out.println(compare(arr, target));
                break;
            }
        }
    }

    /**
     * leetcode 提交已通过
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        //考虑数组最大值都小于目标值的情况
        if(nums[nums.length - 1] < target){
            return nums.length;
        }
        //考虑数组最小值小于目标值的情况
        if(nums[0] > target) {
            return 0;
        }
        int mid = ((nums.length - 1) >> 1);
        int lowerIndex = 0;
        int upperIndex = nums.length - 1;
        while(upperIndex > lowerIndex) {
            mid = lowerIndex + ((upperIndex - lowerIndex) >> 1);
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target){
                upperIndex = mid;
            } else if(nums[mid] < target) {
                lowerIndex = mid + 1;
            }
        }
        return upperIndex;
    }

    //对数器
    public static int compare(int[] nums, int target){
        //考虑数组最大值都小于目标值的情况
        if(nums[nums.length - 1] < target){
            return nums.length;
        }
        //考虑数组最小值小于目标值的情况
        if(nums[0] > target) {
            return 0;
        }

        int lowerIndex = 0;
        int upperIndex = nums.length - 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            } else if(nums[i] < target) {
                lowerIndex = i;
            } else if(nums[i] > target) {
                upperIndex = i;
            }
            if(upperIndex - lowerIndex == 1) {
                break;
            }
        }
        return upperIndex;
    }

    /**
     * 构建随机长度的有序数组
     * @return
     */
    public static int[] getRandomArray() {
        //数组长度[0,100]
        int len = (int)(Math.random() * 10 + 1);
        int[] arr= new int[len];
        //先构建arr[0],后续元素依次加一个随机正整数，保证有序
        arr[0] = (int)(Math.random() * 200);
        for(int i = 1; i < len; i++) {
            int random = arr[i - 1] + (int)(Math.random() * 5 + 1);
            arr[i] = random;
        }
        return arr;
    }
}
