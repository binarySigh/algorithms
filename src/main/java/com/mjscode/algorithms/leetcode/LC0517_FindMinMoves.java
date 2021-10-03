package com.mjscode.algorithms.leetcode;

/**
 * //假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * //
 * // 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * //
 * // 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣
 * //机中衣物的数量相等，则返回 -1 。
 * //
 * // 示例 1：
 * //
 * //输入：machines = [1,0,5]
 * //输出：3
 * //解释：
 * //第一步:    1     0 <-- 5    =>    1     1     4
 * //第二步:    1 <-- 1 <-- 4    =>    2     1     3
 * //第三步:    2     1 <-- 3    =>    2     2     2
 * //
 * // 示例 2：
 * //
 * //输入：machines = [0,3,0]
 * //输出：2
 * //解释：
 * //第一步:    0 <-- 3     0    =>    1     2     0
 * //第二步:    1     2 --> 0    =>    1     1     1
 * //
 * // 示例 3：
 * //
 * //输入：machines = [0,2,0]
 * //输出：-1
 * //解释：
 * //不可能让所有三个洗衣机同时剩下相同数量的衣物。
 * //
 * // 提示：
 * //
 * // n == machines.length
 * // 1 <= n <= 10⁴
 * // 0 <= machines[i] <= 10⁵
 * //
 * // Related Topics 贪心 数组
 * @author binarySigh
 * @date 2021/9/29 20:59
 */
public class LC0517_FindMinMoves {

    public static void main(String[] args) {
        // --> 3
    //  int[] machines1 = {1,0,5};

        // --> 2
    //  int[] machines1 = {0,3,0};

        // --> -1
        int[] machines1 = {0,2,0};

        System.out.println(findMinMoves(machines1));

    }

    public static int findMinMoves(int[] machines) {
        int n = machines.length;
        int cap = 0;
        for(int m : machines){
            cap += m;
        }
        if(cap % n != 0){
            return -1;
        }
        cap /= n;
        int max = 0;
        int pre = 0;
        for(int i = 0; i < n; i++) {
            int cost = pre + machines[i] - cap;
            if(cost < 0) {
                max = Math.max(max, -cost);
                pre = cost;
            } else if(cost > 0) {
                if(pre < 0) {
                    // 当前位置在满足左边的借贷要求之后，还有余力往右边分发。则当前的步数为 左+右 分发的件数和
                    int curCost = Math.abs(pre) + cost;
                    max = Math.max(max, curCost);
                    pre = cost;
                } else {
                    // 当前位置衣服数能高于平均值是因为接受了左边机器的赠送，那么它只需要把多余的赠送出去即可
                    pre = cost;
                    max = Math.max(max, pre);
                }
            } else {
                // 当前刚好满足要求。将pre置零即可
                pre = 0;
            }
        }
        return max;
    }

}
