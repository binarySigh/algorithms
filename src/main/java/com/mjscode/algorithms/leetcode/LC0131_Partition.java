package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * //
 * // 回文串 是正着读和反着读都一样的字符串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aab"
 * //输出：[["a","a","b"],["aa","b"]]
 * //
 * // 示例 2：
 * //
 * //输入：s = "a"
 * //输出：[["a"]]
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 16
 * // s 仅由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划 回溯
 * @author binarySigh
 * @date 2021/8/19 18:47
 */
public class LC0131_Partition {
    public static void main(String[] args){
        String s = "aabaa";
        List<List<String>> ans = partition(s);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了72.70% 的Java用户
     * 		内存消耗:51.8 MB,击败了51.85% 的Java用户
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if(s.length() == 0){
            return ans;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int j = 0; j < s.length(); j++){
            for(int i = j; i >= 0; i--){
                if(i == j) {
                    dp[i][j] = true;
                } else if(i == j - 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
            }
        }
        process(s, dp, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void process(String s, boolean[][] dp, int idx, ArrayList<String> cur, List<List<String>> ans){
        if(idx == s.length()){
            List<String> list = new ArrayList<>(cur.size());
            for(String str : cur){
                list.add(str);
            }
            ans.add(list);
            return;
        }
        for(int i = idx; i < s.length(); i++) {
            if(dp[idx][i]){
                cur.add(s.substring(idx, i + 1));
                process(s, dp, i + 1, cur, ans);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
