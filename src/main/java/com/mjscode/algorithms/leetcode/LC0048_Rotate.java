package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * //
 * // 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * //输出：[[7,4,1],[8,5,2],[9,6,3]]
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * //输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * //
 * // 示例 3：
 * //
 * //输入：matrix = [[1]]
 * //输出：[[1]]
 * //
 * // 示例 4：
 * //
 * //输入：matrix = [[1,2],[3,4]]
 * //输出：[[3,1],[4,2]]
 * //
 * // 提示：
 * //
 * // matrix.length == n
 * // matrix[i].length == n
 * // 1 <= n <= 20
 * // -1000 <= matrix[i][j] <= 1000
 * //
 * // Related Topics 数组
 * @author binarySigh
 * @date 2021/4/15 20:38
 */
public class LC0048_Rotate {
    public static void main(String[] args){
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        //有问题！！！--fixed
        /*int[][] matrix = {
                {5,  1, 9,11},
                {2,  4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };*/

        /*int[][] matrix = {
                {1, 2},
                {3, 4}
        };*/
        rotate(matrix);
        ArrayUtils.printMatrix(matrix);
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.2 MB,击败了97.10% 的Java用户
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        if(matrix.length <= 1){
            return;
        }
        int x0 = 0;
        int x1 = matrix.length - 1;
        while(x0 < x1){
            for(int i = x0; i < x1; i++){
                rotateCur(matrix, x0, x1, i);
            }
            x0++;
            x1--;
        }
    }

    public static void rotateCur(int[][] matrix, int x0, int x1, int pos){
        int tmp = matrix[x0][pos];
        matrix[x0][pos] = matrix[x1 - pos + x0][x0];
        matrix[x1 - pos + x0][x0] = matrix[x1][x1 - pos + x0];
        matrix[x1][x1 - pos + x0] = matrix[pos][x1];
        matrix[pos][x1] = tmp;
    }
}
