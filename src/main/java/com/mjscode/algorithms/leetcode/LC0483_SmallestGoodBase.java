package com.mjscode.algorithms.leetcode;

/**
 * //对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
 * //
 * // 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 * //
 * // 示例 1：
 * //
 * //输入："13"
 * //输出："3"
 * //解释：13 的 3 进制是 111。
 * //
 * // 示例 2：
 * //
 * //输入："4681"
 * //输出："8"
 * //解释：4681 的 8 进制是 11111。
 * //
 * // 示例 3：
 * //
 * //输入："1000000000000000000"
 * //输出："999999999999999999"
 * //解释：1000000000000000000 的 999999999999999999 进制是 11。
 * //
 * // 提示：
 * //
 * // n的取值范围是 [3, 10^18]。
 * // 输入总是有效且没有前导 0。
 * //
 * // Related Topics 数学 二分查找
 * @author binarySigh
 * @date 2021/6/19 21:42
 */
public class LC0483_SmallestGoodBase {
    public static void main(String[] args){
        String n1 = "1000000000000000000";
        System.out.println(n1);
        System.out.println(smallestGoodBase(n1));
    }

    /**
     * 解答成功:
     * 		执行耗时:14 ms,击败了5.39% 的Java用户
     * 		内存消耗:36.6 MB,击败了52.45% 的Java用户
     * @param n
     * @return
     */
    public static String smallestGoodBase(String n) {
        long input = Long.parseLong(n);
        // mostLeft 最大是63,因此最外层循环和最内层循环最多都只有63次，故可以认为均是O(1)复杂度
        int mostLeft = getMostLeftOneInBinary(input);
        long ans = input - 1;
        while(mostLeft > 2){
            long L = 2, R = input;
            while(L <= R){
                long mid = L + (R - L) / 2;
                long curNum = input - 1;
                long curPower = 1;
                for(int i = 1; i < mostLeft; i++){
                    // if-else 防止溢出
                    if(input / mid < curPower){
                        curNum = -1;
                        break;
                    } else {
                        curPower *= mid;
                    }
                    curNum -= curPower;
                }
                if(curNum == 0){
                    ans = mid;
                    return String.valueOf(ans);
                } else if(curNum < 0){
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            mostLeft--;
        }
        return String.valueOf(ans);
    }

    /**
     * 找到 long 类型数字二进制位上最左边的1出现在第几位
     */
    public static int getMostLeftOneInBinary(long num){
        int bit = 0;
        while(num > 0){
            num >>= 1;
            bit++;
        }
        return bit;
    }
}
