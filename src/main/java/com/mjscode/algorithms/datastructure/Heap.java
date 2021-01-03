package com.mjscode.algorithms.datastructure;

import java.util.PriorityQueue;

/**
 * 堆，也叫做PriorityQueue
 * 基本概念：
 *   1） 是完全二叉树。即二叉树每一层都是从左到右依次填充节点，且只有最后一层可能不满
 *   2） 大根堆：每一棵子树的最大值等于该子树头节点的值
 *       小根堆：每一棵子树的最小值等于该子树头节点的值
 *
 * 特性：
 *   1） 位置i上的父节点位置为 (i-1)/2
 *   2)  任意节点i的左右两个子节点为 2*i+1，2*(i+1)
 *
 *  堆是一种逻辑数据结构，底层用数组实现
 *   注：系统内置的 PriorityQueue在不指定比较逻辑的情况下，默认按照小根堆来组织数据
 * @author binarySigh
 */
public class Heap {

    private int heapSize; //实际堆长度
    //private int[] heaps = new int[1000];
    private int[] heaps;
    private int capcity; //实际数组长度

    public Heap(){
    }

    public Heap(int capcity) {
        this.capcity = capcity;
        this.heaps = new int[capcity];
    }

    /**
     * 维持大根堆特性的 堆插入操作
     * 当前节点与自己头节点比较，若大则与头节点比较，然后继续比较；若不大，则停住
     */
    private int[] heapInsert(int index){
        //index节点与自己的头节点比较，若大则与其交换位置，并修改index为其头节点位置进行下一轮比较
        //直到 不再比头节点大 || 当前index已来到0位置 为止
        while(this.heaps[index] > this.heaps[(index - 1) / 2]) {
            this.heaps = swapNodes(this.heaps, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
        return this.heaps;
    }

    /**
     * 维持大根堆特性的 堆节点下沉操作
     * 当前节点与左右子树头节点比较，选择其中较大的一个，若比当前节点大则交换，换完继续比较；若不大或触底就停住
     */
    private int[] heapIfy(int index){
        int nextIndex;
        //若当前节点左子节点位置已经越界，则说明当前位置已是本子树最底节点，退出循环
        while(2 * index + 1 <= this.heapSize - 1) {
            //进入while循环说明index节点左子节点在heap结构中，那么需要再判定右子节点是否也在heap中即可
            if(2 * (index + 1) > this.heapSize - 1) {
                // 进入此 if 说明右子节点已越界
                if(this.heaps[index] < this.heaps[2 * index + 1]) {
                    //右子节点已越界，且左子节点比自己大，交换
                    this.heaps = swapNodes(this.heaps, index, 2 * index + 1);
                    index = 2 * index + 1;
                } else {
                    // 右子节点已越界，左子节点不越界且比自己小，则说明已不可能再有交换的情况发生，直接打破循环
                    break;
                }
            } else {
                //进入此 else 说明左右子节点都没越界，则找出两个子节点中较大的一个，与当前节点比较，
                // 若比当前节点大则交换，继续下一轮比较; 若不比当前节点大，则说明当前已满足大根堆限制，无需其他操作直接打破循环
                nextIndex = this.heaps[2 * index + 1] > this.heaps[2 * (index + 1)] ? 2 * index + 1 : 2 * (index + 1);
                if(this.heaps[index] < this.heaps[nextIndex]) {
                    this.heaps = swapNodes(this.heaps, index, nextIndex);
                    index = nextIndex;
                } else {
                    break;
                }
            }
        }
        return this.heaps;
    }

    /**
     * 将目标数组加入当前的堆中，并完成构建<BR/>
     *  从数组0位置到最后逐个添加并以heapIsert方式构建大根堆<BR/>
     * @param arr
     */
    public void addArrayFromHead(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            this.heaps[this.heapSize] = arr[i];
            this.heaps = heapInsert(this.heapSize);
            this.heapSize++;
        }
    }

    /**
     * 弹出最大值，剩余部分要继续维持大根堆特性
     */
    public int pop(){
        //记录当前堆最大值，即需pop的节点
        int popVal = this.heaps[0];
        //将堆根节点与堆尾节点互换位置，并将heapSize-1，使弹出元素逻辑上与原堆数据分离
        this.heaps = swapNodes(this.heaps, 0, this.heapSize - 1);
        this.heapSize--;
        //将换上来的堆首节点做下沉操作以保持整个堆大根堆的特性
        this.heaps = heapIfy(0);
        return popVal;
    }

    /**
     * 加入新节点数据，需要维持大根堆特性
     */
    public void add(int val){
        //将节点插入到当前堆尾节点的下标+1处
        this.heaps[this.heapSize] = val;
        //对插入的新节点做 heapInsert操作，使整个堆维持大根堆特性
        this.heaps = heapInsert(this.heapSize);
        //插入完成后将堆大小+1
        this.heapSize++;
    }


    /**
     * 检查当前构建的堆是否满足大根堆特性<BR/>
     * true - 是<BR/>
     * false - 否<BR/>
     * @return
     */
    public boolean check(){
        boolean isHeap = true;
        for(int i = this.heapSize - 1; i > 0; i--) {
            //若当前节点比自己父节点大，则当前构建的不满足大根堆特性，打破循环返回false
            if(this.heaps[i] > this.heaps[(i-1)/2]) {
                isHeap = false;
                break;
            }
        }
        return isHeap;
    }

    /**
     * 返回当前堆大小
     * @return
     */
    public int size(){
        return this.heapSize;
    }

    /**
     * 返回堆数组
     * @return
     */
    public int[] getHeapArray(){
        return this.heaps;
    }

    /**
     * 交换数组中i和j位置的两个数，返回交换后的数组
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int[] swapNodes(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        return arr;
    }

}
