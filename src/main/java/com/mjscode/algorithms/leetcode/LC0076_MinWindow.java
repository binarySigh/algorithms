package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * //
 * // 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * //
 * // 示例 1：
 * //
 * //输入：s = "ADOBECODEBANC", t = "ABC"
 * //输出："BANC"
 * //
 * // 示例 2：
 * //
 * //输入：s = "a", t = "a"
 * //输出："a"
 * //
 * // 提示：
 * //
 * // 1 <= s.length, t.length <= 105
 * // s 和 t 由英文字母组成
 * //
 * //进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window
 * @author binarySigh
 * @date 2021/5/21 22:08
 */
public class LC0076_MinWindow {
    public static void main(String[] args){
        // -> BANC
        /*String s = "ADOBECODEBANC";
        String t = "ABC";*/

        /*String s = "ADOBEFODEBAAAAANC";
        String t = "ABC";*/

        String s = "a";
        String t = "ac";
        System.out.println(minWindow(s, t));
    }

    /**
     * 解答成功:
     * 		执行耗时:14 ms,击败了66.19% 的Java用户
     * 		内存消耗:38.7 MB,击败了74.25% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if(len2 > len1){
            return "";
        }
        int ansBegin = 0, ansEnd = len1;
        int minSubLen = len1 + 1;
        //count 统计 t 串中各字符出现频次
        HashMap<Character, Integer> count = new HashMap<>(52);
        for(int i = 0; i < len2; i++){
            count.put(t.charAt(i), count.getOrDefault(t.charAt(i), 0) + 1);
        }
        //kinds 表示 t 中还有多少种字符串没有被完全包括进 窗口内
        int kinds = count.size();
        int L = 0, R = 0;
        //count.get(A) = x > 0 : A字符还有 x个没有被纳入窗口内
        //count.get(A) = x == 0 : A字符已完全被纳入窗口内
        //count.get(A) = -x < 0 : 窗口内不仅完全收集到了t中出现的所有A字符，并且还多收集了 x 个
        while(R < len1 && L <= len1 - len2){
            char cur = s.charAt(R);
            if(count.containsKey(cur)){
                //当前的字符在t串中出现过
                if(count.get(cur) == 1){
                    //当前字符被纳入窗口之后，该字符已经完全包括进窗口内，则将kinds-1
                    kinds--;
                }
                count.put(cur, count.get(cur) - 1);
                //检查kinds 是否为0.为0说明窗口已经完全包含t串中所有字符，则需要收集答案，并收缩左边界
                while(kinds == 0){
                    //当前窗口已经收集到了一种可能性，比较并记录。然后收缩左边界直至 kinds > 0
                    // 先收集当前的可能性
                    if(minSubLen > (R - L + 1)){
                        minSubLen = R - L + 1;
                        ansBegin = L;
                        ansEnd = R;
                    }
                    //收缩左边界，直至 kinds > 0
                    char curL = s.charAt(L);
                    if(count.containsKey(curL)){
                        if(count.get(curL) == 0){
                            kinds++;
                        }
                        count.put(curL, count.get(curL) + 1);
                    }
                    //收缩左边界
                    L++;
                }
            }
            //扩展右边界
            R++;
        }
        //返回答案
        if(ansEnd == len1){
            return "";
        }
        return ansEnd == len1 - 1 ? s.substring(ansBegin) : s.substring(ansBegin, ansEnd + 1);
    }
}
