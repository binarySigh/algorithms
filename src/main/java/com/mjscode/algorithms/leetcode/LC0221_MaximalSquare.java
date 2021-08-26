package com.mjscode.algorithms.leetcode;

/**
 * //在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
 * //,["1","0","0","1","0"]]
 * //输出：4
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [["0","1"],["1","0"]]
 * //输出：1
 * //
 * // 示例 3：
 * //
 * //输入：matrix = [["0"]]
 * //输出：0
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 300
 * // matrix[i][j] 为 '0' 或 '1'
 * //
 * // Related Topics 数组 动态规划 矩阵
 * @author binarySigh
 * @date 2021/8/25 21:02
 */
public class LC0221_MaximalSquare {

    public static void main(String[] args) {
        // --> 4
        /*char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };*/

        // --> 9
        /*char[][] matrix = {
                {'0','0','0','1'},
                {'1','1','0','1'},
                {'1','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'}
        };*/

        // --> 1
        /*char[][] matrix = {
                {'1'}
        };*/

        // --> 1
        /*char[][] matrix = {
                {'0','1'}
        };*/

        // --> 4
        char[][] matrix = {
                {'1','0','1','0','0','1','1','1','0'},
                {'1','1','1','0','0','0','0','0','1'},
                {'0','0','1','1','0','0','0','1','1'},
                {'0','1','1','0','0','1','0','0','1'},
                {'1','1','0','1','1','0','0','1','0'},
                {'0','1','1','1','1','1','1','0','1'},
                {'1','0','1','1','1','0','0','1','0'},
                {'1','1','1','0','1','0','0','0','1'},
                {'0','1','1','1','1','0','0','1','0'},
                {'1','0','0','1','1','1','0','0','0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    public static int maximalSquare(char[][] matrix){
        //dp[i][j] 代表以(i,j)为右下角点的最大正方形边长是多少
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        //第一列
        for(int i = 0; i < matrix.length; i++){
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        //第一行
        for(int i = 0; i < matrix[0].length; i++){
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][i]);
        }
        //普遍位置
        // 对于一个普遍位置i,j而言，他能组成的最大正方形边长只与他的 左，上，左上 三个位置有关
        //  这个关系就是这三个数中的最小值 + 1
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max * max;
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了99.84% 的Java用户
     * 		内存消耗:41.9 MB,击败了5.09% 的Java用户
     * @param matrix
     * @return
     */
    public static int maximalSquare1(char[][] matrix){
        int[] h = new int[matrix[0].length];
        int[] dp = new int[matrix[0].length];
        int max = 0;
        int preLen = 0;
        int pre = 0;
        for(int j = 0; j < matrix.length; j++){
            //压缩数组
            for(int i = 0; i < matrix[j].length; i++){
                h[i] += matrix[j][i] == '0' ? -h[i] : 1;
            }
            //dp
            dp[0] = h[0] > 0 ? 1 : 0;
            max = Math.max(max, dp[0]);
            for(int i = 1; i < dp.length; i++){
                if(h[i] == 0){
                    dp[i] = 0;
                    continue;
                }
                preLen = (int)Math.sqrt(dp[i - 1]);
                if(preLen == 0){
                    dp[i] = h[i] > 0 ? 1 : 0;
                } else if(preLen < h[i]){
                    pre = i;
                    while(pre >= 0 && h[pre] >= preLen + 1){
                        pre--;
                    }
                    dp[i] = preLen + 1 <= i - pre ? (int)Math.pow(preLen + 1, 2) : (int)Math.pow(i - pre, 2);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
