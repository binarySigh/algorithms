package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * //
 * // 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * //
 * // 示例 1：
 * //
 * //输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * //输出: ["i", "love"]
 * //解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * //    注意，按字母顺序 "i" 在 "love" 之前。
 * //
 * // 示例 2：
 * //
 * //输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
 * // = 4
 * //输出: ["the", "is", "sunny", "day"]
 * //解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * //    出现次数依次为 4, 3, 2 和 1 次。
 * //
 * // 注意：
 * //
 * // 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * // 输入的单词均由小写字母组成。
 * //
 * // 扩展练习：
 * //
 * // 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * //
 * // Related Topics 堆 字典树 哈希表
 * @author binarySigh
 * @date 2021/5/20 19:52
 */
public class LC0692_TopKFrequent {
    public static void main(String[] args){
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        List<String> ans = topKFrequent(words, k);
    }

    /**
     * 解答成功:
     * 		执行耗时:6 ms,击败了98.86% 的Java用户
     * 		内存消耗:38.5 MB,击败了72.40% 的Java用户
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>(k);
        if(words.length == 1){
            ans.add(words[0]);
            return ans;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(k, new MyComparator());
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            heap.add(entry);
        }
        while(!heap.isEmpty() && ans.size() < k){
            ans.add(heap.poll().getKey());
        }
        return ans;
    }

    public static class MyComparator implements Comparator<Map.Entry<String, Integer>>{

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            //按照词频降序排列
            if(o2.getValue() > o1.getValue()){
                return 1;
            } else if(o2.getValue() < o1.getValue()){
                return -1;
            } else {
                //词频相同按照字典序，小的在前
                int len = Math.max(o1.getKey().length(), o2.getKey().length());
                for(int i = 0; i < len; i++){
                    if(i >= o1.getKey().length()){
                        return -1;
                    } else if(i >= o2.getKey().length()){
                        return 1;
                    } else if(o1.getKey().charAt(i) != o2.getKey().charAt(i)){
                        return o1.getKey().charAt(i) - o2.getKey().charAt(i);
                    }
                }
            }
            return 0;
        }
    }
}
