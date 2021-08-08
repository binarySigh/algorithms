package com.mjscode.algorithms.techniques;

/**
 * 矩阵的一些公式处理技巧
 * @author binarySigh
 * @date 2021/8/8 22:14
 */
public class matrixProblems {



    /**
     * 快速幂方式求矩阵 m 的 b 次方
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
}
