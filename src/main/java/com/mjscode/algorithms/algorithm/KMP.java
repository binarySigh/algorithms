package com.mjscode.algorithms.algorithm;

/**
 * KMP算法实现
 * @author binarySigh
 */
public class KMP {
    public static void main(String[] args){
        /*String s = "mississippi";
        String p = "mississippi";*/
        String s = "mississippi";
        String p = "sippia";
        System.out.println(kmp(s, p));
    }

    /**
     * KMP算法实现。查找字符串 p 在字符串 s中出现的第一个位置。。不存在反回-1
     *    注：这里若 p为空串返回0
     * @param s
     * @param p
     * @return
     */
    public static int kmp(String s, String p){
        // 处理容易处理的输入情况
        if(s == null || p == null || p.length() > s.length()){
            return -1;
        }
        if(p.length() == 0){
            return 0;
        }
        if(p.length() == 1){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == p.charAt(0)) return i;
            }
            return -1;
        }
        //kmp算法主流程
        int[] next = getNext(p);
        int j = 0;
        for(int i = 0; i < s.length();){
            while(j >= 0){
                if(s.charAt(i) == p.charAt(j)){
                    if(j == p.length() - 1) {
                        return i - j;
                    }
                    i++;
                    j++;
                    break;
                } else if(j > 0){
                    j = next[j];
                } else {
                    i++;
                    break;
                }
            }
        }
        return -1;
    }

    public static int[] getNext(String p){
        int[] next = new int[p.length()];
        next[0] = -1;
        next[1] = 0;
        for(int i = 2; i < p.length(); i++){
            int j = i - 1;
            while(j > 0){
                if(p.charAt(i - 1) == p.charAt(next[j])){
                    next[i] = next[j] + 1;
                    break;
                } else if(j > 0){
                    j = next[j];
                } else {
                    next[i] = 0;
                    break;
                }
            }
        }
        return next;
    }
}
