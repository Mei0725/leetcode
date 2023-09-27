package leetcode_test;

public class NumberOfIncreasingPathsInAGrid {

	/**
	 * solve by depth-first search
	 * use int[][] path to store all possible path for grid[i][j]
	 * and for grid[i][j], the path[i][j] is the sum of 
	 * all possible grid in 4 directions(add it to the path near to it)+1(the path only contains itself)
	 * 
	 * @param grid
	 * @return
	 */
    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] path = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = (res + countPaths(path, i, j, grid)) % 1000000007;
                // System.out.println("res: " + res);
            }
        }
        return res;
    }

    /**
     * get the path that start from grid[i][j]
     * 
     * @param path
     * @param i
     * @param j
     * @param grid
     * @return
     */
    public int countPaths(int[][] path, int i, int j, int[][] grid) {
        if (path[i][j] != 0) {
            return path[i][j];
        }

        int m = grid.length, n = grid[0].length;
        path[i][j] = 1;
        if (i < m - 1 && grid[i + 1][j] > grid[i][j]) {
            path[i][j] = (path[i][j] + countPaths(path, i + 1, j, grid)) % 1000000007;
        }
        if (j < n - 1 && grid[i][j + 1] > grid[i][j]) {
            path[i][j] = (path[i][j] + countPaths(path, i, j + 1, grid)) % 1000000007;
        }
        if (i > 0 && grid[i - 1][j] > grid[i][j]) {
            path[i][j] = (path[i][j] + countPaths(path, i - 1, j, grid)) % 1000000007;
        }
        if (j > 0 && grid[i][j - 1] > grid[i][j]) {
            path[i][j] = (path[i][j] + countPaths(path, i, j - 1, grid)) % 1000000007;
        }
        return path[i][j];
    }
}
