package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * //
 * // 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * //
 * // 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * //
 * // 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * //
 * // 示例 1：
 * //
 * //输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * //输出：2
 * //解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * //总的来说，有两种计划。
 * //
 * // 示例 2：
 * //
 * //输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * //输出：7
 * //解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * //有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * //
 * // 提示：
 * //
 * // 1 <= n <= 100
 * // 0 <= minProfit <= 100
 * // 1 <= group.length <= 100
 * // 1 <= group[i] <= 100
 * // profit.length == group.length
 * // 0 <= profit[i] <= 100
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/6/9 0:08
 */
public class LC0879_ProfitableSchemes {
    public static void main(String[] args){
        // --> 2
        /*int[] groups1 = {2,2};
        int[] profits1 = {2,3};
        int n1 = 5, minProfits1 = 3;*/


        // --> 7
        int[] groups1 = {7,9,1};
        int[] profits1 = {3,2,5};
        int n1 = 65, minProfits1 = 0;
        System.out.println(compare(n1, minProfits1, groups1, profits1));
        System.out.println(profitableSchemes(n1, minProfits1, groups1, profits1));
        System.out.println(profitableSchemes1(n1, minProfits1, groups1, profits1));

        System.out.println("-----------------测试开始-----------------");
        /*for(int i = 0; i < 50_0000; i++){
            int len = (int)(Math.random() * 10) + 1;
            int[] groups = getNums(len, 1);
            int[] profits = getNums(len, 0);
            int n = (int)(Math.random() * 100) + 1;
            int minProfits = (int)(Math.random() * 101);
            int result = profitableSchemes1(n, minProfits, groups, profits);
            int answer = compare(n, minProfits, groups, profits);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错---------");
                ArrayUtils.showArray(groups);
                ArrayUtils.showArray(profits);
                System.out.println("n = " + n + ", minProfits = " + minProfits);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + answer);
                break;
            }
        }*/
        System.out.println("-----------------测试结束-----------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:1299 ms,击败了5.26% 的Java用户
     * 		内存消耗:61.5 MB,击败了5.03% 的Java用户
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        int sum = 1;
        for (int i : profit) {
            sum += i;
        }
        int[][][] dp = new int[n + 1][sum][2];
        //第一层
        dp[0][0][0] = 1;
        if (group[0] <= n) {
            dp[group[0]][profit[0]][0] += 1;
        }
        //标记层数
        int level = 0;
        //后续层填充
        for (int t = 0; t < group.length - 1; t++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j < sum; j++) {
                    dp[i][j][level ^ 1] = (dp[i][j][level ^ 1] + dp[i][j][level]) % 1000000007;
                    if (i + group[t + 1] <= n && j + profit[t + 1] < sum) {
                        dp[i + group[t + 1]][j + profit[t + 1]][level ^ 1] =
                                (dp[i + group[t + 1]][j + profit[t + 1]][level ^ 1] + dp[i][j][level]) % 1000000007;
                    }
                    //由于隔层递推使用的是 += 式的赋值方式，因此当前层的每个格子用完后需要手动清零
                    dp[i][j][level] = 0;
                }
            }
            level ^= 1;
        }
        //统计总数
        int ways = 0;
        for (int i = minProfit; i < sum; i++) {
            for (int j = 0; j <= n; j++) {
                ways = (ways + dp[j][i][level]) % 1000000007;
            }
        }
        return ways;
    }

    //内存超限
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int sum = 1;
        for(int i : profit){
            sum += i;
        }
        int[][][] dp = new int[n + 1][sum][group.length];
        //第一层
        dp[0][0][0] = 1;
        if(group[0] <= n){
            dp[group[0]][profit[0]][0] += 1;
        }
        //后续层填充
        for(int t = 0; t < group.length - 1; t++){
            for(int i = 0; i <= n; i++){
                for(int j = 0; j < sum; j++){
                    dp[i][j][t + 1] = (dp[i][j][t + 1] + dp[i][j][t]) % 1000000007;
                    if(i + group[t + 1] <= n && j + profit[t + 1] < sum){
                        dp[i + group[t + 1]][j + profit[t + 1]][t + 1] =
                                (dp[i + group[t + 1]][j + profit[t + 1]][t + 1] + dp[i][j][t]) % 1000000007;
                    }
                }
            }
        }
        //统计总数
        int ways = 0;
        for(int i = minProfit; i < sum; i++){
            for(int j = 0; j <= n; j++){
                ways = (ways + dp[j][i][group.length - 1]) % 1000000007;
            }
        }
        return ways;
    }

    public static int compare(int n, int minProfit, int[] group, int[] profit){
        return process(n, minProfit, group, profit, 0) % 1000000007;
    }

    public static int process(int n, int minProfit, int[] group, int[] profit, int index){
        if(index == group.length){
            return minProfit <= 0 ? 1 : 0;
        }
        int solves = process(n, minProfit, group, profit, index + 1) % 1000000007;
        if(n - group[index] >= 0){
            solves += process(n - group[index], minProfit - profit[index], group, profit, index + 1);
        }
        return solves % 1000000007;
    }

    public static int[] getNums(int len, int min){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 10) + min;
        }
        return nums;
    }
}
