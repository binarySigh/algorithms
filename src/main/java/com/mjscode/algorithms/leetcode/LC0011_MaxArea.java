package com.mjscode.algorithms.leetcode;


public class LC0011_MaxArea {

	public static void main(String[] args) {
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea(height));
		System.out.println(compare(height));
		System.out.println(compare2(height));
	}
	
	/**
	 * 力扣官方提供的双指针思路
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
		if(height == null || height.length <= 1){
			return 0;
		} else if(height.length == 2){
			return Math.min(height[1], height[0]);
		}
		int i = 0;
		int j = height.length - 1;
		int max = 0;
		for(; i < j;){
			max = Math.max(max, Math.min(height[i], height[j])*(j - i));
			if(height[i] <= height[j]){
				i++;
			} else {
				j--;
			}
		}
		return max;
    }
	
	/**
	 * 对照方法，复杂度O(N^2)
	 * 	测试数据量  最高 height.length = 3*10^4
	 * @param height
	 * @return
	 */
	public static int compare(int[] height){
		if(height == null || height.length <= 1){
			return 0;
		} else if(height.length == 2){
			return Math.abs(height[1] - height[0]);
		}
		int max = 0;
		//当前两个柱子间的储水量
		int tmp = 0;
		//暴力求解，对于每个i位置来说，必须以它为左边界的容器中储水量最大的容器
		for(int i = 0; i < height.length; i++){
			for(int j = i; j < height.length; j++){
				tmp = Math.min(height[j], height[i]) * (j - i);
				max = Math.max(max, tmp);			}
		}
		return max;
	}
	
	/**
	 * 复杂度依然为O(N^2)，但在compare的基础上做了剪枝,在输入数组完全无序的前提下可以极大地节约时间
	 * 剪枝思路：对于i位置的柱子而言，它右边只有比他高的柱子有可能组成容积比他大的容器，
	 * 		因此在i执行循环时，记录下遇到的第一个比他高的柱子，下一轮循环就从这个柱子开始，其他的柱子就可以略过
	 * 执行结果：通过
	 *	执行用时：476 ms, 在所有 Java 提交中击败了24.23%的用户
	 *	内存消耗：40.2 MB, 在所有 Java 提交中击败了7.54%的用户
	 * @param height
	 * @return
	 */
	public static int compare2(int[] height){
		if(height == null || height.length <= 1){
			return 0;
		} else if(height.length == 2){
			return Math.min(height[1], height[0]);
		}
		int max = 0;
		int tmp = 0;
		//用来记录从i位置往右查找时遇到的第一个比i位置柱子高度更高的柱子的位置，如果没有更高的，则设置为-1
		int firstLarger = 0;
		while(firstLarger >= 0 && firstLarger < height.length - 1){
			//获取firstLarger的值,以开始本轮循环查找
			int begin = firstLarger;
			//获取完成后将firstLarger置为无效
			firstLarger = -1;
			//从firstLarger位置往右循环查找最大容器
			for(int i = begin; i < height.length; i++){
				//如果当前firstLarger处于无效状态，且当前柱高大于本轮初始柱子高度，则给firstLarger赋值
				if(firstLarger < begin && height[i] > height[begin]){
					firstLarger = i;
				}
				//正常的查找最大容积过程
				tmp = Math.min(height[i], height[begin]) * (i - begin);
				max = Math.max(max, tmp);
			}
		}
		return max;
	}

}
