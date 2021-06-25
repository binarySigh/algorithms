package com.mjscode.algorithms.leetcode;

/**
 * //给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * //
 * // 示例 1：
 * //
 * //输入：points = [[1,1],[2,2],[3,3]]
 * //输出：3
 * //
 * // 示例 2：
 * //
 * //输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * //输出：4
 * //
 * // 提示：
 * //
 * // 1 <= points.length <= 300
 * // points[i].length == 2
 * // -104 <= xi, yi <= 104
 * // points 中的所有点 互不相同
 * //
 * // Related Topics 几何 哈希表 数学
 * @author binarySigh
 * @date 2021/6/24 19:40
 */
public class LC0149_MaxPoints {
    public static void main(String[] args){
        int[][] points = {
                {2,3},
                {3,3},
                {-5,3}
        };
        System.out.println(maxPoints(points));
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了77.77% 的Java用户
     * 		内存消耗:35.8 MB,击败了95.39% 的Java用户
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {
        if(points.length <= 2){
            return points.length;
        }
        int maxCount = 2;
        //增加两个 boolean 防止除数为零
        for(int i = 0; i < points.length - 1; i++){
            for(int j = i + 1; j < points.length; j++){
                int curCount = 2;
                boolean isZero = points[i][1] - points[j][1] == 0;
                double k = (points[i][0] - points[j][0]) * 1.0D / (points[i][1] - points[j][1]);
                for(int t = j + 1; t < points.length; t++){
                    double k2 = (points[i][0] - points[t][0]) * 1.0D / (points[i][1] - points[t][1]);
                    boolean isCurZero = points[i][1] - points[t][1] == 0;
                    if(isCurZero && isZero){
                        curCount++;
                    } else if(isCurZero || isZero){
                        continue;
                    } else if(k == k2){
                        curCount++;
                    }
                }
                maxCount = Math.max(maxCount, curCount);
            }
        }
        return maxCount;
    }
}
