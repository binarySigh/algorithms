package com.mjscode.algorithms.leetcode;

/**
 * //给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * //
 * // 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * //
 * // 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
 * //也不同。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * //输出：4
 * //解释：四个只含 0 的 1x1 子矩阵。
 * //
 * // 示例 2：
 * //
 * //输入：matrix = [[1,-1],[-1,1]], target = 0
 * //输出：5
 * //解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * //
 * // 示例 3：
 * //
 * //输入：matrix = [[904]], target = 0
 * //输出：0
 * //
 * // 提示：
 * //
 * // 1 <= matrix.length <= 100
 * // 1 <= matrix[0].length <= 100
 * // -1000 <= matrix[i] <= 1000
 * // -10^8 <= target <= 10^8
 * //
 * // Related Topics 数组 动态规划 Sliding Window
 * @author binarySigh
 * @date 2021/5/29 11:23
 */
public class LC1074_NumSubmatrixSumTarget {
    public static void main(String[] args){
        /*int[][] matrix = {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
        int target = 1;*/

        int[][] matrix = {
                {1,  3,  -1,0},
                {2,  4,   5,1},
                {0, -2,-400,0},
                {1,200,  -1,0}
        };
        int target = 7;
        System.out.println(compare(matrix, target));
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        if(matrix.length == 1 && matrix[0].length == 1){
            return matrix[0][0] == target ? 1 : 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //sub[i][j] : (0,0) -> (i,j)围成的子矩阵的所有元素和
        int[][] subSum = new int[rowLen][colLen];
        subSum[0][0] = matrix[0][0];
        //第一行
        for(int i = 1; i < colLen; i++){
            subSum[0][i] = subSum[0][i - 1] + matrix[0][i];
        }
        //第一列
        for(int i = 1; i < rowLen; i++){
            subSum[i][0] = subSum[i - 1][0] + matrix[i][0];
        }
        //普遍位置
        for(int i = 1; i < rowLen; i++){
            for(int j = 1; j < colLen; j++){
                subSum[i][j] = subSum[i - 1][j] + subSum[i][j - 1] - subSum[i - 1][j - 1] + matrix[i][j];
            }
        }
        return 0;
    }

    /**
     * 解答成功:
     * 		执行耗时:413 ms,击败了13.58% 的Java用户
     * 		内存消耗:39.8 MB,击败了31.79% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public static int compare(int[][] matrix, int target){
        if(matrix.length == 1 && matrix[0].length == 1){
            return matrix[0][0] == target ? 1 : 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //sub[i][j] : (0,0) -> (i,j)围成的子矩阵的所有元素和
        int[][] subSum = new int[rowLen][colLen];
        subSum[0][0] = matrix[0][0];
        //第一行
        for(int i = 1; i < colLen; i++){
            subSum[0][i] = subSum[0][i - 1] + matrix[0][i];
        }
        //第一列
        for(int i = 1; i < rowLen; i++){
            subSum[i][0] = subSum[i - 1][0] + matrix[i][0];
        }
        //普遍位置
        for(int i = 1; i < rowLen; i++){
            for(int j = 1; j < colLen; j++){
                subSum[i][j] = subSum[i - 1][j] + subSum[i][j - 1] - subSum[i - 1][j - 1] + matrix[i][j];
            }
        }
        //主流程
        int count = 0;
        //外层双循环是枚举任意一个右下坐标(i,j)
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                //遍历枚举左上坐标(x,y)
                for(int x = 0; x <= i; x++){
                    for(int y = 0; y <= j; y++){
                        if(x == 0 && y == 0){
                            count += subSum[i][j] == target ? 1 : 0;
                            continue;
                        }
                        if(x == i && y == j){
                            count += matrix[x][y] == target ? 1 : 0;
                            continue;
                        }
                        if(x == 0){
                            int cur = subSum[i][j] - subSum[i][y - 1];
                            count += cur == target ? 1 : 0;
                        } else if(y == 0){
                            int cur = subSum[i][j] - subSum[x - 1][j];
                            count += cur == target ? 1 : 0;
                        } else {
                            int cur = subSum[i][j] - subSum[i][y - 1] - subSum[x - 1][j] + subSum[x - 1][y - 1];
                            count += cur == target ? 1 : 0;
                        }
                    }
                }
            }
        }
        return count;
    }
}
