package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TimeNeededToInformAllEmployees {
	
	/**
	 * use Depth-First Search
	 * use a map to get leaves from manager earlier, then calculate time from leaves to root
	 * 
	 * @param n
	 * @param headID
	 * @param manager
	 * @param informTime
	 * @return
	 */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // key is manager and value is it's children
        Map<Integer, Set<Integer>> linked = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Set<Integer> leaves = linked.getOrDefault(manager[i], new HashSet<>());
            leaves.add(i);
            linked.put(manager[i], leaves);
        }

        return numOfMin(headID, informTime, linked);
    }

    public int numOfMin(int root, int[] informTime, Map<Integer, Set<Integer>> linked) {
        Set<Integer> leaves = linked.get(root);
        if (leaves == null) {
            return 0;
        }
        // get the max time that root's children should spend to pass information
        int time = 0;
        for (Integer leaf : leaves) {
            time = Math.max(time, numOfMin(leaf, informTime, linked));
        }
        // plus the time spend in passing information to children
        time += informTime[root];
        return time;
    }

    /**
     * it use the similar way as numOfMinutes
     * but it don't use map to store the relationship of managers and leaves, 
     * instead, there is a map to store the result of every node, 
     * if there is no result, calculate the time from root and save it to map
     * 
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutesOP(int n, int headID, int[] manager, int[] informTime) {
    	// it is the time should be spend to pass message to the key
        Map<Integer, Integer> times = new HashMap<>();
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, dfs(i, manager, informTime, times));
        }
        
        return max;
    }

    private int dfs(int e_id, int[] manager, int[] informTime, Map<Integer, Integer> times) {
        if(manager[e_id] == -1) return 0;
        
        if(times.containsKey(e_id)) return times.get(e_id);
        
        times.put(e_id, informTime[manager[e_id]] + dfs(manager[e_id], manager, informTime, times));
		
        return times.get(e_id);
    } 
}
