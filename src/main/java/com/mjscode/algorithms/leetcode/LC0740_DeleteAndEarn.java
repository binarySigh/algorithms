package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * //给你一个整数数组 nums ，你可以对它进行一些操作。
 * //
 * // 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] +
 * // 1 的元素。
 * //
 * // 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [3,4,2]
 * //输出：6
 * //解释：
 * //删除 4 获得 4 个点数，因此 3 也被删除。
 * //之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [2,2,3,3,3,4]
 * //输出：9
 * //解释：
 * //删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * //之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * //总共获得 9 个点数。
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 2 * 104
 * // 1 <= nums[i] <= 104
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/5 21:52
 */
public class LC0740_DeleteAndEarn {

    public static void main(String[] args){
        /*int[] nums = {2,2,3,3,3,4};
        System.out.println(solution(nums));
        System.out.println(deleteAndEarn(nums));*/

        System.out.println("--------------测试开始-----------------");
        for(int i = 0; i < 50_0000; i++){
            int len = 10;
            int[] nums = getNums(len);
            int result = deleteAndEarn(nums);
            int answer = solution(nums);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错！--------");
                ArrayUtils.showArray(nums);
                System.out.println("我的答案：" + result);
                System.out.println("参照答案：" + answer);
                break;
            }
        }
        System.out.println("--------------测试结束-----------------");
    }

    /**
     * 本体最佳模型就是个 考虑条件的背包模型
     * 解答成功:
     * 		执行耗时:3 ms,击败了41.26% 的Java用户
     * 		内存消耗:38.1 MB,击败了54.98% 的Java用户
     * @param nums
     * @return
     */
    public static int deleteAndEarn(int[] nums) {
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        Arrays.sort(nums);
        int[] dp = new int[len];
        int pre = -1, preOfPre = -1;
        for(int i = 0; i < len; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                dp[i] = dp[i - 1];
                continue;
            }
            int t = i;
            while(t < len && nums[t] == nums[i]){
                t++;
            }
            int curEarn = nums[i] * (t - i);
            if(pre >= 0 && nums[pre] == nums[i] - 1){
                dp[i] = curEarn + (preOfPre >= 0 ? dp[preOfPre] : 0);
                dp[i] = Math.max(dp[i], dp[pre]);
            } else {
                dp[i] = curEarn + (pre >= 0 ? dp[pre] : 0);
            }
            preOfPre = pre;
            pre = i;
        }
        return dp[len - 1];
    }

    /**
     * 解法对的，只是模型选错了，提交超时。
     * @param nums
     * @return
     */
    public static int deleteAndEarn1(int[] nums) {
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        Arrays.sort(nums);
        int[][] dp = new int[len][len];
        for(int j = 0; j < len; j++){
            for(int i = j; i >= 0; i--){
                if(i == j){
                    int lower = i, upper = i;
                    while(lower < len && nums[lower] == nums[j]){
                        lower++;
                    }
                    while(upper >= 0 && nums[upper] == nums[j]){
                        upper--;
                    }
                    dp[i][j] = nums[j] * (lower - upper - 1);
                    continue;
                }
                if(nums[i] == nums[i + 1]){
                    dp[i][j] = dp[i + 1][j];
                    continue;
                }
                int max = Integer.MIN_VALUE;
                for(int t = i; t <= j;){
                    int cur = dp[t][t];
                    int preEnd = t, nextBegin = t;
                    while(nextBegin < len && nums[nextBegin] <= nums[t] + 1){
                        nextBegin++;
                    }
                    while(preEnd >= 0 && nums[preEnd] >= nums[t] - 1){
                        preEnd--;
                    }
                    cur += ((preEnd >= i ? dp[i][preEnd] : 0) +
                            (nextBegin <= j ? dp[nextBegin][j] : 0));
                    max = Math.max(max, cur);
                    int nextPos = t;
                    while(nextPos < len && nums[nextPos] == nums[t]){
                        nextPos++;
                    }
                    t = nextPos;
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 对照方法1
     * @param nums
     * @return
     */
    public static int solution(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        Arrays.sort(nums);
        int gains = process(nums, 0, nums.length - 1);
        return gains;
    }

    public static int process(int[] nums, int L, int R){
        if(L > R){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for(int i = L; i <= R;){
            int selected = i;
            while(selected < nums.length  && nums[selected] == nums[i]){
                selected++;
            }
            int cur = nums[i] * (selected - i);
            int nextBegin = selected;
            while(nextBegin < nums.length && nums[nextBegin] == nums[i] + 1){
                nextBegin++;
            }
            int preEnd = i - 1;
            while(preEnd >= 0 && nums[preEnd] == nums[i] - 1){
                preEnd--;
            }
            cur += (process(nums, L, preEnd) + process(nums, nextBegin, R));
            max = Math.max(max, cur);
            i = selected;
        }
        return max;
    }

    /**
     * 对照方法2
     * @param nums
     * @return
     */
    public static int solution1(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        Arrays.sort(nums);
        HashMap<Integer,Integer> map = new HashMap<>();
        int point = process1(nums, 0, nums.length - 1, map);
        return point;
    }

    public static int process1(int[] nums, int L, int R, HashMap<Integer,Integer> map){
        if(L > R){
           return 0;
        }
        int maxPoint = 0;
        for(int i = L; i <= R; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            map.put(nums[i] + 1, map.getOrDefault(nums[i] + 1, 0) + 1);
            map.put(nums[i] -1, map.getOrDefault(nums[i] - 1, 0) + 1);
            maxPoint = Math.max(maxPoint, nums[i] + process1(nums, i + 1, R, map) + process1(nums, L, i - 1, map));
            //恢复
            if(map.get(nums[i] + 1) == 1){
                map.remove(nums[i] + 1);
            } else {
                map.put(nums[i] + 1, map.get(nums[i] + 1) - 1);
            }
            if(map.get(nums[i] - 1) == 1){
                map.remove(nums[i] - 1);
            } else {
                map.put(nums[i] - 1, map.get(nums[i] - 1) - 1);
            }
        }
        return maxPoint;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 15);
        }
        return nums;
    }
}
