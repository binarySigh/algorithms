package com.mjscode.algorithms.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * //给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * //
 * // 返回所有可能的结果。答案可以按 任意顺序 返回。
 * //
 * // 示例 1：
 * //
 * //输入：s = "()())()"
 * //输出：["(())()","()()()"]
 * //
 * // 示例 2：
 * //
 * //输入：s = "(a)())()"
 * //输出：["(a())()","(a)()()"]
 * //
 * // 示例 3：
 * //
 * //输入：s = ")("
 * //输出：[""]
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 25
 * // s 由小写英文字母以及括号 '(' 和 ')' 组成
 * // s 中至多含 20 个括号
 * //
 * // Related Topics 广度优先搜索 字符串 回溯
 * @author binarySigh
 * @date 2021/10/27 19:45
 */
public class LC0301_RemoveInvalidParentheses {

    public static void main(String[] args) {
        // --> [()()(), (())()]
//  String s1 = "()())()";

        // --> [(a)()(), (a())()]
//  String s1 = "(a)())()";

        // --> []
        String s1 = ")(";

        List<String> ans = removeInvalidParentheses(s1);
        System.out.println(ans);
    }

    public static List<String> removeInvalidParentheses(String s) {
        HashSet<String> set = new HashSet<>();
        dfs(s, 0, set, -1);
        return new ArrayList<>(set);
    }

    public static int dfs(String s, int idx, HashSet<String> set, int maxLen) {
        if(s.length() < maxLen) return maxLen;
        if(idx == s.length()) {
            if(check(s) && !set.contains(s)) {
                if(s.length() > maxLen) {
                    maxLen = s.length();
                    set.clear();
                }
                set.add(s);
            }
            return maxLen;
        }
        // 本位置字符保留
        maxLen = Math.max(maxLen, dfs(s, idx + 1, set, maxLen));
        // 删除当前位置字符
        if(s.charAt(idx) == '(' || s.charAt(idx) == ')') {
            maxLen = Math.max(maxLen, dfs(s.substring(0, idx) + s.substring(idx + 1), idx, set, maxLen));
        }
        return maxLen;
    }

    public static boolean check(String s) {
        int p = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') p++;
            else if(s.charAt(i) == ')') p--;
            if(p < 0) return false;
        }
        return p == 0;
    }

}
