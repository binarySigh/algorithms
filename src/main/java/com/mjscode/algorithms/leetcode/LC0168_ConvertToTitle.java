package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * //
 * // 例如：
 * //
 * //A -> 1
 * //B -> 2
 * //C -> 3
 * //...
 * //Z -> 26
 * //AA -> 27
 * //AB -> 28
 * //...
 * //
 * // 示例 1：
 * //
 * //输入：columnNumber = 1
 * //输出："A"
 * //
 * // 示例 2：
 * //
 * //输入：columnNumber = 28
 * //输出："AB"
 * //
 * // 示例 3：
 * //
 * //输入：columnNumber = 701
 * //输出："ZY"
 * //
 * // 示例 4：
 * //
 * //输入：columnNumber = 2147483647
 * //输出："FXSHRXW"
 * //
 * // 提示：
 * //
 * // 1 <= columnNumber <= 231 - 1
 * //
 * // Related Topics 数学 字符串
 * @author binarySigh
 * @date 2021/6/29 19:54
 */
public class LC0168_ConvertToTitle {
    public static void main(String[] args){
        // --> AZ
        //int n = 26 * 3;

        int n = 1;
        System.out.println(convertToTitle(n));
    }

    /**
     * 解答成功:
     * 		执行耗时:9 ms,击败了9.51% 的Java用户
     * 		内存消耗:36.5 MB,击败了5.02% 的Java用户
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        if(columnNumber < 1){
            return "";
        }
        String ans = "";
        while(columnNumber > 0){
            int left = columnNumber % 26;
            if(left == 0){
                ans = 'Z' + ans;
                columnNumber /= 26;
                columnNumber--;
            } else {
                ans = (char)(left - 1 + 'A') + ans;
                columnNumber /= 26;
            }
        }
        return ans;
    }
}
