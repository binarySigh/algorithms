package com.mjscode.algorithms.leetcode;

/**
 * //给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
 * //
 * // h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别
 * //被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
 * //
 * // 示例:
 * //
 * // 输入: citations = [0,1,3,5,6]
 * //输出: 3
 * //解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 * //     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * //
 * // 说明:
 * //
 * // 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 * //
 * // 进阶：
 * //
 * // 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
 * // 你可以优化你的算法到对数时间复杂度吗？
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/7/12 21:01
 */
public class LC0275_HIndex {
    public static void main(String[] args){
        // --> 3
        //int[] citations = {0,1,3,5,6};

        int[] citations = {4,5,5,5,5,5,6};
        System.out.println(hIndex(citations));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:45.4 MB,击败了22.41% 的Java用户
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        int h = 0;
        int L = 0, R = citations.length - 1;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            int add = R - mid + 1;
            if(citations[mid] >= h + add){
                h += add;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return h;
    }
}
