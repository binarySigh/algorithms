package com.mjscode.algorithms.leetcode;

/**
 * //有效数字（按顺序）可以分成以下几个部分：
 * //
 * // 一个 小数 或者 整数
 * // （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * //
 * // 小数（按顺序）可以分成以下几个部分：
 * //
 * // （可选）一个符号字符（'+' 或 '-'）
 * // 下述格式之一：
 * //
 * // 至少一位数字，后面跟着一个点 '.'
 * // 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * // 一个点 '.' ，后面跟着至少一位数字
 * //
 * // 整数（按顺序）可以分成以下几个部分：
 * //
 * // （可选）一个符号字符（'+' 或 '-'）
 * // 至少一位数字
 * //
 * // 部分有效数字列举如下：
 * //
 * // ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
 * // "53.5e93", "-123.456e789"]
 * //
 * // 部分无效数字列举如下：
 * //
 * // ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * //
 * // 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * //
 * // 示例 1：
 * //
 * //输入：s = "0"
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：s = "e"
 * //输出：false
 * //
 * // 示例 3：
 * //
 * //输入：s = "."
 * //输出：false
 * //
 * // 示例 4：
 * //
 * //输入：s = ".1"
 * //输出：true
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 20
 * // s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 * //
 * // Related Topics 数学 字符串
 * @author binarySigh
 * @date 2021/5/18 19:56
 */
public class LC0065_IsNumber {

    public static void main(String[] args){
        // -> false
        //String s = "4e+";
        // -> false
        String s = "+E3";
        System.out.println(isNumber(s));
    }

    /**
     * 思路：第一次遍历，找出e,'.'位置，并推出e前后两段的符号位；
     *      第二次遍历时跳过符号位和'.','e'位置，外置位中只要出现非数字字符，即非有效数字
     *  注意处理好边界和特殊情况即可。比如：'.'前后不能是 '.','e'和符号，必须含有数字等
     *  解答成功:
     * 		执行耗时:2 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.7 MB,击败了41.32% 的Java用户
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        if(s.length() == 1){
            return s.charAt(0) >= '0' && s.charAt(0) <= '9';
        }
        if(s.endsWith("+") || s.endsWith("-")){
            return false;
        }
        //如果字符串中没有出现 E，则E位置在s.length的无效位置，即认为e存在，但不存在后面的整数部分
        int ePos = s.length();
        int dotPos = -1;
        for(int i = 0; i < s.length(); i++){
            //找到第一个e，记下位置并退出本次循环
            if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                ePos = i;
                break;
            }
            //同时找到有效'.'的位置。有效指的是必须出现在E之前，且只能出现一次
            if(s.charAt(i) == '.'){
                if(dotPos == -1){
                    dotPos = i;
                } else {
                    return false;
                }
            }
        }
        // e 出现在开头结尾都认为是无效数字
        if(ePos == 0 || ePos == s.length() - 1){
            return false;
        }
        //确认 e 前后两段是否有符号位
        boolean firstFlag = s.charAt(0) == '-' || s.charAt(0) == '+' ? true : false;
        boolean secondFlag = ePos + 1 < s.length() && (s.charAt(ePos + 1) == '-' || s.charAt(ePos + 1) == '+')
                                    ? true : false;
        //检查'.'两边是否都为空。即排除 '.' 两边同时为 空/符号位/e 的情况
        //这里只检查'.'前后是否均为空字符位即可，不检查字符是否有效
        // 例： +des.e,在这里是暂算有效数字的，des这个非法部分会在下面的循环里被正确结算
        if(ePos - dotPos == 1 && dotPos - (firstFlag ? 0 : -1) == 1){
            return false;
        }
        //e前面不能直接是符号位
        if(ePos - (firstFlag ? 0 : -1) == 1){
            return false;
        }
        //检查数字部分
        for(int i = firstFlag ? 1 : 0; i < s.length(); i++){
            //'.'位跳过
            if(i == dotPos){
                continue;
            }
            //e位要稍做处理，确保跳过后面的符号位，直接跳到有效数字位去判定
            if(i == ePos){
                i += secondFlag ? 1 : 0;
                continue;
            }
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
