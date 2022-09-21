package leetcode_test;

public class UniquePaths {

	public static void main(String[] args) {
		int input = 3;
		int k = 2;
//		int input = 4;
//		int k = 9;
		int output = -1;
		try {
			output = uniquePaths(input, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by DP.
	 * paths[][] to store the result when width is m and height is n
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
    public static int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (i == 0 || j == 0) {
        			paths[i][j] = 1;
        		} else {
        			paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
        		}
        	}
        }
        return paths[m - 1][n - 1];
    }
}
