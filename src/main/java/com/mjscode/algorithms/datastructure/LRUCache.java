package com.mjscode.algorithms.datastructure;

import com.mjscode.algorithms.leetcode.LC0146_LRUCache;

import java.util.HashMap;

/**
 * LRU内存调度算法实现
 * @author binarySigh
 * @date 2021/9/5 21:33
 */
public class LRUCache {

    private HashMap<Integer, Node> map;
    private int cap;
    private int size;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
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
