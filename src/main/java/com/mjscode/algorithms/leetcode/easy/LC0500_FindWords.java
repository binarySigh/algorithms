package com.mjscode.algorithms.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * //
 * // 美式键盘 中：
 * //
 * // 第一行由字符 "qwertyuiop" 组成。
 * // 第二行由字符 "asdfghjkl" 组成。
 * // 第三行由字符 "zxcvbnm" 组成。
 * //
 * // 示例 1：
 * //
 * //输入：words = ["Hello","Alaska","Dad","Peace"]
 * //输出：["Alaska","Dad"]
 * //
 * // 示例 2：
 * //
 * //输入：words = ["omk"]
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * //输入：words = ["adsdf","sfd"]
 * //输出：["adsdf","sfd"]
 * //
 * // 提示：
 * //
 * // 1 <= words.length <= 20
 * // 1 <= words[i].length <= 100
 * // words[i] 由英文字母（小写和大写字母）组成
 * //
 * // Related Topics 数组 哈希表 字符串
 * @author binarySigh
 * @date 2021/10/31 17:38
 */
public class LC0500_FindWords {
    private static int[] map = new int[256];
    static {
        map['q'] = 1; map['a'] = 2; map['z'] = 3;
        map['w'] = 1; map['s'] = 2; map['x'] = 3;
        map['e'] = 1; map['d'] = 2; map['c'] = 3;
        map['r'] = 1; map['f'] = 2; map['v'] = 3;
        map['t'] = 1; map['g'] = 2; map['b'] = 3;
        map['y'] = 1; map['h'] = 2; map['n'] = 3;
        map['u'] = 1; map['j'] = 2; map['m'] = 3;
        map['i'] = 1; map['k'] = 2; map['Z'] = 3;
        map['o'] = 1; map['l'] = 2; map['X'] = 3;
        map['p'] = 1; map['A'] = 2; map['C'] = 3;
        map['Q'] = 1; map['S'] = 2; map['V'] = 3;
        map['W'] = 1; map['D'] = 2; map['B'] = 3;
        map['E'] = 1; map['F'] = 2; map['N'] = 3;
        map['R'] = 1; map['G'] = 2; map['M'] = 3;
        map['T'] = 1; map['H'] = 2;
        map['Y'] = 1; map['J'] = 2;
        map['U'] = 1; map['K'] = 2;
        map['I'] = 1; map['L'] = 2;
        map['O'] = 1;
        map['P'] = 1;
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.3 MB,击败了57.41% 的Java用户
     * @param words
     * @return
     */
    public static String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>(words.length);
        int i = 0;
        for(String s : words) {
            i = 1;
            for(; i < s.length(); i++) {
                if(map[s.charAt(i)] != map[s.charAt(i - 1)]) break;
            }
            if(i == s.length()) ans.add(s);
        }
        String[] ret = new String[ans.size()];
        for(int j = 0; j < ans.size(); j++) {
            ret[j] = ans.get(j);
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] words = {"Hello","Alaska","Dad","Peace"};
        String[] ans = findWords(words);
    }
}
