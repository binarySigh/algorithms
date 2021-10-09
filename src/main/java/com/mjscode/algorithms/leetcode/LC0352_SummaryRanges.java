package com.mjscode.algorithms.leetcode;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * //给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * //
 * // 实现 SummaryRanges 类：
 * //
 * // SummaryRanges() 使用一个空数据流初始化对象。
 * // void addNum(int val) 向数据流中加入整数 val 。
 * // int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 * //
 * // 示例：
 * //
 * //输入：
 * //["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals",
 * //"addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * //[[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * //输出：
 * //[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
 * // null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * //
 * //解释：
 * //SummaryRanges summaryRanges = new SummaryRanges();
 * //summaryRanges.addNum(1);      // arr = [1]
 * //summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * //summaryRanges.addNum(3);      // arr = [1, 3]
 * //summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * //summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * //summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * //summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * //summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * //summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * //summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 * //
 * // 提示：
 * //
 * // 0 <= val <= 10⁴
 * // 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次
 * //
 * // 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 * // Related Topics 设计 二分查找 有序集合
 * @author binarySigh
 * @date 2021/10/9 22:17
 */
public class LC0352_SummaryRanges {
    private TreeSet<int[]> ranges;

    public LC0352_SummaryRanges() {
        this.ranges = new TreeSet<>((a, b) -> {return a[0] - b[0];});
    }

    public void addNum(int val) {
        int[] tmp = new int[]{val, val};
        int[] floor = this.ranges.floor(tmp);
        int[] ceiling = this.ranges.ceiling(tmp);
        if((floor != null && floor[1] >= val) ||
                (ceiling != null && ceiling[0] == val)) {
            return;
        }
        // 上一个区间检查
        if(floor != null && val - floor[1] == 1) {
            floor[1] = val;
        }
        // 下一个区间检查
        if(ceiling != null && ceiling[0] - val == 1) {
            ceiling[0] = val;
        }
        // 区间合并
        if(floor != null && ceiling != null &&
                ceiling[0] == floor[1]){
            floor[1] = ceiling[1];
            this.ranges.remove(ceiling);
        }
        // 前面的检查和合并都未发生，直接加入当前新区间
        if((floor == null || val - floor[1] > 1) &&
                (ceiling == null || ceiling[0] - val > 1)){
            this.ranges.add(tmp);
        }
    }

    public int[][] getIntervals() {
        int[][] ret = new int[this.ranges.size()][2];
        int i = 0;
        Iterator<int[]> ite = this.ranges.iterator();
        while(ite.hasNext()) {
            int[] tmp = ite.next();
            ret[i][0] = tmp[0];
            ret[i++][1] = tmp[1];
        }
        return ret;
    }

    public static void main(String[] args) {
        LC0352_SummaryRanges sr = new LC0352_SummaryRanges();
        int[][] ans0 = sr.getIntervals();
        sr.addNum(1);
        int[][] ans1 = sr.getIntervals();
        sr.addNum(3);
        int[][] ans2 = sr.getIntervals();
        sr.addNum(7);
        int[][] ans3 = sr.getIntervals();
        sr.addNum(2);
        int[][] ans4 = sr.getIntervals();
        sr.addNum(6);
        int[][] ans5 = sr.getIntervals();

    }

}
