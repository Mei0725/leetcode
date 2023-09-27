package leetcode_test;

public class EqualRowAndColumnPairs {

	/**
	 * check row0->n and for each rowi, check column0->n, if this column equals to rowi, result+1
	 * 
	 * @param grid
	 * @return
	 */
    public int equalPairs(int[][] grid) {
        int res = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	// l is the max length of rowi and columnj which has the same items
                int l = 0;
                while (l < n && grid[i][l] == grid[l][j]) {
                    l++;
                }
                // if l is n, it means the total row and column is equal, result+1
                if (l == n) {
                    res++;
                }
            }
        }
        return res;
    }
}
