package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * //给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * //
 * // 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * //
 * // 返回获得利润的最大值。
 * //
 * // 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * //
 * // 示例 1:
 * //
 * // 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * //输出: 8
 * //解释: 能够达到的最大利润:
 * //在此处买入 prices[0] = 1
 * //在此处卖出 prices[3] = 8
 * //在此处买入 prices[4] = 4
 * //在此处卖出 prices[5] = 9
 * //总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * //
 * // 注意:
 * //
 * // 0 < prices.length <= 50000.
 * // 0 < prices[i] < 50000.
 * // 0 <= fee < 50000.
 * //
 * // Related Topics 贪心算法 数组 动态规划
 * @author binarySigh
 */
public class LC0714_MaxProfit {

    public static void main(String[] args){
        /*//int[] prices = {1,2,4,2,5,7,2,4,9,0};  //123失败案例 13
        //int[] prices = {1,2,3,4,5}; //4
        //int[] prices = {3,2,6,5,0,3}; //7
        //int[] prices = {14,9,10,12,4,8,1,16}; //19
        //int[] prices = {1,1,2,2,1,1,3,3,2,4,6,3,10,5,3}; //12
        //int[] prices = {1,3,5,4,3,7,6,9,2,4}; //10
        //int[] prices = {0,3,8,6,8,6,6,8,2,0,2,7}; //15
        //int[] prices = {8,6,4,3,3,2,3,5,8,3,8,2,6}; // 11: 2~8 + 3~8
        //int[] prices = {5,2,3,0,3,5,6,8,1,5}; // 12
        //int[] prices = {2,6,8,7,8,7,9,4,1,2,4,5,8}; // 14
        //int[] prices = {5,5,4,9,3,8,5,5,1,6,8,3,4}; //12
        //int[] prices = {2,9,2,3,8,1,5,8,4,3,6,4,4}; //14
        int[] prices = {3,4,6,0,3,7,5,8,2,9,1,6,6,2};
        int fee = 2;
        ArrayUtils.showArray(prices);
        System.out.println(maxProfit123(prices));*/

        System.out.println("--------lc123 测试开始----------");
        for(int i = 0; i < 50_0000; i++){
            int[] prices = ArrayUtils.generateArray(10000, 20, false);
            int maxProfitPro = maxProfit123Pro(prices);
            int maxProfit = maxProfit123(prices);
            if(maxProfit != maxProfitPro){
                System.out.println("-----第[" + i + "]次测试结果出错-------");
                ArrayUtils.showArray(prices);
                System.out.println("测试结果：" + maxProfitPro);
                System.out.println("期望结果：" + maxProfit);
                break;
            }
        }
        System.out.println("--------lc123 测试结束----------");

        /*int[] prices = {15,15,18,11,2,16,13,15,6,11};
        ArrayUtils.showArray(prices);
        System.out.println(maxProfit123Pro(prices));*/
    }

    /**
     * LC0121解法，求只能买卖一次的情况下的最大利润
     *      leetcode Accept
     * 解答成功:
     * 		执行耗时:1 ms,击败了98.21% 的Java用户
     * 		内存消耗:38.4 MB,击败了45.25% 的Java用户
     * @param prices
     * @return
     */
    public static int maxProfit121(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        if(prices.length == 2){
            return prices[1] - prices[0] >= 0 ? prices[1] - prices[0] : 0;
        }
        int max = prices[0];
        int min = prices[0];
        int maxProfit = -1;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= max){
                max = prices[i];
            }
            if(prices[i] < min){
                maxProfit = Math.max(maxProfit, max - min);
                max = prices[i];
                min = prices[i];
            }
        }
        maxProfit = Math.max(maxProfit, max - min);
        return maxProfit;
    }

    /**
     * LC714解法,leetcode Accept
     * 解答成功:
     * 		执行耗时:5 ms,击败了65.71% 的Java用户
     * 		内存消耗:47.1 MB,击败了99.20% 的Java用户
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit714(int[] prices, int fee) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        if(prices.length == 2){
            return prices[1] - prices[0] >= fee ? prices[1] - prices[0] - fee : 0;
        }
        int maxProfit = 0;
        int max = prices[0];
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= max){
                max = prices[i];
            }
            if(prices[i] < min){
                maxProfit += max - min <= fee ? 0 : max - min - fee;
                max = prices[i];
                min = prices[i];
            }
            if(max - prices[i] >= fee){
                maxProfit += max - min <= fee ? 0 : max - min - fee;
                max = prices[i];
                min = prices[i];
            }
        }
        maxProfit += max - min <= fee ? 0 : max - min - fee;
        return maxProfit;
    }

    /**
     * leetcode 122 ,求最大利润，可以买卖多次
     *      leetcode Accept
     *      解答成功:
     * 		执行耗时:2 ms,击败了34.82% 的Java用户
     * 		内存消耗:38.5 MB,击败了24.32% 的Java用户
     * @param prices
     * @return
     */
    public static int maxProfit122(int[] prices) {
        int maxProfit = 0;
        int max = prices[0];
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= max){
                max = prices[i];
            } else if(prices[i] < min){
                maxProfit += max - min;
                max = prices[i];
                min = prices[i];
            } else if(prices[i] < prices[i - 1]){
                maxProfit += max - min;
                max = prices[i];
                min = prices[i];
            }
        }
        maxProfit += max - min;
        return maxProfit;
    }

    /**
     * leetcode 123 最多允许进行两次交易，求最大利润。原题分类：HARD
     *
     * 方法没问题，只是提交会超时。需要基于该方案进行优化
     * 数据规模： 1 <= prices.length <= 10^5
     *           0 <= prices[i] <= 10^5
     * @param prices
     * @return
     */
    public static int maxProfit123(int[] prices){
        if(prices == null || prices.length <= 1){
            return 0;
        }
        if(prices.length == 2){
            return prices[1] < prices[0] ? 0 : prices[1] - prices[0];
        }
        int max = 0;
        int min = 0;
        Integer[] tmp = new Integer[2];
        //在已经记录的利润段中，利润最小的那一段在list中的下标
        int minIndex = -1;
        //在已经记录的利润段中，如果要减掉一位的话一定会损失的最小利润
        int profit = Integer.MAX_VALUE;
        //最小损失利润对应的方案是否需要与 minIndex 位置的下一段合并
        boolean isMerge = false;
        int tmpLoss = 0;
        ArrayList<Integer[]> list = new ArrayList<>();
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[max]){
                max = i;
            }
            if(prices[i] < prices[min] || prices[i] < prices[i - 1] || i == prices.length - 1){
                tmp[0] = min;
                tmp[1] = max;
                if(prices[max] - prices[min] > 0) {
                    list.add(tmp);
                    //每加完一次，就需要new一个新的tmp,防止重复
                    tmp = new Integer[2];
                }
                max = i;
                min = i;
            }
        }
        while(list.size() > 2){
             //循环找到最小那一段，看它能不能跟下一段合并，能就合并，不能就直接舍弃
            for(int i = 0; i < list.size(); i++){
                if(i < list.size() - 1){
                    //先看如果合并，将会损失多少利润
                    tmpLoss = (prices[list.get(i)[1]] - prices[list.get(i)[0]]) + (prices[list.get(i + 1)[1]] - prices[list.get(i + 1)[0]])
                                - (prices[list.get(i + 1)[1]] - prices[list.get(i)[0]]);
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = true;
                    }
                    //再看当前段如果不合并直接删掉会损失多少利润
                    tmpLoss = prices[list.get(i)[1]] - prices[list.get(i)[0]];
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = false;
                    }
                } else {
                    //当前是最后一段，看直接删除当前是不是最小损失方案
                    tmpLoss = prices[list.get(i)[1]] - prices[list.get(i)[0]];
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = false;
                    }
                }
            }
            //循环结束，针对找到的最小那段做相应策略
             if(isMerge){
                list.get(minIndex)[1] = list.get(minIndex + 1)[1];
                list.remove(minIndex + 1);
             } else {
                 list.remove(minIndex);
             }
            //每轮筛选结束记录要复位
            profit = Integer.MAX_VALUE;
            minIndex = 0;
            isMerge = false;
        }
        //这里profit已经不代表最小利润了，只是为了变量复用
        profit = 0;
        for(int i = 0; i < list.size(); i++){
            profit += (prices[list.get(i)[1]] - prices[list.get(i)[0]]);
        }
        return profit;
    }

    /**
     * maxProfit123()第一次改进版：<BR/>
     * 对数器发现的出错用例如下：<BR/>
     *      [8,18,9,15,19,1,9,2,5,14]<BR/>
     *      测试结果：23<BR/>
     *      期望结果：24<BR/>
     * 该用例证明每次遍历中的局部最小，无法推出全局最小损失方案，也就是说本方法的优化方向有误<BR/>
     *  错误原因：<BR/>
     *      对于已生成记录的[8,18],[9,19]而言，第三条[1,9]是无法插入的，因此被直接舍弃了；<BR/>
     *      第四条[2,14]到来时可以插入，因此合并了前两条，插入了[2,14]。<BR/>
     *      而事实上如果将之前被直接淘汰的[1,9]跟当前的[2,14]合并之后再操作，将会得到真正的全局最大利润方案。<BR/>
     *  根本原因：被直接淘汰掉的段可能对后面的结果产生影响，因此需要额外留下记录<BR/>
     *  针对性优化：引入数组 passRec 用于记录被直接淘汰掉的利润段,具体含义及操作方式见代码<BR/>
     *
     *  leetcode Accept<BR/>
     *      解答成功:<BR/>
     * 		执行耗时:8 ms,击败了50.67% 的Java用户<BR/>
     * 		内存消耗:54.1 MB,击败了67.19% 的Java用户<BR/>
     * @param prices
     * @return
     */
    public static int maxProfit123Pro(int[] prices){
        if(prices == null || prices.length <= 1){
            return 0;
        }
        if(prices.length == 2){
            return prices[1] < prices[0] ? 0 : prices[1] - prices[0];
        }
        int max = 0;
        int min = 0;
        //profits[i][j],profits[i][k]表示profits中i位置上的利润段在prices数组中起始下标是j,结束下标是k
        int[][] profits = new int[2][2];
        //表示如果要从当前利润段中 去除/合并 掉一个的话，一定会损失的最小利润是多少
        int minLossProfit = Integer.MAX_VALUE;
        //表示损失的最小利润对应的方案到底是与下一段合并，还是要删除本段。true-合并；false-删除
        boolean isMerge = false;
        //最小利润方案中需要调整的下标（在profits中的下标）
        int minLossIndex = 0;
        //记录临时损失的变量
        int tmpLoss = 0;
        //profits中记录的两段利润段里，靠后的一段的下标位置
        int largerIndx = 0;
        //记录淘汰段起讫位置的数组,这里的淘汰段指的是直接被忽略的当前段，而不包含从profits中剔除的那些记录
        int[] passRec = {-1, -1};
        //遍历prices，处理数据
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[max]){
                max = i;
            }
            if(prices[i] < prices[min] || prices[i] < prices[i - 1] || i == prices.length - 1){
                if(prices[max] - prices[min] > 0) {
                    if(profits[0][1] == 0){
                        //如果是第一个加入profit中的，那么就直接加入
                        profits[0][0] = min;
                        profits[0][1] = max;
                        minLossProfit = prices[max] - prices[min];
                    } else if(profits[1][0] == 0){
                        //如果是第二个加入profit中的，那么就直接加入，并且更新最小损失相关记录
                        profits[1][0] = min;
                        profits[1][1] = max;
                        largerIndx = 1;
                        //如果将两段合并，将承受的利润损失
                        tmpLoss = (prices[max] - prices[min]) +
                                  (prices[profits[0][1]] - prices[profits[0][0]]) -
                                  (prices[max] - prices[profits[0][0]]);
                        //如果合并能使损失利润更小化，那就记录合并的方案
                        if(tmpLoss < minLossProfit){
                            minLossProfit = tmpLoss;
                            isMerge = true;
                        }
                        //如果单纯去掉当前这一段，将承受的利润损失
                        tmpLoss = prices[max] - prices[min];
                        //如果利润损失更小，就纪录该方案
                        if(tmpLoss < minLossProfit){
                            minLossProfit = tmpLoss;
                            isMerge = false;
                            minLossIndex = 1;
                        }
                    } else {
                        //先查看淘汰段中是否有有效数据
                        if(passRec[0] > 0){
                            //如果淘汰段跟当前段合并之后能 分别 ≥ 当前段/淘汰段 的利润值，那么就将淘汰段合并进当前段,并清空淘汰段的记录
                            //这里tmpLoss变量复用，此处的含义是 将 淘汰段跟当前段合并之后的利润大小
                            tmpLoss = prices[max] - prices[passRec[0]];
                            if(tmpLoss >= prices[max] - prices[min] &&
                                    tmpLoss >= prices[passRec[1]] - prices[passRec[0]]){
                                //将淘汰段 合并进 当前段
                                min = passRec[0];
                                //清空淘汰段记录
                                passRec[0] = -1;
                                passRec[1] = -1;
                            }
                        }
                        //先看看将当前段跟已有的尾段合并，所承受的利润损失
                        tmpLoss = (prices[max] - prices[min]) +
                                (prices[profits[largerIndx][1]] - prices[profits[largerIndx][0]]) -
                                (prices[max] - prices[profits[largerIndx][0]]);
                        //这里加上 tmpLoss < prices[max] - prices[min]，是因为此处代码先比较了合并的方案，有可能会对原有的利润方案产生修改，
                        //  而如果修改完又发现直接舍弃是更好的方案，在代码里就直接continue进入下一次大循环了，那么这个错误的修改就没有办法被修复，会对后面的流程产生很大影响
                        // 所以这里只有当 合并的损失 < 原有的损失 且 合并的损失 < 直接舍弃当前段的损失 的时候，才对原纪录做修改，并继续后续流程
                        if(tmpLoss < minLossProfit && tmpLoss < prices[max] - prices[min]){
                            //如果合并尾段和当前段是最小损失方案，那么就记录下这个方案
                            minLossProfit = tmpLoss;
                            isMerge = true;
                            minLossIndex = largerIndx;
                        }
                        //看看单独舍弃当前段，是否能成为最小损失方案
                        tmpLoss = prices[max] - prices[min];
                        if(tmpLoss < minLossProfit){
                            //舍弃之前需要将当前段记录到淘汰段记录中
                            if(passRec[0] > 0){
                                //如果代码走到这里passRec中还有记录，那说明之前有淘汰段，且无法与当前段合并
                                // 那么就看这两段 起始位置在prices[]中对应的金额，谁小留谁
                                // 也就是看这两段的买入金额，留下买入金额小的那一段
                                if(prices[min] < prices[passRec[0]]){
                                    passRec[1] = max;
                                    passRec[0] = min;
                                }
                            } else {
                                //走到这里说明之前没有淘汰段记录（要么被清空了，要么被合进当前段了，要么前面整个流程里都没有产生过淘汰段）
                                // 不管哪种都直接更新成当前这一段就行
                                passRec[1] = max;
                                passRec[0] = min;
                            }
                            //如果直接舍弃当前段是最小损失方案，那么就直接舍弃，直接continue
                            // 但是continue之前一定要在这里更新 min和max的位置，因为continue之后就不会走到循环体尾端自动更新了，只能在这里手动更新
                            max = i;
                            min = i;
                            continue;
                        }
                        //根据最小利润损失方案的记录，执行相关调整
                        if(minLossIndex == largerIndx){
                            //需要调整的那一段恰好是最靠近当前段的那一段（也就是prices中位置靠后的那一段）
                            if(isMerge){
                                //最小损失方案是合并，也就是将靠后段与当前段合并
                                profits[largerIndx][1] = max;
                            } else {
                                //最小损失方案是舍弃靠后段
                                profits[largerIndx][0] = min;
                                profits[largerIndx][1] = max;
                            }
                        } else {
                            //需要调整的那一段是靠前那一段
                            if(isMerge){
                                //最小损失方案是合并原有的两段，然后把当前段加进去
                                //合并前两段
                                profits[minLossIndex][1] = profits[largerIndx][1];
                                //加入当前这一段
                                profits[largerIndx][0] = min;
                                profits[largerIndx][1] = max;
                            } else {
                                //最小损失方案是舍弃靠前那一段，然后加进当前段
                                //舍弃前一段,即 将前一段的记录替换为当前段的起讫下标
                                profits[minLossIndex][0] = min;
                                profits[minLossIndex][1] = max;
                                //同时不能忘记变更largerIndx的指标，因为新加进来的这一段已经是新的靠后段了
                                largerIndx = minLossIndex;
                            }
                        }
                        //代码走到这里说明当前段一定已经被写入profits中了，那么之前的淘汰段记录中不管是否有有效值，此时都应该被清空
                        passRec[0] = -1;
                        passRec[1] = -1;
                        //执行完所有调整后，重新计算好当前 profits 数组中的最小损失方案记录
                        //先记录舍弃靠前那一段的损失方案
                        minLossProfit = prices[profits[1 - largerIndx][1]] - prices[profits[1 - largerIndx][0]];
                        isMerge = false;
                        minLossIndex = 1 - largerIndx;
                        //计算将两段合并的损失方案
                        tmpLoss = (prices[profits[1 - largerIndx][1]] - prices[profits[1 - largerIndx][0]]) +
                                (prices[profits[largerIndx][1]] - prices[profits[largerIndx][0]]) -
                                (prices[profits[largerIndx][1]] - prices[profits[1 - largerIndx][0]]);
                        if(tmpLoss < minLossProfit){
                            //如果真的更小就更新记录
                            minLossProfit = tmpLoss;
                            isMerge = true;
                        }
                        //计算直接舍弃靠后一段的损失方案
                        tmpLoss = prices[profits[largerIndx][1]] - prices[profits[largerIndx][0]];
                        if(tmpLoss < minLossProfit){
                            //如果真的更小就更新记录
                            minLossProfit = tmpLoss;
                            minLossIndex = largerIndx;
                            isMerge = false;
                        }
                    }
                }
                max = i;
                min = i;
            }
        }
        //此处 minLossProfit 也是变量复用
        minLossProfit = prices[profits[0][1]] - prices[profits[0][0]] +
                        prices[profits[1][1]] - prices[profits[1][0]];
        return minLossProfit;
    }

    /**
     * leetcode 188要求：最多完成 K 笔交易，求最大利润
     * 数据规模：0 <= k <= 100
     *          0 <= prices.length <= 1000
     *          0 <= prices[i] <= 1000
     * 代码完全复用 上面的 maxProfit123，leetcode Accept
     * 解答成功:
     * 		执行耗时:30 ms,击败了8.33% 的Java用户
     * 		内存消耗:36.6 MB,击败了65.32% 的Java用户
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit188(int k, int[] prices){
        if(prices == null || prices.length <= 1 || k < 1){
            return 0;
        }
        if(prices.length == 2){
            return prices[1] < prices[0] ? 0 : prices[1] - prices[0];
        }
        int max = 0;
        int min = 0;
        Integer[] tmp = new Integer[2];
        //在已经记录的利润段中，利润最小的那一段在list中的下标
        int minIndex = -1;
        //在已经记录的利润段中，如果要减掉一位的话一定会损失的最小利润
        int profit = Integer.MAX_VALUE;
        //最小损失利润对应的方案是否需要与 minIndex 位置的下一段合并
        boolean isMerge = false;
        int tmpLoss = 0;
        ArrayList<Integer[]> list = new ArrayList<>();
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[max]){
                max = i;
            }
            if(prices[i] < prices[min] || prices[i] < prices[i - 1] || i == prices.length - 1){
                tmp[0] = min;
                tmp[1] = max;
                if(prices[max] - prices[min] > 0) {
                    list.add(tmp);
                    //每加完一次，就需要new一个新的tmp,防止重复
                    tmp = new Integer[2];
                }
                max = i;
                min = i;
            }
        }
        while(list.size() > k){
            //循环找到最小那一段，看它能不能跟下一段合并，能就合并，不能就直接舍弃
            for(int i = 0; i < list.size(); i++){
                if(i < list.size() - 1){
                    //先看如果合并，将会损失多少利润
                    tmpLoss = (prices[list.get(i)[1]] - prices[list.get(i)[0]]) + (prices[list.get(i + 1)[1]] - prices[list.get(i + 1)[0]])
                            - (prices[list.get(i + 1)[1]] - prices[list.get(i)[0]]);
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = true;
                    }
                    //再看当前段如果不合并直接删掉会损失多少利润
                    tmpLoss = prices[list.get(i)[1]] - prices[list.get(i)[0]];
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = false;
                    }
                } else {
                    //当前是最后一段，看直接删除当前是不是最小损失方案
                    tmpLoss = prices[list.get(i)[1]] - prices[list.get(i)[0]];
                    if(tmpLoss < profit){
                        profit = tmpLoss;
                        minIndex = i;
                        isMerge = false;
                    }
                }
            }
            //循环结束，针对找到的最小那段做相应策略
            if(isMerge){
                list.get(minIndex)[1] = list.get(minIndex + 1)[1];
                list.remove(minIndex + 1);
            } else {
                list.remove(minIndex);
            }
            //每轮筛选结束记录要复位
            profit = Integer.MAX_VALUE;
            minIndex = 0;
            isMerge = false;
        }
        //这里profit已经不代表最小利润了，只是为了变量复用
        profit = 0;
        for(int i = 0; i < list.size(); i++){
            profit += (prices[list.get(i)[1]] - prices[list.get(i)[0]]);
        }
        return profit;
    }


}
