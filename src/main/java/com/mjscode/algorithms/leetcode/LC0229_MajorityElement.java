package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * //
 * // 示例 1：
 * //
 * //输入：[3,2,3]
 * //输出：[3]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1]
 * //输出：[1]
 * //
 * // 示例 3：
 * //
 * //输入：[1,1,1,3,3,2,2,2]
 * //输出：[1,2]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 5 * 10⁴
 * // -10⁹ <= nums[i] <= 10⁹
 * //
 * // 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * // Related Topics 数组 哈希表 计数 排序
 * @author binarySigh
 * @date 2021/10/22 20:31
 */
public class LC0229_MajorityElement {

    public static void main(String[] args) {
        // --> [3]
//  int[] nums1 = {3,2,3};

        // --> [1]
//  int[] nums1 = {1};

        // --> [2, 1]
//  int[] nums1 = {1,1,1,3,3,2,2,2};

        int[] nums1 = {0,3,4,0};

//  int[] nums1 = {-1136,-1136,2193,-4136};

        List<Integer> ans1 = majorityElement(nums1);
        List<Integer> com1 = compare(nums1);
        showArr(nums1);
        System.out.println(check(ans1, com1));
        System.out.println("Answer  : " + ans1);
        System.out.println("Compare : " + com1);

        System.out.println();
        System.out.println("------------Begin-----------");
        for(int i = 0; i < 10_0000; i++) {
            int len = (int)(Math.random() * 20) + 4;
            int[] nums = getArr(len);
            List<Integer> ans = majorityElement(nums);
            List<Integer> com = compare(nums);
            if(!check(ans, com)) {
                System.out.println("------Oops------");
                showArr(nums);
                System.out.println("Answer  : " + ans);
                System.out.println("Compare : " + com);
                break;
            }
        }
        System.out.println("------------End-----------");
    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>(2);
        int cand1 = 0, cand2 = 0;
        int cnt1 = 0, cnt2 = 0;
        for(int i : nums) {
            if(cnt1 > 0 && cand1 == i) {
                cnt1++;
            } else if(cnt2 > 0 && cand2 == i) {
                cnt2++;
            } else if(cnt1 == 0) {
                cand1 = i;
                cnt1++;
            } else if(cnt2 == 0) {
                cand2 = i;
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        for(int i : nums) {
            if(i == cand1) cnt1++;
            else if(i == cand2) cnt2++;
        }
        if(cnt1 > nums.length / 3) ans.add(cand1);
        if(cnt2 > nums.length / 3) ans.add(cand2);
        return ans;
    }

    public static List<Integer> compare(int[] nums) {
        List<Integer> ans = new ArrayList<>(2);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > nums.length / 3) ans.add(entry.getKey());
        }
        return ans;
    }

    public static int[] getArr(int len) {
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = (int)(Math.random() * 5000) - (int)(Math.random() * 5000);
        }
        return nums;
    }

    public static boolean check(List<Integer> l1, List<Integer> l2) {
        if(l1.size() != l2.size()) {
            return false;
        }
        if(l1.size() == 0) {
            return true;
        }
        if(l1.size() == 1) {
            return (int)l1.get(0) == (int)l2.get(0);
        }
        return ((int)l1.get(0) == (int)l2.get(0) && (int)l1.get(1) == (int)l2.get(1)) ||
                ((int)l1.get(0) == (int)l2.get(1) && (int)l1.get(1) == (int)l2.get(0));
    }

    public static void showArr(int[] nums) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < nums.length - 1; i++) {
            sb.append(nums[i]).append(',');
        }
        sb.append(nums[nums.length - 1]).append(']');
        System.out.println(sb.toString());
    }

}
