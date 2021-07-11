package com.mjscode.algorithms.leetcode;

import java.util.Arrays;

/**
 * //给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * //
 * // h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引
 * //用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
 * //
 * // 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * //
 * // 示例：
 * //
 * //输入：citations = [3,0,6,1,5]
 * //输出：3
 * //解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * //     由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * //
 * // 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 * // Related Topics 数组 计数排序 排序
 * @author binarySigh
 * @date 2021/7/11 16:51
 */
public class LC0274_HIndex {
    public static void main(String[] args){
        // --> 3
        //int[] citations = {3,0,6,1,5};

        //int[] citations = {3,0,6,1,5,5,5,4};

        // --> 1
        //int[] citations = {1,3,1};

        // --> 1
        //int[] citations = {100};

        // --> 0
        //int[] citations = {0,0};

        int[] citations = {4,4,4,4};
        System.out.println(hIndex(citations));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了87.91% 的Java用户
     * 		内存消耗:36.4 MB,击败了33.16% 的Java用户
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for(int i = citations.length - 1; i >= 0;){
            if(citations[i] >= h + 1){
                h++;
            } else {
                break;
            }
            i--;
        }
        return h;
    }
}
