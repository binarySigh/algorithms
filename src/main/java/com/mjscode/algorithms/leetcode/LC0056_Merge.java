package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * //以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
 * //回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * //
 * // 示例 1：
 * //
 * //输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * //输出：[[1,6],[8,10],[15,18]]
 * //解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * //
 * // 示例 2：
 * //
 * //输入：intervals = [[1,4],[4,5]]
 * //输出：[[1,5]]
 * //解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * //
 * // 提示：
 * //
 * // 1 <= intervals.length <= 104
 * // intervals[i].length == 2
 * // 0 <= starti <= endi <= 104
 * //
 * // Related Topics 排序 数组
 * @author binarySigh
 * @date 2021/4/13 22:02
 */
public class LC0056_Merge {
    public static void main(String[] args){
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
    }

    /**
     * 执行结果： 通过
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 96.07% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 86.98% 的用户
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new ArrayComparator());
        for(int i = 0; i < intervals.length; i++){
            queue.add(intervals[i]);
        }
        ArrayList<int[]> list = new ArrayList<>();
        int start = queue.peek()[0], end = queue.peek()[1];
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            if(tmp[0] <= end){
                end = Math.max(tmp[1], end);
            } else {
                list.add(new int[]{start, end});
                start = tmp[0];
                end = tmp[1];
            }
        }
        list.add(new int[]{start, end});
        int[][] res = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public static class ArrayComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}
