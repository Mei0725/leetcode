package leetcode_test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SlidingWindowMaximum {
	
	/**
	 * solve by priority queue
	 * use queue to store the current items in window, and some items in front of window
	 * in order to save time, the items in front of window will not be removed immediately,
	 * but use a map to store the items have not removed, and update the window if the max value should be removed
	 * 
	 * this solution will spend more time but less space.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
    public int[] maxSlidingWindowByPriorityQueue(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Map<Integer, Integer> remove = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (i1 > i2) {
                    return -1;
                } else if (i1 < i2) {
                    return 1;
                }
                return 0;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        result[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            int tmpCount = remove.getOrDefault(nums[i - k], 0) + 1;
            remove.put(nums[i - k], tmpCount);
            queue.offer(nums[i]);
            int max = queue.poll();
            while (queue.size() > k - 1 && remove.containsKey(max)) {
                tmpCount = remove.get(max);
                if (tmpCount == 1) {
                    remove.remove(max);
                } else {
                    remove.put(max, tmpCount - 1);
                }
                max = queue.poll();
            }
            queue.offer(max);
            result[i - k + 1] = queue.peek();
        }
        return result;
    }
    
    /**
     * it is a solution use priority queue and sliding windows.
     * create a new class Num to store the value and index of items in nums.
     * 
     * this solution spend less time(since it should not maintain the remove map),
     * and similar space with maxSlidingWindowByPriorityQueue
     *
     */
	// To keep the track of number and index in nums array
    class Num {
        int data;
        int index;
        public Num(int data, int index){
            this.data = data;
            this.index = index;
        }
    }
    
    public int[] maxSlidingWindowByPriorityQueue1(int[] nums, int k) {
        Queue<Num> queue = new PriorityQueue<>((a,b) -> Integer.compare(b.data, a.data));
        int left = 0;
        int right = 0;
        int[] ans = new int[nums.length - k + 1];
        while(right < nums.length){
			// Add elements in queue if window size is less than k 
            if(right-left < k){
                queue.add(new Num(nums[right], right));
                right++;
            } 
			// If reached at window size k, check the largest number
            if(right-left == k) {
                ans[left++] = queue.peek().data;
            }
			// Remove the highest number if goes out of window
            while(!queue.isEmpty() && queue.peek().index < left){
                queue.poll();
            }
        }
        return ans;
    }
    
    /**
     * solve by monotonic queue
     * use monotonic queue to store the max items in window, 
     * every time before add new item, remove the last items in queue which is less than new value,
     * so that the first one in the queue is always the max one in this window.
     * 
     * use less time than priority queue and more space.
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowByMonotonicQueue(int[] nums, int k) {
		int n = nums.length;
		int[] out = new int[n - k + 1];
		// the index of out(result)
		int windowIdx = 0;

		// store the index
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

		for (int end = 0; end < n; end++) {
			// clean first
			if (deque.size() > 0 && deque.peekFirst() <= end - k) {
				deque.removeFirst();
			}
			// remove the item less than end
			while (deque.size() > 0 && nums[deque.peekLast()] <= nums[end]) {
				deque.removeLast();
			}
			// add new
			deque.addLast(end);

			// add new max
			if (end >= k - 1) {
				out[windowIdx] = nums[deque.peekFirst()];
				windowIdx++;
			}
		}
		return out;
	}

    /**
     * solve by dp
     * use 2 int[nums.length], left and right to store the max value in very k items
     * left checks from the left side and right checks from the right side
     * so that for every window[i->j], right[i] is the max value in [i->nk] and left[j] is the max value in [nk->j]
     * 
     * this solution will spend less time(there is no need to sort) but more space(2 int[])
     * 
     * @param nums
     * @param k
     * @return
     */
	public int[] maxSlidingWindowByDp(int[] nums, int k) {
		// assume nums is not null
		if (nums.length == 0 || k == 0) {
			return new int[0];
		}
		int n = nums.length;
		int[] result = new int[n - k + 1]; // number of windows

		// left & right
		int[] left = new int[n];
		int[] right = new int[n];
		left[0] = nums[0]; // init
		right[n - 1] = nums[n - 1];

		for (int i = 1; i < n; ++i) {
			// left
			if (i % k == 0)
				left[i] = nums[i];
			else
				left[i] = Math.max(left[i - 1], nums[i]);
			// right
			int j = n - i - 1;
			if (j % k == (k - 1))
				right[j] = nums[j];
			else
				right[j] = Math.max(right[j + 1], nums[j]);
		}

		// dp
		for (int i = 0, j = i + k - 1; j < n; ++i, ++j) {
			result[i] = Math.max(right[i], left[j]);
		}

		return result;
	}
}
