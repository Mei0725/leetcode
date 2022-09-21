package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

	public static void main(String[] args) {
//		int[] input = {1,2,3};
		int[] input = {1};
		List<List<Integer>> output = null;
		try {
			output = permute(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: "); 
		for (List<Integer> result : output) {
			System.out.print(result + ","); 
		}
	}

	/**
	 * for every nums[i], it can be added into every position of every result of nums[i-1]
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(Arrays.asList(nums[0]));
    	for (int i = 1; i < nums.length; i++) {
    		// have to create a new list otherwise it would return to ConcurrentModificationException
    		// (since the original list have to be removed from result)
        	List<List<Integer>> tmpRes = new ArrayList<>();
    		for (List<Integer> tmp : result) {
    			for (int add = 0; add <= tmp.size(); add++) {
    				List<Integer> newAdd = new ArrayList<>(tmp);
    				newAdd.add(add, nums[i]);
    				tmpRes.add(newAdd);
    			}
    		}
    		result = tmpRes;
    	}
        return result;
    }
}
