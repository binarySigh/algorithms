package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Stack;

/**
 * //请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * //
 * // 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * //你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * //
 * // 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * // Related Topics 栈 哈希表
 * @author binarySigh
 * @date 2021/4/27 23:42
 */
public class LC0739_DailyTemperatures {
    public static void main(String[] args){
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = dailyTemperatures(T);
        ArrayUtils.showArray(res);
    }

    /**
     * 解答成功:
     * 		执行耗时:21 ms,击败了50.81% 的Java用户
     * 		内存消耗:46.7 MB,击败了42.91% 的Java用户
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {
        if(T.length == 1){
            return new int[]{0};
        }
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < T.length; i++){
            while(!stack.isEmpty() && T[i] > T[stack.peek()]){
                int cur = stack.pop();
                res[cur] = i - cur;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            res[stack.pop()] = 0;
        }
        return res;
    }
}
