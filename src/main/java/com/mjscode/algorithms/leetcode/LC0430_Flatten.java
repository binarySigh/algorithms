package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * //多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生
 * //成多级数据结构，如下面的示例所示。
 * //
 * // 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * //
 * // 示例 1：
 * //
 * // 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * //输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * //解释：
 * //
 * //输入的多级列表如下图所示：
 * //
 * //扁平化后的链表如下图：
 * //
 * // 示例 2：
 * //
 * // 输入：head = [1,2,null,3]
 * //输出：[1,3,2]
 * //解释：
 * //
 * //输入的多级列表如下图所示：
 * //
 * //  1---2---NULL
 * //  |
 * //  3---NULL
 * //
 * // 示例 3：
 * //
 * // 输入：head = []
 * //输出：[]
 * //
 * // 如何表示测试用例中的多级链表？
 * //
 * // 以 示例 1 为例：
 * //
 * //  1---2---3---4---5---6--NULL
 * //         |
 * //         7---8---9---10--NULL
 * //             |
 * //             11--12--NULL
 * //
 * // 序列化其中的每一级之后：
 * //
 * // [1,2,3,4,5,6,null]
 * //[7,8,9,10,null]
 * //[11,12,null]
 * //
 * // 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 * //
 * // [1,2,3,4,5,6,null]
 * //[null,null,7,8,9,10,null]
 * //[null,11,12,null]
 * //
 * // 合并所有序列化结果，并去除末尾的 null 。
 * //
 * // [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * //
 * // 提示：
 * //
 * // 节点数目不超过 1000
 * // 1 <= Node.val <= 10^5
 * //
 * // Related Topics 深度优先搜索 链表 双向链表
 * @author binarySigh
 * @date 2021/9/24 21:14
 */
public class LC0430_Flatten {

    public static void main(String[] args) {
          /*Node head1 = new Node(1);
          head1.next = new Node(2);
          head1.next.prev = head1;
          head1.next.next = new Node(3);
          head1.next.next.prev = head1.next;
          head1.next.next.next = new Node(4);
          head1.next.next.next.prev = head1.next.next;
          head1.next.next.next.next = new Node(5);
          head1.next.next.next.next.prev = head1.next.next.next;
          head1.next.next.next.next.next = new Node(6);
          head1.next.next.next.next.next.prev = head1.next.next.next.next;

          Node head2 = new Node(7);
          head2.next = new Node(8);
          head2.next.prev = head2;
          head2.next.next = new Node(9);
          head2.next.next.prev = head2.next;
          head2.next.next.next = new Node(10);
          head2.next.next.next.prev = head2.next.next;

          Node head3 = new Node(11);
          head3.next = new Node(12);
          head3.next.prev = head3;

          head1.next.next.child = head2;
          head2.next.child = head3;*/

        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        head1.child = head2;
        head2.child = head3;

        Node ret = flatten(head1);
    }

    public static Node flatten(Node head) {
        if(head == null){
            return head;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        Node tmp = null;
        while(cur.next != null || cur.child != null || !stack.isEmpty()) {
            if(cur.child != null){
                tmp = cur.next;
                if(tmp != null) {
                    tmp.prev = null;
                    stack.push(tmp);
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            } else if(cur.next == null) {
                tmp = stack.pop();
                tmp.prev = cur;
                cur.next = tmp;
            }
            cur = cur.next;
        }
        return head;
    }

    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node(int v) {
            this.val = v;
            this.prev = null;
            this.next = null;
            this.child = null;
        }
    }
}
