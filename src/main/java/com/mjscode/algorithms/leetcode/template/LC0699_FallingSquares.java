package com.mjscode.algorithms.leetcode.template;

import java.util.*;

/**
 *
 * //在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
 * //
 * // 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(posit
 * //ions[i][0])，side_length 表示该方块的边长(positions[i][1])。
 * //
 * // 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * //
 * // 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * //
 * // 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positio
 * //ns[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
 * //
 * // 示例 1:
 * //
 * // 输入: [[1, 2], [2, 3], [6, 1]]
 * //输出: [2, 5, 5]
 * //解释:
 * //
 * //第一个方块 positions[0] = [1, 2] 掉落：
 * //_aa
 * //_aa
 * //-------
 * //方块最大高度为 2 。
 * //
 * //第二个方块 positions[1] = [2, 3] 掉落：
 * //__aaa
 * //__aaa
 * //__aaa
 * //_aa__
 * //_aa__
 * //--------------
 * //方块最大高度为5。
 * //大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
 * //
 * //第三个方块 positions[1] = [6, 1] 掉落：
 * //__aaa
 * //__aaa
 * //__aaa
 * //_aa
 * //_aa___a
 * //--------------
 * //方块最大高度为5。
 * //
 * //因此，我们返回结果[2, 5, 5]。
 * //
 * // 示例 2:
 * //
 * // 输入: [[100, 100], [200, 100]]
 * //输出: [100, 100]
 * //解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 * //
 * // 注意:
 * //
 * // 1 <= positions.length <= 1000.
 * // 1 <= positions[i][0] <= 10^8.
 * // 1 <= positions[i][1] <= 10^6.
 * //
 * // Related Topics 线段树 数组 有序集合
 * @author binarySigh
 * @date 2021/9/15 20:57
 */
public class LC0699_FallingSquares {

    public static void main(String[] args) {
        // --> [2, 5, 5]
  /*int[][] pos = {
    {1,2},
    {2,3},
    {6,1}
  };*/

        // --> [100, 100]
  /*int[][] pos = {
    {100,100},
    {200,100}
  };*/

        // --> [7,16,17]
  /*int[][] pos = {
    {9,7},
    {1,9},
    {3,1}
  };*/

        // --> [2,9,9,17,21]
        int[][] pos = {
                {7,2},
                {1,7},
                {9,5},
                {1,8},
                {3,4}
        };


        List<Integer> ans = fallingSquares(pos);

        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:15 ms,击败了90.6% 的Java用户
     * 		内存消耗:39.5 MB,击败了20.3% 的Java用户
     * @param positions
     * @return
     */
    public static List<Integer> fallingSquares(int[][] positions) {
        // 对 position 进行离散化映射，方便构建线段树
        TreeSet<Integer> tree = new TreeSet<>();
        for(int[] tmp : positions){
            tree.add(tmp[0]);
            tree.add(tmp[0] + tmp[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>(tree.size());
        Iterator<Integer> ite = tree.iterator();
        int idx = 1;
        while(ite.hasNext()){
            map.put(ite.next(), idx++);
        }
        //添加线段树，收集答案
        List<Integer> ans = new ArrayList<>(positions.length);
        SegmentTree st = new SegmentTree(map.size());
        for(int[] tmp : positions){
            int l = map.get(tmp[0]);
            int r = map.get(tmp[0] + tmp[1] - 1);
            st.add(l, r, tmp[1]);
            ans.add(st.queryMax());
        }
        return ans;
    }

    public static class SegmentTree {
        int len;
        int[] lazy;
        int[] query;
        public SegmentTree(int n) {
            this.len = getLen(n);
            this.lazy = new int[this.len << 1];
            this.query = new int[this.len << 1];
        }

        public void add(int l, int r, int val){
            int max = query(1, len, l, r, 1);
            addSeg(1, len, l, r, 1, val + max);
        }

        private void addSeg(int b, int e, int l, int r, int idx, int val) {
            if(b >= l && e <= r){
                this.lazy[idx] = val;
                this.query[idx] = val;
                return;
            }
            int mid = b + ((e - b) >> 1);
            //向左右子节点分发当前的新任务
            if(l <= mid){
                addSeg(b, mid, l, r, idx << 1, val);
            }
            if(r >= mid + 1){
                addSeg(mid + 1, e, l, r, idx << 1 | 1, val);
            }
            //同时更新query
            this.query[idx] = Math.max(this.query[idx << 1], this.query[idx << 1 | 1]);
        }

        public int query(int b, int e, int l, int r, int idx){
            if(b >= l && e <= r){
                return this.query[idx];
            }
            int mid = b + ((e - b) >> 1);
            pushDown(idx);
            int max = 0;
            if(l <= mid){
                max = query(b, mid, l, r, idx << 1);
            }
            if(r >= mid + 1){
                max = Math.max(max, query(mid + 1, e, l, r, idx << 1 | 1));
            }
            return max;
        }

        public int queryMax(){
            return this.query[1];
        }

        /**
         * @param idx
         */
        private void pushDown(int idx) {
            if(this.lazy[idx] == 0){
                return;
            }
            if((idx << 1) < this.lazy.length){
                this.lazy[idx << 1] = this.lazy[idx];
                this.query[idx << 1] = this.lazy[idx];
            }
            if((idx << 1 | 1) < this.lazy.length){
                this.lazy[idx << 1 | 1] = this.lazy[idx];
                this.query[idx << 1 | 1] = this.lazy[idx];
            }
            this.lazy[idx] = 0;
        }

        private int getLen(int n){
            n |= n >> 1;
            n |= n >> 2;
            n |= n >> 4;
            n |= n >> 8;
            n |= n >> 16;
            return ++n;
        }
    }

}
