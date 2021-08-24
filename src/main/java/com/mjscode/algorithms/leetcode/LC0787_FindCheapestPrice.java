package com.mjscode.algorithms.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * //有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城
 * //市 fromi 开始，以价格 pricei 抵达 toi。
 * //
 * // 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便
 * //宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 * //
 * // 示例 1：
 * //
 * //输入:
 * //n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * //src = 0, dst = 2, k = 1
 * //输出: 200
 * //解释:
 * //城市航班图如下
 * //
 * //从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * //
 * // 示例 2：
 * //
 * //输入:
 * //n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * //src = 0, dst = 2, k = 0
 * //输出: 500
 * //解释:
 * //城市航班图如下
 * //
 * //从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * //
 * // 提示：
 * //
 * // 1 <= n <= 100
 * // 0 <= flights.length <= (n * (n - 1) / 2)
 * // flights[i].length == 3
 * // 0 <= fromi, toi < n
 * // fromi != toi
 * // 1 <= pricei <= 104
 * // 航班没有重复，且不存在自环
 * // 0 <= src, dst, k < n
 * // src != dst
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列）
 * @author binarySigh
 * @date 2021/8/24 19:53
 */
public class LC0787_FindCheapestPrice {

    /**
     * 动态规划
     * 执行结果： 通过<BR/>
     *      * 执行用时： 32 ms , 在所有 Java 提交中击败了 7.0% 的用户<BR/>
     *      * 内存消耗： 39 MB , 在所有 Java 提交中击败了 91.1% 的用户<BR/>
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){
        int[][] dp = new int[n][2];
        //初始化
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][j] = -1;
            }
        }
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>(n);
        for(int i = 0; i < flights.length; i++){
            if(map.containsKey(flights[i][0])){
                map.get(flights[i][0]).put(flights[i][1], flights[i][2]);
            } else {
                HashMap<Integer, Integer> tmp = new HashMap<>();
                tmp.put(flights[i][1], flights[i][2]);
                map.put(flights[i][0], tmp);
            }
        }
        int ans = -1;
        dp[src][0] = 0;
        int idx = 0;
        for(int j = 0; j <= k; j++ ){
            for(int i = 0; i < n; i++){
                if(dp[i][idx] == -1){
                    continue;
                }
                if(i == dst && dp[i][idx] > 0){
                    ans = ans == -1 ? dp[i][idx] : Math.min(ans, dp[i][idx]);
                }
                HashMap<Integer, Integer> tmp = map.get(i);
                if(tmp == null){
                    continue;
                }
                for(Map.Entry<Integer, Integer> entry : tmp.entrySet()){
                    int cost = entry.getValue() + dp[i][idx];
                    dp[entry.getKey()][idx ^ 1] = dp[entry.getKey()][idx ^ 1] == -1 ? cost :
                            Math.min(dp[entry.getKey()][idx ^ 1], cost);
                }
            }
            idx ^= 1;
        }
        //收集最后一层的答案
        if(dp[dst][idx] > 0){
            ans = ans == -1 ? dp[dst][idx] : Math.min(dp[dst][idx], ans);
        }
        return ans;
    }

    /**
     * 朴素bfs, 超时
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public static int compare(int n, int[][] flights, int src, int dst, int k){
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>(n);
        for(int i = 0; i < flights.length; i++){
            if(map.containsKey(flights[i][0])){
                map.get(flights[i][0]).put(flights[i][1], flights[i][2]);
            } else {
                HashMap<Integer, Integer> tmp = new HashMap<>();
                tmp.put(flights[i][1], flights[i][2]);
                map.put(flights[i][0], tmp);
            }
        }
        long ans = -1L;
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(new Node(src, 0));
        int len = 1;
        int nextLen = 0;
        while(!q.isEmpty()){
            if(k < -1){
                break;
            }
            Node cur = q.pollFirst();
            len--;
            if(cur.des == dst){
                ans = ans == -1 ? cur.price : Math.min(ans, cur.price);
            }
            HashMap<Integer, Integer> tmp = map.get(cur.des);
            if(tmp != null){
                for(Map.Entry<Integer, Integer> entry : tmp.entrySet()){
                    q.addLast(new Node(entry.getKey(), entry.getValue() + cur.price));
                    nextLen++;
                }
            }
            if(len == 0){
                len = nextLen;
                nextLen = 0;
                k--;
            }
        }
        return (int)ans;
    }

    public static class Node{
        int des;
        int price;
        public Node(int d, int p){
            this.des = d;
            this.price = p;
        }
    }
}
