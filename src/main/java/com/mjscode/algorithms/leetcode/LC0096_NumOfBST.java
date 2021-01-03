package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * //给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * //
 * // 示例:
 * //
 * // 输入: 3
 * //输出: 5
 * //解释:
 * //给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * //
 * //   1         3     3      2      1
 * //    \       /     /      / \      \
 * //     3     2     1      1   3      2
 * //    /     /       \                 \
 * //   2     1         2                 3
 * // Related Topics 树 动态规划
 *
 * @author binarySigh
 */
public class LC0096_NumOfBST {

    public static void main(String[] args){
        System.out.println("当 n = 1 时，可以组成的BST树的种类有：" + dp(1));
        System.out.println("当 n = 2 时，可以组成的BST树的种类有：" + dp(2));
        System.out.println("当 n = 3 时，可以组成的BST树的种类有：" + dp(3));
        System.out.println("当 n = 4 时，可以组成的BST树的种类有：" + dp(4));
        System.out.println("当 n = 5 时，可以组成的BST树的种类有：" + dp(5));
    }

    /**
     * 动态规划解法. leetcode  Accept
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:34.9 MB,击败了97.20% 的Java用户
     * @param n
     * @return
     */
    public static int dp(int n){
        //建立一张动态规划表。dp[i][j]表示：当n=i时，以数字j为根节点的BST树数量
        int[][] dp = new int[n+1][n+1];
        //columnSum 表示当前列的种类之和
        int columnSum = 1;
        //初始化，当n=1时，以数字1为根结点的BST树数量可知为1
        dp[1][1] = 1;
        // 观察题设情况下BST生成规律可以发现：
        //  1、当 n=i 时，以数字i为根节点的BST树种数 为前一列BST种数之和，也即：dp[i][i] = dp[i-1][i] + …… + dp[i-1][i-1]
        //               也就是 dp[i][i] = 前一列的columnSum值
        //  2、当 n=i 时，第i列上的有效数字(就是在 dp[i][1] ~ dp[i][i]之间的元素)，是关于j = i/2这条中位线上下对称的，
        //               所以我们可以只求对称线下方的元素的值，而对称线上方的只要把它对称点的值赋给它即可
        //  3、当 n=i 时，以数字j(j≠i时)为根节点的BST树种数 = “当n=j时，以j为根节点的BST种数” * “当n=i-j时全部的BST树种类”
        //               而“当n=i-j时全部的BST树种类”=“当n=i-j+1时，以数字i-j+1为根节点的BST树种数”(也就是dp[i-j+1][i-j+1])
        //               综上可知，当j≠i时，有dp[i][j] = dp[j][j] * dp[i-j+1][i-j+1];
        for(int i = 2; i <= n; i++){
            for(int j = i; j >= 1; j--){
                if(i == j){
                    // 对应规律1
                    dp[i][i] = columnSum;
                    // 前一列的columnSum用完记得清零，让它参与到这一列元素之和的统计时不受之前的影响
                    columnSum = 0;
                } else if(i > j && (j > (i >> 1))){
                    // 对应规律3，此时需要计算本格元素值
                    dp[i][j] = dp[j][j] * dp[i-j+1][i-j+1];
                } else if(i > j && (j <= (i >> 1))){
                    // 对应规律2，此时只要将它对称点的值赋给它即可
                    dp[i][j] = dp[i][i-j+1];
                }
                columnSum += dp[i][j];
            }
        }
        return columnSum;
    }
}
