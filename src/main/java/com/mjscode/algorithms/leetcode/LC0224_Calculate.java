package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * //
 * // 示例 1：
 * //
 * //输入：s = "1 + 1"
 * //输出：2
 * //
 * // 示例 2：
 * //
 * //输入：s = " 2-1 + 2 "
 * //输出：3
 * //
 * // 示例 3：
 * //
 * //输入：s = "(1+(4+5+2)-3)+(6+8)"
 * //输出：23
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 3 * 105
 * // s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * // s 表示一个有效的表达式
 * //
 * // Related Topics 栈 数学
 *
 * 题目难度：HARD
 * @author binarySigh
 * @date 2021/3/10 22:59
 */
public class LC0224_Calculate {

    public static void main(String[] args){
        //String s = "(1+(4+5+2)-3)+(6+8)";
        //String s = "(1+(4+5+2)-3)";
        //String s = " 2-1 + 2 ";
        String s = "(1+2) - (13 + (4 + 5 ) + (7 - 8))";
        //String s = "2147483647";
        System.out.println(calculate(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:19 ms,击败了33.37% 的Java用户
     * 		内存消耗:38.3 MB,击败了95.55% 的Java用户
     * @param s
     * @return
     */
    public static int calculate(String s) {
        //记录当前位置之前的符号
        boolean positive = true;
        int cur = 0;
        int curNum = 0;
        Stack<Prefix> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(new Prefix(cur, positive));
                //压栈这一步是将同优先级之前的计算结果压栈，所以压完之后将相关所有参数调整好
                positive = true;
                cur = 0;
                curNum = 0;
            } else if(s.charAt(i) == ')'){
                Prefix pre = stack.pop();
                cur = pre.flag ? pre.preSum + cur : pre.preSum - cur;
                curNum = 0;
            } else if(s.charAt(i) >= 48 && s.charAt(i) <= 57){
                curNum = curNum * 10 + s.charAt(i) - 48;
                if(i+1 >= s.length() || (s.charAt(i+1) < 48 || s.charAt(i+1) > 57)) {
                    cur = positive ? cur + curNum : cur - curNum;
                    curNum = 0;
                }
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+'){
                positive = s.charAt(i) == '-' ? false : true;
            }
        }
        return cur;
    }

    public static class Prefix{
        int preSum;
        boolean flag;
        Prefix(int sum, boolean fl){
            this.preSum = sum;
            this.flag = fl;
        }
    }
}
