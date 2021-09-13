package com.mjscode.algorithms.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * //你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * //
 * // 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
 * //示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * //
 * // 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * //
 * // 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * //
 * // 示例 1：
 * //
 * //输入：numCourses = 2, prerequisites = [[1,0]]
 * //输出：true
 * //解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * //
 * // 示例 2：
 * //
 * //输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * //输出：false
 * //解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * //
 * // 提示：
 * //
 * // 1 <= numCourses <= 105
 * // 0 <= prerequisites.length <= 5000
 * // prerequisites[i].length == 2
 * // 0 <= ai, bi < numCourses
 * // prerequisites[i] 中的所有课程对 互不相同
 * //
 * // Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
 * @author binarySigh
 * @date 2021/9/13 20:52
 */
public class LC0207_CanFinish {

    public static void main(String[] args) {
        // --> true
        /*int num = 2;
        int[][] pre = {
            {1,0}
        };*/

        int num = 2;
        int[][] pre = {
                {1,0},
                {0,1}
        };

        System.out.println(canFinish(num, pre));
    }

    /**
     * 解答成功:
     * 		执行耗时:8 ms,击败了22.7% 的Java用户
     * 		内存消耗:39.1 MB,击败了51.5% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites){
        // 简单图结构 set[i][j]:
        // -> i : 对应课程编号。0 ~ numCourses - 1
        // -> j : 对应课程的入度出度记录表。0-入度记录表；1-出度记录表
        HashSet<Integer>[][] set = new HashSet[numCourses][2];
        //初始化
        for(int i = 0; i < numCourses; i++){
            set[i][0] = new HashSet<>();
            set[i][1] = new HashSet<>();
        }
        //补全图关系
        for(int i = 0; i < prerequisites.length; i++){
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            set[cur][0].add(pre);
            set[pre][1].add(cur);
        }
        //拓补排序
        Stack<Integer> stack = new Stack<>();
        //寻找初始入度为0的节点入栈
        for(int i = 0; i < numCourses; i++){
            if(set[i][0].size() == 0){
                stack.add(i);
            }
        }
        while(!stack.isEmpty()){
            numCourses--;
            int curIdx = stack.pop();
            Iterator<Integer> ite = set[curIdx][1].iterator();
            while(ite.hasNext()){
                int idx = (int)ite.next();
                set[idx][0].remove(curIdx);
                if(set[idx][0].size() == 0){
                    stack.push(idx);
                }
            }
        }
        return numCourses == 0;
    }

}
