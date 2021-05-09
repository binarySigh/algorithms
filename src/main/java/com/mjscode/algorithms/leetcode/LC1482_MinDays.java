package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * //
 * // 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * //
 * // 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * //
 * // 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * //
 * // 示例 1：
 * //
 * // 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * //输出：3
 * //解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * //现在需要制作 3 束花，每束只需要 1 朵。
 * //1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * //2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * //3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * //
 * // 示例 2：
 * //
 * // 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * //输出：-1
 * //解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * //
 * // 示例 3：
 * //
 * // 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * //输出：12
 * //解释：要制作 2 束花，每束需要 3 朵。
 * //花园在 7 天后和 12 天后的情况如下：
 * //7 天后：[x, x, x, x, _, x, x]
 * //可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * //12 天后：[x, x, x, x, x, x, x]
 * //显然，我们可以用不同的方式制作两束花。
 * //
 * // 示例 4：
 * //
 * // 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * //输出：1000000000
 * //解释：需要等 1000000000 天才能采到花来制作花束
 * //
 * // 示例 5：
 * //
 * // 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * //输出：9
 * //
 * // 提示：
 * //
 * // bloomDay.length == n
 * // 1 <= n <= 10^5
 * // 1 <= bloomDay[i] <= 10^9
 * // 1 <= m <= 10^6
 * // 1 <= k <= n
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/5/9 8:25
 */
public class LC1482_MinDays {
    public static void main(String[] args){
        // --> 3
        /*int[] bloomDays = {1,10,3,10,2};
        int m = 3, k = 1;*/

        // --> -1
        /*int[] bloomDays = {1,10,3,10,2};
        int m = 3, k = 2;*/

        // --> 12
        /*int[] bloomDays = {7,7,7,7,12,7,7};
        int m = 2, k = 3;*/

        // --> 1000000000
        /*int[] bloomDays = {1000000000,1000000000};
        int m = 1, k = 1;*/
        /*System.out.println(compare(bloomDays, m, k));
        System.out.println(minDays(bloomDays, m, k));*/

        int[] bloomDays = {6,1,3,5,1};
        int m = 4, k = 1;
        ArrayUtils.showArray(bloomDays);
        System.out.println("m = " + m + ", k = " + k);
        System.out.println("我的答案：" + minDays(bloomDays, m, k));
        System.out.println("参照答案：" + compare(bloomDays, m, k));

        System.out.println("--------------测试开始-----------------");
        /*for(int i = 0; i < 50_0000; i++){
            int len = 5;
            int[] bloomDays = getBloomDays(len);
            int m = (int)(Math.random() * 4) + 1;
            int k = (int)(Math.random() * 4) + 1;
            int result = minDays(bloomDays, m, k);
            int answer = compare(bloomDays, m, k);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错！--------");
                ArrayUtils.showArray(bloomDays);
                System.out.println("m = " + m + ", k = " + k);
                System.out.println("我的答案：" + result);
                System.out.println("参照答案：" + answer);
                break;
            }
        }*/
        System.out.println("--------------测试结束-----------------");
    }

    /**
     * 本题二分有两个思路：
     * 1. 遍历数组，得到最大最小；以最大最小值作为二分初始区域。
     *      复杂度：O(N) + O(N*logM); N为数组长度，M为数组最大最小元素之差
     * 2. 遍历并拷贝数组；给复制的数组升序排序；以数组起止位置为二分初始区域。
     *      复杂度：O(N) + O(N*logN) + O(N*logN); N为数组长度
     * 评价: 虽然 N M 量级差距很大，但是取对数之后 O(N*logM)和O(N*logN)差距大概在两倍，
     *      并且思路二还要承担一个排序的时间消耗 以及 辅助数组的额外空间消耗
     */
    /**
     * 复杂度：O(N*logM),N为数组长度，M为数组最大最小元素之差
     * 解答成功:
     * 		执行耗时:22 ms,击败了47.06% 的Java用户
     * 		内存消耗:47.1 MB,击败了83.24% 的Java用户
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if(len < m * k){
            return -1;
        }
        //遍历数组，获取到最小最大值，作为二分查找的初始上下限
        int L = bloomDay[0], R = bloomDay[0];
        for(int i = 1; i < len; i++){
            L = Math.min(L, bloomDay[i]);
            R = Math.max(R, bloomDay[i]);
        }
        int minDays = R;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            int curBunch = 0;
            //以 mid 为标准，查找是否能够凑出 m 束花
            for(int i = 0; i < len; ){
                int limit = i + k;
                for(; i < limit &&  i < len; i++){
                    if(bloomDay[i] > mid){
                        i++;
                        break;
                    }
                    if(i == limit - 1 || i == len - 1){
                        curBunch = i == limit - 1 ? curBunch + 1 : curBunch;
                    }
                }
                if(curBunch >= m) {
                    break;
                }
            }
            //能凑出 m 束花,就说明当前 mid 标准偏低，等待天数应该降低；反之说明标准偏高，等待天数应该增大
            if(curBunch >= m){
                minDays = Math.min(minDays, mid);
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return minDays;
    }

    /**
     * 回溯法作为对数器方法。
     *      注：作为对数器的方法有问题，但是懒得找了
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public static int compare(int[] bloomDay, int m, int k){
        if(bloomDay.length < m * k){
            return -1;
        }
        int min = process(bloomDay, 0, 0, m, k);
        return min;
    }

    public static int process(int[] bloomDay, int preMax, int index, int m, int k){
        if(index + k >= bloomDay.length || m == 0){
            return m == 0 ? preMax : -1;
        }
        int minWait = -1;
        for(; index + k <= bloomDay.length; index++){
            int curMax = preMax;
            for(int i = 0; i < k; i++){
                curMax = Math.max(curMax, bloomDay[index + i]);
            }
            int next = process(bloomDay, curMax, index + k, m - 1, k);
            minWait = next > -1 ?
                    (minWait == -1 ? next : Math.min(minWait, next))
                    : minWait;
        }
        return minWait;
    }

    public static int[] getBloomDays(int len){
        int[] bloomDays = new int[len];
        for(int i = 0; i < len; i++){
            bloomDays[i] = (int)(Math.random() * 6) + 1;
        }
        return bloomDays;
    }
}
