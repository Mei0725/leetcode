package leetcode_test;

import java.util.Arrays;

public class InsertInterval {

	public static void main(String[] args) {
//		int[][] input = {{1,3},{6,9}};
//		int[] newInterval = {2,5};
//		int[][] input = {{0,0},{1,3},{6,8},{9,11}};
//		int[] newInterval = {0,9};
		int[][] input = {{1,3},{6,9}};
		int[] newInterval = {5,5};
		int[][] output = null;
		try {
			output = insert(input, newInterval);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * traverse intervals, if the item can merge with newInterval, merge and get the new interval
	 * otherwise, added item into the result[]
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
    	int[][] result = new int[intervals.length + 1][2];
    	int length = 0, contains = 1;
    	int i = 0;
    	int max = newInterval[1], min = newInterval[0];
    	
    	while (i < intervals.length) {
    		if (intervals[i][1] < min) {
    			result[length] = intervals[i];
    			length++;
    		} else if (intervals[i][0] > max) {
    			//  if it is the first item in the right of disjoint list, added the new interval into result
    			if (contains > 0) {
    				result[length][0] = min;
    				result[length][1] = max;
    				length++;
    				contains = 0;
    			}
    			result[length] = intervals[i];
    			length++;
    		} else {
    			// merge the new interval and item[i]
    			min = Math.min(min, intervals[i][0]);
    			max = Math.max(max, intervals[i][1]);
    			contains++;
    		}
    		i++;
    	}
    	// handle the case that new interval in the end of result[]
		if (contains > 0) {
			result[length][0] = min;
			result[length][1] = max;
			length++;
			contains = 0;
		}
    	return Arrays.copyOf(result, length);
    }

    /**
     * this solution is as same as insert, but in different way of expression
     * 
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insertTraverse(int[][] intervals, int[] newInterval) {
    	int[][] result = new int[intervals.length + 1][2];
    	if (intervals.length == 0) {
    		result[0] = newInterval;
    		return result;
    	}

    	int length = 0;
    	if (newInterval[1] < intervals[0][0]) {
    		result[0] = newInterval;
            length++;
    	}
    	boolean added = false;
    	for (int i = 0; i < intervals.length; i++) {
    		if (newInterval[0] <= intervals[i][1] && newInterval[1] >= intervals[i][0]) {
    			int min = Math.min(newInterval[0], intervals[i][0]);
    			int max = Math.max(newInterval[1], intervals[i][1]);
    			if (length == 0 || result[length - 1][1] < min) {
    				result[length][0] = min;
    				result[length][1] = max;
    				length++;
    			} else {
    				result[length - 1][0] = Math.min(min, result[length - 1][0]);
    				result[length - 1][1] = Math.max(max, result[length - 1][1]);
    			}
    		} else {
    			if (!added && newInterval[1] < intervals[i][0]) {
        			result[length] = newInterval;
        			length++;
        			added = true;
    			}
				result[length][0] = intervals[i][0];
				result[length][1] = intervals[i][1];
				length++;
    		}
    	}
    	if (newInterval[0] > result[length - 1][1]) {
			result[length] = newInterval;
			length++;
    	}
    	return Arrays.copyOf(result, length);
    }
}
