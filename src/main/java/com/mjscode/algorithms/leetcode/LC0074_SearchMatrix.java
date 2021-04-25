package com.mjscode.algorithms.leetcode;

/**
 * //编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * //
 * // 每行中的整数从左到右按升序排列。
 * // 每行的第一个整数大于前一行的最后一个整数。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * //输出：false
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 100
 * // -104 <= matrix[i][j], target <= 104
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/4/25 21:44
 */
public class LC0074_SearchMatrix {
    public static void main(String[] args){
        int[][] matrix = {
                { 1, 3, 5, 7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 20;

        /*int[][] matrix = {{2},{21},{23},{43}};
        int target = 43;*/

        /*int[][] matrix = {{22}};
        int target = 21;*/
        System.out.println(searchMatrix(matrix, target));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:37.5 MB,击败了96.13% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length;
        int row = matrix.length;
        if(col == 1 && row == 1){
            return matrix[0][0] == target;
        }
        int upper = 0, lower = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        int rowMid = 0;
        int colMid = 0;
        while(upper < lower){
            colMid = (upper + lower) / 2;
            if(matrix[colMid][0] == target){
                return true;
            }
            if(matrix[colMid][0] <= target && matrix[colMid][right] >= target){
                upper = colMid;
                lower = colMid;
                break;
            }
            if(matrix[colMid][0] > target){
                lower = colMid - 1;
            } else if(matrix[colMid][0] < target){
                upper = colMid + 1;
            }
        }
        if(upper != lower){
            return false;
        }
        while(left <= right){
            rowMid = (left + right) / 2;
            if(matrix[upper][rowMid] == target){
                return true;
            }
            if(matrix[upper][rowMid] < target){
                left = rowMid + 1;
            } else{
                right = rowMid - 1;
            }
        }
        return false;
    }
}
