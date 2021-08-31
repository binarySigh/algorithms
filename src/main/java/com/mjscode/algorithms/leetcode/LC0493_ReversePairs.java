package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * //
 * // 你需要返回给定数组中的重要翻转对的数量。
 * //
 * // 示例 1:
 * //
 * //输入: [1,3,2,3,1]
 * //输出: 2
 * //
 * // 示例 2:
 * //
 * //输入: [2,4,3,5,1]
 * //输出: 3
 * //
 * // 注意:
 * //
 * // 给定数组的长度不会超过50000。
 * // 输入数组中的所有数字都在32位整数的表示范围内。
 * //
 * // Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
 * @author binarySigh
 * @date 2021/8/31 22:42
 */
public class LC0493_ReversePairs {

    public static void main(String[] args) {
        // --> 2
        /*int[] nums1 = {1,3,2,3,1};*/

        // --> 3
        int[] nums1 = {2,4,3,5,1};
        System.out.println(reversePairs(nums1));

        System.out.println("----------Begin-----------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 100) + 1;
            int[] nums = getArr(len);
            int com = compare(nums);
            int ans = reversePairs(nums);
            if(ans != com){
                System.out.println("------Oops-----");
                ArrayUtils.showArray(nums);
                break;
            }
        }
        System.out.println("----------End-----------");
    }

    public static int reversePairs(int[] nums){
        if(nums.length < 2){
            return 0;
        }
        int path = 1;
        int ans = 0;
        while(path < nums.length){
            for(int i = 0; i < nums.length;){
                if(i + path >= nums.length){
                    break;
                } else {
                    int leftBegin = i, leftEnd = i + path - 1;
                    int rightBegin = i + path;
                    int rightEnd = i + path * 2 >= nums.length ? (nums.length - 1) : (i + path * 2 - 1);
                    ans += mergeSort(nums, leftBegin, leftEnd, rightBegin, rightEnd);
                    i = rightEnd + 1;
                }
            }
            path <<= 1;
        }
        return ans;
    }

    public static int mergeSort(int[] nums, int li, int lj, int ri, int rj){
        int ans = 0;
        int l = li, r = ri;
        //先获取数对
        while(l <= lj && r <= rj){
            double a = nums[l] / 2.0D;
            if(a > nums[r]){
                ans += (lj - l + 1);
                r++;
            } else {
                l++;
            }
        }
        //两段排序
        int[] tmp = new int[rj - li + 1];
        l = li;
        r = ri;
        int idx = 0;
        while(l <= lj && r <= rj){
            if(nums[l] <= nums[r]){
                tmp[idx++] = nums[l++];
            } else {
                tmp[idx++] = nums[r++];
            }
        }
        while(l <= lj){
            tmp[idx++] = nums[l++];
        }
        while(r <= rj){
            tmp[idx++] = nums[r++];
        }
        //将排序结果拷贝到原数组中
        idx = 0;
        while(idx < tmp.length){
            nums[li++] = tmp[idx++];
        }
        return ans;
    }

    public static int compare(int[] nums){
        if(nums.length < 2){
            return 0;
        }
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                double a = nums[i] / 2.0D;
                if(a > nums[j]){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int[] getArr(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 500) - (int)(Math.random() * 500);
        }
        return nums;
    }

}
