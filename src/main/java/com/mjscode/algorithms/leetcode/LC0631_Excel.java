package com.mjscode.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * //你的任务是实现 Excel 的求和功能，具体的操作如下：
 * //
 * // Excel(int H, char W): 这是一个构造函数，输入表明了 Excel 的高度和宽度。H 是一个正整数，范围从 1 到 26，代表高度。W
 * //是一个字符，范围从 'A' 到 'Z'，宽度等于从 'A' 到 W 的字母个数。Excel 表格是一个高度 * 宽度的二维整数数组，数组中元素初始化为 0。第一
 * //行下标从 1 开始，第一列下标从 'A' 开始。
 * //
 * // void Set(int row, char column, int val): 设置 C(row, column) 中的值为 val。
 * //
 * // int Get(int row, char column): 返回 C(row, column) 中的值。
 * //
 * // int Sum(int row, char column, List of Strings : numbers): 这个函数会将计算的结果放入 C(row
 * //, column) 中，计算的结果等于在 numbers 中代表的所有元素之和，这个函数同时也会将这个结果返回。求和公式会一直计算更新结果直到这个公式被其他的值
 * //或者公式覆盖。
 * //
 * // numbers 是若干字符串的集合，每个字符串代表单个位置或一个区间。如果这个字符串表示单个位置，它的格式如下：ColRow，例如 "F7" 表示位置 (
 * //7, F) 。如果这个字符串表示一个区间，它的格式如下：ColRow1:ColRow2。区间就是左上角为 ColRow1 右下角为 ColRow2 的长方形。
 * //
 * // 注意: 你可以认为不会出现循环求和的定义，比如说：mat[1]['A'] == sum(1, "B") 和 mat[1]['B'] == sum(1, "
 * //A").
 * //
 * // 示例 1:
 * //
 * //输入:
 * //["Excel", "set", "sum", "set", "get"]
 * //[[3, "C"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
 * //输出:
 * //[null, null, 4, null, 6]
 * //
 * //解释:
 * //Excel excel = new Excel(3, "C");
 * // // 构造一个 3*3 的二维数组，初始化全是 0。
 * // //   A B C
 * // // 1 0 0 0
 * // // 2 0 0 0
 * // // 3 0 0 0
 * //excel.set(1, "A", 2);
 * // // 设置 C(1,"A") 为 2。
 * // //   A B C
 * // // 1 2 0 0
 * // // 2 0 0 0
 * // // 3 0 0 0
 * //excel.sum(3, "C", ["A1", "A1:B2"]); // return 4
 * // // 将 C(3,"C") 的值设为 C(1,"A") 单点以及左上角为 C(1,"A") 右下角为 C(2,"B") 的长方形两者之和。返回值 4。
 * // // 1 2 0 0
 * // // 2 0 0 0
 * // // 3 0 0 4
 * //excel.set(2, "B", 2);
 * //// 将 C(2,"B") 设为 2。 注意 C(3, "C") 的值也同时改变。
 * // //   A B C
 * // // 1 2 0 0
 * // // 2 0 2 0
 * // // 3 0 0 6
 * //excel.get(3, "C"); // 返回 6
 * //
 * // 提示:
 * //
 * // 1 <= height <= 26
 * // 'A' <= width <= 'Z'
 * // 1 <= row <= height
 * // 'A' <= column <= width
 * // -100 <= val <= 100
 * // 1 <= numbers.length <= 5
 * // numbers[i] 的格式为 "ColRow" 或 "ColRow1:ColRow2".
 * // set, get, and sum 操作数不超过 100 次
 * //
 * // Related Topics 图 设计 拓扑排序
 *
 *
 * 执行结果：通过
 *  执行用时：17 ms, 在所有 Java 提交中击败了 75.0% 的用户
 *  内存消耗：38.4 MB, 在所有 Java 提交中击败了 71.4% 的用户
 * @author binarySigh
 * @date 2021/9/14 19:47
 */
public class LC0631_Excel {
    private int m;
    private int n;
    private int[][] matrix;
    //由于 a 可能会对 b 依赖不止一次，因此这里选择使用Map,其中key为依赖的对象，value为依赖的次数
    // [i][0] -> 入度集  ; [i][1] -> 出度集
    private HashMap<Integer, Integer>[][] nodes;

    public LC0631_Excel(int height, char width){
        this.m = height;
        this.n = width - 'A' + 1;
        this.matrix = new int[m][n];
        this.nodes = new HashMap[m * n][2];
    }

    public void set(int row, char column, int val){
        int r = row - 1, c = column - 'A';
        int pos = r * n + c;
        // 为当前节点赋值之前，先将该节点可能存在的规则影响消除掉
        deleteRule(pos);
        //设值
        this.matrix[r][c] = val;
    }

    public int get(int row, char column){
        int r = row - 1, c = column - 'A';
        //按照规则计算
        calculate();
        //取结果
        return this.matrix[r][c];
    }

    public int sum(int row, char column, String[] numbers){
        int r = row - 1, c = column - 'A';
        int pos = r * n + c;
        // 为当前节点制定新规则之前，先将该节点可能存在的规则影响消除掉
        deleteRule(pos);
        //赋予新规则
        if(this.nodes[pos][0] == null){
            this.nodes[pos][0] = new HashMap<>();
        }
        if(this.nodes[pos][1] == null){
            this.nodes[pos][1] = new HashMap<>();
        }
        for(String s : numbers){
            dealWithSingleRule(s, pos);
        }
        //按照规则计算
        calculate();
        return this.matrix[r][c];
    }

    private void dealWithSingleRule(String s, int pos){
        HashMap<Integer, Integer> map = this.nodes[pos][0];
        //区间分隔符位置
        int partIdx = s.indexOf(":");
        if(partIdx < 0){
            int r = Integer.parseInt(s.substring(1)) - 1;
            int c = s.charAt(0) - 'A';
            int curPos = r * this.n + c;
            if(this.nodes[curPos][0] == null){
                this.nodes[curPos][0] = new HashMap<>();
                this.nodes[curPos][1] = new HashMap<>();
            }
            this.nodes[curPos][1].put(pos, 1);
            map.put(curPos, map.getOrDefault(curPos, 0) + 1);
            return;
        }
        //区间处理
        int r1 = Integer.parseInt(s.substring(1, partIdx)) - 1;
        int c1 = s.charAt(0) - 'A';
        int r2 = Integer.parseInt(s.substring(partIdx + 2)) - 1;
        int c2 = s.charAt(partIdx + 1) - 'A';
        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                int curIdx = i * this.n + j;
                if(this.nodes[curIdx][0] == null){
                    this.nodes[curIdx][0] = new HashMap<>();
                    this.nodes[curIdx][1] = new HashMap<>();
                }
                this.nodes[curIdx][1].put(pos, 1);
                map.put(curIdx, map.getOrDefault(curIdx, 0) + 1);
            }
        }
    }

    private void deleteRule(int p){
        HashMap<Integer, Integer> in = this.nodes[p][0];
        if(in == null){
            return;
        }
        //消除入度影响
        for(Map.Entry<Integer, Integer> entry : in.entrySet()){
            int key = entry.getKey();
            this.nodes[key][1].remove(p);
            //如果该入度节点也为空了，就置空
            if(this.nodes[key][0].size() == 0 &&
                    this.nodes[key][1].size() == 0){
                this.nodes[key][0] = null;
                this.nodes[key][1] = null;
            }
        }
        //当前规则置空
        this.nodes[p][0].clear();
        if(this.nodes[p][0].size() == 0 &&
                this.nodes[p][1].size() == 0){
            this.nodes[p][0] = null;
            this.nodes[p][1] = null;
        }
    }

    //按照规则计算
    private void calculate(){
        int len = this.nodes.length;
        Stack<Integer> stack = new Stack<>();
        //入度记录表，用于拓补排序。对于没有被任何规则影响到的节点，入度记为-1，即无效位置
        int[] ins = new int[len];
        //初始化入度表，同时将初始入度为0的节点压栈
        for(int i = 0; i < len; i++){
            if(this.nodes[i][0] == null){
                ins[i] = -1;
            } else {
                ins[i] = this.nodes[i][0].size();
                if(ins[i] == 0){
                    stack.push(i);
                }
            }
        }
        //拓补排序
        while(!stack.isEmpty()){
            int cur = stack.pop();
            int curRow = cur / this.n;
            int curCol = cur % this.n;
            //如果当前节点是真实的求和公式起点，则当前格无需置零。
            // 否则需将当前格置零，避免以前的结果影响本次的 += 计算
            // 入度集size==0 表示当前格子值就是他本身，不来源于某个sum公式，因此也不能置零
            if(this.nodes[cur][0].size() > 0){
                this.matrix[curRow][curCol] = 0;
            }
            HashMap<Integer, Integer> in = this.nodes[cur][0];
            HashMap<Integer, Integer> out = this.nodes[cur][1];
            //根据入度表，计算当前格子的值
            for(Map.Entry<Integer, Integer> entry : in.entrySet()){
                int preRow = entry.getKey() / this.n;
                int preCol = entry.getKey() % this.n;
                this.matrix[curRow][curCol] += this.matrix[preRow][preCol] * entry.getValue();
            }
            //遍历出度表，消除影响
            for(Map.Entry<Integer, Integer> entry : out.entrySet()){
                ins[entry.getKey()]--;
                if(ins[entry.getKey()] == 0){
                    stack.push(entry.getKey());
                }
            }
        }
    }






    public static void main(String[] args) {
  /*LC0631_Excel exc = new LC0631_Excel(3, 'C');
  exc.set(1, 'A', 2);
  int a = exc.sum(3, 'C', new String[]{"A1", "A1:B2"});
  exc.set(2, 'B', 2);
  int b = exc.get(3, 'C');

  exc.set(3, 'C', 5);
  System.out.println(a);
  System.out.println(b);

  System.out.println(exc.get(3, 'C'));*/

        LC0631_Excel exc = new LC0631_Excel(5, 'E');
        exc.set(1, 'A', 5);
        exc.set(1, 'B', 3);
        exc.set(1, 'C', 2);
        int a1 = exc.sum(1, 'C', new String[]{"A1","A1:B1"});
        int a2 = exc.get(1, 'C');
        exc.set(1, 'B', 5);
        int a3 = exc.get(1, 'C');
        int a4 = exc.sum(1, 'B', new String[]{"A1:A5"});
        exc.set(5, 'A', 10);
        int a5 = exc.get(1, 'B');
        int a6 = exc.get(1, 'C'); // -> 25
        int a7 = exc.sum(3, 'C', new String[]{"A1:C1", "A1:A5"});
        exc.set(3, 'A', 3);
        int a8 = exc.get(1, 'B');
        int a9 = exc.get(1, 'C');
        int a10 = exc.get(3, 'C');
        int a11 = exc.get(5, 'A');

        System.out.println(a1); // 13
        System.out.println(a2); // 13
        System.out.println(a3); // 15
        System.out.println(a4); // 5
        System.out.println(a5); // 15
        System.out.println(a6); // 25 --->
        System.out.println(a7); // 60
        System.out.println(a8); // 18
        System.out.println(a9); // 28
        System.out.println(a10); // 69
        System.out.println(a11); // 10

    }
}
