package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;
import sun.plugin2.util.SystemUtil;

import java.util.HashMap;

/**
 * //给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * //
 * // 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,0]
 * //输出：3
 * //
 * // 示例 2：
 * //
 * //输入：nums = [3,4,-1,1]
 * //输出：2
 * //
 * // 示例 3：
 * //
 * //输入：nums = [7,8,9,11,12]
 * //输出：1
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 300
 * // -231 <= nums[i] <= 231 - 1
 * //
 * // Related Topics 数组
 *
 * 难度：Hard
 * @author binarySigh
 */
public class LC0041_FirstMissingPositive {

    public static void main(String[] args){
        /*int[] arr = ArrayUtils.generateArray(20, 5, true);
        //int[] arr = {11,1,-3,-9,-12};
        ArrayUtils.showArray(arr);
        System.out.println(compare(arr));
        ArrayUtils.showArray(arr);*/

        System.out.println("---------测试开始---------");
        for(int i = 0; i < 50_0000; i++){
            int[] arr = ArrayUtils.generateArray(20, 50, true);
            int result = firstMissingPositive(arr);
            int compare = compare(arr);
            if(result != compare){
                System.out.println("------第[" + i + "]次结果出错-----");
                ArrayUtils.showArray(arr);
                System.out.println("期望结果：" + compare);
                System.out.println("测试结果：" + result);
                break;
            }
        }
        System.out.println("---------测试开始---------");
    }

    /**
     * 力扣官方提供的 时间复杂度 O(N),空间复杂度 O(1)的解法。
     * 思路就是在原数组上做一定的加工，使得原数组具有简单哈希表的效果
     * 思想：N个数中缺失的第一个正整数一定是 1-N 中的一个
     * @param arr
     * @return
     */
    public static int firstMissingPositive(int[] arr){
        //这里为了测试方便，所以拷贝原数组操作，防止影响输入数组。真正解题或提交时可以去掉
        int[] nums = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            nums[i] = arr[i];
        }
        //第一次遍历，将数组中所有 <= 0的数都变为 > N 的值
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0){
                nums[i] = nums.length + 1;
            }
        }
        //第二次遍历时做标记，如果|nums[i]| = j , 且j为一个有效值，那么就把nums[j] 标记为负数
        for(int i = 0; i < nums.length; i++){
            if(Math.abs(nums[i]) <= nums.length){
                //只有当j位置大于0时才标记，否则不标记。防止原数组中存在重复值导致重复标记而发生误标
                nums[Math.abs(nums[i]) - 1] *= nums[Math.abs(nums[i]) - 1] > 0 ? -1 : 1;
            }
        }
        //第三次遍历，找到第一个为正数的数，假设为i位置的数，那么第一个缺失的那个数就是 i + 1
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) return i + 1;
        }
        //全部遍历完了还没返回，说明缺失的第一个正数是N+1.即原数组里放的就是 1~N的正整数
        return nums.length + 1;
    }

    /**
     * 将输入数组排序后查找第一个丢失的正整数
     * 解答成功:
     * 		执行耗时:1 ms,击败了70.32% 的Java用户
     * 		内存消耗:36.2 MB,击败了55.64% 的Java用户
     * @param nums
     * @return
     */
    public static int compare(int[] nums){
        //过滤无效输入
        if(nums == null || nums.length == 0){
            return 1;
        }
        if(nums.length == 1){
            return nums[0] == 1 ? 2 : 1;
        }
        quickSort(nums, 0, nums.length - 1);
        int ret = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                if(nums[i] - ret > 1) {
                    return ret + 1;
                } else {
                    ret = nums[i];
                }
            }
        }
        return ret + 1;
    }

    /**
     * 随机快排
     * @param arr
     * @return
     */
    public static void quickSort(int[] arr, int l, int r){
        if(l == r){
           return;
        }
        //随机选数,交换
        int ran = (int)(Math.random() * (r - l)) + l;
        swap(arr, ran, r);
        int i = l - 1;
        int j = r;
        int index = l;
        while(index < j){
            if(arr[index] < arr[r]){
                swap(arr, index++, ++i);
            } else if(arr[index] > arr[r]){
                swap(arr, index, --j);
            } else {
                index++;
            }
        }
        swap(arr, j, r);
        if(l <= i) {
            quickSort(arr, l, i);
        }
        if(j + 1 <= r) {
            quickSort(arr, j + 1, r);
        }
    }

    /**
     * 交换两个位置上的元素
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static void swap(int[] arr, int i, int j){
        if(arr[i] == arr[j]){
            return;
        }
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[j] ^ arr[i];
    }
}
