package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * //
 * // 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * //
 * // 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * //
 * // 示例 1：
 * //
 * // 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * //输出：15
 * //解释：
 * //船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * //第 1 天：1, 2, 3, 4, 5
 * //第 2 天：6, 7
 * //第 3 天：8
 * //第 4 天：9
 * //第 5 天：10
 * //
 * //请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
 * //0) 是不允许的。
 * //
 * // 示例 2：
 * //
 * // 输入：weights = [3,2,2,4,1,4], D = 3
 * //输出：6
 * //解释：
 * //船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * //第 1 天：3, 2
 * //第 2 天：2, 4
 * //第 3 天：1, 4
 * //
 * // 示例 3：
 * //
 * // 输入：weights = [1,2,3,1,1], D = 4
 * //输出：3
 * //解释：
 * //第 1 天：1
 * //第 2 天：2
 * //第 3 天：3
 * //第 4 天：1, 1
 * //
 * // 提示：
 * //
 * // 1 <= D <= weights.length <= 50000
 * // 1 <= weights[i] <= 500
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/4/26 20:30
 */
public class LC1011_ShipWithinDays {
    public static void main(String[] args){
        /*int[] weights = getArray(50000);
        int D = (int)(Math.random() * (weights.length - 2)) + 1;
        long begin = System.nanoTime();
        int myResult = shipWithinDays(weights, D);
        long end = System.nanoTime();

        long begin2 = System.nanoTime();
        int compare = compare(weights, D);
        long end2 = System.nanoTime();
        System.out.println("我的答案：" + myResult + ", cost: " + String.valueOf(end - begin));
        System.out.println("正确答案：" + compare + ", cost: " + String.valueOf(end2 - begin2));*/

        /*int[] weights = {3,1,5,4,3};
        int D = 2;
        ArrayUtils.showArray(weights);
        System.out.println("限制天数：" + D);
        System.out.println("我的答案：" + shipWithinDays(weights, D));
        System.out.println("正确答案：" + compare(weights, D));*/

        System.out.println("------------测试开始-----------");
        for(int i = 0; i < 100_0000; i++){
            int len = (int)(Math.random() * 200) + 4;
            int[] weights = getArray(len);
            int D = (int)(Math.random() * (len - 2)) + 1;
            int myResult = shipWithinDays(weights, D);
            int compare = compare(weights, D);
            if(myResult != compare){
                System.out.println("--------第[" + i + "]次实验出错！--------");
                ArrayUtils.showArray(weights);
                System.out.println("限制天数：" + D);
                System.out.println("正确答案：" + compare);
                System.out.println("我的答案：" + myResult);
                break;
            }
        }
        System.out.println("------------测试结束-----------");
    }

    /**
     * 解答成功:
     * 		执行耗时:10 ms,击败了89.74% 的Java用户
     * 		内存消耗:40.9 MB,击败了99.52% 的Java用户
     * @param weights
     * @param D
     * @return
     */
    public static int shipWithinDays(int[] weights, int D) {
        if(weights.length == 1){
            return weights[0];
        }
        int maxWeight = weights[0];
        int[] sum = new int[weights.length];
        sum[0] = weights[0];
        for(int i = 1; i < weights.length; i++){
            sum[i] = sum[i - 1] + weights[i];
            maxWeight = Math.max(maxWeight, weights[i]);
        }
        int L = 0;
        int R = weights.length - 1;
        int min = sum[L];
        int curCover = 0;
        int days = 0;
        int flagIndex = weights.length - 1;
        while(L <= R){
            int mid = (L + R) >> 1;
            min = sum[mid];
            curCover = 0;
            days = 1;
            //只有当最小载重有效时才验算天数条件，否则说明载重不够，直接将左边界提升
            // 最小载重有效，是指最小载重应大于等于所需运载的最大货物重量
            if(min >= maxWeight){
                for(int i = mid + 1; i < weights.length; i++){
                    if(weights[i] + curCover > min){
                        days++;
                        curCover = weights[i];
                    } else {
                        curCover += weights[i];
                    }
                }
            } else {
                days = D + 1;
            }
            if(++days > D){
                L = mid + 1;
            } else {
                flagIndex = Math.min(flagIndex, mid);
                R = mid - 1;
            }
        }
        if(flagIndex == 0){
            return sum[0];
        }
        //对于上面找到的 flagIndex,sum[flagIndex]是肯定能满足题意的载重，而sum[flagIndex - 1]是无法满足题意的
        // 但并不等于sum[flagIndex]就一定是答案，正确答案是可能在sum[flagIndex - 1] + 1 ~ sum[flagIndex]之间的
        // 例如 weights = {5,4,4,1,1,1,1}, D = 3这个例子中，正确答案就是 8，既不是9也不是5
        // 因此需要单独对这一区域进行验算找出最小载重。由于这块区域可能会很大，所以这块区域的查找也用二分来完成
        L = sum[flagIndex - 1];
        R = sum[flagIndex];
        min = R;
        curCover = 0;
        days = 0;
        while(L <= R){
            int mid = (L + R) >> 1;
            //只有当最小载重有效时才验算天数条件，否则说明载重不够，直接将左边界提升
            // 最小载重有效，是指最小载重应大于等于所需运载的最大货物重量
            if(mid >= maxWeight) {
                for (int i = 0; i < weights.length; i++) {
                    if (curCover + weights[i] > mid) {
                        days++;
                        curCover = weights[i];
                    } else {
                        curCover += weights[i];
                    }
                }
            } else {
                days = D + 1;
            }
            if(++days > D){
                L = mid + 1;
            } else {
                min = Math.min(min, mid);
                R = mid - 1;
            }
            days = 0;
            curCover = 0;
        }
        return min;
    }

    /**
     * 纯粹暴力解
     * @param weights
     * @param D
     * @return
     */
    public static int compare(int[] weights, int D){
        if(weights.length == 1){
            return weights[0];
        }
        int maxWeight = 0;
        for(int i = 0; i < weights.length; i++){
            maxWeight = Math.max(maxWeight, weights[i]);
        }
        int curCover = 0;
        int days = 0;
        int min = maxWeight;
        while(true){
            for(int i = 0; i < weights.length; i++){
                if(curCover + weights[i] > min){
                    days++;
                    curCover = weights[i];
                } else {
                    curCover += weights[i];
                }
            }
            if(++days > D){
                min++;
                days = 0;
                curCover = 0;
            } else {
                break;
            }
        }
        return min;
    }

    public static int[] getArray(int length){
        int[] arr = new int[length];
        for(int i = 0; i < length; i++){
            arr[i] = (int)(Math.random() * 500) + 1;
        }
        return arr;
    }
}
