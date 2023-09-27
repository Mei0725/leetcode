package leetcode_test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindKthLargest {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int k = 2;
		int output = -1;
		try {
			output = findKthLargestByPriorityQueue(input, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static int findKthLargestByPriorityQueue(int[] nums, int k) {
    	// @Override function compare can reduce some time
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2)
                    return 1;
                else if(o1 == o2)
                    return 0;
                else
                    return -1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
        	queue.offer(nums[i]);
        }
        int i = 0;
        while (i < k - 1) {
        	queue.poll();
//    		System.out.println("poll: " + queue.poll());
        	i++;
        }
        return queue.poll();
    }

    /**
     * use 2 int[] to store the nums < current value and nums > current value
     * and the int same is to store the count of items == current value
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int[] larger = new int[nums.length];
        int[] smaller = new int[nums.length];
        // tmp is the current int[]
        int[] tmp = nums;
        // res is the current value, usually assign the value tmp[0]
        // size1 is the valid size of larger, and size2 is the valid size of smaller
        // same is the count of nums which is == res
        // length is the valid size of tmp
        int res, size1, size2, same, length = tmp.length;
        while (true) {
            size1 = 0; size2 = 0; same = 1;
            res = tmp[0];
		// System.out.println("res: " + res);
		// System.out.println("k: " + k);
            for (int i = 1; i < length; i++) {
                if (tmp[i] > res) {
                    larger[size1++] = tmp[i];
                } else if (tmp[i] < res) {
                    smaller[size2++] = tmp[i];
                } else {
                    same++;
                }
            }
		// System.out.println("size1: " + size1);
		// System.out.println("size2: " + size2);
		// System.out.println("same: " + same);
            if (size1 >= k) {
                tmp = larger;
                length = size1;
            } else if (size1 + same < k) {
                tmp = smaller;
                length = size2;
                k = k - size1 - same;
            } else {
                break;
            }
        }
        return res;
    }
    
    /**
     * this solution divide nums into 2 parts according to the last value of current nums
     * swap all items <= last value into the front of nums instead of creating new int[] to save space
     * 
     * @param nums
     * @param k
     * @return
     */
	public static int findKthLargestON(int[] nums, int k) {
		return quickSelect(nums, 0, nums.length - 1, k);
	}

	static int quickSelect(int[] nums, int low, int high, int k) {
		// pivot is the count of nums which <= nums[high]
		// after the following operation, all nums <= nums[high] will be swapped to the left of pivot
		int pivot = low;

		// use quick sort's idea
		// put nums that are <= pivot to the left
		// put nums that are > pivot to the right
		for (int j = low; j < high; j++) {
			if (nums[j] <= nums[high]) {
				swap(nums, pivot++, j);
			}
		}
		swap(nums, pivot, high);

		// count the nums that are > pivot from high
		int count = high - pivot + 1;
		// pivot is the one!
		if (count == k)
			return nums[pivot];
		// pivot is too small, so it must be on the right
		if (count > k)
			return quickSelect(nums, pivot + 1, high, k);
		// pivot is too big, so it must be on the left
		return quickSelect(nums, low, pivot - 1, k - count);
	}

	static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
