package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

	public static void main(String[] args) {
		int[] input = {2,2,2,2,2};
		int target = 8;
//		int[] input = {1000000000,1000000000,1000000000,1000000000};
//		int target = -294967296;
//		int[] input = {-2,-1,-1,1,1,2,2};
//		int target = 0;
		List<List<Integer>> output = null;
		try {
			output = fourSum(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * the solution is similar to 3Sum, but the time complexity should be n^3
	 * assume that nums[i] and nums[j] is in the result list, and try to find out the remaining two nums
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
        	return new ArrayList<>();
        }
        
        // we can also deal with the repeated case using Set(since the result list must be in positive order)
        // but Set will spend much more time to remove weight(190ms VS 27ms)
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
        	// deal with the repeated case
        	if (i != 0 && nums[i] == nums[i - 1]) {
        		continue;
        	}
        	for (int j = i + 1; j < nums.length - 2; j++) {
        		// to deal with the repeated case
            	if (j != i + 1 && nums[j] == nums[j - 1]) {
            		continue;
            	}
        		int m = j + 1, n = nums.length - 1;
        		while(m < n) {
        			// to deal with the case just like {1000000000,1000000000,1000000000,1000000000}, 
        			// in which the sum of these 4 nums is bigger than Integer.MAX_VALUE
        			long tmpSum = (long)nums[i] + (long)nums[j] + (long)nums[m] + (long)nums[n];
        			if (tmpSum < target) {
        				m++;
        			} else if (tmpSum > target) {
        				n--;
        			} else {
        				// since maybe there are more then one result list in the nums[i] and nums[j],
        				// can't break this for-cycle immediately
        				result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
        				m++;
        				n--;
        				// to deal with the repeated case that nums[m-1]==nums[m]
        				while(m < nums.length && nums[m - 1] == nums[m]) {
        					m++;
        				}
        				while(n > j && nums[n + 1] == nums[n]) {
        					n--;
        				}
        			}
        		}
        	}
        }
        return result;
    }

}
