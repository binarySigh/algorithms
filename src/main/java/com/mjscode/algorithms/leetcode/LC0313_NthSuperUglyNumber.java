package com.mjscode.algorithms.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * //超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * //
 * // 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * //
 * // 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * //
 * // 示例 1：
 * //
 * //输入：n = 12, primes = [2,7,13,19]
 * //输出：32
 * //解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
 * //28,32] 。
 * //
 * // 示例 2：
 * //
 * //输入：n = 1, primes = [2,3,5]
 * //输出：1
 * //解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * //
 * // 提示：
 * //
 * // 1 <= n <= 106
 * // 1 <= primes.length <= 100
 * // 2 <= primes[i] <= 1000
 * // 题目数据 保证 primes[i] 是一个质数
 * // primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 * //
 * // Related Topics 数组 哈希表 数学 动态规划 堆（优先队列）
 * @author binarySigh
 * @date 2021/8/9 10:28
 */
public class LC0313_NthSuperUglyNumber {
    public static void main(String[] args){
        // --> 32
        /*int[] primes = {2,7,13,19};
        int n = 12;*/

        /*int[] primes = {2,3,5};
        int n = 1;*/

        // --> 1092889481
        int[] primes = {7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251};
        int n = 100000;
        System.out.println(nthSuperUglyNumber(n, primes));
    }

    /**
     * 解答成功:
     * 		执行耗时:126 ms,击败了33.74% 的Java用户
     * 		内存消耗:65.3 MB,击败了30.28% 的Java用户
     * @param n
     * @param primes
     * @return
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        q.add(1);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(--n == 0){
                return cur;
            }
            for(int i : primes) {
                // 虽然输入保证最终要求的第 n个超级丑数是在整型范围内的，
                // 但是中间产生的结果是可能超出整型上限溢出为负数的，这样就会影响算法正确性，因此需要筛选掉
                if(cur < Integer.MAX_VALUE / i) {
                    int newly = cur * i;
                    if (!set.contains(newly)) {
                        q.add(newly);
                        set.add(newly);
                    }
                }
            }
        }
        return -1;
    }
}
