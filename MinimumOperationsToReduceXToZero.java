package leetcode_test;

public class MinimumOperationsToReduceXToZero {

	/**
	 * solve by sliding window.
	 * 
	 * firstly, get the min operations when it only remove items in the left.
	 * then, try the add the left items and remove the right items, and find out the min operations during this process.
	 * 
	 * @param nums
	 * @param x
	 * @return
	 */
    public int minOperations(int[] nums, int x) {
    	// find out the min operations when we only remove the left items
    	// use i to mark the board of left side
        int i = 0, sum = nums[0];
        while (sum < x && i < nums.length - 1) {
            sum += nums[++i];
        }

        int min = Integer.MAX_VALUE;
        if (sum == x) {
            min = i + 1;
        } else if (sum < x){
        	// check if the sum of all items is less then x
            return -1;
        }

        // use j to mark the board of right side
        int j = nums.length - 1;
        while (i >= 0) {
            sum -= nums[i--];
            while (sum < x) {
                sum += nums[j--];
            }
            
            // in this case, the number of operation is in [0, i] and (j, nums.length - 1]
            if (sum == x) {
                // min = Math.min(min, i + 1 + (nums.length - 1 - j));
                min = Math.min(min, i + nums.length - j);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
