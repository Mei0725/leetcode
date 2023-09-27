package leetcode_test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElementInAStream {
	
	/**
	 * solve the problem by int[]
	 * 
	 * since it will take much time when update new val, this solution will spend a very long time(74ms)
	 */
    int k;
    //  to save the min size of nums
    int[] nums;

    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.nums = new int[k];
        int length = nums.length;
        // handle the case that nums.length=0
        if (length == 0) {
            Arrays.fill(this.nums, Integer.MIN_VALUE);
            return;
        }
        Arrays.sort(nums);
        int max = Math.min(k, length);
        int i = 0;
        for (i = 0; i < max; i++) {
            this.nums[i] = nums[length - i - 1];
        }
        // handle the case that nums.length is smaller than k
        // the min value of nums.length is k-1(otherwise there will be a mistake), we should only init nums[k-1]'s value
        if (max < k) {
            this.nums[k - 1] = Integer.MIN_VALUE;
        }
    }
    
    public int add(int val) {
        if (val <= nums[k - 1]) {
            return nums[k - 1];
        }
        int i = 0;
        for (i = 0; i < k; i++) {
            if (nums[i] < val) {
                break;
            }
        }
        int tmp = nums[i];
        while (i < k) {
            tmp = nums[i];
            nums[i] = val;
            val = tmp;
            i++;
        }
        return nums[k - 1];
    }


    /**
     * solve the problem by heap.
     * 
     * this solution spends less time than solution by int[](12ms)
     */
    PriorityQueue<Integer> queue;
    int size;

    public KthLargestElementInAStream(int k, int[] nums, int heap) {
        this.size = k;
        this.queue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                if (n1 < n2) {
                    return -1;
                } else if (n1 > n2) {
                    return 1;
                }
                return 0;
            }
        });
        if (nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }
    
    public int addByHeap(int val) {
        if (queue.size() == size) {
            if (val > queue.peek()) {
                queue.poll();
                queue.offer(val);
            }
        } else {
            queue.offer(val);
        }
        return queue.peek();
    }
}
