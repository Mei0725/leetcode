package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {

	/**
	 * use list-nums and map-idxMap can both get nums and index easily
	 * this problem can also be solved by only a list, but this time complexity can't be O(1)
	 * since it is impossible find out a number's index in O(1)
	 */
    List<Integer> nums;
    // key-number's value; value-index
    Map<Integer, Integer> idxMap;
    Random random;

    public InsertDeleteGetRandomO1() {
        nums = new ArrayList<>();
        idxMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (idxMap.containsKey(val)) {
            return false;
        }

        idxMap.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) {
            return false;
        }

        // if there is no idxMap, this operation can be replaced by nums.indexOf
        // but get index by map can save lots of time(30ms->215ms)
        int idx = idxMap.get(val);
        int lastIdx = nums.size() - 1;
        if (idx != lastIdx) {
        	// replace the last value and removed value
            int lastVal = nums.get(lastIdx);
            nums.set(idx, lastVal);
            idxMap.put(lastVal, idx);
        }
        
        nums.remove(lastIdx);
        idxMap.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }

}
