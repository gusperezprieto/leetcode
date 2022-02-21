/*

https://leetcode.com/problems/sudoku-solver/

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.

*/


class Solution {
    
    private int[][] rows = new int[9][10]; 
    private int[][] cols = new int[9][10]; 
    private int[][] boxes = new int[9][10]; 
    
    public void solveSudoku(char[][] board) {
        
        for (int pos = 0; pos<81; pos++){
            if (board[pos/9][pos%9] == '.') continue; 
            
            int num = Integer.parseInt(board[pos/9][pos%9] + ""); 
            placeNumber(board, num, pos); 
        }
        
        solveSudoku(board, 0); 
        
    }
    
    public boolean solveSudoku(char[][] board, int pos){
        
        if (pos >= 81) return true; 
        int nextp = pos; 
        for (nextp = pos; nextp<81; nextp++){
            
            int row = nextp/9; 
            int col = nextp%9;
            
            if (board[nextp/9][nextp%9] == '.') break; 
        }
        
        if (nextp == 81) return true; 
        
        for (int n = 1; n<=9; n++){
            
            if (!couldPlace(board,n,nextp)) continue;
            
            placeNumber(board,n,nextp); 
        
            if (solveSudoku(board, nextp+1)) return true; 
            
            // backtrack
            removeNumber(board,n, nextp); 
            
        }
        
        return false; 
    }
    
    private boolean couldPlace(char[][] board, int d, int pos){
        
        int row = pos/9; 
        int col = pos%9;
        int box = (row/3)*3 + (col/3); 
 
        if (board[row][col] != '.') return false; 
        return (this.rows[row][d] + this.cols[col][d] + this.boxes[box][d] == 0); 
    }
    
    private void placeNumber(char[][] board, int d, int pos){
        
        int row = pos/9; 
        int col = pos%9;
        int box = (row/3)*3 + (col/3); 
        
        this.rows[row][d] = 1; 
        this.cols[col][d] = 1; 
        this.boxes[box][d] = 1; 
        board[row][col] = (char)('0' + d); 
        
    }
    
    private void removeNumber(char[][] board, int d, int pos){
        
        int row = pos/9; 
        int col = pos%9;
        int box = (row/3)*3 + (col/3); 
        
        this.rows[row][d] = 0; 
        this.cols[col][d] = 0; 
        this.boxes[box][d] = 0; 
        board[row][col] = '.'; 
        
    }
    
}
