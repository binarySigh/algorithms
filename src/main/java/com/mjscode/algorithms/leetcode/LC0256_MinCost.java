package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * //
 * // 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 * //
 * // 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷
 * //完所有房子最少的花费成本。
 * //
 * // 注意：
 * //
 * // 所有花费均为正整数。
 * //
 * // 示例：
 * //
 * // 输入: [[17,2,17],[16,16,5],[14,3,19]]
 * //输出: 10
 * //解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 * //     最少花费: 2 + 5 + 3 = 10。
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/2 12:23
 */
public class LC0256_MinCost {

    public static void main(String[] args){
        /*int[][] costs = {
                {17,2,17},
                {16,16,5},
                {14,3,19}
        };
        System.out.println(solution(costs));
        System.out.println(minCost(costs));*/
        System.out.println("----------测试开始------------");
        for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 5) + 1;
            int[][] costs = getMatrix(len);
            int result = minCost(costs);
            int compare = solution(costs);
            if(compare != result){
                System.out.println("----测试出错！-----");
                ArrayUtils.printMatrix(costs);
                System.out.println("我的答案：" + result);
                System.out.println("标准答案：" + compare);
                break;
            }
        }
        System.out.println("----------测试结束------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了24.42% 的Java用户
     * 		内存消耗:38.1 MB,击败了13.49% 的Java用户
     * @param costs
     * @return
     */
    public static int minCost(int[][] costs) {
        int len = costs.length;
        if(costs == null || len == 0){
            return 0;
        }
        int[][] dp = new int[len][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for(int i = 1; i < len; i++){
            for(int j = 0; j < 3; j++){
                int min = Integer.MAX_VALUE;
                for(int t = 0; t < 3; t++){
                    if(t == j){
                        continue;
                    }
                    min = Math.min(dp[i - 1][t], min);
                }
                dp[i][j] = min + costs[i][j];
            }
        }
        return Math.min(Math.min(dp[len - 1][0], dp[len - 1][1]), dp[len - 1][2]);
    }

    public static int solution(int[][] costs){
        if(costs == null || costs.length == 0){
            return 0;
        }
        int minCost = process(costs, -1, 0);
        return minCost;
    }

    public static int process(int[][] costs, int preColor, int home){
        if(home == costs.length){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            if(i == preColor){
                continue;
            }
            int curCost = costs[home][i];
            min = Math.min(min, process(costs, i, home + 1) + curCost);
        }
        return min;
    }

    public static int[][] getMatrix(int len){
        int[][] matrix = new int[len][3];
        for(int i = 0 ; i < len; i++){
            for(int j = 0; j < 3; j++){
                matrix[i][j] = (int)(Math.random() * 20) + 1;
            }
        }
        return matrix;
    }
}
