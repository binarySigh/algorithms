package com.mjscode.algorithms.leetcode.medium;

import java.util.PriorityQueue;

/**
 * //给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * //
 * // 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [10,2]
 * //输出："210"
 * //
 * // 示例 2：
 * //
 * //输入：nums = [3,30,34,5,9]
 * //输出："9534330"
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1]
 * //输出："1"
 * //
 * // 示例 4：
 * //
 * //输入：nums = [10]
 * //输出："10"
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 100
 * // 0 <= nums[i] <= 10⁹
 * //
 * // Related Topics 贪心 字符串 排序
 * @author binarySigh
 * @date 2021/10/27 19:44
 */
public class LC0179_LargestNumber {

    public static void main(String[] args) {
        // --> 9534330
        int[] nums = {3,30,34,5,9};

        // --> 43243432
//  int[] nums = {432,43243};

        System.out.println(LargestNumber(nums));


    }

    public static String LargestNumber(int[] nums) {
        PriorityQueue<String> q = new PriorityQueue<>((a, b) -> {
            int i = 0;
            String s1 = a + b, s2 = b + a;
            while(i < s1.length() && i < s2.length()) {
                if(s1.charAt(i) > s2.charAt(i)) return -1;
                else if(s1.charAt(i) < s2.charAt(i++)) return 1;
            }
            return 0;
        });
        for(int i : nums) {
            q.add(String.valueOf(i));
        }
        if("0".equals(q.peek())) return "0";
        StringBuilder ans = new StringBuilder();
        while(!q.isEmpty()) {
            ans.append(q.poll());
        }
        return ans.toString();
    }

}
