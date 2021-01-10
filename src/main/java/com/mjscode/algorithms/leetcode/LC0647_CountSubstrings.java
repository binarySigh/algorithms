package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * //
 * // 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * //
 * // 示例 1：
 * //
 * // 输入："abc"
 * //输出：3
 * //解释：三个回文子串: "a", "b", "c"
 * //
 * // 示例 2：
 * //
 * // 输入："aaa"
 * //输出：6
 * //解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * //
 * // 提示：
 * //
 * // 输入的字符串长度不会超过 1000 。
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 */
public class LC0647_CountSubstrings {
    public static void main(String[] args){
        //String s = "abab";
        String s = StringUtils.generateString(10, -1);
        int compareCount = compare(s);
        int count = countSubstrings(s);
        System.out.println(s);
        System.out.println("count: " + count);
        System.out.println("compareCount: " + compareCount);
    }

    /**
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:12 ms,击败了42.53% 的Java用户
     * 		内存消耗:47.4 MB,击败了5.01% 的Java用户
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        if(s == null){
            return 0;
        }
        if(s.length() <= 1){
            return s.length();
        }
        char[] chars = s.toCharArray();
        //辅助数组 list, 具体含义以list.get(i)为例，说明如下：
        //  一定以chars[i]位置为结尾的回文子串的情况，
        //      其中list.get(i).get(0)表示从chars[0]~chars[i],一共已经产生了多少种回文子串
        //      list.get(i).get(1) ~ list.get(i).get(n)表示如果以i位置字符结尾的回文子串一共有N个，那么这n个子串的长度分别是多少
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(chars.length);
        int count = 1;
        for(int i = 0; i < chars.length; i++){
            ArrayList<Integer> palindromes = new ArrayList<>();
            if(i == 0){
                palindromes.add(1);
                palindromes.add(1);
            } else {
                count = chars[i] == chars[i - 1] ? 2 : 1;
                palindromes.add(count);
                palindromes.add(1);
                if(chars[i] == chars[i - 1]){
                    palindromes.add(2);
                }
                for(int j = 1; j < list.get(i - 1).size(); j++){
                    if(i - 1 - list.get(i - 1).get(j) >= 0 && chars[i - 1 - list.get(i - 1).get(j)] == chars[i]){
                        palindromes.add(list.get(i - 1).get(j) + 2);
                        count++;
                    }
                }
                count += list.get(i - 1).get(0);
                palindromes.set(0,count);
            }
            list.add(palindromes);
        }
        return list.get(chars.length - 1).get(0);
    }

    /**
     * 解法1：暴力递归
     * @param s
     * @return
     */
    public static int compare(String s){
        if(s == null){
            return 0;
        }
        if(s.length() <= 1){
            return s.length();
        }
        char[] chars = s.toCharArray();
        ArrayList<Integer> len = process(chars, chars.length - 1);
        return len.get(0);
    }

    /**
     *  返回值list的 0 号位置表示：初始字符数组从0~index位置已有的回文子串总数量
     *        list 后面的元素表示以 index 位置字符结尾的回文子串的长度
     * @param chars 字符数组
     * @param index 当前的位置
     * @return
     */
    public static ArrayList<Integer> process(char[] chars, int index){
        ArrayList<Integer> result = new ArrayList<>();
        if(index == 0){
            result.add(1);
            result.add(1);
            return result;
        }
        //如果index位置字符串和index-1 位置字符相同，则初始就有两个回文串，否则初始只有一个（即index位置自己构成的回文串）
        int count = chars[index] == chars[index - 1] ? 2 : 1;
        result.add(count);
        //将初始就可判断的回文串长度加入list
        result.add(1);
        if(chars[index] == chars[index - 1]){
            result.add(2);
        }
        //根据上一个位置字符对应的回文串分布情况 寻找其他可能
        ArrayList<Integer> len = process(chars, index - 1);
        for(int i = 1; i < len.size(); i++){
            //index - 1位置回文串没有顶到chars[0]，且该回文串左边界-1位置的字符与index位置字符相同
            if(index - 1 - len.get(i) >= 0 && chars[index - 1 - len.get(i)] == chars[index]){
                //可能性增加
                result.add(len.get(i) + 2);
                //计数也++
                count++;
            }
        }
        //计算目前为止全部可能性，并更新到result.get(0)中
        count += len.get(0);
        result.set(0, count);
        return result;
    }
}
