package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;

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

        int[][] matrix1 = {
                {1,  3,  -1,0},
                {2,  4,   5,1},
                {0, -2,-400,0},
                {1,200,  -1,0}
        };
        int target1 = 7;
        System.out.println(compare(matrix1, target1));

        System.out.println("-------------------测试开始---------------");
        for(int i = 0; i < 20_0000; i++){
            int len = (int)(Math.random() * 4) + 1;
            int width = (int)(Math.random() * 4) + 1;
            int[][] matrix = getMatrix(len, width);
            int target = (int)(Math.random() * 10) - (int)(Math.random() * 10);
            int result = numSubmatrixSumTarget(matrix, target);
            int compare = compare(matrix, target);
            if(result != compare){
                System.out.println("------第[" + i + "]次测试出错---------");
                ArrayUtils.printMatrix(matrix);
                System.out.println("target = " + target);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + compare);
                break;
            }
        }
        System.out.println("-------------------测试开始---------------");
    }

    /**
     * 思路：枚举子矩阵大致有两种方案：
     *      1. 枚举左上右下两个点卡子矩阵
     *      2. 枚举上线左右四条边卡子矩阵
     * 优化方案使用的就是卡边方案，因为本题枚举两点复杂度无法降低；
     * 而枚举边可以通过卡三边，另一条边用哈希表的方式能将复杂度降两阶
     *
     * 解答成功:
     * 		执行耗时:159 ms,击败了65.25% 的Java用户
     * 		内存消耗:38.6 MB,击败了99.78% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        if(matrix.length == 1 && matrix[0].length == 1){
            return matrix[0][0] == target ? 1 : 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int count = 0;
        //sub[i][j] : (0,0) -> (i,j)围成的子矩阵的所有元素和
        for(int i = 0; i < rowLen; i++){
            int[] sum = new int[colLen];
            for(int j = i; j < rowLen; j++){
                //枚举上下边界
                for(int t = 0; t < colLen; t++){
                    sum[t] += matrix[j][t];
                }
                count += getSubCount(sum, target);
            }
        }
        return count;
    }

    public static int getSubCount(int[] sum, int target){
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //put(0,1),是为了防止错过列和为target的情况。
        // 下面的算法中正常情况是无法抓住宽度为1的子矩阵的，所以人为先加一个0
        map.put(0,1);
        int count = 0;
        for(int i = 0; i < sum.length; i++){
            preSum += sum[i];
            count += map.getOrDefault(preSum - target, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
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

    public static int[][] getMatrix(int len, int width){
        int[][] matrix = new int[len][width];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < width; j++){
                matrix[i][j] = (int)(Math.random() * 10) - (int)(Math.random() * 10);
            }
        }
        return matrix;
    }
}
