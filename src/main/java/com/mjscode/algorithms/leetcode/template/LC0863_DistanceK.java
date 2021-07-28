package com.mjscode.algorithms.leetcode.template;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * //
 * // 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * //
 * // 示例 1：
 * //
 * // 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * //输出：[7,4,1]
 * //解释：
 * //所求结点为与目标结点（值为 5）距离为 2 的结点，
 * //值分别为 7，4，以及 1
 * //
 * //注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * //上面的输入仅仅是对这些对象进行了序列化描述。
 * //
 * // 提示：
 * //
 * // 给定的树是非空的。
 * // 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * // 目标结点 target 是树上的结点。
 * // 0 <= K <= 1000.
 * //
 * // Related Topics 树 深度优先搜索 广度优先搜索 二叉树
 * @author binarySigh
 * @date 2021/7/28 21:05
 */
public class LC0863_DistanceK {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        List<Integer> ans = distanceK(root, root.left, 1);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:13 ms,击败了99.66% 的Java用户
     * 		内存消耗:38.7 MB,击败了16.73% 的Java用户
     * @param root
     * @param target
     * @param k
     * @return
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if(k > 500){
            return ans;
        }
        if(k == 0){
            ans.add(target.val);
            return ans;
        }
        findKthSon(target, k, ans);
        getDistanceFromTarget(root, target, k, ans);
        return ans;
    }

    /**
     * 在以 root为头的子树中 查找 target节点与其距离，若未发现 target，则返回-1
     * @param root
     * @param target
     * @param k
     * @param ans
     * @return
     */
    private static int getDistanceFromTarget(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if(root == target){
            return 1;
        }
        if(root.left == null && root.right == null){
            return -1;
        }
        int distance = -1;
        int leftDistance = -1;
        int rightDistance = -1;
        if(root.left != null){
            leftDistance = getDistanceFromTarget(root.left, target, k, ans);
            if(leftDistance != -1 && root.right != null){
                findKthSon(root.right, k - leftDistance - 1, ans);
            }
        }
        if(root.right != null){
            rightDistance = getDistanceFromTarget(root.right, target, k, ans);
            if(rightDistance != -1 && root.left != null){
                findKthSon(root.left, k - rightDistance - 1, ans);
            }
        }
        distance = Math.max(leftDistance, rightDistance);
        if(distance == k){
            ans.add(root.val);
        }
        return distance == -1 ? -1 : (distance + 1);
    }

    /**
     * 在以 root为头的子树中 查找与其距离为 K的节点
     * @param root
     * @param k
     * @param ans
     */
    private static void findKthSon(TreeNode root, int k, List<Integer> ans) {
        if(root == null || k < 0){
            return;
        }
        if(k == 0){
            ans.add(root.val);
            return;
        }
        if(root.left != null){
            findKthSon(root.left, k - 1, ans);
        }
        if(root.right != null){
            findKthSon(root.right, k - 1, ans);
        }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
   }
}
