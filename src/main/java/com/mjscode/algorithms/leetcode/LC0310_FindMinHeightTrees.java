package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * //
 * // 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中
 * // edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * //
 * // 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度
 * //树 。
 * //
 * // 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * //树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 * //
 * // 示例 1：
 * //
 * //输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * //输出：[1]
 * //解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 * //
 * // 示例 2：
 * //
 * //输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * //输出：[3,4]
 * //
 * // 示例 3：
 * //
 * //输入：n = 1, edges = []
 * //输出：[0]
 * //
 * // 示例 4：
 * //
 * //输入：n = 2, edges = [[0,1]]
 * //输出：[0,1]
 * //
 * // 提示：
 * //
 * // 1 <= n <= 2 * 10⁴
 * // edges.length == n - 1
 * // 0 <= ai, bi < n
 * // ai != bi
 * // 所有 (ai, bi) 互不相同
 * // 给定的输入 保证 是一棵树，并且 不会有重复的边
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
 * @author binarySigh
 * @date 2021/10/8 19:58
 */
public class LC0310_FindMinHeightTrees {

    public static void main(String[] args) {
        // --> [1]
  /*int n = 4;
  int[][] edges = {
    {1,0},
    {1,2},
    {1,3}
  };*/

        // --> [3, 4]
  /*int n = 6;
  int[][] edges = {
    {3,0},
    {3,1},
    {3,2},
    {3,4},
    {5,4}
  };*/

        // --> [0]
  /*int n = 1;
  int[][] edges = {};*/

        // --> [0, 1]
        int n = 2;
        int[][] edges = {{0,1}};
        List<Integer> ans = findMinHeightTrees(n, edges);
        System.out.println(ans);
    }

    /**
     * 队列 + 拓补排序，实现自底向顶的层序遍历，最后一层的所有节点均是可做最小高度树的头结点
     * 注意：过程中并不像传统拓补排序一样查找入度为0的节点入队，而是找入度为1的节点入队
     * @param n
     * @param edges
     * @return
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if(n == 1){
            ans.add(0);
            return ans;
        }
        // 无向图在拓补过程中任一时刻的出入度相同，因此这里只保留 入度表/出度表 即可
        HashSet<Integer>[] nodes = new HashSet[n];
        for(int i = 0; i < n; i++){
            nodes[i] = new HashSet<>();
        }
        for(int[] tmp : edges) {
            nodes[tmp[0]].add(tmp[1]);
            nodes[tmp[1]].add(tmp[0]);
        }
        // 队列去重
        boolean[] used = new boolean[n];
        // 队列
        LinkedList<Integer> q = new LinkedList<>();
        int curLen = 0;
        int nextLen = 0;
        // 查找第一批拓补排序起始节点
        for(int i = 0; i < n; i++){
            if(nodes[i].size() == 1 && !used[i]){
                curLen++;
                q.addFirst(i);
                used[i] = true;
            }
        }
        // 拓补排序 + 层序遍历经典过程
        while(!q.isEmpty()) {
            curLen--;
            int cur = q.pollLast();
            ans.add(cur);
            Iterator<Integer> ite = nodes[cur].iterator();
            while(ite.hasNext()) {
                int next = ite.next();
                nodes[next].remove(cur);
                if(nodes[next].size() == 1 && !used[next]){
                    used[next] = true;
                    q.addFirst(next);
                    nextLen++;
                }
            }
            if(curLen == 0 && nextLen > 0){
                curLen = nextLen;
                nextLen = 0;
                ans.clear();
            }
        }
        return ans;
    }

}
