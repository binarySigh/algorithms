package com.mjscode.algorithms.leetcode;

/**
 * //将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * //
 * // 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * //
 * // P   A   H   N
 * // A P L S I I G
 * // Y   I   R
 * //
 * // 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * //
 * // 请你实现这个将字符串进行指定行数变换的函数：
 * //
 * //string convert(string s, int numRows);
 * //
 * // 示例 1：
 * //
 * //输入：s = "PAYPALISHIRING", numRows = 3
 * //输出："PAHNAPLSIIGYIR"
 * //
 * //示例 2：
 * //
 * //输入：s = "PAYPALISHIRING", numRows = 4
 * //输出："PINALSIGYAHRPI"
 * //解释：
 * // P     I    N
 * // A   L S  I G
 * // Y A   H R
 * // P     I
 * //
 * // 示例 3：
 * //
 * //输入：s = "A", numRows = 1
 * //输出："A"
 * //
 * // 提示：
 * //
 * // 1 <= s.length <= 1000
 * // s 由英文字母（小写和大写）、',' 和 '.' 组成
 * // 1 <= numRows <= 1000
 * //
 * // Related Topics 字符串
 * @author binarySigh
 */
public class LC0006_Convert {

	public static void main(String[] args) {
		/*String s = "PAYPALISHIRING";
		int numRows = 4;*/
		String s = "ABC";
		int numRows = 1;
		System.out.println(convert(s, numRows));

	}
	
	/**
	 * 执行结果：通过
	 *	执行用时：17 ms, 在所有 Java 提交中击败了25.39%的用户
	 *	内存消耗：39.4 MB, 在所有 Java 提交中击败了18.04%的用户
     *这实际上是力扣官方提供的两种思路之一，都是一次遍历得到结果。只是由于代码细节处理不够优雅导致耗时较长
	 * @param s
	 * @param numRows
	 * @return
	 */
	public static String convert(String s, int numRows) {
        if(s == null || s.length() <= 2 || numRows <= 1){
            return s;
        }
        String convert = "";
        int[] path = new int[2];
        int pos = 0;
        for(int i = 0; i < numRows; i++){
            if(i == 0 || i == numRows - 1){
                for(int j = i; j < s.length();){
                    convert += s.charAt(j);
                    j += ((numRows - 1) << 1);
                }
            } else {
                path[0] = (numRows - i - 1) << 1;
                path[1] = ((numRows - 1) << 1) - path[0];
                pos = 0;
                for(int j = i; j < s.length();){
                    convert += s.charAt(j);
                    j += path[pos];
                    pos = Math.abs(1 - pos);
                }
            }
        }
        return convert;
    }

}
