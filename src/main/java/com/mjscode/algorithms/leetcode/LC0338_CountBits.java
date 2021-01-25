package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * leetcde 338,给定非负整数num,对于0~num上每个数字二进制位上1出现的次数,记录成数组形式，并返回
 * 例：num = 2
 * 返回：[0,1,1]
 * 例：num = 5
 * 返回：[0,1,1,2,1,2]
 * @author binarySigh
 *
 */
public class LC0338_CountBits {

	public static void main(String[] args) {
		System.out.println("--------测试开始--------");
		for(int i = 0; i < 50_0000; i++){
			int num = (int)(Math.random() * 1001);
			int[] result = countBits(num);
			int[] expected = compare(num);
			boolean same = ArrayUtils.getArray(result).equals(ArrayUtils.getArray(expected));
			if(!same){
				System.out.println("-------第[" + i + "]次结果出错-------");
				System.out.println("num = " + num);
				System.out.println("测试结果如下：");
				ArrayUtils.showArray(result);
				System.out.println("期望结果如下：");
				ArrayUtils.showArray(expected);
				break;
			}
		}
		System.out.println("--------测试结束--------");
		/*for(int i = 0; i < 100; i++){
			System.out.println("num: " + i);
			ArrayUtils.showArray(compare(i));
		}*/
	}
	
	public static int[] countBits(int num){
		if(num <= 0){
			int[] arr = {0};
			return arr;
		}
		int[] arr = new int[num + 1];
		arr[0] = 0;
		for(int i = 1; i <= num; i++){
			arr[i] = arr[(i >> 1)] + (i & 1);
		}
		return arr;
	}
	
	public static int[] compare(int num){
		if(num <= 0){
			int[] arr = {0};
			return arr;
		}
		int[] arr = new int[num + 1];
		int count = 0;
		for(int i = 0; i <= num; i++){
			for(int j = 0; j < 32; j++){
				if((i & (1 << j)) != 0){
					count++;
				}
			}
			arr[i] = count;
			count = 0;
		}
		return arr;
	}

}
