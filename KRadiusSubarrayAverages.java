package leetcode_test;

import java.util.Arrays;

public class KRadiusSubarrayAverages {

	/**
	 * solve by sliding window
	 * maintain a window that contains (2*k+1) items
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
    public int[] getAverages(int[] nums, int k) {
        int[] res = new int[nums.length];
        int length = k * 2 + 1;
        // when length is larger than nums.length, all items in result should be -1
        if (nums.length < length) {
            Arrays.fill(res, -1);
            return res;
        }
        
        long sum = 0;
        // handle res[0->nums.length-k-1]
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i < length - 1) {
                if (i - k >= 0) {
                    res[i - k] = -1;
                }
                continue;
            }
            if (i - length >= 0) {
                sum -= nums[i - length];
            }
            res[i - k] = (int)(sum / length);
        }
        // handle res[nums.length-k->last one]
        for (int i = nums.length - k; i < nums.length; i++) {
            res[i] = -1;
        }
        return res;
    }
}
