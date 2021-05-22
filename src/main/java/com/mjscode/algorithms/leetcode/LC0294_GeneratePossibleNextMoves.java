package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：
 * //
 * // 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效
 * //的翻转时便意味着游戏结束，则另一方获胜。
 * //
 * // 计算并返回 一次有效操作 后，字符串 currentState 所有的可能状态，返回结果可以按 任意顺序 排列。如果不存在可能的有效操作，请返回一个空列表
 * // [] 。
 * //
 * // 示例 1：
 * //
 * //输入：currentState = "++++"
 * //输出：["--++","+--+","++--"]
 * //
 * // 示例 2：
 * //
 * //输入：currentState = "+"
 * //输出：[]
 * //
 * // 提示：
 * //
 * // 1 <= currentState.length <= 500
 * // currentState[i] 不是 '+' 就是 '-'
 * //
 * // Related Topics 字符串
 * @author binarySigh
 * @date 2021/5/22 13:04
 */
public class LC0294_GeneratePossibleNextMoves {
    public static void main(String[] args){
        String currentState = "--";
        List<String> ans = generatePossibleNextMoves(currentState);
    }

    /**
     * 解答成功:
     * 		执行耗时:5 ms,击败了25.38% 的Java用户
     * 		内存消耗:39 MB,击败了6.15% 的Java用户
     * @param currentState
     * @return
     */
    public static List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();
        if(currentState == null || currentState.length() < 2){
            return ans;
        }
        for(int i = 0; i < currentState.length() - 1; i++){
            if(currentState.charAt(i) == '+' && currentState.charAt(i) == currentState.charAt(i + 1)){
                String s = currentState.substring(0, i) + "--" +
                        (i + 2 < currentState.length() ? currentState.substring(i + 2) : "");
                ans.add(s);
            }
        }
        return ans;
    }
}
