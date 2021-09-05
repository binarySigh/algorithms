package com.mjscode.algorithms.leetcode.offer;

import java.util.PriorityQueue;

/**
 * @author binarySigh
 * @date 2021/9/4 10:16
 */
public class offerII_078_MergeKLists {
    public static void main(String[] args){
        ListNode[] list = new ListNode[4];
        list[0] = new ListNode(1);
        list[0].next = new ListNode(2);
        list[0].next.next = new ListNode(3);
        list[0].next.next.next = new ListNode(3);
        list[1] = new ListNode(-21);
        list[1].next = new ListNode(-12);
        list[1].next.next = new ListNode(0);
        list[2] = new ListNode(23);
        list[2].next = new ListNode(24);
        list[3] = new ListNode(2);

        ListNode head = mergeKLists(list);
    }

    /**
     * 解答成功:
     * 		执行耗时:4 ms,击败了61.30% 的Java用户
     * 		内存消耗:40.1 MB,击败了53.69% 的Java用户
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        PriorityQueue<ListNode> q = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for(ListNode cur : lists){
            if(cur != null){
                q.add(cur);
            }
        }
        ListNode head = q.peek();
        ListNode cur = null;
        ListNode tmp = null;
        while(!q.isEmpty()){
            if(cur == null) {
                cur = q.poll();
            } else {
                cur.next = q.poll();
                cur = cur.next;
            }
            tmp = cur.next;
            cur.next = null;
            if(tmp != null){
                q.add(tmp);
            }
        }
        return head;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
