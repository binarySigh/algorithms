package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * //给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * //
 * // 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1
 * //-> ... 这样的车站路线行驶。
 * //
 * // 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * //
 * // 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * //
 * // 示例 1：
 * //
 * //输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * //输出：2
 * //解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * //
 * // 示例 2：
 * //
 * //输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * //输出：-1
 * //
 * // 提示：
 * //
 * // 1 <= routes.length <= 500.
 * // 1 <= routes[i].length <= 105
 * // routes[i] 中的所有值 互不相同
 * // sum(routes[i].length) <= 105
 * // 0 <= routes[i][j] < 106
 * // 0 <= source, target < 106
 * //
 * // Related Topics 广度优先搜索 数组 哈希表
 * @author binarySigh
 * @date 2021/6/28 20:44
 */
public class LC0815_NumBusesToDestination {
    public static void main(String[] args){
        // -> -1
        /*int[][] routes = {
                {7,12},
                {4,5,15},
                {6},
                {15,19},
                {9,12,13}
        };
        int source = 15;
        int target = 12;*/

        // -> 2
        /*int[][] routes = {
                {1,2,7},
                {3,6,7},
        };
        int source = 1;
        int target = 6;*/

        int[][] routes = {
                {7,12},
                {4,5,15},
                {6},
                {15,19},
                {9,12,13}
        };
        int source = 15;
        int target = 12;
        System.out.println(numBusesToDestination(routes, source, target));
    }

    /**
     * 解答成功:
     * 		执行耗时:38 ms,击败了89.62% 的Java用户
     * 		内存消耗:60.7 MB,击败了55.20% 的Java用户
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target){
            return 0;
        }
        // 公交车数/总线路数
        int busNum = routes.length;
        // key -> 站点编号
        // value -> 拥有该站点的所有线路编号
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < busNum; i++){
            for(int stop : routes[i]){
                if(map.containsKey(stop)){
                    map.get(stop).add(i);
                } else {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    map.put(stop, tmp);
                }
            }
        }
        if(!map.containsKey(source) || !map.containsKey(target)){
            return -1;
        }
        // set -> 去重站点集合
        HashSet<Integer> set = new HashSet<>();
        // taken -> 去重线路集合，防止宽度优先遍历过程中重复添加线路
        boolean[] taken = new boolean[busNum];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addFirst(source);
        set.add(source);
        int nextEnds = source;
        int curEnds = source;
        int level = 0;
        // bfs
        while(!queue.isEmpty()){
            int curStop = queue.pollLast();
            if(curStop == target){
                return level;
            }
            ArrayList<Integer> buses = map.get(curStop);
            for(Integer line : buses){
                if(!taken[line]){
                    for(int stop : routes[line]){
                        if(!set.contains(stop)){
                            queue.addFirst(stop);
                            nextEnds = stop;
                            set.add(stop);
                        }
                    }
                    taken[line] = true;
                }
            }
            if(curStop == curEnds){
                curEnds = nextEnds;
                level++;
            }
        }
        return -1;
    }
}
