package com.mjscode.algorithms.leetcode;

import java.util.PriorityQueue;

/**
 * //给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * //
 * // 示例 1:
 * //
 * //输入:
 * //"tree"
 * //
 * //输出:
 * //"eert"
 * //
 * //解释:
 * //'e'出现两次，'r'和't'都只出现一次。
 * //因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * //
 * // 示例 2:
 * //
 * //输入:
 * //"cccaaa"
 * //
 * //输出:
 * //"cccaaa"
 * //
 * //解释:
 * //'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * //注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * //
 * // 示例 3:
 * //
 * //输入:
 * //"Aabb"
 * //
 * //输出:
 * //"bbAa"
 * //
 * //解释:
 * //此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * //注意'A'和'a'被认为是两种不同的字符。
 * //
 * // Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列）
 * @author binarySigh
 * @date 2021/7/3 21:58
 */
public class LC0451_FrequencySort {
    public static void main(String[] args){
        String s = "Aabb";
        System.out.println(frequencySort(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:7 ms,击败了88.34% 的Java用户
     * 		内存消耗:38.2 MB,击败了99.48% 的Java用户
     * @param s
     * @return
     */
    public static String frequencySort(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        int[] chars = new int[256];
        for(int i = 0; i < s.length(); i++){
            chars[s.charAt(i)]++;
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for(int i = 0; i < 256; i++){
            if(chars[i] > 0) {
                heap.add(new Node((char) i, chars[i]));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!heap.isEmpty()){
            Node cur = heap.poll();
            while(cur.count > 0) {
                sb.append(cur.key);
                cur.count--;
            }
        }
        return sb.toString();
    }

    public static class Node{
        char key;
        int count;
        public Node(){};
        public Node(char k, int c){
            this.key = k;
            this.count = c;
        }
    }
}
