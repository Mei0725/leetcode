package leetcode_test;

import java.util.Arrays;

public class MinimumNumberOfTapsToOpenToWaterAGarden {

	/**
	 * solve by dp.
	 * for every tap, check the farthest place it can arrive and store its min taps.
	 * since we will only consider about the linked of 2 taps, so there is no need to store the middle place value.
	 * 
	 * @param n
	 * @param ranges
	 * @return
	 */
    public int minTaps(int n, int[] ranges) {
        int[] res = new int[n + 1];
        Arrays.fill(res, 100000);
        res[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (ranges[i] == 0) {
                continue;
            }

            // find out the min number of taps it will use to arrive right
            int left = Math.max(0, i - ranges[i]), right = Math.min(i + ranges[i], n);
            for (int j = left; j <= right; j++) {
                res[right] = Math.min(res[j] + 1, res[right]);
            }
        }
        return res[n] > 10000 ? -1 : res[n];
    }
}
