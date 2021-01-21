package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * //
 * // 有效字符串需满足：
 * //
 * // 左括号必须用相同类型的右括号闭合。
 * // 左括号必须以正确的顺序闭合。
 * //
 * // 注意空字符串可被认为是有效字符串。
 * //
 * // 示例 1:
 * //
 * // 输入: "()"
 * //输出: true
 * //
 * // 示例 2:
 * //
 * // 输入: "()[]{}"
 * //输出: true
 * //
 * // 示例 3:
 * //
 * // 输入: "(]"
 * //输出: false
 * //
 * // 示例 4:
 * //
 * // 输入: "([)]"
 * //输出: false
 * //
 * // 示例 5:
 * //
 * // 输入: "{[]}"
 * //输出: true
 * // Related Topics 栈 字符串
 * @author binarySigh
 */
public class LC0020_IsValid {

    public static void main(String[] args){
        String s = "({{{{}}}))";
        System.out.println(isValid(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了73.91% 的Java用户
     * 		内存消耗:36.6 MB,击败了58.13% 的Java用户
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if(s == null || s.length() < 2 || s.length() % 2 == 1){
            return s != null && s.length() == 0 ? true : false;
        }
        // ( - 40 ; ) - 41
        // [ - 91 ; ] - 93
        // { - 123; } - 125
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 41 || s.charAt(i) == 93 || s.charAt(i) == 125){
                if(stack.isEmpty()){
                    return false;
                } else {
                    if((int)s.charAt(i) - stack.peek() <= 2 && (int)s.charAt(i) - stack.peek() > 0){
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            } else {
                stack.push((int)s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
