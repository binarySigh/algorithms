package com.mjscode.algorithms.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。</BR>
 * 示例 1:
 *  输入: 123
 *  输出: 321
 *
 * 示例 2:
 *  输入: -123
 *  输出: -321
 *
 * 示例 3:
 *  输入: 120
 *  输出: 21
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。</BR>
 * @author binarySigh
 */
public class LC0007_ReverseInteger {
    public static void main(String[] args){
        /*int num = -123;
        String str = String.valueOf(num);
        System.out.println(str);*/
        /*System.out.println("max: " + Integer.MAX_VALUE);
        System.out.println(reverse(214748364));*/
        System.out.println("min: " + Integer.MIN_VALUE);
        System.out.println(reverse(-1230));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.6 MB,击败了43.34% 的Java用户
     * @param num
     * @return
     */
    public static int reverse(int num){
        if(num == 0){
            return 0;
        }
        boolean flag = num > 0;
        //去除尾部的0
        while(num % 10 == 0){
            num /= 10;
        }
        int cur = num % 10;
        num /= 10;
        //正常反转
        if(flag){
            while(num > 0){
                if(cur > Integer.MAX_VALUE / 10.0D){
                    return 0;
                }
                cur = (cur * 10) + num % 10;
                num /= 10;
            }
        } else {
            while(num < 0){
                if(cur < Integer.MIN_VALUE / 10.0D){
                    return 0;
                }
                cur = (cur * 10) + num % 10;
                num /= 10;
            }
        }
        return cur;
    }
    /**
     * leetcode Accept
     * 解答成功:
     * 		执行耗时:2 ms,击败了33.60% 的Java用户
     * 		内存消耗:35.5 MB,击败了79.27% 的Java用户
     *
     * @param num
     * @return
     */
    public static int reversePre(int num) {
        //将整型数转成字符数组
        String str = String.valueOf(num);
        char[] ch = str.toCharArray();
        int result = 0;
        for(int i = ch.length - 1; i >= 0; i--){
            //符号位仅做判断，不做处理
            if(ch[i] - 48 >= 0 && ch[i] - 57 <= 0) {
                if (ch[0] - 45 == 0) {
                    //负数处理流程
                    if((result <= Integer.MIN_VALUE / 10.0D) || ((result * 10 - (ch[i] - 48)) > 0)) {
                        //越界的情况,result置0，且退出循环
                        result = 0;
                        break;
                    } else{
                        //否则正常处理
                        result = result * 10 - (ch[i] - 48);
                    }
                } else {
                    //正数处理流程
                    if((result >= Integer.MAX_VALUE / 10.0D) || ((result * 10 + (ch[i] - 48)) < 0)){
                        result = 0;
                        break;
                    } else {
                        result = result * 10 + (ch[i] - 48);
                    }
                }
            }
        }
        return result;
    }
}
