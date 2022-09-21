package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

	public static void main(String[] args) {
		int n = 4;
		int k = 2;
		List<List<Integer>> output = null;
		try {
			output = combine(n, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by backtracking
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
    public static List<List<Integer>> combine(int n, int k) {
    	List<Integer> currList = new ArrayList<>();
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(currList);
        return combine(k, 1, n, result);
    }

    public static List<List<Integer>> combine(int k, int start, int end, List<List<Integer>> currLists) {
    	if (k == 0) {
    		return currLists;
    	}

		List<List<Integer>> result = new ArrayList<>();
    	int add = start;
    	while (add + k - 1 <= end) {
    		// since List<List<Integer>> sort the location of every List<Integer>
    		// in order to avoid to rejoin nums to lists, we must create totally new list
    		List<List<Integer>> newLists = new ArrayList<>();
            for (List<Integer> list : currLists) {
            	List<Integer> newList =  new ArrayList<>(list);
            	newList.add(add);
            	newLists.add(newList);
            }
            result.addAll(combine(k - 1, add + 1, end, newLists));
            add++;
    	}
        return result;
    }
}
