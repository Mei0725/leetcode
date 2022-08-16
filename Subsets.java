package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		int[] input = {1,2,3};
//		int[] input = {0};
		List<List<Integer>> output = null;
		try {
			output = subsetsBit(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * translate the result list to 0/1 list, and 1 means the nums in this position contain in this int list
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsetsBit(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	for(int i = 0; i < Math.pow(2, nums.length); i++) {
    		String bit = Integer.toString(i, 2);
    		List<Integer> tmp = new ArrayList<>();
    		int num0 = nums.length - bit.length();
    		for(int j = 0; j < bit.length(); j++) {
    			if (bit.charAt(j) == '1') {
    				tmp.add(nums[num0 + j]);
    			}
    		}
    		result.add(tmp);
    	}
    	return result;
	}
	
	/**
	 * use backtracking to deal with this problem
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	if (nums.length == 0) {
    		return result;
    	}
    	return subsets(nums, result);
    }
    
    public static List<List<Integer>> subsets(int[] nums, List<List<Integer>> preResult) {
    	if (nums.length == 0) {
    		return preResult;
    	}
    	List<List<Integer>> result = new ArrayList<>();
    	if (preResult.size() == 0) {
    		List<Integer> tmpAdd = new ArrayList<>();
    		List<Integer> tmpNoAdd = new ArrayList<>();
    		tmpAdd.add(nums[0]);
    		result.add(tmpAdd);
    		result.add(tmpNoAdd);
    	} else {
        	for (List<Integer> tmp : preResult) {
        		List<Integer> tmpAdd = new ArrayList<>(tmp);
        		List<Integer> tmpNoAdd = new ArrayList<>(tmp);
        		tmpAdd.add(nums[0]);
        		result.add(tmpAdd);
        		result.add(tmpNoAdd);
        	}
    	}
    	return subsets(Arrays.copyOfRange(nums, 1, nums.length), result);
    }
}
