package com.mjscode.algorithms.leetcode;

/**
 * //给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数
 * //组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * //
 * // 你按照如下规则进行一场游戏：
 * //
 * // 你从第 0 天开始吃糖果。
 * // 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
 * // 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
 * //
 * // 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是
 * //：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[
 * //i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * //
 * // 请你返回得到的数组 answer 。
 * //
 * // 示例 1：
 * //
 * //输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * //输出：[true,false,true]
 * //提示：
 * //1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
 * //2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型
 * //4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
 * //3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
 * //
 * // 示例 2：
 * //
 * //输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,3
 * //0],[1,3,1]]
 * //输出：[false,true,true,false,false]
 * //
 * // 提示：
 * //
 * // 1 <= candiesCount.length <= 105
 * // 1 <= candiesCount[i] <= 105
 * // 1 <= queries.length <= 105
 * // queries[i].length == 3
 * // 0 <= favoriteTypei < candiesCount.length
 * // 0 <= favoriteDayi <= 109
 * // 1 <= dailyCapi <= 109
 * //
 * // Related Topics 数学
 * @author binarySigh
 * @date 2021/6/1 21:31
 */
public class LC1744_CanEat {
    public static void main(String[] args){
        int[] candiesCount = {5,2,6,4,1};
        int[][] queries = {
                {3,1,2},
                {4,10,3},
                {3,10,100},
                {4,100,30},
                {1,3,1}
        };
        boolean[] canEat = canEat(candiesCount, queries);

        System.out.println(1000000000L * 1000000000L);
        System.out.println(100000 * 100000);
        System.out.println(100000L * 100000L);
    }

    /**
     * 解答成功:
     * 		执行耗时:7 ms,击败了98.59% 的Java用户
     * 		内存消耗:74.8 MB,击败了96.48% 的Java用户
     * @param candiesCount
     * @param queries
     * @return
     */
    public static boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] preSum = new long[candiesCount.length];
        boolean[] canEat = new boolean[queries.length];
        preSum[0] = candiesCount[0];
        for(int i = 1; i < candiesCount.length; i++){
            preSum[i] = preSum[i - 1] + (long)(candiesCount[i]);
        }
        for(int i = 0; i < queries.length; i++){
            long curMax = preSum[queries[i][0]];
            long pre = queries[i][0] == 0 ? 0 : preSum[queries[i][0] - 1];
            long maxConsum = (long)(queries[i][1] + 1) * (long)(queries[i][2]);
            long minConsum = queries[i][1] + 1;
            if(minConsum <= curMax && //按照最小限度吃不会超过最爱品类糖果
                    maxConsum > pre){
                //按照最大限制能吃到当前品类的糖
                canEat[i] = true;
            }
        }
        return canEat;
    }
}
