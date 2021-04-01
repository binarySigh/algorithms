package com.mjscode.algorithms.leetcode;

import java.util.Stack;

/**
 * @author binarySigh
 * @date 2021/4/1 23:55
 */
public class LC1006_Clumsy {
    public static void main(String[] args){
        /*int N = 10;
        System.out.println(clumsy2(N));
        System.out.println(clumsy(N));*/
        System.out.println("----------测试开始---------");
        for(int i = 1; i <= 10_000; i++){
            int res = clumsy(i);
            int compare = clumsy2(i);
            if(res != compare){
                System.out.println("第" + i + "]次实验出错！");
                System.out.println("正确答案：" + compare);
                System.out.println("我的答案：" + res);
                break;
            }
        }
        System.out.println("----------测试结束---------");
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了40.09% 的Java用户
     * 		内存消耗:35.2 MB,击败了75.00% 的Java用户
     * @param N
     * @return
     */
    public static int clumsy(int N){
        if(N == 1){
            return 1;
        }
        int res = -1;
        int curRes = N--;
        int op = 1;
        while(N >= 1){
            if(op % 4 == 1){
                curRes *= N--;
            } else if(op % 4 == 2){
                curRes /= N--;
                res = res == -1 ? curRes : res - curRes;
                curRes = 0;
            } else if(op % 4 == 3){
                if(N == 1){
                    return res + N;
                }
                curRes += N--;
            } else {
                res += curRes;
                if(N == 1){
                    return res - N;
                }
                curRes = N--;
            }
            op++;
        }
        res = res == -1 ? curRes : (res - curRes);
        return res;
    }

    /**
     *执行结果：通过
     * 执行用时： 894 ms, 在所有 Java 提交中击败了 5.16% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了 9.03% 的用户
     * @param N
     * @return
     */
    public static int clumsy2(int N) {
        String s = String.valueOf(N);
        int op = 1;
        N--;
        while(N >= 1){
            if(op % 4 == 1){
                s += '*';
            } else if(op % 4 == 2){
                s += '/';
            } else if(op % 4 == 3){
                s += '+';
            } else {
                s += '-';
            }
            s += String.valueOf(N);
            op++;
            N--;
        }
        return LC0227_calculate(s);
    }

    public static int LC0227_calculate(String s) {
        //这里的cur用来记录当前优先级 计算积累下来的商/积
        int cur = 1;
        //当前得到的数字
        int curNum = 0;
        //上一个符号是否为*
        boolean multi = true;
        Stack<Prefix> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                //将前面可能没计算完的乘除计算完
                cur = multi ? cur * curNum : cur / curNum;
                if(!stack.isEmpty()){
                    Prefix prefix = stack.pop();
                    cur = prefix.flag ? prefix.preSum + cur : prefix.preSum - cur;
                }
                stack.push(new Prefix(cur, s.charAt(i) == '+' ? true : false));
                cur = 1;
                curNum = 0;
                multi = true;
            } else if(s.charAt(i) == '*' || s.charAt(i) == '/'){
                cur = multi ? cur * curNum : cur / curNum;
                multi = s.charAt(i) == '*' ? true : false;
                curNum = 0;
            } else if(s.charAt(i) >= 48 && s.charAt(i) <= 57){
                curNum = curNum * 10 + s.charAt(i) - 48;
            }
            if(i == s.length() - 1){
                cur = multi ? cur * curNum : cur / curNum;
                if(!stack.isEmpty()){
                    Prefix prefix = stack.pop();
                    cur = prefix.flag ? prefix.preSum + cur : prefix.preSum - cur;
                }
            }
        }
        return cur;
    }

    public static class Prefix{
        int preSum;
        boolean flag;
        Prefix(int sum, boolean fl){
            this.preSum = sum;
            this.flag = fl;
        }
    }
}
