package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;

/**
 * //给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * //
 * // 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * //
 * // 示例 1：
 * //
 * //输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * //输出：[[1,5],[6,9]]
 * //
 * // 示例 2：
 * //
 * //输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * //输出：[[1,2],[3,10],[12,16]]
 * //解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * //
 * // 示例 3：
 * //
 * //输入：intervals = [], newInterval = [5,7]
 * //输出：[[5,7]]
 * //
 * // 示例 4：
 * //
 * //输入：intervals = [[1,5]], newInterval = [2,3]
 * //输出：[[1,5]]
 * //
 * // 示例 5：
 * //
 * //输入：intervals = [[1,5]], newInterval = [2,7]
 * //输出：[[1,7]]
 * //
 * // 提示：
 * //
 * // 0 <= intervals.length <= 104
 * // intervals[i].length == 2
 * // 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * // intervals 根据 intervals[i][0] 按 升序 排列
 * // newInterval.length == 2
 * // 0 <= newInterval[0] <= newInterval[1] <= 105
 * //
 * // Related Topics 排序 数组
 * @author binarySigh
 * @date 2021/4/13 23:52
 */
public class LC0057_Insert {
    public static void main(String[] args){
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {11,12};
        int[][] ans = insert(intervals, newInterval);
    }

    /**
     *解答成功:
     * 		执行耗时:2 ms,击败了71.79% 的Java用户
     * 		内存消耗:41 MB,击败了5.82% 的Java用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0){
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }
        int curIndx = 0;
        boolean inserted = false;
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            if(!inserted){
                if(i == 0 && intervals[i][0] >= newInterval[1]){
                    if(intervals[i][0] > newInterval[1]){
                        list.add(newInterval);
                    } else {
                        intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
                        intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                    }
                    list.add(intervals[i]);
                    curIndx++;
                    inserted = true;
                    continue;
                }
                if(curIndx == intervals.length - 1){
                    break;
                }
                if(newInterval[0] <= intervals[i][1]){
                    //融合到当前区间插入
                    intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
                    intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                    inserted = true;
                } else if(newInterval[1] <= intervals[i + 1][0]){
                    list.add(intervals[i]);
                    if(newInterval[1] < intervals[i + 1][0]){
                        //作为独立区间插入
                        list.add(newInterval);
                    } else {
                        //并到下一区间
                        intervals[i + 1][0] = Math.min(intervals[i + 1][0], newInterval[0]);
                    }
                    curIndx++;
                    inserted = true;
                } else {
                    list.add(intervals[i]);
                    curIndx++;
                }
            } else {
                if(intervals[i][0] <= intervals[curIndx][1]){
                    intervals[curIndx][1] = Math.max(intervals[curIndx][1], intervals[i][1]);
                } else {
                    list.add(intervals[curIndx]);
                    curIndx = i;
                }
            }
        }
        if(curIndx < intervals.length) {
            list.add(intervals[curIndx]);
        }
        if(!inserted){
            if(list.get(list.size() - 1)[1] >= newInterval[0]){
                list.get(list.size() - 1)[0] = Math.min(newInterval[0], list.get(list.size() - 1)[0]);
                list.get(list.size() - 1)[1] = Math.max(newInterval[1], list.get(list.size() - 1)[1]);
            } else {
                list.add(newInterval);
            }
        }
        int[][] res = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
