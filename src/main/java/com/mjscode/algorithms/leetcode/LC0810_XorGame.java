package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或
 * //运算得出的结果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
 * //
 * // 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
 * //
 * // 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
 * //
 * // 示例：
 * //
 * //输入: nums = [1, 1, 2]
 * //输出: false
 * //解释:
 * //Alice 有两个选择: 擦掉数字 1 或 2。
 * //如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数
 * //字的人，她总是会输。
 * //如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
 * //
 * // 提示：
 * //
 * // 1 <= N <= 1000
 * // 0 <= nums[i] <= 2^16
 * //
 * // Related Topics 数学
 * @author binarySigh
 * @date 2021/5/22 8:57
 */
public class LC0810_XorGame {

    public static void main(String[] args){
        //int[] nums = {38,1,16,8};
        // -> false
        //int[] nums = {1,1,2,3,4};
        //int[] nums = getNums(4);
        //int[] nums = {1, 1, 2};
        /*long begin = System.nanoTime();
        boolean result = compare(nums);
        long end = System.nanoTime();
        System.out.println(result);
        System.out.println("Time cost: " + (end - begin) / 1000000);*/

        /*for(int i = 0; i < 100; i++){
            int[] nums = getNums(5);
            boolean result = compare(nums);
            //System.out.println(result);
            if(result){
                System.out.println("--------第" + i + "]次 先手能赢----------");
                ArrayUtils.showArray(nums);
                System.out.println(getXor(nums));
                System.out.println(result);
                //break;
            }
        }
        System.out.println("TEST END!");*/

        System.out.println("------------测试开始-----------");
        for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 9);
            int[] nums = getNums(len);
            boolean myResult = xorGame(nums);
            boolean compare = compare(nums);
            if(myResult != compare){
                System.out.println("--------第[" + i + "]次实验出错！--------");
                ArrayUtils.showArray(nums);
                System.out.println("正确答案：" + compare);
                System.out.println("我的答案：" + myResult);
                break;
            }
        }
        System.out.println("------------测试结束-----------");
    }

    /**
     * 打表找规律
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.3 MB,击败了23.17% 的Java用户
     * @param nums
     * @return
     */
    public static boolean xorGame(int[] nums) {
        if(nums == null || nums.length % 2 == 0){
            return true;
        }
        int xor = 0;
        for(int i : nums){
            xor ^= i;
        }
        return xor == 0;
    }

    public static boolean compare(int[] nums){
        if(nums == null || nums.length == 0){
            return true;
        }
        int xor = nums[0];
        for(int i = 1; i < nums.length; i++){
            xor ^= nums[i];
        }
        boolean[] used = new boolean[nums.length];
        return process(nums, used, xor);
    }

    public static boolean process(int[] nums, boolean[] used, int xor){
        if(xor == 0){
            return true;
        }
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                used[i] = true;
                if(!process(nums, used, xor ^ nums[i])){
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        int max = 1 << 16;
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * max);
        }
        return nums;
    }

    public static int getXor(int[] nums){
        int xor = 0;
        for(int i : nums){
            xor ^= i;
        }
        return xor;
    }
}
