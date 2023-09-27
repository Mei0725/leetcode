package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FindKPairsWithSmallestSums {

	/**
	 * use heap/PriorityQueue to solve problem.
	 * use PriorityQueue queue to store the nearest pairs of the current one
	 * and use set to store the marked pairs(k is smeller than length of nums1 and nums2)
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int l1= nums1.length, l2 = nums2.length;
        // l1*l2 may larger than Integer.MAX_VALUE
        if ((long)l1 * l2 < k) {
            k = l1 * l2;
        }
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>((p1, p2) -> ((nums1[p1.index] + nums2[p1.height]) - (nums1[p2.index] + nums2[p2.height])));
        // the index 0,0 must be the first pair
        queue.offer(new Pair(0, 0));
        // use set because k is much smaller than length of nums1 and nums2
        // use boolean[][] will cause memory limit exceeded
        Set<Pair> marked = new HashSet<>();

        List<List<Integer>> res = new ArrayList<>();
        while (k > 0) {
            Pair tmp = queue.poll();
            int i1 = tmp.index, i2 = tmp.height;
            res.add(Arrays.asList(nums1[i1], nums2[i2]));
            Pair newP = new Pair(i1 + 1, i2);
            if (i1 + 1 < l1 && !marked.contains(newP)) {
                queue.offer(newP);
                marked.add(newP);
            }
            newP = new Pair(i1, i2 + 1);
            if (i2 + 1 < l2 && !marked.contains(newP)) {
                queue.offer(newP);
                marked.add(newP);
            }
            k--;
        }
        // System.out.println("result: " + res);
        return res;
    }
}
