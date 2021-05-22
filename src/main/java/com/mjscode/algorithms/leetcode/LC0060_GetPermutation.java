package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * //给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * //
 * // 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * //
 * // "123"
 * // "132"
 * // "213"
 * // "231"
 * // "312"
 * // "321"
 * //
 * // 给定 n 和 k，返回第 k 个排列。
 * //
 * // 示例 1：
 * //
 * //输入：n = 3, k = 3
 * //输出："213"
 * //
 * // 示例 2：
 * //
 * //输入：n = 4, k = 9
 * //输出："2314"
 * //
 * // 示例 3：
 * //
 * //输入：n = 3, k = 1
 * //输出："123"
 * //
 * // 提示：
 * //
 * // 1 <= n <= 9
 * // 1 <= k <= n!
 * //
 * // Related Topics 数学 回溯算法
 * @author binarySigh
 * @date 2021/5/22 21:29
 */
public class LC0060_GetPermutation {

    public static void main(String[] args){
        int n = 9;
        int k = 32345;
        String result = getPermutation(n, k);
        String answer = compare(n, k);
        System.out.println("我的答案 : " + result);
        System.out.println("正确答案 : " + answer);
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了99.89% 的Java用户
     * 		内存消耗:35.8 MB,击败了58.24% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        if(n == 1){
            return "1";
        }
        int[] used = new int[10];
        int[] facs = new int[10];
        facs[0] = 1;
        k--;
        for(int i = n; i > 0; i--){
            facs[i] = getFactorial(i);
        }
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            int factorial = facs[n - 1];
            int index = k / factorial;
            for(int i = 1; i <= 9 && index >= 0; i++){
                if(used[i] < 0){
                    continue;
                }
                if(index == 0){
                    sb.append(i);
                    used[i] = -1;
                }
                index--;
            }
            n--;
            k = k % factorial;
        }
        return sb.toString();
    }

    /**
     * 返回 n!
     * @param n
     * @return
     */
    public static int getFactorial(int n){
        int f = 1;
        while(n > 0){
            f *= n--;
        }
        return f;
    }

    /**
     * 对数器解应该也能过，目测两秒内，可达标
     * @param n
     * @param k
     * @return
     */
    public static String compare(int n, int k){
        if(n == 1){
            return "1";
        }
        ArrayList<String> ans = new ArrayList<>();
        process(n, new HashSet<Integer>() , ans, new StringBuilder());
        return ans.get(k - 1);
    }

    public static void process(int n, HashSet<Integer> set, ArrayList<String> ans, StringBuilder sb){
        if(set.size() == n){
            ans.add(sb.toString());
            return;
        }
        for(int i = 1; i <= n; i++){
            if(!set.contains(i)){
                set.add(i);
                sb.append(i);
                process(n, set, ans, sb);
                set.remove(i);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
