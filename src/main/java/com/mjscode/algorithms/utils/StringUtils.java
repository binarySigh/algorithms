package com.mjscode.algorithms.utils;

/**
 *
 * 字符串工具类
 * @author binarySigh
 */
public class StringUtils {

    /**
     * 返回随机生成指定的只含有大小写字母的字符串
     * @param maxLength 随机字符串的最大长度
     * @param charCase 负数-只包含小写字母，正数-只包含大写字母，0-大小写字母混合
     * @return
     */
    public static String generateString(int maxLength, int charCase){
        int len = (int)(Math.random() * maxLength + 1);
        if(len == 0){
            return "";
        }
        char[] chars = new char[len];
        for(int i = 0; i < len; i++){
            chars[i] = generateChar(charCase);
        }
        return String.valueOf(chars);
    }

    /**
     * 随机生成大小写字符
     *      大写字符：65-90
     *      小写字符：97-122
     * @param charCase 负数-只生成小写字母，正数-只生成大写字母，0-大小写字母随机生成
     * @return
     */
    private static char generateChar(int charCase){
        int lowwerChar = 0;
        int upperChar = 0;
        do{
            lowwerChar = (int)(Math.random() * 122 + 1);
        } while(lowwerChar - 97 < 0);
        do{
            upperChar = (int)(Math.random() * 90 + 1);
        } while(upperChar - 65 < 0);

        if(charCase < 0){
            return (char)lowwerChar;
        } else if(charCase > 0){
            return (char)upperChar;
        } else {
            return Math.random() > 0.5 ? (char)lowwerChar : (char)upperChar;
        }
    }
}
