package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * //给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * //
 * // 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * //
 * // 示例 1：
 * //
 * //输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * //输出：[0,9]
 * //解释：
 * //从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * //输出的顺序不重要, [9,0] 也是有效答案。
 * //
 * // 示例 2：
 * //
 * //输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * //输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * //输出：[6,9,12]
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 104
 * // s 由小写英文字母组成
 * // 1 <= words.length <= 5000
 * // 1 <= words[i].length <= 30
 * // words[i] 由小写英文字母组成
 * //
 * // Related Topics 哈希表 字符串 滑动窗口
 * @author binarySigh
 * @date 2021/7/29 20:00
 */
public class LC0030_FindSubstring {

    /**
     * 执行结果： 通过
     *      *      * 执行用时： 89 ms , 在所有 Java 提交中击败了 62.2% 的用户
     *      *      * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 88.9% 的用户
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words){
        HashMap<String, Integer> ws = new HashMap<>(words.length);
        int len = words[0].length();
        int maxWin = len * words.length;
        for(String word : words){
            ws.put(word, ws.getOrDefault(word, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> rec = new HashMap<>(ws.size());
        for(int i = 0; i <= s.length() - maxWin; i++){
            if(!ws.containsKey(s.substring(i, i + len))){
                continue;
            }
            int j = i;
            for(; j < i + maxWin;){
                String cur = s.substring(j, j + len);
                if(!ws.containsKey(cur)){
                    break;
                } else {
                    if(!rec.containsKey(cur)){
                        rec.put(cur, 1);
                    } else if(rec.get(cur) < ws.get(cur)) {
                        rec.put(cur, rec.get(cur) + 1);
                    } else {
                        break;
                    }
                    j += len;
                }
            }
            if(j == i + maxWin){
                ans.add(i);
            }
            rec.clear();
        }
        return ans;
    }
}
