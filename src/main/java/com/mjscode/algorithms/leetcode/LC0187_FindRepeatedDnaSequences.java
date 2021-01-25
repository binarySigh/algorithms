package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * //所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复
 * //序列有时会对研究非常有帮助。
 * //
 * // 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * //
 * // 示例 1：
 * //
 * //输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * //输出：["AAAAACCCCC","CCCCCAAAAA"]
 * //
 * // 示例 2：
 * //
 * //输入：s = "AAAAAAAAAAAAA"
 * //输出：["AAAAAAAAAA"]
 * //
 * // 提示：
 * //
 * // 0 <= s.length <= 105
 * // s[i] 为 'A'、'C'、'G' 或 'T'
 * //
 * // Related Topics 位运算 哈希表
 * @author binarySigh
 */
public class LC0187_FindRepeatedDnaSequences {
    public static void main(String[] args){
        String s = "AAAAAAAAAAA";
        ArrayList<String> list = findRepeatedDnaSequencesInMap(s);
        ArrayUtils.showArray(list);
    }

    public static ArrayList<String> findRepeatedDnaSequences(String s){
        //洗掉无效输入
        if(s == null || s.length() <= 10){
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>(s.length() - 10);


        return list;
    }

    /**
     * 可行的优化思路：维护一个长度为10的窗口，这样就不需要每次都嵌套一层10次的循环
     * 解答成功:
     * 		执行耗时:82 ms,击败了5.58% 的Java用户
     * 		内存消耗:53.2 MB,击败了5.04% 的Java用户
     * @param s
     * @return
     */
    public static ArrayList<String> findRepeatedDnaSequencesInMap(String s){
        //洗掉无效输入
        if(s == null || s.length() <= 10){
            return new ArrayList<>();
        }
        //int eor = 0;
        String cur = "";
        //HashMap<Integer, Integer> map = new HashMap<>(s.length() - 10);
        HashMap<String, Integer> map = new HashMap<>(s.length() - 10);
        ArrayList<String> list = new ArrayList<>(s.length() - 10);
        for(int i = 0; i <= s.length() - 10; i++){
            for(int j = i; j < i + 10; j++){
                //eor ^= (int)(s.charAt(j));
                cur += s.charAt(j);
            }
            //如果当前十位异或值在map中存在且有效,则往list中加入当前十位的子串，并将map中记录置为无效
            if(map.containsKey(cur) && map.get(cur) >= 0){
                list.add(cur);
                map.put(cur, -1);
            }
            //如果当前十位异或结果不存在，则直接加入
            else if(!map.containsKey(cur)){
                map.put(cur, i);
            }
            //如果当前异或值存在，但是值是无效状态，则直接略过
            /*else{
            }*/
            //将cur恢复成空串，防止影响下一轮结果
            cur = "";
        }
        return list;
    }
}
