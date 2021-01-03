package com.mjscode.algorithms.datastructure;

/**
 * 单链表
 * @author binarySigh
 */
public class SingleList {
    private Integer data;
    private int size;
    private SingleList next;

    public SingleList() {
    }

    private SingleList(Integer data, SingleList next){
        this.data = data;
        this.next = next;
    }

    /**
     * 增加节点--尾插法
     * @param param
     * @return
     */
    public Integer add(int param){
        Integer head;
        if(this.size == 0){
            this.data = param;
            this.next = null;
            this.size++;
        } else {
            SingleList cur = this;
            SingleList local = new SingleList(param, null);
            while(cur.next != null){
                cur = cur.next;
            }
            cur.next = local;
            this.size++;
        }
        head = this.data;
        return head;
    }

    /**
     * 将对象链表数据全部加入当前链表
     * @param sl
     * @return
     */
    public Integer addAll(SingleList sl){
        SingleList tmpList = new SingleList();
        SingleList slCur = sl;
        while(slCur != null){
            tmpList.add(slCur.data);
            slCur = slCur.next;
        }
        if(this.size == 0){
            this.size = tmpList.size;
            this.data = tmpList.data;
            this.next = tmpList.next;
            tmpList.next = null;
        } else {
            SingleList pre = null;
            SingleList cur = this;
            while(cur != null){
                pre = cur;
                cur = cur.next;
            }
            pre.next = tmpList;
            this.size += tmpList.size;
        }
        return this.data;
    }

    /**
     * 删除链表中元素param，并返回新链表的首节点
     * 若有多个则仅删除第一个出现的
     * @param param
     * @return
     */
    public Integer remove(int param){
        Integer head;
        if(this.size == 0){
            System.out.println("数据删除失败，链表为空！");
            return null;
        }
        if(this.data == param){
            if(this.size == 1){
                this.data = null;
                this.size--;
            } else {
                SingleList nextNode = this.next;
                this.data = nextNode.data;
                this.next = nextNode.next;
                nextNode.next = null;
                this.size--;
            }
            head = this.data;
            return head;
        }
        SingleList pre = null;
        SingleList cur = this;
        while(cur != null){
            if(cur.data == param){
                pre.next = cur.next;
                cur.next = null;
                this.size--;
                head = this.data;
                return head;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        System.out.println("删除失败！待删除节点在链表中不存在！");
        return this.data;
    }

    /**
     * 链表逆序，并返回逆序后新链表的首节点
     * @return
     */
    public Integer reverse(){
        Integer head;
        if(this.size == 0){
            System.out.println("链表为空，逆序失败！");
            return null;
        }
        SingleList pre = null;
        //此处不能直接将cur指向this的内存地址，而必须重新申请一块新的内存地址，原因如下：
        //  逆序过程中，原链表第二节点会指向this的内存空间，而逆序过程最后一步会将this.next指向原链表尾节点的内存地址，
        //  这样一来会造成循环链表而无法正确逆序
        //SingleList cur = this;
        SingleList cur = new SingleList(this.data, this.next);

        SingleList follow;
        while(cur != null){
            follow = cur.next;
            cur.next = pre;
            pre = cur;
            cur = follow;
        }
        this.data = pre.data;
        this.next = pre.next;
        head = this.data;
        return head;
    }

    /**
     * 判断链表中是否包含元素param
     * @param param
     * @return
     */
    public boolean contains(int param){
        if(this.size == 0){
            return false;
        }
        boolean isContains = false;
        SingleList cur = this;
        do{
            if(cur.data == param){
                isContains = true;
                break;
            } else {
                cur = cur.next;
            }
        } while(cur != null);
        return isContains;
    }

    /**
     * 返回链表长度
     * @return
     */
    public int size(){
        return this.size;
    }
}
