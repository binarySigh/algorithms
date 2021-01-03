package com.mjscode.algorithms.structure;

import com.mjscode.algorithms.datastructure.GreaterHeap;
import org.junit.Test;

public class HeapTest {

    @Test
    public void baseFunctionTest(){
        GreaterHeap<Integer> heap = new GreaterHeap<>(14);
        heap.poll(12);
        heap.poll(11);
        heap.poll(13);
        heap.poll(14);
        heap.poll(15);
        heap.poll(16);
        System.out.println(heap.peek());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.contains(16));
        heap.reset(16,18);
        heap.reset(13,25);
        System.out.println(heap.peek());
    }
}
