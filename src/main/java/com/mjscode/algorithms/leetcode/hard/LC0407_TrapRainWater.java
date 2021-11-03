package com.mjscode.algorithms.leetcode.hard;

import java.util.PriorityQueue;

/**
 * //给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * //
 * // 示例 1:
 * //
 * //输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * //输出: 4
 * //解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * //
 * // 示例 2:
 * //
 * //输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * //输出: 10
 * //
 * // 提示:
 * //
 * // m == heightMap.length
 * // n == heightMap[i].length
 * // 1 <= m, n <= 200
 * // 0 <= heightMap[i][j] <= 2 * 10⁴
 * //
 * // Related Topics 广度优先搜索 数组 矩阵 堆（优先队列）
 * @author binarySigh
 * @date 2021/11/3 20:56
 */
public class LC0407_TrapRainWater {
    public static void main(String[] args) {
        // --> 4
//        int[][] heightMap = {
//                {1,4,3,1,3,2},
//                {3,2,1,3,2,4},
//                {2,3,3,2,3,1}
//        };

        // --> 10
        int[][] heightMap = {
                {3,3,3,3,3},
                {3,2,2,2,3},
                {3,2,1,2,3},
                {3,2,2,2,3},
                {3,3,3,3,3}
        };
        System.out.println(trapRainWater(heightMap));
    }

    /**
     * 解答成功:
     * 		执行耗时:1701 ms,击败了5.06% 的Java用户
     * 		内存消耗:171.2 MB,击败了5.06% 的Java用户
     * @param heightMap
     * @return
     */
    public static int trapRainWater(int[][] heightMap) {
        int M = heightMap.length, N = heightMap[0].length;
        PriorityQueue<Node> heap = new PriorityQueue<>(M * N, (a, b) -> a.height - b.height);
        int[][] visited = new int[M][N];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                heap.add(new Node(heightMap[i][j], i, j));
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        int ans = 0;
        int steps = 0;
        while(!heap.isEmpty()) {
            Node cur = heap.poll();
            if(visited[cur.x][cur.y] <= steps) {
               continue;
            }
            int[] tmp = bfs(heightMap[cur.x][cur.y], cur.x, cur.y, visited, heightMap, steps++);
            ans += tmp[0] * (tmp[1] <= heightMap[cur.x][cur.y] ? 0 : (tmp[1] - heightMap[cur.x][cur.y]));
        }
        return ans;
    }

    public static int[] bfs(int pre, int x, int y, int[][] visited, int[][] heightMap, int steps) {
        if(x >= heightMap.length || x < 0 ||
            y >= heightMap[0].length || y < 0) {
            return new int[]{0, 0};
        }
        if(heightMap[x][y] > pre) {
            return new int[]{0, heightMap[x][y]};
        }
        if(visited[x][y] == steps) {
            return new int[]{0, Integer.MAX_VALUE};
        }
        visited[x][y] = steps;
        int ans = 1, height = Integer.MAX_VALUE;
        int[] right = bfs(pre, x + 1, y, visited, heightMap, steps);
        int[] up = bfs(pre, x, y - 1, visited, heightMap, steps);
        int[] left = bfs(pre, x - 1, y, visited, heightMap, steps);
        int[] down = bfs(pre, x, y + 1, visited, heightMap, steps);
        ans += right[0] + up[0] + left[0] + down[0];
        height = Math.min(Math.min(right[1], left[1]), Math.min(up[1], down[1]));
        return new int[]{ans, height};
    }

    public static class Node {
        int height;
        int x;
        int y;
        public Node(int h, int a, int b) {
            this.height = h;
            this.x = a;
            this.y = b;
        }
    }
}
