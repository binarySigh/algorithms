package com.mjscode.algorithms.newCoder.huawei;

import java.util.Scanner;

/**
 * A类地址1.0.0.0~126.255.255.255;
 * B类地址128.0.0.0~191.255.255.255;
 * C类地址192.0.0.0~223.255.255.255;
 * D类地址224.0.0.0~239.255.255.255；
 * E类地址240.0.0.0~255.255.255.255
 *
 * 私网IP范围是：
 * 10.0.0.0～10.255.255.255
 * 172.16.0.0～172.31.255.255
 * 192.168.0.0～192.168.255.255
 *
 *  类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
 * @author binarySigh
 * @date 2021/8/23 20:50
 */
public class HJ18_ValidIPAndipMask {
    public static long[][] range = {
            {1L << 24, 126L << 24 | 0x00ffffff}, //A
            {128L << 24, 191L << 24 | 0x00ffffff},//B
            {192L << 24, 223L << 24 | 0x00ffffff},//C
            {224L << 24, 239L << 24 | 0x00ffffff},//D
            {240L << 24, 255L << 24 | 0x00ffffff}, //E
            {10L << 24, 10L << 24 | 0X00ffffff}, //私网
            {(172L << 24) | (16L << 16), (172L << 24) | (31L << 16) | 0x0000ffff}, //私网
            {(192L << 24) | (168L << 16), (192L << 24) | (168L << 16) | 0X0000ffff} //私网
    };
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] ret = new int[7];
        long ip = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            if(s.indexOf('~') <= 0){
                ret[5]++;
                continue;
            }
            String s1 = s.substring(0, s.indexOf('~'));
            String s2 = s.substring(s.indexOf('~') + 1);
            if(s2 == null || s2.length() == 0){
                ret[5]++;
                continue;
            }
            if(!(s2.startsWith("0.") || s2.startsWith("127."))){
                if(!isValidMask(s2)){
                    ret[5]++;
                    continue;
                }
            }
            if(!(s1.startsWith("0.") || s1.startsWith("127."))){
                ip = isValidIp(s1);
                count(ret, ip);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ret[0]).append(" ").append(ret[1]).append(" ")
                .append(ret[2]).append(" ").append(ret[3]).append(" ")
                .append(ret[4]).append(" ").append(ret[5]).append(" ")
                .append(ret[6]).append(" ");
        System.out.println(sb.toString());
    }

    public static void count(int[] ret, long ip){
        if(ip < 0){
            ret[5]++;
            return;
        }
        if(ip >= range[0][0] && ip <= range[0][1]){
            ret[0]++;
        }
        if(ip >= range[1][0] && ip <= range[1][1]){
            ret[1]++;
        }
        if(ip >= range[2][0] && ip <= range[2][1]){
            ret[2]++;
        }
        if(ip >= range[3][0] && ip <= range[3][1]){
            ret[3]++;
        }
        if(ip >= range[4][0] && ip <= range[4][1]){
            ret[4]++;
        }
        if((ip >= range[5][0] && ip <= range[5][1]) ||
                (ip >= range[6][0] && ip <= range[6][1]) ||
                (ip >= range[7][0] && ip <= range[7][1])){
            ret[6]++;
        }
    }

    public static long isValidIp(String s){
        long ip = 0L;
        long cur = -1L;
        for(int i = 0; i <= s.length(); i++){
            if(i == s.length() || s.charAt(i) == '.'){
                if(cur < 0 || cur > 255){
                    return -1;
                }
                ip = ip << 8 | cur;
                cur = -1L;
            } else {
                cur = cur < 0 ? 0 : cur;
                cur = cur * 10 + s.charAt(i) - '0';
            }
        }
        return ip;
    }

    /**
     * 注意：合格的掩码 不只是 255.255.255.0;只要转化后的二进制形式形如 11110000...即可
     * @param s
     * @return
     */
    public static boolean isValidMask(String s){
        long ip = 0L;
        long cur = -1L;
        for(int i = 0; i <= s.length(); i++){
            if(i == s.length() || s.charAt(i) == '.'){
                ip = ip << 8 | cur;
                cur = -1L;
            } else {
                cur = cur < 0 ? 0 : cur;
                cur = cur * 10 + s.charAt(i) - '0';
            }
        }
        return checkMask(ip);
    }

    public static boolean checkMask(long mask){
        if(mask == range[4][1] || mask == 0){
            return false;
        }
        // 找到最右侧的1，并将其左边全部覆盖为1
        int p = (int)(mask & (-mask));
        p |= (p << 1);
        p |= (p << 2);
        p |= (p << 4);
        p |= (p << 8);
        p |= (p << 16);
        //如果相 ^ 不为0，则说明原来mask的1不连续
        return (p ^ (int)mask) == 0;
    }
}
