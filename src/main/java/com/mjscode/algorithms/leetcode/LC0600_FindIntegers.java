package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 * //
 * // 示例 1:
 * //
 * // 输入: 5
 * //输出: 5
 * //解释:
 * //下面是带有相应二进制表示的非负整数<= 5：
 * //0 : 0
 * //1 : 1
 * //2 : 10
 * //3 : 11
 * //4 : 100
 * //5 : 101
 * //其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
 * //
 * // 说明: 1 <= n <= 109
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/9/11 9:27
 */
public class LC0600_FindIntegers {
    public static void main(String[] args){
        //int n = 5;

        // -- > 987
        int n = 11234;
        System.out.println(findIntegers(n));
        System.out.println(Integer.toBinaryString(n));

        System.out.println("-------------------");
        // --> 2584
        int n1 = 44352;
        System.out.println(findIntegers(n1));
        System.out.println(Integer.toBinaryString(n1));

        System.out.println("-------------------");
        // --> 3
        int n2 = 2;
        System.out.println(findIntegers(n2));
        System.out.println(Integer.toBinaryString(n2));

        System.out.println("-------------------");
        // --> 32838
        int n3 = 2223345;
        System.out.println(findIntegers(n3));
        System.out.println(Integer.toBinaryString(n3));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了98.61% 的Java用户
     * 		内存消耗:35.4 MB,击败了44.18% 的Java用户
     * @param n
     * @return
     */
    public static int findIntegers(int n) {
        //help[i] 表示：如果从第 i 位往下的二进制位上全是1，那么满足要求的排列种数共有多少种
        int[] help = new int[32];
        help[0] = 2;
        help[1] = 3;
        for(int i = 2; i < 32; i++){
            help[i] = help[i - 1] + help[i - 2];
        }
        int k = getLeftIdx(n);
        int[] dp = new int[k + 1];
        for(int i = 0; i <= k; i++){
            //当前位上为0，则直接取当前位的help结果
            if(((1 << i) & n) == 0){
                dp[i] = help[i];
                continue;
            }
            //当前位上为1
            // 1. 选择保留当前位的1
            if(i >= 1){
                if(((1 << (i - 1)) & n) == 0){
                    //i-1位上是0，则只要找到最近的为1的位j即可，i位置保留1的排列种数就是dp[j]的值
                    int j = i - 1;
                    for(; j >= 0; j--){
                        if(((1 << j) & n) > 0){
                            dp[i] = dp[j];
                            break;
                        }
                    }
                    // 如果前面所有的位上都是0，那么保留当前位的1的排列方式就只有1种，即：100000...
                    if(j < 0){
                        dp[i] = 1;
                    }
                } else {
                    // i-1 位上是1，则直接将这个1拆解掉，也即help[i-2]的排列数量就是保留i位置上的1的情况下的所有排列数
                    dp[i] = i >= 2 ? help[i - 2] : 1;
                }
            } else {
                dp[i] = 2;
            }
            // 2. 选择不要当前位上的1
            dp[i] += (i >= 1 ? help[i - 1] : 0);
        }
        return dp[k];
    }

    /**
     * 获取正整数的有效二进制位数，从0开始计数
     * @param n
     * @return
     */
    public static int getLeftIdx(int n){
        int k = 0;
        while((1 << k) <= n){
            k++;
        }
        return k - 1;
    }
}
