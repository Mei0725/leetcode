package leetcode_test;

public class LongestIncreasingSubsequence {

	/**
	 * use dp to solve problem.
	 * for every item, find out its longest increasing length by finding out this length for items before and less than it.
	 * do this operation until the last item in nums.
	 * 
	 * @param nums
	 * @return
	 */
    public int lengthOfLIS(int[] nums) {
        int[] length = new int[nums.length];
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            length[i] = 1;
            for (int j = i - 1; j >= length[i] - 1; j--) {
                if (nums[i] > nums[j] && length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                }
            }
            max = Math.max(max, length[i]);
            // System.out.println("i: " + i);
            // System.out.println("length[i]: " + length[i]);
        }
        return max;
    }
}
