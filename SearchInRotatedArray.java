package leetcode_test;

import java.util.Arrays;

public class SearchInRotatedArray {
	
	public static void main(String[] args) {
		int[] array = {1, 0};
		int target = 1;
		int output = 0;
		try {
			output = search(array, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int search(int[] nums, int target) {
    	int length = nums.length, min = 0, max = length - 1;
    	int k, kNum;
    	while (max - min > 1) {
    		k = (min + max) / 2;
    		kNum = nums[k];
    		if (kNum < nums[min]) {
    			max = k;
    		} else {
    			min = k;
    		}
    	}
//		System.out.println("min: " + min);
//		System.out.println("max: " + max);
//		System.out.println("length: " + length);
    	int leftIndex = Arrays.binarySearch(nums, 0, max, target);
    	int rightIndex = Arrays.binarySearch(nums, max, length, target);
//		System.out.println("leftIndex: " + leftIndex);
//		System.out.println("rightIndex: " + rightIndex);
        return leftIndex >= 0 ? leftIndex : rightIndex >= 0 ? rightIndex : -1;
    }
}
