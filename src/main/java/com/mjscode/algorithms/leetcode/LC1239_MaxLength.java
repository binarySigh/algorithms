package com.mjscode.algorithms.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * //给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * //
 * // 请返回所有可行解 s 中最长长度。
 * //
 * // 示例 1：
 * //
 * // 输入：arr = ["un","iq","ue"]
 * //输出：4
 * //解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * //
 * // 示例 2：
 * //
 * // 输入：arr = ["cha","r","act","ers"]
 * //输出：6
 * //解释：可能的解答有 "chaers" 和 "acters"。
 * //
 * // 示例 3：
 * //
 * // 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * //输出：26
 * //
 * // 提示：
 * //
 * // 1 <= arr.length <= 16
 * // 1 <= arr[i].length <= 26
 * // arr[i] 中只含有小写英文字母
 * //
 * // Related Topics 位运算 回溯算法
 * @author binarySigh
 * @date 2021/6/19 21:15
 */
public class LC1239_MaxLength {

    public static void main(String[] args){
        // --> 4
        /*String[] strs1 = {"un","iq","ue"};
        List<String> arr1 = Arrays.asList(strs1);*/

        // --> 6
        /*String[] strs1 = {"cha","r","act","ers"};
        List<String> arr1 = Arrays.asList(strs1);*/

        // --> 26
        String[] strs1 = {"abcdefghijklmnopqrstuvwxyz"};
        List<String> arr1 = Arrays.asList(strs1);


        System.out.println(maxLength(arr1));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.4 MB,击败了99.63% 的Java用户
     * @param arr
     * @return
     */
    public static int maxLength(List<String> arr) {
        int[] nums = new int[arr.size()];
        // 将输入字符串数组 转化成整型数组，方便统计
        for(int i = 0; i < arr.size(); i++){
            String s = arr.get(i);
            for(int j = 0; j < s.length(); j++){
                int curBit = 1 << (s.charAt(j) - 'a');
                if((nums[i] & curBit) == 0){
                    nums[i] |= curBit;
                } else {
                    //当前字符串就已经有重复字符了，该位置置为-1，在回溯主流程里直接跳过
                    nums[i] = -1;
                    break;
                }
            }
            if(Integer.bitCount(nums[i]) == 26){
                return 26;
            }
        }
        //回溯主流程
        return process(nums, 0, 0);
    }

    public static int process(int[] nums, int index, int res){
        if(index == nums.length){
            return Integer.bitCount(res);
        }
        int ans = process(nums, index + 1, res);
        if(nums[index] > 0 && (res & nums[index]) == 0){
            ans = Math.max(ans, process(nums, index + 1, res | nums[index]));
        }
        return ans;
    }
}
