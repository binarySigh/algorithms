package com.mjscode.algorithms.leetcode;

/**
 * //老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * //
 * // 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * //
 * // 每个孩子至少分配到 1 个糖果。
 * // 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * //
 * // 那么这样下来，老师至少需要准备多少颗糖果呢？
 * //
 * // 示例 1：
 * //
 * //输入：[1,0,2]
 * //输出：5
 * //解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * //
 * // 示例 2：
 * //
 * //输入：[1,2,2]
 * //输出：4
 * //解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * //     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * // Related Topics 贪心算法
 * @author binarySigh
 * @date 2021/4/23 22:00
 */
public class LC0135_Candy {

    public static void main(String[] args){
        // 23
        int[] ratings = {1,0,2,2,3,5,4,3,2,1};
        // -> 11
        //int[] ratings = {1,3,4,5,2};
        System.out.println(candy(ratings));
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了63.44% 的Java用户
     * 		内存消耗:39.6 MB,击败了51.35% 的Java用户
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        if(ratings.length == 1){
            return 1;
        }
        int total = 0;
        int preCandy = 1;
        for(int i = 0; i < ratings.length; ){
            if(i == ratings.length - 1){
                total += ratings[i] > ratings[i - 1] ? preCandy : 1;
                break;
            }
            int curIndex = i;
            if(ratings[i] > ratings[i + 1]){
                while(curIndex + 1 < ratings.length && ratings[curIndex] > ratings[curIndex + 1]){
                    curIndex++;
                }
                preCandy = Math.max(preCandy, 1 + (curIndex - i));
                //total += ((preCandy + 2) * (curIndex - i) / 2);
                total += preCandy;
                total += (2 + (curIndex - i)) * (curIndex - i - 1) / 2;
                preCandy = 1;
            } else if(ratings[i] < ratings[i + 1]){
                while(curIndex + 1 < ratings.length && ratings[curIndex] < ratings[curIndex + 1]){
                    curIndex++;
                }
                preCandy = 1 + curIndex - i;
                total += (preCandy * (curIndex - i) / 2);
            } else {
                while(curIndex + 1 < ratings.length && ratings[curIndex] == ratings[curIndex + 1]){
                    curIndex++;
                }
                total += (preCandy + curIndex - i - 1);
                preCandy = 1;
            }
            i = curIndex;
        }
        return total;
    }
}
