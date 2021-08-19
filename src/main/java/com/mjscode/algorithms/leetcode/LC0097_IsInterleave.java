package com.mjscode.algorithms.leetcode;

/**
 * //给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * //
 * // 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * //
 * // s = s1 + s2 + ... + sn
 * // t = t1 + t2 + ... + tm
 * // |n - m| <= 1
 * // 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * //
 * // 提示：a + b 意味着字符串 a 和 b 连接。
 * //
 * // 示例 1：
 * //
 * //输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * //输出：false
 * //
 * // 示例 3：
 * //
 * //输入：s1 = "", s2 = "", s3 = ""
 * //输出：true
 * //
 * // 提示：
 * //
 * // 0 <= s1.length, s2.length <= 100
 * // 0 <= s3.length <= 200
 * // s1、s2、和 s3 都由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/8/19 9:07
 */
public class LC0097_IsInterleave {
    public static void main(String[] args){
        // --> true
        /*String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";*/

        // --> false
        /*String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";*/

        String s1 = "a";
        String s2 = "da";
        String s3 = "aad";
        System.out.println(isInterleave(s1, s2, s3));

    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了22.16% 的Java用户
     * 		内存消耗:36.7 MB,击败了50.63% 的Java用户
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(!check(s1, s2, s3)){
            return false;
        }
        int n = s1.length(), m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        //第一列
        for(int i = 1; i <= s1.length(); i++){
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        //第一行
        for(int i = 1; i <= s2.length(); i++){
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        //后续
        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                if(s1.charAt(i - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
                if(s2.charAt(j - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 解答成功:
     * 		执行耗时:73 ms,击败了6.04% 的Java用户
     * 		内存消耗:41.5 MB,击败了5.02% 的Java用户
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave1(String s1, String s2, String s3) {
        if(!check(s1, s2, s3)){
            return false;
        }
        int n = s1.length(), m = s2.length();
        boolean[][][] dp = new boolean[n + 1][m + 1][s3.length() + 1];
        dp[0][0][0] = true;
        for(int t = 1; t <= s3.length(); t++){
            for(int i = 0; i <= s1.length(); i++){
                for(int j = 0; j <= s2.length(); j++){
                    if((i == 0 && j == 0) || i + j > t){
                        continue;
                    } else if(i == 0) {
                        dp[i][j][t] = dp[i][j - 1][t - 1] && s2.charAt(j - 1) == s3.charAt(t - 1);
                    } else if(j == 0){
                        dp[i][j][t] = dp[i - 1][j][t - 1] && s1.charAt(i - 1) == s3.charAt(t - 1);
                    } else {
                        if(s1.charAt(i - 1) == s3.charAt(t - 1)){
                            dp[i][j][t] = dp[i][j][t] || dp[i - 1][j][t - 1];
                        }
                        if(s2.charAt(j - 1) == s3.charAt(t - 1)){
                            dp[i][j][t] = dp[i][j][t] || dp[i][j - 1][t - 1];
                        }
                    }
                }
            }
        }
        return dp[n][m][s3.length()];
    }

    public static boolean check(String s1, String s2, String s3){
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        int[] cnt = new int[26];
        for(int i = 0; i < s3.length(); i++){
            cnt[s3.charAt(i) - 'a']++;
        }
        for(int i = 0; i < s1.length(); i++){
            if(--cnt[s1.charAt(i) - 'a'] < 0){
                return false;
            }
        }
        for(int i = 0; i < s2.length(); i++){
            if(--cnt[s2.charAt(i) - 'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
