package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;

/**
 * //给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * //
 * // 示例 1：
 * //
 * //输入：n = 13
 * //输出：6
 * //
 * // 示例 2：
 * //
 * //输入：n = 0
 * //输出：0
 * //
 * // 提示：
 * //
 * // 0 <= n <= 2 * 109
 * //
 * // Related Topics 递归 数学 动态规划
 * @author binarySigh
 * @date 2021/8/13 9:39
 */
public class LC0233_CountDigitOne {
    public static int[] digitCount = {0,1,20,300,4000,50000,600000,7000000,80000000,900000000};
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Compare : " + compare(n));
        System.out.println("Answer  : " + countDigitOne(n));

        /*int[] ans = new int[10];
        ans[1] = 1;
        int[] sum = new int[10];
        sum[1] = ans[1];
        for(int i = 2; i < 10; i++){
            ans[i] = sum[i - 1] * 9 + (int)Math.pow(10, i - 1);
            sum[i] = sum[i - 1] + ans[i];
        }
        ArrayUtils.showArray(ans);
        ArrayUtils.showArray(sum);

        int[] info = getInfo(23456);
        ArrayUtils.showArray(info);*/
        /*int[] info = getInfo(99);
        ArrayUtils.showArray(info);*/

        System.out.println("----------START!-------");
        for(int i = 0; i < 10_0000; i++){
            int com = compare(i);
            int ans = countDigitOne(i);
            if(ans != com){
                System.out.println("n = " + i);
                System.out.println("Compare : " + com);
                System.out.println("Answer  : " + ans);
                break;
            }
        }
        System.out.println("----------END!-------");
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.1 MB,击败了69.86% 的Java用户
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        if(n < 0){
            return 0;
        }
        int ans = 0;
        while(n > 0) {
            int[] info = getInfo(n);
            if(info[2] > 1){
                ans += digitCount[info[1]] * info[2] + info[0];
            } else {
                ans += digitCount[info[1]] + (n % info[0]) + 1;
            }
            n = n % info[0];
        }
        return ans;
    }

    /**
     * 获取十进制整数 i 的相关信息。
     *   info[0]：i的数量级。如：1212 -> 1000; 32324 -> 10000
     *   info[1]: i处在十的几次方上。如：1212 -> 3; 32324 -> 4
     *   info[1]: i的最高位是几。如：1212 -> 1; 32324 -> 3
     * @param i
     * @return
     */
    public static int[] getInfo(int i){
        int[] info = new int[3];
        info[0] = 1;
        while(i / 10 >= info[0]){
            info[0] *= 10;
            info[1]++;
        }
        info[2] = i / info[0];
        return info;
    }

    public static int compare(int n){
        int ans = 0;
        for(int i = 0; i <= n; i++){
            ans += check(i);
        }
        return ans;
    }

    public static int check(int i){
        if(i < 1){
            return 0;
        }
        int ans = 0;
        while(i > 0){
            if((i - 1) % 10 == 0){
                ans++;
            }
            i /= 10;
        }
        return ans;
    }
}
