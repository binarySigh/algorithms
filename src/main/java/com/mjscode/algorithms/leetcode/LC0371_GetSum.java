package com.mjscode.algorithms.leetcode;

/**
 * //给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
 * //
 * // 示例 1：
 * //
 * //输入：a = 1, b = 2
 * //输出：3
 * //
 * // 示例 2：
 * //
 * //输入：a = 2, b = 3
 * //输出：5
 * //
 * // 提示：
 * //
 * // -1000 <= a, b <= 1000
 * //
 * // Related Topics 位运算 数学
 * @author binarySigh
 * @date 2021/9/26 22:11
 */
public class LC0371_GetSum {

    public static void main(String[] args) {
        int a1 = -348, b1 = 598;

        int ans = getSum(a1, b1);
        int san = getSumBySanye(a1, b1);

        System.out.println("a1  : " + Integer.toBinaryString(a1));
        System.out.println("b1  : " + Integer.toBinaryString(b1));
        System.out.println("ans : " + Integer.toBinaryString(ans));
        System.out.println("san : " + Integer.toBinaryString(san));
        System.out.println("sum : " + Integer.toBinaryString(a1 + b1));
        System.out.println(ans);
        System.out.println(a1 + b1);

        System.out.println("-----START------");
        for(int i = 0; i < 10_0000; i++) {
            int a = (int)(Math.random() * 2000) - (int)(Math.random() * 2000);
            int b = (int)(Math.random() * 2000) - (int)(Math.random() * 2000);
            if(a + b != getSum(a, b)){
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println("ans : " + getSum(a, b));
                System.out.println("sum: " + (a + b));
                break;
            }
        }
        System.out.println("-----END------");

    }

    public static int getSum(int a, int b) {
        boolean flag = false;
        if(a < 0 && b < 0){
            flag = true;
        } else if(a >= 0 && b < 0 && Math.abs(a) < Math.abs(b)) {
            flag = true;
        } else if(b >= 0 && a < 0 && Math.abs(b) < Math.abs(a)) {
            flag = true;
        }
        int k = 0;
        int bit = 0;
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            bit = 1 << i;
            if(((bit & a) > 0) && ((bit & b) > 0)) {
                if((bit & k) > 0) {
                    ans |= bit;
                }
                k = (bit << 1);
            } else if(((bit & a) > 0) ^ ((bit & b) > 0)) {
                if((bit & k) == 0) {
                    ans |= bit;
                } else {
                    k = (bit << 1);
                }
            } else {
                if((bit & k) > 0 || (i == 31 && flag)) {
                    ans |= bit;
                }
                k = 0;
            }
        }
        return ans;
    }

    public static int getSumBySanye(int a, int b){
        int ans = 0;
        for(int i = 0, t = 0; i < 32; i++){
            int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
            if(u1 == 1 && u2 == 1) {
                ans |= (t << i);
                t = 1;
            } else if(u1 == 1 || u2 == 1) {
                ans |= ((1 ^ t) << i);
            } else {
                ans |= (t << i);
                t = 0;
            }
        }
        return ans;
    }

}
