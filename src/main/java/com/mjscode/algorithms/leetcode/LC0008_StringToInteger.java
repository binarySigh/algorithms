package com.mjscode.algorithms.leetcode;

/**
 * //请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * //
 * // 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * //
 * // 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * // 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * // 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * //
 * // 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * //
 * // 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * //
 * // 提示：
 * //
 * // 本题中的空白字符只包括空格字符 ' ' 。
 * // 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231
 * // − 1) 或 INT_MIN (−231) 。
 * //
 * // 示例 1:
 * //
 * // 输入: "42"
 * //输出: 42
 * //
 * //
 * // 示例 2:
 * //
 * // 输入: "   -42"
 * //输出: -42
 * //解释: 第一个非空白字符为 '-', 它是一个负号。
 * //     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * //
 * // 示例 3:
 * //
 * // 输入: "4193 with words"
 * //输出: 4193
 * //解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * //
 * // 示例 4:
 * //
 * // 输入: "words and 987"
 * //输出: 0
 * //解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * //     因此无法执行有效的转换。
 * //
 * // 示例 5:
 * //
 * // 输入: "-91283472332"
 * //输出: -2147483648
 * //解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * //     因此返回 INT_MIN (−231) 。
 * //
 * // Related Topics 数学 字符串
 * @author binarySigh
 */
public class LC0008_StringToInteger {
    public static void main(String[] args){
        //String str = "-91283472332";
        String str = "2147483646";
        System.out.println(myAtoi(str));
        /*int i = 214748364;
        System.out.println(Integer.MAX_VALUE / 10.0D);
        System.out.println(i >= Integer.MAX_VALUE / 10.0D);
        System.out.println(i >= Integer.MAX_VALUE / 10);
        System.out.println(Integer.MIN_VALUE / 10.0D);
        System.out.println(Integer.MIN_VALUE / 10);
        System.out.println(Integer.MIN_VALUE);*/
    }

    /**
     *
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:2 ms,击败了99.68% 的Java用户
     * 		内存消耗:38.4 MB,击败了85.22% 的Java用户
     *
     * ASCII表：
     *  - ：45
     *  + ：43
     *  空格：32
     *  0~9：48~57
     * @param s
     * @return
     */
    public static int myAtoi(String s){
        if(s == null){
            return 0;
        }
        //flag记录正负数，默认正数
        boolean flag = true;
        int result = 0;
        //记录是否已经开始转换
        boolean begin = false;
        char[] str = s.toCharArray();
        for(int i = 0; i < str.length; i++){
            if(str[i] - 32 == 0 && !begin){
                //还没正式开始转换，且当前是空格，那么就直接continue
                continue;
            } else if(!begin && (str[i] - 45 == 0 || str[i] - 43 == 0)){
                //开始转换符号位，记录符号位
                flag = str[i] - 43 == 0 ? true : false;
                //这个时候已经开始转换了，所以begin状态要变更
                begin = true;
            } else if(str[i] - 48 >= 0 && str[i] - 57 <= 0){
                //这个时候已经开始转换了，所以begin状态要变更
                begin = true;
                //开始数字转换部分的处理
                if(flag){
                    //正数直接处理。加上当前数如果越界就直接返回最大值,同时要break；如果不越界就加上当前值,然后继续循环处理后续字符
                    // 这里判定一定要/10.0D，不能/10；防止因为精度问题，导致2147483640~2147483646之间的数字也误进分支
                    if((result >= Integer.MAX_VALUE / 10.0D) || ((result * 10 + (str[i] - 48)) < 0)) {
                        result = Integer.MAX_VALUE;
                        break;
                    } else {
                        result = result * 10 + (str[i] - 48);
                    }
                } else if(!flag){
                    //负数也做类似处理
                    if((result <= Integer.MIN_VALUE / 10.0D) || ((result * 10 - (str[i] - 48)) > 0)){
                        result = Integer.MIN_VALUE;
                        break;
                    } else {
                        result = result * 10 - (str[i] - 48);
                    }
                }
            } else {
                //碰到其他字符直接break
                break;
            }
        }
        return result;
    }
}
