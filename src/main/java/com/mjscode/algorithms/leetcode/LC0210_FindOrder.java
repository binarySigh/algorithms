package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * //现在你总共有 n 门课需要选，记为 0 到 n-1。
 * //
 * // 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * //
 * // 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * //
 * // 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * //
 * // 示例 1:
 * //
 * // 输入: 2, [[1,0]]
 * //输出: [0,1]
 * //解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * //
 * // 示例 2:
 * //
 * // 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * //输出: [0,1,2,3] or [0,2,1,3]
 * //解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * //     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * //
 * // 说明:
 * //
 * // 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * // 你可以假定输入的先决条件中没有重复的边。
 * //
 * // 提示:
 * //
 * // 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * // 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * //
 * // 拓扑排序也可以通过 BFS 完成。
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
 * @author binarySigh
 * @date 2021/9/13 20:49
 */
public class LC0210_FindOrder {

    public static void main(String[] args) {
        // --> [0,1]
        /*int num = 2;
        int[][] pre = {
            {1,0}
        };*/

        int num = 4;
        int[][] pre = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        };

        int[] ans = findOrder(num, pre);
        ArrayUtils.showArray(ans);
    }

    /**
     * 解答成功:
     *		执行耗时:7 ms,击败了33.1% 的Java用户
     * 		内存消耗:38.9 MB,击败了98.2% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites){
        // [i][0] -> 入度集；[i][1] -> 出度集
        HashSet<Integer>[][] node = new HashSet[numCourses][2];
        for(int i = 0; i < numCourses; i++){
            node[i][0] = new HashSet<>();
            node[i][1] = new HashSet<>();
        }
        for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            node[cur][0].add(pre);
            node[pre][1].add(cur);
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < numCourses; i++){
            if(node[i][0].size() == 0){
                stack.push(i);
            }
        }
        int[] ans = new int[numCourses];
        int pos = 0;
        while(!stack.isEmpty()){
            numCourses--;
            int curIdx = stack.pop();
            ans[pos++] = curIdx;
            Iterator<Integer> ite = node[curIdx][1].iterator();
            while(ite.hasNext()){
                int idx = ite.next();
                node[idx][0].remove(curIdx);
                if(node[idx][0].size() == 0){
                    stack.push(idx);
                }
            }
        }
        if(numCourses > 0){
            return new int[]{};
        }
        return ans;
    }

}
