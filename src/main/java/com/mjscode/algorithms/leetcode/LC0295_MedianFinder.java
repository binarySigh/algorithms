package com.mjscode.algorithms.leetcode;

import java.util.PriorityQueue;

/**
 * //中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * //
 * // 例如，
 * //
 * // [2,3,4] 的中位数是 3
 * //
 * // [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * //
 * // 设计一个支持以下两种操作的数据结构：
 * //
 * // void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * // double findMedian() - 返回目前所有元素的中位数。
 * //
 * // 示例：
 * //
 * // addNum(1)
 * //addNum(2)
 * //findMedian() -> 1.5
 * //addNum(3)
 * //findMedian() -> 2
 * //
 * // 进阶:
 * //
 * // 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * // 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * //
 * // Related Topics 设计 双指针 数据流 排序 堆（优先队列）
 * @author binarySigh
 * @date 2021/8/27 22:59
 */
public class LC0295_MedianFinder {
    /** initialize your data structure here. */
    public PriorityQueue<Integer> maxHeap;
    public PriorityQueue<Integer> minHeap;

    public LC0295_MedianFinder(){
        maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        minHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num){
        if(maxHeap.size() < 1){
            maxHeap.add(num);
            return;
        }
        if(num > maxHeap.peek()){
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        if(minHeap.size() - maxHeap.size() >= 2){
            maxHeap.add(minHeap.poll());
        } else if(maxHeap.size() - minHeap.size() >= 2){
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian(){
        if(maxHeap.size() + minHeap.size() > 0){
            if(maxHeap.size() == minHeap.size()){
                return (maxHeap.peek() + minHeap.peek()) / 2.0D;
            } else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }
        return 0;
    }
}
