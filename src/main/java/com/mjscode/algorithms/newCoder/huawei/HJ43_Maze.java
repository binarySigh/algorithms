package com.mjscode.algorithms.newCoder.huawei;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Scanner;

/**
 * 描述
 * 定义一个二维数组N*M（其中2<=N<=10;2<=M<=10），如5 × 5数组下所示：
 *
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 *
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。入口点为[0,0],既第一格是可以走的路。
 *
 * 本题含有多组数据。
 *
 * 输入描述：
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * 输出描述：
 * 左上角到右下角的最短路径，格式如样例所示。
 * @author binarySigh
 * @date 2021/8/27 23:10
 */
public class HJ43_Maze {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String[] s = in.nextLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int[][] maze = new int[m][n];
            for(int i = 0; i < m; i++){
                String[] line = in.nextLine().split(" ");
                for(int j = 0; j < n; j++){
                    maze[i][j] = "1".equals(line[j]) ? -1 : 0;
                }
            }
            String ans = process(maze, m, n);
            System.out.println(ans);
        }
    }

    /**
     * 运行时间：44ms 超过39.18% 用Java提交的代码
     * 占用内存：12460KB 超过3.78%用Java提交的代码
     * @param maze
     * @param m
     * @param n
     * @return
     */
    public static String process(int[][] maze, int m, int n){
        StringBuilder sb = new StringBuilder();
        maze[0][0] = 1;
        dfs(maze, 0, 0, 1);
        int i = m - 1, j = n - 1;
        int pre = maze[i][j];
        //从终点到起点补齐路线
        sb.append("(").append(i).append(",").append(j).append(")");
        while(pre > 0){
            //上路线存在且合格
            if(i - 1 < m && i - 1 >= 0 && j < n && j >= 0 && maze[i - 1][j] == pre - 1){
                sb.insert(0, ")\n");
                sb.insert(0, j);
                sb.insert(0, ',');
                sb.insert(0, --i);
                sb.insert(0, '(');
            }
            //下路线存在且合格
            else if(i + 1 < m && i + 1 >= 0 && j < n && j >= 0 && maze[i + 1][j] == pre - 1){
                sb.insert(0, ")\n");
                sb.insert(0, j);
                sb.insert(0, ',');
                sb.insert(0, ++i);
                sb.insert(0, '(');
            }
            //左路线存在且合格
            else if(i < m && i >= 0 && j - 1 < n && j - 1 >= 0 && maze[i][j - 1] == pre - 1){
                sb.insert(0, ")\n");
                sb.insert(0, --j);
                sb.insert(0, ',');
                sb.insert(0, i);
                sb.insert(0, '(');
            }
            //右路线存在且合格
            else if(i < m && i >= 0 && j + 1 < n && j + 1 >= 0 && maze[i][j + 1] == pre - 1){
                sb.insert(0, ")\n");
                sb.insert(0, ++j);
                sb.insert(0, ',');
                sb.insert(0, i);
                sb.insert(0, '(');
            }
            pre--;
        }
        return sb.toString();
    }

    public static void dfs(int[][] maze, int i, int j, int point){
        if(i < 0 || i >= maze.length ||
            j < 0 || j >= maze[0].length ||
            maze[i][j] == -1 ||
                (maze[i][j] > 0 && maze[i][j] < point)){
            return;
        }
        maze[i][j] = point;
        dfs(maze, i + 1, j, point + 1);
        dfs(maze, i - 1, j, point + 1);
        dfs(maze, i, j + 1, point + 1);
        dfs(maze, i, j - 1, point + 1);
    }
}
