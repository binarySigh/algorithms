package com.mjscode.algorithms.newCoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 对输入字符串中的字符重新排序，规则如下：
 *  1. 只对字母进行排序，按照字典序排序；
 *  2. 大小写不区分，如果两个字符互为大小写，则它们的顺序依然为他们在原字符串中的相对顺序
 *  3. 所有非字母字符所在的绝对位置不能改变
 * @author binarySigh
 * @date 2021/8/23 16:58
 */
public class HJ26_StringSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            char[] str = new char[s.length()];
            int j = 0;
            for(int i = 0; i < s.length(); i++){
                if(check(s.charAt(i))){
                    str[j++] = s.charAt(i);
                }
            }
            //排序,因为这里需要保留相同类字符的相对顺序，所以使用冒泡而非效率更高的快排
            for(int i = 0; i < j; i++){
                for(int t = i; t > 0; t--){
                    if(compare(str[t - 1], str[t]) <= 0){
                        break;
                    } else {
                        char tmp = str[t];
                        str[t] = str[t - 1];
                        str[t - 1] = tmp;
                    }
                }
            }
            //还原
            StringBuilder sb = new StringBuilder();
            j = 0;
            for(int i = 0; i < s.length(); i++){
                if(!check(s.charAt(i))){
                    sb.append(s.charAt(i));
                } else {
                    sb.append(str[j++]);
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static boolean check(char c){
        if((c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z')){
            return true;
        }
        return false;
    }

    public static int compare(char a, char b){
        if(a > 'Z'){
            a = (char) (a - 'a' + 'A');
        }
        if(b > 'Z'){
            b = (char) (b - 'a' + 'A');
        }
        return a - b;
    }
}
