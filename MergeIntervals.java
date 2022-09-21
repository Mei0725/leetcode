package leetcode_test;

import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

	public static void main(String[] args) {
		int[][] input = {{1,3},{2,6},{8,10},{15,18}};
		int[][] output = null;
		try {
			output = merge(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * sort intervals firstly, and then check every item that if it should be merge into the last item of result or become a new item
	 * 
	 * @param intervals
	 * @return
	 */
    public static int[][] merge(int[][] intervals) {
    	int[][] result = new int[intervals.length][2];
    	int count = 0;
    	
    	// 2 ways to sort intervals, create Comparator spend less time than the other way(80ms VS 107ms)
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(final int[] item1, final int[] item2) {
				return item1[0] - item2[0];
			}
		});
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    	for (int i = 0; i < intervals.length; i++) {
    		boolean added = false;
    		// since intervals is sorted, the intervals[i] can only be added into the last one of the result or become a new item
    		// so we should only check the last one of result
    		for (int j = count == 0 ? 0 :count - 1; j < count; j++) {
    			if (intervals[i][0] <= result[j][1] && intervals[i][1] >= result[j][0]) {
    				added = true;
    				result[j][1] = Math.max(result[j][1], intervals[i][1]);
    				result[j][0] = Math.min(result[j][0], intervals[i][0]);
    				break;
    			}
    		}
    		if (!added) {
    			result[count][0] = intervals[i][0];
    			result[count][1] = intervals[i][1];
    			count++;
    		}
    	}
    	
    	return Arrays.copyOf(result, count);
    }
    
    /**
     * the time complexity is n^2, for each intervals[i], it should check all of the result before i
     * it can avoid wrong result such as [[2,3],[4,5],[6,7],[8,9],[1,10]], but this solution would spend too much time
     * 
     * @param intervals
     * @return
     */
    public static int[][] mergeOvertime(int[][] intervals) {
    	int[][] result = new int[intervals.length][2];
    	int count = 0;
    	
    	for (int i = 0; i < intervals.length; i++) {
        	int[][] tmp = new int[intervals.length][2];
        	int tmpCount = 0;
        	int[] addInterval = Arrays.copyOf(intervals[i], 2);
    		for (int j = 0; j < count; j++) {
    			if ((addInterval[0] <= result[j][1] && addInterval[1] >= result[j][0])
    					|| (result[j][0] <= addInterval[1] && result[j][1] >= addInterval[0])) {
    				addInterval[1] = Math.max(result[j][1], addInterval[1]);
    				addInterval[0] = Math.min(result[j][0], addInterval[0]);
    			} else {
    				tmp[tmpCount][0] = result[j][0];
    				tmp[tmpCount][1] = result[j][1];
    				tmpCount++;
    			}
    		}
			tmp[tmpCount][0] = addInterval[0];
			tmp[tmpCount][1] = addInterval[1];
			tmpCount++;
			result = tmp;
			count = tmpCount;
    	}
    	
    	return Arrays.copyOf(result, count);
    }

}
