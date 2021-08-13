package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 *
 * //给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * //
 * // 如果小数部分为循环小数，则将循环的部分括在括号内。
 * //
 * // 如果存在多个答案，只需返回 任意一个 。
 * //
 * // 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * //
 * // 示例 1：
 * //
 * //输入：numerator = 1, denominator = 2
 * //输出："0.5"
 * //
 * // 示例 2：
 * //
 * //输入：numerator = 2, denominator = 1
 * //输出："2"
 * //
 * // 示例 3：
 * //
 * //输入：numerator = 2, denominator = 3
 * //输出："0.(6)"
 * //
 * // 示例 4：
 * //
 * //输入：numerator = 4, denominator = 333
 * //输出："0.(012)"
 * //
 * // 示例 5：
 * //
 * //输入：numerator = 1, denominator = 5
 * //输出："0.2"
 * //
 * // 提示：
 * //
 * // -231 <= numerator, denominator <= 231 - 1
 * // denominator != 0
 * //
 * // Related Topics 哈希表 数学 字符串
 * @author binarySigh
 * @date 2021/8/13 20:56
 */
public class LC0166_FractionToDecimal {

    public static void main(String[] args){
        // --> 0.0000000004656612873077392578125
        /*int n = -1;
        int d = -2147483648;*/

        int n = 1;
        int d = 666;
        System.out.println(fractionToDecimal(n, d));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.6 MB,击败了87.67% 的Java用户
     * @param numerator
     * @param denominator
     * @return
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if((numerator ^ denominator) < 0){
            sb.append("-");
        }
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        sb.append(num / den);
        if(num % den == 0){
            return sb.toString();
        }
        sb.append(".");
        num = num % den;
        HashMap<Long, Integer> map = new HashMap<>();
        while(num > 0){
            long cur = num * 10 / den;
            // 余数放入哈希表，余数相同才能表示进入循环节
            if(map.containsKey(num)){
                sb.insert((int)map.get(num), '(');
                sb.append(')');
                break;
            } else {
                map.put(num, sb.length());
                sb.append(cur);
                num = (num * 10 % den);
            }
        }
        return sb.toString();
    }
}
