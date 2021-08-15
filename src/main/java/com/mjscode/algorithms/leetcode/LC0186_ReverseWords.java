package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个字符串，逐个翻转字符串中的每个单词。
 * //
 * // 示例：
 * //
 * // 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * //输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * //
 * // 注意：
 * //
 * // 单词的定义是不包含空格的一系列字符
 * // 输入字符串中不会包含前置或尾随的空格
 * // 单词与单词之间永远是以单个空格隔开的
 * //
 * // 进阶：使用 O(1) 额外空间复杂度的原地解法。
 * // Related Topics 双指针 字符串
 * @author binarySigh
 * @date 2021/8/15 19:03
 */
public class LC0186_ReverseWords {
    public static void main(String[] args){
        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(s);
        ArrayUtils.showArray(s);
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:41.5 MB,击败了61.40% 的Java用户
     * @param s
     */
    public static void reverseWords(char[] s) {
        int n = s.length;
        swap(s, 0, n - 1);
        int i = 0, j = 0;
        while(i < n) {
            j = i + 1;
            while(j < n && s[j] != ' ') {
                j++;
            }
            swap(s, i, j - 1);
            i = j + 1;
        }
    }

    public static void swap(char[] s, int l, int r){
        char tmp = '0';
        while(l < r){
            if(s[l] != s[r]){
                tmp = s[l];
                s[l] = s[r];
                s[r] = tmp;
            }
            l++;
            r--;
        }
    }
}
