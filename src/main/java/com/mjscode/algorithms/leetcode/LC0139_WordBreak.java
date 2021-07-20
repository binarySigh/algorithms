package com.mjscode.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * //给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * //
 * // 说明：
 * //
 * // 拆分时可以重复使用字典中的单词。
 * // 你可以假设字典中没有重复的单词。
 * //
 * // 示例 1：
 * //
 * // 输入: s = "leetcode", wordDict = ["leet", "code"]
 * //输出: true
 * //解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * //
 * // 示例 2：
 * //
 * // 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * //输出: true
 * //解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * //     注意你可以重复使用字典中的单词。
 * //
 * // 示例 3：
 * //
 * // 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * //输出: false
 * //
 * // Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划
 * @author binarySigh
 * @date 2021/7/20 23:11
 */
public class LC0139_WordBreak {
    public static void main(String[] args){
        // --> true
        /*String s = "leetcode";
        String[] words = {"leet", "code"};*/

        // --> true
        /*String s = "applepenapple";
        String[] words = {"apple", "pen"};*/

        // --> false
        String s = "catsandog";
        String[] words = {"cats", "dog", "sand", "and", "cat"};
        System.out.println(wordBreak(s, Arrays.asList(words)));
    }

    /**
     * 记忆化
     * 解答成功:
     * 		执行耗时:5 ms,击败了81.27% 的Java用户
     * 		内存消耗:38.5 MB,击败了66.38% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict.size());
        int[] canDo = new int[s.length()];
        for(String word : wordDict){
            set.add(word);
        }
        return canBreak(s, 0, set, canDo);
    }

    public static boolean canBreak(String s, int index, HashSet<String> set, int[] canDo){
        if(index >= s.length()){
            return true;
        }
        for(int i = index + 1; i <= s.length(); i++){
            if(set.contains(s.substring(index, i))){
                if(i >= s.length() || canDo[i] == 1){
                    return true;
                } else if(canDo[i] == -1){
                    continue;
                } else {
                    if(canBreak(s, i, set, canDo)) return true;
                }
            }
        }
        canDo[index] = -1;
        return false;
    }
}
