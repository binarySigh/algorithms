package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * //一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * //
 * // 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * //
 * // 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * //
 * // 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * //
 * // 示例 1：
 * //
 * //输入：stones = [0,1,3,5,6,8,12,17]
 * //输出：true
 * //解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然
 * //后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * //
 * // 示例 2：
 * //
 * //输入：stones = [0,1,2,3,4,8,9,11]
 * //输出：false
 * //解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 * //
 * // 提示：
 * //
 * // 2 <= stones.length <= 2000
 * // 0 <= stones[i] <= 231 - 1
 * // stones[0] == 0
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/4/29 19:37
 */
public class LC0403_CanCross {
    public static void main(String[] args){
        System.out.println("-------------------测试开始---------------");
        /*for(int i = 0; i < 500_0000; i++){
            int len = (int)(Math.random() * 20) + 2;
            int[] stones = getArray(len);
            boolean result = canCross(stones);
            boolean compare = solution(stones);
            if(result != compare){
                System.out.println("------第[" + i + "]次测试出错---------");
                ArrayUtils.showArray(stones);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + compare);
                break;
            }
        }*/
        System.out.println("-------------------测试开始---------------");

        int[] stones1 = {0,2,4,5,6,8,9,11,14,17,18,19,20,22,23,24,25,27,30};
        System.out.println("我的答案：" + canCross(stones1));
        System.out.println("正确答案：" + solution(stones1));

    }

    /**
     * 解答成功:
     * 		执行耗时:137 ms,击败了11.40% 的Java用户
     * 		内存消耗:46.4 MB,击败了22.70% 的Java用户
     * @param stones
     * @return
     */
    public static boolean canCross(int[] stones) {
        if(stones.length == 2){
            return stones[1] == 1;
        }
        int len = stones.length;
        HashMap<Integer,Integer> map = new HashMap<>(len);
        for(int i = 0; i < len; i++){
            map.put(stones[i], i);
        }
        //dp[i][j] = true : 从j位置之前的某个位置以步长i起跳，可以跳到j
        //dp[i][j] = false : 从j位置之前的任意位置以步长i起跳，都无法跳到j
        // 这里所指的 j 是指stones数组中下标为j 的石头，而并非石头所在的实际单元格位置。
        //          石头在数组中的下标以及石头实际所处单元格位置通过map映射
        boolean[][] dp = new boolean[len][len];
        dp[1][1] = stones[1] == 1;
        for(int j = 1; j < len - 1; j++){
            for(int i = 1; i < len; i++){
                if(dp[i][j]){
                    int curIndex = stones[j];
                    int nextIndex = 0;
                    if(i - 1 > 0 && map.containsKey(curIndex + i - 1)){
                        nextIndex = map.get(curIndex + i - 1);
                        dp[i - 1][nextIndex] = true;
                    }
                    if(map.containsKey(curIndex + i)){
                        nextIndex = map.get(curIndex + i);
                        dp[i][nextIndex] = true;
                    }
                    if(i + 1 < len && map.containsKey(curIndex + i + 1)){
                        nextIndex = map.get(curIndex + i + 1);
                        dp[i + 1][nextIndex] = true;
                    }
                }
            }
        }
        for(int i = 1; i < len; i++){
            if(dp[i][len - 1]){
                return true;
            }
        }
        return false;
    }

    //对数器部分
    public static boolean solution(int[] stones){
        if(stones.length == 2){
            return stones[1] == 1;
        }
        int dest = stones[stones.length - 1];
        HashSet<Integer> set = new HashSet<>(stones.length);
        for(int i = 0; i < stones.length; i++){
            set.add(stones[i]);
        }
        return process(1, 0, dest, set);
    }

    public static boolean process(int preStep, int preIndex, int dest, HashSet<Integer> set){
        if(!set.contains(preStep + preIndex) || preStep < 0){
            return false;
        }
        if(preIndex + preStep == dest){
            return true;
        }
        //当前位置尝试跳跃方案，并收集可能性
        boolean canDo = false;
        for(int i = -1; i < 2; i++){
            int step = preStep + i;
            if(step <= 0){
                continue;
            }
            canDo = process(step, preIndex + preStep, dest, set);
            if(canDo){
                return true;
            }
        }
        return canDo;
    }

    public static int[] getArray(int len){
        int[] arr = new int[len];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i < len; i++){
            arr[i] = arr[i - 1] + (int)(Math.random() * 4) + 1;
        }
        return arr;
    }
}
