package com.mjscode.algorithms.leetcode;

/**
 * //将非负整数 num 转换为其对应的英文表示。
 * //
 * // 示例 1：
 * //
 * //输入：num = 123
 * //输出："One Hundred Twenty Three"
 * //
 * // 示例 2：
 * //
 * //输入：num = 12345
 * //输出："Twelve Thousand Three Hundred Forty Five"
 * //
 * // 示例 3：
 * //
 * //输入：num = 1234567
 * //输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * //
 * // 示例 4：
 * //
 * //输入：num = 1234567891
 * //输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven
 * //Thousand Eight Hundred Ninety One"
 * //
 * // 提示：
 * //
 * // 0 <= num <= 2³¹ - 1
 * //
 * // Related Topics 递归 数学 字符串
 * @author binarySigh
 * @date 2021/10/11 20:22
 */
public class LC0273_NumberToWords {
    private static int BILLION = (int)Math.pow(10, 9);
    private static int MILLION = (int)Math.pow(10, 6);
    private static int THOUSAND = (int)Math.pow(10, 3);
    private static String[] singles = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"
            , "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen"
            , "Seventeen", "Eighteen", "Nineteen"};
    private static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            if(num >= BILLION) {
                sb.append(getStrInHundreds(num / BILLION)).append(" Billion");
                num %= BILLION;
            } else if(num >= MILLION) {
                sb.append(getStrInHundreds(num / MILLION)).append(" Million");
                num %= MILLION;
            } else if(num >= THOUSAND) {
                sb.append(getStrInHundreds(num / THOUSAND)).append(" Thousand");
                num %= THOUSAND;
            } else {
                sb.append(getStrInHundreds(num));
                num = 0;
            }
            if(num > 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private static String getStrInHundreds(int num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            if(num >= 100) {
                sb.append(singles[num / 100]).append(" Hundred");
                num %= 100;
            } else if(num >= 20) {
                sb.append(tens[num / 10]);
                num %= 10;
            } else {
                sb.append(singles[num]);
                num = 0;
            }
            if(num > 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1234567891);

        // One Hundred Twenty Three
//  int num = 123;

        // Twelve Thousand Three Hundred Forty Five
//  int num = 12345;

        //One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven
//  int num = 1234567;

        //One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One
        int num = 1234567891;
        System.out.println(numberToWords(num));

    }

}
