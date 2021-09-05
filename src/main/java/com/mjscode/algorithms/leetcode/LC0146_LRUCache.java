package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * //
 * // 实现 LRUCache 类：
 * //
 * // LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * // int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * // void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
 * //限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * //
 * // 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * //
 * // 示例：
 * //
 * //输入
 * //["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * //输出
 * //[null, null, null, 1, null, -1, null, -1, 3, 4]
 * //
 * //解释
 * //LRUCache lRUCache = new LRUCache(2);
 * //lRUCache.put(1, 1); // 缓存是 {1=1}
 * //lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * //lRUCache.get(1);    // 返回 1
 * //lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * //lRUCache.get(2);    // 返回 -1 (未找到)
 * //lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * //lRUCache.get(1);    // 返回 -1 (未找到)
 * //lRUCache.get(3);    // 返回 3
 * //lRUCache.get(4);    // 返回 4
 * //
 * // 提示：
 * //
 * // 1 <= capacity <= 3000
 * // 0 <= key <= 10000
 * // 0 <= value <= 105
 * // 最多调用 2 * 105 次 get 和 put
 * //
 * // Related Topics 设计 哈希表 链表 双向链表
 *
 * 解答成功:
 *  执行耗时:46 ms,击败了68.78% 的Java用户
 *  内存消耗:108.6 MB,击败了40.85% 的Java用户
 * @author binarySigh
 * @date 2021/9/5 20:28
 */
public class LC0146_LRUCache {

    private HashMap<Integer, Node> map;
    private int cap;
    private int size;
    private Node head;
    private Node tail;

    public LC0146_LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.cap = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node cur = map.get(key);
            renewNode(cur);
            return cur.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(this.cap <= 0){
            return;
        }
        Node cur = null;
        if(map.containsKey(key)){
            cur = map.get(key);
            cur.val = value;
            renewNode(cur);
            return;
        }
        //结构已满，弹出头部节点
        if(this.size == this.cap){
            //弹出头节点，指定第二节点为新头
            Node tmp = this.head;
            this.head = tmp.next;
            if(this.head != null){
                // 新头的前驱节点置空
                this.head.pre = null;
            }
            //弹出节点的后继节点置空，方便回收
            tmp.next = null;
            if(this.cap == 1){
                // 特殊情况，若整个容量只有1，则弹出旧元素之后整个结构应为空，结构里的尾节点也应该置空
                this.tail = null;
            }
            //删除哈希表中记录，结构size--
            map.remove(tmp.key);
            this.size--;
        }
        // 尾部添加新节点
        cur = new Node(key, value);
        map.put(key, cur);
        if(this.tail != null){
            this.tail.next = cur;
            cur.pre = this.tail;
        }
        this.tail = cur;
        //检查头
        if(this.head == null){
            this.head = this.tail;
        }
        this.size++;
    }

    /**
     * 将指定节点更新至最新使用，也就是更新至队尾
     * @param cur
     */
    private void renewNode(Node cur){
        if(this.tail == cur){
            //已经是队尾，直接返回
            return;
        }
        Node pre = cur.pre;
        Node next = cur.next;
        if(pre == null){
            this.head = next;
        } else {
            pre.next = next;
        }
        if(next != null){
            next.pre = pre;
        }
        //当前节点挂到队尾
        cur.pre = this.tail;
        this.tail.next = cur;
        this.tail = this.tail.next;
    }

    private class Node{
        int key;
        int val;
        Node pre;
        Node next;
        Node(int k, int v){
            this.key = k;
            this.val = v;
            this.pre = null;
            this.next = null;
        }
    }
}
