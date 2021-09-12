package com.mjscode.algorithms.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * //现有一种使用英语字母的火星语言，这门语言的字母顺序与英语顺序不同。
 * //
 * // 给你一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * //
 * // 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种
 * // 顺序即可。
 * //
 * // 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * //
 * // 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * // 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 * //
 * // 示例 1：
 * //
 * //输入：words = ["wrt","wrf","er","ett","rftt"]
 * //输出："wertf"
 * //
 * // 示例 2：
 * //
 * //输入：words = ["z","x"]
 * //输出："zx"
 * //
 * // 示例 3：
 * //
 * //输入：words = ["z","x","z"]
 * //输出：""
 * //解释：不存在合法字母顺序，因此返回 "" 。
 * //
 * // 提示：
 * //
 * // 1 <= words.length <= 100
 * // 1 <= words[i].length <= 100
 * // words[i] 仅由小写英文字母组成
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 数组 字符串
 * @author binarySigh
 * @date 2021/9/12 19:53
 */
public class LC0269_AlienOrder {
    public static void main(String[] args){
        // --> wertf
        //String[] words = {"wrt","wrf","er","ett","rftt"};

        // --> zx
        //String[] words = {"z","x"};

        // --> acbz
        //String[] words = {"ac","ab","zc","zb"};

        // --> "z"
        String[] words = {"z", "z"};
        System.out.println(alienOrder(words));
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了93.31% 的Java用户
     * 		内存消耗:37.8 MB,击败了63.94% 的Java用户
     * @param words
     * @return
     */
    public static String alienOrder(String[] words) {
        // 关于字母的图结构。nodes[i][j] :
        // i -> 字母，取值 0~25，代表26个小写字母；
        // j -> 0 表示对应字母的入度表；1表示对应字母的出度表
        HashSet<Integer>[][] nodes = new HashSet[26][2];
        // k记录字典中出现的字母种数
        int k = 0;
        for(int i = 0; i < words.length; i++){
            int j = 0;
            //将当前字符串中字母加入图结构中
            for(char cs : words[i].toCharArray()){
                int c = cs - 'a';
                if(nodes[c][0] == null){
                    nodes[c][0] = new HashSet<>();
                    nodes[c][1] = new HashSet<>();
                    k++;
                }
            }
            if(i == 0){
                continue;
            }
            //比较找出当前串和前一串第一个不同的字母
            while(j < words[i].length() && j < words[i - 1].length()
                    && words[i].charAt(j) == words[i - 1].charAt(j)){
                j++;
            }
            if(j >= words[i - 1].length()){
                continue;
            }
            if(j >= words[i].length()){
                return "";
            }
            int pre = words[i - 1].charAt(j) - 'a';
            int cur = words[i].charAt(j) - 'a';
            nodes[pre][1].add(cur);
            nodes[cur][0].add(pre);
        }
        //拓补排序
        StringBuilder sb = new StringBuilder();
        while(k > 0){
            int begin = -1;
            //寻找入度为0的点
            for(int i = 0; i < 26; i++){
                if(nodes[i][0] != null && nodes[i][0].size() == 0){
                    begin = i;
                    break;
                }
            }
            if(begin == -1){
                //找不到入度为0的节点，说明顺序关系存在依赖循环，也即不存在特定字典序，返回空字符串即可
                return "";
            }
            sb.append((char)(begin + 'a'));
            Iterator ite = nodes[begin][1].iterator();
            //迭代找到他的下一个节点，并在下一节点中消除自己的影响
            while(ite.hasNext()){
                int cur = (int) ite.next();
                nodes[cur][0].remove(begin);
            }
            //将当前节点从nodes表中消除，也即将0，1位置均置空，剩余种类数也--
            nodes[begin][0] = null;
            nodes[begin][1] = null;
            k--;
        }
        return sb.toString();
    }
}
