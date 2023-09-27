package leetcode_test;

public class MaximalSquare {

	/**
	 * use dp to solve problem.
	 * 
	 * use dp[i][j] to store the max edge for matrix[i][j], and find out the max one.
	 * for each matrix[i][j], the edge of it should be the min one of its row and line.
	 * and to avoid mistake in case like: 1 0 1
	 *                                    1 0 1
	 *                                    1 1 1
	 * for max edge, we should check if it is full by checking diagonal. 
	 * 
	 * @param matrix
	 * @return
	 */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	// if item is '0', then the max edge must be 0
                if (matrix[i][j] == '0') {
                    continue;
                }
                // System.out.println("i:" + i + ", j:" + j);
                // calculate the max edge
                int line, row;
                if (i == 0) {
                    line = 1;
                } else {
                    line = dp[i - 1][j] + 1;
                }
                if (j == 0) {
                    row = 1;
                } else {
                    row = dp[i][j - 1] + 1;
                }
                // System.out.println("line:" + line + ", row:" + row);
                dp[i][j] = Math.min(line, row);
                // check if it is full
                // to get the correct value, we should check its nearby diagonal every time
                // but to save time, we can only check it when we want to update output, and if it's necessary, change dp[i][j] to correct value
//                int dia = 0;
//                if (i > 0 && j > 0) {
//                    dia = dp[i - 1][j - 1];
//                }
//                dp[i][j] = Math.min(dp[i][j], dia + 1);
                if (dp[i][j] > max) {
                	// by checking its diagonal to make sure the square is full
                    boolean full = true;
                    for (int k = 1; k < dp[i][j]; k++) {
                        if (dp[i - k][j - k] < dp[i][j] - k) {
                            full = false;
                            dp[i][j] = dp[i - k][j - k] + k;
                            break;
                        } 
                    }
                    if (full) {
                        max = dp[i][j];
                    }
                }
                // max = Math.max(max, dp[i][j]);
                // System.out.println("dp[i][j]:" + dp[i][j]);
            }
        }
        return max * max;
    }
}
