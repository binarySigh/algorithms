package com.mjscode.algorithms.leetcode.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * //城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * //
 * // 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * //
 * // lefti 是第 i 座建筑物左边缘的 x 坐标。
 * // righti 是第 i 座建筑物右边缘的 x 坐标。
 * // heighti 是第 i 座建筑物的高度。
 * //
 * // 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
 * //列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * //
 * // 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
 * //案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * //
 * // 示例 1：
 * //
 * //输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * //输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * //解释：
 * //图 A 显示输入的所有建筑物的位置和高度，
 * //图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 * //
 * // 示例 2：
 * //
 * //输入：buildings = [[0,2,3],[2,5,3]]
 * //输出：[[0,3],[5,0]]
 * //
 * // 提示：
 * //
 * // 1 <= buildings.length <= 104
 * // 0 <= lefti < righti <= 231 - 1
 * // 1 <= heighti <= 231 - 1
 * // buildings 按 lefti 非递减排序
 * //
 * // Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列）
 * @author binarySigh
 * @date 2021/7/27 23:03
 */
public class LC0218_GetSkyline {
    public static void main(String[] args){
        int[][] bs = {
                {2,9,10},
                {3,7,15},
                {5,12,12},
                {15,20,10},
                {19,24,8}
        };
        List<List<Integer>> ans = getSkyline(bs);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:191 ms,击败了22.19% 的Java用户
     * 		内存消耗:40.9 MB,击败了98.11% 的Java用户
     * @param buildings
     * @return
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        for(int[] bs : buildings){
            list.add(new int[]{bs[0], -bs[2]});
            list.add(new int[]{bs[1], bs[2]});
        }
        Collections.sort(list, (int[] a, int[] b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int pre = 0;
        q.add(0);
        for(int[] ps : list){
            if(ps[1] < 0){
                q.add(-ps[1]);
            } else {
                q.remove(ps[1]);
            }
            int cur = q.peek();
            if(cur != pre){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(ps[0]);
                tmp.add(cur);
                ans.add(tmp);
                pre = cur;
            }
        }
        return ans;
    }
}
