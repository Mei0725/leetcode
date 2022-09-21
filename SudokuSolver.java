package leetcode_test;

import java.util.Arrays;

public class SudokuSolver {

	public static void main(String[] args) {
		char[][] input = {{'.'}};
		boolean output = true;
		try {
			solveSudoku(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}
	
	/**
	 * use backtracking(greedy) to find out the sudoku's result
	 * for every point, find out valid char list, and try every char
	 * if the char list is empty or all chars are invalid, then backtrack to the previous one
	 * 
	 * @param board
	 */
	public static void solveSudoku(char[][] board) {
		solveSudoku(board, 0, 0);
    }
	
	/**
	 * try every valid char for point board[i][j]
	 * as a simpler solution, we can try char[1->9] as the valid char, but it would spend much more time(116ms->8ms)
	 * 
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
	public static boolean solveSudoku(char[][] board, int i, int j) {
		// deal with newline
		if (j > 8) {
			i++;
			j -= 9;
		}
		if (i > 8) {
			return true;
		}
		
		//System.out.println("board[" + i + "][" + j + "]=" + board[i][j]);
		if (board[i][j] != '.') {
			return solveSudoku(board, i, j + 1);
		} else {
			for (char tmp : getValidList(board, i, j)) {
				board[i][j] = tmp;
				if (solveSudoku(board, i, j + 1)) {
					return true;
				}
			}
		}
		board[i][j] = '.';
		return false;
	}
	
	/**
	 * to get the valid char list in point[i][j] as same as check if the sudoku in point[i][j] is valid
	 * 
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
	public static char[] getValidList(char[][] board, int i, int j) {
		// if there is no need to check sudoku valid, these 3 int[] can be reduced to 1 int[]
		int[] rowList = new int[64];
		int[] colList = new int[64];
		int[] boxList = new int[64];
		for (int k = 0; k < 9; k++) {
			rowList[board[i][k]]--;
			colList[board[k][j]]--;
		}
    	int row = (i / 3) * 3;
    	int col = (j / 3) * 3;
    	for (int m = 0; m < 3; m++) {
    		for (int n = 0; n < 3; n++) {
    			boxList[board[m + row][n + col]]--;
    		}
    	}
		char[] validList = new char[9];
		int count = 0;
		for (char tmp = '1'; tmp <= '9'; tmp++) {
			if (rowList[tmp] == 0 && colList[tmp] == 0 && boxList[tmp] == 0) {
				validList[count] = tmp;
				count++;
			} else if (rowList[tmp] < -1 || colList[tmp] < -1 || boxList[tmp] < -1) {
				return new char[0];
			}
		}
		return Arrays.copyOf(validList, count);
	}

	/**
	 * to check if the sudoku in point[i][j] is valid
	 * this function now is merged into getValidList(char[][] board, int i, int j)
	 * 
	 * @param board
	 * @param i
	 * @param j
	 * @return
	 */
    public static boolean vaildSudoku(char[][] board, int i, int j) {
    	// check the row and column
    	int[] rowCount = new int[64];
    	int[] colCount = new int[64];
    	Arrays.fill(rowCount, 1);
    	Arrays.fill(colCount, 1);
    	for (int k = 0; k < 9; k++) {
			if (board[i][k] != '.') {
				if (rowCount[board[i][k]] < 1) {
					return false;
				}
				rowCount[board[i][k]]--;
			}
			if (board[k][j] != '.') {
				if (colCount[board[k][j]] < 1) {
					return false;
				}
				colCount[board[k][j]]--;
			}
    	}

    	// check the 3*3 box
    	Arrays.fill(rowCount, 1);
    	int row = (i / 3) * 3;
    	int col = (j / 3) * 3;
    	for (int m = 0; m < 3; m++) {
    		for (int n = 0; n < 3; n++) {
    			if (board[m + row][n + col] != '.') {
    				if (rowCount[board[m + row][n + col]] < 1) {
    					return false;
    				}
    				rowCount[board[m + row][n + col]]--;
    			}
    		}
    	}
    	return true;
    }
}
