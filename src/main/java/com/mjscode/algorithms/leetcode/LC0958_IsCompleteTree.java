package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * //给定一个二叉树，确定它是否是一个完全二叉树。
 * //
 * // 百度百科中对完全二叉树的定义如下：
 * //
 * // 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：
 * //第 h 层可能包含 1~ 2h 个节点。）
 * //
 * // 示例 1：
 * //
 * // 输入：[1,2,3,4,5,6]
 * //输出：true
 * //解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 * //
 * // 示例 2：
 * //
 * // 输入：[1,2,3,4,5,null,7]
 * //输出：false
 * //解释：值为 7 的结点没有尽可能靠向左侧。
 *
 * @author binarySigh
 */
public class LC0958_IsCompleteTree {
    public static void main(String[] args){

    }

    /**
     * 非递归写法。leetcode Accepted
     *
     * 执行耗时:1 ms,击败了93.67% 的Java用户
     * 内存消耗:37.8 MB,击败了75.24% 的Java用户
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int lastLevelNum = 0; //上一层节点数
        int curLevelNum = 0; //当前层节点数
        TreeNode cur = null;
        TreeNode curLevelEnds = root; //当前层最右节点
        TreeNode nextLevelEnds = null; //下一层最右节点
        boolean pauseFlag = false; //
        TreeNode pauseNode = null; //
        queue.add(root);
        while(!queue.isEmpty()){
            cur = queue.poll();
            curLevelNum++; //当前层节点数+1
            //根据当前节点设置下一层最右节点
            nextLevelEnds = cur.right() == null ? (cur.left() == null ? nextLevelEnds : cur.left()) : cur.right();
            if(cur.left() != null){
                queue.add(cur.left());
            }
            if(cur.right() != null){
                queue.add(cur.right());
            }
            // 以下为完全二叉树逻辑判定部分
            // 如果当前节点有右子树无左子树，则说明不满足完全二叉树定义，返回false
            if(cur.left() == null && cur.right() != null){
                return false;
            }
            // 如果当前节点至少缺一个子树，且又不是当前层最右节点，则记录一下
            if((cur.left() == null || cur.right() == null) && cur != curLevelEnds){
                // 如果当前层已经有过中断记录，则后序遍历不再重复更新pauseNode的结果，防止连续出现中断节点而导致最后误判
                // 即，若当前层有多个节点缺失子节点，则只记录第一个就行
                if(!pauseFlag) {
                    // 中断标记置为true，表示本层后面的节点都不能再有子节点，也即本层后面的节点遍历过程不该再更新nextLevelEnds
                    pauseFlag = true;
                    // 记录当前的nextLevelEnds，与本层结束时的nextLevelEnds比较，若相等则没事，若不等则说明非完全二叉树
                    pauseNode = nextLevelEnds;
                }
            }
            // 如果当前节点是当前层最右节点，而且还有下一层
            if(cur == curLevelEnds && !queue.isEmpty()){
                // 但该层节点数不是上一层两倍,返回false,第一层没办法两倍且不影响结果，跳过判断;
                if(lastLevelNum > 0 && curLevelNum < (lastLevelNum << 1)){
                    return false;
                }
            }
            // 判断结束，继续正常层序遍历流程
            if(cur == curLevelEnds){
                // 本层有节点缺过右子节点，且遍历至该节点时下层最右节点与本层结束时下层最右节点不同，则返回false
                if(pauseFlag && pauseNode != nextLevelEnds){
                    return false;
                }
                curLevelEnds = nextLevelEnds;
                nextLevelEnds = null;
                lastLevelNum = curLevelNum;
                curLevelNum = 0;
                pauseFlag = false;
                pauseNode = null;
            }
        }
        return true;
    }
}
