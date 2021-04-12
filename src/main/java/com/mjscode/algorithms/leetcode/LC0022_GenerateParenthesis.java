package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * //
 * // 示例 1：
 * //
 * //输入：n = 3
 * //输出：["((()))","(()())","(())()","()(())","()()()"]
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：["()"]
 * //
 * // 提示：
 * //
 * // 1 <= n <= 8
 * //
 * // Related Topics 字符串 回溯算法
 * @author binarySigh
 * @date 2021/4/10 9:32
 */
public class LC0022_GenerateParenthesis {
    public static void main(String[] args){
        int n = 4;
        List<String> list = generateParenthesis(n);
    }

    /**
     * 力扣官方思路解
     * 解答成功:
     * 		执行耗时:1 ms,击败了96.55% 的Java用户
     * 		内存消耗:38.7 MB,击败了33.54% 的Java用户
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(list, 0, 0, new StringBuilder(), n);
        return list;
    }

    public static void generate(List<String> res, int left, int right, StringBuilder sb, int limit){
        if(sb.length() == (limit << 1)){
            res.add(sb.toString());
            return;
        }
        if(left < limit){
            sb.append("(");
            generate(res, left + 1, right, sb, limit);
            sb.deleteCharAt(sb.length() - 1);
        }
        //保证右括号必须小于等于左括号数量
        if(right < left){
            sb.append(")");
            generate(res, left, right + 1, sb, limit);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
