package leetcode_test;

import java.util.Arrays;

public class NextPermutation {

	public static void main(String[] args) {
//		int[] input = {1,2,3};
//		int[] input = {1,5,1};
//		int[] input = {5,1,1};
//		int[] input = {3,2,1};
		int[] input = {4,2,4,4,3};
		try {
			nextPermutation(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + ",");
		}
	}
	
	public static void nextPermutation2(int[] nums) {
		// to find out i as the pre item of last top
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        
        // find out the value which should swap with nums[i]
        // since the value from i+1 to the end is ascending, can just simply check items from the end
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // change the ascending arrays to descending, and descending to ascending
        reverse(nums, i + 1);
	}

    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * swap the value of index i and j
     * 
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * this solution is coded as my way to deal with this problem
     * 
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
    	// if nums.length == 1, there is no need to resort
    	if (nums.length == 1) {
    		return;
    	}
    	
    	// to find out the last top in this array
    	int topLast = nums.length - 1;
    	for (int i = nums.length - 1; i > 0; i--) {
    		if (nums[i] > nums[i - 1] && ( i == nums.length - 1 || nums[i] >= nums[i + 1])) {
    			topLast = i;
    			break;
    		}
    	}
		System.out.println("topLast: " + topLast);
		// if we can't find out the last top(this array is sorted descending), then change it to sorted ascending
    	if (topLast == nums.length - 1 && nums[topLast] <= nums[topLast - 1]) {
    		Arrays.sort(nums);
    		return;
    	}
    	
    	// change the array from topLast-1 to the end
    	// topLast-1 is changed to the next ascending num, and then the last nums are sorted ascending
    	int[] changeNums = Arrays.copyOfRange(nums, topLast, nums.length);
		System.out.println("changeNums.length: " + changeNums.length);
    	Arrays.sort(changeNums);
    	int start = nums[topLast - 1];
    	int newStart = 0;
    	for (int i = changeNums.length - 1; i >= 0; i--) {
    		if (changeNums[i] <= start) {
    			newStart = i + 1;
    			break;
    		}
    	}
		System.out.println("newStart: " + newStart);
    	nums[topLast - 1] = changeNums[newStart];
    	changeNums[newStart] = start;
    	for (int i = topLast; i < nums.length; i++) {
    		nums[i] = changeNums[i - topLast];
    	}
    }
}
