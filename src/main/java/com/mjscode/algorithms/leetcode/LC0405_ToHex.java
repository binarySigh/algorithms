package com.mjscode.algorithms.leetcode;

/**
 * //给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * //
 * // 注意:
 * //
 * // 十六进制中所有字母(a-f)都必须是小写。
 * // 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
 * // 给定的数确保在32位有符号整数范围内。
 * // 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * //
 * // 示例 1：
 * //
 * //输入:
 * //26
 * //
 * //输出:
 * //"1a"
 * //
 * // 示例 2：
 * //
 * //输入:
 * //-1
 * //
 * //输出:
 * //"ffffffff"
 * //
 * // Related Topics 位运算 数学
 * @author binarySigh
 * @date 2021/10/2 9:13
 */
public class LC0405_ToHex {
    public static void main(String[] args){
        // --> ffffffff
//        int n1 = -1;

        // --> 1a
//        int n1 = 26;

        int n1 = 0;
        System.out.println(toHex(n1));

        System.out.println("------Begin---------");
        for(int i = 0; i < 10_0000; i++) {
            int num = (int)(Math.random() * Integer.MAX_VALUE) - (int)(Math.random() * Integer.MAX_VALUE);
            String ans = toHex(num);
            String com = Integer.toHexString(num);
            if(!ans.equals(com)){
                System.out.println("---Oops!---");
                System.out.println("num = " + num);
                System.out.println("ans : " + ans);
                System.out.println("com : " + com);
                break;
            }
        }
        System.out.println("------End---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.7 MB,击败了40.30% 的Java用户
     * @param num
     * @return
     */
    public static String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        int base = 0xf0000000;
        boolean flag = false;
        int cur = 0;
        char tmp = '0';
        for(int i = 0; i < 8; i++){
            cur = (num & base) >>> (28 - (i << 2));
            tmp = cur <= 9 ? (char)(cur + '0') : (char)((cur - 10) + 'a');
            if(tmp != '0' || flag || i == 7) {
                sb.append(tmp);
                flag = true;
            }
            base >>>= 4;
        }
        return sb.toString();
    }
}
