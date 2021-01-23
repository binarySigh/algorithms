package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.*;

/**
 * //给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * //
 * // 说明：
 * //
 * // 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * //
 * // 示例 1:
 * //
 * // 输入: [2,2,3,2]
 * //输出: 3
 * //
 * // 示例 2:
 * //
 * // 输入: [0,1,0,1,0,1,99]
 * //输出: 99
 * // Related Topics 位运算
 *
 * @author binarySigh
 */
public class LC0137_SingleNumber {

    public static void main(String[] args){
        System.out.println("--------测试开始-------");
        for(int i = 0; i < 50_0000; i++){
            int[] arr = generateArray(300, 40);
            int expected = compare(arr);
            int result = singleNumber(arr);
            if(expected != result){
                System.out.println("------第[" + i + "]次结果出现错误-------");
                ArrayUtils.showArray(arr);
                System.out.println("期望结果：" + expected);
                System.out.println("测试结果：" + result);
            }
        }
        System.out.println("--------测试结束-------");
    }

    /**
     * 思路：申请一个32位的数组，代表数字的32位，最后统计每个位上的数出现次数，还原出只出现了一次的数
     * 优化思路：是否可以先使用排序，再去找只出现一次的数
     *解答成功:
     * 		执行耗时:4 ms,击败了39.94% 的Java用户
     * 		内存消耗:38.5 MB,击败了11.60% 的Java用户
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums){
        //处理明显的特殊输入
        if(nums == null || nums.length < 4){
            //之所以这里返回nums[0]，是为了使输入数组只有一个出现了一次的元素时，返回结果能正确
            return nums[0];
        }
        int[] bits = new int[32];
        int tmp = 0;
        //遍历 nums 数组，统计数组中所有数二进制位的出现频次
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < 32; j++){
                if((nums[i] & (1 << j)) != 0){
                    bits[j] ++;
                }
            }
        }
        //遍历统计数组，还原出只出现一次的数
        for(int j = 0; j < 32; j++){
            if(bits[j] % 3 == 1){
                tmp |= (1 << j);
            }
        }
        return tmp;
    }

    /**
     * 对数器
     * @param nums
     * @return
     */
    public static int compare(int[] nums){
        if(nums == null || nums.length < 4){
            //之所以这里返回nums[0]，是为了使输入数组只有一个出现了一次的元素时，返回结果能正确
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>(nums.length / 3 + 1);
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int result = 0;
        Iterator<Integer> ite = map.keySet().iterator();
        while(ite.hasNext()){
            int cur = ite.next();
            if(map.get(cur) == 1){
                result = cur;
                break;
            }
        }
        return result;
    }

    /**
     * 生成题设数组
     * @param range 元素范围，[-range,range]
     * @param tribleCounts 出现三次的数的总种数
     * @return
     */
    public static int[] generateArray(int range, int tribleCounts){
        int[] arr = new int[tribleCounts * 3 + 1];
        Set<Integer> set = new HashSet<>();
        arr[0] = (int)(Math.random() * (range + 1)) - (int)(Math.random() * (range + 1));
        set.add(arr[0]);
        for(int i = 1; i < arr.length;){
            int cur = 0;
            do{
                cur = (int)(Math.random() * (range + 1)) - (int)(Math.random() * (range + 1));
            } while(set.contains(cur));
            set.add(cur);
            int j = i + 3;
            for(; i < j; i++){
                arr[i] = cur;
            }
        }
        return arr;
    }
}
