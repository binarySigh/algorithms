package com.mjscode.algorithms.leetcode;

/**
 * //给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * //
 * // 示例 1:
 * //
 * // 输入: [5,7]
 * //输出: 4
 * //
 * // 示例 2:
 * //
 * // 输入: [0,1]
 * //输出: 0
 * // Related Topics 位运算
 * @author binarySigh
 */
public class LC0201_RangeBitwiseAnd {

    public static void main(String[] args){
        System.out.println("-------------测试开始------------");
        for(int i = 0; i < 50_0000; i++){
            int m = (int)(Math.random() * 501);
            int n = m + (int)(Math.random() * 1001);
            int result = rangeBitwiseAnd(m, n);
            int compare = compare(m, n);
            if(result != compare){
                System.out.println("------第[" + i + "]次结果出错---------");
                System.out.println("m = " + m + "; n = " + n);
                System.out.println("测试结果：" + result);
                System.out.println("期望结果：" + compare);
                break;
            }
        }
        System.out.println("-------------测试结束------------");
        /*System.out.println(rangeBitwiseAnd(3, 7));*/
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了24.20% 的Java用户
     * 		内存消耗:37.8 MB,击败了57.23% 的Java用户
     * @param m
     * @param n
     * @return
     */
    public static int rangeBitwiseAnd(int m, int n){
        if(m < 0 || n < 0 || m >= n){
            return m;
        }
        int result = 0;
        //不管m,n中间有多少数相与，能保留下来的只有m,n高位相同的部分
        for(int i = 31; i >= 0; i--){
            if((m & (1 << i)) == (n & (1 << i))){
                //相同的时候判断该位是否为1，为0跳过，为1再设值
                if((m & (1 << i)) != 0){
                    result |= (1 << i);
                }
            } else {
                break;
            }
        }
        return result;
    }

    public static int compare(int m, int n){
        if(m < 0 || n < 0 || m >= n){
            return m;
        }
        int result = m;
        for(int i = m; i <= n; i++){
            result &= i;
        }
        return result;
    }
}
