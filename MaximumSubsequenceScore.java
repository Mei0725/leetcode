package leetcode_test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumSubsequenceScore {

	/**
	 * in this problem, for every min in nums2, find out the max-k items in nums1, then this is the max sum for min
	 * then remove the min in nums2 and the linked item in nums1, check the next min in nums2 and max-k items in nums1,
	 * until there is no enough items left
	 * 
	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
    public long maxScore(int[] nums1, int[] nums2, int k) {
    	// use map to easily find out the linked relation between nums1 and nums2
    	// since there may be same value in nums1 and nums2, the value of map must be Set/Queue
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            Queue<Integer> tmp = map.getOrDefault(nums2[i], new LinkedList<>());
            tmp.add(nums1[i]);
            map.put(nums2[i], tmp);
        }

        // sort nums1 and nums2 so that it is easier to find out the max items in nums1 and min item in nums2
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // use map1 to store the items' index in nums1 so that it is easier to remove items
        Map<Integer, Queue<Integer>> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            Queue<Integer> tmp = map1.getOrDefault(nums1[i], new LinkedList<>());
            tmp.add(i);
            map1.put(nums1[i], tmp);
        }
            // System.out.println("map: " + map);
            // System.out.println("map1: " + map1);
        // remove is the amount of removed items
        // sum store the sum of max items in nums1 so that it will not be calculate every loop
        // sumNum is the amount of items in sum, to make sure if sum needs added new items
        // check1 is the checked index in nums1, to mark what is the next item in nums1 should be added into sum
        // score is used to store result
        int remove = 0, sumNum = 0, check1 = nums1.length - 1;
        long score = -1, sum = 0;
        while (remove + k <= nums1.length) {
            int min = nums2[remove];
            // check and recalculate sum
            while (sumNum < k && check1 >= 0) {
                if (nums1[check1] != -1) {
                    sum += nums1[check1];
                    sumNum++;
                }
                check1--;
            }
            // System.out.println("check1: " + check1);
            // System.out.println("sum: " + sum);
            // System.out.println("min: " + min);
            // if check1<0, it means there is no enough items in nums1,
            // so break at this place can save the time to remove items
            if (check1 < 0){
                if (sumNum == k) {
                    score = Math.max(score, min * sum);
                }
                break;
            }
            score = Math.max(score, min * sum);

            // remove2 is the value that this time it should be removed
            // remove1 is the items in nums1 which are linked to remove2
            int remove2 = nums2[remove];
            Queue<Integer> remove1 = map.get(remove2);
            // there may be more than one item should be removed
            remove += remove1.size();
            for (Integer n1 : remove1) {
                Queue<Integer> tmp1 = map1.get(n1);
                int i1 = tmp1.poll();
                // check if the removed item in nums1 contains in sum
                if (i1 > check1) {
                    sum -= nums1[i1];
                    sumNum--;
                }
                nums1[i1] = -1;
            }
            // System.out.println("sum: " + sum);
            // System.out.println("-------------------------");
        }
        return score;
    }

    /**
     * solve by priority queue.
     * PriorityQueue pq is used to get the min item in nums2
     * int[][] ess is sorted by nums1 from large one to small one
     * make sure that the k items in nums1 is the largest items we can get, 
     * and whenever a new couple is added, remove the min item in nums2
     * 
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScoreByPriorityQueue(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        // ess is used to easily get the min items in nums2
        int[][] ess = new int[n][2];
        for (int i = 0; i < n; ++i)
            ess[i] = new int[] {nums2[i], nums1[i]};
        Arrays.sort(ess, (a, b) -> b[0] - a[0]);
        //pg is used to store the k-max items in nums1
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = 0, sumS = 0;
        for (int[] es : ess) {
        	// since ess is sorted from large to small, the current es[0] must be smallest one in nums2 of the list
            pq.add(es[1]);
            sumS = (sumS + es[1]);
            // remove the smallest one in k+1 items in nums1
            if (pq.size() > k) sumS -= pq.poll();
            if (pq.size() == k) res = Math.max(res, (sumS * es[0]));
        }
        return res;
    }

    /**
     * solve the problem by iteration
     * this solution checks all legal cases so it easily become overtime
     * 
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScoreOverTime(int[] nums1, int[] nums2, int k) {
        return maxScore(nums1, nums2, 0, k, 0, 1000000);
    }

    public long maxScore(int[] n1, int[] n2, int start, int k, long sum, int min) {
        if (k == 0) {
            return sum * min;
        }

        long score = 0;
        for (int i = start; i <= n1.length - k; i++) {
            score = Math.max(score, maxScore(n1, n2, i + 1, k - 1, sum + n1[i], Math.min(min, n2[i])));
        }
        return score;
    }
}
