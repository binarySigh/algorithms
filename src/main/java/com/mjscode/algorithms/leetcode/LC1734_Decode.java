package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * //
 * // 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说
 * //，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * //
 * // 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * //
 * // 示例 1：
 * //
 * // 输入：encoded = [3,1]
 * //输出：[1,2,3]
 * //解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * //
 * // 示例 2：
 * //
 * // 输入：encoded = [6,5,4,6]
 * //输出：[2,4,1,5,3]
 * //
 * // 提示：
 * //
 * // 3 <= n < 105
 * // n 是奇数。
 * // encoded.length == n - 1
 * //
 * // Related Topics 位运算
 * @author binarySigh
 * @date 2021/5/11 19:47
 */
public class LC1734_Decode {
    public static void main(String[] args){
        int[] encoded = {6,5,4,6};
        int[] decoded = decode(encoded);
        ArrayUtils.showArray(decoded);
    }

    /**
     * 思路：
     * 1. 已知 decoded 数组是由 1~n 的正整数组成的一个排列。那么先求 1~n 所有数的异或结果，也就是 d0^d1^d2^d3^d4 的值 -> xor
     * 2. encoded 数组是 d数组相邻两位异或的结果，即：
     *        e = {d0^d1, d1^d2, d2^d3, d3^d4}
     *    对 e 求前N项异或和，即可得到辅助异或和数组，即：
     *    assis = {d0^d1, d0^d2, d0^d3, d0^d4}
     * 3. 由于 n 为奇数，因此 assis 和 e的元素个数为偶数，那么将 assis所有元素异或的结果就是 d1^d2^d3^d4 的结果 -> encodeXor
     * 4. d[0] = xor ^ encodeXor
     * 5. 通过 d[0] 和 encoded数组，即可还原出 decoded数组
     *
     * 解答成功:
     * 		执行耗时:4 ms,击败了30.00% 的Java用户
     * 		内存消耗:59.7 MB,击败了84.00% 的Java用户
     * @param encoded
     * @return
     */
    public static int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int xor = 0;
        //先求 1~n 所有数异或结果
        for(int i = 0; i <= n; i++){
            xor ^= i;
        }
        int[] decoded = new int[n];
        int encodeXor = encoded[0];
        decoded[0] = encoded[0];
        for(int i = 1; i < encoded.length; i++){
            decoded[i] = encoded[i] ^ decoded[i - 1];
            encodeXor ^= decoded[i];
        }
        //得到decoded[0]
        decoded[0] = encodeXor ^ xor;
        //还原decoded数组
        for(int i = 1; i < n; i++){
            decoded[i] = encoded[i - 1] ^ decoded[i - 1];
        }
        return decoded;
    }
}
