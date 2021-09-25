package com.mjscode.algorithms.leetcode;

/**
 * //给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * //
 * // 示例：
 * //
 * // 输入: "sea", "eat"
 * //输出: 2
 * //解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * //
 * // 提示：
 * //
 * // 给定单词的长度不超过500。
 * // 给定单词中的字符只含有小写字母。
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/9/25 9:05
 */
public class LC0583_MinDistance {
    public static void main(String[] args) {
        String w1 = "sea";
        String w2 = "eat";
        System.out.println(minDistance(w1, w2));
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了62.07% 的Java用户
     * 		内存消耗:39 MB,击败了42.77% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        if(word1 == null || word1.length() == 0){
            return word2 == null ? 0 : word2.length();
        }
        if(word2 == null || word2.length() == 0){
            return word1 == null ? 0 : word1.length();
        }
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m][n];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 1 : 0;
        for(int i = 1; i < m; i++){
            dp[i][0] = Math.max(dp[i - 1][0], word1.charAt(i) == word2.charAt(0) ? 1 : 0);
        }
        for(int i = 1; i < n; i++){
            dp[0][i] = Math.max(dp[0][i - 1], word1.charAt(0) == word2.charAt(i) ? 1 : 0);
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (word1.charAt(i) == word2.charAt(j) ? 1 : 0);
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return m - dp[m - 1][n - 1] + n - dp[m - 1][n - 1];
    }
}
