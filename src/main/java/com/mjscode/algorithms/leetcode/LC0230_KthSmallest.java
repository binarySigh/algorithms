package com.mjscode.algorithms.leetcode;

/**
 * //给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * //
 * // 示例 1：
 * //
 * //输入：root = [3,1,4,null,2], k = 1
 * //输出：1
 * //
 * // 示例 2：
 * //
 * //输入：root = [5,3,6,2,4,null,null,1], k = 3
 * //输出：3
 * //
 * // 提示：
 * //
 * // 树中的节点数为 n 。
 * // 1 <= k <= n <= 104
 * // 0 <= Node.val <= 104
 * //
 * // 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * // Related Topics 树 深度优先搜索 二叉搜索树 二叉树
 * @author binarySigh
 * @date 2021/9/10 22:48
 */
public class LC0230_KthSmallest {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);

        int k = 1;
        System.out.println(kthSmallest(root, k));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.3 MB,击败了54.17% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        return find(root, k)[0];
    }

    public static int[] find(TreeNode root, int k){
        int[] cur = new int[]{-1, k};
        if(root.left == null && root.right == null){
            if(--cur[1] == 0){
                cur[0] = root.val;
            }
            return cur;
        }
        if(root.left != null){
            cur = find(root.left, k);
        }
        if(cur[0] >= 0){
            //答案在左子树中，直接返回
            return cur;
        }
        if(--cur[1] == 0){
            //是否在当前节点
            cur[0] = root.val;
            cur[1] = 0;
            return cur;
        }
        if(root.right != null){
            cur = find(root.right, cur[1]);
        }
        return cur;
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
