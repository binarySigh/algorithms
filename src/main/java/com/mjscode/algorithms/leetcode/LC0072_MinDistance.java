package com.mjscode.algorithms.leetcode;

/**
 * //给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * //
 * // 你可以对一个单词进行如下三种操作：
 * //
 * // 插入一个字符
 * // 删除一个字符
 * // 替换一个字符
 * //
 * // 示例 1：
 * //
 * //输入：word1 = "horse", word2 = "ros"
 * //输出：3
 * //解释：
 * //horse -> rorse (将 'h' 替换为 'r')
 * //rorse -> rose (删除 'r')
 * //rose -> ros (删除 'e')
 * //
 * // 示例 2：
 * //
 * //输入：word1 = "intention", word2 = "execution"
 * //输出：5
 * //解释：
 * //intention -> inention (删除 't')
 * //inention -> enention (将 'i' 替换为 'e')
 * //enention -> exention (将 'n' 替换为 'x')
 * //exention -> exection (将 'n' 替换为 'c')
 * //exection -> execution (插入 'u')
 * //
 * // 提示：
 * //
 * // 0 <= word1.length, word2.length <= 500
 * // word1 和 word2 由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/5/31 22:59
 */
public class LC0072_MinDistance {
    public static void main(String[] args){
        // --> 3
        /*String word1 = "horse";
        String word2 = "ros";*/

        // --> 5
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了11.50% 的Java用户
     * 		内存消耗:38.4 MB,击败了69.02% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if(len1 == 0 || len2 == 0){
            return len2 == 0 ? len1 : len2;
        }
        //dp[i][j] : word1 前i位子串变成word2 前j位子串，最少需要编辑多少步
        int[][] dp = new int[len1][len2];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        //填充第一行
        for(int i = 1; i < len2; i++){
            dp[0][i] = Math.max(i, word1.charAt(0) == word2.charAt(i) ? dp[0][i - 1] : dp[0][i - 1] + 1);
        }
        //填充第一列
        for(int i = 1; i < len1; i++){
            dp[i][0] = Math.max(i, word1.charAt(i) == word2.charAt(0) ? dp[i - 1][0] : dp[i - 1][0] + 1);
        }
        //填充普遍位置
        for(int i = 1; i < len1; i++){
            for(int j = 1; j < len2; j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
