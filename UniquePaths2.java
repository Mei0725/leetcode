package leetcode_test;

public class UniquePaths2 {

	public static void main(String[] args) {
		int[][] input = {{0,0,0},{0,1,0},{0,0,0}};
		int output = -1;
		try {
			output = uniquePathsWithObstacles(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] paths = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        for (int i = 1; i <= obstacleGrid.length; i++) {
        	for (int j = 1; j <= obstacleGrid[0].length; j++) {
        		if (obstacleGrid[i - 1][j - 1] == 1) {
        			continue;
        		}
        		if (i == 1 && j == 1) {
        			paths[i][j] = 1;
        		} else {
        			paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
        		}
        	}
        }
        return paths[obstacleGrid.length][obstacleGrid[0].length];
    }
}
