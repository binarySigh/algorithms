package com.mjscode.algorithms.leetcode;

/**
 * //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * //
 * // 示例 1：
 *
 * //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * //输出：6
 * //解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * //
 * // 示例 2：
 * //
 * //输入：height = [4,2,0,3,2,5]
 * //输出：9
 * //
 * // 提示：
 * //
 * // n == height.length
 * // 0 <= n <= 3 * 104
 * // 0 <= height[i] <= 105
 * //
 * // Related Topics 栈 数组 双指针 动态规划
 * @author binarySigh
 */
public class LC0042_Trap {

	public static void main(String[] args) {
		//int[] height = {0,1,0,4,1,0,2,1,0,4};
		int[] height = {5,5,1,7,1,1,5,2,7,6};
		//System.out.println(compare(height));
		System.out.println(trap(height));
	}
	
	/**
	 * 思路：使用单调栈解决
	 * 执行结果：通过
	 *	执行用时：1 ms, 在所有 Java 提交中击败了99.89% 的用户
	 *	内存消耗：38.2 MB, 在所有 Java 提交中击败了31.19%的用户
	 * @param height
	 * @return
	 */
	public static int trap(int[] height) {
		if(height == null || height.length <= 2){
			return 0;
		}
		/**pos:单调栈数组，含义如下：
		 *  pos[i][0]:height数组中i位置柱子左边离他最近高度大于等于它的柱子的位置
		 *  pos[i][1]:height数组中i位置柱子右边离他最近高度大于等于它的柱子的位置
		 * 这里用不到这个辅助数组，每次结算都用rains收集了，所以这里也注释掉省去pos的空间开销
		 */
		//int[][] pos = new int[height.length][2];
		//使用stack + top 来模拟替代系统提供的栈结构
		// stack中压入的是柱子的位置而不是高度
		// top记录当前栈顶在 stack中的位置，如果此时栈空则为-1
		int[] stack = new int[height.length];
		int top = -1;
		//rains 收集每一步产生的雨水量
		int rains = 0;
		//填充单调栈数组，单调栈中元素 自底向顶 依次变小
		for(int i = 0; i < height.length; i++){
			//如果栈空，或者栈顶元素大于当前元素
			if(top == -1 || height[stack[top]] > height[i]){
				stack[++top] = i;
			}
			//栈不为空，且当前柱高大于等于栈顶位置柱子高度
			else {
				//只要栈中还有元素，且栈顶柱子高度大于当前柱子高度，就一直弹出，每次弹出需要结算
				// 这里相等也直接弹出，因为等高的柱子对应的雨水区积水量一定会在最后一根柱子弹出时被结算
				while(top >= 0 && height[stack[top]] <= height[i]){
					int tmp = stack[top--];
					//弹出元素时计算必须以它为积水区最低深度的雨水量
					//min表示 tmp 位置左右两边离他最近不低于它的两个柱子里，较低的那个柱子的高度
					int min = Math.min(height[i], top == -1 ? 0 : height[stack[top]]);
					// min - height[tmp] ： 当前积水区的高
					// i - stack[top] - 1： 当前积水区的底
					rains += min == 0 ? 0 : (min - height[tmp]) * (i - stack[top] - 1);
				}
				//退出循环时表示栈空或者战中元素符合要求，加入当前柱子
				stack[++top] = i;
			}
		}
		//经典单调栈在退出for循环后是一定要对栈中剩余元素逐个结算的，但是这里栈中剩余元素其实不会产生雨水量，故这里略去，节约时间
		return rains;
    }
	
	/**
	 * 思路：对于i位置的柱子而言，它能额外接到的雨水量 = 
	 * 	它到左边最近高度≥它的柱子之间的有效容积 - 他俩之间所有柱子能攒下的雨水量 - 他俩之间所有柱子的所占体积（这里就是柱子高度）
	 * 补充：如果左边柱子都比它低，那么就以左边柱子中最高的且离他最近的那一个柱子来计算
	 * @param height
	 * @return
	 */
	public static int compare(int[] height){
		if(height == null || height.length <= 2){
			return 0;
		}
		int[] record = new int[height.length];
		record[0] = 0;
		int total = 0;
		int tmp = 0;
		//记录之前遍历过的柱子中最高的那一个（如果有多个，则在实际计算中会取位置靠后的那个）
		int leftHeighest = 0;
		for(int i = 1; i < height.length; i++){
			tmp = i - 1;
			//遍历策略：
			//  找到i位置左边离他最近且不矮于它的那根柱子；
			//	如果左边都比他矮，那么就找左边最高的那根柱子(若有多个最高，就取离他最近的那个)
			while(tmp >= 0 && 
					(height[tmp] < height[i] && height[tmp] < leftHeighest)){
				//这里record[i]用来记录的是前面柱子高度以及他们储存雨水量之和，
				//	这个是后面要减掉的部分，这里为了节约变量，暂时记录在record[i]中
				record[i] += (height[tmp] + record[tmp]);
				tmp--;
			}
			//如果是因为越界导致的退出循环，则i位置结果算作0
			if(tmp < 0){
				record[i] = 0;
			} 
			//如果找到了i位置左边离他最近比他大的值，那么判断
			else {
				//这个位置正好是i - 1位置，那么record[i]依然算作0
				if(tmp == i - 1){
					record[i] = 0;
				}
				//如果找到的是比i位置柱子高的，那么以i位置柱高计算
				else if(height[tmp] >= height[i]){
					record[i] = height[i] * (i - tmp - 1) - record[i];
				}
				//如果左边没有比他高的，当前找到的是左边最大的,那么就以leftHeighest来计算
				else if(height[tmp] < height[i] && height[tmp] == leftHeighest){
					record[i] = leftHeighest * (i - tmp - 1) - record[i];
				}
			}
			total += record[i];
			//更新最大柱子高度
			leftHeighest = Math.max(leftHeighest, height[i]);
		}
		return total;
	}

}
