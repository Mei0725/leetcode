package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

	public static void main(String[] args) {
//		int[] input = {10,1,2,7,6,1,5};
//		int target = 8;
		int[] input = {2,5,2,1,2};
		int target = 5;
		List<List<Integer>> output = null;
		try {
			output = combinationSum(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result:");
		for (List<Integer> list : output) {
			System.out.print(list + ",");
		}
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		// to make sure there is no repeated result lists, candidates should be sorted first
		Arrays.sort(candidates);
		combinationSum(0, candidates, target, new ArrayList<>(), result);
		return result;
	}
	
	/**
	 * use backtracking to solve the problem.
	 * handle the items which have the same value
	 * start means the start index in candidates which we should consider in this function
	 * 
	 * @param start
	 * @param candidates
	 * @param target
	 * @param curList
	 * @param result
	 */
	public static void combinationSum(int start, int[] candidates, int target, List<Integer> curList, List<List<Integer>> result) {
		if (start >= candidates.length) {
			return;
		}
		// to find out there are how many same value items
		// index is the index of the first item whose value is different from candidates[start]
		int index = start + 1;
		while (index < candidates.length) {
			if (candidates[start] != candidates[index]) {
				break;
			}
			index++;
		}
		
		int val = candidates[start];
		int newTar = target;
		// handle the case that those result lists don't contain val
		combinationSum(index, candidates, newTar, curList, result);
		for (int i = start; i < index; i++) {
			curList.add(val);
			newTar = newTar - val;
			if (newTar == 0) {
				// if newTar == 0, it means it is one of the result lists
				result.add(new ArrayList<>(curList));
				break;
			} else if (newTar > val) {
				// when newTar > val, it means that it is possible to create result list using the rest of candidates
				combinationSum(index, candidates, newTar, curList, result);
			} else if (newTar == val) {
				// when newTar == val, continue so that it can be handled in the next cycle if it is existed
				continue;
			} else {
				break;
			}
		}
		// before leaving this backtracking, remove all of val when added in previous cycle so that they will not influence others
		int removeIndex = curList.indexOf(val);
		while (removeIndex != -1) {
			curList.remove(removeIndex);
			removeIndex = curList.indexOf(val);
		}
	}
}
