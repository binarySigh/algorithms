package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
 * // i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * //
 * // 返回平面上所有回旋镖的数量。
 * //
 * // 示例 1：
 * //
 * //输入：points = [[0,0],[1,0],[2,0]]
 * //输出：2
 * //解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * //
 * // 示例 2：
 * //
 * //输入：points = [[1,1],[2,2],[3,3]]
 * //输出：2
 * //
 * // 示例 3：
 * //
 * //输入：points = [[1,1]]
 * //输出：0
 * //
 * // 提示：
 * //
 * // n == points.length
 * // 1 <= n <= 500
 * // points[i].length == 2
 * // -104 <= xi, yi <= 104
 * // 所有点都 互不相同
 * //
 * // Related Topics 数组 哈希表 数学
 * @author binarySigh
 * @date 2021/9/13 20:54
 */
public class LC0447_NumberOfBoomerangs {

    /**
     * 解答成功:
     * 		执行耗时:99 ms,击败了89.1% 的Java用户
     * 		内存消耗:38.2 MB,击败了82.8% 的Java用户
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        if(points == null || points.length < 3){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < points.length; i++){
            // 第一轮 加
            for(int j = 0; j < points.length; j++){
                if(j == i){
                    continue;
                }
                //这里不能放距离，因为开根号之后的double结果可能存在精度丢失，而根据数据量可知距离的平方不会超过整数范围，因此可以直接放距离的平方
                int curDisPow = (int)(
                        Math.pow(points[j][0] - points[i][0], 2) + Math.pow(points[j][1] - points[i][1], 2));
                map.put(curDisPow, map.getOrDefault(curDisPow, 0) + 1);
            }
            // 第二轮 找
            for(int j = 0; j < points.length; j++){
                if(j == i){
                    continue;
                }
                int curDisPow = (int)(
                        Math.pow(points[j][0] - points[i][0], 2) + Math.pow(points[j][1] - points[i][1], 2));
                ans += map.getOrDefault(curDisPow, 1) - 1;
            }
            map.clear();
        }
        return ans;
    }
}
