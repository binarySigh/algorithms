package com.mjscode.algorithms.leetcode;

/**
 * //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * //
 * // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * //
 * // 注意：给定 n 是一个正整数。
 * //
 * // 示例 1：
 * //
 * // 输入： 2
 * //输出： 2
 * //解释： 有两种方法可以爬到楼顶。
 * //1.  1 阶 + 1 阶
 * //2.  2 阶
 * //
 * // 示例 2：
 * //
 * // 输入： 3
 * //输出： 3
 * //解释： 有三种方法可以爬到楼顶。
 * //1.  1 阶 + 1 阶 + 1 阶
 * //2.  1 阶 + 2 阶
 * //3.  2 阶 + 1 阶
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/23 0:49
 */
public class LC0070_ClimbStairs {
    public static void main(String[] args){
        /*int n = 3;
        System.out.println(compare(n));
        System.out.println(climbStairs(n));*/
        System.out.println("--------------测试开始-------------");
        for(int i = 1; i < 100; i++){
            int res = climbStairs(i);
            int ans = compare(i);
            if(res != ans){
                System.out.println("出错台阶数：" + i);
                System.out.println("我的答案：" + res);
                System.out.println("正确答案：" + ans);
                break;
            }
        }
        System.out.println("--------------测试结束-------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.4 MB,击败了15.89% 的Java用户
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n < 3){
            return n;
        }
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;
        int cur = 0;
        for(int i = 3; i <= n; i++){
            dp[cur] = dp[cur ^ 1] + dp[cur];
            cur ^= 1;
        }
        return dp[cur ^ 1];
    }

    public static int compare(int n){
        if(n <= 0){
            return n == 0 ? 1 : 0;
        }
        return compare(n - 1) + compare(n - 2);
    }
}
