package com.mjscode.algorithms.leetcode.template;

import java.util.LinkedList;
import java.util.List;

/**
 * //在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 * //
 * // 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * //
 * // 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * //
 * // 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 * //
 * // 示例 1：
 * //
 * // 输入：label = 14
 * //输出：[1,3,4,14]
 * //
 * // 示例 2：
 * //
 * // 输入：label = 26
 * //输出：[1,2,6,10,26]
 * //
 * // 提示：
 * //
 * // 1 <= label <= 10^6
 * //
 * // Related Topics 树 数学 二叉树
 * @author binarySigh
 * @date 2021/7/29 19:57
 */
public class LC1104_PathInZigZagTree {

    /**
     * 借鉴完全二叉树的思路，通过实际值label找到其在完全二叉树数组中的下标 idx，并通过下标层层递进查找父节点
     * 执行结果： 通过
     *      * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.0% 的用户
     *      * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 12.8% 的用户
     * @param label
     * @return
     */
    public static List<Integer> pathInZigZagTree(int label){
        LinkedList<Integer> ans = new LinkedList<>();
        int[] info = getInfo(label);
        int level = info[2];
        // label在本层中的偏移量，从0开始计数
        int offset = (level & 1) == 1 ? (label - info[1]) : (info[0] - label);
        // label在整个完全二叉树数组中的实际下标
        int idx = info[1] - 1 + offset;
        while(level > 0){
            ans.addFirst(label);
            level--;
            //找父节点位置
            idx = (idx - 1) >> 1;
            //计算该父节点在其所处层数的下标偏移量(从左往右数，从0开始计数)
            offset = idx - ((1 << (level - 1)) - 1);
            //根据父节点所在层数及偏移量计算该父节点实际数值
            label = (level & 1) == 1 ? ((1 << (level - 1)) + offset) : ((1 << level) - 1 - offset);
        }
        return ans;
    }

    /**
     * 找到离val最近且比val小的2的N次幂。
     *   其中 ans[0] 为找到的  2的N次幂，也就是树中与val同层的最大值；
     *      ans[1] 为与 val 同层的最小值
     *      ans[2] 为 val在树中的层数,从1开始计数
     * @param val
     * @return
     */
    public static int[] getInfo(int val){
        int[] ans = new int[3];
        for(int i = 0; i < 31; i++){
            if(val < (1 << i)){
                ans[0] = (1 << i) - 1;
                ans[1] = 1 << (i - 1);
                ans[2] = i;
                break;
            }
        }
        return ans;
    }
}
