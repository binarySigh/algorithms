package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * //
 * // 返回符合要求的 最少分割次数 。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aab"
 * //输出：1
 * //解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * //
 * // 示例 2：
 * //
 * //输入：s = "a"
 * //输出：0
 * //
 * // 示例 3：
 * //
 * //输入：s = "ab"
 * //输出：1
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 2000
 * // s 仅由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/8/19 19:54
 */
public class LC0132_MinCut {
    public static void main(String[] args){
        //String s = "aabaa";

        // --> 3
        String s1 = "cabababcbc";

        //String s1 = "rhhfffkwem";
        System.out.println(minCut(s1));
        System.out.println(minCut1(s1));


        System.out.println("-------------START-------------");
        for(int i = 0; i < 1_0000; i++){
            String s = getStr(20);
            int ans = minCut(s);
            int com = minCut1(s);
            if(ans != com){
                System.out.println("----------Oops-----------");
                System.out.println("s : " + s);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
                break;
            }
        }
        System.out.println("-------------END-------------");
    }

    /**
     *  1. 动态规划方式获取 f表，作为辅助结构表
     *  2. 根据 f表，将问题转化为背包问题
     *  复杂度：O(N^2)
     *
     *  解答成功:
     * 		执行耗时:25 ms,击败了61.27% 的Java用户
     * 		内存消耗:39.5 MB,击败了78.90% 的Java用户
     * @param s
     * @return
     */
    public static int minCut(String s) {
        if(s.length() < 2){
            return 0;
        }
        boolean[][] f = new boolean[s.length()][s.length()];
        for(int j = 0; j < s.length(); j++){
            for(int i = j; i >= 0; i--){
                if(i == j) {
                    f[i][j] = true;
                } else if(i == j - 1) {
                    f[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
                }
            }
        }
        //有了dp表之后，将问题转化为背包问题
        int[] dp = new int[s.length()];
        for(int i = 1; i < s.length(); i++){
            dp[i] = s.length();
        }
        dp[0] = 1;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(f[i][j]){
                    dp[j] = Math.min(dp[j], i == 0 ? 1 : (dp[i - 1] + 1));
                }
            }
        }
        return dp[s.length() - 1] - 1;
    }

    /**
     * 因为流程中涉及枚举过程，因此整体复杂度为 O(N^3)
     * pass : 32/33
     * Time Limit Exceeded
     * @param s
     * @return
     */
    public static int minCut1(String s) {
        if(s.length() < 2){
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for(int j = 0; j < s.length(); j++){
            for(int i = j; i >= 0; i--){
                if(i == j){
                    dp[i][j] = 1;
                } else if(i == j - 1){
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 2;
                } else {
                    dp[i][j] = dp[i + 1][j] + 1;
                    for(int t = j; t > i; t--){
                        if(s.charAt(i) == s.charAt(t) &&
                                (i == t - 1 || dp[i + 1][t - 1] == 1)){
                            dp[i][j] = Math.min(dp[i][j], (1 + (t == j ? 0 : dp[t + 1][j])));
                        }
                    }
                }
            }
        }
        return dp[0][s.length() - 1] - 1;
    }

    public static String getStr(int len){
        char[] str = new char[len];
        for(int i = 0; i < len; i++){
            str[i] = (char)((int)(Math.random() * 26) + 'a');
        }
        return String.valueOf(str);
    }

    public static int process(String s, boolean[][] f, int idx, int cnts){
        if(s.length() <= idx){
            return cnts;
        }
        int cur = s.length();
        for(int i = idx; i < s.length(); i++){
            if(f[idx][i]){
                cur = Math.min(cur, process(s, f, i + 1, cnts + 1));
            }
        }
        return cur;
    }

    public static int dp(String s, boolean[][] f){
        int[] dp = new int[s.length()];
        for(int i = 1; i < s.length(); i++){
            dp[i] = s.length();
        }
        dp[0] = 1;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(f[i][j]){
                    dp[j] = Math.min(dp[j], i == 0 ? 1 : (dp[i - 1] + 1));
                }
            }
        }
        return dp[s.length() - 1] - 1;
    }
}
