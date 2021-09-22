package com.mjscode.algorithms.leetcode;

/**
 * //给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * //
 * // 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * //
 * // 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * //
 * // 返回一个由上述 k 部分组成的数组。
 * //
 * // 示例 1：
 * //
 * //输入：head = [1,2,3], k = 5
 * //输出：[[1],[2],[3],[],[]]
 * //解释：
 * //第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * //最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * //
 * // 示例 2：
 * //
 * //输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * //输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * //解释：
 * //输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 * //
 * // 提示：
 * //
 * // 链表中节点的数目在范围 [0, 1000]
 * // 0 <= Node.val <= 1000
 * // 1 <= k <= 50
 * //
 * // Related Topics 链表
 * @author binarySigh
 * @date 2021/9/22 20:54
 */
public class LC0725_SplitListToParts {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);
        int k = 3;


        ListNode[] ans = SplitListToParts(head, k);

    }

    public static ListNode[] SplitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];
        int len = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            ++len;
        }
        int partLen = len / k;
        int rests = len % k;
        int idx = 0;
        while(head != null){
            ans[idx++] = head;
            int curLen = partLen + (rests-- > 0 ? 1 : 0);
            while(head != null && --curLen > 0){
                head = head.next;
            }
            cur = head.next;
            head.next = null;
            head = cur;
        }
        return ans;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int v){
            this.val = v;
        }
    }
}
