package com.mjscode.algorithms.leetcode;

/**
 * //可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * //
 * // 'A'：Absent，缺勤
 * // 'L'：Late，迟到
 * // 'P'：Present，到场
 * //
 * // 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * //
 * // 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * // 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * //
 * // 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7
 * //取余 的结果。
 * //
 * // 示例 1：
 * //
 * //输入：n = 2
 * //输出：8
 * //解释：
 * //有 8 种长度为 2 的记录将被视为可奖励：
 * //"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * //只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：3
 * //
 * // 示例 3：
 * //
 * //输入：n = 10101
 * //输出：183236316
 * //
 * // 提示：
 * //
 * // 1 <= n <= 105
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/8/17 8:59
 */
public class LC0552_CheckRecord {
    public static void main(String[] args) {
        /*String s = "PPALLL";
        System.out.println(check(s));*/

        // --> 183236316
        //int n = 10101;

        int n = 2;
        //System.out.println(compare(n));
        System.out.println(checkRecord(n));
    }

    /**
     * 解答成功:
     * 		执行耗时:90 ms,击败了45.75% 的Java用户
     * 		内存消耗:45.8 MB,击败了42.55% 的Java用户
     * @param n
     * @return
     */
    public static int checkRecord(int n) {
        int mod = (int)Math.pow(10, 9) + 7;
        //dp[i][j][t] 含义：
        // i : 0 -> 到t个字符为止，一共出现过 0 个A； 1 -> 到t个字符为止，一共出现过 1 个A
        // j : 0 -> 到t个字符连续出现过 0 个L； 1 -> 到t个字符连续出现过 1 个L；2 -> 到t个字符连续出现过 2 个L
        int[][][] dp = new int[2][3][n];
        dp[0][0][0] = 1;
        dp[0][1][0] = 1;
        dp[1][0][0] = 1;
        for(int t = 0; t < n - 1; t++){
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 3; j++){
                    if(i == 1 && j == 2) {
                        //当前已出现过1次A，连续出现2个L。
                        // 因此该情况只能选择将下一个字符变为P
                        dp[1][0][t + 1] = (dp[1][0][t + 1] + dp[i][j][t]) % mod; //变为P
                    } else if(i == 1) {
                        //当前已出现过1次A，连续出现0 或 1个L。
                        // 因此该情况可选择将下一个字符变为P 或者 L
                        dp[1][0][t + 1] = (dp[1][0][t + 1] + dp[i][j][t]) % mod; //变为P
                        dp[1][j + 1][t + 1] = (dp[1][j + 1][t + 1] + dp[i][j][t]) % mod; //变为L
                    } else if(j == 2) {
                        //当前已出现过0次A，连续出现2个L。
                        // 因此该情况可选择将下一个字符变为P 或者 A
                        dp[i][0][t + 1] = (dp[i][0][t + 1] + dp[i][j][t]) % mod; //变为P
                        dp[1][0][t + 1] = (dp[1][0][t + 1] + dp[i][j][t]) % mod; //变为A
                    } else {
                        //当前已出现过0次A，连续出现0 或 1个L。
                        // 因此该情况可选择将下一个字符变为P 或者 A 或者L
                        dp[1][0][t + 1] = (dp[1][0][t + 1] + dp[i][j][t]) % mod; //变为A
                        dp[0][j + 1][t + 1] = (dp[0][j + 1][t + 1] + dp[i][j][t]) % mod; //变为L
                        dp[0][0][t + 1] = (dp[0][0][t + 1] + dp[i][j][t]) % mod; //变为P
                    }
                }
            }
        }
        //统计所有可能性
        int ans = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                ans = (ans + dp[i][j][n - 1]) % mod;
            }
        }
        return ans;
    }

    public static int compare(int n) {
        return process(n, "", 0, 0);
    }

    public static int process(int n, String s, int a, int l){
        if(a >= 2 || l >= 3){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        int mod = (int)Math.pow(10, 9) + 7;
        int ans = 0;
        ans = (ans + process(n - 1, s + "A", a + 1, 0)) % mod;
        ans = (ans + process(n - 1, s + "L", a, l + 1)) % mod;
        ans = (ans + process(n - 1, s + "P", a, 0)) % mod;
        return ans;
    }

    public static boolean check(String s) {
        int a = 0;
        int l = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'A') {
                a++;
                l = 0;
            } else if(s.charAt(i) == 'L'){
                l++;
            } else {
                l = 0;
            }
            if(a >= 2 || l >= 3) {
                return false;
            }
        }
        return true;
    }
}
