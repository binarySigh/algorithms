package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * //
 * //
 * // 任何左括号 ( 必须有相应的右括号 )。
 * // 任何右括号 ) 必须有相应的左括号 ( 。
 * // 左括号 ( 必须在对应的右括号之前 )。
 * // * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * // 一个空字符串也被视为有效字符串。
 * //
 * // 示例 1:
 * //
 * //输入: "()"
 * //输出: True
 * //
 * // 示例 2:
 * //
 * //输入: "(*)"
 * //输出: True
 * //
 * // 示例 3:
 * //
 * //输入: "(*))"
 * //输出: True
 * //
 * // 注意:
 * //
 * // 字符串大小将在 [1，100] 范围内。
 * //
 * // Related Topics 栈 贪心 字符串 动态规划
 * @author binarySigh
 * @date 2021/9/12 9:26
 */
public class LC0678_CheckValidString {
    public static void main(String[] args){
        // --> true
        /*String s = "()";*/

        // --> true
        /*String s = "(*)";*/

        // --> true
        //String s = "(*))";

        // --> ((((((((((((
        // --> stars    01       2
        // --> left     5  76   10
        String s = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";

        // --> true
        //String s = "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
        System.out.println(checkValidString(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.3 MB,击败了59.89% 的Java用户
     * @param s
     * @return
     */
    public static boolean checkValidString(String s) {
        int[] left = new int[100];
        int[] star = new int[100];
        int lp = -1;
        int sp = -1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                left[++lp] = i;
            } else if(s.charAt(i) == '*'){
                star[++sp] = i;
            } else {
                //当前为右括号，只要左括号不为空就优先弹出左括号与之匹配
                if(lp == -1 && sp == -1){
                    return false;
                } else if(lp > -1){
                    lp--;
                } else if(sp > -1){
                    sp--;
                }
            }
        }
        // 对多余的每个左括号，找到一个位置在它之后的*与之匹配
        while(lp >= 0 && sp >= 0 && left[lp] < star[sp]){
            lp--;
            sp--;
        }
        return lp == -1;
    }
}
