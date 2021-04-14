package com.mjscode.algorithms.leetcode;

/**
 * //给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * //
 * // '?' 可以匹配任何单个字符。
 * //'*' 可以匹配任意字符串（包括空字符串）。
 * //
 * // 两个字符串完全匹配才算匹配成功。
 * //
 * // 说明:
 * //
 * // s 可能为空，且只包含从 a-z 的小写字母。
 * // p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * //
 * // 示例 1:
 * //
 * // 输入:
 * //s = "aa"
 * //p = "a"
 * //输出: false
 * //解释: "a" 无法匹配 "aa" 整个字符串。
 * //
 * // 示例 2:
 * //
 * // 输入:
 * //s = "aa"
 * //p = "*"
 * //输出: true
 * //解释: '*' 可以匹配任意字符串。
 * //
 * // 示例 3:
 * //
 * // 输入:
 * //s = "cb"
 * //p = "?a"
 * //输出: false
 * //解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * //
 * // 示例 4:
 * //
 * // 输入:
 * //s = "adceb"
 * //p = "*a*b"
 * //输出: true
 * //解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * //
 * // 示例 5:
 * //
 * // 输入:
 * //s = "acdcb"
 * //p = "a*c?b"
 * //输出: false
 * // Related Topics 贪心算法 字符串 动态规划 回溯算法
 * @author binarySigh
 * @date 2021/4/14 22:35
 */
public class LC0044_IsMatch {
    public static void main(String[] args){
        //结果--true
        /*String s = "adceb";
        String p = "*a*b";*/

        //结果--false
        /*String s = "acdcb";
        String p = "a*c?b";*/

        //结果--false
        /*String s = "cb";
        String p = "?a";*/

        //结果--false
        /*String s = "aa";
        String p = "a";*/

        //结果--true
        /*String s = "adced";
        String p = "*a*";*/

        //LC测试案例 结果--false
        /*String s = "aab";
        String p = "c*a*b";*/

        //LC测试案例 结果--true
        String s = "abcabczzzde";
        String p = "*abc???de*";
        System.out.println(isMatch(s, p));
    }

    /**
     * 解答成功:
     * 		执行耗时:39 ms,击败了23.34% 的Java用户
     * 		内存消耗:39.6 MB,击败了5.89% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        //过滤输入
        if(s == null || s.length() == 0){
            if(p == null || p.length() == 0){
                return true;
            } else {
                for(int j = 0; j < p.length(); j++){
                    if(p.charAt(j) != '*'){
                        return false;
                    }
                }
                return true;
            }
        }
        if(p == null || p.length() == 0){
            return false;
        }
        //主流程
        //res[j][i] : p的前j位子串是否能按规则匹配上s的前i位子串
        boolean[][] res = new boolean[p.length()][s.length()];
        //填充第一行
        if(p.charAt(0) == '*'){
            for(int i = 0; i < s.length(); i++){
                res[0][i] = true;
            }
        } else {
            res[0][0] = p.charAt(0) == '?' || p.charAt(0) == s.charAt(0);
        }
        //填充第一列
        int pos = p.charAt(0) != '*' ? 0 : -1;
        for(int j = 1; j < p.length(); j++){
            if(p.charAt(j) != '*'){
                if(pos < 0){
                    pos = j;
                    res[j][0] = p.charAt(j) == '?' || p.charAt(j) == s.charAt(0);
                } else {
                    break;
                }
            } else {
                res[j][0] = res[j - 1][0];
            }
        }
        //填充剩余的普遍位置
        for(int j = 1; j < p.length(); j++){
            for(int i = 1; i < s.length(); i++){
                if(p.charAt(j) == '*'){
                    res[j][i] = res[j - 1][i - 1] || res[j][i - 1] || res[j - 1][i];
                } else if(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)){
                    res[j][i] = res[j - 1][i - 1];
                } else {
                    res[j][i] = false;
                }
            }
        }
        return res[p.length() - 1][s.length() - 1];
    }
}
