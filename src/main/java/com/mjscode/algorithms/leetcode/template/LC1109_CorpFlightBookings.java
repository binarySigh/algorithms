package com.mjscode.algorithms.leetcode.template;

/**
 * //这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * //
 * // 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 fi
 * //rsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * //
 * // 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
 * //
 * // 示例 1：
 * //
 * //输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * //输出：[10,55,45,25,25]
 * //解释：
 * //航班编号        1   2   3   4   5
 * //预订记录 1 ：   10  10
 * //预订记录 2 ：       20  20
 * //预订记录 3 ：       25  25  25  25
 * //总座位数：      10  55  45  25  25
 * //因此，answer = [10,55,45,25,25]
 * //
 * // 示例 2：
 * //
 * //输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * //输出：[10,25]
 * //解释：
 * //航班编号        1   2
 * //预订记录 1 ：   10  10
 * //预订记录 2 ：       15
 * //总座位数：      10  25
 * //因此，answer = [10,25]
 * //
 * // 提示：
 * //
 * // 1 <= n <= 2 * 104
 * // 1 <= bookings.length <= 2 * 104
 * // bookings[i].length == 3
 * // 1 <= firsti <= lasti <= n
 * // 1 <= seatsi <= 104
 * //
 * // Related Topics 数组 前缀和
 * @author binarySigh
 * @date 2021/8/31 22:31
 */
public class LC1109_CorpFlightBookings {

    /**
     * 差分数组 + 前缀和
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        //sum 为差分数组
        int[] sum = new int[n];
        for(int[] b : bookings){
            int l = b[0] - 1, r = b[1], v = b[2];
            sum[l] += v;
            if(r < n) {
                sum[r] -= v;
            }
        }
        int[] arr = new int[n];
        arr[0] = sum[0];
        for(int i = 1; i < sum.length; i++){
            arr[i] = arr[i - 1] + sum[i];
        }
        return arr;
    }

    /**
     * 线段树方式
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBooking1(int[][] bookings, int n){
        SegmentTree st = new SegmentTree(n);
        for(int[] tmp : bookings){
            st.addSegment(tmp[0], tmp[1], tmp[2]);
        }
        return st.getArr();
    }

    public static class SegmentTree{
        //线段树数组
        private int[] tree;
        //原始数组最终结果
        private int[] arr;
        private int len;

        public SegmentTree(int n){
            this.arr = new int[n];
            this.len = getLen(n);
            this.tree = new int[len << 1];
        }

        public void addSegment(int l, int r, int val){
            add(1, len, l, r, val, 1);
        }

        public int[] getArr(){
            distribute(1, len, 0, 1);
            return this.arr;
        }

        private void distribute(int b, int e, int cur, int idx){
            if(idx >= (len << 1)){
                return;
            }
            this.tree[idx] += cur;
            if(b == e && b <= arr.length){
                this.arr[b - 1] = this.tree[idx];
                return;
            }
            int mid = b + ((e - b) >> 1);
            distribute(b, mid, this.tree[idx], idx << 1);
            distribute(mid + 1, e, this.tree[idx], idx << 1 | 1);
            this.tree[idx] = 0;
        }

        /**
         * @param b 当前格子表示的区间下界
         * @param e 当前格子表示的区间上界
         * @param l 当前需要添加的区间下界
         * @param r 当前需要添加的区间上界
         * @param cur 当前区间需要加的数
         * @param idx 当前格子在线段树中的下标位置
         */
        private void add(int b, int e, int l, int r, int cur, int idx){
            if(l <= b && r >= e){
                this.tree[idx] += cur;
                return;
            }
            int mid = b + ((e - b) >> 1);
            if(this.tree[idx] > 0) {
                //当前节点有缓存的任务，先将当前缓存的任务分发给子节点
                add(b, mid, b, mid, this.tree[idx], idx << 1);
                add(mid + 1, e, mid + 1, e, this.tree[idx], idx << 1 | 1);
                this.tree[idx] = 0;
            }
            if(l <= mid && r > mid){
                add(b, mid, l, mid, cur, idx << 1);
                add(mid + 1, e, mid + 1, r, cur, idx << 1 | 1);
            } else if(l > mid){
                add(mid + 1, e, l, r, cur, idx << 1 | 1);
            } else if(r <= mid){
                add(b, mid, l, r, cur, idx << 1);
            }
        }

        private int getLen(int n){
            n |= n >> 1;
            n |= n >> 2;
            n |= n >> 4;
            n |= n >> 8;
            n |= n >> 16;
            n++;
            return n;
        }
    }
}
