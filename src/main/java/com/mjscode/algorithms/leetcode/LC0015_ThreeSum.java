package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
 * //复的三元组。
 * //
 * // 注意：答案中不可以包含重复的三元组。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [-1,0,1,2,-1,-4]
 * //输出：[[-1,-1,2],[-1,0,1]]
 * //
 * // 示例 2：
 * //
 * //输入：nums = []
 * //输出：[]
 * //
 * // 示例 3：
 * //
 * //输入：nums = [0]
 * //输出：[]
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 3000
 * // -105 <= nums[i] <= 105
 * //
 * // Related Topics 数组 双指针
 * @author binarySigh
 * @date 2021/4/6 22:53
 */
public class LC0015_ThreeSum {
    public static void main(String[] args){
        //int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-1,0,1,2,-4,-1,-4,0,0,2,2,2,2};
        List<List<Integer>> list = threeSum(nums);
    }

    /**
     * 暴力方法为 N^3 的时间复杂度，这里先排序，然后利用哈希表做加速，使复杂度降一阶，以达到AC要求。
     */
    /**
     * 解答成功:
     * 		执行耗时:89 ms,击败了12.22% 的Java用户
     * 		内存消耗:43.5 MB,击败了5.44% 的Java用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length <= 2){
            return list;
        }
        sort(nums, 0, nums.length - 1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        //枚举子数组过程
        for(int i = 0; i < nums.length && nums[i] <= 0;){
            List<Integer> sub = new ArrayList<>();
            if(map.get(nums[i]) > 1){
                int left = 0 - nums[i] - nums[i];
                if((left != 0 && map.get(left) != null) ||
                        (left == 0) && map.get(left) >= 3){
                    sub.add(nums[i]);
                    sub.add(nums[i]);
                    sub.add(left);
                    list.add(sub);
                    sub = new ArrayList<>();
                }
            }
            for (int j = i + map.getOrDefault(nums[i],1); j < nums.length;) {
                int left = 0 - nums[i] - nums[j];
                //防止重复枚举，也就是防止出现[-4，3，1]这种情况，因为该情况在[-4，1，3]已经枚举过。
                if(left < nums[j]){
                    break;
                }
                //正常验证
                if((left != nums[j] && map.get(left) != null) ||
                        (left == nums[j] && map.get(left) >= 2)){
                    sub.add(nums[i]);
                    sub.add(nums[j]);
                    sub.add(left);
                    list.add(sub);
                    sub = new ArrayList<>();
                }
                j += map.getOrDefault(nums[j], 1);
            }
            i += map.getOrDefault(nums[i], 1);
        }
        return list;
    }

    /*public static void sort(int[] nums, int l, int r){
        if(l == r){
            return;
        }
        int pivot = (int)(Math.random() * (r - l + 1)) + l;
        swap(nums, pivot, r);
        int i = l;
        int j = r - 1;
        int cur = l;
        while(cur < j){
            if(nums[cur] < nums[r]){
                swap(nums, cur++, i++);
            } else if(nums[cur] > nums[r]){
                swap(nums, cur, j--);
            } else {
                cur++;
            }
        }
        swap(nums, j, r);
        sort(nums, l, i);
        sort(nums, j + 1, r);
    }*/

    public static void sort(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        //随机选择一个数，将他和R位置交换，并且在本过程中作为比较基准数
        int ran = (int)(Math.random() * (R - L)) + L;
        swap(arr, ran, R);
        //小于区的右边界
        int i = L - 1;
        //大于区的左边界
        int j = R;
        //全程遍历指针
        int index = L;
        while(index < j){
            if(arr[index] < arr[R]){
                swap(arr, index++, ++i);
            } else if(arr[index] > arr[R]){
                swap(arr, index, --j);
            } else {
                index++;
            }
        }
        //不能忘了把R位置的比较基准数换到等于区下一个位置，也就是大于区第一个位置，也就是j位置
        swap(arr, j, R);
        if(i >= L){
            sort(arr, L, i);
        }
        if(j + 1 <= R){
            sort(arr, j + 1, R);
        }
    }

    public static void swap(int[] nums, int i, int j){
        if(nums[i] == nums[j]){
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
