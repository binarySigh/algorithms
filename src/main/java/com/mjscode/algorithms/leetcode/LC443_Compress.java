package com.mjscode.algorithms.leetcode;

/**
 * @author binarySigh
 * @date 2021/8/21 9:55
 */
public class LC443_Compress {
    public static void main(String[] args){
        char[] s = {'a','b','b','b','b','b','b','b','c','b','b','b','b'};
        int n = compress(s);
        System.out.println(n);
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了98.90% 的Java用户
     * 		内存消耗:38 MB,击败了74.97% 的Java用户
     * @param chars
     * @return
     */
    public static int compress(char[] chars) {
        int i = 0, j = 0;
        int digits = 0;
        int cnt = 0;
        while(j < chars.length){
            while(j < chars.length && chars[j] == chars[i]){
                cnt++;
                j++;
            }
            if(cnt > 1){
                digits = getDigits(cnt);
                for(int c = i + digits; c >= i && cnt > 0; c--){
                    chars[c] = (char)((cnt % 10) + '0');
                    cnt /= 10;
                }
            }
            i += digits;
            if(j < chars.length){
                chars[++i] = chars[j];
            }
            cnt = 0;
            digits = 0;
        }
        return i + 1;
    }

    public static int getDigits(int n){
        int d = 0;
        while(n > 0){
            d++;
            n /= 10;
        }
        return d;
    }
}
