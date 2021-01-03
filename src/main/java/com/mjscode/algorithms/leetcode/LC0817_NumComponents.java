package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * //给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。
 * //
 * // 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 * //
 * // 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 * //
 * // 示例 1：
 * //
 * // 输入:
 * //head: 0->1->2->3
 * //G = [0, 1, 3]
 * //输出: 2
 * //解释:
 * //链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * //
 * // 示例 2：
 * //
 * // 输入:
 * //head: 0->1->2->3->4
 * //G = [0, 3, 1, 4]
 * //输出: 2
 * //解释:
 * //链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 * //
 * // 提示：
 * //
 * // 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
 * // 链表中每个结点的值所在范围为 [0, N - 1]。
 *
 * // 1 <= G.length <= 10000
 * // G 是链表中所有结点的值的一个子集.
 * //
 * // Related Topics 链表
 *
 * @author binarySigh
 */
public class LC0817_NumComponents {

    private static class Node{
        private int val;
        private Node next;

        public Node(int val){
            this.val = val;
        }

        public int val(){
            return this.val;
        }
    }

    /**
     * 方案1思路：遍历链表，每遍历到一个节点就去查数组，看是否满足以下条件：
     *  1. 当前节点在数组中；
     *  2. 当前节点上一个节点不在数组中 或 上一个节点为空(为空主要是针对链表首节点在数组中的情况，这种情况组件数是应该+1的)
     *  满足以上两点即说明 当前节点是一个新组件，因此 num+1
     *
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:1265 ms,击败了5.11% 的Java用户
     * 		内存消耗:39 MB,击败了90.87% 的Java用户
     * @param head
     * @param G
     * @return
     */
    public int numComponents(Node head, int[] G) {
        if(G.length == 1){
            return 1;
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < G.length; i++) {
            list.add(G[i]);
        }
        int num = 0;
        Node pre = null;
        Node cur = head;
        while(cur != null){
            if((pre == null || !list.contains(pre.val)) && list.contains(cur.val)){
                num++;
            }
            pre = cur;
            cur = cur.next;
        }
        return num;
    }

    /**
     * 解题思路同上，只是把容器从 ArrayList 换成了 HashSet
     *   容器替换原因：对于容器内元素读取操作量大的场景而言，哈希表优势非常明显。
     *          同时定义哈希表时，如果可以一定要初始化其大小，防止哈希表初始化过程中扩容操作消耗时间
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:7 ms,击败了79.57% 的Java用户
     * 		内存消耗:39.2 MB,击败了73.54% 的Java用户
     * @param head
     * @param G
     * @return
     */
    public int numComponents2(Node head, int[] G){
        if(G.length == 1){
            return 1;
        }
        HashSet<Integer> list = new HashSet<>(G.length);
        for(int i = 0; i < G.length; i++) {
            list.add(G[i]);
        }
        int num = 0;
        Node pre = null;
        Node cur = head;
        while(cur != null){
            if((pre == null || !list.contains(pre.val)) && list.contains(cur.val)){
                num++;
            }
            pre = cur;
            cur = cur.next;
        }
        return num;
    }
}
