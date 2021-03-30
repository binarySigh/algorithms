package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 * //
 * //
 * // 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [4,5,6,7,0,1,2], target = 0
 * //输出：4
 * //
 * // 示例 2：
 * //
 * //输入：nums = [4,5,6,7,0,1,2], target = 3
 * //输出：-1
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1], target = 0
 * //输出：-1
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 5000
 * // -10^4 <= nums[i] <= 10^4
 * // nums 中的每个值都 独一无二
 * // nums 肯定会在某个点上旋转
 * // -10^4 <= target <= 10^4
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 */
public class LC0033_Search {

    public static void main(String[] args){
        /*int[] nums = {32,35,40,3,8,11,14,18,22,27};
        int target = 3;
        System.out.println(search(nums, target));*/

        System.out.println("------------测试开始------------");
        for(int i = 0; i < 50_0000; i++){
            int[] nums = getRotateArray(10);
            int target = getNumber(nums, Math.random());
            int myRes = search(nums, target);
            int lcRes = searchByLeetCode(nums, target);
            if(myRes != lcRes){
                System.out.println("第[" + i + "]次测试出错！");
                System.out.println("出错数组为：");
                ArrayUtils.showArray(nums);
                System.out.println("目标值为：" + target);
                System.out.println("我的答案：" + myRes);
                System.out.println("正确答案：" + lcRes);
                break;
            }
        }
        System.out.println("------------测试结束------------");
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:37.7 MB,击败了87.29% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length < 1){
            return -1;
        }
        if(nums.length == 1){
            return nums[0] == target ? 0 : -1;
        }
        int mid = 0;
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            mid = l + ((r - l) >> 1);
            if(target == nums[mid]){
                return mid;
            }
            //if - mid处于左半段；else - mid处于右半段
            if(nums[mid] >= nums[l]){
                if(target <= nums[mid] && target >= nums[l]){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if(target > nums[mid] && target <= nums[r]){
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return target == nums[l] ? l : -1;
    }

    /**
     * 官方的二分解法
     * @param nums
     * @param target
     * @return
     */
    public static int searchByLeetCode(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    //对数器部分
    /**
     * 获取题设的无重复元素的旋转升序数组
     * @param maxLen 最大长度，不能低于5
     * @return
     */
    public static int[] getRotateArray(int maxLen){
        int length = 0;
        do {
            length = (int) (Math.random() * (maxLen + 1));
        } while (length < 5);
        int[] arr = new int[length];
        arr[0] = (int)(Math.random() * 5);
        for(int i = 1; i < length; i++){
            //path保证数组每两个相邻元素之间差>3
            int path = 0;
            do{
                path = (int)(Math.random() * 6);
            } while(path < 3);
            arr[i] = arr[i - 1] + path;
        }
        return rotateArray(arr);
    }

    /**
     * 返回原数组的旋转数组
     * @param arr
     * @return
     */
    public static int[] rotateArray(int[] arr){
        int[] res = new int[arr.length];
        //pos为旋转点下标。do-while保证该下标一定有意义
        int pos = 0;
        do{
            pos = (int)(Math.random() * (arr.length - 1));
        } while(pos < 1);
        //旋转数组
        int i = 0;
        int j = pos;
        while(j < arr.length){
            res[i++] = arr[j++];
        }
        j = 0;
        while(i < arr.length){
            res[i++] = arr[j++];
        }
        return res;
    }

    /**
     * 按照要求返回一个数字
     * @param arr 输入数组
     * @param dou <0.5:返回数组中出现的一个数；>0.5:返回数组中未出现的一个数
     */
    public static int getNumber(int[] arr, double dou){
        //获取随机起始位置,范围：[0, arr.length - 2]
        int start = 0;
        //由于传入数组是一个旋转数组，因此必须用do-while保证生成的随机位置是在两个增序段内部，
        //  而不能正好是交界处，防止下面的else逻辑复杂化
        do{
            start = (int)(Math.random() * (arr.length - 1));
        } while(arr[start] > arr[start + 1]);
        if(dou < 0.5){
            //如果是要返回出现过的数，那么直接返回start位置的数
            return arr[start];
        } else {
            //如果要返回未出现过的数，那么返回arr[start] ~ arr[start+1]位置之间的数
            //随机生成一个 1 ~ arr[start+1]-arr[start] 的随机数
            int ret = 0;
            do{
                ret = (int)(Math.random() * (arr[start + 1] - arr[start]));
            } while(ret < 1);
            //返回 arr[start] + ret 即可保证这个数在数组中从未出现过
            return arr[start] + ret;
        }
    }
}
