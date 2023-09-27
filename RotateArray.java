package leetcode_test;

public class RotateArray {

	/**
	 * solve by store the moved items into tmp, move all left items, put items in tmp to new position
	 * 
	 * @param nums
	 * @param k
	 */
    public void rotate(int[] nums, int k) {
        int l = nums.length;
    	//if there are loops, remove them 
        k = k % l;

        // store all moved items
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[l - k + i];
        }

        // replace items outside of k, 
        // to avoid to be covered value, it should handle from then end
        for (int i = l - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
        // put moved items into new position
        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }
}
