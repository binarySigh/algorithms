package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 uppe
 * //r）之内的 区间和的个数 。
 * //
 * // 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * //
 * //示例 1：
 * //
 * //输入：nums = [-2,5,-1], lower = -2, upper = 2
 * //输出：3
 * //解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0], lower = 0, upper = 0
 * //输出：1
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 105
 * // -231 <= nums[i] <= 231 - 1
 * // -105 <= lower <= upper <= 105
 * // 题目数据保证答案是一个 32 位 的整数
 * //
 * // Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
 * @author binarySigh
 * @date 2021/9/1 20:22
 */
public class LC0327_CountRangeSum {

    public static void main(String[] args) {
        // --> 3
        /*int[] nums1 = {-2, 5, -1};
        int lower1 = -2, upper1 = 2;*/

        // --> 1
        /*int[] nums1 = {0};
        int lower1 = 0, upper1 = 0;*/

        int[] nums1 = {1,-1,1,2,3,-2};
        int lower1 = -1, upper1 = 2;
        System.out.println(compare(nums1, lower1, upper1));
        System.out.println(countRangeSum(nums1, lower1, upper1));


        System.out.println("----------Begin-----------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 20) + 1;
            int[] nums = getNums(len);
            int lower = (int)(Math.random() * 500) - (int)(Math.random() * 500);
            int upper = 0;
            do{
                upper = (int)(Math.random() * 500) - (int)(Math.random() * 500);
            } while(upper < lower);
            int com = compare(nums, lower, upper);
            int ans = countRangeSum(nums, lower, upper);
            if(ans != com){
                System.out.println("------Oops-----");
                break;
            }
        }
        System.out.println("----------End-----------");
    }

    public static int countRangeSum(int[] nums, int lower, int upper){
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        //填充前缀和数组，同时捕捉长度为1和长度为0-i的达标子数组个数
        int ans = nums[0] >= lower && nums[0] <= upper ? 1 : 0;
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i] * 1L;
            ans += sum[i] >= lower && sum[i] <= upper ? 1 : 0;
        }
        int path = 1;
        int li = 0, lj = 0, ri = 0, rj = 0;
        while(path < sum.length){
            for(;li + path < sum.length;){
                lj = li + path - 1;
                ri = li + path;
                rj = li + path * 2 - 1 < sum.length ? (li + path * 2 - 1) : sum.length - 1;
                ans += mergeSort(sum, li, lj, ri, rj, lower, upper);
                li = rj + 1;
            }
            path <<= 1;
            li = 0;
        }
        return ans;
    }

    public static int mergeSort(long[] sum, int li, int lj, int ri, int rj, int lower, int upper){
        int ans = 0;
        int i = li, j = li;
        int r = ri;
        while(r <= rj){
            //上界
            while(j <= lj && sum[j] <= sum[r] - lower){
                j++;
            }
            //下界
            while(i <= lj && sum[i] < sum[r] - upper){
                i++;
            }
            ans += (j - i);
            r++;
        }
        //排序
        long[] tmp = new long[rj - li + 1];
        int idx = 0;
        i = li;
        r = ri;
        while(i <= lj && r <= rj){
            if(sum[i] <= sum[r]){
                tmp[idx++] = sum[i++];
            } else {
                tmp[idx++] = sum[r++];
            }
        }
        while(i <= lj){
            tmp[idx++] = sum[i++];
        }
        while(r <= rj){
            tmp[idx++] = sum[r++];
        }
        //排序结果拷贝回原数组
        idx = 0;
        while(li <= rj){
            sum[li++] = tmp[idx++];
        }
        return ans;
    }

    public static int compare(int[] nums, int lower, int upper){
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        int ans = nums[0] >= lower && nums[0] <= upper ? 1 : 0;
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i] * 1L;
            ans += sum[i] >= lower && sum[i] <= upper ? 1 : 0;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                long cur = sum[j] - sum[i];
                ans += cur >= lower && cur <= upper ? 1 : 0;
            }
        }
        return ans;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 500) - (int)(Math.random() * 500);
        }
        return nums;
    }

}
