package leetcode_test;

import java.util.Stack;

public class Pattern132 {

	/**
	 * use Monotonic Stack as solution
	 * check from end to start, when there is a descending, then mark nums[k] as the max value which is following and less than max one
	 * it means when k is not -1, the part 32 is ready
	 * then if there is nums[i] < nums[k], i is the 1 and return true
	 * 
	 * check it from end to start is because it is easier to find out the min one
	 * 
	 * @param nums
	 * @return
	 */
    public boolean find132pattern(int[] nums) {
    	// store a descending list as the part (?->3)
        Stack<Integer> stack = new Stack<>();
        // the index of max one after current max value
        int k = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
        	// check if nums[i] can be seem as 1
            if (k > -1 && nums[k] > nums[i]) {
                return true;
            }

            // find out k, which is the max one smaller than nums[i]
            // then for the next i, nums[i] is 3 and nums[k] is 2
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                k = stack.pop();
            }

            stack.push(i);
        }

        return false;
    }
}
