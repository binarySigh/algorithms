package com.mjscode.algorithms.techniques;

/**
 * 位运算小技巧
 * @author binarySigh
 * @date 2021/3/16 21:01
 */
public class bitTech {

    /**
     * 判断一个整型数是否是2的N次幂
     *      2的N次幂特点：二进制位上只有一个1，其余位全是0
     * @param i
     * @return
     */
    public static boolean isPowerOfTwo(int i){
        boolean isPower = true;
        // 方法1原理：
        //      如果一个数二进制位上只有一个1，那么把它最右侧的1提取出来，这个结果依然是它本身
        isPower = (i & (~i + 1)) == i;
        // 方法2：
        //      如果一个数二进制位上只有一个1，那么这个数-1之后这个1将会被拆散，拿着拆散之后的数跟他本身相&，结果即为0
        isPower = (i & (i - 1)) == 0;
        return isPower;
    }
}
