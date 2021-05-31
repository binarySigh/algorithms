package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：
 * //
 * // 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效
 * //的翻转时便意味着游戏结束，则另一方获胜。
 * //
 * // 请你写出一个函数来判定起始玩家 是否存在必胜的方案 ：如果存在，返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：currentState = "++++"
 * //输出：true
 * //解释：起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。
 * //
 * // 示例 2：
 * //
 * //输入：currentState = "+"
 * //输出：false
 * //
 * // 提示：
 * //
 * // 1 <= currentState.length <= 60
 * // currentState[i] 不是 '+' 就是 '-'
 * //
 * // 进阶：请推导你算法的时间复杂度。
 * // Related Topics 极小化极大 回溯算法
 * @author binarySigh
 * @date 2021/5/23 21:53
 */
public class LC0294_CanWin {
    public static void main(String[] args){
        String currentState = getStr(60);
        System.out.println(currentState);
        System.out.println("我的答案：" + canWin(currentState));
        System.out.println("正确答案：" + compare(currentState));
        System.out.println("-------------------测试开始---------------");
        /*for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 10) + 1;
            String str = getStr(len);
            boolean result = canWin(str);
            boolean compare = compare(str);
            if(result != compare){
                System.out.println("------第[" + i + "]次测试出错---------");
                System.out.println("currentState : " + str);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + compare);
                break;
            }
        }*/
        System.out.println("-------------------测试开始---------------");

        /*for(int i = 1; i < 100; i++){
            String s = getStrOfPlus(i);
            System.out.println("字符长度：" + i + ", 结果：" + compare(s));
        }*/
    }

    /**
     * 回溯 + 记忆化
     *解答成功:
     * 		执行耗时:11 ms,击败了77.88% 的Java用户
     * 		内存消耗:39.4 MB,击败了35.58% 的Java用户
     * @param currentState
     * @return
     */
    public static boolean canWin(String currentState) {
        if(currentState.length() < 2){
            return false;
        }
        return dfs(currentState, new HashMap<String, Boolean>());
    }

    public static boolean dfs(String s, HashMap<String, Boolean> map){
        StringBuilder sb = new StringBuilder(s);
        boolean canWin = false;
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+'){
                boolean nextWin = true;
                sb.replace(i, i + 2, "--");
                if(map.containsKey(sb.toString())){
                    nextWin = map.get(sb.toString());
                } else {
                    nextWin = dfs(sb.toString(), map);
                    map.put(sb.toString(), nextWin);
                }
                sb.replace(i, i + 2, "++");
                if(!nextWin){
                    canWin = true;
                    break;
                }
            }
        }
        map.put(s, canWin);
        return canWin;
    }

    public static boolean compare(String currentState){
        if(currentState.length() < 2){
            return false;
        }
        int len = currentState.length();
        StringBuilder sb = new StringBuilder(currentState);
        for(int i = 0; i < len - 1; i++){
            if(currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+'){
                sb.replace(i, i + 2, "--");
                if(!compare(sb.toString())){
                    return true;
                }
                sb.replace(i, i + 2, "++");
            }
        }
        return false;
    }

    public static String getStr(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            char c = Math.random() > 0.3 ? '+' : '-';
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getStrOfPlus(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            sb.append('+');
        }
        return sb.toString();
    }
}
