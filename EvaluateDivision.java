package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

	/**
	 * question about graph.
	 * use map to store the relationship about 2 items, and then find out result by DFS.
	 * 
	 * @param equations
	 * @param values
	 * @param queries
	 * @return
	 */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    	// store the relationship about 2 items, use map to make searching easier
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String s1 = equations.get(i).get(0), s2 = equations.get(i).get(1);
            Map<String, Double> val1 = map.getOrDefault(s1, new HashMap<>()), val2 = map.getOrDefault(s2, new HashMap<>());
            val1.put(s2, values[i]);
            // for every equation, there is 2 relationship
            // store both of them to save time about searching
            val2.put(s1, 1 / values[i]);
            map.put(s1, val1);
            map.put(s2, val2);
        }
        // System.out.println("map: " + map);

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0), s2 = queries.get(i).get(1);
            Set<String> used = new HashSet<>();
            // find out the result for every query
            res[i] = getValue(map, s1, s2, used);
        }
        return res;
    }

    /**
     * find out the result of s/e for map.
     * 
     * @param map
     * @param s
     * @param e
     * @param used
     * @return
     */
    public double getValue(Map<String, Map<String, Double>> map, String s, String e, Set<String> used) {
        if (used.contains(s) || !map.containsKey(s)) {
            return -1;
        }

        used.add(s);
        Map<String, Double> vals = map.get(s);
        if (vals.containsKey(e)) {
            return vals.get(e);
        }
        for (String key : vals.keySet()) {
            double val = getValue(map, key, e, used);
            if (val != -1) {
                return vals.get(key) * val;
            }
        }
        return -1;
    }
}
