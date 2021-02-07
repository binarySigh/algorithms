package com.mjscode.algorithms.leetcode;

/**
 * //给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * //将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * //
 * // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * //
 * // 示例 1:
 * //
 * // 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * //
 * // 示例 2:
 * //
 * // 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * // Related Topics 链表
 *
 * @author binarySigh
 */
public class LC0143_ReorderList {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderList(head);
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了79.95% 的Java用户
     * 		内存消耗:41.3 MB,击败了29.16% 的Java用户
     * @param head
     */
    public static void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = reverse(slow.next);
        slow.next = null;
        slow = head;
        ListNode tmp1 = null;
        ListNode tmp2 = null;
        while(slow != null && fast != null){
            tmp1 = slow.next;
            tmp2 = fast.next;
            slow.next = fast;
            fast.next = tmp1;
            slow = tmp1;
            fast = tmp2;
        }
    }

    public static ListNode reverse(ListNode node){
        ListNode pre = null;
        ListNode cur = node;
        ListNode tmp = null;
        while(cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
