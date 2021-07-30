package com.mjscode.algorithms.leetcode.template;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * //给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 rig
 * //ht（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR n
 * //ums[right] 。
 * //
 * // 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,0,3,0], k = 1
 * //输出：3
 * //解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
 * //输出：3
 * //解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
 * //输出：3
 * //解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
 * //
 * // 提示：
 * //
 * // 1 <= k <= nums.length <= 2000
 * // 0 <= nums[i] < 210
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/5/25 19:50
 */
public class LC1787_MinChanges {
    public static void main(String[] args){
        // --> 11
        /*int[] nums1 = {26,19,19,28,13,14,6,25,28,19,0,15,25,11};
        int k1 = 3;*/

        // --> 3
        /*int[] nums = {3,4,5,2,1,7,3,4,7};
        int k = 3;*/

        // -- > 3
        /*int[] nums = {1,2,0,3,0};
        int k = 1;*/

        // -> 3
        /*int[] nums = {1,2,4,1,2,5,1,2,6};
        int k = 3;*/

        // --> 75
        int[] nums1 = {231,167,89,85,224,180,45,58,23,108,157,95,
                108,64,206,109,147,28,194,17,4,46,74,96,237,109,114,
                122,161,76,181,251,9,82,44,15,242,7,23,109,210,109,181,
                12,14,226,61,49,8,74,19,152,4,137,243,27,187,200,168,145,
                188,203,98,193,253,133,164,198,132,119,148,146,94,43,181,123,212,83,157};
        int k1 = 2;
        System.out.println("动态规划：" + minChanges(nums1, k1));
        System.out.println("对照结果：" + compare(nums1, k1));

        System.out.println("-----------------测试开始-----------------");
        /*for(int i = 0; i < 50_0000; i++){
            int len = (int)(Math.random() * 15) + 1;
            int k = (int)(Math.random() * len) + 1;
            int[] nums = getNums(len);
            int result = minChanges(nums, k);
            int answer = compare(nums, k);
            if(result != answer){
                System.out.println("--------第[" + i + "]次测试出错---------");
                ArrayUtils.showArray(nums);
                System.out.println("k = " + k);
                System.out.println("我的答案：" + result);
                System.out.println("正确答案：" + answer);
                break;
            }
        }*/
        System.out.println("-----------------测试结束-----------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:466 ms,击败了91.57% 的Java用户
     * 		内存消耗:54.7 MB,击败了5.06% 的Java用户
     *
     * 时间复杂度估算：
     *  1. k: 2000 -> 2 * 10^3
     *  2. maxXor: 2^11 - 1 -> 2047 -> 2 * 10^3
     *  3. 枚举：nums.length/k
     *  整体复杂度：O(k * maxXor * 枚举)，
     *  其中 k * 枚举 是有最大值的，当 k 最接近 enum时二者乘积最大，最大不超过50*50，也就是2.5 * 10^3
     *  也就是说整体复杂度大约在：2*10^3 * 2.5*10^3 -> 5 * 10^6
     * @param nums
     * @param k
     * @return
     */
    public static int minChanges(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer>[] kList = new HashMap[k];
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            int index = i % k;
            if(kList[index] == null){
                kList[index] = new HashMap<>();
            }
            kList[index].put(nums[i], kList[index].getOrDefault(nums[i], 0) + 1);
        }
        //找到词频最低的数所在的组号,记录每组照里面词频最高的
        int[] maxIndex = new int[k];
        for(int i = 0; i < kList.length; i++){
            for(Map.Entry<Integer, Integer> entry : kList[i].entrySet()){
                maxIndex[i] = Math.max(maxIndex[i], entry.getValue());
            }
        }
        //在最高的里面挑出最低的
        int minIndex = 0;
        int min = maxIndex[0];
        for(int i = 1; i < k; i++){
            if(maxIndex[i] < min){
                min = maxIndex[i];
                minIndex = i;
            }
        }
        //人为将最高词频最小的那一组交换到最后一组
        HashMap<Integer, Integer> tmp = kList[minIndex];
        if(minIndex < k - 1) {
            kList[minIndex] = kList[k - 1];
            kList[k - 1] = tmp;
        }
        if(max == 0){
            return 0;
        }
        int maxXor = getMaxXor(max);
        int[][] dp = new int[k][maxXor];
        //先填充第一组位置
        for(Map.Entry<Integer, Integer> entry : kList[0].entrySet()){
            dp[0][entry.getKey()] = entry.getValue();
        }
        //填充后续组位置。填充方法为：利用前一组填充当前组
        //每个格子的赋值一定要用max，确保赋值最大的。因为前面组的情况跟当前组的不同数异或后可能映射到当前组的同一个格子上
        for(int i = 0; i < k - 1; i++){
            for(int j = 0; j < maxXor; j++){
                if(dp[i][j] == 0 && j > 0){
                    //过滤无效位置。前面一个数都不取理论上异或值无法凑成一个大于0的数
                    // j == 0时例外，因为一个数都不取异或值本来就是0
                    continue;
                }
                //当前组的数一个也不取
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                //当前组的数取其中一种的情况
                for(Map.Entry<Integer, Integer> entry : kList[i + 1].entrySet()){
                    int num = entry.getKey();
                    int saved = entry.getValue();
                    dp[i + 1][j ^ num] = Math.max(dp[i + 1][j ^ num], dp[i][j] + saved);
                }
            }
        }
        //最大保留数可能来自于 dp[k-1][0],也可能来自于倒数第二行中的最大值
        //前者找的是 k组数参与选择最终恰好异或值为0的情况；
        //后者找的是 k-1组数参与选择的最大值，不带最后一组是因为可能这个最大值带上最后一组的数异或和怎么都凑不出0
        max = dp[k - 1][0];
        if(k > 1){
            //在倒数第二行找可能性的前提是至少得有两行，也就是说 k > 1
            for(int i = 0; i < maxXor; i++){
                max = Math.max(max, dp[k - 2][i]);
            }
        }
        return nums.length - max;
    }

    /**
     * 找到 大于 max的 2的 n次幂
     *  这里要找的是绝对大于的，所以 max不能先 -1
     *  题目限制 max最大为 2^10,因此可能的最大异或值为 2^11 - 1。因此不需要再加左移 16位
     * @param max
     * @return
     */
    public static int getMaxXor(int max){
        max |= (max >> 1);
        max |= (max >> 2);
        max |= (max >> 4);
        max |= (max >> 8);
        return max + 1;
    }

    /**
     * 由异或运算特性可知，若要 nums 中每个长度为 k 的连续区间异或和为0，则需要同时满足以下两点：<BR/>
     *      1. 0...k-1 异或和为0<BR/>
     *      2. k...2k-1, 2k...3k-1 等区域内的每个数字都要与 0...k-1中的数字一一对应，且相对位置也必须相同<BR/>
     * 上面两个条件换种描述就是：<BR/>
     *      1. 对于nums中的任意两个位置 i,j，如果 i%k == j%k，那么 nums[i] == nums[j]必须要成立<BR/>
     *      2. 0...k-1 这段区间内元素异或和必须为0<BR/>
     * 例：k = 3时，不管 nums如何调整，最终呈现出来的形式一定是：ABC ABC ABC ABC AB...，且 A^B^C = 0;<BR/>
     *    k = 4时，不管 nums如何调整，最终呈现出来的形式一定是：ABCD ABCD ABCD ABCD A...，且 A^B^C^D = 0;<BR/>
     *
     * 因此对于题目中所求的最少修改元素个数，可以替换成：<BR/>
     *      将数组中的数按 下标 %k 分成k组，每个小组中最多只能保留一种数的时候，怎样取舍使得整体保留下来的元素个数更多<BR/>
     *      也就是将问题换成了 二维的背包问题<BR/>
     *
     * 特别备注：因为算法过程中有可能会舍弃掉 最后一组的所有数，因此我们必须要保证：<BR/>
     *      找到每组中词频最高的数对应的词频，如果这个词频在组间比较结果最小，那我们必须保证它出现在最后一组<BR/>
     *     例：K组中，每组词频最高数的词频 如果是[2,4,6,12,1,10,3],<BR/>
     *             可见倒数第三组中的数最多的也就出现过1次，因此我们必须保证倒数第三组被交换到最后一组去<BR/>
     * @param nums
     * @param k
     * @return
     */
    public static int compare(int[] nums, int k){
        HashMap<Integer, Integer>[] kList = new HashMap[k];
        for(int i = 0; i < nums.length; i++){
            int index = i % k;
            if(kList[index] == null){
                kList[index] = new HashMap<>();
            }
            kList[index].put(nums[i], kList[index].getOrDefault(nums[i], 0) + 1);
        }
        //找到词频最低的数所在的组号,记录每组照里面词频最高的
        int[] maxIndex = new int[k];
        for(int i = 0; i < kList.length; i++){
            for(Map.Entry<Integer, Integer> entry : kList[i].entrySet()){
                maxIndex[i] = Math.max(maxIndex[i], entry.getValue());
            }
        }
        //在最高的里面挑出最低的
        int minIndex = 0;
        int min = maxIndex[0];
        for(int i = 1; i < k; i++){
            if(maxIndex[i] < min){
                min = maxIndex[i];
                minIndex = i;
            }
        }
        //人为将最高词频最小的那一组交换到最后一组
        HashMap<Integer, Integer> tmp = kList[minIndex];
        if(minIndex < k - 1) {
            kList[minIndex] = kList[k - 1];
            kList[k - 1] = tmp;
        }
        int maxSaved = process(kList, 0, 0, 0);
        return nums.length - maxSaved;
    }

    public static int process(HashMap<Integer, Integer>[] kList, int index, int preXor, int saved){
        if(index == kList.length - 1){
            // 如果当前 最后一组的数中含有preXor，则说明本次选的k种数异或和能凑出0，那么返回总数
            // 否则说明在本次方案中处在最后一组位置的数都要被替换，也就是说保留下来的元素个数为 saved
            if(kList[index].containsKey(preXor)){
                return saved + kList[index].get(preXor);
            } else {
                return saved;
            }
        }
        //当前组号的数一个都不取
        int curSaved = process(kList, index + 1, preXor, saved);
        //当前组号的数取其中一种
        for(Map.Entry<Integer, Integer> entry : kList[index].entrySet()){
            curSaved = Math.max(curSaved,
                    process(kList, index + 1, preXor ^ entry.getKey(), saved + entry.getValue()));
        }
        return curSaved;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 20);
        }
        return nums;
    }

    public static int[] copyOfNums(int[] nums){
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = nums[i];
        }
        return res;
    }
}
