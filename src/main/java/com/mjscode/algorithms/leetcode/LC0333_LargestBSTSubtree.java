package com.mjscode.algorithms.leetcode;

/**
 * //给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。
 * //
 * // 二叉搜索树（BST）中的所有节点都具备以下属性：
 * //
 * // 左子树的值小于其父（根）节点的值。
 * //
 * // 右子树的值大于其父（根）节点的值。
 * //
 * // 注意:
 * //
 * // 子树必须包含其所有后代。
 * //
 * // 示例 1：
 * //
 * //输入：root = [10,5,15,1,8,null,7]
 * //输出：3
 * //解释：本例中最大的 BST 子树是高亮显示的子树。返回值是子树的大小，即 3 。
 * //
 * // 示例 2：
 * //
 * //输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * //输出：2
 * //
 * // 提示：
 * //
 * // 树上节点数目的范围是 [0, 10⁴]
 * // -10⁴ <= Node.val <= 10⁴
 * //
 * // 进阶: 你能想出 O(n) 时间复杂度的解法吗？
 * // Related Topics 树 深度优先搜索 二叉搜索树 动态规划 二叉树
 * @author binarySigh
 * @date 2021/10/8 19:57
 */
public class LC0333_LargestBSTSubtree {

    public static void main(String[] args) {
        // --> 3
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(7);

        //TreeNode root = null;

        System.out.println(largestBSTSubtree(root));
    }

    public static int largestBSTSubtree(TreeNode root) {
        return root == null ? 0 : find(root)[1];
    }

    /**
     * 返回值数组释义：
     * [0] -> 本子树是否是BST树。0-是；1-否
     * [1] -> 本子树中发现的最大BST子树节点数
     * [2] -> 本子树中发现的最大BST子树最小节点元素
     * [3] -> 本子树中发现的最大BST子树最大节点元素
     * @param root
     * @return
     */
    public static int[] find(TreeNode root) {
        if(root.left == null && root.right == null){
            return new int[]{0, 1, root.val, root.val};
        }
        int[] leftInfo = null;
        int[] rightInfo = null;
        if(root.left != null) {
            leftInfo = find(root.left);
        }
        if(root.right != null) {
            rightInfo = find(root.right);
        }
        // 左树为空
        if(root.left == null) {
            if(rightInfo[0] == 0 && rightInfo[2] > root.val) {
                return new int[]{0, rightInfo[1] + 1, root.val, rightInfo[3]};
            } else {
                return new int[]{1, rightInfo[1], 0, 0};
            }
        }
        // 右树为空
        if(root.right == null) {
            if(leftInfo[0] == 0 && leftInfo[3] < root.val){
                return new int[]{0, leftInfo[1] + 1, leftInfo[2], root.val};
            } else {
                return new int[]{1, leftInfo[1], 0, 0};
            }
        }
        //左右子树都存在
        if(leftInfo[0] == 0 && rightInfo[0] == 0 &&
                leftInfo[3] < root.val && rightInfo[2] > root.val) {
            return new int[]{0, leftInfo[1] + 1 + rightInfo[1], leftInfo[2], rightInfo[3]};
        }
        return new int[]{1, Math.max(leftInfo[1], rightInfo[1]), 0, 0};
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int v) {
            this.val = v;
        }
    }

}
