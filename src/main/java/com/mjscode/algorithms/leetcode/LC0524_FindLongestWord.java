package com.mjscode.algorithms.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * //给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * //
 * // 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * //输出："apple"
 * //
 * // 示例 2：
 * //
 * //输入：s = "abpcplea", dictionary = ["a","b","c"]
 * //输出："a"
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 1000
 * // 1 <= dictionary.length <= 1000
 * // 1 <= dictionary[i].length <= 1000
 * // s 和 dictionary[i] 仅由小写英文字母组成
 * //
 * // Related Topics 数组 双指针 字符串 排序
 * @author binarySigh
 * @date 2021/9/14 19:43
 */
public class LC0524_FindLongestWord {

    public static void main(String[] args) {
        // --> apple
  /*String s = "abpcplea";
  String s1 = "ale";
  String s2 = "apple";
  String s3 = "monkey";
  String s4 = "plea";
  String ans = findLongestWord(s, Arrays.asList(s1, s2, s3, s4));*/

        String s = "abpcplea";
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String ans = findLongestWord(s, Arrays.asList(s1, s2, s3));

        System.out.println(ans);

    }

    /**
     * 解答成功:
     * 		执行耗时:23 ms,击败了42.1% 的Java用户
     * 		内存消耗:39.2 MB,击败了84.4% 的Java用户
     * @param s
     * @param dictionary
     * @return
     */
    public static String findLongestWord(String s, List<String> dictionary){
        Collections.sort(dictionary, (a, b) -> {
            //按长度 长 -> 短排序
            if(a.length() != b.length()){
                return b.length() - a.length();
            } else {
                //长度相同的字典序小的排前面
                int i = 0, j = 0;
                while(i < a.length() && j < b.length() && a.charAt(i) == b.charAt(j)){
                    i++;
                    j++;
                }
                //长度相同字典序也相同，返回0
                if(i == a.length()){
                    return 0;
                } else {
                    return a.charAt(i) - b.charAt(j);
                }
            }
        });
        for(String cur : dictionary){
            int i = 0, j = 0;
            while(i < cur.length() && j < s.length()){
                if(cur.charAt(i) == s.charAt(j)){
                    i++;
                }
                j++;
            }
            if(i == cur.length()){
                return cur;
            }
        }
        return "";
    }

}
