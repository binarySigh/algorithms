package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * //
 * // 示例 1：
 * //
 * //输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
 * //,["1","0","0","1","0"]]
 * //输出：6
 * //解释：最大矩形如上图所示。
 * //
 * // 示例 2：
 * //
 * //输入：matrix = []
 * //输出：0
 * //
 * // 示例 3：
 * //
 * //输入：matrix = [["0"]]
 * //输出：0
 * //
 * // 示例 4：
 * //
 * //输入：matrix = [["1"]]
 * //输出：1
 * //
 * // 示例 5：
 * //
 * //输入：matrix = [["0","0"]]
 * //输出：0
 * //
 * // 提示：
 * //
 * // rows == matrix.length
 * // cols == matrix[0].length
 * // 0 <= row, cols <= 200
 * // matrix[i][j] 为 '0' 或 '1'
 * //
 * // Related Topics 栈 数组 哈希表 动态规划
 * @author binarySigh
 * @date 2021/5/3 22:17
 */
public class LC0085_MaximalRectangle {
    public static void main(String[] args){
        char[][] matrix = {
                {'1','0','1','1','0'},
                {'0','1','1','0','1'},
                {'1','1','0','1','1'},
                {'1','0','1','1','0'}
        };
        /*char[][] matrix = {
                {'0','0','1'},
                {'1','1','1'}
        };*/
        System.out.println(maximalRectangle(matrix));
    }

    /**
     * 思路：矩阵压缩 + 单调栈
     * 解答成功:
     * 		执行耗时:19 ms,击败了17.87% 的Java用户
     * 		内存消耗:41.9 MB,击败了25.23% 的Java用户
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        int[] pre = new int[cols];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                pre[j] = matrix[i][j] == '1' ? pre[j] + 1 : 0;
                if(stack.isEmpty() || pre[j] > pre[stack.peek()]){
                    stack.push(j);
                } else if(pre[j] <= pre[stack.peek()]){
                    while(!stack.isEmpty() && pre[j] <= pre[stack.peek()]){
                        int height = pre[stack.pop()];
                        int width = j - (stack.isEmpty() ? -1 : stack.peek()) - 1;
                        max = Math.max(max, height * width);
                    }
                    stack.push(j);
                }
            }
            //处理本层过程中，单调栈中剩余的矩形
            while(!stack.isEmpty()){
                int height = pre[stack.pop()];
                int width = cols - (stack.isEmpty() ? -1 : stack.peek()) - 1;
                max = Math.max(max, height * width);
            }
        }
        return max;
    }
}
