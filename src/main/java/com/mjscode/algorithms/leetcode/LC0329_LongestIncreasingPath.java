package com.mjscode.algorithms.leetcode;

/**
 * //给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * //
 * // 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * //输出：4
 * //解释：最长递增路径为 [1, 2, 6, 9]。
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * //输出：4
 * //解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * //
 * // 示例 3：
 * //
 * //输入：matrix = [[1]]
 * //输出：1
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 200
 * // 0 <= matrix[i][j] <= 231 - 1
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划
 * @author binarySigh
 * @date 2021/9/8 19:12
 */
public class LC0329_LongestIncreasingPath {

    public static void main(String[] args) {
        // --> 4
  /*int[][] matrix = {
    {9,9,4},
    {6,6,8},
    {2,1,1}
  };*/

        // --> 4
  /*int[][] matrix = {
    {3,4,5},
    {3,2,6},
    {2,2,1}
  };*/

        int[][] matrix = {
                {1}
        };

        System.out.println(longestIncreasingPath(matrix));
    }

    /**
     * 解答成功:
     * 		执行耗时:44 ms,击败了6.8% 的Java用户
     * 		内存消耗:39 MB,击败了15.5% 的Java用户
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        int[][] rec = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rec[i][j] == 0){
                    rec[i][j] = 1;
                    infect(matrix, i - 1, j, matrix[i][j], 1, rec);
                    infect(matrix, i + 1, j, matrix[i][j], 1, rec);
                    infect(matrix, i, j - 1, matrix[i][j], 1, rec);
                    infect(matrix, i, j + 1, matrix[i][j], 1, rec);
                }
            }
        }
        //收集答案
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans = Math.max(rec[i][j], ans);
            }
        }
        return ans;
    }

    public static void infect(int[][] matrix, int i, int j, int pre, int len, int[][] rec){
        if(i < 0 || i >= matrix.length || //行越界
                j < 0 || j >= matrix[0].length || //列越界
                matrix[i][j] <= pre ||   //不严格递增
                len + 1 <= rec[i][j]){  //当前格子已经感染过，且之前感染的路径不比现在这个短
            return;
        }
        rec[i][j] = len + 1;
        infect(matrix, i - 1, j, matrix[i][j], rec[i][j], rec);
        infect(matrix, i + 1, j, matrix[i][j], rec[i][j], rec);
        infect(matrix, i, j - 1, matrix[i][j], rec[i][j], rec);
        infect(matrix, i, j + 1, matrix[i][j], rec[i][j], rec);
    }

}
