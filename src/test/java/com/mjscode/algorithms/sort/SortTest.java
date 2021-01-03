package com.mjscode.algorithms.sort;

import com.mjscode.algorithms.datastructure.Heap;
import com.mjscode.algorithms.utils.ArrayUtils;
import org.junit.Test;

public class SortTest {

    @Test
    public void inertionSortTest() {
        int[] arr = {1,2,4,3,6,3,2,7,13,9,8,10,22,54,32,41};

        ArrayUtils.showArray(arr);
        InsertionSort.sort(arr);
        ArrayUtils.showArray(arr);
    }

    @Test
    public void selectSortTest(){
        int[] arr = {1,2,4,3,6,3,2,7,13,9,8,10,22,54,32,41};

        ArrayUtils.showArray(arr);
        SelectSort.sort(arr);
        ArrayUtils.showArray(arr);
    }

    @Test
    public void mergeSortTest(){
        int[] arr = {2,1,51,23,41,99,102,72,15,33};
        ArrayUtils.showArray(MergeSort.mergeSort01(arr,0,arr.length - 1));
    }

    @Test
    public void heapSortTest(){
        int[] arr = {2,1,51,23,41,99,102,72,15,33};
        //先将给定数组构建成堆
        Heap heap = new Heap(arr.length);
        /*for(int i = 0; i < arr.length; i++){
            heap.add(arr[i]);
        }*/
        heap.addArrayFromHead(arr);
        //先打印构建好的堆
        ArrayUtils.showArray(heap.getHeapArray());
        System.out.println(heap.check());
        System.out.println("heapSize is :" + heap.size());
        //利用堆pop操作进行升序排序
        for(int i = 0; i < arr.length; i++){
            heap.pop();
        }
        ArrayUtils.showArray(heap.getHeapArray());
        System.out.println("heapSize is :" + heap.size());
    }

}
