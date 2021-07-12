package com.mjscode.algorithms.leetcode;

/**
 * @author binarySigh
 * @date 2021/7/12 21:08
 */
public class LC0029_Divide {

    public static void main(String[] args){
        System.out.println(divide(14,2));
        System.out.println("-------------测试开始------------");
        for (int i = 0; i < 20_0000; i++) {
            int x = (int)(Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
            int y = 0;
            do {
               y = (int)(Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
            } while(y == 0);
            int res = divide(x, y);
            int com = x / y;
            if(res != com){
                System.out.println("-----测试出错！------");
                System.out.println("x = " + x + "; y = " + y);
                System.out.println("测试结果：" + res);
                System.out.println("对照结果：" + com);
            }
        }
        System.out.println("-------------测试结束------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了26.20% 的Java用户
     * 		内存消耗:35.5 MB,击败了61.00% 的Java用户
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        long x = dividend, y = divisor;
        boolean isNeg = (x ^ y) < 0;
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;
        long L = 0, R = x;
        long ans = 0;
        while(L <= R){
            long mid = L + ((R - L) >> 1);
            if(mul(mid, y) <= x){
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        ans = isNeg ? -ans : ans;
        if(ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int)ans;
    }

    /**
     * 倍速相乘
     * @param a
     * @param b
     * @return
     */
    public static long mul(long a, long b){
        long ans = 0;
        while(b > 0){
            if((b & 1) == 1){
                ans += a;
            }
            b >>= 1;
            a += a;
        }
        return ans;
    }
}
