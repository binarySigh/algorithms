package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * //
 * // 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 * //
 * // 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此
 * //类推。请你计算出粉刷完所有房子最少的花费成本。
 * //
 * // 注意：
 * //
 * // 所有花费均为正整数。
 * //
 * // 示例：
 * //
 * // 输入: [[1,5,3],[2,9,4]]
 * //输出: 5
 * //解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 * //     或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 * //
 * // 进阶：
 * //您能否在 O(nk) 的时间复杂度下解决此问题？
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/3 16:26
 */
public class LC0265_MinCostII {
    public static void main(String[] args){
        /*int[][] costs = {
                {17,2,17,1},
                {16,16,5,3},
                {14,3,19,2}
        };
        System.out.println(minCostII(costs));
        System.out.println(compare(costs));*/

        System.out.println("----------测试开始------------");
        for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 5) + 1;
            int[][] costs = getMatrix(len);
            int result = minCostII(costs);
            int compare = compare(costs);
            if(compare != result){
                System.out.println("----第[" + i + "]次测试出错！-----");
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
     * 		执行耗时:5 ms,击败了26.01% 的Java用户
     * 		内存消耗:38.6 MB,击败了54.71% 的Java用户
     * @param costs
     * @return
     */
    public static int minCostII(int[][] costs) {
        int houses = costs.length;
        int colors = costs[0].length;
        if(costs == null || houses == 0){
            return 0;
        }
        int curCost = 0;
        //记录上一个房子 前2小的涂色方案，以及其对应的色号。用以降低第三层循环的复杂度
        // preMin[0][0] = 表示到上一个房子为止的涂色方案里最小的耗费；preMin[0][1] = 表示到上一个房子为止的涂色方案里次小的耗费
        // preMin[0][0] = 表示到当前房子为止的涂色方案里最小的耗费；preMin[0][1] = 表示到当前房子为止的涂色方案里次小的耗费
        int[][] preMin = new int[2][2];
        preMin[0][0] = Integer.MAX_VALUE;
        preMin[0][1] = Integer.MAX_VALUE;
        preMin[1][0] = Integer.MAX_VALUE;
        preMin[1][1] = Integer.MAX_VALUE;
        // preIndex ：对应记录上述四种情况对应的颜色下标
        int[][] preIndex = new int[2][2];
        for(int i = 0; i < colors; i++){
            curCost = costs[0][i];
            if(curCost < preMin[0][1]){
                if(curCost < preMin[0][0]){
                    //更新前二小耗费记录
                    preMin[0][1] = preMin[0][0];
                    preMin[0][0] = curCost;
                    //对应更新位置记录
                    preIndex[0][1] = preIndex[0][0];
                    preIndex[0][0] = i;
                } else {
                    preMin[0][1] = curCost;
                    preIndex[0][1] = i;
                }
            }
        }
        for(int i = 1; i < houses; i++){
            for(int j = 0; j < colors; j++){
                curCost = (j == preIndex[0][0] ? preMin[0][1] : preMin[0][0]) + costs[i][j];
                if(curCost < preMin[1][1]){
                    if(curCost < preMin[1][0]){
                        //更新前二小耗费记录
                        preMin[1][1] = preMin[1][0];
                        preMin[1][0] = curCost;
                        //对应更新位置记录
                        preIndex[1][1] = preIndex[1][0];
                        preIndex[1][0] = j;
                    } else {
                        preMin[1][1] = curCost;
                        preIndex[1][1] = j;
                    }
                }
            }
            //整理重置辅助信息。相当于把 pre = cur ; cur = null
            preMin[0][0] = preMin[1][0];
            preMin[0][1] = preMin[1][1];
            preMin[1][0] = Integer.MAX_VALUE;
            preMin[1][1] = Integer.MAX_VALUE;
            preIndex[0][0] = preIndex[1][0];
            preIndex[0][1] = preIndex[1][1];
        }
        return Math.min(preMin[0][0], preMin[0][1]);
    }

    public static int compare(int[][] costs) {
        int houses = costs.length;
        int colors = costs[0].length;
        if(costs == null || houses == 0){
            return 0;
        }
        int[][] dp = new int[houses][colors];
        for(int i = 0; i < colors; i++){
            dp[0][i] = costs[0][i];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < houses; i++){
            for(int j = 0; j < colors; j++){
                for(int t = 0; t < colors; t++){
                    if(t == j){
                        continue;
                    }
                    min = Math.min(dp[i - 1][t], min);
                }
                dp[i][j] = min + costs[i][j];
                min = Integer.MAX_VALUE;
            }
        }
        min = dp[houses - 1][0];
        for(int i = 1; i < colors; i++){
            min = Math.min(min, dp[houses - 1][i]);
        }
        return min;
    }

    public static int[][] getMatrix(int len){
        int[][] matrix = new int[len][5];
        for(int i = 0 ; i < len; i++){
            for(int j = 0; j < 5; j++){
                matrix[i][j] = (int)(Math.random() * 20) + 1;
            }
        }
        return matrix;
    }
}
