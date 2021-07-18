package com.mjscode.algorithms.leetcode.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * //编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * //
 * // 注意：本题相对原题稍作修改
 * //
 * // 示例:
 * //
 * // 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * //输出:
 * //[
 * //  ["ate","eat","tea"],
 * //  ["nat","tan"],
 * //  ["bat"]
 * //]
 * //
 * // 说明：
 * //
 * // 所有输入均为小写字母。
 * // 不考虑答案输出的顺序。
 * //
 * // Related Topics 哈希表 字符串 排序
 * @author binarySigh
 * @date 2021/7/18 8:41
 */
public class The10_02_GroupAnagrams {
    public static void main(String[] args){
        //String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        // --> [["","",""]]
        String[] strs = {"","",""};
        System.out.println(groupAnagrams(strs));
    }

    /**
     * 本题实际解题时无需考虑 “排列不同” 这一设定。自己吃设定，傻逼
     * 解答成功:
     * 		执行耗时:297 ms,击败了6.84% 的Java用户
     * 		内存消耗:41.1 MB,击败了91.26% 的Java用户
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        int[][] count = new int[strs.length][26];
        for(int i = 0; i < strs.length; i++){
            for(int j = 0; j < strs[i].length(); j++){
                count[i][strs[i].charAt(j) - 'a']++;
            }
        }
        List<List<String>> ans = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];
        //先处理空串。本题空串特殊处理，因为设定是空串之间视作异构
        //addNullStr(strs, visited, ans);
        for(int i = 0; i < strs.length; i++){
            if(visited[i]){
                continue;
            }
            // set去重，根据异构词定义，不能放排列相同的词进来，也就是本次 curAns 中已收录的词将会直接跳过
            //HashSet<String> set = new HashSet<>();
            List<String> curAns = new ArrayList<>();
            curAns.add(strs[i]);
            //set.add(strs[i]);
            // 由于 i,j 遍历的顺序性，这一步 visited[i] 的赋值可以省略，节约命令数
            //visited[i] = true;
            for(int j = i + 1; j < strs.length; j++){
                if(visited[j] || /*set.contains(strs[j]) || */strs[i].length() != strs[j].length()){
                    continue;
                }
                int t = 0;
                for(; t < 26; t++){
                    if(count[i][t] != count[j][t]){
                        break;
                    }
                }
                if(t == 26){
                    //set.add(strs[j]);
                    curAns.add(strs[j]);
                    visited[j] = true;
                }
            }
            ans.add(curAns);
        }
        return ans;
    }/*

    private static void addNullStr(String[] strs, boolean[] visited, List<List<String>> ans) {
        List<String> curAns = new ArrayList<>();
        for(int i = 0; i < strs.length; i++){
            if(strs[i].length() == 0){
                curAns.add(strs[i]);
                visited[i] = true;
            }
        }
        if(curAns.size() > 0){
            ans.add(curAns);
        }
    }*/
}
