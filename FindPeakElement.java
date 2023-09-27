package leetcode_test;

public class FindPeakElement {

	/**
	 * solve by binary search.
	 * 
	 * for every item, the peak element must appear in the up side.
	 * so if it is not the peak, then we can try to find out the peak in its up side.
	 * if there are 2 up sides, every side will have a peak.
	 * 
	 * in this way, the time complexity will be O(logN)
	 * 
	 * @param nums
	 * @return
	 */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        return findPeakElement(nums, 0, nums.length - 1);
    }

    /**
     * find out the peak in [i1, i2]
     * 
     * @param nums
     * @param i1
     * @param i2
     * @return
     */
    public int findPeakElement(int[] nums, int i1, int i2) {
        if (i1 > i2) {
        	// normally, this code will not be used.
            return -1;
        } else if (i1 == i2) {
        	// if there is only one item, then check if it is the peak
            if ((i1 != 0 && nums[i1] <= nums[i1 - 1]) || 
                (i2 != nums.length - 1 && nums[i1] <= nums[i1 + 1])) {
                return -1;
            } else {
                return i1;
            }
        }

        // check the mid of [i1,i2], if it is the peak, return
        int mid = (i1 + i2) / 2;
        if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
            return mid;
        }
        // find out the peak in the up side
        if (mid != 0 && nums[mid] < nums[mid - 1]) {
            int res = findPeakElement(nums, i1, mid - 1);
            if (res != -1) {
                return res;
            }
        }
        if (mid != nums.length - 1 && nums[mid] < nums[mid + 1]) {
            int res = findPeakElement(nums, mid + 1, i2);
            if (res != -1) {
                return res;
            }
        }
    	// normally, this code will not be used.
        return -1;
    }
}
