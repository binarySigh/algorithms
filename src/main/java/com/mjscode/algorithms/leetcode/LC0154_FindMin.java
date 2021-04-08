package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变
 * //化后可能得到：
 * //
 * // 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * // 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * //
 * // 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],
 * //..., a[n-2]] 。
 * //
 * // 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,3,5]
 * //输出：1
 * //
 * // 示例 2：
 * //
 * //输入：nums = [2,2,2,0,1]
 * //输出：0
 * //
 * // 提示：
 * //
 * // n == nums.length
 * // 1 <= n <= 5000
 * // -5000 <= nums[i] <= 5000
 * // nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * //
 * // 进阶：
 * //
 * // 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * // 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/4/9 0:03
 */
public class LC0154_FindMin {
    public static void main(String[] args){
        System.out.println("--------------测试开始-------------");
        for(int i = 0; i < 100_0000; i++){
            int[] arr = getRotateArray(1000);
            int compare = compare(arr);
            int result = findMin(arr);
            if(result != compare){
                System.out.println("------第[" + i + "]次测试结果出错！-------");
                ArrayUtils.showArray(arr);
                System.out.println("正确结果：" + compare);
                System.out.println("我的结果：" + result);
                break;
            }
        }
        System.out.println("--------------测试结束-------------");

        //int[] arr = {7,8,8,4,7,7};
        /*int[] arr = {9,13,13,15,20,-1,4,4,5};
        System.out.println(findMin(arr));*/
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.2 MB,击败了75.61% 的Java用户
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if(nums.length == 1 || nums[0] < nums[nums.length - 1]){
            return nums[0];
        }
        int min = Integer.MAX_VALUE;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            while(nums[l] == nums[r] && l < r){
                r--;
            }
            min = Math.min(min, nums[l]);
            min = Math.min(min, nums[r]);
            int mid = l + ((r - l) >> 1);
            min = Math.min(min, nums[mid]);
            if(nums[mid] >= nums[l]){
                l = mid + 1;
            } else if(nums[mid] <= nums[r]){
                r = mid - 1;
            }
        }
        return min;
    }

    //以下为对数器部分

    public static int compare(int[] nums) {
        if(nums.length == 1 || nums[0] < nums[nums.length - 1]){
            return nums[0];
        }
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            min = Math.min(min, num);
        }
        return min;
    }

    public static int[] getRotateArray(int maxLen){
        //随机长度：2-maxLen
        int len = (int)(Math.random() * (maxLen - 1)) + 2;
        int[] arr = new int[len];
        arr[0] = (int)(Math.random() * 6) - (int)(Math.random() * 6);
        for(int i = 1; i < len; i++){
            int path = 0;
            // 40%几率不递增
            if(Math.random() > 0.4){
                path = (int)(Math.random() * 6);
            }
            arr[i] = arr[i - 1] + path;
        }
        //旋转数组,旋转点下标：1~len-1
        int path = (int)(Math.random() * (len - 2)) + 1;
        int[] res = new int[len];
        int index = 0;
        int pre = path;
        for(; pre < len;){
            res[index++] = arr[pre++];
        }
        pre = 0;
        for(; pre < path;){
            res[index++] = arr[pre++];
        }
        return res;
    }
}
