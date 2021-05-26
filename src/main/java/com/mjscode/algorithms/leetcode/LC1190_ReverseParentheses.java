package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //给出一个字符串 s（仅含有小写英文字母和括号）。
 * //
 * // 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * //
 * // 注意，您的结果中 不应 包含任何括号。
 * //
 * // 示例 1：
 * //
 * // 输入：s = "(abcd)"
 * //输出："dcba"
 * //
 * // 示例 2：
 * //
 * // 输入：s = "(u(love)i)"
 * //输出："iloveu"
 * //
 * // 示例 3：
 * //
 * // 输入：s = "(ed(et(oc))el)"
 * //输出："leetcode"
 * //
 * // 示例 4：
 * //
 * // 输入：s = "a(bcdefghijkl(mno)p)q"
 * //输出："apmnolkjihgfedcbq"
 * //
 * // 提示：
 * //
 * // 0 <= s.length <= 2000
 * // s 中只有小写英文字母和括号
 * // 我们确保所有括号都是成对出现的
 * //
 * // Related Topics 栈
 * @author binarySigh
 * @date 2021/5/26 20:34
 */
public class LC1190_ReverseParentheses {
    public static void main(String[] args){
        String s = "abs()d(fg)";
        System.out.println(reverseParentheses(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了98.96% 的Java用户
     * 		内存消耗:36.7 MB,击败了75.55% 的Java用户
     * @param s
     * @return
     */
    public static String reverseParentheses(String s) {
        if(s.length() <= 2){
            return s.startsWith("(") ? "" : s;
        }
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(sb);
                sb = new StringBuilder();
            } else if(s.charAt(i) == ')'){
                sb.reverse();
                if(!stack.isEmpty()){
                    sb = stack.pop().append(sb);
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
