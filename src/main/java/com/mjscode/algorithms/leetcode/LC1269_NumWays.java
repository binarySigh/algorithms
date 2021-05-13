package com.mjscode.algorithms.leetcode;

/**
 * //有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * //
 * // 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * //
 * // 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * //
 * // 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * //
 * // 示例 1：
 * //
 * // 输入：steps = 3, arrLen = 2
 * //输出：4
 * //解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * //向右，向左，不动
 * //不动，向右，向左
 * //向右，不动，向左
 * //不动，不动，不动
 * //
 * // 示例 2：
 * //
 * // 输入：steps = 2, arrLen = 4
 * //输出：2
 * //解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * //向右，向左
 * //不动，不动
 * //
 * // 示例 3：
 * //
 * // 输入：steps = 4, arrLen = 2
 * //输出：8
 * //
 * // 提示：
 * //
 * // 1 <= steps <= 500
 * // 1 <= arrLen <= 10^6
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/13 19:48
 */
public class LC1269_NumWays {

    public static void main(String[] args){
        int steps = 27;
        int arrLen = 7;
        System.out.println(numWays(steps, arrLen));
        System.out.println(solution(steps, arrLen));
    }

    /**
     * 解答成功:
     * 		执行耗时:15 ms,击败了68.42% 的Java用户
     * 		内存消耗:38.8 MB,击败了54.39% 的Java用户
     * @param steps
     * @param arrLen
     * @return
     */
    public static int numWays(int steps, int arrLen) {
        if(arrLen == 1 || steps == 1){
            return 1;
        }
        int maxIndex = Math.min(steps, arrLen);
        int[][] dp = new int[steps][maxIndex];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i = 1; i < steps; i++){
            for(int j = 0; j < maxIndex; j++){
                int pre = j - 1 >= 0 ? dp[i - 1][j - 1] : 0;
                int cur = dp[i - 1][j];
                int next = j + 1 < maxIndex ? dp[i - 1][j + 1] : 0;
                dp[i][j] = (pre + cur) % 1000000007;
                dp[i][j] = (dp[i][j] + next) % 1000000007;
            }
        }
        return dp[steps - 1][0];
    }

    public static int solution(int steps, int arrLen) {
        if(arrLen == 1 || steps == 1){
            return 1;
        }
        int ways = process(steps, arrLen, 0);
        return ways % 1000000007;
    }

    public static int process(int steps, int arrLen, int index){
        if(index < 0 || index >= arrLen){
            return 0;
        }
        if(steps == 0){
            return index == 0 ? 1 : 0;
        }
        int ways = process(steps  - 1, arrLen, index - 1) +
                process(steps  - 1, arrLen, index) +
                process(steps  - 1, arrLen, index + 1);
        return ways;
    }
}
