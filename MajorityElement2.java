package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement2 {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		List<Integer> output = null;
		try {
			output = majorityElement(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * simple solution, store values in map to count
	 * 
	 * @param nums
	 * @return
	 */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int majority = nums.length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], tmp);
            if (tmp > majority && !result.contains(nums[i])) {
                result.add(nums[i]);
                // this check can save some time(17ms->15ms)
                if (result.size() >= 2) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * since every result appear more than n/3 times, it means that for result i in sorted array, nums[i]==nums[i+n/3]
     * there are also some check to reduce time
     * compare to majorityElement, it spend less time even when there is no special check(17ms->13ms)
     * 
     * @param nums
     * @return
     */
    public static List<Integer> majorityElementBySort(int[] nums) {
    	// sort array
        Arrays.sort(nums);
        int tmp = nums[0], majority = nums.length / 3;
        int i = 1;
        List<Integer> result = new ArrayList<>();
        // check if nums[0] is in the result
        if (nums[0] == nums[majority]) {
            result.add(nums[0]);
            // reduce the time to check nums[i->n/3]
            i += majority;
        }
        while (i < nums.length - majority) {
        	// only check the different nums, so continue when there is the same num
            if (tmp == nums[i]) {
                i++;
                continue;
            }
            tmp = nums[i];
            if (nums[i] == nums[i + majority]) {
                result.add(nums[i]);
                // the max size of result is 2, so when result.size is 2, we can break immediately
                // this check can save a lot of time(13ms->2ms)
                if (result.size() >= 2) {
                    break;
                }
                i += majority;
            }
            i++;
        }
        return result;
    }
}
