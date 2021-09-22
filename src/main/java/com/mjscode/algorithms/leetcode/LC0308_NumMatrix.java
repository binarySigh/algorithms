package com.mjscode.algorithms.leetcode;

/**
 * //给你一个二维矩阵 matrix ，你需要处理下面两种类型的若干次查询：
 * //
 * // 更新：更新 matrix 中某个单元的值。
 * // 求和：计算矩阵 matrix 中某一矩形区域元素的 和 ，该区域由 左上角 (row1, col1) 和 右下角 (row2, col2) 界定。
 * //
 * // 实现 NumMatrix 类：
 * //
 * // NumMatrix(int[][] matrix) 用整数矩阵 matrix 初始化对象。
 * // void update(int row, int col, int val) 更新 matrix[row][col] 的值到 val 。
 * // int sumRegion(int row1, int col1, int row2, int col2) 返回矩阵 matrix 中指定矩形区域元素的
 * //和 ，该区域由 左上角 (row1, col1) 和 右下角 (row2, col2) 界定。
 * //
 * // 示例：
 * //
 * //输入
 * //["NumMatrix", "sumRegion", "update", "sumRegion"]
 * //[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0,
 * //3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * //输出
 * //[null, 8, null, 10]
 * //
 * //解释
 * //NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2,
 * //0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * //numMatrix.sumRegion(2, 1, 4, 3); // 返回 8 (即, 左侧红色矩形的和)
 * //numMatrix.update(3, 2, 2);       // 矩阵从左图变为右图
 * //numMatrix.sumRegion(2, 1, 4, 3); // 返回 10 (即，右侧红色矩形的和)
 * //
 * // 提示：
 * //
 * // m == matrix.length
 * // n == matrix[i].length
 * // 1 <= m, n <= 200
 * // -10⁵ <= matrix[i][j] <= 10⁵
 * // 0 <= row < m
 * // 0 <= col < n
 * // -10⁵ <= val <= 10⁵
 * // 0 <= row1 <= row2 < m
 * // 0 <= col1 <= col2 < n
 * // 最多调用10⁴ 次 sumRegion 和 update 方法
 * //
 * // Related Topics 设计 树状数组 线段树 数组 矩阵
 * @author binarySigh
 * @date 2021/9/22 20:52
 */
public class LC0308_NumMatrix {
    private SegmentTree[] st;
    private int singleLen;

    public LC0308_NumMatrix(int[][] matrix){
        this.st = new SegmentTree[matrix.length];
        this.singleLen = getLen(matrix[0].length);
        for(int i = 0; i < matrix.length; i++){
            this.st[i] = new SegmentTree(matrix[i], this.singleLen);
        }
    }

    public void update(int row, int col, int val) {
        this.st[row].update(col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = 0;
        col1++;
        col2++;
        for(int i = row1; i <= row2; i++){
            ans += this.st[i].query(1, this.singleLen, col1, col2, 1);
        }
        return ans;
    }

    private int getLen(int n){
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return ++n;
    }

    public static class SegmentTree {
        private int len;
        private int[] sum;

        public SegmentTree(int[] nums, int n){
            this.len = n;
            this.sum = new int[this.len << 1];
            for(int i = 0; i < nums.length; i++){
                this.sum[i + len] = nums[i];
            }
            for(int i = this.len - 1; i >= 0; i--){
                this.sum[i] = this.sum[i << 1] + this.sum[i << 1 | 1];
            }
        }

        public void update(int n, int val){
            int i = this.len + n;
            this.sum[i] = val;
            while(i > 1) {
                int f = ((i & 1) == 0) ? (i >> 1) : ((i - 1) >> 1);
                this.sum[f] = this.sum[f << 1] + this.sum[f << 1 | 1];
                i = f;
            }
        }

        public int query(int b, int e, int i, int j, int idx) {
            if(b >= i && e <= j){
                return this.sum[idx];
            }
            int mid = b + ((e - b) >> 1);
            int ans = 0;
            if(i <= mid){
                ans = query(b, mid, i, j, idx << 1);
            }
            if(j >= mid + 1){
                ans += query(mid + 1, e, i, j, idx << 1 | 1);
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        };

        LC0308_NumMatrix nm = new LC0308_NumMatrix(matrix);
        System.out.println(nm.sumRegion(2, 1, 4, 3));
        nm.update(3, 2, 2);
        System.out.println(nm.sumRegion(2, 1, 4, 3));
    }

}
