package com.mjscode.algorithms.sample;

import java.util.ArrayList;
import java.util.HashMap;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * 从一个整型数组中找到出现了K次的数<BR/>
 * 该数组满足以下特征：<BR/>
 * 有且仅有一种数出现了K次，其余数都出现了M次，{@code M>1,K<M} <BR/>
 * 要求：额外空间复杂度O(1),时间复杂度O(N)<BR/>
 * @author bangsun
 *
 */
public class FindKTimesNum {

	public static void main(String[] args) {
		/*int[] arr = {1,1,1,1,-2,-2,-2,0,4,4,4,4,-15,-15,0,-15,0,0,-15,34,34,34,34};
		System.out.println(findKTimesNum(arr,3,4));*/
		System.out.println("----------测试开始-----------");
		for(int i = 0; i < 4_000_000L; i++) {
			int kinds = getRandomKinds(5);
			int k, m;
			do {
				m = getRandomTimes(5);
			} while (m <= 1);
			do {
				k = getRandomTimes(4);
			} while (k >= m || k < 1);
			int[] arr = getRandomArray(200, kinds, k, m);
			int[] findArr = arr;
			int[] tmpArr = arr;
			if(findKTimesNum(findArr, k, m) != compareMethod(tmpArr, k, m)) {
				System.out.println("比对出错！出错数组如下：");
				ArrayUtils.showArray(arr);
				System.out.println("kinds = " + kinds);
				System.out.println("k = " + k);
				System.out.println("m = " + m);
				System.out.println("findKTimesNum计算结果 ： " + findKTimesNum(findArr, k, m));
				System.out.println("compareMethod计算结果 ： " + compareMethod(tmpArr, k, m));
			}

		}
		System.out.println("----------测试结束-----------");
	}
	
	/**
	 * 从一个数组中查找出现了K次的数，假设为result<BR/>
	 * 实现思路如下：<BR/>
	 * 用一个32位数组tmp代表整型数的32位，对原数组进行遍历，当前数的二进制位上每出现一次1，就在tmp对应位置+1；<BR/>
	 * 最后统计tmp每一位上的数,如果某一位上的数%M ！= 0，则说明a对应的二进制位上即为1，这样即可推导出result<BR/>
	 * @param arr
	 * @param k
	 * @param m
	 * @return
	 */
	public static int findKTimesNum(int[] arr, int k, int m){
		//tmp数组代表整型数的二进制位，tmp[0]代表低位，tmp[31]代表高位
		int[] tmp = new int[32];
		int result = 0;
		//遍历原数组，对其中每个数的二进制位情况进行记录
		for(int i = 0; i < arr.length; i++){
			//虽然下面也是for循环，但是因为是有限次，所以计算复杂度是O(1)
			for(int j = 0; j < 32; j++){
				//1 << j 表示 2 ^ j
				//(arr[i] & (1 << j)) != 0 表示 arr[i]在第j位上为1
				if((arr[i] & (1 << j)) != 0){
					tmp[j] ++;
				}
			}
		}
		
		//遍历tmp数组，还原出result
		for(int t = 0; t < 32; t++){
			//若tmp[t] % m != 0，则说明result在t位上值为1
			if(tmp[t] % m != 0){
				result += (1 << t);
			}
		}
		
		return result;
	}
	
	//以下代码为本题对数器部分实现

	/**
	 * 本题对照解法</BR>
	 * @param arr
	 * @param k
	 * @param m
	 * @return
	 */
	public static int compareMethod(int[] arr, int k, int m){
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int result = 0;
		for(int i = 0; i < arr.length; i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		for(Integer key : map.keySet()){
			if (map.get(key) == k) {
				result = key;
				break;
			}
		}
		return result;
	}

	/**
	 * 按以下规律生成随机数组：</BR>
	 * 共有kinds种数，每种数范围[-range,range],其中有且仅有一种数出现k次，其余数都出现了m次
	 * {@code k < m ; m > 1} </BR>
	 * @param range
	 * @param kinds
	 * @param k
	 * @param m
	 * @return
	 */
	public static int[] getRandomArray(int range, int kinds, int k, int m){
		//nums 用于记录已生成的数，防止重复生成
		ArrayList<Integer> nums = new ArrayList<Integer>();
		if(k >= m || m <= 1 || kinds < 2){
			System.out.println("随机数组生成失败！k,m,kinds的值不满足题设要求！");
			System.out.println("k=" + k + ", m=" + m + ", kinds=" + kinds);
			return null;
		}
		//先生成出现K次的数
		int kTimesNum = getRandomNum(range);
		nums.add(kTimesNum);
		//生成剩余的出现了M次的数
		for(int i = 0; i < kinds - 1; i ++){
			int curNum;
			do{
				curNum = getRandomNum(range);
			} while(nums.contains(curNum));
			nums.add(curNum);
		}
		//开始生成随机数组
		int[] arr = new int[k + (kinds-1)*m];
		//记录数组当前已填充到的下标位置
		int j = 0;
		//遍历nums数组，对待生成数组进行填充
		for(int i = 0; i < nums.size(); i++){
			if(nums.get(i) == kTimesNum){
				for(int t = 0; t < k; t++){
					arr[j+t] = kTimesNum;
				}
				j = j + k;
			} else {
				for(int t = 0; t < m; t++){
					arr[j+t] = nums.get(i);
				}
				j = j + m;
			}
		}
		return arr;
	}

	/**
	 * 生成随机数，范围[-range,range]</BR>
	 * @param range </BR>
	 * @return
	 */
	public static int getRandomNum(int range){
		return (int)((Math.random() * range + 1) - (Math.random() * range + 1));
	}

	/**
	 *随机生成数组中数的种数，最少为2，最大为max </BR>
	 * @param max </BR>
	 * @return
	 */
	public static int getRandomKinds(int max){
		int numKinds = 2;
		do{
			numKinds = (int)(Math.random() * max + 1);
		} while(numKinds < 2);
		return numKinds;
	}

	/**
	 * 随机生成每种数出现次数，范围[1,max]
	 * @param max
	 * @return
	 */
	public static int getRandomTimes(int max){
		return (int)(Math.random() * max + 1);
	}
}
