package com.mjscode.algorithms.leetcode;

/**
 * //给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * //
 * // 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应
 * //该含有冗余的括号。
 * //
 * // 示例：
 * //
 * //输入: [1000,100,10,2]
 * //输出: "1000/(100/10/2)"
 * //解释:
 * //1000/(100/10/2) = 1000/((100/10)/2) = 200
 * //但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
 * //因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
 * //
 * //其他用例:
 * //1000/(100/10)/2 = 50
 * //1000/(100/(10/2)) = 50
 * //1000/100/10/2 = 0.5
 * //1000/100/(10/2) = 2
 * //
 * // 说明:
 * //
 * // 输入数组的长度在 [1, 10] 之间。
 * // 数组中每个元素的大小都在 [2, 1000] 之间。
 * // 每个测试用例只有一个最优除法解。
 * //
 * // Related Topics 数组 数学 动态规划
 * @author binarySigh
 * @date 2021/8/18 18:34
 */
public class LC0553_OptimalDivision {
    public static void main(String[] args){
        /*int[] nums = {1,1000,100,10,2};*/
        int[] nums = {2,2,3};
        System.out.println(compare(nums));
    }

    public static String optimalDivision(int[] nums) {
        return "";
    }

    /**
     * 解答成功:
     * 		执行耗时:6 ms,击败了80.25% 的Java用户
     * 		内存消耗:36.5 MB,击败了69.75% 的Java用户
     * @param nums
     * @return
     */
    public static String compare(int[] nums){
        if(nums.length < 3){
            return nums.length == 1 ? String.valueOf(nums[0]) : (nums[0] + "/" + nums[1]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append('/').append('(');
        for(int i = 1; i < nums.length; i++){
            sb.append(nums[i]).append('/');
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }
}
