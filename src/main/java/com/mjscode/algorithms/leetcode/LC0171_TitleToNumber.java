package com.mjscode.algorithms.leetcode;

/**
 * //给定一个Excel表格中的列名称，返回其相应的列序号。
 * //
 * // 例如，
 * //
 * //     A -> 1
 * //    B -> 2
 * //    C -> 3
 * //    ...
 * //    Z -> 26
 * //    AA -> 27
 * //    AB -> 28
 * //    ...
 * //
 * // 示例 1:
 * //
 * // 输入: "A"
 * //输出: 1
 * //
 * // 示例 2:
 * //
 * // 输入: "AB"
 * //输出: 28
 * //
 * // 示例 3:
 * //
 * // 输入: "ZY"
 * //输出: 701
 * //
 * // 致谢：
 * //特别感谢 @ts 添加此问题并创建所有测试用例。
 * // Related Topics 数学 字符串
 * @author binarySigh
 * @date 2021/7/30 9:06
 */
public class LC0171_TitleToNumber {
    public static void main(String[] args){
        String s = "ZY";
        System.out.println(titleToNumber(s));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:38.5 MB,击败了34.55% 的Java用户
     * @param columnTitle
     * @return
     */
    public static int titleToNumber(String columnTitle) {
        int pow = 1;
        int ans = 0;
        for(int i = columnTitle.length() - 1; i >= 0; i--){
            ans += (columnTitle.charAt(i) - 'A' + 1) * pow;
            pow *= 26;
        }
        return ans;
    }
}
