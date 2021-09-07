package com.mjscode.algorithms.leetcode.template;

/**
 * //使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * //
 * // 如果字符串的长度为 1 ，算法停止
 * // 如果字符串的长度 > 1 ，执行下述步骤：
 * //
 * // 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * // 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x
 * // 。
 * // 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * //
 * // 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：s1 = "great", s2 = "rgeat"
 * //输出：true
 * //解释：s1 上可能发生的一种情形是：
 * //"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * //"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * //"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * //"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * //"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * //"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * //算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * //这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 * //
 * // 示例 2：
 * //
 * //输入：s1 = "abcde", s2 = "caebd"
 * //输出：false
 * //
 * // 示例 3：
 * //
 * //输入：s1 = "a", s2 = "a"
 * //输出：true
 * //
 * // 提示：
 * //
 * // s1.length == s2.length
 * // 1 <= s1.length <= 30
 * // s1 和 s2 由小写英文字母组成
 * //
 * // Related Topics 字符串 动态规划
 * @author binarySigh
 * @date 2021/6/2 23:12
 */
public class LC0087_IsScramble {

    public static void main(String[] args) {
        // --> true
        /*String s1 = "great";
        String s2 = "rgeat";*/

        // --> false
        /*String s1 = "abcde";
         String s2 = "caebd";*/

        // --> true
        /*String s1 = "a";
        String s2 = "a";*/

        String s1 = "abcde";
        String s2 = "dabec";
        System.out.println(isScramble(s1, s2));
    }

    /**
     * 解答成功:
     * 		执行耗时:10 ms,击败了36.1% 的Java用户
     * 		内存消耗:38.3 MB,击败了94.7% 的Java用户
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble(String s1, String s2){
        if(!check(s1, s2)){
            return false;
        }
        int n = s1.length();
        // dp[i][j][k] : 以s1 i 位置字符结尾，长度为k的子串 跟 以s2 j 位置字符结尾，长度为k的子串之间，是否互为扰乱字符串
        boolean[][][] dp = new boolean[n][n][n + 1];
        //第一层，也即长度为1的时候。(0层弃而不用)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        //后续层
        for(int k = 2; k <= n; k++){
            for(int i = k - 1; i < n; i++){
                for(int j = k - 1; j < n; j++){
                    int iStart = i - k;
                    int jStart = j - k;
                    for(int t = iStart + 1; t < i; t++){
                        //以s1为基准的 右左两部分长度
                        int rLen = i - t;
                        int lLen = t - iStart;
                        if((dp[iStart + lLen][jStart + lLen][lLen] && dp[i][j][rLen]) ||
                                (dp[iStart + lLen][j][lLen] && dp[i][jStart + rLen][rLen])){
                            dp[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[n - 1][n - 1][n];
    }

    public static boolean check(String s1, String s2){
        if(s1 == null || s2 == null){
            return s1 == null && s2 == null;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int[] cnts = new int[256];
        for(int i = 0; i < s1.length(); i++){
            cnts[s1.charAt(i)]++;
            cnts[s2.charAt(i)]--;
        }
        for(int i : cnts){
            if(i != 0){
                return false;
            }
        }
        return true;
    }

}
