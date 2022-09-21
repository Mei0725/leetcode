package leetcode_test;

public class SortColors {

	public static void main(String[] args) {
//		int[] input = {2,0,2,1,1,0};
		int[] input = {2,0,1};
		try {
			sortColors(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: ");
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + ",");
		}
	}

	/**
	 * Improved Bubble Sort.
	 * the max time complexity is 2*nums.length.
	 * 
	 * @param nums
	 */
    public static void sortColors(int[] nums) {
    	// index is the final position of the sorted array
    	// target is the current target value
    	int index = 0, target = 0;
    	while (target <= 1) {
    		// find out the final position that unsorted
    		while (index < nums.length && nums[index] == target) {
    			index++;
    		}
    		for (int i = index; i < nums.length; i++) {
    			if (nums[i] == target) {
    				int tmp = nums[index];
    				nums[index] = nums[i];
    				nums[i] = tmp;
            		while (index < nums.length && nums[index] == target) {
            			index++;
            		}
    			}
    		}
    		target++;
    	}
    }
}
