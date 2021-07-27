package com.mjscode.algorithms.leetcode.template;

/**
 * //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * //
 * // '.' 匹配任意单个字符
 * // '*' 匹配零个或多个前面的那一个元素
 * //
 * // 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * //
 * // 示例 1：
 * //
 * //输入：s = "aa" p = "a"
 * //输出：false
 * //解释："a" 无法匹配 "aa" 整个字符串。
 * //
 * // 示例 2:
 * //
 * //输入：s = "aa" p = "a*"
 * //输出：true
 * //解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * //
 * // 示例 3：
 * //
 * //输入：s = "ab" p = ".*"
 * //输出：true
 * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * //
 * // 示例 4：
 * //
 * //输入：s = "aab" p = "c*a*b"
 * //输出：true
 * //解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * //
 * // 示例 5：
 * //
 * //输入：s = "mississippi" p = "mis*is*p*."
 * //输出：false
 *
 * // 提示：
 * //
 * // 0 <= s.length <= 20
 * // 0 <= p.length <= 30
 * // s 可能为空，且只包含从 a-z 的小写字母。
 * // p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * // 保证每次出现字符 * 时，前面都匹配到有效的字符
 * //
 * // Related Topics 字符串 动态规划 回溯算法
 *
 * @author binarySigh
 */
public class LC0010_IsMatch {
    public static void main(String[] args){
        /*char c1 = 'a';
        char c2 = 'a';
        System.out.println(c1 == c2);  // true
        System.out.println(c1 - 0);  // 97
        System.out.println(c1 == 97);  // true */
    	/*String s = "mississippi";
    	String p = "mis*is*p*";*/ //false
    	/*String s = "mississippi";
    	String p = "mis*is*p*.";*/ //false
    	/*String s = "aaa";
    	String p = "ab*ac*a";*/	//true
    	/*String s = "a";
    	String p = "ab*"; //true*/
		/*String s = "ab";
		String p = ".*..c*";*/  //true
		/*String s = "";
		String p = "c*";*/   //true
		String s = "bcccccbaccccacaa";
		String p = ".*bb*c*a*b*.*b*b*c*";   //true
    	System.out.println(isMacthV1(s, p));
    }

    /**
     *动态规划解法
     * @param s
     * @param p
     * @return
     */
    public static boolean isMacth(String s, String p){
    	// 直接返回易于辨识结果的输入
		if(s == null && p == null) {
			return true;
		}
		if(s == null || p == null) {
			return false;
		}
		if("".equals(s)){
			if("".equals(p)){
				return true;
			} else {
				int j = 0;
				while(j + 1 < p.length() && p.charAt(j + 1) == '*'){
					j += 2;
				}
				if(j < p.length()) return false;
				else return true;
			}
		}
		if("".equals(p)){
			if("".equals(s)){
				return true;
			}
			else{
				return false;
			}
		}
		//以下为正式算法流程
        boolean[][] dp = new boolean[s.length()][p.length()];
        
        
        return false;
    }

	/**
	 * 第一种解法：直接递归回溯
	 * 执行结果：通过
	 * 		执行用时：35 ms, 在所有 Java 提交中击败了 17.78% 的用户
	 * 		内存消耗：38.8 MB, 在所有 Java 提交中击败了 12.16% 的用户
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMacthV1(String s, String p){
		if(s == null && p == null) {
			return true;
		}
		if(s == null || p == null) {
			return false;
		}
		if("".equals(s)){
			if("".equals(p)){
				return true;
			} else {
				int j = 0;
				while(j + 1 < p.length() && p.charAt(j + 1) == '*'){
					j += 2;
				}
				if(j < p.length()) return false;
				else return true;
			}
		}
		if("".equals(p)){
			if("".equals(s)){
				return true;
			}
			else{
				return false;
			}
		}
		return process(s, p, 0, 0);
	}
    
    /**
     * 递归方法。该方法中仅做递归匹配，原始输入字符串是否合法需要由上层调用者来判断</BR>
     * 	默认i,j位置之前的部分已经完全匹配上；</BR>
     * 	流程中严格控制j不会来到'*'字符所在位置</BR>
     * @param s 被匹配串
     * @param p 匹配串（可能含通配符的那个）
     * @param i 被匹配串当前到达的位置
     * @param j	匹配串当前到达的位置
     * @return
     */
    public static boolean process(String s, String p, int i, int j){
    	//base case
    	if(p.charAt(j) == '*'){
    		//该流程里是严格控制j位置不会走到*字符所在位置的，如果还是发现j来到了*字符，
    		//	那么一定是匹配串中出现了以*字符开头（"*xxx"）或者连续存在两个*（"xxx**xxx"）的非法情况
    		//	这两种非法情况都统一认为匹配失败
    		return false;
    	}
    	if(i == s.length() - 1){
    		//主串当前到了最后一位字符
    		if(j == p.length() - 1){
    			if(p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)){
	    			//主串和匹配串都来到了最后一位，且两者最后一位按照规则能匹配
	    			return true;
    			} else {
    				//主串和匹配串都来到了最后一位，且两者最后一位按照规则不能匹配
    				return false;
    			}
    		} else {
				//匹配串还没到最后一位
				if(p.charAt(j + 1) == '*'){
					// 防止主串当前最后一位a；而匹配串剩余的部分是 a*b*c*，或者 a*b*c*c这两种情况
					boolean isMatch = false;
					while(j + 1 < p.length() && p.charAt(j + 1) == '*'){
						if(p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') isMatch = true;
						j += 2;
					}
					if(isMatch && j == p.length()){
						//对应 a;   a*b*c*这种情况
						return true;
					} else if(j == p.length() - 1){
						//对应 a;   a*b*c*c这种情况
						if(p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') return true;
						else return false;
					} else {
						//对应其他情况，比如a <-> a*b*c*cb*asd / a <-> s*d*c*等情况
						return false;
					}
				} else {
					//匹配串不是最后一位，且匹配串下一位也不是'*'
					if(s.charAt(i) != p.charAt(j) && p.charAt(j) != '.'){
						//对应: 主串 - a,匹配串 - ba*c*;认为匹配失败
						return false;
					} else {
						//对应：主串 - a,匹配串 - ab*c* / abb*等两种情况
						//策略就是往下一位把所有 x* 的都排除掉之后看匹配串有没有冗余，有就说明不匹配，否则说明能匹配
						int t = j + 1;
						while(t + 1 < p.length() && p.charAt(t + 1) == '*'){
							t += 2;
						}
						if(t == p.length() &&
								(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))){
							return true;
						} else {
							return false;
						}
					}
				}
    		}
    	}
    	if(j == p.length() - 1){
    		//匹配串率先走完，则认为匹配失败
    		return false;
    	}
    	if(j == p.length() - 2 && p.charAt(j + 1) == '*'){
    		//当前匹配串来到倒数第二位，且下一位字符是'*'
    		if(p.charAt(j) == '.'){
    			//当前字符是'.'，认为匹配成功
    			return true;
    		} else if(p.charAt(j) == s.charAt(i)){
    			return process(s, p, i + 1, j);
    		} else {
    			return false;
    		}
    	}
    	//下面是process主流程
    	//由于base case严格控制了两个串尾字符的情况，所以这里可以放心地使用  i+1 和 j+1,无需担心越界
    	boolean isMatch = false;
    	if(p.charAt(j + 1) != '*'){
    		//匹配串下一个字符不越界，且不是*
    		if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)){
    			isMatch = process(s, p, i + 1, j + 1);
    			if(isMatch) return isMatch;
    		} else {
    			return false;
    		}
    	} else if(p.charAt(j + 1) == '*'){
    		//匹配串下一个字符是*
    		// 这里注意因为'*'是可以匹配0的，所以递归调用一定要从i开始，而不是i+1
    		if(p.charAt(j) == '.'){
    			//匹配串当前是'.'，暴力逐位匹配
    			for(; i < s.length(); i++){
    				isMatch = process(s, p, i, j + 2);
    				if(isMatch) return true;
    			}
    			//如果用 j+2 位置与 i-s.length-1位置逐个匹配都匹配不上的话，再用j位置与s.length-1位置匹配，尝试是否匹配
				// 这是为了防止 abcccd <-> .*s*f*h* 这样的情况。如果仅以j+2位置往后暴力匹配的话按照算法逻辑是肯定匹配不上的，
				// 	而这个例子.*可以直接匹配到结尾d的位置，这样按照算法逻辑就能匹配上，所以这里j位置和s最后一个位置的匹配一定要加上
				isMatch = process(s, p, s.length() - 1, j);
    		} else if(p.charAt(j) == s.charAt(i)){
    			//匹配串不是'.',但是匹配串当前位置和主串当前位置相同，同样暴力循环
    			//如果主串当前处在一个连续相同的字符区，那么获取这一块相同字符区的右边界
    			int t = i;
    			while(s.charAt(t) == s.charAt(i)){
    				//如果t已经来到最后一位，那么直接break，防止t越界
    				if(t == s.length() - 1) break;
    				//否则t++
    				t++;
    			}
    			//与当前是'.'的策略不同的是，这里暴力循环最多只能循环到主串当前重复区的下一位
    			for(; i <= t; i++){
    				isMatch = process(s, p, i, j + 2);
    				if(isMatch) return true;
    			}
    		} else {
    			//匹配串既不是'.',也跟主串当前位置不相同
    			isMatch = process(s, p, i, j + 2);
    			if(isMatch) return true;
    		}
    	}
    	return isMatch;
    }
}
