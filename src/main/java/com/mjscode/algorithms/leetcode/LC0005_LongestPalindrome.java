package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.StringUtils;

/**
 * //给你一个字符串 s，找到 s 中最长的回文子串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "babad"
 * //输出："bab"
 * //解释："aba" 同样是符合题意的答案。
 * //
 * // 示例 2：
 * //
 * //输入：s = "cbbd"
 * //输出："bb"
 * //
 * // 示例 3：
 * //
 * //输入：s = "a"
 * //输出："a"
 * //
 * // 示例 4：
 * //
 * //输入：s = "ac"
 * //输出："a"
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 1000
 * // s 仅由数字和英文字母（大写和/或小写）组成
 * //
 * // Related Topics 字符串 动态规划
 *
 * @author binarySigh
 */
public class LC0005_LongestPalindrome {

    public static void main(String[] args){
        /*String s = "abs";
        System.out.println(s.charAt(0));
        System.out.println(2 % 2);
        System.out.println(2 >> 1);
        System.out.println(s.charAt(3 >> 1));*/

        //String s = StringUtils.generateString(10, 0);
        /*String s = "babadada";
        System.out.println("String is : " + s);
        System.out.println("测试结果：" + longestPalindrome(s));
        System.out.println("期望结果：" + compare(s));*/
        System.out.println("-----------测试开始------------");
        for(int i = 0; i < 50_0000; i++){
            String s = StringUtils.generateString(20, -1);
            String test = longestPalindrome(s);
            String expected = compare(s);
            if(!test.equals(expected)){
                System.out.println("---第[" + i + "]次结果出错---");
                System.out.println("String is : " + s);
                System.out.println("测试结果：" + test);
                System.out.println("期望结果：" + expected);
                break;
            }
        }
        System.out.println("-----------测试结束------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:18 ms,击败了89.75% 的Java用户
     * 		内存消耗:39 MB,击败了55.52% 的Java用户
     * Manacher算法
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        //将输入字符串填充进字符数组，特殊处理：每两个有效字符之间插入一个间隔字符‘#’，首尾也都填充‘#’
        char[] chars = new char[(s.length() << 1) + 1];
        for(int i = 0; i < chars.length; i++){
            chars[i] = i % 2 == 1 ? s.charAt(i >> 1) : '#';
        }
        //辅助数组len[],len[i]含义：以chars[i]位置字符为回文轴，产生的回文子串最大是多长,即最长回文直径
        int[] len = new int[chars.length];
        len[0] = 1;
        len[1] = 3;
        //maxLen 回文最右边界 maxRight 对应的回文直径
        int maxLen = 3;
        //maxRight 当前已遍历过的回文串中的回文最右边界
        int maxRight = 2;
        //maxCentral 回文最右边界maxRight对应的回文中点
        int maxCentral = 1;
        //全局最大回文子串的中心点位置
        int maxPosition = 1;
        for(int i = 2; i < chars.length; i++){
            //如果当前遍历指针在之前产生的回文右边界右边，那么以当前i为中心尝试往左右两边扩，然后更新maxRight，maxCentral
            if(i > maxRight){
                for(int j = 1; j < chars.length; j++){
                    //扩到左右边越界，或者左右两边不相同，说明当前 i + j - 1 位置是以i为中心的回文串的最右边界
                    if(i - j < 0 || i + j >= chars.length || chars[i - j] != chars[i + j]){
                        //同时记录 最右回文边界对应的回文直径
                        maxLen = i + j - 1 > maxRight ? ((j - 1) << 1) + 1 : maxLen;
                        //回文中点也是相同的更新逻辑
                        maxCentral = i + j - 1 > maxRight ? i : maxCentral;
                        //更新最右边界，真的比当前的maxRight大了，再更新，否则不更新
                        maxRight = i + j - 1 > maxRight ? i + j - 1 : maxRight;
                        //记录此时 i 位置为中心的最大回文直径 len[i]
                        len[i] = (i + j - 1) - (i - j + 1) + 1;
                        //更新maxPosition
                        maxPosition = len[i] > len[maxPosition] ? i : maxPosition;
                        break;
                    }
                }
            }
            //当前遍历到的位置依然在 回文最右边界左边（或刚好是最右边界）
            else {
                //找到回文最右边界关于它的回文轴的对称点 maxLeft
                int maxLeft = maxRight - maxLen + 1;
                //找到 当前位置 i 关于 maxCentral 的对称点 iMirror
                int iMirror = maxCentral - (i - maxCentral);
                //找出以 iMirror 位置字符为回文中心的最大回文串的左边界
                int iMirrorLeft = iMirror - (len[iMirror] >> 1);
                //如果 iMirror 位置的回文区在 maxLeft-maxRight 之间，也就是 iMirrorLeft > maxLeft
                if(iMirrorLeft > maxLeft){
                    //该情况下，i位置为中心的回文串长度应与它对称点的回文串长度相等
                    // 显然该情况不会更新全局最大值
                    len[i] = len[iMirror];
                }
                //如果 iMirror 位置的回文区左边有一部分在 maxLeft-maxRight 外面，也就是 iMirrorLeft < maxLeft
                else if(iMirrorLeft < maxLeft){
                    //该情况下，i位置为中心的最大回文右边界一定同 maxRight
                    // 显然该情况不会更新全局最大值
                    len[i] = ((maxRight - i) << 1) + 1;
                }
                //iMirror 位置的回文区左边界刚好是 maxLeft
                else {
                    // 检查以i位置为回文中点的回文串，最远能扩到哪儿
                    // iLeft -> maxRight 关于 i的对称点位置
                    int iLeft = i - (maxRight - i);
                    //这一步的扩展并不是从i往两边开始，而是分别从 maxRight + 1 和 iLeft - 1位置开始扩
                    for(int j = 1; j < chars.length; j++){
                        if(iLeft - j < 0 || maxRight + j >= chars.length || chars[iLeft - j] != chars[maxRight + j]){
                            //此时是不能再扩的位置，检查j是否 > 1，如果是则证明确实有过扩展，那么应该更新记录
                            if(j > 1){
                                // 当前i为中心的回文直径：(maxRight + j - 1) - (iLeft - j + 1) + 1
                                len[i] = (maxRight + j - 1) - (iLeft - j + 1) + 1;
                                maxLen = len[i];
                                //同时记录全局最大回文串的回文中点位置
                                maxPosition = len[i] > len[maxPosition] ? i : maxPosition;
                                //更新最右回文右边界，以及它对应的回文中心轴
                                maxRight = maxRight + j - 1;
                                maxCentral = i;
                            }
                            //实际上并没有 扩出去，也就是说此时 i 为中心的最长回文串的右边界还是 maxRight
                            else {
                                //这种情况下只记录len[i]，不更新最右边界的记录
                                len[i] = maxRight - iLeft + 1;
                            }
                            break;
                        }
                    }
                }
            }
        }
        //根据全局最大回文串的中点位置及其回文直径，还原出最大回文子串
        String result = "";
        for(int i = maxPosition - (len[maxPosition] >> 1); i <= maxPosition + (len[maxPosition] >> 1); i++){
            result += chars[i] == '#' ? "" : chars[i];
        }
        return result;
    }

    /**
     * 暴力解法，对照
     * @param s
     * @return
     */
    public static String compare(String s){
        if(s == null || s.length() <= 1){
            return s;
        }
        char[] chars = new char[(s.length() << 1) + 1];
        for(int i = 0; i < chars.length; i++){
            chars[i] = i % 2 == 1 ? s.charAt(i >> 1) : '#';
        }
        int max = 0;
        int maxCentral = 0;
        for(int i = 0; i < chars.length; i++){
            //对每个位置暴力解求最大回文子串长度
            for(int j = 1; j < chars.length; j++){
                if(i - j < 0 || i + j >= chars.length || chars[i - j] != chars[i + j]){
                    maxCentral = (i + j - 1) - (i - j + 1) - 1 > max ? i : maxCentral;
                    max = Math.max(max, (i + j - 1) - (i - j + 1) - 1);
                    break;
                }
            }
        }
        //根据求出来的最大值及对应的中点，组装出回文子串
        String result = "";
        for(int i = maxCentral - (max >> 1); i <= maxCentral + (max >> 1); i++){
            result += chars[i] == '#' ? "" : chars[i];
        }
        return result;
    }
}
