package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;

/**
 * //在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要
 * //被重新涂色。
 * //
 * // 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区 [{1}, {2,2}
 * //, {3,3}, {2}, {1,1}] 。）
 * //
 * // 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 * //
 * // houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * // cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * //
 * // 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
 * //
 * // 示例 1：
 * //
 * // 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n
 * // = 2, target = 3
 * //输出：9
 * //解释：房子涂色方案为 [1,2,2,1,1]
 * //此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
 * //涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
 * //
 * // 示例 2：
 * //
 * // 输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n
 * // = 2, target = 3
 * //输出：11
 * //解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
 * //此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
 * //给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
 * //
 * // 示例 3：
 * //
 * // 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5,
 * //n = 2, target = 5
 * //输出：5
 * //
 * // 示例 4：
 * //
 * // 输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3
 * //, target = 3
 * //输出：-1
 * //解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
 * //
 * // 提示：
 * //
 * // m == houses.length == cost.length
 * // n == cost[i].length
 * // 1 <= m <= 100
 * // 1 <= n <= 20
 * // 1 <= target <= m
 * // 0 <= houses[i] <= n
 * // 1 <= cost[i][j] <= 10^4
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/4 0:25
 */
public class LC1473_MinCost {

    public static void main(String[] args){
        // --->  -1
        /*int[][] cost = {
                {1,1,1},
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };
        int[] houses = {3,1,2,3};
        int target = 3;
        int m = 4, n = 3;*/

        // ---> 9
        /*int[][] cost = {
                {1,10},
                {10,1},
                {10,1},
                {1,10},
                {5,1}
        };
        int[] houses = {0,0,0,0,0};
        int target = 3;
        int m = 5, n = 2;*/

        // ---> 11
        /*int[][] cost = {
                {1,10},
                {10,1},
                {10,1},
                {1,10},
                {5,1}
        };
        int[] houses = {0,2,1,2,0};
        int target = 3;
        int m = 5, n = 2;*/

        // ---> 5
        /*int[][] cost = {
                {1,10},
                {10,1},
                {1,10},
                {10,1},
                {1,10}
        };
        int[] houses = {0,0,0,0,0};
        int target = 5;
        int m = 5, n = 2;
        System.out.println(minCost(houses, cost, m, n, target));
        System.out.println(solution1(houses, cost, m, n, target));*/

        /*int[][] cost = {
                {3,2,6},
                {2,6,1},
                {4,3,6},
                {7,1,5},
                {4,5,1}
        };
        int[] houses = {3,0,0,2,2};
        int target = 4;
        int m = 5, n = 3;
        System.out.println(minCost(houses, cost, m, n, target));
        System.out.println(solution1(houses, cost, m, n, target));*/

        System.out.println("-----------------测试开始-----------------");
        for(int i = 0; i < 10_0000; i++){
            int m = 10, n = 7;
            int[][] cost = getCost(m, n);
            int[] houses = getHouses(m, n);
            int target = (int)(Math.random() * m) + 1;
            int result = minCost(houses, cost, m, n, target);
            int answer = solution(houses, cost, m, n, target);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错---------");
                ArrayUtils.showArray(houses);
                ArrayUtils.printMatrix(cost);
                System.out.println("m = " + m + "; n = " + n);
                System.out.println("target = " + target);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + answer);
                break;
            }
        }
        System.out.println("-----------------测试结束-----------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:22 ms,击败了67.92% 的Java用户
     * 		内存消耗:39.3 MB,击败了24.03% 的Java用户
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n][target];
        //填充第一层
        for(int i = 0; i < n; i++){
            if(houses[0] > 0){
                int curColor = houses[0] - 1;
                dp[0][i][0] = i == curColor ? 0 : -1;
            } else {
                dp[0][i][0] = cost[0][i];
            }
        }
        //填充后续层
        for(int i = 1; i < m; i++){
            for(int j = 0; j <= i && j < target; j++){
                for(int t = 0; t < n; t++){
                    //当前房子颜色固定
                    if(houses[i] > 0){
                        if(houses[i] - 1 != t){
                            dp[i][t][j] = -1;
                            continue;
                        }
                        if(j == 0){
                            dp[i][t][j] = houses[i] - 1 == t ? dp[i - 1][t][j] : -1;
                            continue;
                        }
                        int curMin = -1;
                        //找到以 i-1 号房子为 j-1 街区最后一个房子的情况下的最小花费
                        //这里的最小需要有意义，因此剔除 -1 的情况，并且颜色不与当前房子颜色相同，因为街区数要增加
                        if(j - 1 < target){
                            for(int index = 0; index < n; index++){
                                curMin = dp[i - 1][index][j - 1] > -1 && index != houses[i] - 1 ?
                                        (curMin == -1 ? dp[i - 1][index][j - 1] : Math.min(curMin, dp[i - 1][index][j - 1]))
                                        : curMin;
                            }
                        }
                        //找到以 i-1 号房子为 j 街区最后一个房子的情况下的花费
                        //需要有意义，因此剔除 -1 的情况，并且颜色与当前房子颜色相同，因为街区数不增加
                        // 因为i个房子最多组成i个街区，因此对于第i房而言，
                        // 如果j == i那么说明前一个房子不会有J个街区，因此不能走下面的if逻辑
                        // 而只能用上面的if逻辑中获取的最小值
                        if(j < i){
                            curMin = dp[i - 1][t][j] > -1 ?
                                    (curMin == -1 ? dp[i - 1][t][j] : Math.min(curMin, dp[i - 1][t][j]))
                                    : curMin;
                        }
                        dp[i][t][j] = curMin;
                    } else {
                        if(j == 0){
                            dp[i][t][j] = dp[i - 1][t][j] == -1 ? -1 : dp[i - 1][t][j] + cost[i][t];
                            continue;
                        }
                        int curMin = -1;
                        //找到以 i-1 号房子为 j-1 街区最后一个房子的情况下的最小花费
                        //这里的最小需要有意义，因此剔除 -1 的情况，并且颜色不与当前房子颜色相同，因为街区数要增加
                        //if(j - 1 < target){
                            for(int index = 0; index < n; index++){
                                curMin = dp[i - 1][index][j - 1] > -1 && index != t ?
                                        (curMin == -1 ? dp[i - 1][index][j - 1] : Math.min(curMin, dp[i - 1][index][j - 1]))
                                        : curMin;
                            }
                        //}
                        //找到以 i-1 号房子为 j 街区最后一个房子的情况下的花费
                        //需要有意义，因此剔除 -1 的情况，并且颜色与当前房子颜色相同，因为街区数不增加
                        // 因为i个房子最多组成i个街区，因此对于第i房而言，
                        // 如果j == i那么说明前一个房子不会有J个街区，因此不能走下面的if逻辑
                        // 而只能用上面的if逻辑中获取的最小值
                        if(j < i){
                            curMin = dp[i - 1][t][j] > -1 ?
                                    (curMin == -1 ? dp[i - 1][t][j] : Math.min(curMin, dp[i - 1][t][j]))
                                    : curMin;
                        }
                        dp[i][t][j] = curMin == -1 ? -1 : curMin + cost[i][t];
                    }
                }
            }
        }
        //收集答案
        int min = -1;
        for(int t = 0; t < n; t++){
            min = dp[m - 1][t][target - 1] > -1 ?
                    (min == -1 ? dp[m - 1][t][target - 1] : Math.min(min, dp[m - 1][t][target - 1]))
                    : min;
        }
        return min;
    }

    /**
     * 暴力方案
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public static int solution(int[] houses, int[][] cost, int m, int n, int target){
        if(m == 0){
            if(target > 1){
                return -1;
            } else {
                int min = Integer.MAX_VALUE;
                for(int i = 0; i < n; i++){
                    min = Math.min(min, cost[0][i]);
                }
                return houses[0] > 0 ? 0 : min;
            }
        }
        int res = process(houses, cost, 0, 0, 0,0, target);
        return res;
    }

    public static int process(int[] houses, int[][] cost, int preColor, int index, int used, int blocks, int target){
        if(index == houses.length || blocks > target){
            return blocks == target ? used : -1;
        }
        int min = -1;
        int curCost = 0;
        for(int i = 0; i < cost[0].length; i++){
            //当前颜色已固定
            if(houses[index] > 0){
                boolean isSameColor = houses[index] == preColor;
                if(isSameColor){
                    min = process(houses, cost, preColor, index + 1, used, blocks, target);
                } else {
                    min = process(houses, cost, houses[index], index + 1, used, blocks + 1, target);
                }
                break;
            }
            //当前颜色可选
            int curColor = i + 1;
            if(curColor == preColor){
                curCost = process(houses, cost, preColor, index + 1, used + cost[index][i], blocks, target);
            } else {
                curCost = process(houses, cost, curColor, index + 1, used + cost[index][i], blocks + 1, target);
            }
            if(curCost > 0){
                min = min >= 0 ? Math.min(curCost, min) : curCost;
            }
        }
        return min;
    }

    public static int[] getHouses(int m, int n){
        int[] houses = new int[m];
        for(int i = 0; i < m; i++){
            houses[i] = (int)(Math.random() * (n + 1));
        }
        return houses;
    }

    public static int[][] getCost(int m, int n){
        int[][] cost = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                cost[i][j] = (int)(Math.random() * 8) + 1;
            }
        }
        return cost;
    }
}
