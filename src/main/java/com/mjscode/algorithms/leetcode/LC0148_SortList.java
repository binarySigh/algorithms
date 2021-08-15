package com.mjscode.algorithms.leetcode;

/**
 * //给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * //
 * // 进阶：
 * //
 * // 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
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
 * // Related Topics 链表 双指针 分治 排序 归并排序
 * @author binarySigh
 * @date 2021/8/15 15:46
 */
public class LC0148_SortList {
    public static void main(String[] args){
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next.next  = new ListNode(-3);

        ListNode ans = sortList(head);
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了45.50% 的Java用户
     * 		内存消耗:46.7 MB,击败了53.69% 的Java用户
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        int len = 0;
        ListNode tmp = head;
        while(tmp != null){
            len++;
            tmp = tmp.next;
        }
        int path = 1;
        ListNode head1 = null;
        ListNode head2 = null;
        ListNode pre = null;
        ListNode next = null;
        while(path < len){
            tmp = head;
            while(tmp != null) {
                head1 = tmp;
                for(int i = 1; i < path && tmp != null; i++) {
                    tmp = tmp.next;
                }
                if(tmp == null || tmp.next == null) {
                    //如果不存在第二小段则在此处直接处理，否则下面流程中会报错
                    if(pre == null) {
                        head = head1;
                    } else {
                        pre.next = head1;
                    }
                    break;
                }
                //当前小段找到之后要与下一段断连
                head2 = tmp.next;
                tmp.next = null;
                tmp = head2;
                //若存在第二小段，则继续查找，切割，合并
                for(int i = 1; i < path && tmp != null; i++) {
                    tmp = tmp.next;
                }
                //当前小段找到之后要与下一段断连,前提是第二小段后面还有节点需要处理
                if(tmp != null) {
                    next = tmp.next;
                    tmp.next = null;
                }
                //将找出来的2小段进行合并
                ListNode[] res = mergeSort(head1, head2);
                if(pre == null) {
                    head = res[0];
                } else {
                    pre.next = res[0];
                }
                pre = res[1];
                tmp = tmp == null ? null : next;
            }
            pre = null;
            path <<= 1;
        }
        return head;
    }

    public static ListNode[] mergeSort(ListNode head1, ListNode head2) {
        ListNode[] res = new ListNode[2];
        ListNode tmp = null;
        res[0] = head1.val <= head2.val ? head1 : head2;
        tmp = res[0];
        if(head1.val <= head2.val){
            head1 = head1.next;
        } else {
            head2 = head2.next;
        }
        while(head1 != null && head2 != null) {
            if(head1.val <= head2.val){
                tmp.next = head1;
                head1 = head1.next;
            } else {
                tmp.next = head2;
                head2 = head2.next;
            }
            tmp = tmp.next == null ? tmp : tmp.next;
        }
        if(head1 != null){
            tmp.next = head1;
        }
        if(head2 != null){
            tmp.next = head2;
        }
        while(tmp != null && tmp.next != null){
            tmp = tmp.next;
        }
        res[1] = tmp;
        return res;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
