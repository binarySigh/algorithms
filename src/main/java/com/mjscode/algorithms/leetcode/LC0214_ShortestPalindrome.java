package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aacecaaa"
 * //输出："aaacecaaa"
 * //
 * // 示例 2：
 * //
 * //输入：s = "abcd"
 * //输出："dcbabcd"
 * //
 * // 提示：
 * //
 * // 0 <= s.length <= 5 * 10⁴
 * // s 仅由小写英文字母组成
 * //
 * // Related Topics 字符串 字符串匹配 哈希函数 滚动哈希
 * @author binarySigh
 * @date 2021/9/22 21:18
 */
public class LC0214_ShortestPalindrome {

    public static void main(String[] args) {
        // --> aaacecaaa
//  String s1 = "aacecaaa";

        // --> dcbabcd
        String s1 = "abcd";
        System.out.println("com : " + compare(s1));

        //性能测试
        System.out.println("------性能测试-------");
//        StringBuilder sb1 = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//        for(int i = 0; i < 20000; i++){
//            sb1.append('a');
//            sb2.append('a');
//        }
//        sb1.append("cd");
//        String str = sb1.toString() + sb2.toString();
//        long comStart = System.nanoTime();
////  String c = compare(str);
//        String c = getString(50000);
//        long comEnd = System.nanoTime();
//        long ansStart = System.nanoTime();
//        String a = shortestPalindrome(str);
//        long ansEnd = System.nanoTime();
//        System.out.println("结果是否正确： " + c.equals(a));
//        System.out.println("对照组结果： " + c);
//        System.out.println("对照组耗时：" + (comEnd - comStart));
//        System.out.println("实验组结果： " + a);
//        System.out.println("实验组耗时：" + (ansEnd - ansStart));
    }

    public static String shortestPalindrome(String s) {
        HashMap<Integer, String> set = new HashMap<>();

        return null;
    }

    /**
     * 执行耗时：584ms, 击败用户 5.1%
     * 内存消耗：38.4MB, 击败用户 80.8%
     */
    public static String compare(String s) {
        int i = s.length() - 1;
        // 找到以0位置开始的最长回文子串的终止位置i
        for(; i >= 0; i--) {
            int l = 0, r = i;
            while(l <= r) {
                if(s.charAt(l) != s.charAt(r)) {
                    break;
                }
                l++; r--;
            }
            if(l > r) {
                break;
            }
        }
        // 将 i位置之后的子串逆序拼接到原字符串前面即可
        return buildPalindrome(s, i + 1);
    }

    public static String buildPalindrome(String s, int i) {
        return new StringBuilder(s.substring(i)).reverse().toString() + s;
    }

    public static String getString(int len) {
        StringBuilder sb = new StringBuilder();
        while(len > 0) {
            sb.append((char)((Math.random() * 26) + 'a'));
            len--;
        }
        return sb.toString();
    }

}
