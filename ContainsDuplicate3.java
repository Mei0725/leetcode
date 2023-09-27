package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainsDuplicate3 {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int indexDiff = 2;
		int valueDiff = 2;
		boolean output = false;
		try {
			output = containsNearbyAlmostDuplicate(input, indexDiff, valueDiff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * use a sorted list win as a window to store all valid items
	 * for every item nums[j], find out the closest item nums[tmpi] in window and check nums[j] and nums[tmpi-1] and nums[tmpi]
	 * 
	 * @param nums
	 * @param indexDiff
	 * @param valueDiff
	 * @return
	 */
    public static boolean containsNearbyAlmostDuplicate0(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff == 0) return false;

        int i = 0, j = i + 1;
        // win is a sorted list
        List<Integer> win = new ArrayList<>();
        win.add(nums[i]);
        System.out.println("indexDiff: " + indexDiff);
        while(j < nums.length) {
        	// get the index of tmpi that win[tmpi] is the first item that bigger than nums[j]
        	// find out the tmpi by binary search can save some time(2368ms->1317ms)
        	// through the running time is still very long(beats 26.6%)
            int tmpl = 0, tmpr = win.size() - 1, tmpi = (tmpl + tmpr) / 2;
            while (tmpi >= 0 && tmpi <= win.size() - 1 && tmpl <= tmpr) {
                if (win.get(tmpi) < nums[j]) {
                    tmpl = tmpi + 1;
                } else if (tmpi > 0 && win.get(tmpi - 1) > nums[j]) {
                    tmpr = tmpi - 1;
                } else {
                    break;
                }
                tmpi = (tmpl + tmpr) / 2;
            }
            // handle the case that all items in win is smaller than nums[j]
            if (win.get(tmpi) < nums[j]) {
                tmpi++;
            }
            // find out tmpi one by one, it will spend too much time when win is big enough
            // int tmpi = 0;
            // while (tmpi < j - i && win.get(tmpi) < nums[j]) {
            //     tmpi++;
            // }
        //     System.out.println("win: " + win);
        // System.out.println("tmpi: " + tmpi);
            int left = nums[j] - valueDiff, right = nums[j] + valueDiff;
            if ((tmpi > 0 && win.get(tmpi - 1) >= left && win.get(tmpi - 1) <= right) 
                || (tmpi < j - i && win.get(tmpi) >= left && win.get(tmpi) <= right)) {
                    // System.out.println("win: " + win);
                    return true;
            }
            // maintain the win
            win.add(tmpi, nums[j]);
            if (j - i >= indexDiff) {
                win.remove(win.indexOf(nums[i++]));
            }
            // System.out.println("win: " + win);
            j++;
        }
        // System.out.println("win: " + win);
        return false;
    }


    /**
     * solve this problem by Bucket sort, since it uses map, the running time is very short(33ms)
     * separate items by valueDiff
     * for every item nums[i], if it is valid, then can find the linked item in the same bucket or in its neighbor
     * 
     * there is no need to worry about the cover of map: 
     * whenever there is a possibility to cover the map's item, it will be checked with the removing item and return true firstly
     * 
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
	public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
		if (valueDiff < 0)
			return false;
		// use map to store buckets to save time
		Map<Long, Long> d = new HashMap<>();
		// make sure that the bucket is not 0
		long w = (long) valueDiff + 1;
		for (int i = 0; i < nums.length; ++i) {
			long m = getID(nums[i], w);
			// check the current bucket, and its neighbor
			if (d.containsKey(m))
				return true;
			if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
				return true;
			if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
				return true;
			// maintain the bucket map
			d.put(m, (long) nums[i]);
			if (i >= indexDiff)
				d.remove(getID(nums[i - indexDiff], w));
		}
		return false;
	}

	// every bucket should have the same number of items, so 0 should be handled specially
	private static long getID(long i, long w) {
		return i < 0 ? ((i + 1) / w) - 1 : i / w;
	}
}
