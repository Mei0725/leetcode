package leetcode_test;

import java.util.PriorityQueue;

public class TheKWeakestRowsInAMatrix {

	/**
	 * use priority queue to solve problem.
	 * 
	 * store the int[2]{index, number-of-soldier} into queue, and sort by rule.
	 * then poll the first k items in this queue.
	 * 
	 * to save time and space, we can also add the int[2] into int[][2] and sort it after putting all items into it, and output the first k items.
	 * 
	 * @param mat
	 * @param k
	 * @return
	 */
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((i1, i2) -> ((i1[1] == i2[1]) ? (i1[0] - i2[0]) : (i1[1] - i2[1])));
        for (int i = 0; i < mat.length; i++) {
            int soldiers = 0;
            for (int j = 0; j < mat[i].length; j++) {
                soldiers += mat[i][j];
            }
            queue.offer(new int[]{i, soldiers});
        }

        int[] res = new int[k];
        int i = 0;
        while (i < k) {
            res[i++] = queue.poll()[0];
        }
        return res;
    }
}
