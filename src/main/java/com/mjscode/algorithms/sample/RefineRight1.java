package com.mjscode.algorithms.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * 提取一个二进制数最右侧的1
 * @author bangsun
 *
 */
public class RefineRight1 {

	public static void main(String[] args){
		int[] arr;
		int[] tmpArr;
		int[] findArr;
		System.out.println("----测试开始-------");
		for(int i = 0; i < 1_000_000L; i++){
			arr = getRandomArray(200,5,5);
			tmpArr = arr;
			findArr = arr;
			if(!ArrayUtils.compareArrayList(findTwoOddTimesNum(findArr), compareMethod(tmpArr))){
				System.out.println("对比出现不一致！原数组，算法处理后结果，对照组结果分别如下：");
				ArrayUtils.showArray(arr);
				System.out.println(findTwoOddTimesNum(findArr));
				System.out.println(compareMethod(tmpArr));
			}
		}
		System.out.println("----测试结束-------");
	}

	/**
	 * 在一个数组中查找两种出现了奇数次的数，其他的数都只出现偶数次
	 *
	 * 此类算法题实现时不需要考虑对原输入值进行题设合法性校验，即认为用户输入的参数一定满足题设中的参数要求
	 *
	 * @return
	 */
	public static ArrayList findTwoOddTimesNum(int[] arr){
		ArrayList<Integer> list = new ArrayList<Integer>();
		//假设这两种出现奇数次的数分别是a,b，先求出 a^b；
		int eor = 0;  //a ^ b
		for(int i = 0; i < arr.length; i++ ){
			eor ^= arr[i];
		}
		//若eor == 0 则说明所有数都出现了偶数次，则返回数组不满足题设要求
		/*if(eor == 0){
			System.out.println("数组错误！所有数都出现了偶数次！");
			return null;
		}*/
		//因为a,b不相等，所以eor必定不为0，则eor最右侧出现1的二进制位就是能区分开ab的其中一个二进制位
		int rightOne = refingRight1(eor);
		//假设a最右侧1的二进制位与 rightOne相同，b在相应二进制位上的数是0
		//按此种规律可将原数组分为以下两种：
		//1. 和a一样最右侧的1出现的二进制位与rightOne相同的：arrA[]
		//   arrA[]满足规律：所有数都出现了偶数次，有且仅有a出现奇数次
		//2. 和b一样最右侧的1出现的二进制位与rightOne不同的：arrB[]
		//   arrB[]满足规律：所有数都出现了偶数次，有且仅有b出现奇数次
		//将arrA[]中的数全部做 异或运算，得出a
		int rightOneEor = 0;//保存arrA[]中的数的 异或运算结果
		for(int j = 0; j < arr.length; j++ ){
			if((arr[j] & rightOne) != 0){
				rightOneEor ^= arr[j];
			}
		}
		//此时若 rightOneEor ^ eor == 0,则说明原数组仅有一种数出现了奇数次，则返回数组不满足题设要求
		/*if((rightOneEor ^ eor) == 0){
			System.out.println("数组错误！原数组中有且仅有一种数出现了奇数次！");
			return null;
		}*/
		//此时得到的rightOneEor即为a;则 b = rightOneEor ^ eor
		list.add(rightOneEor);
		list.add((rightOneEor ^ eor));
		return list;
	}

	/**
	 * 利用&运算提取int类型的数最右侧的1<BR/>
	 * ~a + 1 = -a<BR/>
	 * @param a
	 * @return
	 */
	public static int refingRight1(int a){
		//return a & (~a + 1);
		return a & (-a);
	}


	//以下方法为本题对数器部分实现
	/**
	 * findTwoOddTimesNum 问题的基础解法，用于做比对校验
	 * @param arr
	 * @return
	 */
	public static ArrayList compareMethod(int[] arr){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();

		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int i = 0; i < arr.length; i++){
			if(map.get(arr[i]) != null){
				map.replace(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}

		for(Integer key : map.keySet()){
			if(map.get(key) % 2 == 1){
				list.add(key);
			}
		}
		return list;

	}

	/**
	 * 生成满足题设要求的随机数组<BR/>
	 * 返回的随机数组满足以下条件：<BR/>
	 * 数组中有多种数，其中有且仅有两种数出现了奇数次，其他种类的数均出现了偶数次<BR/>
	 * numRange: 每种数值范围[-numRange,numRange]<BR/>
	 * numCounts: 每种数出现的最大次数<BR/>
	 * numKinds: 数的种类<BR/>
	 * @return
	 */
	public static int[] getRandomArray(int numRange, int numCounts, int numKinds){
		if(numKinds <= 2){
			System.out.println("生成失败！参数不满足生成题设数组的限制条件！");
			return null;
		}
		//list即时填充随机数组
		ArrayList<Integer> list = new ArrayList<Integer>();
		//生成第一种出现奇数次的数
		int oddTimesNum1 = getRandomNum(numRange);
		int oddNum1Counts = getRandomTimes(numCounts,true);
		list.addAll(getCertainNumsList(oddTimesNum1,oddNum1Counts));
		//生成第二种出现奇数次的数
		int oddTimesNum2;
		do {
			oddTimesNum2 = getRandomNum(numRange);
		} while (oddTimesNum1 == oddTimesNum2);
		int oddNum2Counts = getRandomTimes(numCounts,true);
		list.addAll(getCertainNumsList(oddTimesNum2,oddNum2Counts));
		//生成剩余出现偶数次的数
		for(int i = 0; i < numKinds-2; i++) {
			int curNum;
			do {
				curNum = getRandomNum(numRange);
			} while (list.contains(curNum));
			int curCount = getRandomTimes(numCounts,false);
			list.addAll(getCertainNumsList(curNum,curCount));
		}
		//生成随机数组
		int[] arr = new int[list.size()];
		for(int j = 0; j < list.size(); j++){
			arr[j] = list.get(j);
		}
		return arr;
	}

	/**
	 * 返回随机数，范围[-cur，cur]
	 * @param cur
	 * @return
	 */
	public static int getRandomNum(int cur){
		return (int)((Math.random() * cur + 1) - (Math.random() * cur + 1));
	}

	/**
	 * 返回数的出现次数,范围[1,times]<BR/>
	 * cur : 最大出现次数<BR/>
	 * isOdd : 是否需要是奇数,true-奇数次；false-偶数次<BR/>
	 * @param cur
	 * @return
	 */
	public static int getRandomTimes(int cur, boolean isOdd){
		int times;
		if(isOdd){
			do {
				times = (int)(Math.random() * cur + 1);
			} while (times % 2 == 0);
		} else {
			do {
				times = (int)(Math.random() * cur + 1);
			} while (times % 2 == 1);
		}
		return times;
	}

	/**
	 * 获取某一种数的小集合<BR/>
	 * num : 特定数的值<BR/>
	 * counts ： 生成的小集合的大小<BR/>
	 * @return
	 */
	public static ArrayList<Integer> getCertainNumsList(int num, int counts){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(counts <= 0){
			return list;
		}
		for(int i = 0; i < counts; i++){
			list.add(num);
		}
		return list;
	}
}
