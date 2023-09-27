package leetcode_test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumPerformanceOfATeam {

	/**
	 * the solution is similar to Class MaximumSubsequenceScore
	 * use int[][] link to store all speed and efficiency from efficiency higher to lower
	 * and use Priority pool to store the k-max value in speed
	 * 
	 * check link 0->i, when there is a new speed[i] and efficiency[i], 
	 * (the sum of k-max speeds)*efficiency[i] is the current performance
	 * there is no need to consider about speed[i] is moved, since at that case performance[i-1] must larger than the real performance[i]
	 * 
	 * @param n
	 * @param speed
	 * @param efficiency
	 * @param k
	 * @return
	 */
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    	// link speed and efficiency, and sort input by efficiency from higher to lower
        int[][] link = new int[n][2];
        for (int i = 0; i < n; i++) {
            link[i][0] = speed[i];
            link[i][1] = efficiency[i];
        }
        Arrays.sort(link, (a, b) -> (b[1] - a[1]));

        // speedSum may be bigger than Integer.MAX_VALUE, use it so that there is no need to calculate sum of pool every loop
        long per = 0, speedSum = 0;
        PriorityQueue<Integer> pool = new PriorityQueue<Integer>(k, (a, b) -> (a - b));
        for (int i = 0; i < n; i++) {
            // there is no need to consider about link[i][0] is moved, 
            // since at that case performance[i-1] must larger than the real performance[i]
            pool.offer(link[i][0]);
            speedSum += link[i][0];
            if (pool.size() > k) {
                speedSum -= pool.poll();
            }
            // if the number of item must be k, then check if pool.size()==k
            // but in this question, the largest number of item is k, so check every loop
            per = Math.max(per, speedSum * link[i][1]);
        }
        return (int)(per % 1000000007);
    }
}
