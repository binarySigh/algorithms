package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * //在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，每个人都有不同数目的钱，以及不同程度的安静（quietness）。
 * //
 * // 为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * //
 * // 如果能够肯定 person x 比 person y 更有钱的话，我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的
 * //一个子集。
 * //
 * // 另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。
 * //
 * // 现在，返回答案 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 person x 的人中，person y 是最安静的人
 * //（也就是安静值 quiet[y] 最小的人）。
 * //
 * // 示例：
 * //
 * // 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1
 * //,7,0]
 * //输出：[5,5,2,5,4,5,6,7]
 * //解释：
 * //answer[0] = 5，
 * //person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * //
 * //唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * //但是目前还不清楚他是否比 person 0 更有钱。
 * //
 * //answer[7] = 7，
 * //在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)，
 * //最安静(有较低安静值 quiet[x])的人是 person 7。
 * //
 * //其他的答案也可以用类似的推理来解释。
 * //
 * // 提示：
 * //
 * // 1 <= quiet.length = N <= 500
 * // 0 <= quiet[i] < N，所有 quiet[i] 都不相同。
 * // 0 <= richer.length <= N * (N-1) / 2
 * // 0 <= richer[i][j] < N
 * // richer[i][0] != richer[i][1]
 * // richer[i] 都是不同的。
 * // 对 richer 的观察在逻辑上是一致的。
 * //
 * // Related Topics 深度优先搜索 图 拓扑排序 数组
 * @author binarySigh
 * @date 2021/9/15 20:45
 */
public class LC0851_LoudAndRich {

    public static void main(String[] args) {
        int[][] ri = {
                {1,0},
                {2,1},
                {3,1},
                {3,7},
                {4,3},
                {5,3},
                {6,3}
        };
        int[] qu = {3,2,5,4,6,1,7,0};

        int[] ans = loudAndRich(ri, qu);
        ArrayUtils.showArray(ans);
    }

    /**
     * 解答成功:
     *		执行耗时:22 ms,击败了16.4% 的Java用户
     * 		内存消耗:48.2 MB,击败了22.6% 的Java用户
     * @param richer
     * @param quiet
     * @return
     */
    public static int[] loudAndRich(int[][] richer, int[] quiet){
        HashSet<Integer>[][] nodes = new HashSet[quiet.length][2];
        for(int i = 0; i < quiet.length; i++){
            nodes[i][0] = new HashSet<>();
            nodes[i][1] = new HashSet<>();
        }
        for(int i = 0; i < richer.length; i++){
            int pre = richer[i][0];
            int cur = richer[i][1];
            nodes[pre][1].add(cur);
            nodes[cur][0].add(pre);
        }
        int[] ans = new int[quiet.length];
        for(int i = 0; i < quiet.length; i++){
            ans[i] = i;
        }
        //拓补排序
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nodes.length; i++){
            if(nodes[i][0].size() == 0){
                stack.push(i);
            }
        }
        while(!stack.isEmpty()){
            int cur = stack.pop();
            Iterator<Integer> ite = nodes[cur][1].iterator();
            while(ite.hasNext()){
                int next = ite.next();
                //由当前的节点给下一级节点设置喧闹值
                if(quiet[ans[next]] > quiet[ans[cur]]){
                    ans[next] = ans[cur];
                }
                //消去影响
                nodes[next][0].remove(cur);
                if(nodes[next][0].size() == 0){
                    stack.push(next);
                }
            }
        }
        return ans;
    }

}
