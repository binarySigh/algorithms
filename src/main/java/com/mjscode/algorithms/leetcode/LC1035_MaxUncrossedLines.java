package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * //
 * // 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * //
 * // nums1[i] == nums2[j]
 * // 且绘制的直线不与任何其他连线（非水平线）相交。
 * //
 * // 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * //
 * // 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * //
 * // 示例 1：
 * //
 * //输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * //输出：2
 * //解释：可以画出两条不交叉的线，如上图所示。
 * //但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相
 * //交。
 * //
 * // 示例 2：
 * //
 * //输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * //输出：3
 * //
 * // 示例 3：
 * //
 * //输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * //输出：2
 * //
 * // 提示：
 * //
 * // 1 <= nums1.length <= 500
 * // 1 <= nums2.length <= 500
 * // 1 <= nums1[i], nums2[i] <= 2000
 * //
 * // Related Topics 数组
 * @author binarySigh
 * @date 2021/5/21 20:57
 */
public class LC1035_MaxUncrossedLines {
    public static void main(String[] args){
        System.out.println("-----------------测试开始-----------------");
        for(int i = 0; i < 50_0000; i++){
            int len1 = (int)(Math.random() * 20) + 1;
            int len2 = (int)(Math.random() * 20) + 1;
            int[] nums1 = getNums(len1);
            int[] nums2 = getNums(len2);
            int result = maxUncrossedLines(nums1, nums2);
            int answer = compare(nums1, nums2);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错---------");
                ArrayUtils.showArray(nums1);
                ArrayUtils.showArray(nums2);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + answer);
                break;
            }
        }
        System.out.println("-----------------测试结束-----------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了12.42% 的Java用户
     * 		内存消耗:36.5 MB,击败了98.37% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        if(nums1.length == 1 && nums2.length == 1){
            return nums1[0] == nums2[0] ? 1 : 0;
        }
        int len1 = nums1.length, len2 = nums2.length;
        //dp[i][j] : nums1前i个数 跟 nums2前j个数，最多能画出几条线。
        // 不难发现，对于一个普遍位置(i,j)，他只依赖左，上，左上。依赖方式为：
        // (i,j)=Math.max( Math.max((i-1,j) ,(i, j-1)), (i-1,j-1) + (nums1[i] == nums2[j] ? 1 : 0))
        //由于依赖关系仅限于两行之内，因此用两行数组滚动求解，代替 m*n的大矩阵，节约空间
        int[][] dp = new int[2][len2];
        int curRow = 0;
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(i == 0 && j == 0){
                    dp[curRow][j] = nums1[i] == nums2[j] ? 1 : 0;
                } else if(i == 0){
                    dp[curRow][j] = Math.max(dp[curRow][j - 1], nums1[i] == nums2[j] ? 1 : 0);
                } else if(j == 0){
                    dp[curRow][j] = Math.max(dp[curRow ^ 1][j], nums1[i] == nums2[j] ? 1 : 0);
                } else {
                    dp[curRow][j] = Math.max(Math.max(dp[curRow][j - 1], dp[curRow ^ 1][j]),
                            dp[curRow ^ 1][j - 1] + (nums1[i] == nums2[j] ? 1 : 0));
                }
            }
            curRow ^= 1;
        }
        return dp[curRow ^ 1][len2 - 1];
    }

    //对数器部分
    public static int compare(int[] nums1, int[] nums2){
        if(nums1.length == 1 && nums2.length == 1){
            return nums1[0] == nums2[0] ? 1 : 0;
        }
        int len1 = nums1.length, len2 = nums2.length;
        int posShort = 0, posLong = 0;
        return process(len1 <= len2 ? nums1 : nums2, len1 <= len2 ? nums2 : nums1, posShort, posLong, 0);
    }

    public static int process(int[] nums1, int[] nums2, int posShort, int posLong, int lines){
        if(posShort == nums1.length || posLong == nums2.length){
            return lines;
        }
        int maxLines = process(nums1, nums2, posShort + 1, posLong, lines);
        for(int i = posLong; i < nums2.length; i++){
            if(nums2[i] == nums1[posShort]){
                maxLines = Math.max(maxLines, process(nums1, nums2, posShort + 1, i + 1, lines + 1));
                break;
            }
        }
        return maxLines;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 10) + 1;
        }
        return nums;
    }
}
