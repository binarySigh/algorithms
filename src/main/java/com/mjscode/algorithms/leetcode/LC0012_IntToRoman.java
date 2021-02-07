package com.mjscode.algorithms.leetcode;

/**
 * //罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * //
 * //字符          数值
 * //I             1
 * //V             5
 * //X             10
 * //L             50
 * //C             100
 * //D             500
 * //M             1000
 * //
 * // 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
 * //I 。
 * //
 * // 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * // 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * //
 * // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * //
 * // 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * //
 * // 示例 1:
 * //
 * //输入: 3
 * //输出: "III"
 * //
 * // 示例 2:
 * //
 * //输入: 4
 * //输出: "IV"
 * //
 * // 示例 3:
 * //
 * //输入: 9
 * //输出: "IX"
 * //
 * // 示例 4:
 * //
 * //输入: 58
 * //输出: "LVIII"
 * //解释: L = 50, V = 5, III = 3.
 * //
 * // 示例 5:
 * //
 * //输入: 1994
 * //输出: "MCMXCIV"
 * //解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * //
 * // 提示：
 * //
 * // 1 <= num <= 3999
 * //
 * // Related Topics 数学 字符串
 * @author binarySigh
 */
public class LC0012_IntToRoman {
    public static void main(String[] args){
        int t = 3;
        System.out.println(t);
        System.out.println(intToRoman(t));
        System.out.println(romanToInt(intToRoman(t)));
    }

    /**
     * 作者：LeetCode
     *     链接：https://leetcode-cn.com/problems/integer-to-roman/solution/zheng-shu-zhuan-luo-ma-shu-zi-by-leetcode/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public String intToRomanByLC(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了24.40% 的Java用户
     * 		内存消耗:38.9 MB,击败了13.46% 的Java用户
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        if(num < 0){
            return "";
        }
        int tmp = 0;
        int path = 1000;
        String ret = "";
        String roman = "";
        while(path >= 1){
            tmp = num / path ;
            num %= path;
            //个位数处理
            if(path == 1){
                if(tmp < 4){
                    for(int i = 0; i < tmp; i++){
                        ret += "I";
                    }
                } else if(tmp == 4 || tmp == 9){
                    ret += tmp == 4 ? "IV" : "IX";
                } else if(tmp >= 5){
                    ret += "V";
                    for(int i = 6; i <= tmp; i++){
                        ret += "I";
                    }
                }
            }
            //十位数处理
            else if(path == 10){
                if(tmp < 4){
                    for(int i = 0; i < tmp; i++){
                        ret += "X";
                    }
                } else if(tmp == 4 || tmp == 9){
                    ret += tmp == 4 ? "XL" : "XC";
                } else if(tmp >= 5){
                    ret += "L";
                    for(int i = 6; i <= tmp; i++){
                        ret += "X";
                    }
                }
            }
            //百位数处理
            else if(path == 100){
                if(tmp < 4){
                    for(int i = 0; i < tmp; i++){
                        ret += "C";
                    }
                } else if(tmp == 4 || tmp == 9){
                    ret += tmp == 4 ? "CD" : "CM";
                } else if(tmp >= 5){
                    ret += "D";
                    for(int i = 6; i <= tmp; i++){
                        ret += "C";
                    }
                }
            }
            //千位数处理
            else {
                for(int i = 0; i < tmp; i++){
                    ret += "M";
                }
            }
            //重置 roman 变量，path增加
            path /= 10;
        }
        return ret;
    }

    /**
     * LC0013 罗马数字转为整数
     * 解答成功:
     * 		执行耗时:5 ms,击败了73.31% 的Java用户
     * 		内存消耗:38.6 MB,击败了63.07% 的Java用户
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ret = 0;
        for(int i = 0; i < s.length(); i++){
            //千位
            if(s.charAt(i) == 'M'){
                ret += 1000;
            }
            //百位
            else if(s.charAt(i) == 'C'){
                if(i + 1 < s.length() && s.charAt(i + 1) == 'D'){
                    ret += 400;
                    i++;
                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'M'){
                    ret += 900;
                    i++;
                } else{
                    ret += 100;
                }
            } else if(s.charAt(i) == 'D'){
                ret += 500;
            }
            //十位
            else if(s.charAt(i) == 'X'){
                if(i + 1 < s.length() && s.charAt(i + 1) == 'L'){
                    ret += 40;
                    i++;
                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'C'){
                    ret += 90;
                    i++;
                } else{
                    ret += 10;
                }
            } else if(s.charAt(i) == 'L'){
                ret += 50;
            }
            //个位
            else if(s.charAt(i) == 'I'){
                if(i + 1 < s.length() && s.charAt(i + 1) == 'V'){
                    ret += 4;
                    i++;
                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'X'){
                    ret += 9;
                    i++;
                } else{
                    ret += 1;
                }
            } else if(s.charAt(i) == 'V'){
                ret += 5;
            }
        }
        return ret;
    }
}
