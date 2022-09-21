package leetcode_test;

public class MinimumPathSum {

	public static void main(String[] args) {
		int[][] input = {{1,3,1},{1,5,1},{4,2,1}};
//		int[][] input = {{1}};
		int output = -1;
		try {
			output = minPathSumDP(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * use Backtracking to solve this problem.
	 * when the grid is very big, this solution will overtime.
	 * 
	 * @param grid
	 * @return
	 */
    public static int minPathSumBacktracking(int[][] grid) {
    	return minPathSumBacktracking(0, 0, 0, grid);
    }
	
    public static int minPathSumBacktracking(int i, int j, int sum, int[][] grid) {
    	if (i == grid.length - 1 && j == grid[0].length - 1) {
    		return sum + grid[i][j];
    	} else if (i == grid.length - 1) {
    		return minPathSumBacktracking(i, j + 1, sum + grid[i][j], grid);
    	} else if (j == grid[0].length - 1) {
    		return minPathSumBacktracking(i + 1, j, sum + grid[i][j], grid);
    	} else {
    		return Math.min(minPathSumBacktracking(i + 1, j, sum + grid[i][j], grid), minPathSumBacktracking(i, j + 1, sum + grid[i][j], grid));
    	}
    }
	
    /**
     * use DP to solve this problem
     * 
     * @param grid
     * @return
     */
    public static int minPathSumDP(int[][] grid) {
    	int width = grid.length, height = grid[0].length;
        int[][] paths = new int[width][height];
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j++) {
        		if (i == 0 && j == 0) {
        			paths[i][j] = grid[i][j];
        		} else if (i == 0) {
        			paths[i][j] = paths[i][j - 1] + grid[i][j];
        		} else if (j == 0) {
        			paths[i][j] = paths[i - 1][j] + grid[i][j];
        		} else {
            		paths[i][j] = Math.min(paths[i - 1][j], paths[i][j - 1]) + grid[i][j];
        		}
        	}
        }
        return paths[width - 1][height - 1];
    }
}
