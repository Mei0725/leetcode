package leetcode_test;

public class PredictTheWinner {

	/**
	 * solve by recursion.
	 * for every num, this function will calculate 4 times so the time complexity is O(4N^N)
	 * so it will spend a lot of time.
	 * 
	 * @param nums
	 * @return
	 */
    public boolean PredictTheWinnerByRecursion(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // to win, play1 should have a higher score than play2,
        // it means its score>=sum of nums
        return sum1(nums, 0, nums.length - 1, 0) >= ((double)sum / 2);
    }

    /**
     * play the game in [i,j], and sum is the current sum of play1.
     * for this function, play1 chance a num firstly.
     * 
     * @param nums
     * @param i
     * @param j
     * @param sum
     * @return
     */
    public int sum1(int[] nums, int i, int j, int sum) {
    	// it it the end of game.
        if (i == j) {
            return sum + nums[i];
        } else if (j - i == 1) {
        	// because play1 chance firstly, he can get a larger one
            return sum + Math.max(nums[i], nums[j]);
        }
        
        // if play1 chance i, to win, play2 will chance a num make the score of play1 smaller
        int s1 = Math.min(sum1(nums, i + 2, j, sum + nums[i]), sum1(nums, i + 1, j - 1, sum + nums[i]));
        int s2 = Math.min(sum1(nums, i, j - 2, sum + nums[j]), sum1(nums, i + 1, j - 1, sum + nums[j]));
        // to win, play1 will chance for i and j to make his final score larger
        return Math.max(s1, s2);
    }
    

    /**
     * solve by dp.
     * 
     * sum[i][j] is used to store the max score for person who get the final one in nums in [i,j]
     * for nums in [i,j], for play1, he will try to find out the max one from nums[i]+sum[i+1,j] and sum[i,j-1]+nums[j]
     *                    for play2, he will try to find out a smaller one from sum[i,j-1] and sum[i+1,j]
     * the time complexity is less than O(N*N), so it will spend less time(26ms->1ms)
     * 
     * @param nums
     * @return
     */
    public boolean PredictTheWinnerByDP(int[] nums) {
        int n = nums.length;
        int[][] sum = new int[n][n];
        int total = 0;
        for (int s = 1; s <= n; s++) {
            total += nums[s - 1];
            for (int i = 0; i + s - 1 < n; i++) {
                int j = i + s - 1;
                if (s == 1) {
                	// when size is 1, the only one can be chanced is nums[i]
                    sum[i][j] = nums[i];
                } else if (s % 2 == 1) {
                    sum[i][j] = Math.max(nums[i] + sum[i + 1][j], sum[i][j - 1] + nums[j]);
                } else {
                    sum[i][j] = Math.min(sum[i + 1][j], sum[i][j - 1]);
                }
            }
        }
        // if n is odd, it means play1 is the person get the final num
        if (n % 2 == 1) {
            return sum[0][n - 1] >= ((double)total / 2);
        }
        // if n is even, it means sum store the score of play2, so if play1 want to win, the max score of play2 should be small
        return sum[0][n - 1] <= ((double)total / 2);
    }
}
