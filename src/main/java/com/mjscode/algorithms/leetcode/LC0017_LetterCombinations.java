package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * //
 * // 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * // 2 - a,b,c  ; 3 - d,e,f
 * // 4 - g,h,i  ; 5 - j,k,l
 * // 6 - m,n,o  ; 7 - p,q,r,s
 * // 8 - t,u,v  ; 9 - w,x,y,z
 * //
 * // 示例 1：
 * //
 * //输入：digits = "23"
 * //输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * //
 * // 示例 2：
 * //
 * //
 * //输入：digits = ""
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * //
 * //输入：digits = "2"
 * //输出：["a","b","c"]
 * //
 * // 提示：
 * //
 * // 0 <= digits.length <= 4
 * // digits[i] 是范围 ['2', '9'] 的一个数字。
 * //
 * // Related Topics 深度优先搜索 递归 字符串 回溯算法
 * @author binarySigh
 * @date 2021/4/15 22:47
 */
public class LC0017_LetterCombinations {

    public static void main(String[] args){
        //String digits = "23";
        String digits = "2";
        List<String> list = letterCombinations(digits);
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.8 MB,击败了98.06% 的Java用户
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        complete(list, 0, digits, new StringBuilder());
        return list;
    }

    public static void complete(List<String> list, int p, String dig, StringBuilder sb){
        if(p == dig.length()){
            list.add(sb.toString());
            return;
        }
        char[] cur = getCurChar(dig.charAt(p));
        for(int i = 0; i < cur.length; i++){
            sb.append(cur[i]);
            complete(list, p + 1, dig, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static char[] getCurChar(char c){
        char[] chars = null;
        if(c == '2'){
            chars = new char[]{'a','b','c'};
        } else if(c == '3'){
            chars = new char[]{'d','e','f'};
        } else if(c == '4'){
            chars = new char[]{'g','h','i'};
        } else if(c == '5'){
            chars = new char[]{'j','k','l'};
        } else if(c == '6'){
            chars = new char[]{'m','n','o'};
        } else if(c == '7'){
            chars = new char[]{'p','q','r','s'};
        } else if(c == '8'){
            chars = new char[]{'t','u','v'};
        } else if(c == '9'){
            chars = new char[]{'w','x','y','z'};
        }
        return chars;
    }
}
