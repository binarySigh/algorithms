package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * //
 * // 示例 1：
 * //
 * //输入：nums1 = [1,3], nums2 = [2]
 * //输出：2.00000
 * //解释：合并数组 = [1,2,3] ，中位数 2
 * //
 * // 示例 2：
 * //
 * //输入：nums1 = [1,2], nums2 = [3,4]
 * //输出：2.50000
 * //解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * //
 * // 示例 3：
 * //
 * //输入：nums1 = [0,0], nums2 = [0,0]
 * //输出：0.00000
 * //
 * // 示例 4：
 * //
 * //输入：nums1 = [], nums2 = [1]
 * //输出：1.00000
 * //
 * // 示例 5：
 * //
 * //输入：nums1 = [2], nums2 = []
 * //输出：2.00000
 * //
 * // 提示：
 * //
 * // nums1.length == m
 * // nums2.length == n
 * // 0 <= m <= 1000
 * // 0 <= n <= 1000
 * // 1 <= m + n <= 2000
 * // -106 <= nums1[i], nums2[i] <= 106
 * //
 * // 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * // Related Topics 数组 二分查找 分治算法
 * @author binarySigh
 * @date 2021/3/22 20:56
 */
public class LC0004_FindMedianSortedArrays {

    public static void main(String[] args){
        System.out.println("----------测试开始------------");
        for(int i = 0; i < 20_0000; i++){
            int len1 = (int)(Math.random() * 10);
            int len2 = (int)(Math.random() * 10);
            //sep处理，防止两个数组长度同时为0
            double sep = Math.random();
            if(sep <= 0.5){
                len1 += 1;
            } else {
                len2 += 1;
            }
            int[] nums1 = generateArray(len1);
            int[] nums2 = generateArray(len2);
            double res = findMedianSortedArrays(nums1, nums2);
            double mid = compare(nums1, nums2);
            if(res != mid){
                System.out.println("第[" + i + "]次实验出错！");
                System.out.println("两个升序数组分别是：");
                ArrayUtils.showArray(nums1);
                ArrayUtils.showArray(nums2);
                System.out.println("正确结果：" + mid);
                System.out.println("我的结果：" + res);
                break;
            }
        }
        System.out.println("----------测试结束------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了82.37% 的Java用户
     * 		内存消耗:39.8 MB,击败了30.28% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0){
            int len = nums2.length;
            return len % 2 == 1 ? nums2[len/2] : (nums2[len/2] + nums2[len/2 - 1])/2.0D;
        }
        if(nums2.length == 0){
            int len = nums1.length;
            return len % 2 == 1 ? nums1[len/2] : (nums1[len/2] + nums1[len/2 - 1])/2.0D;
        }
        int len = nums1.length + nums2.length;
        if(len % 2 == 1){
            return getKthNumber(nums1, nums2, len / 2 + 1);
        } else {
            int upperMid = getKthNumber(nums1, nums2, len / 2);
            int lowerMid = getKthNumber(nums1, nums2, len / 2 + 1);
            return (upperMid + lowerMid) / 2.0D;
        }
    }

    /**
     * 在两个正序数组中寻找整体第 K小的数
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static int getKthNumber(int[] nums1, int[] nums2, int k){
        int len1 = nums1.length, len2 = nums2.length;
        int offset1 = 0, offset2 = 0;
        //int kthElement = 0;
        while(true){
            if(offset1 == len1){
                return nums2[offset2 + k - 1];
            }
            if(offset2 == len2){
                return nums1[offset1 + k - 1];
            }
            if(k == 1){
                return Math.min(nums1[offset1], nums2[offset2]);
            }
            int path = k / 2;
            int index1 = Math.min(offset1 + path, len1) - 1;
            int index2 = Math.min(offset2 + path, len2) - 1;
            if(nums1[index1] <= nums2[index2]){
                k -= (index1 - offset1 + 1);
                offset1 = index1 + 1;
            } else {
                k -= (index2 - offset2 + 1);
                offset2 = index2 + 1;
            }
        }
    }

    //以下为对数器部分
    /**
     * 解答成功:
     * 		执行耗时:4 ms,击败了23.33% 的Java用户
     * 		内存消耗:39.4 MB,击败了96.73% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public static double compare(int[] nums1, int[] nums2){
        if(nums1.length == 0){
            int len = nums2.length;
            return len % 2 == 1 ? nums2[len/2] : (nums2[len/2] + nums2[len/2 - 1])/2.0D;
        } else if(nums2.length == 0){
            int len = nums1.length;
            return len % 2 == 1 ? nums1[len/2] : (nums1[len/2] + nums1[len/2 - 1])/2.0D;
        }
        int[] arr = new int[nums1.length + nums2.length];
        int i = 0, j = 0, a = 0;
        while(i < nums1.length && j < nums2.length){
            arr[a++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        while(i < nums1.length){
            arr[a++] = nums1[i++];
        }
        while(j < nums2.length){
            arr[a++] = nums2[j++];
        }
        int len = arr.length;
        if(len % 2 == 0){
            return (arr[len / 2] + arr[len/2 - 1]) / 2.0D;
        } else {
            return arr[len / 2];
        }
    }

    /**
     * 生成最大长度为 maxLen的随即长度增序数组
     * @param length 随机数组长度
     * @return
     */
    public static int[] generateArray(int length){
        if(length == 0){
            return new int[]{};
        }
        int[] arr = new int[length];
        arr[0] = (int)(Math.random() * 6);
        for(int i = 1; i < length; i++){
            int path = 0;
            do{
                path = (int)(Math.random() * 10);
            } while(path == 0);
            arr[i] = arr[i - 1] + path;
        }
        return arr;
    }
}
