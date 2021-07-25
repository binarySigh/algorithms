package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * //
 * // 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui
 * // 和 vi 在 nums 中相邻。
 * //
 * // 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i]
 * //, nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
 * //
 * // 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * //
 * // 示例 1：
 * //
 * //输入：adjacentPairs = [[2,1],[3,4],[3,2]]
 * //输出：[1,2,3,4]
 * //解释：数组的所有相邻元素对都在 adjacentPairs 中。
 * //特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
 * //
 * // 示例 2：
 * //
 * //输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * //输出：[-2,4,1,-3]
 * //解释：数组中可能存在负数。
 * //另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
 * //
 * // 示例 3：
 * //
 * //输入：adjacentPairs = [[100000,-100000]]
 * //输出：[100000,-100000]
 * //
 * // 提示：
 * //
 * // nums.length == n
 * // adjacentPairs.length == n - 1
 * // adjacentPairs[i].length == 2
 * // 2 <= n <= 105
 * // -105 <= nums[i], ui, vi <= 105
 * // 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
 * //
 * // Related Topics 数组 哈希表
 * @author binarySigh
 * @date 2021/7/25 17:08
 */
public class LC1743_RestoreArray {
    public static void main(String[] args){

        // --> [1,2,3,4]
        /*int[][] ad = {
                {2,1},
                {3,4},
                {3,2}
        };*/

        // --> [-2,4,1,-3]
        /*int[][] ad = {
                {4,-2},
                {1,4},
                {-3,1}
        };*/

        // --> [100000,-100000]
        int[][] ad = {
                {100000,-100000}
        };
        int[] nums = restoreArray(ad);
        ArrayUtils.showArray(nums);
    }

    /**
     * 解答成功:
     * 		执行耗时:89 ms,击败了94.40% 的Java用户
     * 		内存消耗:89.3 MB,击败了40.00% 的Java用户
     * @param adjacentPairs
     * @return
     */
    public static int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        int[] nums = new int[n];
        int index = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>(n);
        for(int[] pair : adjacentPairs){
            if(map.containsKey(pair[0])){
                map.get(pair[0]).add(pair[1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pair[1]);
                map.put(pair[0], list);
            }
            if(map.containsKey(pair[1])){
                map.get(pair[1]).add(pair[0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pair[0]);
                map.put(pair[1], list);
            }
        }
        //找到数组第一个数。有两种可能性，但根据题目要求只需要找到其中一种即可
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            if(entry.getValue().size() == 1){
                nums[index++] = entry.getKey();
                break;
            }
        }
        //按照顺序恢复原数组
        for(; index < n; index++){
            List<Integer> cur = map.get(nums[index - 1]);
            if(cur.size() == 1){
                nums[index] = cur.get(0);
            } else {
                nums[index] = cur.get(0) == nums[index - 2] ? cur.get(1) : cur.get(0);
            }
        }
        return nums;
    }
}
