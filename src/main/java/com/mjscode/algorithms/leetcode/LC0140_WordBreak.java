package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * //给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
 * //句子。
 * //
 * // 说明：
 * //
 * // 分隔时可以重复使用字典中的单词。
 * // 你可以假设字典中没有重复的单词。
 * //
 * // 示例 1：
 * //
 * // 输入:
 * //s = "catsanddog"
 * //wordDict = ["cat", "cats", "and", "sand", "dog"]
 * //输出:
 * //[
 * //  "cats and dog",
 * //  "cat sand dog"
 * //]
 * // 示例 2：
 * //
 * // 输入:
 * //s = "pineapplepenapple"
 * //wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * //输出:
 * //[
 * //  "pine apple pen apple",
 * //  "pineapple pen apple",
 * //  "pine applepen apple"
 * //]
 * //解释: 注意你可以重复使用字典中的单词。
 * //
 * // 示例 3：
 * //
 * // 输入:
 * //s = "catsandog"
 * //wordDict = ["cats", "dog", "sand", "and", "cat"]
 * //输出:
 * //[]
 * //
 * // Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯
 * @author binarySigh
 * @date 2021/7/21 21:46
 */
public class LC0140_WordBreak {
    public static void main(String[] args){
        /*String s = "catsanddog";
        String[] words = {"cat", "cats", "and", "sand", "dog"};*/

        /*String s = "pineapplepenapple";
        String[] words = {"apple", "pen", "applepen", "pine", "pineapple"};*/

        String s = "catsandog";
        String[] words = {"cats", "dog", "sand", "and", "cat"};
        List<String> ans = wordBreak(s, Arrays.asList(words));
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:5 ms,击败了25.46% 的Java用户
     * 		内存消耗:37 MB,击败了22.46% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict.size());
        for(String word : wordDict){
            set.add(word);
        }
        List<List<String>> dp = new ArrayList<>(s.length());
        for(int i = 0; i < s.length(); i++){
            dp.add(null);
        }
        process(s, 0, set, dp);
        return dp.get(0);
    }

    public static void process(String s, int index, HashSet<String> set, List<List<String>> dp){
        List<String> cur = new ArrayList<>();
        for(int i = index + 1; i <= s.length(); i++){
            String curStr = s.substring(index, i);
            if(set.contains(curStr)){
                if(i == s.length()){
                    cur.add(curStr);
                    break;
                }
                if(dp.get(i) == null){
                    //为空说明该位置的可能性没有被计算过。需递归计算完再查验
                    process(s, i, set, dp);
                    if(dp.get(i).size() > 0){
                        for(String next : dp.get(i)){
                            cur.add(curStr + " " + next);
                        }
                    }
                } else if(dp.get(i).size() == 0){
                    //空集合说明该位置经过计算，但是没有刚好能分割完的可能结果，直接去下一个位置查看可能性
                    continue;
                } else {
                    //不为空且不是空集。遍历结果集拼接上当前字符
                    for(String next : dp.get(i)){
                        cur.add(curStr + " " + next);
                    }
                }
            }
        }
        dp.set(index, cur);
    }
}
