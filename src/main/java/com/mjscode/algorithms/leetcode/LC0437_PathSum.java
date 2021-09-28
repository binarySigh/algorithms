package com.mjscode.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * //给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * //
 * // 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * //
 * // 示例 1：
 * //
 * //输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * //输出：3
 * //解释：和等于 8 的路径有 3 条，如图所示。
 * //
 * // 示例 2：
 * //
 * //输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * //输出：3
 * //
 * // 提示:
 * //
 * // 二叉树的节点个数的范围是 [0,1000]
 * // -10⁹ <= Node.val <= 10⁹
 * // -1000 <= targetSum <= 1000
 * //
 * // Related Topics 树 深度优先搜索 二叉树
 * @author binarySigh
 * @date 2021/9/28 22:57
 */
public class LC0437_PathSum {

    public static int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        HashMap<Integer, Integer> next = new HashMap<>();
        next.put(0, 1);
        return f(root, targetSum, next);
    }

    public static int f(TreeNode root, int target, HashMap<Integer, Integer> map) {
        if(root.left == null && root.right == null) {
            return map.getOrDefault(target - root.val, 0);
        }
        int ans = map.getOrDefault(target - root.val, 0);
        HashMap<Integer, Integer> next = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            next.put(entry.getKey() + root.val, entry.getValue());
        }
        next.put(0, next.getOrDefault(0, 0) + 1);
        if(root.left != null){
            ans += f(root.left, target, next);
        }
        if(root.right != null){
            ans += f(root.right, target, next);
        }
        return ans;
    }

    public class TreeNode {
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
