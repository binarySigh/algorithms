package com.mjscode.algorithms.leetcode;

import java.util.LinkedList;

/**
 * //给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * //
 * //struct Node {
 * //  int val;
 * //  Node *left;
 * //  Node *right;
 * //  Node *next;
 * //}
 * //
 * // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * //
 * // 初始状态下，所有 next 指针都被设置为 NULL。
 * //
 * // 进阶：
 * //
 * // 你只能使用常量级额外空间。
 * // 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * //
 * // 示例：
 * //
 * //输入：root = [1,2,3,4,5,6,7]
 * //输出：[1,#,2,3,#,4,5,6,7,#]
 * //解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
 * //next 指针连接，'#' 标志着每一层的结束。
 * //
 * // 提示：
 * //
 * // 树中节点的数量少于 4096
 * // -1000 <= node.val <= 1000
 * //
 * // Related Topics 树 深度优先搜索 广度优先搜索 二叉树
 * @author binarySigh
 * @date 2021/9/9 22:43
 */
public class LC0116_Connect {

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了39.33% 的Java用户
     * 		内存消耗:38.8 MB,击败了21.60% 的Java用户
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null ||
                (root.left == null && root.right == null)){
            return root;
        }
        LinkedList<Node> q = new LinkedList<>();
        int curLen = 1;
        int nextLen = 0;
        q.addFirst(root);
        while(!q.isEmpty()){
            curLen--;
            Node cur = q.pollLast();
            cur.next = curLen == 0 ? null : q.peekLast();
            if(cur.left != null){
                q.addFirst(cur.left);
                q.addFirst(cur.right);
                nextLen += 2;
            }
            if(curLen == 0){
                curLen = nextLen;
                nextLen = 0;
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
