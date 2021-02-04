package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * //给你一个链表数组，每个链表都已经按升序排列。
 * //
 * // 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * //
 * // 示例 1：
 * //
 * // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * //输出：[1,1,2,3,4,4,5,6]
 * //解释：链表数组如下：
 * //[
 * //  1->4->5,
 * //  1->3->4,
 * //  2->6
 * //]
 * //将它们合并到一个有序链表中得到。
 * //1->1->2->3->4->4->5->6
 * //
 * // 示例 2：
 * //
 * // 输入：lists = []
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * // 输入：lists = [[]]
 * //输出：[]
 * //
 * // 提示：
 * //
 * // k == lists.length
 * // 0 <= k <= 10^4
 * // 0 <= lists[i].length <= 500
 * // -10^4 <= lists[i][j] <= 10^4
 * // lists[i] 按 升序 排列
 * // lists[i].length 的总和不超过 10^4
 * //
 * // Related Topics 堆 链表 分治算法
 *
 * 难度：Hard
 * @author binarySigh
 */
public class LC0023_MergeKLists {

    public static void main(String[] args){
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(4);
        head1.next.next.next = new ListNode(6);
        head1.next.next.next.next = new ListNode(8);
        head1.next.next.next.next.next = new ListNode(9);
        head1.next.next.next.next.next.next = new ListNode(12);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(7);
        head2.next.next.next.next = new ListNode(11);
        head2.next.next.next.next.next = new ListNode(21);
        head2.next.next.next.next.next.next = new ListNode(24);

        ListNode head3 = new ListNode(-4);
        head3.next = new ListNode(0);
        head3.next.next = new ListNode(4);
        head3.next.next.next = new ListNode(7);
        head3.next.next.next.next = new ListNode(10);
        head3.next.next.next.next.next = new ListNode(14);
        head3.next.next.next.next.next.next = new ListNode(22);
        head3.next.next.next.next.next.next.next = new ListNode(29);
        ListNode[] lists = {head1, head2, head3};

        ListNode merged = mergeKLists(lists);
        System.out.println("Finished!");
    }

    /**
     * 使用加强小根堆的思路进行 K 个链表的串接
     * 解答成功:
     * 		执行耗时:6 ms,击败了46.79% 的Java用户
     * 		内存消耗:39.3 MB,击败了98.52% 的Java用户
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length <= 1){
            return (lists == null || lists.length == 0) ? null : lists[0];
        }
        Heap heap = new Heap(lists.length);
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        ArrayList<ListNode> tmp = new ArrayList<>();
        ListNode head = null; //新链表头
        ListNode cur = null; //新链表的当前指针
        while(!heap.isEmpty()){
            tmp = heap.pop();
            for(int i = 0; i < tmp.size(); i++){
                ListNode node = tmp.get(i);
                ListNode next = node.next;
                node.next = null;
                if(head == null){
                    head = node;
                    cur = head;
                } else {
                    cur.next = node;
                    cur = cur.next;
                }
                if(next != null) heap.add(next);
            }
        }
        return head;
    }

    /**
     * 小根堆
     */
    public static class Heap {
        private int capcity = 4;
        private ArrayList<ArrayList<ListNode>> heap;
        //key - ListNode节点的值； value - 该值的节点在heap中的下标位置
        private HashMap<Integer, Integer> inxMap;
        private int size = 0;
        public Heap(int cap){
            this.capcity = cap;
            this.heap = new ArrayList<>(cap);
            this.inxMap = new HashMap<>(cap);
        }

        public void add(ListNode node){
            if(inxMap.containsKey(node.val)){
                //如果已经存在，那么直接往对应位置的小链表上挂就行
                heap.get(inxMap.get(node.val)).add(node);
                return;
            }
            ArrayList<ListNode> added = new ArrayList<>();
            added.add(node);
            heap.add(added);
            inxMap.put(node.val, size);
            heapInsert(size++);
        }

        public ArrayList<ListNode> pop(){
            ArrayList<ListNode> tmp = heap.get(0);
            //交换堆的首尾节点
            swap(0, --size);
            heap.remove(size);
            //消除掉弹出节点的位置记录
            inxMap.remove(tmp.get(0).val);
            //对交换上来的节点执行堆下沉操作
            heapIfy(0);
            return tmp;
        }

        public boolean isEmpty(){
            return this.size <= 0;
        }

        /**
         * 小根堆的向上调整策略
         * @param inx
         */
        private void heapInsert(int inx){
            while(inx > 0 && heap.get(inx).get(0).val < heap.get((inx >> 1)).get(0).val){
                swap(inx, (inx >> 1));
                inx >>= 1;
            }
        }

        /**
         * 小根堆的向下调整策略
         * @param inx
         */
        private void heapIfy(int inx){
            while((inx << 1) + 1 < size || (inx << 1) < size){
                if((inx << 1) + 1 < size &&
                    heap.get(inx).get(0).val > heap.get((inx << 1) + 1).get(0).val){
                    //右子节点存在，且右子节点值比当前头节点小，交换头节点和两个子节点中较小的那个
                    if(heap.get((inx << 1) + 1).get(0).val < heap.get((inx << 1)).get(0).val){
                        //右子节点比左子节点小。交换头节点和右子节点
                        swap(inx, (inx << 1) + 1);
                        inx = ((inx << 1) + 1);
                        continue;
                    } else if(heap.get((inx << 1) + 1).get(0).val > heap.get((inx << 1)).get(0).val){
                        //左子节点比右子节点小。交换头节点和左子节点
                        swap(inx, (inx << 1));
                        inx <<= 1;
                        continue;
                    }
                } else if((inx << 1) < size &&
                        heap.get(inx).get(0).val > heap.get((inx << 1)).get(0).val){
                    //左子节点存在，且左子节点值比当前头节点小
                    // 进入这个分支，说明要么左子节点不存在，要么左子节点比当前头节点大，所以这个分支里直接交换头节点和左子节点
                    swap(inx, (inx << 1));
                    inx <<= 1;
                    continue;
                } else {
                    //以上两种大情况都不满足，说明左右子节点都比他大，那么直接break
                    break;
                }
            }
        }

        private void swap(int i, int j){
            //先取出i,j位置存放的链表节点的值，以便交换完之后修改inxMap的记录
            int iVal = heap.get(i).get(0).val;
            int jVal = heap.get(j).get(0).val;
            //交换堆中的位置
            ArrayList<ListNode> tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
            //更新位置表里的记录
            inxMap.put(iVal, j);
            inxMap.put(jVal, i);
        }
    }


    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
