package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;

/**
 * //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
 * //房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * //
 * // 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [2,3,2]
 * //输出：3
 * //解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,3,1]
 * //输出：4
 * //解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * //     偷窃到的最高金额 = 1 + 3 = 4 。
 * //
 * // 示例 3：
 * //
 * //输入：nums = [0]
 * //输出：0
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 100
 * // 0 <= nums[i] <= 1000
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 */
public class LC0213_Rob {

    public static void main(String[] args){
        //int[] nums = {1,4,2,5,9,3,0,13,4,5};
        //int[] nums = {1,2,3,1};
        //int[] nums = {0,0,1,3,4};
        //int[] nums = {2,1,2,6,1,8,10,10};
        //int[] nums = {200,234,182,111,87,194,221,217,71,162,140,51,81,80,232,193,223,103,139,103};
        //int[] nums = {272,447,266,128,41};
        /*int maxRob = process(nums, false, false, 0, 0);
        int rob = rob(nums);
        ArrayUtils.showArray(nums);
        System.out.println("测试结果：" + rob);
        System.out.println("期望结果：" + maxRob);*/
        System.out.println("-------------test begin-------------");
        for(int i = 0; i < 100000; i++){
            int[] nums = ArrayUtils.generateArray(500, 5, false);
            int maxRob = process(nums, false, false, 0, 0);
            int rob = rob(nums);
            if(maxRob != rob) {
                ArrayUtils.showArray(nums);
                System.out.println("测试结果：" + rob);
                System.out.println("期望结果：" + maxRob);
                break;
            }
        }
        System.out.println("-------------test end-------------");
    }

    /**
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:1 ms,击败了5.84% 的Java用户
     * 		内存消耗:35.8 MB,击败了51.47% 的Java用户
     * @param nums
     * @return
     */
    public static int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 2 ){
            return Math.max(nums[0],nums[1]);
        }
        if(nums.length == 3 ){
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }
        if(nums.length == 1){
            return nums[0];
        }
        int max = 0;
        //dp表，含义如下：
        //  1. maxRob[i][0]:包含当前位置金额的最大值
        //  2. maxRob[i][1]:包含当前位置金额的最大值对应的方案中是否包含第一间房,为0代表包含，为1代表不包含
        //  1. maxRob[i][2]:不包含当前位置金额的最大值
        //  1. maxRob[i][3]:不包含当前位置金额的最大值对应的方案中是否包含第一间房,为0代表包含，为1代表不包含
        int[][] maxRob = new int[nums.length][4];
        //初始化dp表前两间房对应的情况
        maxRob[0][0] = nums[0];
        //这里防止第一间房金额为0，结果还取了，然后影响最后一间房时候的计算结果
        //  所以如果第一间房金额为0，都一律默认不取第一间房
        maxRob[0][1] = nums[0] == 0 ? 1 : 0;
        maxRob[0][2] = 0;
        maxRob[0][3] = 1;
        maxRob[1][0] = nums[1];
        maxRob[1][1] = 1;
        maxRob[1][2] = nums[0];
        maxRob[1][3] = 0;
        //处理后面房间的情况
        for(int i = 2; i < nums.length; i++){
            //处理包含当前房间金额的情况
            //也就是上一间房间的情况不考虑，看上上间房间情况的最大值
            if(i != nums.length - 1 || (maxRob[i - 2][1] == 1 && maxRob[i - 2][3] == 1)) {
                //当前不是最后一间房，或者是最后一间但是上上间房的两种情况都不包含第一间房
                maxRob[i][0] = Math.max(maxRob[i - 2][0], maxRob[i - 2][2]) + nums[i];
                maxRob[i][1] = maxRob[i - 2][0] >= maxRob[i - 2][2] ? maxRob[i - 2][1] : maxRob[i - 2][3];
            } else {
                //如果当前是最后一间房，则直接从上上间房间找不包含第一间房的方案来计算
                if(maxRob[i - 2][1] == 0 && maxRob[i - 2][3] == 0){
                    //上上间房两种方案都包含第一间房，那么比较以下两个数大小
                    // maxRob[i - 2]最大值 + nums[i] - nums[0] <-> maxRob[i - 2]最大值
                    // 如果前者大，就用最大值加上首尾差；如果后者大就不动
                    maxRob[i][0] = Math.max(maxRob[i - 2][2], maxRob[i - 2][0]);
                    maxRob[i][0] = nums[i] - nums[0] > 0 ? maxRob[i][0] + nums[i] - nums[0] : maxRob[i][0];
                    maxRob[i][1] = nums[i] - nums[0] > 0 ? 1 : 0;
                } else if(maxRob[i - 2][1] == 0){
                    maxRob[i][0] = Math.max(maxRob[i - 2][2] + nums[i], maxRob[i - 2][0]);
                    maxRob[i][0] = Math.max(maxRob[i][0], maxRob[i - 2][0] + nums[i] - nums[0]);
                    maxRob[i][1] = maxRob[i - 2][2] + nums[i] >= maxRob[i - 2][0] ? 1 : maxRob[i - 2][0];
                } else if(maxRob[i - 2][3] == 0){
                    maxRob[i][0] = Math.max(maxRob[i - 2][0] + nums[i], maxRob[i - 2][2]);
                    maxRob[i][0] = Math.max(maxRob[i][0], maxRob[i - 2][2] + nums[i] - nums[0]);
                    maxRob[i][1] = maxRob[i - 2][0] + nums[i] >= maxRob[i - 2][2] ? 1 : maxRob[i - 2][3];
                }
            }
            //处理不包含当前房间金额的情况
            //考虑取上一间房间的最大值
            maxRob[i][2] = Math.max(maxRob[i - 1][0], maxRob[i - 1][2]);
            maxRob[i][3] = maxRob[i - 1][0] >= maxRob[i - 1][2] ? maxRob[i - 1][1] : maxRob[i - 1][3];
        }
        //先记录从头到尾遍历过程中的最大值
        max = Math.max(maxRob[nums.length - 1][0], maxRob[nums.length - 1][2]);
        //按照上面的方式和情况，从尾到头再次遍历重新一轮动态规划查找可能性
        maxRob[nums.length - 1][0] = nums[nums.length - 1];
        maxRob[nums.length - 1][1] = nums[nums.length - 1] == 0 ? 1 : 0;
        maxRob[nums.length - 1][2] = 0;
        maxRob[nums.length - 1][3] = 1;
        maxRob[nums.length - 2][0] = nums[nums.length - 2];
        maxRob[nums.length - 2][1] = 1;
        maxRob[nums.length - 2][2] = nums[nums.length - 1];
        maxRob[nums.length - 2][3] = 0;
        for(int i = nums.length - 3; i >= 0; i--){
            if(i != 0 ||  (maxRob[i + 2][1] == 1 && maxRob[i + 2][3] == 1)){
                maxRob[i][0] = Math.max(maxRob[i + 2][0], maxRob[i + 2][2]) + nums[i];
                maxRob[i][1] = maxRob[i + 2][0] >= maxRob[i + 2][2] ? maxRob[i + 2][1] : maxRob[i + 2][3];
            } else {
                //如果当前是最后一间房，则直接从上上间房间找不包含第一间房的方案来计算
                if(maxRob[i + 2][1] == 0 && maxRob[i + 2][3] == 0){
                    //上上间房两种方案都包含第一间房，那么比较以下两个数大小
                    // maxRob[i - 2]最大值 + nums[i] - nums[0] <-> maxRob[i - 2]最大值
                    // 如果前者大，就用最大值加上首尾差；如果后者大就不动
                    maxRob[i][0] = Math.max(maxRob[i + 2][2], maxRob[i + 2][0]);
                    maxRob[i][0] = nums[0] - nums[i] > 0 ? maxRob[i][0] + nums[0] - nums[i] : maxRob[i][0];
                    maxRob[i][1] = nums[0] - nums[i] > 0 ? 1 : 0;
                } else if(maxRob[i + 2][1] == 0){
                    maxRob[i][0] = Math.max(maxRob[i + 2][2] + nums[i], maxRob[i + 2][0]);
                    maxRob[i][0] = Math.max(maxRob[i][0], maxRob[i + 2][0] + nums[0] - nums[i]);
                    maxRob[i][1] = maxRob[i + 2][2] + nums[i] >= maxRob[i + 2][0] ? 1 : maxRob[i + 2][0];
                } else if(maxRob[i + 2][3] == 0){
                    maxRob[i][0] = Math.max(maxRob[i + 2][0] + nums[i], maxRob[i + 2][2]);
                    maxRob[i][0] = Math.max(maxRob[i][0], maxRob[i + 2][2] + nums[0] - nums[i]);
                    maxRob[i][1] = maxRob[i + 2][0] + nums[i] >= maxRob[i + 2][2] ? 1 : maxRob[i + 2][3];
                }
            }
            maxRob[i][2] = Math.max(maxRob[i + 1][0], maxRob[i + 1][2]);
            maxRob[i][3] = maxRob[i + 1][0] >= maxRob[i + 1][2] ? maxRob[i + 1][1] : maxRob[i + 1][3];
        }
        max = Math.max(max, Math.max(maxRob[0][0], maxRob[0][2]));
        return max;
    }

    /**
     * 暴力递归解法
     * @param nums
     * @param hasPre
     * @param hasFirst
     * @param total
     * @param index
     * @return
     */
    public static int process(int[] nums, boolean hasPre, boolean hasFirst, int total, int index){
        //base case
        if(index >= nums.length){
            return total;
        }
        if(index == nums.length - 1){
            if(hasFirst || hasPre){
                return total;
            } else {
                return total + nums[index];
            }
        }
        //递归计算
        int withNow = 0;
        int withoutNow = 0;
        if(index == 0){
            withNow = process(nums, true, true, total + nums[index], index + 1);
            withoutNow = process(nums, false, false, total, index + 1);
        } else {
            if(!hasPre) {
                withNow = process(nums, true, hasFirst, total + nums[index], index + 1);
                withoutNow = process(nums, false, hasFirst, total, index + 1);
            } else if(hasPre){
                withoutNow = process(nums, false, hasFirst, total, index + 1);
                return withoutNow;
            }
        }
        return Math.max(withNow, withoutNow);
    }
}
