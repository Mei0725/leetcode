package leetcode_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int k = 2;
		int[] output = null;
		try {
			output = topKFrequent(input, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static class Node {
        int val;
        int count;
        Node() {}; 
        Node(int val, int count) {this.val = val; this.count = count;}; 
    }

    /**
     * solve this problem by PriorityQueue
     * create a class Node, store array items by val and sort by count
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).count++;
            } else {
                map.put(nums[i], new Node(nums[i], 1));
            }
        }
        PriorityQueue<Node> queue = new PriorityQueue<Node>(map.size(), new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.count > o2.count)
                    return -1;
                else if(o1.count == o2.count)
                    return 0;
                else
                    return 1;
            }
        });
        for (Node node : map.values()) {
            queue.offer(node);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().val;
        }
        return result;
    }
    
    /**
     * this solution is similar to topKFrequent but use custom list sort to instead of PriorityQueue
     * this way would spend more time(24ms->42ms)
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentList(int[] nums, int k) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).count++;
            } else {
                map.put(nums[i], new Node(nums[i], 1));
            }
        }
        List<Node> list = new ArrayList<>(map.values());
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.count > o2.count)
                    return -1;
                else if(o1.count == o2.count)
                    return 0;
                else
                    return 1;
            }
         });
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
        	result[i] = list.get(i).val;
        }
        return result;
    }

    /**
     * this solution put the val which has the same count into list[count]
     * since count will not be very large, then get result from the last item of list and check one by one
     * also, use count as index can deal with the case that val is negative number
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentBucket(int[] nums, int k) {
        // since the range of count is fixed (0, nums), we can use buckt sort
        // count frequency
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }

        // create one bucket for each frequency
        // each bucket is a list of n for that frequency
        // NOTE: no <> after new List[]
        List<Integer>[] bucket = new List[nums.length + 1];
        int max = Integer.MIN_VALUE;
        for (int n : counts.keySet()) {
            int count = counts.get(n);
            // default vaule for object is null
            if (bucket[count] == null) bucket[count] = new ArrayList<>();
            bucket[count].add(n);
            max = Math.max(max, counts.get(n));
        }

        // scan each bucket and add to res
        int[] res = new int[k];
        int j = 0;
        for (int i = max; i >= 0; i--) {
            if (bucket[i] == null) continue;
            for (int n : bucket[i]) {
                res[j++] = n;
                if (j == k) return res;
            }
        }

        return res;
    }
}
