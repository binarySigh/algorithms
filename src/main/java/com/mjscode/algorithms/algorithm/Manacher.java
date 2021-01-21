package com.mjscode.algorithms.algorithm;

/**
 * Manacher 算法实现。
 *      查找一个字符串中的最大回文子串
 *  应用见 leetcode包下 LC0005
 * @author binarySigh
 */
public class Manacher {

    /**
     * 如果有多个最大，就返回位置靠前的那个
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
}
