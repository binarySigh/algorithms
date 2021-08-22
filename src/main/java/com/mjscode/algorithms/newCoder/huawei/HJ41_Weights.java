package com.mjscode.algorithms.newCoder.huawei;

import java.util.HashSet;

/**
 * @author binarySigh
 * @date 2021/8/22 18:18
 */
public class HJ41_Weights {
    public static void main(String[] args){
        /*int[] weights = {1,2};
        int[] cnts = {2,1};
        int max = getMax(weights, cnts);*/

        int[] weights = {5,133,140,81,73,10};
        int[] cnts = {2,4,6,1,2,6};
        int max = getMax(weights, cnts);
        System.out.println(compare(weights, cnts, max));
        System.out.println(solution(weights, cnts, max));

        System.out.println(Math.floor(6.9));
    }

    /**
     * 通过全部用例
     * 运行时间 67ms
     * 占用内存 13312KB
     * @param weights
     * @param cnts
     * @param max
     * @return
     */
    public static int solution(int[] weights, int[] cnts, int max){
        boolean[][] dp = new boolean[max + 1][2];
        int idx = 0;
        int cur = 0;
        for(int i = 0; i <= cnts[0]; i++){
            cur = weights[0] * i;
            dp[cur][0] = true;
        }
        for(int i = 0; i < weights.length - 1; i++){
            for(int j = 0; j <= max; j++){
                for(int c = 0; c <= cnts[i + 1]; c++){
                    cur = c * weights[i + 1] + (dp[j][idx] ? j : 0);
                    dp[cur][idx ^ 1] = true;
                }
            }
            idx ^= 1;
        }
        cur = 0;
        for(int i = 0; i <= max; i++){
            cur += dp[i][idx] ? 1 : 0;
        }
        return cur;
    }

    public static int compare(int[] weights, int[] cnts, int max){
        HashSet<Integer> set = new HashSet<>();
        process(weights, cnts, 0, 0, set);
        return set.size();
    }

    public static void process(int[] weights, int[] cnts, int idx, int pre, HashSet<Integer> set){
        if(idx >= weights.length) {
            set.add(pre);
            return;
        }
        for(int i = 0; i <= cnts[idx]; i++){
            process(weights, cnts, idx + 1, pre + weights[idx] * i, set);
            process(weights, cnts, idx + 1, i * weights[idx], set);
        }
    }

    public static int getMax(int[] weights, int[] cnts){
        int ret = 0;
        for(int i = 0; i < weights.length; i++){
            ret += weights[i] * cnts[i];
        }
        return ret;
    }
}
