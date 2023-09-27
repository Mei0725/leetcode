package leetcode_test;

public class MinSubArrayLen {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int target = 2;
		int output = -1;
		try {
			output = minSubArrayLen(target, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * check the valid sub-array one by one, and the time complexity is n^2
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
    public static int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int i = 0;
        while (i < nums.length) {
            int sum = nums[i];
            int tmp = i + 1;
            while (tmp < nums.length && sum < target) {
                sum += nums[tmp++];
            }
                // System.out.println("i: " + i);
                // System.out.println("sum: " + sum);
            if (sum >= target && tmp - i < result) {
                result = tmp - i;
            }
            if ((tmp == nums.length && sum <= target) || result == 1) {
                break;
            }
            i++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * solve this problem by sliding window
     * this solution's time complexity is O(n)
     * 
     * @param target
     * @param nums
     * @return
     */
	public static int minSubArrayLenWindow(int target, int[] nums) {
		// i is the left side of window and j is the right side of window
		int i = 0;
		int j = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		while (i <= j && j < nums.length) {
			if (sum < target) {
				sum += nums[j];
				j++;
			} else {
				min = Math.min(min, j - i);
				sum -= nums[i];
				i++;
			}
		}

		// when j is the right side of nums, the left side of window may can be moved to reduce window's wide
		while (i < j && sum >= target) {
			min = Math.min(min, j - i);
			sum -= nums[i];
			i++;
		}

		if (min == Integer.MAX_VALUE)
			return 0;
		else
			return min;
	}
    
	/**
	 * solve the problem by prefix sum
	 * the time complexity is O(n*log(n))
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
    public static int minSubArrayLenPrefixSum(int target, int[] nums) {
		int min = Integer.MAX_VALUE;
		// pre[] is used to store the prefix sum
		int pre[] = new int[nums.length + 1];
		pre[0] = 0;
		for (int i = 1; i < nums.length + 1; i++) {
			pre[i] = pre[i - 1] + nums[i - 1];
		}

		for (int i = 0; i < nums.length; i++) {
			// secondIndex is used to mark the min valid sub-array for nums[i]
			int secondIndex = -1;
			// l is the mark of left side of sub-array and h is the right side
			int l = i + 1;
			int h = nums.length;
			// binary search to find out the valid min sub-array
			while (l <= h) {
				int mid = (l + h) / 2;
				if (pre[mid] - pre[i] < target) {
					l = mid + 1;
				} else {
					secondIndex = mid;
					h = mid - 1;
				}
			}

			if (secondIndex != -1) {
				min = Math.min(min, secondIndex - i);
			}
		}

		if (min == Integer.MAX_VALUE)
			return 0;
		else
			return min;
    }
}
