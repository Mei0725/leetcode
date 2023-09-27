package leetcode_test;

public class MinimumReplacementsToSortTheArray {

	/**
	 * check from end to start. 
	 * And for any item nums[i], which should be replaced, to avoid to influence about nums[i-1],
	 * the fount replaced item should be as big as possible, so we will try to separate nums[i] averagely
	 * 
	 * @param nums
	 * @return
	 */
    public long minimumReplacement(int[] nums) {
        long ope = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
        	// in this case, nums[i] should be replaced
            if (nums[i] <= nums[i + 1]) {
                continue;
            }

            // try to separate nums[i] averagely
            // because the biggest replaced item equals to nums[i+1], the replacing operation will happen time-1 times
            int time = 1;
            if (nums[i] % nums[i + 1] == 0) {
                time = nums[i] / nums[i + 1];
                nums[i] = nums[i + 1];
            } else {
                time = (nums[i] / nums[i + 1]) + 1;
                // since there is no need to store the replaced items, we can simply change nums[i] to the min replaced item
                nums[i] = nums[i] / time;
            }
            ope += (time - 1);
        }
        return ope;
    }
}
