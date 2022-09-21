package leetcode_test;

public class SearchInRotatedSortedArray2 {

	public static void main(String[] args) {
//		int[] input = {3,4,9,1,1};
//		int target = 4;
//		int[] input = {1,0,1,1,1};
//		int target = 0;
//		int[] input = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
//		int target = 2;
//		int[] input = {3,1};
//		int target = 1;
		int[] input = {1,1,3};
		int target = 3;
//		int[] input = {2,2,2,0,0,1};
//		int target = 0;
		boolean output = false;
		try {
			output = search(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * find the pivot index, create left as the top of the array and right as the bottom of the array
	 * must handle the same values in array specially
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
    public static boolean search(int[] nums, int target) {
    	int i = 0, j = nums.length - 1;
    	if (target < nums[i] && target > nums[j]) {
    		return false;
    	}
    	// find out 'k'
    	int left = i, right = j;
    	while (right - left > 1) {
    		// if left == right, it is impossible to make sure which side the top at
    		// so we can only move the position one by one
    		if (nums[right] == nums[left]) {
    			if (nums[left + 1] < nums[left]) {
    				// to handle the case that left is the top of the array
    				right = left + 1;
    				break;
    			} else {
        			left++;
        			continue;
    			}
    		}
    		int middle = (left + right) / 2;
    		// it must be >= to handle the array have many same values
    		if (nums[middle] >= nums[left]) {
    			left = middle;
    		} else {
    			right = middle;
    		}
    	}
		System.out.println("left: " + left);
		System.out.println("right: " + right);
		if (nums[left] <= nums[right]) {
			//in this case, this array is sorted, so there is no need to change i and j
		} else if (target > nums[left] || target < nums[right]) {
    		return false;
    	} else if (target < nums[0]) {
    		i = right;
    	} else {
    		j = left;
    	}
		System.out.println("i: " + i);
		System.out.println("j: " + j);
    	
		// binary search
    	while (i <= j) {
    		int middle = (i + j) / 2;
    		if (target == nums[middle]) {
    			return true;
    		} else if (target > nums[middle]) {
    			i = middle + 1;
    		} else {
				j = middle - 1;
    		}
    	}
    	return false;
    }
}
