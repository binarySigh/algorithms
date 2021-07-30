package com.mjscode.algorithms.leetcode;

/**
 * //给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
 * //个。
 * //
 * // 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * //
 * // 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * //
 * // 示例 1：
 * //
 * //输入：root = [2,2,5,null,null,5,7]
 * //输出：5
 * //解释：最小的值是 2 ，第二小的值是 5 。
 * //
 * // 示例 2：
 * //
 * //输入：root = [2,2,2]
 * //输出：-1
 * //解释：最小的值是 2, 但是不存在第二小的值。
 * //
 * // 提示：
 * //
 * // 树中节点数目在范围 [1, 25] 内
 * // 1 <= Node.val <= 231 - 1
 * // 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 * //
 * // Related Topics 树 深度优先搜索 二叉树
 * @author binarySigh
 * @date 2021/7/27 22:05
 */
public class LC0671_FindSecondMinimumValue {
    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.6 MB,击败了75.81% 的Java用户
     * @param root
     * @return
     */
    public static int findSecondMinimumValue(TreeNode root) {
        if(root.left == null || root.right == null){
            return -1;
        }
        //防止左右子节点值相同，取最大之后将后续子节点里第二小的值覆盖掉
        int curSecond = root.left.val == root.right.val ? -1 : Math.max(root.left.val, root.right.val);
        int sonSecond = findSecondMinimumValue(root.left);
        if(sonSecond != -1){
            curSecond = curSecond == -1 ? sonSecond : Math.min(curSecond, sonSecond);
        }
        sonSecond = findSecondMinimumValue(root.right);
        if(sonSecond != -1){
            curSecond = curSecond == -1 ? sonSecond : Math.min(curSecond, sonSecond);
        }
        return curSecond == root.val ? -1 : curSecond;
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