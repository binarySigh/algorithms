package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * //
 * // 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * //
 * // 示例 1：
 * //
 * //输入：s = "bbbab"
 * //输出：4
 * //解释：一个可能的最长回文子序列为 "bbbb" 。
 * //
 * // 示例 2：
 * //
 * //输入：s = "cbbd"
 * //输出：2
 * //解释：一个可能的最长回文子序列为 "bb" 。
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 1000
 * // s 仅由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/8/12 10:39
 */
public class LC0516_LongestPalindromeSubseq {
    public static void main(String[] args){
        // --> 4
        //String s = "bbbab";

        // --> 2
        //String s = "cbbd";

        // --> 7
        //String s = "abcabcabcabc";

        // --> 6
        String s = "aabaaba";
        System.out.println(longestPalindromeSubseq(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:55 ms,击败了16.83% 的Java用户
     * 		内存消耗:48.5 MB,击败了31.82% 的Java用户
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        if(s.length() < 2){
            return s.length();
        }
        //dp[i][j] : i ~ j 范围内的子串中最长回文子序列是多长
        int[][] dp = new int[s.length()][s.length()];
        for(int j = 0; j < s.length(); j++){
            for(int i = j; i >= 0; i--){
                if(i == j) {
                    dp[i][j] = 1;
                } else if(i + 1 == j){
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 2 : 0));
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
