package com.mjscode.algorithms.leetcode;

/**
 * //在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
 * //房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * //
 * // 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * //
 * // 示例 1:
 * //
 * // 输入: [3,2,3,null,3,null,1]
 * //
 * //     3
 * //    / \
 * //   2   3
 * //    \   \
 * //     3   1
 * //
 * //输出: 7
 * //解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * //
 * // 示例 2:
 * //
 * // 输入: [3,4,5,1,3,null,1]
 * //
 * //     3
 * //    / \
 * //   4   5
 * //  / \   \
 * // 1   3   1
 * //
 * //输出: 9
 * //解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * //
 * // Related Topics 树 深度优先搜索
 * @author binarySigh
 */
public class LC0337_Rob {

    /**
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:1 ms,击败了78.00% 的Java用户
     * 		内存消耗:37.9 MB,击败了88.56% 的Java用户
     * @param root
     * @return
     */
    public static int rob(TreeNode root){
        if(root == null){
            return 0;
        }
        int[] rob = getRob(root);
        return Math.max(rob[1], rob[0]);
    }

    /**
     * 递归
     * @param root 当前节点
     * @return a[0]-包含当前节点元素的最大值；a[1]-不包含当前节点元素的最大值
     */
    public static int[] getRob(TreeNode root){
        int[] rob = new int[2];
        if(root.left == null && root.right == null){
            rob[0] = root.val;
            return rob;
        }
        int[] left = new int[2];
        int[] right = new int[2];
        if(root.left != null) {
            left = getRob(root.left);
        }
        if(root.right != null) {
            right = getRob(root.right);
        }
        int max = 0;
        //计算不包含当前节点的情况下的最大值
        max = Math.max(left[0] + right[0], left[0] + right[1]);
        max = Math.max(max, left[1] + right[0]);
        max = Math.max(max, left[1] + right[1]);
        rob[1] = max;
        //计算包含当前结点的情况下的最大值
        max = Math.max(0, left[1] + right[1]) + root.val;
        rob[0] = max;
        return rob;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
