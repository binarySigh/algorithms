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

    public static void main(String[] args) {
        // --> [AAAAACCCCC, CCCCCAAAAA]
//  String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        String s = "AAAAACCCCCAAAAACCCCC";

//  String s = "AAAAAA";
        List<String> ans = findRepeatedDnaSequences(s);
        List<String> com = findRepeatedDnaSequences1(s);
        System.out.println(ans);
        System.out.println(com);
    }

    public static List<String> findRepeatedDnaSequences1(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        if(s == null) {
            return ans;
        }
        String cur = "";
        Integer cnts = null;
        for(int i = 0; i <= s.length() - 10; i++){
            cur = s.substring(i, i + 10);
            cnts = map.get(cur);
            if(cnts == null) {
                map.put(cur, 1);
            } else if(cnts > 0) {
                ans.add(cur);
                map.put(cur, -1);
            }
        }
        return ans;
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int mod = 3 << 18;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        if(s == null || s.length() < 10) {
            return ans;
        }
        int cur = 0;
        int i = 0;
        for(; i < 10; i++) {
            cur = cur << 2 | getInt(s.charAt(i));
        }
        map.put(cur, 1);
        for(; i < s.length(); i++) {
            cur -= (cur & mod);
            cur = cur << 2 | getInt(s.charAt(i));
            int cnts = map.getOrDefault(cur, 0);
            if(cnts == 0){
                map.put(cur, 1);
            } else if(cnts == 1) {
                ans.add(s.substring(i - 9, i + 1));
                map.put(cur, -1);
            }
        }
        return ans;
    }

    public static int getInt(char c) {
        if(c == 'A') return 0;
        if(c == 'C') return 1;
        if(c == 'G') return 2;
        else return 3;
    }

}
