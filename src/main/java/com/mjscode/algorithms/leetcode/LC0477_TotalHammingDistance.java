package com.mjscode.algorithms.leetcode;

/**
 * //两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * //
 * // 计算一个数组中，任意两个数之间汉明距离的总和。
 * //
 * // 示例:
 * //
 * //输入: 4, 14, 2
 * //
 * //输出: 6
 * //
 * //解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * //所以答案为：
 * //HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 +
 * //2 + 2 = 6.
 * //
 * // 注意:
 * //
 * // 数组中元素的范围为从 0到 10^9。
 * // 数组的长度不超过 10^4。
 * //
 * // Related Topics 位运算
 * @author binarySigh
 */
public class LC0477_TotalHammingDistance {
    /**
     * 解题思路：统计每一位上的不同数量，再将其相加即可
     * 解答成功:
     * 		执行耗时:17 ms,击败了61.06% 的Java用户
     * 		内存消耗:39.7 MB,击败了7.74% 的Java用户
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        //过滤明显的无效输入
        if(nums == null || nums.length < 2){
            return 0;
        }
        int total = 0;
        int curCount = 0;
        for(int i = 0; i < 32; i++){
            curCount = 0;
            for(int j = 0; j < nums.length; j++){
                //遍历nums数组，找到当前第i位上为1的元素的个数
                if((nums[j] & (1 << i)) != 0){
                    curCount++;
                }
            }
            //当前位上产生的距离计算方式：当前位是1的元素个数*当前位是0的元素个数
            total += curCount * (nums.length - curCount);
        }
        return total;
    }
}
