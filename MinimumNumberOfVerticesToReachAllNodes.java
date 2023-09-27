package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfVerticesToReachAllNodes {
	
	/**
	 * for every vertex, if it is the end of any edge, then there must be other vertex can reach it,
	 * so collect all ends of edges and then the other vertices must be the results
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
    	// mark if a vertex is the end of any edge
    	// this part of function can also use Set but boolean[] will spend less time and space
        boolean[] ends = new boolean[n];
        for (int i = 0; i < edges.size(); i++) {
            ends[edges.get(i).get(1)] = true;
        }
        // find out all vertices that are not end of any edge
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!ends[i]) {
                results.add(i);
            }
        }
        return results;
    }
}
