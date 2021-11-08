package com.mjscode.algorithms.leetcode.medium;

/**
 * @author binarySigh
 * @date 2021/11/8 22:30
 */
public class LC0299_GetHint {

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:36.7 MB,击败了99.60% 的Java用户
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint(String secret, String guess) {
        int[] scnts = new int[10];
        int[] gcnts = new int[10];
        int hints = 0;
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                hints++;
            } else {
                scnts[secret.charAt(i) - '0']++;
                gcnts[guess.charAt(i) - '0']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hints).append('A');
        hints = 0;
        for(int i = 0; i < 10; i++) {
            hints += Math.min(scnts[i], gcnts[i]);
        }
        sb.append(hints).append('B');
        return sb.toString();
    }
}
