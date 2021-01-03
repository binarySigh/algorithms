package com.mjscode.algorithms.datastructure;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * 加强堆。默认大根堆
 * @author binarySigh
 */
public class GreaterHeap<T> {
    private Object[] heap;
    private HashMap<T, Integer> indexMap;
    private int capcity;
    private int heapSize;
    private final Comparator<? super T> comparator;
    private final int DEFAULT_CAPCITY = 11;

    public GreaterHeap(){
        this.capcity = DEFAULT_CAPCITY;
        this.heap = new Object[capcity];
        this.indexMap = new HashMap<>(capcity);
        this.comparator = null;
    }

    public GreaterHeap(int cap) {
        this.capcity = cap;
        this.heap = new Object[cap];
        this.indexMap = new HashMap<>(cap);
        this.comparator = null;
    }

    public GreaterHeap(Comparator<? super T> comp){
        this.capcity = DEFAULT_CAPCITY;
        this.heap = new Object[DEFAULT_CAPCITY];
        this.indexMap = new HashMap<>(DEFAULT_CAPCITY);
        this.comparator = comp;
    }

    public GreaterHeap(int cap, Comparator<? super T> comp){
        this.capcity = cap;
        this.heap = new Object[cap];
        this.indexMap = new HashMap<>(cap);
        this.comparator = comp;
    }

    public void poll(T t){
        if(!indexMap.containsKey(t)) {
            heap[heapSize++] = t;
            indexMap.put(t, heapSize - 1);
            //维持堆特性
            heapInsert(heapSize - 1);
        }
    }

    public T peek(){
        return (T)heap[0];
    }

    public T pop(){
        T t = (T)heap[0];
        if(heapSize > 0){
            swap(0, --heapSize);
            //清除弹出元素在堆数组和位置表中的记录，释放内存
            heap[heapSize] = null;
            indexMap.remove(t);
            //下沉操作，维持堆特性
            heapIfy(0);
        } else {
            return null;
        }
        return t;
    }

    /**
     * 更新堆中元素。
     *      若新元素存在则不做操作；
     *      若旧元素不存在，则直接插入新元素；否则执行更新操作
     * @param old
     * @param update
     */
    public void reset(T old, T update){
        if(indexMap.containsKey(update)){
            return;
        }
        if(!indexMap.containsKey(old)){
            poll(update);
        } else {
            //获取元素位置
            int index = indexMap.get(old);
            //更新堆和位置表中记录
            heap[index] = update;
            indexMap.remove(old);
            indexMap.put(update, index);
            //检查堆特性，并调整
            check(index);
        }
    }

    public boolean contains(T t){
        return indexMap.containsKey(t);
    }

    public int size(){
        return heapSize;
    }

    private void check(int index){
        heapInsert(index);
        heapIfy(index);
    }

    private void heapIfy(int index){
        if(comparator != null){
            heapIfyWithComparator(index);
        } else {
            heapIfyWithComparable(index);
        }
    }

    private void heapIfyWithComparator(int index){
        while(((index << 1 | 1) < heapSize && comparator.compare((T)heap[index], (T)heap[index << 1 | 1]) <= 0) ||
                ((index << 1) < heapSize) && comparator.compare((T)heap[index], (T)heap[index << 1]) <= 0){
            if((index << 1 | 1) < heapSize && (index << 1) < heapSize){
                index = swap(index, comparator.compare((T)heap[index << 1], (T)heap[index << 1 | 1]) >= 0 ? (index << 1 | 1) : (index << 1));
            } else if((index << 1 | 1) >= heapSize || (index << 1) >= heapSize){
                index = swap(index, (index << 1) >= heapSize ? (index << 1 | 1) : (index << 1));
            }
        }
    }

    private void heapIfyWithComparable(int index){
        Comparable<? super T> key = (Comparable<? super T>)heap[index];
        while(((index << 1 | 1) < heapSize && key.compareTo((T)heap[index << 1 | 1]) <= 0) ||
                ((index << 1) < heapSize) && key.compareTo((T)heap[index << 1]) <= 0){
            if((index << 1 | 1) < heapSize && (index << 1) < heapSize){
                key = (Comparable<? super T>)heap[index << 1];
                index = swap(index, key.compareTo((T)heap[index << 1 | 1]) >= 0 ? (index << 1) : (index << 1 | 1));
            } else if((index << 1) >= heapSize || (index << 1 | 1) >= heapSize){
                index = swap(index, (index << 1) >= heapSize ? (index << 1 | 1) : (index << 1));
            }
            key = (Comparable<? super T>)heap[index];
        }
    }

    private void heapInsert(int index){
        if(comparator != null){
            heapInsertWithComparator(index);
        } else {
            heapInsertComparable(index);
        }
    }

    private void heapInsertWithComparator(int index){
        while((index >> 1) >= 0 && comparator.compare((T)heap[index], (T)heap[index >> 1]) > 0){
            index = swap(index, (index >> 1));
        }
    }

    private void heapInsertComparable(int index){
        Comparable<? super T> key = (Comparable<? super T>) heap[index];
        while((index >> 1) >= 0 && key.compareTo((T)heap[index >> 1]) > 0){
            index = swap(index, index >> 1);
        }
    }

    /**
     * 交换两个位置上的元素
     * @param index 发起交换的元素下标
     * @param target 交换目标下标
     * @return 交换后发起者下标
     */
    private int swap(int index, int target){
        T t = (T)heap[index];
        heap[index] = heap[target];
        heap[target] = t;
        indexMap.put((T)heap[index], index);
        indexMap.put((T)heap[target], target);
        return target;
    }

}
