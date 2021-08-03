package com.mjscode.algorithms.leetcode.template;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * //给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * //
 * // 请你找出符合题意的 最短 子数组，并输出它的长度。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [2,6,4,8,10,9,15]
 * //输出：5
 * //解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,3,4]
 * //输出：0
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1]
 * //输出：0
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 104
 * // -105 <= nums[i] <= 105
 * //
 * // 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 * //
 * // Related Topics 栈 贪心 数组 双指针 排序 单调栈
 * @author binarySigh
 * @date 2021/8/3 10:39
 */
public class LC0581_FindUnsortedSubarray {

    public static void main(String[] args){
        // --> 5
        //int[] nums = {2,6,4,8,10,9,15};

        // --> 0
        //int[] nums = {1,2,3,4};

        // --> 0
        //int[] nums = {1};

        //int[] nums1 = {1,50,52,10,11,13,52,16,70};

        int[] nums1 = {-60,-52,-11,-33,58,2,5,-30,-38,-30,76};
        System.out.println(findUnsortedSubarray(nums1));
        System.out.println(compare(nums1));

        System.out.println("-----------BEGIN----------");
        for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 20) + 1;
            int[] nums = getNums(len);
            int ans = findUnsortedSubarray(nums);
            int com = compare(nums);
            if(ans != com){
                System.out.println("---------Oops!----------");
                ArrayUtils.showArray(nums);
                break;
            }
        }
        System.out.println("-----------END-------------");
    }

    /**
     * 思路：单调栈找出每个数左边离他最近比它大的数。但此方法只能找到目标子数组的最右边界
     *  最左边界的确定方法：单调栈过程中记录非递增区间中的最小值，然后单独遍历一次找到第一个出现的比最小值还大的数字位置，即左边界
     *  解答成功:
     * 		执行耗时:6 ms,击败了60.08% 的Java用户
     * 		内存消耗:39 MB,击败了96.49% 的Java用户
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        int L = nums.length, R = 0;
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        //由于数组中可能出现重复元素，因此栈中元素为链表
        LinkedList<Integer>[] stack = new LinkedList[nums.length];
        int size = -1;
        for(int i = 0; i < nums.length; i++) {
            // 单调栈过程只是为了找出待调整子数组的最右边界
            while(size >= 0 && nums[i] > nums[stack[size].peekLast()]){
                if(size - 1 >= 0){
                    //只有当弹出元素底下还有数且这个数严格大于弹出元素时才记录最右，
                    // 因为只有底下有数才能说明此时的弹出元素左边存在一个比它大的数,也才能说明该位置处于乱序区
                    R = Math.max(R, stack[size].peekLast());
                    if(nums[stack[size].peekLast()] < min) {
                        //记录最小值位置。
                        // 记录规则：如果该位置弹出后栈中还有数字，则记录最小，若弹出元素是最后一个元素则不做最小值记录
                        min = nums[stack[size].peekLast()];
                        minIdx = stack[size].peek();
                    }
                }
                size--;
            }
            if(size >= 0 && nums[i] == nums[stack[size].peekLast()]){
                stack[size].addLast(i);
            } else {
                LinkedList<Integer> cur = new LinkedList<>();
                cur.addLast(i);
                stack[++size] = cur;
            }
        }
        //如果上面 while 跑完，栈中还剩超过两个数，那么说明栈顶位置元素必然比次顶位置的元素要小，
        // 因此栈顶位置就是子数组最右边界位置
        if(size > 0){
            R = stack[size].peekLast();
            //同时要再记录一次最小
            if(nums[stack[size].peekLast()] < min){
                min = nums[stack[size].peekLast()];
                minIdx = stack[size].peekLast();
            }
        }
        //第二次循环，找出待调整子数组的最左边界
        for(int i = 0; i < minIdx; i++){
            if(nums[i] > nums[minIdx]){
                L = i;
                break;
            }
        }
        return L >= R ? 0 : (R - L + 1);
    }

    /**
     * 排序法
     * @param nums
     * @return
     */
    public static int compare(int[] nums){
        int[] copy = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int L = 0, R = nums.length - 1;
        for(; R >= 0; R--){
            if(copy[R] != nums[R]){
                break;
            }
        }
        for(; L < nums.length; L++){
            if(copy[L] != nums[L]){
                break;
            }
        }
        return L >= R ? 0 : (R - L + 1);
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 100) - (int)(Math.random() * 100);
        }
        return nums;
    }
}
