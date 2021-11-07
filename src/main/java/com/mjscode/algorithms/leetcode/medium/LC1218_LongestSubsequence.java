package com.mjscode.algorithms.leetcode.medium;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * //给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于
 * //difference 。
 * //
 * // 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * //
 * // 示例 1：
 * //
 * //输入：arr = [1,2,3,4], difference = 1
 * //输出：4
 * //解释：最长的等差子序列是 [1,2,3,4]。
 * //
 * // 示例 2：
 * //
 * //输入：arr = [1,3,5,7], difference = 1
 * //输出：1
 * //解释：最长的等差子序列是任意单个元素。
 * //
 * // 示例 3：
 * //
 * //输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * //输出：4
 * //解释：最长的等差子序列是 [7,5,3,1]。
 * //
 * // 提示：
 * //
 * // 1 <= arr.length <= 10⁵
 * // -10⁴ <= arr[i], difference <= 10⁴
 * //
 * // Related Topics 数组 哈希表 动态规划
 * @author binarySigh
 * @date 2021/11/5 20:58
 */
public class LC1218_LongestSubsequence {
    public static void main(String[] args) {
//        int[] arr1 = {1,2,3,4};
//        int diff1 = 5;

        int[] arr1 = {-611,-280,-611,241,-74,-83,297,-459};
        int diff1 = 0;
        System.out.println(longestSubsequence(arr1, diff1));
        System.out.println(compare(arr1, diff1));

        System.out.println("-----------BEGIN---------");
        for (int i = 0; i < 10_0000; i++) {
            int len = (int)(Math.random() * 10) + 1;
            int[] arr = getArr(len);
            int diff = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
            int com = compare(arr, diff);
            int ans = longestSubsequence(arr, diff);
            if(ans != com) {
                System.out.println("----Oops-----");
                ArrayUtils.showArray(arr);
                System.out.println("diff : " + diff);
                break;
            }
        }
        System.out.println("-----------END---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:58 ms,击败了8.63% 的Java用户
     * 		内存消耗:52.8 MB,击败了70.56% 的Java用户
     * @param arr
     * @param difference
     * @return
     */
    public static int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr) {
            if(difference != 0) {
                map.put(i, Math.max(map.getOrDefault(i, 1), 1));
                if (map.containsKey(i - difference)) {
                    map.put(i, map.get(i - difference) + 1);
                }
            } else {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }
        return ans;
    }

    public static int compare(int[] arr, int difference) {
        int ans = 1;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] - arr[i] == difference) {
                    int pre = arr[j], curAns = 2;
                    for(int t = j + 1; t < arr.length; t++) {
                        if(arr[t] - pre == difference) {
                            pre = arr[t];
                            curAns++;
                        }
                    }
                    ans = Math.max(curAns, ans);
                }
            }
        }
        return ans;
    }

    public static int[] getArr(int len) {
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
        }
        return arr;
    }
}
