package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate2 {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int k = 2;
		boolean output = false;
		try {
			output = containsNearbyDuplicate(input, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * use sliding windows to solve the problem
	 * use a map win to store all valid items in window, and maintain the window whenever there is a item added
	 * use map instead of list is because map is earlier to search and remove item
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) return false;

        int i = 0, j = i + 1;
        Map<Integer, Integer> win = new HashMap<>();
        win.put(nums[i], i);
        while(j < nums.length) {
		// System.out.println("i: " + i);
		// System.out.println("j: " + j);
		// System.out.println("tmp: " + tmp);
            if (win.containsKey(nums[j])) {
                return true;
            } else if (j - i < k) {
                win.put(nums[j++], j);
            } else {
                win.put(nums[j++], j);
                win.remove(nums[i++]);
            }
        }
        return false;
    }
}
