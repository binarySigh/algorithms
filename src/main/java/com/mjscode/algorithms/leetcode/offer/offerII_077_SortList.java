package com.mjscode.algorithms.leetcode.offer;

/**
 * //给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * //
 * // 示例 1：
 * //
 * //输入：head = [4,2,1,3]
 * //输出：[1,2,3,4]
 * //
 * // 示例 2：
 * //
 * //输入：head = [-1,5,3,4,0]
 * //输出：[-1,0,3,4,5]
 * //
 * // 示例 3：
 * //
 * //输入：head = []
 * //输出：[]
 * //
 * // 提示：
 * //
 * // 链表中节点的数目在范围 [0, 5 * 104] 内
 * // -105 <= Node.val <= 105
 * //
 * // 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * //
 * // 注意：本题与主站 148 题相同：https://leetcode-cn.com/problems/sort-list/
 * // Related Topics 链表 双指针 分治 排序 归并排序
 * @author binarySigh
 * @date 2021/9/3 22:22
 */
public class offerII_077_SortList {
    public static void main(String[] args){
        ListNode head = new ListNode(12);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(14);
        head.next.next.next.next = new ListNode(-2);
        head.next.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next.next = new ListNode(55);

        ListNode ans = sortList(head);

        StringBuilder sb = new StringBuilder("[");
        while(ans != null){
            sb.append(ans.val).append(',');
            ans = ans.next;
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了34.72% 的Java用户
     * 		内存消耗:46.8 MB,击败了40.41% 的Java用户
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        int n = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            n++;
        }
        int path = 1;
        ListNode curHead = head;
        ListNode pre = null, next = head;
        ListNode li = head, lj = head, ri = head, rj = head;
        while(path < n){
            li = curHead;
            pre = null;
            while(li != null) {
                cur = li;
                for (int i = 1; i < path && cur != null; i++) {
                    cur = cur.next;
                }
                lj = cur;
                ri = cur == null ? null : cur.next;
                cur = ri;
                for(int i = 1; i < path && cur != null; i++){
                    cur = cur.next;
                }
                rj = cur;
                next = cur == null ? null : cur.next;
                if(lj != null) lj.next = null;
                if(rj != null) rj.next = null;
                ListNode[] tmp = mergeSort(li, ri);
                if(pre == null){
                    curHead = tmp[0];
                } else {
                    pre.next = tmp[0];
                }
                pre = tmp[1];
                li = next;
            }
            path <<= 1;
        }
        return curHead;
    }

    public static ListNode[] mergeSort(ListNode l, ListNode r){
        ListNode[] nodes = new ListNode[2];
        ListNode cur = null;
        while(l != null && r != null){
            if(l.val <= r.val){
                if(cur == null) {
                    cur = l;
                    nodes[0] = cur;
                } else {
                    cur.next = l;
                    cur = l;
                }
                l = l.next;
            } else {
                if(cur == null) {
                    cur = r;
                    nodes[0] = cur;
                } else {
                    cur.next = r;
                    cur = r;
                }
                r = r.next;
            }
        }
        while(l != null){
            if(cur == null){
                nodes[0] = l;
                cur = l;
            } else {
                cur.next = l;
                cur = cur.next;
            }
            if(l.next == null){
                nodes[1] = l;
            }
            l = l.next;
        }
        while(r != null){
            if(cur == null){
                nodes[0] = r;
                cur = r;
            } else {
                cur.next = r;
                cur = cur.next;
            }
            if(r.next == null){
                nodes[1] = r;
            }
            r = r.next;
        }
        return nodes;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
