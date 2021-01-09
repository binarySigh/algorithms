package com.mjscode.algorithms.leetcode;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * //给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如
 * //同俄罗斯套娃一样。
 * //
 * // 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * //
 * // 说明:
 * //不允许旋转信封。
 * //
 * // 示例:
 * //
 * // 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * //输出: 3
 * //解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * //
 * // Related Topics 二分查找 动态规划
 *
 * @author binarySigh
 */
public class LC0354_MaxEnvelopes {

    public static void main(String[] args){
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        int max = maxEnvelopes(envelopes);
        System.out.println(max);
    }

    /**
     * 版本 v1.0. leetcode Accept
     *    思路：1. 先将信封排序，按照宽度从小到大，相同时按照高度从小到大
     *         2.  对排好序的信封数组求最长严格递增子序列的长度，即转化为 leetcode_300 问题
     *    本题优化空间：
     *          1. 对本思路中的排序算法优化
     *          2. 放弃本题动态规划的思路，尝试标签中提及的 二分查找 的解法
     *  插入排序的版本：
     *      解答成功:
     * 		执行耗时:555 ms,击败了5.05% 的Java用户
     * 		内存消耗:39.7 MB,击败了13.90% 的Java用户
     * 改成堆排序的版本：
     *      解答成功:
     * 		执行耗时:461 ms,击败了5.05% 的Java用户
     * 		内存消耗:39.6 MB,击败了26.43% 的Java用户
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes){
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }
        if(envelopes.length == 1){
            return 1;
        }
        Comparator<int[]> comparator = new EnvelopeComparator();
        Comparator<int[]> sortComparator = new EnvelopeSortComparator();
        //先将envelopes按照定义的排序比较器排序
        /*// 此处选择的是插入排序，复杂度O(N^2),可优化
        for(int i = 1; i < envelopes.length; i++){
            for(int j = i - 1; j >= 0 && sortComparator.compare(envelopes[j], envelopes[j + 1]) > 0; j--){
                swap(envelopes, j, j + 1);
            }
        }*/
        // 改进：使用堆排序，时间复杂度O(N*logN)
        PriorityQueue<int[]> queue = new PriorityQueue<>(sortComparator);
        for(int i = 0; i < envelopes.length; i++){
            queue.add(envelopes[i]);
        }
        for(int i = 0; i < envelopes.length; i++){
            envelopes[i] = queue.poll();
        }
        //剩下的按照LC0300的思路来做即可
        int[] maxText = new int[envelopes.length];
        maxText[envelopes.length - 1] = 1;
        int max = 1;
        int totalMax = 1;
        for(int i = envelopes.length - 2; i >= 0; i--){
            for(int j = i; j < envelopes.length; j++){
                max = comparator.compare(envelopes[i], envelopes[j]) == -1 ? Math.max(max, maxText[j] + 1) : max;
            }
            maxText[i] = max;
            max = 1;
            totalMax = Math.max(totalMax, maxText[i]);
        }
        return totalMax;
    }

    /**
     * 交换数组 i, j 位置的元素
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[][] arr, int i, int j){
        int[] tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    /**
     * 比较信封大小的比较器
     */
    public static class EnvelopeComparator implements Comparator<int[]>{
        @Override
        public int compare(int[] o1, int[] o2) {
            if((o1[0] - o2[0] < 0) && (o1[1] - o2[1] < 0)){
                return -1;
            } else if((o1[0] - o2[0] > 0) && (o1[1] - o2[1] > 0)){
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 给信封排序的比较器
     */
    public static class EnvelopeSortComparator implements Comparator<int[]>{
        /**
         * 信封 宽度小的排前面，宽度一样则高度小的放前面
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
        }
    }
}
