package com.mjscode.algorithms.leetcode;

/**
 * //给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
 * //
 * // 注意：
 * //
 * // 满足编辑距离等于 1 有三种可能的情形：
 * //
 * // 往 s 中插入一个字符得到 t
 * // 从 s 中删除一个字符得到 t
 * // 在 s 中替换一个字符得到 t
 * //
 * // 示例 1：
 * //
 * // 输入: s = "ab", t = "acb"
 * //输出: true
 * //解释: 可以将 'c' 插入字符串 s 来得到 t。
 * //
 * // 示例 2:
 * //
 * // 输入: s = "cab", t = "ad"
 * //输出: false
 * //解释: 无法通过 1 步操作使 s 变为 t。
 * //
 * // 示例 3:
 * //
 * // 输入: s = "1203", t = "1213"
 * //输出: true
 * //解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
 * // Related Topics 字符串
 * @author binarySigh
 * @date 2021/6/1 23:42
 */
public class LC0161_IsOneEditDistance {
    public static void main(String[] args){
        /*String s = "teacher";
        String t = "detacher";*/

        String s = "acb";
        String t = "acb";
        System.out.println(isOneEditDistance(s, t));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.1 MB,击败了39.28% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public static boolean isOneEditDistance(String s, String t) {
        char[] str1 = s.length() >= t.length() ? s.toCharArray() : t.toCharArray();
        char[] str2 = s.length() >= t.length() ? t.toCharArray() : s.toCharArray();
        if(str2.length == 0){
            return str1.length == 1;
        }
        if(str1.length - str2.length > 1){
            return false;
        }
        if(s.equals(t)){
            return false;
        }
        boolean changed = false;
        int i = 0, j = 0;
        while(i < str1.length && j < str2.length){
            if(str1[i] != str2[j]){
                if(changed){
                    return false;
                } else {
                    changed = true;
                    if(str1.length == str2.length){
                        j++;
                    }
                    i++;
                }
            } else {
                i++;
                j++;
            }
        }
        if(changed && i < str1.length){
            return false;
        }
        return true;
    }

    /**
     * 解答成功:
     * 		执行耗时:1073 ms,击败了12.01% 的Java用户
     * 		内存消耗:199.9 MB,击败了8.11% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public static boolean compare(String s, String t){
        int len1 = s.length();
        int len2 = t.length();
        if(len1 == 0){
            return len2 == 1;
        }
        if(len2 == 0){
            return len1 == 1;
        }
        if(Math.abs(len1 - len2) > 1){
            return false;
        }
        //dp[i][j] : word1 前i位子串变成word2 前j位子串，最少需要编辑多少步
        int[][] dp = new int[len1][len2];
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 0 : 1;
        //填充第一行
        for(int i = 1; i < len2; i++){
            dp[0][i] = Math.max(i, s.charAt(0) == t.charAt(i) ? dp[0][i - 1] : dp[0][i - 1] + 1);
        }
        //填充第一列
        for(int i = 1; i < len1; i++){
            dp[i][0] = Math.max(i, s.charAt(i) == t.charAt(0) ? dp[i - 1][0] : dp[i - 1][0] + 1);
        }
        //填充普遍位置
        for(int i = 1; i < len1; i++){
            for(int j = 1; j < len2; j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[len1 - 1][len2 - 1] == 1;
    }
}
