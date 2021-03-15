package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * //输出：[1,2,3,6,9,8,7,4,5]
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * //输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 10
 * // -100 <= matrix[i][j] <= 100
 * //
 * // Related Topics 数组
 * @author binarySigh
 * @date 2021/3/15 23:00
 */
public class LC0054_SpiralOrder {

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        //int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        //int[][] matrix = new int[][]{{7},{9},{6}};
        int[][] matrix = new int[][]{{7,9,6}};
        List<Integer> list = spiralOrder(matrix);
        ArrayUtils.showArray((ArrayList)list);
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.2 MB,击败了99.60% 的Java用户
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int x1 = 0;
        int y1 = 0;
        int x2 = matrix.length - 1;
        int y2 = matrix[0].length - 1;
        int i = x1;
        int j = y1;
        while(x1 <= x2 && y1 <= y2){
            //特殊情况：最后只剩一行或最后一列的时候
            if(x1 == x2){
                for(; j <= y2; j++){
                    list.add(matrix[i][j]);
                }
                break;
            }
            if(y1 == y2){
                for(; i <= x2; i++){
                    list.add(matrix[i][j]);
                }
                break;
            }
            //正常情况
            for(; j < y2; j++){
                list.add(matrix[i][j]);
            }
            for(; i < x2; i++){
                list.add(matrix[i][j]);
            }
            for(; j > y1; j--){
                list.add(matrix[i][j]);
            }
            for(; i > x1; i--){
                list.add(matrix[i][j]);
            }
            i = ++x1; j = ++y1;
            x2--; y2--;
        }
        return list;
    }
}
