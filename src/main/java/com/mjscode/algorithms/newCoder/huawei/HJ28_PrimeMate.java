package com.mjscode.algorithms.newCoder.huawei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 描述
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的N（N为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
 *
 * 输入:
 *
 * 有一个正偶数N（N≤100），表示待挑选的自然数的个数。后面给出具体的数字，范围为[2,30000]。
 *
 * 输出:
 *
 * 输出一个整数K，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 *
 * 输入描述：
 * 输入说明
 * 1 输入一个正偶数n
 * 2 输入n个整数
 * 注意：数据可能有多组
 *
 * 输出描述：
 * 求得的“最佳方案”组成“素数伴侣”的对数。
 * @author binarySigh
 * @date 2021/8/27 21:43
 */
public class HJ28_PrimeMate {
    public static HashSet<Integer> prime = new HashSet<>();

    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            ArrayList<Integer> even = new ArrayList<>();
            ArrayList<Integer> odd = new ArrayList<>();
            while(n > 0){
                int i = in.nextInt();
                if((1 & i) == 0){
                    even.add(i);
                } else {
                    odd.add(i);
                }
                n--;
            }
            //int max = compare(even, odd);
            int max = match(even, odd);
            System.out.println(max);
        }
    }

    /**
     * 运行时间：69ms 超过32.96% 用Java提交的代码
     * 占用内存：13252KB 超过1.53%用Java提交的代码
     * 匈牙利算法：
     *      1. 先到先得：配对过程中，先到先得，也即两个数本来都没有配对，则认为这两个数配对成功；
     *      2. 能让则让：对于后来的配对者 y，找到了一个已有配对的数 x，但是x的对象可以重新找到一个新对象，则认为 y 可以跟x重新组成一对,
     * @param even
     * @param odd
     * @return
     */
    public static int match(ArrayList<Integer> even, ArrayList<Integer> odd){
        int[] evenMatch =new int[even.size()];
        int result = 0;
        //遍历奇数去匹配偶数
        for(int i = 0; i < odd.size(); i++) {
            //每一步重新创建，也就是相当于清空
            //used数组用于标记某个某数位置是否
            boolean[] used = new boolean[even.size()];
            //这里采用了匈牙利算法，先到先得
            if(find(odd.get(i), even, used, evenMatch)) {
                result++;
            }
        }
        return result;
    }

    public static boolean find(int x, ArrayList<Integer> even, boolean[] used, int[] evenMatch) {
        //遍历偶数
        //去检查当前传入的奇数能否与偶数哪些数匹配
        for(int i = 0; i < even.size(); i++) {
            //如果当前偶数与传入的奇数匹配，并且当前偶数位还没有匹配过奇数
            if(isPrime(x + even.get(i)) && !used[i]) {
                //设置当前偶数位匹配为true，也就是 1
                used[i] = true;
                //如果第i个偶数没有伴侣
                //或者第i个偶数原来有伴侣，并且该伴侣能够重新找到伴侣的话(这里有递归调用)
                //则奇数x可以设置为第i个偶数的伴侣
                //这里采用了匈牙利算法，能让则让
                if(evenMatch[i] == 0 || find(evenMatch[i], even, used, evenMatch)) {
                    evenMatch[i] = x;
                    return true;
                }
            }
        }
        //遍历完偶数都没有可以与传入奇数做伴侣的，该奇数只能孤独终老了
        return false;
    }

    /**
     * 超时
     * @param even
     * @param odd
     * @return
     */
    public static int compare(ArrayList<Integer> even, ArrayList<Integer> odd){
        boolean[] e = new boolean[even.size()];
        boolean[] o = new boolean[odd.size()];
        return f(even, odd, e, o, 0);
    }

    public static int f(ArrayList<Integer> even, ArrayList<Integer> odd, boolean[] e, boolean[] o, int cnts){
        int cur = 0;
        for(int i = 0; i < e.length; i++){
            if(e[i]){
                continue;
            }
            e[i] = true;
            for(int j = 0; j < o.length; j++){
                if(o[j]){
                    continue;
                }
                if(isPrime(even.get(i) + odd.get(j))){
                    o[j] = true;
                    cur = Math.max(cur, f(even, odd, e, o, cnts + 1));
                    o[j] = false;
                }
            }
            e[i] = false;
        }
        return Math.max(cnts, cur);
    }

    public static boolean isPrime(int num){
        if(prime.contains(num)){
            return true;
        }
        int limit = (int)Math.sqrt(num);
        for(int i = 2; i <= limit; i++){
            if(num % i == 0){
                return false;
            }
        }
        prime.add(num);
        return true;
    }
}
