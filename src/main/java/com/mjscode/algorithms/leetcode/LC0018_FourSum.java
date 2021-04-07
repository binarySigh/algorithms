package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * //给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
 * // d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * //
 * // 注意：答案中不可以包含重复的四元组。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,0,-1,0,-2,2], target = 0
 * //输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [], target = 0
 * //输出：[]
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 200
 * // -109 <= nums[i] <= 109
 * // -109 <= target <= 109
 * //
 * // Related Topics 数组 哈希表 双指针
 * @author binarySigh
 * @date 2021/4/7 22:23
 */
public class LC0018_FourSum {

    public static void main(String[] args){
        /*int[] nums = {1,0,-1,0,-2,2};
        int target = 0;*/
        int[] nums = {1,0,-1,0,-2,-2,-2,-2,2};
        int target = -8;
        List<List<Integer>> list = fourSum(nums, target);
    }

    /**
     * 解答成功:
     * 		执行耗时:177 ms,击败了 5.35% 的Java用户
     * 		内存消耗:39.5 MB,击败了 5.05% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length < 4){
            return list;
        }
        //排序
        sort(nums, 0, nums.length - 1);
        //装填辅助哈希表。key-数组中的元素i;value-元素i右边最近的与它不同的元素的下标
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(i == nums.length - 1){
                map.put(nums[i], nums.length);
                break;
            }
            if(nums[i] != nums[i + 1]){
                map.put(nums[i], i + 1);
            }
        }
        //遍历查找
        List<Integer> sub = new ArrayList<>();
        for(int i = 0; i < nums.length;){
            for(int j = i + 1; j < nums.length - 2;){
                for(int t = j + 1; t < nums.length - 1;){
                    int left = target - nums[i] - nums[j] - nums[t];
                    //判断四元组要有效的条件：
                    // 1. left要在map中存在
                    // 2. 找到的left必须在t的下下个位置及之后，保证这个left必须是有效的，而不能是i,j,t甚至之前的位置
                    if(map.get(left) != null && nums[t + 1] <= left){
                        sub.add(nums[i]);
                        sub.add(nums[j]);
                        sub.add(nums[t]);
                        sub.add(left);
                        list.add(sub);
                        sub = new ArrayList<>();
                    }
                    t = map.get(nums[t]);
                }
                j = map.get(nums[j]);
            }
            i = map.get(nums[i]);
        }
        return list;
    }

    public static void sort(int[] nums, int l, int r){
        if(l == r){
            return;
        }
        int pivot = (int)(Math.random() * (r - l + 1)) + l;
        swap(nums, pivot, r);
        int i = l - 1;
        int j = r;
        int index = l;
        while(index < j){
            if(nums[index] < nums[r]){
                swap(nums, index++, ++i);
            } else if(nums[index] > nums[r]){
                swap(nums, index, --j);
            } else{
                index++;
            }
        }
        swap(nums, j, r);
        if(l < i){
            sort(nums, l, i);
        }
        if(j + 1 < r) {
            sort(nums, j + 1, r);
        }
    }

    public static void swap(int[] nums, int i, int j){
        if(nums[i] != nums[j]){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
