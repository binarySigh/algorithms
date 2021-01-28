package com.mjscode.algorithms.leetcode;

public class LC0014_LongestCommonPrefix {

	public static void main(String[] args) {
		

	}
	
	/**
	 * 力扣官方提供的二分查找法
	 * 简单描述就是：
	 * 		1.找到数组中最小的串，假设为str,长度为8；
	 *  	2.用str前半段(str.subString(0,4))去匹配；如果发现有某一个不能完全匹配那么本轮结束全部匹配工作也就结束；
	 *  	3.如果经历了第二步发现所有串都能匹配上，那就将前半段加上后半段的前半段作为匹配串（str.subString(0,6)），继续匹配；
	 *  	4.重复第三步，直到找到最大前缀串为止
	 * 该方案可作为实现思路之一，但是复杂度比常规方案要高，只有当第二步就能结束匹配时整体耗时才会比常规方案低，故这里仅作介绍不做实现。
	 * 
	 * 另外官方提供的分治法思路，实际耗时也大概率比常规方案要高
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		return "";
	}
	
	/**
	 * 执行结果：通过
	 *	执行用时：12 ms, 在所有 Java 提交中击败了5.77%的用户
	 *	内存消耗：38.2 MB, 在所有 Java 提交中击败了12.36%的用户
	 *
	 *本思路可优化点：先取到最短的那个字符串，以它为基准；这里为了简便是直接以数组中第一个字符串为基准来做的
	 * @param strs
	 * @return
	 */
	public static String mySolution(String[] strs) {
        //前缀树的简单应用
        if(strs == null || strs.length == 0){
            return "";
        } else if(strs.length == 1 || strs[0] == null || strs[0].length() == 0){
            return strs[0];
        }
        //实现前缀树效果的简单数组（这个chars空间这里也可以节约下来）
        char[] chars = strs[0].toCharArray();
        //当前公共前缀最大位置
        int valid = chars.length - 1;
        for(int i = 1; i < strs.length; i++){
            if(valid < 0 || strs[i].length() <= 0){
                valid = -1;
                break;
            }
            for(int j = 0; j < strs[i].length() && j <= valid; j++){
                if(strs[i].charAt(j) != chars[j]){
                    valid = j - 1;
                    break;
                }
                //当前已遍历到I位置字符串的末尾，结果还相等，那么valid强制更新为当前串长度
                if(strs[i].charAt(j) == chars[j] && j == strs[i].length() - 1){
                    valid = j;
                }
            }
        }
        String ret = "";
        for(int i = 0; i <= valid; i++){
            ret += chars[i];
        }
        return ret;
    }

}
