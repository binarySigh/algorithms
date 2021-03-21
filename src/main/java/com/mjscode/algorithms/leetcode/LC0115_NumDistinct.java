package com.mjscode.algorithms.leetcode;

/**
 * //给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * //
 * // 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
 * //，而 "AEC" 不是）
 * //
 * // 题目数据保证答案符合 32 位带符号整数范围。
 * //
 * // 示例 1：
 * //
 * //输入：s = "rabbbit", t = "rabbit"
 * //输出：3
 * //解释：
 * //如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * //(上箭头符号 ^ 表示选取的字母)
 * //rabbbit
 * //^^^^ ^^
 * //rabbbit
 * //^^ ^^^^
 * //rabbbit
 * //^^^ ^^^
 * //
 * // 示例 2：
 * //
 * //输入：s = "babgbag", t = "bag"
 * //输出：5
 * //解释：
 * //如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * //(上箭头符号 ^ 表示选取的字母)
 * //babgbag
 * //^^ ^
 * //babgbag
 * //^^    ^
 * //babgbag
 * //^    ^^
 * //babgbag
 * //  ^  ^^
 * //babgbag
 * //    ^^^
 * //
 * // 提示：
 * //
 * // 0 <= s.length, t.length <= 1000
 * // s 和 t 由英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/3/17 23:17
 */
public class LC0115_NumDistinct {

    public static void main(String[] args){
        /*String s = "babgbag";
        String t = "bag";*/
        /*String s = "rabbbit";
        String t = "rabbit";*/
        String s = "moonsssterereraremoneststeeer";
        String t = "monsterq";
        System.out.println("动态规划答案：" + numDistinct(s, t));
        System.out.println("暴力递归答案：" + func(s, t));
    }

    public static int func(String s, String t){
        if(s.length() == 0 || t.length() == 0 || t.length() > s.length()){
            return t.length() == 0 ? 1 : 0;
        }
        return process(s, t, "", 0);
    }
    public static int process(String s, String t, String sub, int index){
        if(index == s.length()){
            return sub.equals(t) ? 1 : 0;
        }
        int count = 0;
        count += process(s, t, sub + s.charAt(index), index + 1);
        count += process(s, t, sub, index + 1);
        return count;
    }

    /**
     * 解答成功:
     * 		执行耗时:6 ms,击败了12.03% 的Java用户
     * 		内存消耗:36.8 MB,击败了55.15% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        if(s.length() == 0 || t.length() == 0 || t.length() > s.length()){
            return t.length() == 0 ? 1 : 0;
        }
        //dp[i][j] 表示s串前i位构成的所有子序列中，出现过t前j位子串的个数
        int[][] dp = new int[s.length()][t.length()];
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
        for(int i = 1; i < s.length(); i++){
            for(int j = 0; j < t.length(); j++){
                if(j > i){
                    break;
                } else if(j == i){
                    dp[i][j] = s.charAt(i) == t.charAt(j) ? dp[i - 1][j - 1] : 0;
                } else {
                    if(s.charAt(i) == t.charAt(j)){
                        dp[i][j] = j == 0 ? dp[i - 1][j] + 1 : dp[i - 1][j] + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length() - 1][t.length() - 1];
    }
}
