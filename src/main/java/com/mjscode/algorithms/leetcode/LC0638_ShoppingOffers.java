package com.mjscode.algorithms.leetcode;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * //在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * //
 * // 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[
 * //i] 是需要购买第 i 件物品的数量。
 * //
 * // 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第
 * //j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
 * //
 * // 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限
 * //次购买。
 * //
 * // 示例 1：
 * //
 * //输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * //输出：14
 * //解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
 * //大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
 * //大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
 * //需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
 * //
 * // 示例 2：
 * //
 * //输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * //输出：11
 * //解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * //可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * //需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * //不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。
 * //
 * // 提示：
 * //
 * // n == price.length
 * // n == needs.length
 * // 1 <= n <= 6
 * // 0 <= price[i] <= 10
 * // 0 <= needs[i] <= 10
 * // 1 <= special.length <= 100
 * // special[i].length == n + 1
 * // 0 <= special[i][j] <= 50
 * //
 * // Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩
 * @author binarySigh
 * @date 2021/10/24 10:26
 */
public class LC0638_ShoppingOffers {
    public static void main(String[] args){
        // --> 14
//        List<Integer> price = Arrays.asList(2,5);
//        List<List<Integer>> special = new ArrayList<>();
//        special.add(Arrays.asList(3,0,5));
//        special.add(Arrays.asList(1,2,10));
//        List<Integer> needs = Arrays.asList(3,2);

        // --> 11
        List<Integer> price = Arrays.asList(2,3,4);
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(1,1,0,4));
        special.add(Arrays.asList(2,2,1,9));
        List<Integer> needs = Arrays.asList(1,2,1);


        System.out.println(compare(price, special, needs));
    }

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return 0;
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了96.00% 的Java用户
     * 		内存消耗:37.8 MB,击败了84.29% 的Java用户
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public static int compare(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return process(price, special, needs, 0, 0);
    }

    public static int process(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
                              int idx, int cost) {
        if(idx >= special.size()) {
            for(int i = 0; i < needs.size(); i++) {
                cost += needs.get(i) * price.get(i);
            }
            return cost;
        }
        //当前的礼包如果要买，最多只能买几个（防止某件货物买超）
        int limit = 10;
        List<Integer> tmp = special.get(idx);
        for(int i = 0; i < needs.size(); i++) {
            if(tmp.get(i) == 0) {
                continue;
            }
            limit = Math.min(limit, needs.get(i) / tmp.get(i));
        }
        // 当前大礼包不要
        int c = process(price, special, needs, idx + 1, cost);
        int curCost = tmp.get(needs.size());
        for(int i = 1; i <= limit; i++) {
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) - tmp.get(j) * i);
            }
            c = Math.min(c, process(price, special, needs, idx + 1, cost + i * curCost));
            // 恢复现场
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + tmp.get(j) * i);
            }
        }
        return c;
    }
}
