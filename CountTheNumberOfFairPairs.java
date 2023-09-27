package leetcode_test;

import java.util.Arrays;

public class CountTheNumberOfFairPairs {
	
	/**
	 * this solution uses binary search to find out the fair pairs for every possible num[i]
	 * 
	 * @param nums
	 * @param lower
	 * @param upper
	 * @return
	 */
    public long countFairPairs(int[] nums, int lower, int upper) {
    	// sort nums so that we can find out the possible pairs by binary
    	// and check if this loop should be skipped
        Arrays.sort(nums);
        long result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
        	/*
        	 * this solution is used when the pairs which have the same value is seen as one pair
        	 */
            // if (nums[i] + nums[nums.length - 1] < lower) {
            //     continue;
            // } else if (nums[i] + nums[i + 1] > upper) {
            //     break;
            // }
            // int last = i, low = lower - nums[i], up = upper - nums[i];
            // for (int j = nums.length - 1; j > i; j--) {
            //     if (nums[j] < low) {
            //         break;
            //     } else if (nums[j] > up) {
            //         continue;
            //     } else if (nums[j] != nums[last]) {
            //         System.out.println("j: " + j);
            //         last = j;
            //         result++;
            //     }
            // }
            // System.out.println("result: " + result);
            
            // System.out.println("i: " + i);
        	// check if this loop should be skipped
            int min = i + 1, max = nums.length - 1;
            if (nums[i] + nums[max] < lower) {
                continue;
            } else if (nums[i] + nums[min] > upper) {
                break;
            }
            // find out the left possible index for nums[i]
            int tmp0 = min;
            while (min < max) {
                tmp0 = (min + max) / 2;
                if (nums[i] + nums[tmp0] < lower) {
                    min = tmp0 + 1;
                } else if (nums[i] + nums[tmp0] == lower || nums[i] + nums[tmp0 - 1] < lower) {
                    break;
                } else {
                    max = tmp0 - 1;
                }
            }
            // System.out.println("min: " + min);
            // System.out.println("max: " + max);
            tmp0 = (min + max) / 2;
            // since max is found by last tmp0-1 and / is round down
            // we should make sure the tmp0>=(i+1)
            if (tmp0 <= i) {
                tmp0 = i + 1;
            }
            // since there may not be nums make nums[i]+nums[tmp0]==lower
            // we should make sure tmp0 is possible result for lower
            if (nums[i] + nums[tmp0] < lower) {
                tmp0++;
            }
            // handle the case that there are nums have the same value as tmp0
            while (tmp0 > i + 1 && nums[tmp0] == nums[tmp0 - 1]){
                tmp0--;
            }
            // System.out.println("tmp0: " + tmp0);
            // find out the right possible index for nums[i]
            min = i + 1; max = nums.length - 1;
            int tmp1 = max;
            while (min < max) {
                tmp1 = (min + max) / 2;
                if (nums[i] + nums[tmp1] > upper) {
                    max = tmp1 - 1;
                } else if (nums[i] + nums[tmp1] == upper || nums[i] + nums[tmp1 + 1] > upper) {
                    break;
                } else {
                    min = tmp1 + 1;
                }
            }
            // System.out.println("min: " + min);
            // System.out.println("max: " + max);
            tmp1 = (min + max) / 2;
            if (tmp1 >= nums.length) {
                tmp1 = nums.length - 1;
            }
            if (nums[i] + nums[tmp1] > upper) {
                tmp1--;
            }
            while (tmp1 + 1 < nums.length && nums[tmp1] == nums[tmp1 + 1]) {
                tmp1++;
            }
            // System.out.println("tmp1: " + tmp1);
            // if tmp1 < tmp0, then there is no possible result, skip
            if (tmp1 >= tmp0) {
                result += (tmp1 - tmp0 + 1);
            }
        }
        return result;
    }
}
