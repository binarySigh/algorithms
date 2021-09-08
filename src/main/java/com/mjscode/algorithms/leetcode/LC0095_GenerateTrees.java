package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * //
 * // 示例 1：
 * //
 * //输入：n = 3
 * //输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * //
 * // 示例 2：
 * //
 * //输入：n = 1
 * //输出：[[1]]
 * //
 * // 提示：
 * //
 * // 1 <= n <= 8
 * //
 * // Related Topics 树 二叉搜索树 动态规划 回溯 二叉树
 * @author binarySigh
 * @date 2021/9/8 19:18
 */
public class LC0095_GenerateTrees {

    public static void main(String[] args){
        int n = 3;
        List<TreeNode> ans = generateTrees(n);
    }

    /**
     *
     解答成功:
     执行耗时:215 ms,击败了8.83% 的Java用户
     内存消耗:39 MB,击败了72.40% 的Java用户
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if(n < 1 || n > 8){
            return ans;
        }
        boolean[] used = new boolean[n + 1];
        int[] nums = new int[n + 1];
        process(nums, used, 1, ans);
        return ans;
    }

    public static void process(int[] nums, boolean[] used, int idx, List<TreeNode> ans){
        if(idx == nums.length){
            TreeNode head = new TreeNode(nums[1]);
            for(int i = 2; i < nums.length; i++){
                buildTree(head, nums[i]);
            }
            for(TreeNode root : ans) {
                if(check(root, head)) {
                    return;
                }
            }
            ans.add(head);
            return;
        }
        for(int i = 1; i < nums.length; i++){
            if(!used[i]){
                used[i] = true;
                nums[idx] = i;
                process(nums, used, idx + 1, ans);
                nums[idx] = 0;
                used[i] = false;
            }
        }
    }

    public static boolean check(TreeNode root1, TreeNode root2){
        if(root1 == null || root2 == null){
            return root1 == null && root2 == null;
        }
        if(root1.val != root2.val){
            return false;
        }
        if(check(root1.left, root2.left) && check(root1.right, root2.right)){
            return true;
        }
        return false;
    }

    public static void buildTree(TreeNode head, int val){
        TreeNode pre = head;
        while(head != null){
            pre = head;
            head = head.val > val ? head.left : head.right;
        }
        head = new TreeNode(val);
        if(pre.val > val){
            pre.left = head;
        } else {
            pre.right = head;
        }
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
