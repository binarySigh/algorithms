package com.mjscode.algorithms.leetcode.medium;

/**
 * //给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为
 * //每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * //
 * // 示例 1:
 * //
 * //输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * //输出: 16
 * //解释: 这两个单词为 "abcw", "xtfn"。
 * //
 * // 示例 2:
 * //
 * //输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * //输出: 4
 * //解释: 这两个单词为 "ab", "cd"。
 * //
 * // 示例 3:
 * //
 * //输入: ["a","aa","aaa","aaaa"]
 * //输出: 0
 * //解释: 不存在这样的两个单词。
 * //
 * // 提示：
 * //
 * // 2 <= words.length <= 1000
 * // 1 <= words[i].length <= 1000
 * // words[i] 仅包含小写字母
 * //
 * // Related Topics 位运算 数组 字符串
 * @author binarySigh
 * @date 2021/11/17 20:14
 */
public class LC0318_MaxProduct {

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了69.67% 的Java用户
     * 		内存消耗:38.6 MB,击败了34.25% 的Java用户
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] wd = new int[words.length];
        for(int i = 0; i < words.length; i++) {
            String s = words[i];
            for(int j = 0; j < s.length(); j++) {
                wd[i] |= 1 << (s.charAt(j) - 'a');
            }
        }
        int ans = 0;
        for(int i = 0; i < wd.length; i++) {
            for(int j = i + 1; j < wd.length; j++) {
                if((wd[i] & wd[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
