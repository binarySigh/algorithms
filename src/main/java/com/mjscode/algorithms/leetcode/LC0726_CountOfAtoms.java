package com.mjscode.algorithms.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * //给定一个化学式formula（作为字符串），返回每种原子的数量。
 * //
 * // 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * //
 * // 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * //
 * // 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * //
 * // 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * //
 * // 给定一个化学式 formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字
 * //典序），跟着它的数量（如果数量大于 1），以此类推。
 * //
 * // 示例 1：
 * //
 * //输入：formula = "H2O"
 * //输出："H2O"
 * //解释：
 * //原子的数量是 {'H': 2, 'O': 1}。
 * //
 * // 示例 2：
 * //
 * //输入：formula = "Mg(OH)2"
 * //输出："H2MgO2"
 * //解释：
 * //原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * //
 * // 示例 3：
 * //
 * //输入：formula = "K4(ON(SO3)2)2"
 * //输出："K4N2O14S4"
 * //解释：
 * //原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * //
 * // 示例 4：
 * //
 * //输入：formula = "Be32"
 * //输出："Be32"
 * //
 * // 提示：
 * //
 * // 1 <= formula.length <= 1000
 * // formula 由小写英文字母、数字 '(' 和 ')' 组成。
 * // formula 是有效的化学式。
 * //
 * // Related Topics 栈 哈希表 字符串
 * @author binarySigh
 */
public class LC0726_CountOfAtoms {

	public static void main(String[] args) {
		String formula = "K4(ON(SO3)2)2";
		System.out.println(countOfAtoms(formula));

	}
	
	/**
	 * 执行结果：通过
	 *  执行用时：24 ms, 在所有 Java 提交中击败了 5.36% 的用户
	 *  内存消耗：38.6 MB, 在所有 Java 提交中击败了 15.72% 的用户
	 * @param formula
	 * @return
	 */
	public static String countOfAtoms(String formula) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Stack<String> stack = new Stack<String>();
		String cur = "";
		for(int i = 0; i < formula.length(); i++){
			if(formula.charAt(i) == '('){
				stack.push(cur);
				cur = "";
			} else if(formula.charAt(i) == ')'){
				int count = 0;
				i++;
				while(i < formula.length() &&
						formula.charAt(i) >= '0' && formula.charAt(i) <= '9'){
					count = count * 10 + formula.charAt(i) - '0';
					i++;
				}
				count = count == 0 ? 1 : count;
				cur = getNewFormula(cur, count);
				cur = stack.pop() + cur;
				i--;
			} else {
				cur += formula.charAt(i);
			}
		}
		fillMap(cur, map);
		String ans = "";
		for(int i = 0; i < 26; i++){
			String curAtom = "" + (char)(i + 'A');
			if(map.containsKey(curAtom)){
				int curAtomCount = map.get(curAtom);
				ans += curAtom + (curAtomCount == 1 ? "" : curAtomCount);
			}
			for(int j = 0; j < 26; j++){
				String atom = curAtom + (char)(j + 'a');
				if(map.containsKey(atom)){
					int atomCount = map.get(atom);
					ans += atom + (atomCount == 1 ? "" : atomCount);
				}
			}
		}
		return ans;
    }
	
	public static String getNewFormula(String pre, int count){
		if(count == 1){
			return pre;
		}
		String ret = "" + pre.charAt(0);
		int curCount = 0;
		for(int i = 1; i < pre.length(); i++){
			if(pre.charAt(i) >= 'A' && pre.charAt(i) <= 'Z'){
				ret += String.valueOf((curCount == 0 ? 1 : curCount) * count) + (char)pre.charAt(i);
				curCount = 0;
			} else if(pre.charAt(i) >= '0' && pre.charAt(i) <= '9'){
				curCount = curCount * 10 + pre.charAt(i) - '0';
			} else {
				ret += (char)pre.charAt(i);
			}
		}
		ret += String.valueOf((curCount == 0 ? 1 : curCount) * count);
		return ret;
	}
	
	public static void fillMap(String curS, HashMap<String, Integer> map){
		String ret = "";
		int count = 0;
		for(int i = 0; i < curS.length(); i++){
			if(curS.charAt(i) >= 'A' && curS.charAt(i) <= 'Z'){
				count = count == 0 ? 1 : count;
				if(map.containsKey(ret)){
					map.put(ret, map.get(ret) + count);
				} else {
					map.put(ret, count);
				}
				ret = "" + curS.charAt(i);
				count = 0;
			} else if(curS.charAt(i) >= '0' && curS.charAt(i) <= '9') {
				count = count * 10 + curS.charAt(i) - '0';
			} else {
				ret += curS.charAt(i);
			}
		}
		count = count == 0 ? 1 : count;
		if(map.containsKey(ret)){
			map.put(ret, map.get(ret) + count);
		} else {
			map.put(ret, count);
		}
	}

}
