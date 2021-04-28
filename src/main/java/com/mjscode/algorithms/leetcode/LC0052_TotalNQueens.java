package com.mjscode.algorithms.leetcode;

/**
 * //n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * //
 * // 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * //
 * // 示例 1：
 * //
 * //输入：n = 4
 * //输出：2
 * //解释：如上图所示，4 皇后问题存在两个不同的解法。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：1
 * //
 * // 提示：
 * //
 * // 1 <= n <= 9
 * // 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * //
 * // Related Topics 回溯算法
 * @author binarySigh
 * @date 2021/4/28 22:09
 */
public class LC0052_TotalNQueens {
    public static void main(String[] args){
        int n = 10;
        System.out.println(totalNQueens(n));
    }

    /**
     * 本解法利用位运算做了优化，因此碍于整型的位数限制，该解法最高仅能解 32皇后问题，规模更大的皇后问题解法参考 LC0051
     * 解答成功:
     * 		执行耗时:1 ms,击败了84.15% 的Java用户
     * 		内存消耗:35 MB,击败了92.07% 的Java用户
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {
        if(n <= 0 || n > 32){
            return 0;
        }
        return nQueen(0, n, 0, 0, 0);
    }

    /**
     * @param curRow 当前处理到的行数
     * @param N 问题规模
     * @param left 位信息记录前面行皇后在左斜线上的影响
     * @param right 位信息记录前面行皇后在右斜线上的影响
     * @param col 位信息记录哪些列上已有皇后
     * @return
     */
    public static int nQueen(int curRow, int N, int left, int right, int col){
        if(curRow == N){
            return 1;
        }
        int ans = 0;
        //处理当前行皇后的摆放位置
        for(int i = 0; i < N; i++){
            //当前行皇后位置
            int curCol = 1 << i;
            //前面的皇后对当前行带来的左右斜线上的限制
            int curLeft = left << 1;
            int curRight = right >>> 1;
            //检查列
            if((col | curCol) == col){
                continue;
            }
            //检查左右斜线影响
            if((curLeft | curCol) == curLeft || (curRight | curCol) == curRight){
                continue;
            }
            //当前位置在当前行有效，去下一行验证,并收集答案
            col |= curCol;
            curLeft |= curCol;
            curRight |= curCol;
            ans += nQueen(curRow + 1, N, curLeft, curRight, col);
            //当前位置验证完毕，撤销当前位置的皇后摆放，去当前行下一个列位置验证收集可能性
            col -= curCol;
        }
        return ans;
    }
}
