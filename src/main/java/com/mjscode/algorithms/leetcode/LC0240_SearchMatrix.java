package com.mjscode.algorithms.leetcode;

/**
 * //编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * //
 * // 每行的元素从左到右升序排列。
 * // 每列的元素从上到下升序排列。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
 * //,23,26,30]], target = 5
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
 * //,23,26,30]], target = 20
 * //输出：false
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= n, m <= 300
 * // -10⁹ <= matrix[i][j] <= 10⁹
 * // 每行的所有元素从左到右升序排列
 * // 每列的所有元素从上到下升序排列
 * // -10⁹ <= target <= 10⁹
 * //
 * // Related Topics 数组 二分查找 分治 矩阵
 * @author binarySigh
 * @date 2021/10/25 19:54
 */
public class LC0240_SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {666,670,671,673,677,682,685,689,693}
        };
        int target1 = 671;
        System.out.println(searchMatrix(matrix1, target1));

        System.out.println("-----BEGIN-----");
        for(int i = 0; i < 10_0000; i++) {
            int m = (int)(Math.random() * 300) + 1;
            int n = (int)(Math.random() * 300) + 1;
            int[][] matrix = getMatrix(m, n);
            int target = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
            boolean ans = searchMatrix(matrix, target);
            boolean com = compare(matrix, target);
            if(ans ^ com) {
                System.out.println("-----Oops-----");
                System.out.println("matrix: ");
                System.out.println(showMatrix(matrix));
                System.out.println("target : " + target);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
                break;
            }
        }
        System.out.println("-----END-----");
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int N = matrix[0].length;
        //找到开始行和结束行
        int BEGIN = getLine(matrix, N - 1, target - 1) + 1, END = getLine(matrix, 0, target);
        // 在开始行结束行之间逐行二分查找
        for(int i = BEGIN; i <= END; i++) {
            int l = 0, r = N - 1;
            while(l <= r) {
                int mid = l + ((r - l) >> 1);
                if(matrix[i][mid] == target) {
                    return true;
                } else if(matrix[i][mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    public static int getLine(int[][] matrix, int p, int target) {
        int l = 0, r = matrix.length - 1;
        int ans = -1;
        while(l <= r) {
            int mid = l + ((r - l) >> 1);
            if(matrix[mid][p] <= target) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static boolean compare(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    public static int[][] getMatrix(int m, int n) {
        int[][] matrix = new int[m][n];
        matrix[0][0] = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
        for(int i = 1; i < m; i++) {
            matrix[i][0] = matrix[i - 1][0] + (int)(Math.random() * 5) + 1;
        }
        for(int i = 1; i < n; i++) {
            matrix[0][i] = matrix[0][i - 1] + (int)(Math.random() * 5) + 1;
        }
        for(int i =1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]) + (int)(Math.random() * 5) + 1;
            }
        }
        return matrix;
    }

    public static String showMatrix(int[][] matrix){
        StringBuilder sb = new StringBuilder();
        //sb.append('[');
        for(int i = 0; i < matrix.length; i++){
            sb.append('[');
            for(int j = 0; j < matrix[0].length; j++){
                sb.append(matrix[i][j]);
                if(j < matrix[0].length - 1){
                    sb.append(',');
                } else {
                    sb.append(']').append("\r\n");
                }
            }
            if(i < matrix.length - 1){
                sb.append(',');
            } else {
                //sb.append(']');
            }
        }
        return sb.toString();
    }

}
