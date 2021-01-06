package com.mjscode.algorithms.leetcode;

/**
 * //给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * //
 * // 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * //
 * // 示例 1：
 * //
 * //输入：root = [1,3,null,null,2]
 * //输出：[3,1,null,null,2]
 * //解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * //
 * // 示例 2：
 * //
 * //输入：root = [3,1,4,null,null,2]
 * //输出：[2,1,4,null,null,3]
 * //解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 * //
 * // 提示：
 * //
 * // 树上节点的数目在范围 [2, 1000] 内
 * // -231 <= Node.val <= 231 - 1
 * //
 * // Related Topics 树 深度优先搜索
 *
 * @author binarySigh
 */
public class LC0099_RecoverTree {

    /**
     * 进阶要求 的解法思路：Morris遍历解决
     * 解答成功:
     * 		执行耗时:3 ms,击败了60.51% 的Java用户
     * 		内存消耗:39.1 MB,击败了18.27% 的Java用户
     *
     * 当最后两个节点值交换改成异或写法后的运行结果：
     * 	解答成功:
     * 		执行耗时:3 ms,击败了60.51% 的Java用户
     * 		内存消耗:38.8 MB,击败了71.96% 的Java用户
     * @param root
     */
    public void recoverTree(TreeNode root){
        //交换后来到前置位的节点
        TreeNode smaller = null;
        //交换后来到后置位的节点
        TreeNode bigger = null;
        //Morris遍历指针节点
        TreeNode cur = root;
        //Morris遍历当前节点 在中序序列中的前一个节点
        // 所以这里pre的记录只在执行比较的时候才能更新
        // 因为只有执行比较的时机才是中序节点遍历的时机，只有这个时候才更新记录
        TreeNode pre = null;
        //cur的前驱节点
        TreeNode morrisPre = null;
        // 第一轮Morris遍历，找到 smaller 和 bigger
        while(cur != null){
            if(cur.left == null) {
                //左子节点为空的节点，只能来到一次，来到就执行比较策略
                if(pre != null && pre.val > cur.val){
                    if(smaller == null){
                        smaller = pre;
                        //如果交换的两个节点正好是中序遍历序列中相邻的两个节点，那么来到后置位的那个节点会在遍历中被漏掉，
                        //  因此这里在给交换到前置位的节点赋值时，一定要 同时给 bigger 赋值。
                        //  如果实际上真的不相邻的话，bigger 也会在后续的遍历中被覆盖掉的，不会影响最终结果
                        bigger = cur;
                    } else {
                        bigger = cur;
                    }
                }
                //继续执行正常Morris中序遍历流程
                pre = cur;
                cur = cur.right;
            } else {
                //左子节点不为空，则说明该节点能来到两次。判断是否是第二次来到，第二次来到才执行比较策略
                morrisPre = findPreNode(cur);
                if(morrisPre.right == cur){
                    // 第二次来到，执行比较策略
                    if(pre != null && pre.val > cur.val){
                        if(smaller == null){
                            smaller = pre;
                            //赋值理由同上
                            bigger = cur;
                        } else {
                            bigger = cur;
                        }
                    }
                    //继续Morris中序遍历流程
                    morrisPre.right = null;
                    pre = cur;
                    cur = cur.right;
                } else {
                    //第一次来到，直接继续Morris中序遍历流程即可
                    //因为此时并不是中序序列产生的时机，所以pre也不更新
                    morrisPre.right = cur;
                    cur = cur.left;
                }
            }
        }
        bigger.val = bigger.val ^ smaller.val;
        smaller.val = bigger.val ^ smaller.val;
        bigger.val = bigger.val ^ smaller.val;
    }

    /**
     *
     * 寻找当前节点的前驱节点，若无前驱节点则返回null
     * @param cur
     * @return
     */
    public TreeNode findPreNode(TreeNode cur){
        if(cur.left == null){
            return null;
        }
        TreeNode preNode = cur.left;
        while(preNode.right != null && preNode.right != cur){
            preNode = preNode.right;
        }
        return preNode;
    }

    private class TreeNode {
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
