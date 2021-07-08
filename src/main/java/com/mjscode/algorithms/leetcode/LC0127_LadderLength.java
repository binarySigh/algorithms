package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * //
 * // 序列中第一个单词是 beginWord 。
 * // 序列中最后一个单词是 endWord 。
 * // 每次转换只能改变一个字母。
 * // 转换过程中的中间单词必须是字典 wordList 中的单词。
 * //
 * // 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
 * //的 单词数目 。如果不存在这样的转换序列，返回 0。
 * //
 * // 示例 1：
 * //
 * //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
 * //g","cog"]
 * //输出：5
 * //解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * //
 * // 示例 2：
 * //
 * //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
 * //g"]
 * //输出：0
 * //解释：endWord "cog" 不在字典中，所以无法进行转换。
 * //
 * // 提示：
 * //
 * // 1 <= beginWord.length <= 10
 * // endWord.length == beginWord.length
 * // 1 <= wordList.length <= 5000
 * // wordList[i].length == beginWord.length
 * // beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * // beginWord != endWord
 * // wordList 中的所有字符串 互不相同
 * //
 * // Related Topics 广度优先搜索 哈希表 字符串
 * @author binarySigh
 * @date 2021/7/7 21:06
 */
public class LC0127_LadderLength {
    public static void main(String[] args){
        // --> 5
        /*String beginWord = "hit";
        String endWord = "cog";
        String[] ws = {"hot","dot","dog","lot","log","cog"};*/

        // --> 0
        String beginWord = "hit";
        String endWord = "cog";
        String[] ws = {"hot","dot","dog","lot","log"};
        System.out.println(ladderLength(beginWord, endWord, Arrays.asList(ws)));
    }

    /**
     * 解答成功:
     * 		执行耗时:148 ms,击败了39.01% 的Java用户
     * 		内存消耗:41.8 MB,击败了21.99% 的Java用户
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList.size());
        for(String s : wordList){
            words.add(s);
        }
        if(!words.contains(endWord)){
            return 0;
        }
        int len = beginWord.length();
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        set.add(beginWord);
        queue.addFirst(beginWord);
        String nextEnd = beginWord;
        String curEnd = beginWord;
        int level = 1;
        while(!queue.isEmpty()){
            String cur = queue.pollLast();
            if(cur.equals(endWord)){
                return level;
            }
            StringBuilder sb = new StringBuilder(cur);
            for(int i = 0; i < len; i++){
                char curChar = cur.charAt(i);
                for(int j = 0; j < 26; j++){
                    String newChar = "" + (char)(j + 'a');
                    sb.replace(i, i + 1, newChar);
                    if(words.contains(sb.toString()) && !set.contains(sb.toString())){
                        nextEnd = sb.toString();
                        queue.addFirst(nextEnd);
                        set.add(nextEnd);
                    }
                }
                sb.replace(i, i + 1, "" + curChar);
            }
            if(cur.equals(curEnd)){
                curEnd = nextEnd;
                level++;
            }
        }
        return 0;
    }
}
