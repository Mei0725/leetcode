package leetcode_test;

public class New21Game {

	/**
	 * solve by dp
	 * save the results of every value i in dp[i], and calculate dp[i] by the previous ones
	 * 
	 * @param n
	 * @param k
	 * @param maxPts
	 * @return
	 */
    public double new21Game(int n, int k, int maxPts) {
    	// when n is larger than maxVal, then all results must be smaller than maxVal and n,
    	// in this case, return 1
        int maxVal = k + maxPts - 1;
        if (n >= maxVal) {
            return 1.000000;
        }
        
        // dp[i] stores the possibility to arrive in i
        double dp[] = new double[n + 1];
        // no matter what case it is, we must start from dp[0], so dp[0] must be arrived
        dp[0] = 1;
        // s stores the reuslt of previous maxPts-numbers items
        double s = k > 0 ? 1 : 0;
        for (int i = 1; i <= n; i++) {
        	// s is the possibility that the sum of arrived i-maxPts -> i-1
        	// since for every possible position p, there is only 1/maxPts possibility to arrived i from p
        	// it must be divided by maxPts
            dp[i] = s / maxPts;
            // for i+1, i is also a possible position that can arrive i+1
            if (i < k) {
                s += dp[i];
            }
            // for i+1, i-maxPts is too far to arrive i+1, remove dp[i - maxPts] from s
            if (i - maxPts >= 0 && i - maxPts < k) {
                s -= dp[i - maxPts];
            }
        }
        double ans = 0;
        for (int i = k; i <= n; i++) {
            ans += dp[i];
        }
        return ans;
    }

    /**
     * though it is also solved by dp,
     * time complexity is O(maxPtx*n) and there are lots of multiplications 
     * when maxPtx is big this function will be overtime
     * 
     * @param n
     * @param k
     * @param maxPts
     * @return
     */
    public double new21GameOverTime(int n, int k, int maxPts) {
        int maxVal = k + maxPts - 1;
        if (n >= maxVal) {
            return 1.000000;
        }

        double[] num = new double[maxVal + 1];
        num[0] = 1;
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j <= i + maxPts; j++) {
                num[j] += num[i] * 1/maxPts;
            }
        }
        // for (int i = 1; i <= maxVal; i++) {
        //     System.out.print(num[i] + ",");
        // }

        double sum = 0;
        for (int i = k; i <= n; i++) {
            sum += num[i];
        }
        // System.out.println("sum: " + sum);
        return sum;
    }
}
