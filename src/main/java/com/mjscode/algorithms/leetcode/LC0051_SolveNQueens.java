package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * //
 * // 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * //
 * // 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * //
 * // 示例 1：
 * //
 * //输入：n = 4
 * //输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * //解释：如上图所示，4 皇后问题存在两个不同的解法。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：[["Q"]]
 * //
 * // 提示：
 * //
 * // 1 <= n <= 9
 * // 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * //
 * // Related Topics 回溯算法
 * @author binarySigh
 * @date 2021/4/28 21:46
 */
public class LC0051_SolveNQueens {
    public static void main(String[] args){
        int N = 4;
        List<List<String>> ans = solveNQueens(N);
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了93.92% 的Java用户
     * 		内存消耗:38.6 MB,击败了72.63% 的Java用户
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        if(n <= 0){
            return ans;
        }
        nQueen(ans, 0, n, new int[n], new boolean[n]);
        return ans;
    }

    /**
     * @param ans 解集
     * @param curRow 当前该处理的行数
     * @param N 问题规模
     * @param post 记录已存放的皇后位置。post[i] = j,表示 i 行的皇后放在了 j 列
     * @param col 记录每一列是否有皇后。col[i] = true,表示 i 列上有皇后，false表示无皇后
     */
    public static void nQueen(List<List<String>> ans, int curRow, int N, int[] post, boolean[] col){
        if(curRow == N){
            List<String> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++) {
                    if (post[i] == j) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                list.add(sb.toString());
                sb = new StringBuilder();
            }
            ans.add(list);
            return;
        }
        //处理当前行皇后位置
        for(int i = 0; i < N; i++){
            //检查当前列
            if(col[i]){
                continue;
            }
            //对于之前已经摆好的皇后，一个个检查是否与当前行的皇后共斜线
            boolean makeSense = true;
            for(int j = 0; j < curRow; j++){
                if(Math.abs(i - post[j]) == Math.abs(curRow - j)){
                    makeSense = false;
                    break;
                }
            }
            //当前行摆放的位置在当前行有效，往下一行去验证
            if(makeSense){
                col[i] = true;
                post[curRow] = i;
                nQueen(ans, curRow + 1, N, post, col);
                //当前位置尝试完成撤销当前位置的影响，继续尝试当前行下一个位置
                //post数组理论上也要归位，但我们每次遍历post都只遍历到当前行的上一行，所以这里不复位也不影响
                col[i] = false;
            }
        }
    }
}
