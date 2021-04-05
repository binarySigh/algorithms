package com.mjscode.algorithms.leetcode;

/**
 * //森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * //
 * // 返回森林中兔子的最少数量。
 * //
 * //示例:
 * //输入: answers = [1, 1, 2]
 * //输出: 5
 * //解释:
 * //两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * //之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * //设回答了 "2" 的兔子为蓝色。
 * //此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * //因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 * //
 * //输入: answers = [10, 10, 10]
 * //输出: 11
 * //
 * //输入: answers = []
 * //输出: 0
 * //
 * // 说明:
 * //
 * // answers 的长度最大为1000。
 * // answers[i] 是在 [0, 999] 范围内的整数。
 * //
 * // Related Topics 哈希表 数学
 * @author binarySigh
 * @date 2021/4/4 1:02
 */
public class LC0781_NumRabbits {
    public static void main(String[] args){
        //int[] answers = {1, 1, 2};
        int[] answers = {1, 1, 1, 2, 2, 2, 4};
        System.out.println(numRabbits(answers));
    }

    /**
     * 思路：如果一只兔子说还有 2只与自己颜色相同，那么至少还会有另外两只给的结果也会是 2<BR/>
     *          也就是说，如果数组中第一次出现了 2，那么一定会至少有 2 + 1只兔子是同色<BR/>
     *          因此想要是总体兔子数量最少，那么必须假设后面紧接着 说2的两只兔子与第一只说2的同色。<BR/>
     *       也就是类似消消乐，第一次出现某个数字N之后，将它计数为N<BR/>
     *          后面每出现相同的数字就将它的计数-1，减至0时统计它的数量为 N+1<BR/>
     *          最后再遍历一遍辅助统计数组，将其中不为0的下标对应的数量也统计上<BR/>
     * 优化：本题通用解法是哈希表，但由于本题数据规模特殊，最大answer只有999，因此可用数组替代哈希表<BR/>
     *      优化后空间复杂度可视为 O1；并且省去大量hash时间，并且数组结构可有效利用缓存行进一步优化常数时间<BR/>
     */
    /**
     * 执行结果： 通过<BR/>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 96.97% 的用户<BR/>
     * 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 45.83% 的用户<BR/>
     * @param answers
     * @return
     */
    public static int numRabbits(int[] answers) {
        if(answers == null || answers.length < 1){
            return 0;
        }
        //num下标是兔子回答的数字，之后每有一只回答相同的兔子就将对应的计数-1，直至减至0时统计它的数目
        //num[0]特殊含义，用于统计最终答案。
        // 原数组回答是允许存在0的，因此原本0位置是有意义的，但是回答0的兔子表示自己颜色独立，
        // 因此可以直接计数，没必要放到num[0]中，所以将num[0]独立出来用作全文计数
        int[] num = new int[1000];
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == 0){
                num[0]++;
            } else {
                int ans = answers[i];
                if(num[ans] == 0){
                    num[ans] = ans;
                } else {
                    if(num[ans] == 1){
                        num[0] += (ans + 1);
                    }
                    num[ans]--;
                }
            }
        }
        for(int i = 1; i < 1000; i++){
            if(num[i] > 0){
                num[0] += (i + 1);
            }
        }
        return num[0];
    }
}
