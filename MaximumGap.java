package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class MaximumGap {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int output = -1;
		try {
			output = maximumGap(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * solve this problem by bucket sort
	 * 
	 * @param nums
	 * @return
	 */
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        
        // find out the max num in nums
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // gap is the size of every bucket
        // +1 is to make sure gap is larger than 0 when there are many repeated value in nums
        int gap = max / nums.length + 1;
        // bucket is to sort the min and max item in every bucket
        // since the loop break when there are items in no-neighbor bucket, the result must be larger than gap
        // so every item's value is not important
        Map<Integer, int[]> bucket = new HashMap<>();;
        while (gap > 0) {
        // System.out.println("loop: ");
            bucket = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int bucketIndex = nums[i] / gap;
                if (bucket.containsKey(bucketIndex)) {
                    int[] tmp = bucket.get(bucketIndex);
                    tmp[0] = Math.min(tmp[0], nums[i]);
                    tmp[1] = Math.max(tmp[1], nums[i]);
                } else {
                    bucket.put(bucketIndex, new int[]{nums[i], nums[i]});
                }
            }
            // check if there are at least two no-successive buckets
            if (bucket.size() < max / gap && bucket.size() > 1) {
        // System.out.println("bucket.size(): " + bucket.size());
                break;
            } else {
            	// if not, it means the gap is too large
                gap = gap / 2;
            }
        }
        // System.out.println("bucket: " + bucket);
        // System.out.println("gap: " + gap);
        // when gap is 0, it means the result bucket's size is 1
        if (gap == 0) {
            gap++;
        }

        // check all bucket to find out the no-successive buckets, the max of which should be result
        int result = 0;
        int[] last = null;
        for (int i = 0; i <= max / gap + 1; i++) {
            if (!bucket.containsKey(i)) {
                continue;
            }
            int[] tmp = bucket.get(i);
            if (last != null) {
                result = Math.max(result, (tmp[0] - last[1]));
            }
            last = tmp;
        }
        return result;
    }

	/**
	 * this solution also use bucket sort 
	 * but compare to maximumGap, it calculate gap firstly and have no loop,
	 * which help it to reduce the running time
	 * 
	 * @param nums
	 * @return
	 */
	public int maximumGapBucket(int[] nums) {
		int n = nums.length;
		if (n < 2)
			return 0;
		// find out the min and max item in nums
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < n; i++) {
			if (min > nums[i])
				min = nums[i];
			if (max < nums[i])
				max = nums[i];
		}

		// gap is the size of bucket
		// since gap is calculated by max, min and nums.length, the result will not be less than gap
		int gap = (max - min) / (n - 1);
		if (gap == 0)
			gap++;
		// len is the max num of buckets
		int len = (max - min) / gap + 1;
		// tmax[] and tmin[] is to store the max and min in every bucket
		int[] tmax = new int[len];
		int[] tmin = new int[len];

		for (int i = 0; i < n; i++) {
			// -min is to make sure the index will be not over len
			int index = (nums[i] - min) / gap;
			if (nums[i] > tmax[index])
				tmax[index] = nums[i];
			if (tmin[index] == 0 || nums[i] < tmin[index])
				tmin[index] = nums[i];
		}
		int myMax = 0;
		for (int i = 0; i < len; i++) {
			if (myMax < tmin[i] - min)
				myMax = tmin[i] - min;
			if (tmax[i] != 0)
				min = tmax[i];
		}
		return myMax;
	}
    
    /**
     * solve the problem by radix sort
     * resort the nums by radix sort and then check if it's result one by one
     * 
     * @param nums
     * @return
     */
	public static int maximumGapRadix(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}

		// m is the maximal number in nums
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(max, nums[i]);
		}

		int exp = 1; // 1, 10, 100, 1000 ...
		int R = 10; // 10 digits

		int[] aux = new int[nums.length];

		// radix sort, find out max to make sure which time the loop should be over
		while (max / exp > 0) {
			int[] count = new int[R];

			for (int i = 0; i < nums.length; i++) {
				count[(nums[i] / exp) % 10]++;
			}

			// count[i] is the list of number of items whose exp-digit is similar or less than i
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}

			// aux[] is a tmp sorted list
			// every loop, aux[] store resorted nums by exp-digit
			for (int i = nums.length - 1; i >= 0; i--) {
				aux[--count[(nums[i] / exp) % 10]] = nums[i];
			}

			for (int i = 0; i < nums.length; i++) {
				nums[i] = aux[i];
			}
			exp *= 10;
		}

		int result = 0;
		for (int i = 1; i < aux.length; i++) {
			result = Math.max(result, aux[i] - aux[i - 1]);
		}

		return result;
	}
}
