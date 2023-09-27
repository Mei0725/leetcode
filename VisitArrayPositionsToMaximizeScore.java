package leetcode_test;

public class VisitArrayPositionsToMaximizeScore {

	/**
	 * solve by dp, use 2 long[] to store the result that the last choice one is even/odd
	 * 
	 * for nums[i], the result of it is choose from (same parities+nums[i]) and (different parities+nums[i]-x)
	 * 
	 * @param nums
	 * @param x
	 * @return
	 */
	public long maxScoreByArray(int[] nums, int x) {
		long[] even = new long[nums.length], odd = new long[nums.length];
		long max = nums[0];
		// since nums[0] must contains in result
        // for the different parities' 0, it should minus x in advance
		if (nums[0] % 2 == 0) {
			even[0] = nums[0];
			odd[0] = nums[0] - x;
		} else {
			even[0] = nums[0] - x;
			odd[0] = nums[0];
		}

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				even[i] = Math.max(even[i - 1] + nums[i], odd[i - 1] + nums[i] - x);
				max = Math.max(max, even[i]);
				odd[i] = odd[i - 1];
			} else {
				odd[i] = Math.max(odd[i - 1] + nums[i], even[i - 1] + nums[i] - x);
				max = Math.max(max, odd[i]);
				even[i] = even[i - 1];
			}
		}
		return max;
	}

	/**
	 * it is similar to maxScoreByArray, but use 2 long instead of arrays to store previous result
	 * to save time(12ms->10ms) and space(55.8MB->55.4MB)
	 * 
	 * @param nums
	 * @param x
	 * @return
	 */
    public long maxScore(int[] nums, int x) {
		long even, odd;
		long max = nums[0];
		if (nums[0] % 2 == 0) {
			even = nums[0];
			odd = nums[0] - x;
		} else {
			even = nums[0] - x;
			odd = nums[0];
		}

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				even = Math.max(even + nums[i], odd + nums[i] - x);
				max = Math.max(max, even);
			} else {
				odd = Math.max(odd + nums[i], even + nums[i] - x);
				max = Math.max(max, odd);
			}
		}
		return max;
    }
}
