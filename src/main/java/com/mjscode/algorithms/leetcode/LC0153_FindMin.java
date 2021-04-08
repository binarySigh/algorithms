package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
 * //化后可能得到：
 * //
 * // 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * // 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * //
 * // 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],
 * //..., a[n-2]] 。
 * //
 * // 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [3,4,5,1,2]
 * //输出：1
 * //解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [4,5,6,7,0,1,2]
 * //输出：0
 * //解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * //
 * // 示例 3：
 * //
 * //输入：nums = [11,13,15,17]
 * //输出：11
 * //解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 * //
 * // 提示：
 * //
 * // n == nums.length
 * // 1 <= n <= 5000
 * // -5000 <= nums[i] <= 5000
 * // nums 中的所有整数 互不相同
 * // nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/4/8 19:59
 */
public class LC0153_FindMin {

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

        /*int[] arr = {15,19,23,25,1,2,7,10};
        System.out.println(findMin(arr));*/
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:37.9 MB,击败了50.66% 的Java用户
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if(nums.length == 1 || nums[0] < nums[nums.length - 1]){
            return nums[0];
        }
        int l = 0, r = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while(l <= r){
            int mid = l + ((r - l) >> 1);
            min = Math.min(min, nums[mid]);
            if(nums[mid] < nums[r]){
                r = mid - 1;
            } else if(nums[mid] >= nums[l]) {
                l = mid + 1;
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
            arr[i] = arr[i - 1] + (int)(Math.random() * 6) + 1;
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
