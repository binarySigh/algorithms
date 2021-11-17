package com.mjscode.algorithms.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * //给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是
 * // (xi, yi) ，右上顶点是 (ai, bi) 。
 * //
 * // 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * //输出：true
 * //解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 * //
 * // 示例 2：
 * //
 * //输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * //输出：false
 * //解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 * //
 * // 示例 3：
 * //
 * //输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
 * //输出：false
 * //解释：图形顶端留有空缺，无法覆盖成一个矩形。
 * //
 * // 示例 4：
 * //
 * //输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * //输出：false
 * //解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 * //
 * // 提示：
 * //
 * // 1 <= rectangles.length <= 2 * 10⁴
 * // rectangles[i].length == 4
 * // -10⁵ <= xi, yi, ai, bi <= 10⁵
 * //
 * // Related Topics 数组 扫描线
 * @author binarySigh
 * @date 2021/11/16 22:58
 */
public class LC0391_IsRectangleCover {

    /**
     * 解答成功:
     * 		执行耗时:24 ms,击败了92.16% 的Java用户
     * 		内存消耗:47 MB,击败了67.45% 的Java用户
     * @param recs
     * @return
     */
    public static boolean isRectangleCover(int[][] recs) {
        int left = recs[0][0], right = recs[0][2], up = recs[0][3], down = recs[0][1];
        int sumArea = 0;
        HashMap<Integer, HashMap<Integer, Integer>> points = new HashMap<>();
        for(int i = 0; i < recs.length; i++) {
            left = Math.min(left, recs[i][0]);
            right = Math.max(right, recs[i][2]);
            up = Math.max(up, recs[i][3]);
            down = Math.min(down, recs[i][1]);
            sumArea += (recs[i][2] - recs[i][0]) * (recs[i][3] - recs[i][1]);
            for(int t = 0; t < 3; t += 2) {
                for(int k = 1; k < 4; k += 2) {
                    if(points.get(recs[i][t]) == null) {
                        points.put(recs[i][t], new HashMap<>());
                    }
                    HashMap<Integer, Integer> tmp = points.get(recs[i][t]);
                    tmp.put(recs[i][k], tmp.getOrDefault(recs[i][k], 0) + 1);
                }
            }
        }
        //
        for(Map.Entry<Integer, HashMap<Integer, Integer>> father : points.entrySet()) {
            for(Map.Entry<Integer, Integer> entry : father.getValue().entrySet()) {
                // 4个顶点只能出现一次
                if((father.getKey() == left || father.getKey() == right) &&
                        (entry.getKey() == up || entry.getKey() == down)) {
                    if(entry.getValue() != 1) return false;
                } else {
                    // 其余内部点只能出现偶数次
                    if((entry.getValue() & 1) == 1) return false;
                }
            }
        }
        return sumArea == (right - left) * (up - down);
    }
}
