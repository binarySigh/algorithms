package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 *
 * // 。
 * //
 * // 示例 1:
 * //
 * // 输入: "2-1-1"
 * //输出: [0, 2]
 * //解释:
 * //((2-1)-1) = 0
 * //(2-(1-1)) = 2
 * //
 * // 示例 2:
 * //
 * // 输入: "2*3-4*5"
 * //输出: [-34, -14, -10, -10, 10]
 * //解释:
 * //(2*(3-(4*5))) = -34
 * //((2*3)-(4*5)) = -14
 * //((2*(3-4))*5) = -10
 * //(2*((3-4)*5)) = -10
 * //(((2*3)-4)*5) = 10
 * // Related Topics 递归 记忆化搜索 数学 字符串 动态规划
 * @author binarySigh
 * @date 2021/9/10 20:22
 */
public class LC0241_DiffWaysToCompute {

    public static void main(String[] args) {
        // --> [2, 0]
        /*String exp = "2-1-1";*/

        // --> [-34, -10, -14, -10, 10]
        /*String exp = "2*3-4*5";*/

        String exp = "";
        List<Integer> ans = diffWaysToCompute(exp);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了99.89% 的Java用户
     * 		内存消耗:36.9 MB,击败了83.61% 的Java用户
     * @param expression
     * @return
     */
    public static List<Integer> diffWaysToCompute(String expression){
        if(expression == null || expression.length() == 0){
            return new ArrayList<>();
        }
        int n = 1;
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+' ||
                    expression.charAt(i) == '*'){
                n++;
            }
        }
        //第一行为数字，第二行为操作符
        int[][] exp = new int[n][2];
        int idx = 0;
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '-'){
                exp[idx++][1] = 1;
            } else if(expression.charAt(i) == '+' ){
                exp[idx++][1] = 2;
            } else if(expression.charAt(i) == '*'){
                exp[idx++][1] = 3;
            } else {
                exp[idx][0] = exp[idx][0] * 10 + expression.charAt(i) - '0';
            }
        }
        List<Integer>[][] dp = new ArrayList[n][n];
        for(int j = 0; j < n; j++){
            dp[j][j] = new ArrayList<>();
            dp[j][j].add(exp[j][0]);
            for(int i = j - 1; i >= 0; i--){
                dp[i][j] = new ArrayList<>();
                for(int t = i; t < j; t++){
                    dp[i][j].addAll(getResult(exp[t][1], dp[i][t], dp[t + 1][j]));
                }
            }
        }
        return dp[0][n - 1];
    }

    public static List<Integer> getResult(int oper, List<Integer> l1, List<Integer> l2){
        List<Integer> ret = new ArrayList<>();
        if(oper == 1) {
            for (Integer i : l1) {
                for (Integer j : l2) {
                    ret.add(i - j);
                }
            }
        } else if(oper == 2){
            for (Integer i : l1) {
                for (Integer j : l2) {
                    ret.add(i + j);
                }
            }
        } else if(oper == 3){
            for (Integer i : l1) {
                for (Integer j : l2) {
                    ret.add(i * j);
                }
            }
        }
        return ret;
    }

}