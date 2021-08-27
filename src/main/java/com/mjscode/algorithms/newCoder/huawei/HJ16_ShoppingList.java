package com.mjscode.algorithms.newCoder.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述
 * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 *
 * 主件	附件
 * 电脑	打印机，扫描仪
 * 书柜	图书
 * 书桌	台灯，文具
 * 工作椅	无
 *
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。
 * 他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 *     设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
 *      v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 *     请你帮助王强设计一个满足要求的购物单。
 *
 * 输入描述：
 * 输入的第 1 行，为两个正整数，用一个空格隔开：N m
 *
 * （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
 *
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 *
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 *
 * 输出描述：
 *  输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
 * @author binarySigh
 * @date 2021/8/27 21:05
 */
public class HJ16_ShoppingList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int money = sc.nextInt();
            int m = sc.nextInt();
            int[][] goods = new int[m + 1][3];
            for(int i = 1; i <= m; i++){
                goods[i][0] = sc.nextInt();
                goods[i][1] = sc.nextInt();
                goods[i][2] = sc.nextInt();
            }
            System.out.println(compare(goods, money));
            System.out.println(shoppingList(goods, money));
        }

    }

    /**
     * 运行时间：47ms 超过74.46% 用Java提交的代码
     * 占用内存：11808KB 超过65.15%用Java提交的代码
     * @param goods
     * @param money
     * @return
     */
    public static int shoppingList(int[][] goods, int money){
        //将商品分类，主-副-副 分为一类。由于每个主件最多包含两个附件，所以子数组长度为3即可
        int[][] kinds = new int[goods.length][3];
        for(int i = 1; i < goods.length; i++){
            if(goods[i][2] == 0){
                kinds[i][0] = i;
            } else {
                int f = goods[i][2];
                if(kinds[f][1] > 0){
                    kinds[f][2] = i;
                } else {
                    kinds[f][1] = i;
                }
            }
        }
        //dp表,长度为2是因为本题可用滚动数组优化空间
        int[][] dp = new int[money + 1][2];
        int idx = 0;
        for(int j = 0; j < goods.length - 1; j++){
            if(kinds[j + 1][0] == 0){
                continue;
            }
            for(int i = 0; i <= money; i += 10){
                if(i > 0 && dp[i][idx] == 0){
                    continue;
                }
                dp[i][idx ^ 1] = Math.max(dp[i][idx ^ 1], dp[i][idx]);
                //下一层为有效种类。
                int f = kinds[j + 1][0];
                if(i + goods[f][0] > money){
                    continue;
                }
                int fixedCost = i + goods[f][0];
                int fixedGain = dp[i][idx] + goods[f][0] * goods[f][1];
                dp[fixedCost][idx ^ 1] = Math.max(dp[fixedCost][idx ^ 1], fixedGain);
                int cost = 0;
                int gain = 0;
                if(kinds[j + 1][1] > 0 && fixedCost + goods[kinds[j + 1][1]][0] <= money){
                    cost = fixedCost + goods[kinds[j + 1][1]][0];
                    gain = fixedGain + goods[kinds[j + 1][1]][1] * goods[kinds[j + 1][1]][0];
                    dp[cost][idx ^ 1] = Math.max(dp[cost][idx ^ 1], gain);
                }
                if(kinds[j + 1][2] > 0){
                    if(fixedCost + goods[kinds[j + 1][2]][0] <= money){
                        cost = fixedCost + goods[kinds[j + 1][2]][0];
                        gain = fixedGain + goods[kinds[j + 1][2]][1] * goods[kinds[j + 1][2]][0];
                        dp[cost][idx ^ 1] = Math.max(dp[cost][idx ^ 1], gain);
                    }
                    if(fixedCost + goods[kinds[j + 1][2]][0] + goods[kinds[j + 1][1]][0] <= money){
                        cost = fixedCost + goods[kinds[j + 1][2]][0] + goods[kinds[j + 1][1]][0];
                        gain = fixedGain + goods[kinds[j + 1][2]][1] * goods[kinds[j + 1][2]][0] +
                                goods[kinds[j + 1][1]][1] * goods[kinds[j + 1][1]][0];
                        dp[cost][idx ^ 1] = Math.max(dp[cost][idx ^ 1], gain);
                    }
                }
            }
            idx ^= 1;
        }
        int max = 0;
        for(int i = 0; i <= money; i += 10){
            max = Math.max(max, dp[i][idx]);
        }
        return max;
    }

    public static int shoppingList1(int[][] goods, int money){
        int[] map = new int[goods.length];
        //处理输入数据，将原物件分类，同一类的分到一个List中
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for(int i = 1; i < goods.length; i++){
            if(goods[i][2] == 0){
                //当前没有父物件,只需要添加当前物件
                if(map[i] == 0){
                    ArrayList<Integer> cur = new ArrayList<>();
                    cur.add(i);
                    map[i] = list.size();
                    list.add(cur);
                }
            } else {
                int father = goods[i][2];
                if(map[father] > 0){
                    //当前类的物品的主物件已经添加，将当前商品挂上去即可
                    list.get(map[father]).add(i);
                } else {
                    //当前类的物品的主物件还未添加，先将主物品添加好，保证主物品在每一类的第一个位置
                    ArrayList<Integer> cur = new ArrayList<>();
                    cur.add(father);
                    cur.add(i);
                    map[father] = list.size();
                    list.add(cur);
                }
            }
        }
        return 0;
    }

    public static int compare(int[][] goods, int money){
        boolean[] chosen = new boolean[goods.length];
        return process(goods, money, chosen, 1, 0);
    }

    public static int process(int[][] goods, int money, boolean[] chosen, int idx, int pre){
        if(idx == chosen.length || money == 0){
            return pre;
        }
        if(money <= 0){
            return -1;
        }
        int cur = process(goods, money, chosen, idx + 1, pre);
        if(goods[idx][2] > 0 && !chosen[goods[idx][2]]){
            int curCost = goods[idx][0] + goods[goods[idx][2]][0];
            int curWeight = goods[idx][0] * goods[idx][1] + goods[goods[idx][2]][0] * goods[goods[idx][2]][1];
            if(!chosen[idx] && money >= curCost){
                chosen[idx] = true;
                chosen[goods[idx][2]] = true;
                cur = Math.max(cur, process(goods, money - curCost, chosen, idx + 1, pre + curWeight));
                chosen[idx] = false;
                chosen[goods[idx][2]] = false;
            }
        } else {
            int curCost = goods[idx][0];
            int curWeight = goods[idx][0] * goods[idx][1];
            if(!chosen[idx] && money >= curCost){
                chosen[idx] = true;
                cur = Math.max(cur, process(goods, money - curCost, chosen, idx + 1, pre + curWeight));
                chosen[idx] = false;
            }
        }
        return cur;
    }

}
