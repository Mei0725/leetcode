package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

	public static void main(String[] args) {
		int[] input = {-1,0,1,2,-1,-4};
//		int[] input = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
//		int[] input = {0,0,0,0};
		List<List<Integer>> output = null;
		try {
			output = threeSum(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result.length: " + output.size());
		System.out.println("result: " + output);
	}
	
	/**
	 * for i = 0 -> nums.length - 1, take the nums[i] as the target, looking for 2Sum from i+1 -> nums.length - 1
	 * the time complexity can be reduced by improve the 2Sum solution
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	Arrays.sort(nums);
		
    	for (int i = 0; i < nums.length - 1; i++) {
    		Integer target = -nums[i];
        	System.out.println("target: " + target);
    		if (i != 0 && nums[i] == nums[i - 1]) {
    			continue;
    		}
    		
    		Set<Integer> needed = new HashSet<>();
    		boolean sameAdd = false;
    		int sameNum = 0;
    		for (int j = i + 1; j < nums.length; j++) {
    			if (nums[j] * 2 == target) {
    				if (needed.contains(nums[j]) && !sameAdd) {
    					needed.remove(nums[j]);
    					result.add(Arrays.asList(nums[j], nums[j], -target));
    					sameAdd = true;
    				} else if (!sameAdd) {
    					needed.add(nums[j]);
    					sameNum = nums[j];
    				}
    			} else {
					needed.add(target - nums[j]);
    			}
    		}
    		if (!sameAdd) {
				needed.remove(sameNum);
    		}
        	System.out.println("needed: " + needed);
    		for (int j = i + 1; j < nums.length; j++) {
    			if (needed.contains(nums[j])) {
					needed.remove(nums[j]);
					needed.remove(target - nums[j]);
					result.add(Arrays.asList(nums[j], target - nums[j], -target));
		        	System.out.println("remove needed: " + needed);
    			}
    		}
    	}
    	return result;
    	/*List<List<Integer>> result = new ArrayList<>();
    	List<Integer> negative = new ArrayList<>();
    	List<Integer> zero = new ArrayList<>();
    	List<Integer> positive = new ArrayList<>();
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] < 0) {
    			negative.add(nums[i]);
    		} else if (nums[i] > 0) {
    			positive.add(nums[i]);
    		} else {
    			zero.add(i);
    		}
    	}
    	
    	if (zero.size() >= 3) {
			result.add(Arrays.asList(0, 0, 0));
    	}
    	
    	if (negative.size() > 1) {
    		Set<Integer> containsNegative = new HashSet<>();
    		Set<Integer> containsNegative1 = new HashSet<>();
        	for (int i = 0; i < negative.size(); i++) {
        		if (containsNegative.contains(negative.get(i))) {
        			continue;
        		}
        		containsNegative1 = new HashSet<>();
        		for (int j = i + 1; j < negative.size(); j++) {
            		if (containsNegative.contains(negative.get(j)) || containsNegative1.contains(negative.get(j))) {
            			continue;
            		}
        			int temp = negative.get(i) + negative.get(j);
        			if (positive.contains(-temp)) {
    					containsNegative1.add(negative.get(j));
						result.add(Arrays.asList(negative.get(i), negative.get(j), -temp));
        			}
        		}
				containsNegative.add(negative.get(i));
        	}
    	}
    	if (positive.size() > 1) {
    		Set<Integer> containsPositive = new HashSet<>();
    		Set<Integer> containsPositive1 = new HashSet<>();
        	for (int i = 0; i < positive.size(); i++) {
        		if (containsPositive.contains(positive.get(i))) {
        			continue;
        		}
        		containsPositive1 = new HashSet<>();
        		for (int j = i + 1; j < positive.size(); j++) {
            		if (containsPositive.contains(positive.get(j)) || containsPositive1.contains(positive.get(j))) {
            			continue;
            		}
        			int temp = positive.get(i) + positive.get(j);
        			if (negative.contains(-temp)) {
						containsPositive1.add(positive.get(j));
						result.add(Arrays.asList(positive.get(i), positive.get(j), -temp));
        			}
        		}
				containsPositive.add(positive.get(i));
        	}
    	}
    	if (zero.size() > 0) {
    		Set<Integer> containsPositive = new HashSet<>();
        	for (int i = 0; i < positive.size(); i++) {
        		if (containsPositive.contains(positive.get(i))) {
        			continue;
        		}
				if (negative.contains(-positive.get(i))) {
					containsPositive.add(positive.get(i));
					result.add(Arrays.asList(positive.get(i), -positive.get(i), 0));
				}
        	}
    	}
    	
    	return result;*/
    	/*List<List<Integer>> result = new ArrayList<>();
    	List<Integer> negative = new ArrayList<>();
    	List<Integer> zero = new ArrayList<>();
    	List<Integer> positive = new ArrayList<>();
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] < 0) {
    			negative.add(i);
    		} else if (nums[i] > 0) {
    			positive.add(i);
    		} else {
    			zero.add(i);
    		}
    	}
    	
    	if (zero.size() >= 3) {
			result.add(Arrays.asList(0, 0, 0));
    	}
    	
    	if (negative.size() > 1) {
    		Set<Integer> containsNegative = new HashSet<>();
    		Set<Integer> containsNegative1 = new HashSet<>();
        	for (int i = 0; i < negative.size(); i++) {
        		if (containsNegative.contains(nums[negative.get(i)])) {
        			continue;
        		}
        		containsNegative1 = new HashSet<>();
        		for (int j = i + 1; j < negative.size(); j++) {
            		if (containsNegative.contains(nums[negative.get(j)]) || containsNegative1.contains(nums[negative.get(j)])) {
            			continue;
            		}
        			int temp = nums[negative.get(i)] + nums[negative.get(j)];
    				for (int k = 0; k < positive.size(); k++) {
    					if (temp + nums[positive.get(k)] == 0) {
        					containsNegative1.add(nums[negative.get(j)]);
    						result.add(Arrays.asList(nums[negative.get(i)], nums[negative.get(j)], nums[positive.get(k)]));
    						break;
    					}
    				}
        		}
				containsNegative.add(nums[negative.get(i)]);
        	}
    	}
    	if (positive.size() > 1) {
    		Set<Integer> containsPositive = new HashSet<>();
    		Set<Integer> containsPositive1 = new HashSet<>();
        	for (int i = 0; i < positive.size(); i++) {
        		if (containsPositive.contains(nums[positive.get(i)])) {
        			continue;
        		}
        		containsPositive1 = new HashSet<>();
        		for (int j = i + 1; j < positive.size(); j++) {
            		if (containsPositive.contains(nums[positive.get(j)]) || containsPositive1.contains(nums[positive.get(j)])) {
            			continue;
            		}
        			int temp = nums[positive.get(i)] + nums[positive.get(j)];
    				for (int k = 0; k < negative.size(); k++) {
    					if (temp + nums[negative.get(k)] == 0) {
    						containsPositive1.add(nums[positive.get(j)]);
    						result.add(Arrays.asList(nums[positive.get(i)], nums[positive.get(j)], nums[negative.get(k)]));
    						break;
    					}
    				}
        		}
				containsPositive.add(nums[positive.get(i)]);
        	}
    	}
    	if (zero.size() > 0) {
    		Set<Integer> containsPositive = new HashSet<>();
    		Set<Integer> containsNegative = new HashSet<>();
        	for (int i = 0; i < positive.size(); i++) {
        		if (containsPositive.contains(nums[positive.get(i)])) {
        			continue;
        		}
        		for (int j = 0; j < negative.size(); j++) {
            		if (containsNegative.contains(nums[negative.get(j)])) {
            			continue;
            		}
					if (nums[positive.get(i)] + nums[negative.get(j)] == 0) {
						containsPositive.add(nums[positive.get(i)]);
						containsNegative.add(nums[negative.get(j)]);
						result.add(Arrays.asList(nums[positive.get(i)], nums[negative.get(j)], 0));
					}
        		}
        	}
    	}
    	
    	return result;*/
    }

}
