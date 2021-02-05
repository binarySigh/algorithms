package com.mjscode.algorithms.leetcode;

/**
 * //给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * //
 * // k 是一个正整数，它的值小于或等于链表的长度。
 * //
 * // 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * //
 * // 示例：
 * //
 * // 给你这个链表：1->2->3->4->5
 * //
 * // 当 k = 2 时，应当返回: 2->1->4->3->5
 * //
 * // 当 k = 3 时，应当返回: 3->2->1->4->5
 * //
 * // 说明：
 * //
 * // 你的算法只能使用常数的额外空间。
 * // 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * //
 * // Related Topics 链表
 *
 * 原题难度：HARD
 * @author binarySigh
 */
public class LC0025_ReverseKGroup {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        int k = 7;
        ListNode reversed = reverseKGroup(head, k);
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.6 MB,击败了77.33% 的Java用户
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 2){
            return head;
        }
        ListNode ret = null;
        ListNode curKhead = head;
        ListNode curKtail = null;
        ListNode tmp = head;
        ListNode pre = null;
        int count = 1;
        while(head.next != null){
            if(count < k){
                head = head.next;
                count++;
            } else if(count == k){
                //将当前段最后一个节点接过来
                curKtail = head;
                //head先定位到下一段的头节点,并重置计数
                head = head.next;
                count = 1;
                //切断当前K个链表节点与下一段的关联
                curKtail.next = null;
                //反转当前这k个节点，并将当前的接到上一段的尾节点上
                tmp = reverse(curKhead);
                curKtail = curKhead;
                curKhead = tmp;
                if(ret == null){
                    ret = curKhead;
                } else {
                    pre.next = curKhead;
                }
                pre = curKtail;
                //将 curKhead 重置为 下一段的头节点
                curKhead = head;
            }
        }
        //退出循环后对可能存在的剩余部分做单独处理
        if(count == k){
            tmp = reverse(curKhead);
            if(ret == null) ret = tmp;
            else pre.next = tmp;
        } else {
            if(ret == null) ret = tmp;
            else pre.next = curKhead;
        }
        return ret;
    }

    public static ListNode reverse(ListNode node){
        ListNode pre = null;
        ListNode cur = null;
        ListNode tmp = null;
        while(node != null){
            tmp = node.next;
            cur = node;
            cur.next = pre;
            pre = cur;
            node = tmp;
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
