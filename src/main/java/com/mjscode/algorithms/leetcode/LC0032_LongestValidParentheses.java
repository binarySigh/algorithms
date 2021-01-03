package com.mjscode.algorithms.leetcode;

/**
 * //给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * //
 * // 示例 1:
 * //
 * // 输入: "(()"
 * //输出: 2
 * //解释: 最长有效括号子串为 "()"
 * //
 * // 示例 2:
 * //
 * // 输入: ")()())"
 * //输出: 4
 * //解释: 最长有效括号子串为 "()()"
 * //
 * // Related Topics 字符串 动态规划
 *
 * @author binarySigh
 */
public class LC0032_LongestValidParentheses {

    public static void main(String[] args){
        String str = "";
        System.out.println(longestValidParentheses(str));
    }

    /**
     *leetCode Accept
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.5 MB,击败了70.09% 的Java用户
     *
     * ASCII:
     *  ( : 40
     *  ) : 41
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s){
        int result = 0;
        if(s == null || "".equals(s)){
            return 0;
        }
        char[] str = s.toCharArray();
        //辅助数组，fit[i] 表示必须以i位置字符为结尾的最长有效括号字串长度
        int[] fit = new int[str.length];
        fit[0] = 0;
        for(int i = 1; i < str.length; i++){
            if(str[i] - 40 == 0) {
                // 以 （ 结尾的子串不可能是有效括号字串，所以给0
                fit[i] = 0;
            } else if(str[i] - 41 == 0){
                // 以 ） 结尾的子串是有可能产生有效括号字串的，但是情况很多，需要分类讨论
                if(str[i - 1] - 40 == 0){
                    //如果i-1位置是（，那么此时就产生一个长度为2的有效串，但是这个串是有可能比2更大的
                    // 比如(())(),所以还要判断i-2位置，如果i-2也是），那么fit[i] = fit[i-2] + 2;
                    //                              如果i-2是（，那么fit[i] = 2;
                    if(i >= 2 && str[i - 2] - 41 == 0){
                        //i-2 是 ）的情况，那么fit[i] = fit[i-2] + 2;
                        fit[i] = fit[i - 2] + 2;
                    } else {
                        //i-2是（，那么fit[i] = 2;
                        fit[i] = 2;
                    }
                } else {
                    // 如果i位置是)，且i-1位置也是)。可能的情况也有很多
                    //比如 ((())) 和 )(()))
                    //所以要看他i-1位置的最长有效子串长度，
                    // 如果fit[i-1] ＝ 0，那么fit[i]直接就是0，例如：)))))；
                    // 如果fit[i-1] ≠ 0 ，则需要分类讨论：找到i-1位置最长字串左边界的上一个字符位置，
                    //                  1. 如果那个位置是(,那么fit[i] = fit[i-1]+2 -- 对应例1
                    //                  2. 如果那个位置是），那么fit[i] = 0  -- 对应例2

                    //i-1 - fit[i-1]:表示  i-1位置最长字串左边界的上一个字符位置
                    if(fit[i-1] > 0 && (i-1 - fit[i-1] >= 0) && (str[i-1 - fit[i-1]] - 40 == 0)){
                        //if分支代表情况2
                        //但其实这里也要分情况，比如这个例子：()((())),这个例子找到第三个元素之后其实还不是最大的，最大的是整个字串长度
                        //    所以这里还要看 i-1 - fit[i-1] - 1位置，如果这个位置不越界，那就直接给值fit[i] = fit[i-1] + 2 + fit[i-1-fit[i-1]-1]
                        fit[i] = i-1-fit[i-1]-1 >= 0 ? fit[i-1] + 2 + fit[i-1-fit[i-1]-1] : fit[i-1]+2;
                    } else {
                        //对应情况1，3
                        fit[i] = 0;
                    }
                }
            }
            result = Math.max(result,fit[i]);
        }
        return result;
    }
}
