package com.mjscode.algorithms.leetcode;

/**
 * //假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
 * //我们就称这个数组为一个优美的排列。条件：
 * //
 * //
 * // 第 i 位的数字能被 i 整除
 * // i 能被第 i 位上的数字整除
 * //
 * // 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * //
 * // 示例1:
 * //
 * //输入: 2
 * //输出: 2
 * //解释:
 * //
 * //第 1 个优美的排列是 [1, 2]:
 * //  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * //  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * //
 * //第 2 个优美的排列是 [2, 1]:
 * //  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * //  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * //
 * // 说明:
 * //
 * // N 是一个正整数，并且不会超过15。
 * //
 * // Related Topics 位运算 数组 动态规划 回溯 状态压缩
 * @author binarySigh
 * @date 2021/8/16 9:19
 */
public class LC0526_CountArrangement {
    public static void main(String[] args){
        int n1 = 4;
        System.out.println(compare(n1));
    }

    public static int countArrangement(int n) {
        return 0;
    }

    /**
     * 解答成功:
     * 		执行耗时:57 ms,击败了69.41% 的Java用户
     * 		内存消耗:35.1 MB,击败了74.84% 的Java用户
     * @param n
     * @return
     */
    public static int compare(int n) {
        if(n < 0 || n > 15){
            return 0;
        }
        return process(n, 1, 0);
    }

    public static int process(int n, int idx, int selected) {
        if(idx > n){
            return 1;
        }
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if((i % idx == 0 || idx % i == 0) && (selected & (1 << i)) == 0) {
                ans += process(n, idx + 1, selected | (1 << i));
            }
        }
        return ans;
    }
}
