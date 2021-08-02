package com.mjscode.algorithms.leetcode.template;

import java.util.HashMap;
import java.util.Map;

/**
 * //有 n 个网络节点，标记为 1 到 n。
 * //
 * // 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
 * //i 是一个信号从源节点传递到目标节点的时间。
 * //
 * // 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * //
 * // 示例 1：
 * //
 * //输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * //输出：2
 * //
 * // 示例 2：
 * //
 * //输入：times = [[1,2,1]], n = 2, k = 1
 * //输出：1
 * //
 * // 示例 3：
 * //
 * //输入：times = [[1,2,1]], n = 2, k = 2
 * //输出：-1
 * //
 * // 提示：
 * //
 * // 1 <= k <= n <= 100
 * // 1 <= times.length <= 6000
 * // times[i].length == 3
 * // 1 <= ui, vi <= n
 * // ui != vi
 * // 0 <= wi <= 100
 * // 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列）
 * @author binarySigh
 * @date 2021/8/2 14:58
 */
public class LC0743_NetworkDelayTime {

    public static void main(String[] args){
        // --> 2
        /*int[][] times = {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        };
        int n = 4, k = 2;*/

        // --> 1
        /*int[][] times = {
                {1,2,1}
        };
        int n = 2, k = 1;*/

        // --> -1
        /*int[][] times = {
                {1,2,1}
        };
        int n = 2, k = 2;*/

        /*int[][] times = {
                {2,1,1},
                {2,3,1},
                {3,4,12},
                {3,5,7},
                {5,7,2},
                {5,6,7},
                {7,6,1},
                {6,4,1}
        };
        int n = 7, k = 2;*/

        // --> -1 : 4 个节点，但是却只出现了1，2，3三个结点的情况，因此4是不可到达的，应返回-1
        int[][] times = {
                {1,2,1},
                {2,3,7},
                {1,3,4},
                {2,1,2}
        };
        int n = 4, k = 1;
        System.out.println(networkDelayTime(times, n, k));
    }

    /**
     * dijkstra最小生成树算法思路
     * 解答成功:
     * 		执行耗时:24 ms,击败了36.54% 的Java用户
     * 		内存消耗:41.8 MB,击败了65.13% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        int ans = 0;
        // key -> 出发点
        // value ->
        //          key -> 目标点
        //          value -> 耗费距离
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        Heap heap = new Heap(n);
        //初始化边为map,方便查找；同时将所有点加进堆中，堆中每个节点初始化距离为最大值，意为不可达
        for(int[] tmp : times) {
            if(map.containsKey(tmp[0])){
                map.get(tmp[0]).put(tmp[1], tmp[2]);
            } else {
                HashMap<Integer, Integer> next = new HashMap<>();
                next.put(tmp[1], tmp[2]);
                map.put(tmp[0], next);
            }
            heap.add(tmp[0], Integer.MAX_VALUE);
            heap.add(tmp[1], Integer.MAX_VALUE);
        }
        //如果初始化结束后，堆中节点数小于给定节点数n，则必定有节点是无法到达的，因此可直接返回-1
        if(heap.size < n){
            return -1;
        }
        //设置源点距离为0，以源点为起点，做广度优先遍历
        heap.add(k, 0);
        while(heap.size() > 0) {
            Node cur = heap.poll();
            ans = Math.max(ans, cur.time);
            // curRecs 为当前点能到达的所有点的集合
            HashMap<Integer, Integer> curRecs = map.get(cur.origin);
            if(curRecs != null) {
                for (Map.Entry<Integer, Integer> entry : curRecs.entrySet()) {
                    //这个if是为了防止已经出堆的点重新进堆
                    if (heap.contains(entry.getKey())) {
                        heap.add(entry.getKey(), entry.getValue() + cur.time);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static class Node{
        int origin; //节点编号
        int time; // 源点到当前节点的距离
        public Node(){};
        public Node(int ori, int t){
            this.origin = ori;
            this.time = t;
        }
        public void setTime(int ti){
            this.time = ti;
        }
    }

    public static class Heap{
        Node[] heap;
        // key -> 节点编号；val -> 堆中位置
        HashMap<Integer, Integer> map;
        int cap;
        int size;
        public Heap(){}
        public Heap(int c){
            this.cap = c;
            this.heap = new Node[c];
            this.map = new HashMap<>(c);
        }

        public boolean contains(int des){
            return map.containsKey(des);
        }

        public void add(int des, int time){
            if(map.containsKey(des)){
                int idx = map.get(des);
                if(heap[idx].time > time){
                    heap[idx].setTime(time);
                    heapInsert(idx);
                    heapIfy(idx);
                }
            } else {
                Node node = new Node(des, time);
                heap[size] = node;
                map.put(des, size);
                heapInsert(size++);
            }
        }

        public Node poll() {
            Node node = heap[0];
            swap(0, --size);
            heapIfy(0);
            map.remove(node.origin);
            return node;
        }

        private void heapInsert(int idx){
            int father = (idx - 1) >> 1;
            while(father >= 0 && compare(heap[father], heap[idx]) > 0){
                swap(father, idx);
                idx = father;
                father = (idx - 1) >> 1;
            }
        }

        private void heapIfy(int idx){
            while(idx < size) {
                int leftSon = (idx << 1 | 1);
                int rightSon = (idx + 1) << 1;
                if (rightSon < size) {
                    int nextIdx = heap[leftSon].time < heap[rightSon].time ? leftSon : rightSon;
                    if(compare(heap[idx], heap[nextIdx]) > 0){
                        swap(idx, nextIdx);
                        idx = nextIdx;
                    }
                } else if(leftSon < size) {
                    if(compare(heap[idx], heap[leftSon]) > 0){
                        swap(idx, leftSon);
                        idx = leftSon;
                    }
                }
                if(idx < leftSon){
                    idx = rightSon;
                }
            }
        }

        private int compare(Node a, Node b){
            if(a.time == b.time){
                return a.origin - b.origin;
            } else {
                return a.time - b.time;
            }
        }

        public int size(){
            return this.size;
        }

        private void swap(int i, int j){
            Node tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
            map.put(heap[i].origin, i);
            map.put(heap[j].origin, j);
        }
    }
}
