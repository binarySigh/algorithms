package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashSet;

/**
 *
 * //存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * //
 * // 如果 nums[i] 是正数，向前 移动 nums[i] 步
 * // 如果 nums[i] 是负数，向后 移动 nums[i] 步
 * //
 * // 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * //
 * // 数组中的 循环 由长度为 k 的下标序列 seq ：
 * //
 * // 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * // 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * // k > 1
 * //
 * // 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [2,-1,1,2,2]
 * //输出：true
 * //解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [-1,2]
 * //输出：false
 * //解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * //
 * // 示例 3:
 * //
 * //输入：nums = [-2,1,-1,-2,-2]
 * //输出：false
 * //解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
 * //所有 nums[seq[j]] 应当不是全正就是全负。
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 5000
 * // -1000 <= nums[i] <= 1000
 * // nums[i] != 0
 * //
 * // 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
 * // Related Topics 数组 哈希表 双指针
 * @author binarySigh
 * @date 2021/8/7 9:37
 */
public class LC0457_CircularArrayLoop {
    public static void main(String[] args){
        // --> true;
        //int[] nums1 = {2,-1,1,2,2};

        // --> false
        //int[] nums1 = {-1,2};

        // --> false
        //int[] nums1 = {-2,1,-1,-2,-2};

        //int[] nums1 = getNums(10);
        int[] nums1 = {-2,3,-1,-3,4};
        ArrayUtils.showArray(nums1);
        System.out.println("对照组：" + compare(nums1));
        System.out.println("算法组：" + circularArrayLoop(nums1));

        System.out.println("-----------------TEST BEGIN!-------------------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 30) + 1;
            int[] nums = getNums(len);
            int[] copy = new int[len];
            for(int j = 0; j < len; j++){
                copy[j] = nums[j];
            }
            boolean com = compare(nums);
            boolean ans = circularArrayLoop(copy);
            if(com != ans){
                System.out.println("------------Oops!-----------");
                ArrayUtils.showArray(nums);
                break;
            }
        }
        System.out.println("-----------------TEST END!-------------------");
    }

    /**
     * 仍然是对照方法所采取的大思路，找一个点往下跳。但策略有所不同：
     *  1. 若在一条路上发现跳到了终点而未入环，则将这一条路上除终点外的所有点都标记为 2000 + 轮次数；
     *  2. 遍历过程中若起点 > 2000 则直接跳过，若下一步 > 2000直接终止这条路。
     *
     *  解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.8 MB,击败了71.03% 的Java用户
     * @param nums
     * @return
     */
    public static boolean circularArrayLoop(int[] nums) {
        int path = ((1000 / nums.length) + 1) * nums.length;
        int j = 0;
        int preIdx = 0;
        int begin = 0;
        int next = 0;
        // 遍历过程中将本轮遍历过的所有值都修改为 2000 + i (i为轮次数)
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= 2000){
                continue;
            }
            j = i;
            begin = nums[i];
            while(nums[j] < 2000){
                preIdx = j;
                next = (nums[j] + j + path) % nums.length;
                nums[j] = 2000 + i;
                j = next;
                if((nums[j] ^ begin) < 0){
                    break;
                }
            }
            if(j != preIdx && nums[j] == 2000 + i){
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 		执行耗时:62 ms,击败了7.48% 的Java用户
     * 		内存消耗:38.4 MB,击败了5.14% 的Java用户
     * @param nums
     * @return
     */
    public static boolean compare(int[] nums){
        int path = ((1000 / nums.length) + 1) * nums.length;
        for(int i = 0; i < nums.length; i++){
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            int j = (nums[i] + i + path) % nums.length;
            if(j == i || (nums[i] ^ nums[j]) < 0){
                continue;
            }
            while(!set.contains(j)) {
                set.add(j);
                j = (nums[j] + j + path) % nums.length;
                if((nums[i] ^ nums[j]) < 0) {
                    break;
                }
            }
            if((nums[j] + j + path) % nums.length != j && (nums[i] ^ nums[j]) >= 0){
                return true;
            }
        }
        return false;
    }

    /**
     * 每个位置不能有 0
     * @param len
     * @return
     */
    private static int[] getNums(int len) {
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            do{
                nums[i] = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
            } while(nums[i] == 0);
        }
        return nums;
    }
}
