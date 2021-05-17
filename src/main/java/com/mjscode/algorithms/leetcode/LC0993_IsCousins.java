package com.mjscode.algorithms.leetcode;

import java.util.LinkedList;

/**
 * //在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * //
 * // 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * //
 * // 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * //
 * // 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * //
 * // 示例 1：
 * //
 * //输入：root = [1,2,3,4], x = 4, y = 3
 * //输出：false
 * //
 * // 示例 2：
 * //
 * //输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * //输出：true
 * //
 * // 示例 3：
 * //
 * //输入：root = [1,2,3,null,4], x = 2, y = 3
 * //输出：false
 * //
 * // 提示：
 * //
 * // 二叉树的节点数介于 2 到 100 之间。
 * // 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * //
 * // Related Topics 树 广度优先搜索
 * @author binarySigh
 * @date 2021/5/17 21:40
 */
public class LC0993_IsCousins {
    public static void main(String[] args){
        // 13, 14 --> true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.left = new TreeNode(6);
        root.left.left.right.left.left = new TreeNode(13);
        root.left.left.right.left.right = new TreeNode(17);
        root.left.left.right.right = new TreeNode(10);
        root.left.left.right.right.left = new TreeNode(15);
        root.left.left.right.right.left.left = new TreeNode(12);
        root.left.left.right.right.right = new TreeNode(14);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(8);
        root.right.left.left = new TreeNode(11);
        root.right.right = new TreeNode(9);

        // 11, 17 -> true
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(15);
        root.left.left.left.right = new TreeNode(17);
        root.left.left.right = new TreeNode(8);
        root.left.right = new TreeNode(19);

        root.right = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.left.left = new TreeNode(13);
        root.right.left.right = new TreeNode(14);
        root.right.left.right.left = new TreeNode(18);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        root.right.right.right.left = new TreeNode(7);
        root.right.right.right.left.right = new TreeNode(9);
        root.right.right.right.right = new TreeNode(11);
        root.right.right.right.right.left = new TreeNode(16);
        root.right.right.right.right.left.left = new TreeNode(20);
        root.right.right.right.right.right = new TreeNode(12);*/

        System.out.println(isCousins(root, 13, 14));

    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.1 MB,击败了62.27% 的Java用户
     * @param root
     * @param x
     * @param y
     * @return
     */
    public static boolean isCousins(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        boolean findOne = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        TreeNode preEnd = root;
        TreeNode curEnd = root;
        while(!queue.isEmpty()){
            TreeNode cur = queue.pollLast();
            if(cur.left != null){
                queue.addFirst(cur.left);
                curEnd = cur.left;
                if(cur.left.val == x || cur.left.val == y){
                    if(!findOne){
                        findOne = true;
                    } else {
                        return true;
                    }
                }
            }
            if(cur.right != null){
                queue.addFirst(cur.right);
                curEnd = cur.right;
                if(cur.right.val == x || cur.right.val == y){
                    if(!findOne){
                        findOne = true;
                    } else {
                        return cur.left != null && (cur.left.val == x || cur.left.val == y) ? false : true;
                    }
                }
            }
            if(cur == preEnd){
                if(findOne){
                    return false;
                } else {
                    preEnd = curEnd;
                }
            }
        }
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
