package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * //给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * //
 * // 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [100,4,200,1,3,2]
 * //输出：4
 * //解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * //输出：9
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 105
 * // -109 <= nums[i] <= 109
 * //
 * // Related Topics 并查集 数组 哈希表
 * @author binarySigh
 * @date 2021/7/20 22:34
 */
public class LC0128_LongestConsecutive {
    public static void main(String[] args){
        int[] nums1 = {-52,83,146,147,235,246};
        System.out.println(longestConsecutive(nums1));
        System.out.println("---------------Test BEGIN--------------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 1000);
            int[] nums = getNums(len);
            int ans = longestConsecutive(nums);
            int com = compare(nums);
            if(ans != com){
                System.out.println("---------Oops!---------");
                ArrayUtils.showArray(nums);
                System.out.println("并查集：" + ans);
                System.out.println("排序法：" + com);
                break;
            }
        }
        System.out.println("---------------Test END--------------");
    }

    /**
     * 并查集方法
     * 解答成功:
     * 		执行耗时:46 ms,击败了35.17% 的Java用户
     * 		内存消耗:51.6 MB,击败了48.20% 的Java用户
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        UnionMap map = new UnionMap();
        for(int i : nums){
            map.add(i);
        }
        return map.getMaxSize();
    }

    public static class UnionMap {
        private HashMap<Integer, Integer> fatherMap;
        private HashMap<Integer, Integer> sizeMap;
        private int maxSize;
        public UnionMap(){
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
            this.maxSize = 0;
        }

        public void add(int num){
            if(fatherMap.containsKey(num)){
                return;
            }
            fatherMap.put(num, num);
            sizeMap.put(num, 1);
            int next = num - 1;
            if(fatherMap.containsKey(next)){
                fatherMap.put(next, num);
                sizeMap.put(num, sizeMap.get(next) + 1);
                sizeMap.remove(next);
            }
            int pre = num + 1;
            if(fatherMap.containsKey(pre)){
                int realFather = findFather(pre);
                sizeMap.put(realFather, sizeMap.get(realFather) + sizeMap.get(num));
                sizeMap.remove(num);
                fatherMap.put(num, realFather);
            }
        }

        public int getMaxSize(){
            for(Map.Entry<Integer, Integer> entry : sizeMap.entrySet()){
                this.maxSize = Math.max(this.maxSize, entry.getValue());
            }
            return this.maxSize;
        }

        private int findFather(int son){
            ArrayList<Integer> list = new ArrayList<>();
            int father = fatherMap.get(son);
            while(father != son){
                list.add(son);
                son = father;
                father = fatherMap.get(son);
            }
            for(Integer i : list){
                fatherMap.put(i, father);
            }
            return father;
        }
    }

    /**
     * 对照方法 ： 排序 + 遍历
     * 解答成功:
     * 		执行耗时:10 ms,击败了82.51% 的Java用户
     * 		内存消耗:44.3 MB,击败了63.51% 的Java用户
     * @param nums
     * @return
     */
    public static int compare(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        int curAns = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i - 1] == 1){
                curAns++;
            } else if(nums[i] == nums[i - 1]){
                continue;
            } else {
                ans = Math.max(ans, curAns);
                curAns = 1;
            }
        }
        return Math.max(ans, curAns);
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 500) - (int)(Math.random() * 500);
        }
        return nums;
    }
}
