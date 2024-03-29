package com.mjscode.algorithms.leetcode;

import java.util.TreeSet;

/**
 * //给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * //
 * // 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * //输出：2
 * //解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[2,2,-1]], k = 3
 * //输出：3
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 100
 * // -100 <= matrix[i][j] <= 100
 * // -10⁵ <= k <= 10⁵
 * //
 * // 进阶：如果行数远大于列数，该如何设计解决方案？
 * // Related Topics 数组 二分查找 动态规划 矩阵 有序集合
 * @author binarySigh
 * @date 2021/9/24 21:08
 */
public class LC0363_MaxSumSubmatrix {

    public static void main(String[] args) {
        // --> 2
        int[][] matrix = {
                {1, 0,1},
                {0,-2,3}
        };
        int k = 2;

        // --> 3
        /*int[][] matrix = {
            {2, 2,-1}
        };
        int k = 3;*/

        // --> case 38/39
        // --> -1
        /*int[][] matrix = {
            {2, 2,-1}
        };
        int k = 0;*/

        System.out.println(maxSumSubmatrix(matrix, k));
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[] cubeSum = new int[n];
        int[] lineSum = new int[n];
        TreeSet<Integer> bst = new TreeSet<>();
        int ans = Integer.MIN_VALUE;
        Integer cur = 0;
        for(int i = 0; i < m; i++) {
            bst.clear();
            bst.add(0);
            for(int t = 0; t < n; t++){
                if(t == 0){
                    cubeSum[t] = matrix[i][t];
                } else {
                    cubeSum[t] = matrix[i][t] + cubeSum[t - 1];
                }
                cur = bst.ceiling(cubeSum[t] - k);
                if(cur != null){
                    ans = Math.max(ans, cubeSum[t] - cur);
                }
                bst.add(cubeSum[t]);
            }
            for(int j = i + 1; j < m; j++){
                bst.clear();
                bst.add(0);
                for(int t = 0; t < n; t++){
                    if(t == 0){
                        lineSum[t] = matrix[j][t];
                    } else {
                        lineSum[t] = matrix[j][t] + lineSum[t - 1];
                    }
                    cubeSum[t] += lineSum[t];
                    cur = bst.ceiling(cubeSum[t] - k);
                    if(cur != null){
                        ans = Math.max(ans, cubeSum[t] - cur);
                    }
                    bst.add(cubeSum[t]);
                }
            }
        }
        return ans;
    }

}
