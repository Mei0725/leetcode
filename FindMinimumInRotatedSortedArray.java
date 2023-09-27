package leetcode_test;

public class FindMinimumInRotatedSortedArray {

	/**
	 * solve by binary search.
	 * 
	 * because nums is a rotated ascending order arrays, 
	 * we can judge if the result is in which side of mid, so it can be solved by binary search
	 * 
	 * @param nums
	 * @return
	 */
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    /**
     * find out the result in [start, end]
     * 
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int findMin(int[] nums, int start, int end) {
    	// there is only one item
        if (start == end) {
            return nums[start];
        }

        int mid = (start + end) / 2;
        if ((mid == 0 || nums[mid] < nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] < nums[mid + 1])) {
        	// mid is the min one in nums
            return nums[mid];
        } else if (nums[mid] > nums[start] && nums[mid] > nums[end]) {
        	// result is in the right of mid
            return findMin(nums, mid + 1, end);
        } else if (mid == start) {
        	// there are only 2 items in [start,end], 
        	// because mid is round down, in this case mid-1 will be less than start
        	// so it should be handle specially
            return Math.min(nums[start], nums[end]);
        }
		// System.out.println("start: " + start);
		// System.out.println("mid: " + mid);
        return findMin(nums, start, mid - 1);
    }
}
