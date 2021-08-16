package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * //
 * // 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * //
 * // 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * //
 * // 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * //
 * // 说明:
 * //
 * // 单词是指由非空格字符组成的字符序列。
 * // 每个单词的长度大于 0，小于等于 maxWidth。
 * // 输入单词数组 words 至少包含一个单词。
 * //
 * // 示例:
 * //
 * // 输入:
 * //words = ["This", "is", "an", "example", "of", "text", "justification."]
 * //maxWidth = 16
 * //输出:
 * //[
 * //   "This    is    an",
 * //   "example  of text",
 * //   "justification.  "
 * //]
 * //
 * // 示例 2:
 * //
 * // 输入:
 * //words = ["What","must","be","acknowledgment","shall","be"]
 * //maxWidth = 16
 * //输出:
 * //[
 * //  "What   must   be",
 * //  "acknowledgment  ",
 * //  "shall be        "
 * //]
 * //解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * //     因为最后一行应为左对齐，而不是左右两端对齐。
 * //     第二行同样为左对齐，这是因为这行只包含一个单词。
 * //
 * // 示例 3:
 * //
 * // 输入:
 * //words = ["Science","is","what","we","understand","well","enough","to","explain
 * //",
 * //         "to","a","computer.","Art","is","everything","else","we","do"]
 * //maxWidth = 20
 * //输出:
 * //[
 * //  "Science  is  what we",
 * //  "understand      well",
 * //  "enough to explain to",
 * //  "a  computer.  Art is",
 * //  "everything  else  we",
 * //  "do                  "
 * //]
 * //
 * // Related Topics 字符串 模拟
 * @author binarySigh
 * @date 2021/8/16 21:23
 */
public class LC0068_FullJustify {

    public static void main(String[] args){
        /*String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;*/

        /*String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;*/

        /*String[] words = {"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;*/

        //
        String[] words = {"ask","not","what","your","country","can",
                "do","for","you","ask","what","you","can","do","for","your","country"};
        int maxWidth = 16;
        List<String> ans = fullJustify(words, maxWidth);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了7.77% 的Java用户
     * 		内存消耗:38.7 MB,击败了5.01% 的Java用户
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0, j = 0;
        int size = 0;
        int totalBlanks = 0;
        int curBlanks = 0;
        List<String> ans = new ArrayList<>();
        while(j < words.length) {
            while(j < words.length){
                size += words[j].length();
                if(j + 1 == words.length || size + j + 1 - i + words[j + 1].length() > maxWidth) {
                    break;
                } else {
                    j++;
                }
            }
            String cur = "";
            if(i == j || j == words.length - 1) {
                //当前行只有一个，或者当前已是最后一行,均为左对齐
                while(i <= j){
                    cur += words[i++];
                    cur += cur.length() == maxWidth ? "" : " ";
                }
                cur += getBlanks(maxWidth - cur.length());
            } else {
                totalBlanks = maxWidth - size;
                curBlanks = totalBlanks % (j - i) == 0 ? (totalBlanks / (j - i)) : (totalBlanks / (j - i) + 1);
                cur += words[i++] + getBlanks(curBlanks);
                totalBlanks -= curBlanks;
                while(i <= j) {
                    if(j > i) {
                        curBlanks = totalBlanks % (j - i) == 0 ? (totalBlanks / (j - i)) : (totalBlanks / (j - i) + 1);
                    }
                    cur += words[i++];
                    if(cur.length() < maxWidth) {
                        cur += getBlanks(curBlanks);
                        totalBlanks -= curBlanks;
                    }
                }
            }
            ans.add(cur);
            j++;
            size = 0;
        }
        return ans;
    }

    public static String getBlanks(int len) {
        String s = "";
        while(len-- > 0){
            s += " ";
        }
        return s;
    }
}
