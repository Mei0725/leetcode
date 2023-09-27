package leetcode_test;

import java.util.List;

public class Triangle {
	
	/**
	 * solve the problem by dp.
	 * use int[] results to store the min sum which must include results[i],
	 * and calculate results from bottom to the top.
	 * 
	 * @param triangle
	 * @return
	 */
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        // store the min sum in line i
        int[] results = new int[height];
        for (int i = height - 1; i >= 0; i--) {
            List<Integer> line = triangle.get(i);
            for (int j = 0; j < i + 1; j++) {
                if (i == height - 1) {
                	// handle the last line
                    results[j] = line.get(j);
                } else {
                	// for every item, it should only consider about the min result of i and i+1 in the previous line
                	// since the results[i] in the previous line will not be used after item i in this line, it can be covered
                	// otherwise, there should be int[] to store results in this line
                    results[j] = line.get(j) + Math.min(results[j], results[j + 1]);
                }
            }
        }
        return results[0];
    }
}
