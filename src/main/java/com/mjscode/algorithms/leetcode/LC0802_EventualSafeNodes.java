package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 * //
 * // 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 * //
 * // 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 * //
 * // 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
 * //满足 (i, j) 是图的一条有向边。
 * //
 * // 示例 1：
 * //
 * //输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * //输出：[2,4,5,6]
 * //解释：示意图如上。
 * //
 * // 示例 2：
 * //
 * //输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * //输出：[4]
 * //
 * // 提示：
 * //
 * // n == graph.length
 * // 1 <= n <= 104
 * // 0 <= graph[i].length <= n
 * // graph[i] 按严格递增顺序排列。
 * // 图中可能包含自环。
 * // 图中边的数目在范围 [1, 4 * 104] 内。
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
 * @author binarySigh
 * @date 2021/8/5 9:09
 */
public class LC0802_EventualSafeNodes {
    public static void main(String[] args){
        // --> [2,4,5,6]
        /*int[][] graph = {
                {1,2},
                {2,3},
                {5},
                {0},
                {5},
                {},
                {}
        };*/

        // --> [4]
        int[][] graph = {
                {1,2,3,4},
                {1,2},
                {3,4},
                {0,4},
                {}
        };
        List<Integer> ans = eventualSafeNodes(graph);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:4 ms,击败了100.00% 的Java用户
     * 		内存消耗:48.1 MB,击败了60.86% 的Java用户
     * @param graph
     * @return
     */
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int[] dfs = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++) {
            dfs(graph, dfs, visited, i);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < dfs.length; i++){
            if(dfs[i] == 1){
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * dfs查看当前分支是否能走到终点
     * @param graph
     * @param dfs 每个节点对应的情况：-1 表示一定在环中；0表示子节点未完全遍历；1表示该节点所有子节点都能走到终节点
     * @param visited 当前遍历的dfs分支里有哪些节点已经遍历过
     * @param node 当前来到的节点
     * @return
     */
    public static boolean dfs(int[][] graph, int[] dfs, boolean[] visited, int node){
        if(dfs[node] != 0){
            return dfs[node] == 1;
        }
        if(visited[node]){
            return false;
        }
        if(graph[node].length == 0){
            dfs[node] = 1;
            return true;
        }
        visited[node] = true;
        for(int i = 0; i < graph[node].length; i++){
            if(!dfs(graph, dfs, visited, graph[node][i])){
                visited[node] = false;
                dfs[node] = -1;
                return false;
            }
        }
        dfs[node] = 1;
        visited[node] = false;
        return true;
    }
}
