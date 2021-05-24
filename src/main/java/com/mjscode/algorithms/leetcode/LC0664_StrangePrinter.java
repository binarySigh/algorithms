package com.mjscode.algorithms.leetcode;

/**
 * //有台奇怪的打印机有以下两个特殊要求：
 * //
 * // 打印机每次只能打印由 同一个字符 组成的序列。
 * // 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * //
 * // 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aaabbb"
 * //输出：2
 * //解释：首先打印 "aaa" 然后打印 "bbb"。
 * //
 * // 示例 2：
 * //
 * //输入：s = "aba"
 * //输出：2
 * //解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 100
 * // s 由小写英文字母组成
 * //
 * // Related Topics 深度优先搜索 动态规划
 * @author binarySigh
 * @date 2021/5/24 21:32
 */
public class LC0664_StrangePrinter {
    public static void main(String[] args){
        System.out.println("-------------测试开始------------");
        for(int i = 0; i < 50_0000; i++){
            int len = (int)(Math.random() * 20) + 1;
            String s = getStr(len);
            int result = strangePrinter(s);
            int compare = compare(s);
            if(result != compare){
                System.out.println("------第[" + i + "]次结果出错---------");
                System.out.println("s : " + s);
                System.out.println("测试结果：" + result);
                System.out.println("期望结果：" + compare);
                break;
            }
        }
        System.out.println("-------------测试结束------------");

        // --> 15
        //String s = "dddccbdbababaddcbcaabdbdddcccddbbaabddb";
        String s = "clfioonlcazpoilebuge";
        System.out.println("s : " + s);
        System.out.println("测试结果：" + strangePrinter(s));
        System.out.println("对照结果：" + compare(s));
        System.out.println("期望结果：" + 15);
    }

    /**
     * 解答成功:
     * 		执行耗时:31 ms,击败了48.12% 的Java用户
     * 		内存消耗:38.7 MB,击败了12.41% 的Java用户
     * @param s
     * @return
     */
    public static int strangePrinter(String s) {
        if(s.length() == 1){
            return 1;
        }
        int len = s.length();
        //dp[i][j] : i...j (含i,j)的子串如果要打印，最少需要多少次
        int[][] dp = new int[len][len];
        for(int col = 0; col < len; col++){
            for(int row = col; row >= 0; row--){
                if(row == col){
                    dp[row][col] = 1;
                } else {
                    char ch = s.charAt(col);
                    dp[row][col] = dp[row][col - 1] + 1;
                    for(int i = row; i < col;){
                        if(s.charAt(i) == ch){
                            while(i < col && s.charAt(i) == ch){
                                i++;
                            }
                            int curTimes = dp[row][i - 1] + dp[i][col - 1];
                            dp[row][col] = Math.min(dp[row][col], curTimes);
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
        return dp[0][len - 1];
    }

    public static int compare(String s){
        if(s.length() == 1){
            return 1;
        }
        return process(s, 0, s.length() - 1);
    }

    public static int process(String s, int L, int R){
        if(L == R){
            return 1;
        }
        int times = process(s, L, R - 1) + 1;
        char ch = s.charAt(R);
        for(int i = L; i < R;){
            if(s.charAt(i) == ch){
                while(i < R && s.charAt(i) == ch){
                    i++;
                }
                int curTimes = (L <= i - 1 ? process(s, L, i - 1) : 0) +
                        (i <= R - 1 ? process(s, i, R - 1) : 0);
                times = Math.min(times, curTimes);
            } else {
                i++;
            }
        }
        return times;
    }

    public static String getStr(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            char ch = (char)(Math.random() * 26 + 97);
            sb.append(ch);
        }
        return sb.toString();
    }
}
