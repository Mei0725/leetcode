package leetcode_test;

import java.util.Arrays;

public class ValidSudoku {

	public static void main(String[] args) {
		char[][] input = {{'.'}};
		boolean output = true;
		try {
			output = isValidSudoku(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}

    public static boolean isValidSudoku(char[][] board) {
    	// check the row and colCount
    	// put these 2 check together to save time
    	int[] rowCount = new int[128];
    	int[] colCount = new int[128];
    	for (int i = 0; i < 9; i++) {
        	Arrays.fill(rowCount, 1);
        	Arrays.fill(colCount, 1);
    		for (int j = 0; j < 9; j++) {
    			if (board[i][j] != '.') {
    				if (rowCount[board[i][j]] < 1) {
//    					System.out.println("board[i][j]:" + board[i][j]);
//    					System.out.println("i:" + i);
//    					System.out.println("j:" + j);
    					return false;
    				}
    				rowCount[board[i][j]]--;
    			}
    			if (board[j][i] != '.') {
    				if (colCount[board[j][i]] < 1) {
//    					System.out.println("board[j][i]:" + board[i][j]);
    					return false;
    				}
    				colCount[board[j][i]]--;
    			}
    		}
    	}
    	
    	// check the 3*3 box
    	for (int i = 0; i < 9; i += 3) {
    		for (int j = 0; j < 9; j += 3) {
            	Arrays.fill(rowCount, 1);
    			for (int k = 0; k < 9; k++) {
    				int row = k / 3, col = k % 3;
        			if (board[i + row][j + col] != '.') {
        				if (rowCount[board[i + row][j + col]] < 1) {
        					return false;
        				}
        				rowCount[board[i + row][j + col]]--;
        			}
    			}
    		}
    	}
    	return true;
    }
}
