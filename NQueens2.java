package leetcode_test;

public class NQueens2 {

	public static void main(String[] args) {
//		boolean[][] currQueens  = {{false, true},{false, false}};
//		System.out.println("test:" + validQueens(currQueens, 0, 1));
		int input = 4;
		int output = 0;
		try {
			output = totalNQueens(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result:" + output); 
	}
	
	/**
	 * the solution is very similar to NQueens, but use boolean[][] to save time(14ms->1ms) and space (41.5MB->39.2MB)
	 * markCol[] is to store the used column, which uses extra space(boolean[n]) to save time for creating and checking column
	 * 
	 * @param n
	 * @return
	 */
    public static int totalNQueens(int n) {
    	// create all possible lines
    	boolean[] markCol = new boolean[n];
    	boolean[][] currQueens = new boolean[n][n];
    	
        return createQueens(currQueens, markCol, 0);
    }
    
    public static int createQueens(boolean[][] currQueens, boolean[] markCol, int row) {
    	if (row == markCol.length) {
    		return 1;
    	}
    	
    	int total = 0;
    	for (int col = 0; col < markCol.length; col++) {
    		// if this column is used, ignore
    		if (markCol[col]) {
    			continue;
    		}
    		currQueens[row][col] = true;
    		markCol[col] = true;
//			System.out.println("currQueens:" + currQueens); 
    		if (validQueens(currQueens, row, col)) {
    			total += createQueens(currQueens, markCol, row + 1);
    		}
    		currQueens[row][col] = false;
    		markCol[col] = false;
    	}
    	return total;
    }
    
    /**
     * check if queens is valid for new line, whose 'Q' is in [i,j]
     * since the invalid possibility in row and column have avoided when create, we should only check diagonals
     * 
     * @param queens
     * @param i
     * @param j
     * @return
     */
    public static boolean validQueens(boolean[][] currQueens, int i, int j) {
    	int col0 = j - 1;
    	int col1 = j + 1;
    	int row = i - 1;
    	while (row >= 0 && (col0 >= 0 || col1 < currQueens.length)) {
    		if ((col0 >= 0 && currQueens[row][col0]) 
    				|| (col1 < currQueens.length && currQueens[row][col1])) {
    			return false;
    		}
    		col0--;
    		col1++;
    		row--;
    	}
    	return true;
    }
}
