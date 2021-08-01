package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * //给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * //
 * // 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的
 * //根结点位于 (0, 0) 。
 * //
 * // 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则
 * //按结点的值从小到大进行排序。
 * //
 * // 返回二叉树的 垂序遍历 序列。
 * //
 * // 示例 1：
 * //
 * //输入：root = [3,9,20,null,null,15,7]
 * //输出：[[9],[3,15],[20],[7]]
 * //解释：
 * //列 -1 ：只有结点 9 在此列中。
 * //列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * //列  1 ：只有结点 20 在此列中。
 * //列  2 ：只有结点 7 在此列中。
 * //
 * // 示例 2：
 * //
 * //输入：root = [1,2,3,4,5,6,7]
 * //输出：[[4],[2],[1,5,6],[3],[7]]
 * //解释：
 * //列 -2 ：只有结点 4 在此列中。
 * //列 -1 ：只有结点 2 在此列中。
 * //列  0 ：结点 1 、5 和 6 都在此列中。
 * //          1 在上面，所以它出现在前面。
 * //          5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * //列  1 ：只有结点 3 在此列中。
 * //列  2 ：只有结点 7 在此列中。
 * //
 * // 示例 3：
 * //
 * //输入：root = [1,2,3,4,6,5,7]
 * //输出：[[4],[2],[1,5,6],[3],[7]]
 * //解释：
 * //这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * //因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 * //
 * // 提示：
 * //
 * // 树中结点数目总数在范围 [1, 1000] 内
 * // 0 <= Node.val <= 1000
 * //
 * // Related Topics 树 深度优先搜索 广度优先搜索 哈希表 二叉树
 * @author binarySigh
 * @date 2021/7/31 10:35
 */
public class LC0987_VerticalTraversal {

    public static void main(String[] args){
        // --> [[9], [3, 15], [20], [7]]
        /*TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);*/

        // --> [[4], [2], [1, 5, 6], [3], [7]]
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);*/

        // --> [[4], [2], [1, 5, 6], [3], [7]]
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);*/

        // --> [[0],[1],[3,2,2],[4]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        List<List<Integer>> ans = verticalTraversal(root);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了6.16% 的Java用户
     * 		内存消耗:38.8 MB,击败了22.29% 的Java用户
     * @param root
     * @return
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<List<Integer>>> col = new ArrayList<>(2000);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < 2000; i++){
            col.add(null);
        }
        process(root, 0, 0, col);
        for(List<List<Integer>> list : col) {
            if(list != null){
                List<Integer> curAns = new ArrayList<>();
                for(List<Integer> row : list){
                    if(row != null) {
                        if (row.size() > 1) {
                            Collections.sort(row);
                        }
                        curAns.addAll(row);
                    }
                }
                ans.add(curAns);
            }
        }
        return ans;
    }

    public static void process(TreeNode root, int rolIdx, int colIdx, List<List<List<Integer>>> col){
        if(root == null){
            return;
        }
        // 列结果设置
        int idx = colIdx + 999;
        if(col.get(idx) == null) {
            List<List<Integer>> curCol = new ArrayList<>(1000);
            for(int i = 0; i < 1000; i++){
                curCol.add(null);
            }
            col.set(idx, curCol);
        }
        // 行结果设置
        if(col.get(idx).get(rolIdx) != null){
            col.get(idx).get(rolIdx).add(root.val);
        } else {
            List<Integer> curList = new ArrayList<>();
            curList.add(root.val);
            col.get(idx).set(rolIdx, curList);
        }
        process(root.left, rolIdx + 1, colIdx - 1, col);
        process(root.right, rolIdx + 1, colIdx + 1, col);
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
   }
}
