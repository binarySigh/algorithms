package com.mjscode.algorithms.leetcode;

import java.util.List;

/**
 * //给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * //
 * // 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * //
 * // 例如，给定三角形：
 * //
 * // [
 * //     [2],
 * //    [3,4],
 * //   [6,5,7],
 * //  [4,1,8,3]
 * //]
 * //
 * // 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * //
 * // 说明：
 * //
 * // 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * // Related Topics 数组 动态规划
 * @author binarySigh
 */
public class LC0120_MinimumTotal {

    /**
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:6 ms,击败了15.17% 的Java用户
     * 		内存消耗:38.5 MB,击败了55.02% 的Java用户
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0
                || triangle.get(0) == null || triangle.get(0).size() == 0){
            return 0;
        }
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        //申请dp数组，数组长度为三角形底边边长，也就是进阶要求里的 O(n) 的额外空间（n 为三角形的总行数）
        int[] sum = new int[triangle.get(triangle.size() - 1).size()];
        //从最底下一层开始填
        for(int i = triangle.size() - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                if(i == triangle.size() - 1){
                    //如果是最底下那层，直接把最底下一层依次填进数组就行
                    sum[j] = triangle.get(i).get(j);
                } else {
                    sum[j] = triangle.get(i).get(j) + Math.min(sum[j], sum[j + 1]);
                }
            }
        }
        return sum[0];
    }

    /**
     * 方案一的优化版，leetcode Accept<BR/>
     *      优化点：空间节省了一个数组元素的长度<BR/>
     *              时间上减少了最后一层的遍历耗时<BR/>
     * 解答成功:<BR/>
     * 		执行耗时:4 ms,击败了33.46% 的Java用户<BR/>
     * 		内存消耗:38.6 MB,击败了39.89% 的Java用户<BR/>
     * @param triangle
     * @return
     */
    public int minimumTotalPro(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0
                || triangle.get(0) == null || triangle.get(0).size() == 0){
            return 0;
        }
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        //申请dp数组，数组长度为三角形倒数第二底边的边长，也就是进阶要求里的 O(n) 的额外空间（n 为三角形的总行数）
        int[] sum = new int[triangle.get(triangle.size() - 1).size() - 1];
        //从倒数第二层开始填
        for(int i = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                if(i == triangle.size() - 2){
                    //如果是倒数第二层，则根据倒数第一层决定
                    sum[j] = triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                } else {
                    sum[j] = triangle.get(i).get(j) + Math.min(sum[j], sum[j + 1]);
                }
            }
        }
        return sum[0];
    }
}
