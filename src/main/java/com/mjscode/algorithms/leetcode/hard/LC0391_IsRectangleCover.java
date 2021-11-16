package com.mjscode.algorithms.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
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
