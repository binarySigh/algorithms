package com.mjscode.algorithms.newCoder.huawei;

import java.io.*;

/**
 * @author binarySigh
 * @date 2021/8/25 0:01
 */
public class HJ44_sudoku {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        char[][] board = new char[9][9];
        int idx = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while ((str = br.readLine()) != null) { // 注意 while 处理多个 case
            for(int i = 0; i < str.length(); i += 2){
                board[idx][i >> 1] = str.charAt(i);
            }
            idx++;
        }
        solveSudoku(board);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(board[i][j]);
                if(j == 8){
                    sb.append('\n');
                } else {
                    sb.append(' ');
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void solveSudoku(char[][] board){
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] box = new int[3][3];
        boolean[][] fixed = new boolean[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '0'){
                    continue;
                }
                fixed[i][j] = true;
                int cur = 1 << (board[i][j] - '1');
                row[i] |= cur;
                col[j] |= cur;
                box[i/3][j/3] |= cur;
            }
        }
        complete(board, row, col, box, fixed, 0);
    }

    public static boolean complete(char[][] board, int[] row, int[] col, int[][] box, boolean[][] fixed, int idx){
        if(idx == 81){
            return true;
        }
        int i = idx / 9;
        int j = idx % 9;
        if(fixed[i][j]){
            if(complete(board, row, col, box, fixed, idx + 1)){
                return true;
            }
        } else {
            for(int n = 1; n <= 9; n++){
                int cur = 1 << (n - 1);
                if((row[i] & cur) == 0 &&
                        (col[j] & cur) == 0 &&
                        (box[i/3][j/3] & cur) == 0){
                    row[i] |= cur;
                    col[j] |= cur;
                    box[i/3][j/3] |= cur;
                    board[i][j] = (char)(n + '0');
                    if(complete(board, row, col, box, fixed, idx + 1)){
                        return true;
                    } else {
                        row[i] -= cur;
                        col[j] -= cur;
                        box[i/3][j/3] -= cur;
                        board[i][j] = '.';
                    }
                }
            }
        }
        return false;
    }
}
