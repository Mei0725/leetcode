package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

	/**
	 * use map to store the not-full group in size as key, and whenever there is enough members, add this group to result.
	 * 
	 * @param groupSizes
	 * @return
	 */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> group = map.getOrDefault(groupSizes[i], new ArrayList<>());
            group.add(i);
            if (group.size() >= groupSizes[i]) {
            	// if there is enough mumbers, add the group into res
            	// the if the groupSize appears again, a new list will be create in that loop
                res.add(group);
                map.remove(groupSizes[i]);
            } else {
                map.put(groupSizes[i], group);
            }
        }
        
        // put all values in map into res
        for (Integer size : map.keySet()) {
            res.add(map.get(size));
        }
        return res;
    }
}
