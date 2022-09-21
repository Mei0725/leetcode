package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Permutations2 {

	public static void main(String[] args) {
//		int[] input = {1,2,3};
		int[] input = {1,1,2};
		List<List<Integer>> output = null;
		try {
			output = permuteUnique(input);
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
	 * use backtracking to solve the problem.
	 * for the duplicates i, add from count(1 -> count) i into currList, and make sure the next item is not i
	 * transfer int[] to List can save lots of time(18ms -> 4ms) and space(52.7MB-> 42.8MB)
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	// sort arrays first to make sure it has put duplicates together
    	Arrays.sort(nums);
    	permuteUnique(new ArrayList<>(), Arrays.stream(nums).boxed().collect(Collectors.toList()), result);
    	return result;
    }
    
    public static void permuteUnique(List<Integer> currList, List<Integer> nums, List<List<Integer>> result) {
    	if (nums.size() == 0) {
    		result.add(new ArrayList<>(currList));
    		return;
    	}
    	
    	int i = 0, j = i + 1;
    	while (j <= nums.size()) {
    		while (j < nums.size() && nums.get(i) == nums.get(j)) {
    			j++;
    		}
    		
    		// ignore this cycle when nums[i] equal to currList's last one(this case will be handled in the cycle out of it)
    		if (currList.size() != 0 && currList.get(currList.size() - 1) == nums.get(i)) {
    			i = j;
    			j = i + 1;
    			continue;
    		}

//    		System.out.println("i: " + i); 
//    		System.out.println("j: " + j); 
    		int val = nums.get(i);
    		int end = i + 1;
    		while (end <= j) {
    			currList.add(nums.get(i));
//        		System.out.println("currList: " + currList); 
    			nums.remove(i);
    			// when nums is int[], create new array in this way
    			// newNums = IntStream.concat(Arrays.stream(Arrays.copyOfRange(nums, 0, i)), Arrays.stream(Arrays.copyOfRange(nums, end, nums.length))).toArray();
    	    	permuteUnique(currList, nums, result);
    	    	end++;
    		}
    		// restore the currList and nums
    		end = i + 1;
    		while (end <= j) {
    			currList.remove(currList.size() - 1);
    			nums.add(val);
    			end++;
    		}
    		nums.sort(null);
			i = j;
			j = i + 1;
    	}
    }

	/**
	 * a simple solution as same as Permutations, but use HashSet to weight removal
	 * 
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> permute(int[] nums) {
    	Set<List<Integer>> result = new HashSet<>();
    	result.add(Arrays.asList(nums[0]));
    	for (int i = 1; i < nums.length; i++) {
    		// have to create a new list otherwise it would return to ConcurrentModificationException
    		// (since the original list have to be removed from result)
    		Set<List<Integer>> tmpRes = new HashSet<>();
    		for (List<Integer> tmp : result) {
    			for (int add = 0; add <= tmp.size(); add++) {
    				List<Integer> newAdd = new ArrayList<>(tmp);
    				newAdd.add(add, nums[i]);
    				tmpRes.add(newAdd);
    			}
    		}
    		result = tmpRes;
    	}
        return new ArrayList<>(result);
    }
}
