package leetcode_test;

public class ConvertSortedArrayToBinarySearchTree {

	/**
	 * solve by Divide and Conquer.
	 * find out the middle item in [start, end] of nums, and do the same thing in its left and right.
	 * 
	 * @param nums
	 * @return
	 */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(0, nums.length - 1, nums);
    }

    public TreeNode sortedArrayToBST(int start, int end, int[] nums) {
        if (start == end) {
            return new TreeNode(nums[start]);
        } else if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        return new TreeNode(nums[mid], sortedArrayToBST(start, mid - 1, nums), sortedArrayToBST(mid + 1, end, nums));
    }
}
