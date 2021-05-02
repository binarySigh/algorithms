package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * //你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 * //
 * // 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没
 * //有穿过一块砖的。
 * //
 * // 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的
 * //砖块数量最少 ，并且返回 穿过的砖块数量 。
 * //
 * // 示例 1：
 * //
 * //输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * //输出：2
 * //
 * // 示例 2：
 * //
 * //输入：wall = [[1],[1],[1]]
 * //输出：3
 * //
 * // 提示：
 * //
 * // n == wall.length
 * // 1 <= n <= 104
 * // 1 <= wall[i].length <= 104
 * // 1 <= sum(wall[i].length) <= 2 * 104
 * // 对于每一行 i ，sum(wall[i]) 应当是相同的
 * // 1 <= wall[i][j] <= 231 - 1
 * //
 * // Related Topics 哈希表
 * @author binarySigh
 * @date 2021/5/2 10:41
 */
public class LC0554_LeastBricks {
    public static void main(String[] args){
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer> bricks = new ArrayList<>();
        bricks.add(1);
        bricks.add(2);
        bricks.add(2);
        bricks.add(1);
        wall.add(bricks);
        bricks = new ArrayList<>();
        bricks.add(3);
        bricks.add(1);
        bricks.add(2);
        wall.add(bricks);
        bricks = new ArrayList<>();
        bricks.add(1);
        bricks.add(3);
        bricks.add(2);
        wall.add(bricks);
        bricks = new ArrayList<>();
        bricks.add(2);
        bricks.add(4);
        wall.add(bricks);
        bricks = new ArrayList<>();
        bricks.add(3);
        bricks.add(1);
        bricks.add(2);
        wall.add(bricks);
        bricks = new ArrayList<>();
        bricks.add(1);
        bricks.add(3);
        bricks.add(1);
        bricks.add(1);
        wall.add(bricks);
        System.out.println(leastBricks(wall));
    }

    /**
     * 如果上下两层有砖块可以在某个位置被竖线穿过，那么说明上下两个数组在这个位置的前缀和对不上
     * 例：[1,2,2,3] -> sum :[1,3,5,8]
     *     [2,1,1,4] -> sum :[2,3,4,8]
     *    上面的数组包含前缀和 3，下层也包含这个前缀和。那么只要从第一层第二块转右边界往下切割，穿过的砖块数是最少的
     *          为：wall.size - sum(3).size
     *
     * 解答成功:
     * 		执行耗时:14 ms,击败了83.74% 的Java用户
     * 		内存消耗:40.9 MB,击败了91.79% 的Java用户
     * @param wall
     * @return
     */
    public static int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int rowSum = 0;
        for(Integer i : wall.get(0)){
            rowSum += i;
        }
        int curSum = 0;
        int maxOver = 0;
        for(List<Integer> list : wall){
            for(Integer i : list){
                curSum += i;
                if(curSum < rowSum) {
                    int preOver = map.getOrDefault(curSum, 0);
                    map.put(curSum, preOver + 1);
                    maxOver = Math.max(maxOver, preOver + 1);
                }
            }
            curSum = 0;
        }
        return wall.size() - maxOver;
    }
}
