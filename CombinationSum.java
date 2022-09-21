package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		int[] input = {2,3,6,7};
		int target = 7;
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
		Arrays.sort(candidates);
		combinationSum(candidates, target, new ArrayList<>(), result);
		return result;
	}
	
	/**
	 * use backtracking to solve the problem.
	 * add every valid item into current list and update target.
	 * then same goes for the new current list.
	 * 
	 * @param candidates
	 * @param target
	 * @param curList
	 * @param result
	 */
	public static void combinationSum(int[] candidates, int target, List<Integer> curList, List<List<Integer>> result) {
		for (int i : candidates) {
			// to make sure that the result list is in ascending order
			// it can also make sure that there is no repeated list in result
			if (curList.isEmpty() || i >= curList.get(curList.size() - 1)) {
				if (target == i) {
					// in this case, have to create new list
					List<Integer> newadd = new ArrayList<>(curList);
					newadd.add(i);
					result.add(newadd);
					return;
				} else if (target > i) {
					// do not create new list in this case can save some space(48.3MB->45.3MB)
					// transfer the index of i to the next function can save some time
					curList.add(i);
					combinationSum(candidates, target - i, curList, result);
					curList.remove(curList.size() - 1);
				} else {
					// if target < i, it is impossible that there are valid item to add to curList
					return;
				}
			} else {
				continue;
			}
		}
	}
}
