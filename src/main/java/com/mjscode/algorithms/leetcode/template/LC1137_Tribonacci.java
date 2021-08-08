package com.mjscode.algorithms.leetcode.template;

/**
 * //泰波那契序列 Tn 定义如下：
 * //
 * // T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * //
 * // 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * //
 * // 示例 1：
 * //
 * // 输入：n = 4
 * //输出：4
 * //解释：
 * //T_3 = 0 + 1 + 1 = 2
 * //T_4 = 1 + 1 + 2 = 4
 * //
 * // 示例 2：
 * //
 * // 输入：n = 25
 * //输出：1389537
 * //
 * // 提示：
 * //
 * // 0 <= n <= 37
 * // 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * //
 * // Related Topics 记忆化搜索 数学 动态规划
 * @author binarySigh
 * @date 2021/8/8 9:39
 */
public class LC1137_Tribonacci {

    public static void main(String[] args){
        int a = 3, b = 14;
        System.out.println(Math.pow(a, b));
        System.out.println(quickPow(a, b));

        System.out.println(tribonacci(25));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.2 MB,击败了52.48% 的Java用户
     * @param n
     * @return
     */
    public static int tribonacci(int n) {
        if(n < 3){
            return n == 0 ? 0 : 1;
        }
        int[][] m = {
                {1,1,0},
                {1,0,1},
                {1,0,0}
        };
        n += 1;
        int[][] res = matrixMultiply(new int[][] {{1,1,0}}, quickPowOfMatrix(m, n - 3));
        return res[0][0];
    }

    /**
     * 求矩阵 m 的 b 次方
     * @param m
     * @param b
     * @return
     */
    public static int[][] quickPowOfMatrix(int[][] m, int b){
        int[][] ans = getIdentityMatrix(m.length);
        while(b > 0) {
            if((b & 1) == 1) {
                ans = matrixMultiply(ans, m);
            }
            m = matrixMultiply(m, m);
            b >>= 1;
        }
        return ans;
    }

    /**
     * 获取 n * n 的单位矩阵
     * @param n
     * @return
     */
    public static int[][] getIdentityMatrix(int n){
        int[][] ret = new int[n][n];
        for(int i = 0; i < n; i++){
            ret[i][i] = 1;
        }
        return ret;
    }

    /**
     * 矩阵乘法
     * @param m
     * @param n
     * @return
     */
    public static int[][] matrixMultiply(int[][] m, int[][] n){
        int[][] ans = new int[m.length][n[0].length];
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[0].length; j++){
                for(int t = 0; t < m[0].length; t++){
                    ans[i][j] += m[i][t] * n[t][j];
                }
            }
        }
        return ans;
    }

    /**
     * 求 a^b 的值
     * @param a
     * @param b
     * @return
     */
    public static int quickPow(int a, int b){
        int ans = 1;
        while(b > 0) {
            if((b & 1) == 1){
                ans *= a;
            }
            a *= a;
            b >>= 1;
        }
        return ans;
    }
}
