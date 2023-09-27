package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

	/**
	 * use a list-change to store the cells should change value, 
	 * check if cells should change firstly but do not change immediately,
	 * to avoid the changed value will influence the check of following cells.
	 * 
	 * @param board
	 */
    public void gameOfLife(int[][] board) {
    	// store the cells should be changed
        List<int[]> change = new ArrayList<>();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	// count the number of living cells around board[i][j]
                int count1 = 0;
                for (int a = i - 1; a <= i + 1; a++) {
                    for (int b = j - 1; b <= j + 1; b++) {
                    	// check if it is out of board or if it is the cell itself
                        if (a < 0 || a >= m || b < 0 || b >= n || (a == i && b == j)) {
                            continue;
                        }
                        count1 += board[a][b];
                    }
                }
                // check if this cell should be changed
                if ((board[i][j] == 1 && (count1 < 2 || count1 > 3)) || 
                    (board[i][j] == 0 && count1 == 3)) {
                    change.add(new int[] {i, j});
                }
            }
        }

        // handle all changed cells simultaneously
        for (int[] tmp : change) {
            board[tmp[0]][tmp[1]] = board[tmp[0]][tmp[1]] == 0 ? 1 : 0;
        }
    }
}
