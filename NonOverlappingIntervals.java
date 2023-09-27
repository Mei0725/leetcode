package leetcode_test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class NonOverlappingIntervals {

	/**
	 * solve by dp.
	 * use res[][] to store the number of intervals gets, res[i][0] means intervals[i] do not contains in,
	 * and res[i][1] means contains in.
	 * then: res[i][0]=max(res[i-1][0],res[i-1][1])
	 * 		 res[i][1]=max(res[j][0],res[j][i]), j is the nearest interval that do not contains in i.
	 * 
	 * @param intervals
	 * @return
	 */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int length = intervals.length;
        int[][] res = new int[length][2];
        res[0][0] = 0;
        res[0][1] = 1;
        for (int i = 1; i < length; i++) {
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1]);
            // find out the nearest tmp make tmp and i do not contains each other.
            // it can also be solved by PriorityQueue
            int tmp = i - 1;
            while (tmp >= 0) {
                if (intervals[tmp][1] <= intervals[i][0]) {
                    break;
                }
                tmp--;
            }
            if (tmp < 0) {
                res[i][1] = 1;
            } else {
                res[i][1] = Math.max(res[tmp][0], res[tmp][1]) + 1;
            }
        }
        return length - Math.max(res[length - 1][0], res[length - 1][1]);
    }
    
    /**
     * similar to eraseOverlapIntervals, but get j by PriorityQueue.
     * 
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervalsByPriority(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int length = intervals.length;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(length, (a, b) -> (a[0] - b[0]));
        int[][] res = new int[length][2];
        res[0][0] = 0;
        res[0][1] = 1;
        queue.add(new int[]{intervals[0][1], 0});
        int tmp = -1;
        for (int i = 1; i < length; i++) {
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1]);
            while (!queue.isEmpty()) {
                int[] pair = queue.peek();
                if (pair[1] < tmp) {
                    queue.poll();
                    continue;
                } else if (pair[0] <= intervals[i][0]) {
                    tmp = pair[1];
                    queue.poll();
                } else {
                    break;
                }
            }
            if (tmp < 0) {
                res[i][1] = 1;
            } else {
                res[i][1] = Math.max(res[tmp][0], res[tmp][1]) + 1;
            }
            queue.add(new int[]{intervals[i][1], i});
        }
        return length - Math.max(res[length - 1][0], res[length - 1][1]);
    }
    
    /**
     * solve by sort by intervals[i][1]
     * for intervals, when it is sorted by end, then a interval only have 2 case:
     * 1.can added into the previous gotten intervals and update it;
     * 2.can not add into previous intervals;
     * 
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals1(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prev = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        return n - count;
    }
}
