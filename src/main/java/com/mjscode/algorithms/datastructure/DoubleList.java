package com.mjscode.algorithms.datastructure;

/**
 * 双向链表
 * @author binarySigh
 */
public class DoubleList {
    private Integer data;
    private int size;
    private DoubleList pre;
    private DoubleList next;

    public DoubleList() {
    }

    private DoubleList(Integer data, DoubleList pre, DoubleList next) {
        this.data = data;
        this.pre = pre;
        this.next = next;
    }

    public Integer add(int param) {
        if(this.size == 0){
            this.data = param;
            this.pre = null;
            this.next = null;
        } else {
            DoubleList pre = null;
            DoubleList cur = this;
            while(cur != null){
                pre = cur;
                cur = cur.next;
            }
            cur = new DoubleList(param, pre, null);
            pre.next = cur;
        }
        this.size++;
        return this.data;
    }

    public Integer remove(int param){
        if(this.size == 0){
            System.out.println("链表为空，删除失败！");
            return null;
        }
        if(this.data == param) {
            DoubleList next = this.next;
            this.data = next != null ? next.data : null;
            this.next = next != null ? next.next : null;
            next.pre = null;
            next.next = null;
            next = this.next;
            if(next != null){
                next.pre = this;
            }
            this.size--;
        } else {
            DoubleList pre;
            DoubleList cur = this;
            DoubleList follow;
            while(cur != null) {
                if(cur.data == param){
                    pre = cur.pre;
                    follow = cur.next;
                    pre.next = follow;
                    if(follow != null){
                        follow.pre = pre;
                    }
                    cur.pre = null;
                    cur.next = null;
                    this.size--;
                    return this.data;
                }
                cur = cur.next;
            }
            System.out.println("待删除数据在链表中不存在！");
        }
        return this.data;
    }

    public Integer reverse() {
        if(this.size <= 1) {
            return this.data;
        }
        DoubleList pre = null;
        DoubleList cur = new DoubleList(this.data, this.pre, this.next);
        DoubleList follow = null;
        this.pre = null;
        this.next = null;
        while(cur != null) {
            follow = cur.next;
            cur.next = pre;
            cur.pre = follow;
            pre = cur;
            cur = follow;
        }
        this.data = pre.data;
        this.next = pre.next;
        this.pre = pre.pre;
        pre.pre = null;
        pre.next = null;
        pre = this.next;
        if(pre != null) {
            pre.pre = this;
        }
        return this.data;
    }

    public boolean contains(int param) {
        if(this.size == 0){
            return false;
        }
        DoubleList cur = this;
        while(cur != null) {
            if(cur.data == param) {
                return true;
            } else {
                cur = cur.next;
            }
        }
        return false;
    }

    public int size() {
        return this.size;
    }
}
