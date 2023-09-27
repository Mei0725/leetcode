package leetcode_test;

public class LongestSubarrayOf1AfterDeletingOneElement {

	/**
	 * solve by store the length of last 1's substring
	 * 
	 * @param nums
	 * @return
	 */
    public int longestSubarray(int[] nums) {
        int max = 0;
        // l1 is -1 to handle the case that all items are 1
        int l1 = -1, l2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                l2++;
            } else {
            	// once there is 0, then the last length l1 can be 0
            	// because there is no need to delete a 1-item
                if (l1 < 0) {
                    l1 = 0;
                }
                max = Math.max(max, l1 + l2);
                l1 = l2;
                l2 = 0;
            }
        }
        // can't check all-1-string by max==0 because it can not handle case like[0,1,1,1]
        // return max == 0 ? l2 - 1 : Math.max(max, l1 + l2);
        return Math.max(max, l1 + l2);
    }

    /**
     * solve by store the start and middle position(0) of every substring
     * 
     * @param nums
     * @return
     */
    public int longestSubarrayBySlidingWindows(int[] nums) {
    	// start is the start of substring
    	// mid is the position of 0
    	// end is the end of substring
        int start = 0, end = 0, mid = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            end = i;
            if (nums[i] == 0) {
            	// now end is in the position 0, which should not contain in result
            	// and there is a 0 in this substring
            	// so the length should be end - start - 1
                max = Math.max(max, end - start - 1);
                if (mid == start) {
                    mid = i + 1;
                } else {
                    start = mid;
                    mid = i + 1;
                }
            }
        }
        // now end contains in result, so there is no need to -1
        return Math.max(end - start, max); 
    }
}
