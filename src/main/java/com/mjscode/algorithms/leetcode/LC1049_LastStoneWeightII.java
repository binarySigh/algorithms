package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * //有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * //
 * // 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * //
 * // 如果 x == y，那么两块石头都会被完全粉碎；
 * // 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * //
 * // 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * //
 * // 示例 1：
 * //
 * //输入：stones = [2,7,4,1,8,1]
 * //输出：1
 * //解释：
 * //组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * //组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * //组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * //组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * //
 * // 示例 2：
 * //
 * //输入：stones = [31,26,33,21,40]
 * //输出：5
 * //
 * // 示例 3：
 * //
 * //输入：stones = [1,2]
 * //输出：1
 * //
 * // 提示：
 * //
 * // 1 <= stones.length <= 30
 * // 1 <= stones[i] <= 100
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/6/8 21:25
 */
public class LC1049_LastStoneWeightII {

    public static void main(String[] args){
        int[] stones1 = {31,26,33,21,40};
        System.out.println(compare(stones1));


        System.out.println("------test begin---------");
        for(int i = 1; i < 20_0000; i++) {
            int len = (int)(Math.random() * 7) + 1;
            int[] stones = getStones(len);
            int result = lastStoneWeightII(stones);
            int compare = compare(stones);
            if(result != compare){
                System.out.println("--------第[" + i + "]次测试失败！-----");
                ArrayUtils.showArray(stones);
                System.out.println("动态规划: " + result);
                System.out.println("暴力方法: " + compare);
                break;
            }
        }
        System.out.println("------test end---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了76.62% 的Java用户
     * 		内存消耗:36.3 MB,击败了17.27% 的Java用户
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int i : stones){
            sum += i;
        }
        int len = stones.length;
        int target = sum / 2;
        boolean[][] dp = new boolean[target + 1][len];
        dp[0][0] = true;
        if(stones[0] <= target){
            dp[stones[0]][0] = true;
        }
        for(int j = 1; j < len; j++){
            if(stones[j] <= target){
                dp[stones[j]][j] = true;
            }
            for(int i = 0; i <= target; i++){
                int preSum = i - stones[j];
                dp[i][j] = dp[i][j - 1] || (preSum >= 0 && dp[preSum][j - 1]);
            }
        }
        for(int i = target; i >= 0; i--){
            if(dp[i][len - 1]){
                return sum - i - i;
            }
        }
        return stones[0];
    }

    public static int compare(int[] stones){
        return process(stones);
    }

    public static int process(int[] stones){
        int select1 = 0, select2 = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < stones.length; i++){
            if(stones[i] > 0){
                select1 = stones[i];
                for(int j = i + 1; j < stones.length; j++){
                    if(stones[j] > 0){
                        select2 = stones[j];
                        stones[i] = 0;
                        stones[j] = Math.abs(select1 - select2);
                        min = Math.min(min, process(stones));
                        stones[i] = select1;
                        stones[j] = select2;
                    }
                }
            }
        }
        return select2 == 0 ? select1 : min;
    }

    public static int[] getStones(int len){
        int[] stones = new int[len];
        for(int i = 0; i < len; i++){
            stones[i] = (int)(Math.random() * 100) + 1;
        }
        return stones;
    }
}
