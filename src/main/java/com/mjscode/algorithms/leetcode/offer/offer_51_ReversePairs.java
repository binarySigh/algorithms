package com.mjscode.algorithms.leetcode.offer;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * //
 * // 示例 1:
 * //
 * // 输入: [7,5,6,4]
 * //输出: 5
 * //
 * // 限制：
 * //
 * // 0 <= 数组长度 <= 50000
 * // Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
 * @author binarySigh
 * @date 2021/9/4 10:34
 */
public class offer_51_ReversePairs {
    public static void main(String[] args){
        int[] nums1 = {7,5,6,4};
        System.out.println(compare(nums1));
        System.out.println(reversePairs(nums1));

        System.out.println("-------------Begin---------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 20) + 1;
            int[] nums = getNums(len);
            int com = compare(nums);
            int ans = reversePairs(nums);
            if(com != ans){
                System.out.println("-----------Oops------------");
                ArrayUtils.showArray(nums);
                break;
            }
        }
        System.out.println("-------------End---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:33 ms,击败了68.18% 的Java用户
     * 		内存消耗:45.9 MB,击败了99.45% 的Java用户
     * @param nums
     * @return
     */
    public static int reversePairs(int[] nums) {
        int ans = 0;
        int path = 1;
        int cur = 0;
        while(path < nums.length){
            cur = 0;
            while(cur + path < nums.length){
                ans += mergeSort(nums, cur, path);
                cur = cur + path * 2 < nums.length ? (cur + path * 2) : nums.length;
            }
            path <<= 1;
        }
        return ans;
    }

    public static int mergeSort(int[] nums, int li, int path){
        int len = li + path * 2 >= nums.length ? (nums.length - li) : (path * 2);
        int[] tmp = new int[len];
        int idx = 0;
        int lj = li + path - 1;
        int ri = li + path;
        int rj = li + path * 2 - 1 < nums.length ? (li + path * 2 - 1) : (nums.length - 1);
        int i = li, j = ri;
        int ans = 0;
        while(i <= lj && j <= rj){
            if(nums[i] < nums[j]){
                tmp[idx++] = nums[i++];
            } else if(nums[i] == nums[j]){
                //等于的时候一定要先拷贝左边的
                tmp[idx++] = nums[i++];
            } else {
                ans += (lj - i + 1);
                tmp[idx++] = nums[j++];
            }
        }
        while(i <= lj){
            tmp[idx++] = nums[i++];
        }
        while(j <= rj){
            tmp[idx++] = nums[j++];
        }
        //排序结果拷贝回原数组
        idx = 0;
        while(li <= rj){
            nums[li++] = tmp[idx++];
        }
        return ans;
    }

    //对数器部分
    public static int compare(int[] nums){
        int ans = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] > nums[i]){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 20) - (int)(Math.random() * 20);
        }
        return nums;
    }
}
