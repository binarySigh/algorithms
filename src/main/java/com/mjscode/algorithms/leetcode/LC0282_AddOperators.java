package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * //给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所
 * //有能够得到目标值的表达式。
 * //
 * // 示例 1:
 * //
 * //输入: num = "123", target = 6
 * //输出: ["1+2+3", "1*2*3"]
 * //
 * // 示例 2:
 * //
 * //输入: num = "232", target = 8
 * //输出: ["2*3+2", "2+3*2"]
 * //
 * // 示例 3:
 * //
 * //输入: num = "105", target = 5
 * //输出: ["1*0+5","10-5"]
 * //
 * // 示例 4:
 * //
 * //输入: num = "00", target = 0
 * //输出: ["0+0", "0-0", "0*0"]
 * //
 * // 示例 5:
 * //
 * //输入: num = "3456237490", target = 9191
 * //输出: []
 * //
 * // 提示：
 * //
 * // 1 <= num.length <= 10
 * // num 仅含数字
 * // -2³¹ <= target <= 2³¹ - 1
 * //
 * // Related Topics 数学 字符串 回溯
 * @author binarySigh
 * @date 2021/10/16 8:53
 */
public class LC0282_AddOperators {
    public static void main(String[] args) {
        // --> [1+2+3, 1*2*3]
//        String num1 = "123";
//        int target1 = 6;

        // -->> [2+3*2, 2*3+2]
//        String num1 = "232";
//        int target1 = 8;

        // --> [1*0+5, 10-5]
//        String num1 = "105";
//        int target1 = 5;

        // --> [0+0, 0-0, 0*0]
//        String num1 = "00";
//        int target1 = 0;

        // --> []
//        String num1 = "3456237490";
//        int target1 = 9191;

        // --> []
        String num1 = "2147483648";
        int target1 = -2147483648;

        List<String> ans = addOperators(num1, target1);
        System.out.println(ans);
        System.out.println(check(new StringBuilder(num1), target1));
    }

    /**
     * 解答成功:
     * 		执行耗时:443 ms,击败了5.54% 的Java用户
     * 		内存消耗:38.8 MB,击败了87.86% 的Java用户
     * @param num
     * @param target
     * @return
     */
    public static List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        process(num, 0, target, ans, sb, -1);
        return ans;
    }

    public static void process(String num, int idx, int target, List<String> ans, StringBuilder sb, int pre) {
        if(idx == num.length()) {
            if(check(sb, target)) {
                ans.add(sb.toString());
            }
            return;
        }
        // pre 参数主要是防止出现含前导0的无效数字
        if(pre == 0) {
            return;
        }
        sb.append(num.charAt(idx));
        int len = sb.length();
        if(idx < num.length() - 1) {
            sb.append('+');
            process(num, idx + 1, target, ans, sb, -1);
            sb.delete(len, sb.length());
            sb.append('-');
            process(num, idx + 1, target, ans, sb, -1);
            sb.delete(len, sb.length());
            sb.append('*');
            process(num, idx + 1, target, ans, sb, -1);
            sb.delete(len, sb.length());
        }
        process(num, idx + 1, target, ans, sb, (pre == -1 && num.charAt(idx) == '0') ? 0 : 1);
    }

    public static boolean check(StringBuilder sb, int target) {
        long res = 0;
        long pre = 1;
        long cur = 0;
        long last = 1;
        for(int i = 0; i < sb.length(); i++) {
            while(i < sb.length() && sb.charAt(i) >= '0' && sb.charAt(i) <= '9') {
                cur = cur * 10 + sb.charAt(i++) - '0';
            }
            if(i == sb.length() || sb.charAt(i) == '+' || sb.charAt(i) == '-') {
                res += pre * cur * last;
                pre = 1;
                cur = 0;
                last = i == sb.length() || sb.charAt(i) == '+' ? 1 : -1;
            } else {
                pre *= cur;
                cur = 0;
            }
        }
        return res == target;
    }
}
