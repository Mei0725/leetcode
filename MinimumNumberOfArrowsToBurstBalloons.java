package leetcode_test;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {

	/**
	 * check points from min to max, and see their overlapping as the range to shots.
	 * 
	 * @param points
	 * @return
	 */
    public int findMinArrowShots(int[][] points) {
        // Arrays.sort(points, (a, b) -> (a[0] - b[0]));
    	// can't use the previous code because there may be mistake when a[0]-b[0]>Integer.MAX_VALUE
        Arrays.sort(points, (a, b) -> {
            if (a[0] > b[0]) {
                return 1;
            } else if (a[0] < b[0]) {
                return -1;
            }
            return 0;
        });
        
        // store range of shots instead of index
        // otherwise there may be mistake when input is like:
        // [[9,10],[0,9],[0,6]]
        int res = 1, xMin = points[0][0], xMax = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // System.out.print("," + points[i][0]);
            // System.out.println("i:" + points[i][0]);
            // System.out.println("tmp:" + points[tmp][1]);
            if (points[i][0] > xMax) {
                res++;
                xMin = points[i][0];
                xMax = points[i][1];
            } else {
                xMin = Math.max(xMin, points[i][0]);
                xMax = Math.min(xMax, points[i][1]);
            }
        }
        return res;
    }
}
