package com.mjscode.algorithms.leetcode;

/**
 * //给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * //
 * // 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * //
 * // 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * //
 * // 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * //
 * // 示例 1：
 * //
 * //输入：text1 = "abcde", text2 = "ace"
 * //输出：3
 * //解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * //
 * // 示例 2：
 * //
 * //输入：text1 = "abc", text2 = "abc"
 * //输出：3
 * //解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * //
 * // 示例 3：
 * //
 * //输入：text1 = "abc", text2 = "def"
 * //输出：0
 * //解释：两个字符串没有公共子序列，返回 0 。
 * //
 * // 提示：
 * //
 * // 1 <= text1.length, text2.length <= 1000
 * // text1 和 text2 仅由小写英文字符组成。
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/4/3 23:04
 */
public class LC1143_LongestCommonSubsequence {

    public static void main(String[] args){
        /*String text1 = "abcde";
        String text2 = "ace";*/
        String text1 = "adbcetttttf";
        String text2 = "edf";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    /**
     * 执行结果： 通过
     * 执行用时： 13 ms , 在所有 Java 提交中击败了 47.67% 的用户
     * 内存消耗： 42.7 MB , 在所有 Java 提交中击败了 5.04% 的用户
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        //dp[i][j]含义：text1前i个字符组成的子串 和 text2前j个字符组成的子串 的最长公共子序列是多长
        int[][] dp = new int[len1][len2];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for(int j = 1; j < len2; j++){
            dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : dp[0][j - 1];
        }
        for(int i = 1; i < len1; i++){
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        }
        for(int j = 1; j < len2; j++){
            for(int i = 1; i < len1; i++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
