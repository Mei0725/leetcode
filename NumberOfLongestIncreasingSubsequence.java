package leetcode_test;

import java.util.HashSet;
import java.util.Set;

public class NumberOfLongestIncreasingSubsequence {

	/**
	 * for every item i, store the max legal length in i and the number of cases can reach that length
	 * for item j, length[j] is the max length before j +1, and count[j] is the sum of all counts in this length
	 * 
	 * @param nums
	 * @return
	 */
    public int findNumberOfLIS(int[] nums) {
    	// count[i] is the number of cases that can reach length[i]
        int[] count = new int[nums.length];
        // length[i] is the max legal length
        int[] length = new int[nums.length];
        count[0] = 1;
        length[0] = 1;
        // max is the finally max length, it should be stored to get the finally result
        int max = 1, res = 0;
        for (int i = 1; i < nums.length; i++) {
        	// pre is used to store index which can reach the max length
            Set<Integer> pre = new HashSet<>();
            // maxLength is the max length from 0->(i-1)
            int maxLength = 0, tmp = i - 1;
            while (tmp >= 0 && tmp >= maxLength - 1) {
                if (nums[tmp] >= nums[i]) {
                    tmp--;
                    continue;
                }
                if (maxLength == length[tmp]) {
                    pre.add(tmp);
                } else if (maxLength < length[tmp]) {
                    maxLength = length[tmp];
                    pre = new HashSet<>();
                    pre.add(tmp);
                }
                tmp--;
            }
            // System.out.println("i: " + i);
            // System.out.println("pre: " + pre);
            // System.out.println("maxLength: " + maxLength);
            
            // length[i] start from maxLength
            length[i] = maxLength + 1;
            for (Integer p : pre) {
                count[i] += count[p];
            }
            if (count[i] == 0) {
                count[i] = 1;
            }
            if (max == length[i]) {
                res += count[i];
            } else if (max < length[i]) {
                max = length[i];
                res = count[i];
            }
        }
        return res;
    }

    /**
     * it is similar to findNumberOfLIS, but instead of using Set to store the possible i, 
     * it calculate count[i], length[i] and max during the loop, it can help to save time and space
     * 
     * @param nums
     * @return
     */
    public int findNumberOfLIS1(int[] nums) {
        int[] count = new int[nums.length];
        int[] length = new int[nums.length];
        count[0] = 1;
        length[0] = 1;
        int max = 1, res = 1;
        for (int i = 1; i < nums.length; i++) {
            int tmp = i - 1;
            count[i] = 1;
            length[i] = 1;
            while (tmp >= 0 && tmp >= length[i] - 2) {
                if (nums[tmp] >= nums[i]) {
                    tmp--;
                    continue;
                }
                if (length[i] == length[tmp] + 1) {
                    count[i] += count[tmp];
                } else if (length[i] < length[tmp] + 1) {
                    length[i] = length[tmp] + 1;
                    count[i] = count[tmp];
                }
                tmp--;
            }
            // System.out.println("tmp: " + tmp);
            
            if (max == length[i]) {
                res += count[i];
            } else if (max < length[i]) {
                max = length[i];
                res = count[i];
            }
        }
        // System.out.println("max: " + max);
        return res;
    }
}
