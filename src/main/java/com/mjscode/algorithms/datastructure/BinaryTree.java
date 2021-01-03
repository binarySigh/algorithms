package com.mjscode.algorithms.datastructure;

import com.mjscode.algorithms.utils.TreeNode;

import java.util.*;

/**
 * 二叉树的非递归遍历方式<BR/>
 *  - Stack 方式<BR/>
 * @author binarySigh
 */
public class BinaryTree {

    /**
     * 先序遍历
     *
     * @param head
     * @return
     */
    public static List<Integer> showByPreOrder(TreeNode head){
        List<Integer> list = new ArrayList<Integer>();
        if(head == null){
            list.add(null);
            return list;
        }
        TreeNode cur = null;
        Stack<TreeNode> tree = new Stack<TreeNode>();
        tree.push(head);
        while(!tree.empty()) {
            cur = tree.pop();
            list.add(cur == null ? null : cur.val());
            //先序遍历 先压入右子节点后压入左子节点
            if(cur != null){
                //添加右子节点
                tree.push(cur.right());
                //添加左子节点
                tree.push(cur.left());
            }
        }
        return list;
    }

    /**
     * 后序遍历
     * @param head
     * @return
     */
    public static List<Integer> showByPosOrder(TreeNode head){
        List<Integer> list = new ArrayList<Integer>();
        if(head == null){
            list.add(null);
            return list;
        }
        TreeNode cur = null;
        Stack<TreeNode> tree = new Stack<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        tree.push(head);
        while(!tree.isEmpty()){
            cur = tree.pop();
            if(cur != null){
                tree.push(cur.left());
                tree.push(cur.right());
            }
            stack.push(cur);
        }
        while(!stack.isEmpty()){
            cur = stack.pop();
            list.add(cur != null ? cur.val() : null);
        }
        return list;
    }

    /**
     * 中序遍历
     * @param head
     * @return
     */
    public static List<Integer> showByInOrder(TreeNode head){
        List<Integer> list = new ArrayList<Integer>();
        if(head == null){
            list.add(null);
            return list;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(cur);
        while(!stack.isEmpty()){
            //如果当前节点不为空，则直接将当前节点左子节点压栈
            while(cur != null) {
                cur = cur.left();
                stack.push(cur);
            }
            //若当前节点为空，则说明当前整条左边界已经全部压栈完毕，则开始弹出元素
            cur = stack.pop();
            //弹出元素 并打印
            list.add(cur == null ? null : cur.val());
            //若弹出元素不为空，则将弹出元素的右子节点压栈，然后进入下轮循环，将该右子节点的所有左边界压栈
            //若当前弹出元素为空，则什么也不做直接进入下轮循环，继续弹出栈顶元素，直至弹出非空元素，并将其右子节点压栈
            if(cur != null) {
                cur = cur.right();
                stack.push(cur);
            }
        }
        return list;
    }

    /**
     * 层序遍历二叉树。返回逻辑同 lc102
     * @param head
     * @return
     */
    public static List<List<Integer>> showByLevelOrder(TreeNode head){
        List<List<Integer>> levels = new ArrayList<>();
        if(head == null){
            return levels;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode cur = new TreeNode();
        TreeNode curLevelEnds = head; //本层最右节点
        TreeNode nextLevelEnds = new TreeNode(); //下一层最右节点
        queue.add(head);
        while(!queue.isEmpty()){
            cur = queue.poll();
            //如果当前节点有右子节点就将nextLevelEnds更新成当前节点的右子节点
            // 否则若当前节点有左子节点，则将nextLevelEnds更新成当前节点的左子节点
            // 若当前节点既无右子节点又无左子节点，则维持 nextLevelEnds 原值不变
            nextLevelEnds = cur.right() != null ? cur.right() : (cur.left() != null ? cur.left() : nextLevelEnds);
            list.add(cur.val());
            if(cur.left() != null){
                queue.add(cur.left());
            }
            if(cur.right() != null){
                queue.add(cur.right());
            }
            // 若当前节点是本层最右节点
            if(cur == curLevelEnds){
                levels.add(list);
                //这里list一定要指向新开辟的内存空间，不能直接clear来清空数据，直接clear会导致最终levels结果为[[],[],[]]
                // 原因是 list是按照地址传递的，最后一层结束时调用的clear操作会将levels中所有list都清空
                list = new ArrayList<>();
                // 因为下一个poll出的元素就是下一层的元素了，所以将 本层的nextLevelEnds赋给curLevelEnds，给下一层用做层尾判断
                curLevelEnds = nextLevelEnds;
                // nextLevelEnds 在前一层结束时一定要置空，否则会导致最后一层的元素无法加入levels
                nextLevelEnds = null;
            }
        }
        return levels;
    }

    /**
     * 输出二叉搜索树的中序遍历结果
     *       递归方式
     * @return
     */
    public static String showBinarySearchTree(BinarySearchTree root) {
        String str = "";
        if(root == null){
            return "";
        } else if(root.left == null && root.right == null){
            return String.valueOf(root.val);
        }
        String left = showBinarySearchTree(root.left);
        String right = showBinarySearchTree(root.right);
        str = left + "," + root.val + right;
        return str;
    }
}
