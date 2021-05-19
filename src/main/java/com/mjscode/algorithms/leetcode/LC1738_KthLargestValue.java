package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;

/**
 * //给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * //
 * // 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
 * //标从 0 开始计数）执行异或运算得到。
 * //
 * // 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * //
 * // 示例 1：
 * //
 * // 输入：matrix = [[5,2],[1,6]], k = 1
 * //输出：7
 * //解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * //
 * // 示例 2：
 * //
 * // 输入：matrix = [[5,2],[1,6]], k = 2
 * //输出：5
 * //解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * //
 * // 示例 3：
 * //
 * // 输入：matrix = [[5,2],[1,6]], k = 3
 * //输出：4
 * //解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * //
 * // 示例 4：
 * //
 * // 输入：matrix = [[5,2],[1,6]], k = 4
 * //输出：0
 * //解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 1000
 * // 0 <= matrix[i][j] <= 106
 * // 1 <= k <= m * n
 * //
 * // Related Topics 数组
 * @author binarySigh
 * @date 2021/5/19 20:47
 */
public class LC1738_KthLargestValue {

    public static void main(String[] args){
        /*int[][] matrix = {
                {5,2},
                {1,6}
        };
        int k = 2;
        System.out.println("我的答案：" + kthLargestValue(matrix, k));
        System.out.println("正确答案：" + compare(matrix, k));*/

        System.out.println("-----------------测试开始-----------------");
        for(int i = 0; i < 10_0000; i++){
            int m = 10, n = 10;
            int[][] matrix = getMatrix(m, n);
            int k = (int)(Math.random() * m * n) + 1;
            int result = kthLargestValue(matrix, k);
            int answer = compare(matrix, k);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错---------");
                ArrayUtils.printMatrix(matrix);
                System.out.println("k = " + k);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + answer);
                break;
            }
        }
        System.out.println("-----------------测试结束-----------------");
    }

    /**
     * 本题空间消耗可进一步降低，xor两行数组可以转化为 一行数组 +一个int变量。
     * 原因：每个坐标(i,j)求解最多只需要知道(i-1,j),(i,j-1),(i-1,j-1)这三个坐标的异或值，
     *    之所以用两行数组就是防止滚动过程中丢失 (i-1,j-1)，所以针对地，可以用一个额外变量记住每一步的(i-1,j-1)，来替代掉第二行数组的作用
     *  但本题解依然采用两行数组，没做优化的原因在于，这样每一步都要修改这个额外变量的值，这个指令会发生 m*n次，极大增加求解算法的常数时间
     *     而两行数组只需要在行尾转换行号即可，这个指令开销仅有 m 次，一定程度上节约了算法的常数时间
     *
     *  另外本题 第二阶段求第K大时，最好是利用 bfprt 算法求解，但这里懒得去弄了
     * 解答成功:
     * 		执行耗时:61 ms,击败了88.48% 的Java用户
     * 		内存消耗:131.2 MB,击败了98.18% 的Java用户
     * @param matrix
     * @param k
     * @return
     */
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        if(m == 1 && n == 1){
            return matrix[0][0];
        }
        //坐标 m[i][j]表示：从左上角点(0,0) -> 右下角点(i,j)围成的子矩阵中所有元素异或值
        // 由于这个m[i][j] 只依赖 左，上，左上三个值和自身matrix[i][j]即可，因此只需xor滚动求解即可，无需申请m*n规模的矩阵
        int[][] xor = new int[2][n];
        //每个坐标求解的结果收集到 allXor中，以便最后求解其中第k大
        int[] allXor = new int[m*n];
        int allPos = 0;
        //标记当前正在计算填充的行号，因为xor只有两行，所以需要记录区分当前填充计算的行和作为参考的行
        int curRow = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    xor[curRow][j] = matrix[i][j];
                } else if(i == 0){
                    xor[curRow][j] = xor[curRow][j - 1] ^ matrix[i][j];
                } else if(j == 0){
                    xor[curRow][j] = xor[1 - curRow][j] ^ matrix[i][j];
                } else {
                    xor[curRow][j] = xor[curRow][j - 1] ^ xor[1 - curRow][j] ^ xor[1 - curRow][j - 1] ^ matrix[i][j];
                }
                allXor[allPos++] = xor[curRow][j];
            }
            curRow = 1 - curRow;
        }
        //查找 allXor 中第K大
        int L = 0, R = allXor.length - 1;
        int begin = L, end = R;
        //如果 allXor 有序，要求的第k大元素所在的下标位置
        int desPos = allXor.length - k;
        while(L < R){
            begin = L; end = R;
            int ranPos = L + (int)(Math.random() * (R - L));
            swap(allXor, ranPos, end);
            for(int i = L; i < end;){
                if(allXor[i] > allXor[R]){
                    swap(allXor, i, --end);
                    continue;
                } else if(allXor[i] < allXor[R]){
                    swap(allXor, i, begin++);
                }
                i++;
            }
            swap(allXor, end, R);
            if(begin <= desPos && end >= desPos){
                return allXor[begin];
            }
            if(begin > desPos){
                R = begin - 1;
            } else if(end < desPos){
                L = end + 1;
            }
        }
        return allXor[L];
    }

    public static void swap(int[] arr, int i, int j){
        if(arr[i] != arr[j]){
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    //对数器
    public static int compare(int[][] matrix, int k){
        int m = matrix.length, n = matrix[0].length;
        if(m == 1 && n == 1){
            return matrix[0][0];
        }
        //暴力方法求解所有坐标异或值数组
        int[] xors = new int[m*n];
        int xorPos = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                xors[xorPos++] = getMatrixXor(matrix, i, j);
            }
        }
        //暴力方法将xors数组排序，然后找第k大
        Arrays.sort(xors);
        return xors[xors.length - k];
    }

    /**
     * 计算 (0,0) -> (row, col)所围成的子矩阵中所有元素的异或值
     * @param matrix
     * @param row
     * @param col
     * @return
     */
    public static int getMatrixXor(int[][] matrix, int row, int col){
        int ans = 0;
        for(int i = 0; i <= row; i++){
            for(int j = 0; j <= col; j++){
                ans ^= matrix[i][j];
            }
        }
        return ans;
    }

    public static int[][] getMatrix(int m, int n){
        int[][] cost = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                cost[i][j] = (int)(Math.random() * 8) + 1;
            }
        }
        return cost;
    }
}
