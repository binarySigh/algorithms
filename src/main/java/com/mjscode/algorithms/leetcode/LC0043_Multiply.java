package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * //
 * // 示例 1:
 * //
 * // 输入: num1 = "2", num2 = "3"
 * //输出: "6"
 * //
 * // 示例 2:
 * //
 * // 输入: num1 = "123", num2 = "456"
 * //输出: "56088"
 * //
 * // 说明：
 * //
 * // num1 和 num2 的长度小于110。
 * // num1 和 num2 只包含数字 0-9。
 * // num1 和 num2 均不以零开头，除非是数字 0 本身。
 * // 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * //
 * // Related Topics 数学 字符串 模拟
 * @author binarySigh
 * @date 2021/8/15 14:20
 */
public class LC0043_Multiply {
    public static void main(String[] args){
        String num1 = "23456";
        String num2 = "1900";
        System.out.println(multiply(num1, num2));
        System.out.println(Long.parseLong(num1) * Long.parseLong(num2));
    }

    /**
     * 解答成功:
     * 		执行耗时:19 ms,击败了28.15% 的Java用户
     * 		内存消耗:39.1 MB,击败了8.55% 的Java用户
     * 优化方案：将 res集合替换成 ans + curNum两个字符串，每一步中间结果生成后立即计算入最终结果中。
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if(num1 == null || num1.length() == 0 ||
                num2 == null || num2.length() == 0 ||
                "0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        List<String> res = new ArrayList<>(num2.length());
        int tail = 0;
        for(int i = num2.length() - 1; i >= 0; i--){
            res.add(getSingleMultiply(num1, num2.charAt(i), tail++));
        }
        String ans = "";
        int path = 0;
        int digits = 0;
        int cur = 0;
        while(digits < res.get(num2.length() - 1).length()) {
            for (int i = 0; i < res.size(); i++) {
                String curNum = res.get(i);
                int curNumIdx = curNum.length() - 1;
                if(curNumIdx >= digits){
                    cur += curNum.charAt(curNumIdx - digits) - '0';
                }
            }
            cur += path;
            ans = (cur % 10) + ans;
            path = cur / 10;
            cur = 0;
            digits++;
        }
        return path == 0 ? ans : (path + ans);
    }

    public static String getSingleMultiply(String s, char c, int tail){
        if(c == '0'){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int path = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            int cur = (s.charAt(i) - '0') * (c - '0') + path;
            sb.insert(0, cur % 10);
            path = cur / 10;
        }
        if(path > 0) {
            sb.insert(0, path);
        }
        for(; tail > 0; tail--) {
            sb.append('0');
        }
        return sb.toString();
    }
}
