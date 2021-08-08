package com.mjscode.algorithms.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * //存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 * //
 * // 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 * //
 * // 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 * //
 * //
 * // 示例 1：
 * //
 * //输入：graph = [[1,2,3],[0],[0],[0]]
 * //输出：4
 * //解释：一种可能的路径为 [1,0,2,0,3]
 * //
 * // 示例 2：
 * //
 * //输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * //输出：4
 * //解释：一种可能的路径为 [0,1,4,2,3]
 * //
 * // 提示：
 * //
 * // n == graph.length
 * // 1 <= n <= 12
 * // 0 <= graph[i].length < n
 * // graph[i] 不包含 i
 * // 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * // 输入的图总是连通图
 * //
 * // Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩
 * @author binarySigh
 * @date 2021/8/6 8:59
 */
public class LC0847_ShortestPathLength {
    public static void main(String[] args){
        // --> 4
        int[][] graph = {
                {1,2,3},
                {0},
                {0},
                {0}
        };

        // --> 4
        /*int[][] graph = {
                {1},
                {0,2,4},
                {1,3,4},
                {2},
                {1,2}
        };*/

        // [[1,2,3,4,5,6,7,8,9,10,11],[0,2,5,6,8],[0,1,4,5,6,9,10,11],[0,4,5,6,8,9,10,11],[0,2,3,5,6,8,10],[0,1,2,3,4,6,8,9,10,11],[0,1,2,3,4,5,8,10,11],[0,8],[0,1,3,4,5,6,7,9,10,11],[0,2,3,5,8,10],[0,2,3,4,5,6,8,9],[0,2,3,5,6,8]]
        //超时
        // --> 11
        /*int[][] graph = {
                {1,2,3,4,5,6,7,8,9,10,11},
                {0,2,5,6,8},
                {0,1,4,5,6,9,10,11},
                {0,4,5,6,8,9,10,11},
                {0,2,3,5,6,8,10},
                {0,1,2,3,4,6,8,9,10,11},
                {0,1,2,3,4,5,8,10,11},
                {0,8},
                {0,1,3,4,5,6,7,9,10,11},
                {0,2,3,5,8,10},
                {0,2,3,4,5,6,8,9},
                {0,2,3,5,6,8}
        };*/

        // --> 0
        //int[][] graph = {{}};

        System.out.println(shortestPathLength(graph));
    }

    /**
     * dfs + 记忆化
     * 解答成功:
     * 		执行耗时:247 ms,击败了5.42% 的Java用户
     * 		内存消耗:46.3 MB,击败了5.42% 的Java用户
     * @param graph
     * @return
     */
    public static int shortestPathLength(int[][] graph) {
        int ans = Integer.MAX_VALUE;
        int max = getMax(graph.length);
        HashSet<Integer> set = new HashSet<>();
        HashMap<String, Integer> dp = new HashMap<>();
        for(int i = 0; i < graph.length; i++) {
            ans = Math.min(ans, dfs(graph, max, i, 0, set, dp));
        }
        return ans == Integer.MAX_VALUE ? 0 : (ans - 1);
    }

    /**
     * 用整型表示一条边，低 14位表示边的终点；15位之前的表示边的起点
     * @param graph 图结构
     * @param max 所有节点都遍历对应的整型数字
     * @param idx 当前遍历到的节点编号
     * @param visited 标记已经遍历过的节点。遍历过的节点会将其下标对应二进制位上置为 1
     * @param set 在当前的 dfs 路径中已经遍历过的边
     * @param dp 记忆化表
     * @return
     */
    public static int dfs(int[][] graph, int max, int idx, int visited, HashSet<Integer> set, HashMap<String, Integer> dp){
        int preVisited = visited;
        visited |= (1 << idx);
        if(visited == max){
            dp.put(idx + "-" + preVisited, 1);
            return 1;
        }
        int ans = Integer.MAX_VALUE - 1;
        for(int i = 0; i < graph[idx].length; i++){
            int next = (idx << 15) | graph[idx][i];
            String key = graph[idx][i] + "-" + visited;
            if(dp.containsKey(key)){
                ans = Math.min(ans, dp.get(key));
                continue;
            }
            if(!set.contains(next)){
                set.add(next);
                ans = Math.min(ans, dfs(graph, max, graph[idx][i], visited, set, dp));
                set.remove(next);
            }
        }
        dp.put(idx + "-" + preVisited, ans + 1);
        return ans + 1;
    }

    public static int getMax(int len){
        int ret = 0;
        for(int i = 0; i < len; i++){
            ret |= (1 << i);
        }
        return ret;
    }
}
