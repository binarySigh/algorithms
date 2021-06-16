package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * //给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
 * //
 * // 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 * // 总成本必须恰好等于 target 。
 * // 添加的数位中没有数字 0 。
 * //
 * // 由于答案可能会很大，请你以字符串形式返回。
 * //
 * // 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 * //
 * // 示例 1：
 * //
 * //输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * //输出："7772"
 * //解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要
 * //求的数字，但 "7772" 是较大的数字。
 * // 数字     成本
 * //  1  ->   4
 * //  2  ->   3
 * //  3  ->   2
 * //  4  ->   5
 * //  5  ->   6
 * //  6  ->   7
 * //  7  ->   2
 * //  8  ->   5
 * //  9  ->   5
 * //
 * // 示例 2：
 * //
 * //输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 * //输出："85"
 * //解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 * //
 * // 示例 3：
 * //
 * //输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 * //输出："0"
 * //解释：总成本是 target 的条件下，无法生成任何整数。
 * //
 * // 示例 4：
 * //
 * //输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 * //输出："32211"
 * //
 * // 提示：
 * //
 * // cost.length == 9
 * // 1 <= cost[i] <= 5000
 * // 1 <= target <= 5000
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/6/12 22:38
 */
public class LC1449_LargestNumber {
    public static void main(String[] args){
        // --> 7772
        /*int[] cost1 = {4,3,2,5,6,7,2,5,5};
        int target1 = 9;*/

        // --> 85
        int[] cost1 = {7,6,5,5,5,6,8,7,8};
        //int[] cost1 = {7,6,5,5,5,8,8,7,6};
        int target1 = 12;

        // --> 0
        /*int[] cost1 = {2,4,6,2,4,6,4,4,4};
        int target1 = 5;*/

        // --> 32211
        /*int[] cost1 = {6,10,15,40,40,40,40,40,40};
        int target1 = 47;*/

        // --> 777777771
        /*int[] cost1 = {4,7,3,3,8,7,3,6,5};
        int target1 = 28;*/
        System.out.println(compare(cost1, target1));
        System.out.println(largestNumber(cost1, target1));

        System.out.println("------test begin---------");
        for(int i = 1; i < 20_0000; i++) {
            int[] cost = getCost();
            int target = (int)(Math.random() * 200) + 1;
            String result = largestNumber(cost, target);
            String compare = compare(cost, target);
            if(!result.equals(compare)){
                System.out.println("--------第[" + i + "]次测试失败！-----");
                ArrayUtils.showArray(cost);
                System.out.println("target = " + target);
                System.out.println("动态规划: " + result);
                System.out.println("暴力方法: " + compare);
                break;
            }
        }
        System.out.println("------test end---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:25 ms,击败了27.10% 的Java用户
     * 		内存消耗:39.1 MB,击败了26.83% 的Java用户
     * @param cost
     * @param target
     * @return
     */
    public static String largestNumber(int[] cost, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        //set去重，同时记录每个数字对应的下标，最后还原时需要用到这个下标
        HashSet<Integer> set = new HashSet<>(9);
        for(int i = 8; i >= 0; i--){
            if(!set.contains(cost[i])){
                list.add(i);
                set.add(cost[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        //过滤数组中只有一种数字的情况
        if(set.size() == 1){
            if(target % cost[0] == 0){
                for(int i = target / cost[0]; i > 0; i--){
                    sb.append(9);
                }
                return sb.toString();
            } else {
                return "0";
            }
        }
        //正常算法dp流程
        //dp[i][j] : 表示list中前J个数去凑成本和i，最多需要几个数
        // 通过前面的list中数字排列顺序，以及后面dp生成流程，我们可以保证dp[i][j] 越大，最终生成的答案数字也越大
        int[][] dp = new int[target + 1][list.size()];
        //第一列
        for(int i = 1; i <= target; i++){
            int curNum = cost[list.get(0)];
            dp[i][0] = i % curNum == 0 ? i / curNum : 0;
        }
        //后序列
        for(int j = 1; j < list.size(); j++){
            int curNum = cost[list.get(j)];
            for(int i = 1; i <= target; i++){
                dp[i][j] = i % curNum == 0 ? Math.max(dp[i][j - 1], i / curNum) : dp[i][j - 1];
                if(i >= curNum && dp[i - curNum][j] > 0){
                    dp[i][j] = Math.max(dp[i - curNum][j] + 1, dp[i][j]);
                }
            }
        }
        //根据dp表还原数字
        String[] nums = {"0"};
        rebuildNumber(dp, cost, list, nums, target, list.size() - 1, "");
        return  nums[0];
    }

    public static void rebuildNumber(int[][] dp, int[] cost, ArrayList<Integer> list, String[] nums, int row, int col, String s){
        if(dp[row][col] == 0){
            nums[0] = getLagerStr(nums[0], s);
            return;
        }
        while(dp[row][col] > 0){
            int curNum = cost[list.get(col)];
            if(col == 0 || dp[row][col] > dp[row][col - 1]) {
                /* sb.insert(0, list.get(col) + 1)*/
                s = (list.get(col) + 1) + s;
                row -= curNum;
            } else if(row - curNum <= 0 || dp[row - curNum][col] + 1 < dp[row][col]){
                col--;
            } else {
                //可能性可能来自左，可能来自上的，就都走一遍
                rebuildNumber(dp, cost, list, nums, row - curNum, col, (list.get(col) + 1) + s);
                rebuildNumber(dp, cost, list, nums, row, col - 1, s);
                break;
            }
        }
        nums[0] = getLagerStr(nums[0], s);
    }

    public static String getLagerStr(String s1, String s2){
        if(s1.length() != s2.length()){
            return s1.length() > s2.length() ? s1 : s2;
        } else {
            for(int i = 0; i < s1.length(); i++){
                if(s1.charAt(i) > s2.charAt(i)){
                    return s1;
                } else if(s1.charAt(i) < s2.charAt(i)){
                    return s2;
                }
            }
        }
        return s1;
    }

    public static String compare(int[] cost, int target){
        int[] copy = new int[9];
        HashSet<Integer> set = new HashSet<>();
        for(int i = 8; i >= 0; i--){
            if(set.contains(cost[i])){
                copy[i] = -cost[i];
            } else {
                copy[i] = cost[i];
                set.add(copy[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] ans = {""};
        process(copy, 8, ans, sb, target);
        return "".equals(ans[0]) ? "0" : ans[0];
    }

    public static void process(int[] copy, int index, String[] ans, StringBuilder sb, int target){
        if(index < 0 || target <= 0){
            if(target == 0 && sb.length() > ans[0].length()){
                ans[0] = sb.toString();
            }
            return;
        }
        if(copy[index] < 0){
            process(copy, index - 1, ans, sb, target);
        } else {
            int left = target;
            int curAdd = 0;
            while(left - copy[index] >= 0){
                sb.append(index + 1);
                left -= copy[index];
                curAdd++;
            }
             while(left <= target) {
                process(copy, index - 1, ans, sb, left);
                left += copy[index];
                if(curAdd > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    curAdd--;
                }
            }
        }
    }

    public static int[] getCost(){
        int[] cost = new int[9];
        for(int i = 0; i < 9; i++){
            cost[i] = (int)(Math.random() * 5000) + 1;
        }
        return cost;
    }
}
