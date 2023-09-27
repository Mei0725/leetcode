package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class SingleNumberII {

	/**
	 * solve by bit manipulation
	 * add num's 2-bit value into bits[32]
	 * for every num appears 3 times, bits[i] % 3 == 0
	 * all positions i that bits[i] % 3 == 1 belong to the result
	 * 
	 * @param nums
	 * @return
	 */
    public int singleNumber(int[] nums) {
    	// to store the time of 1 appears in every position 
    	// its length is 32 because int is 32-bit
        int[] bits = new int[32];
        for (int i = 0; i < nums.length; i++) {
        	// this code can handle positive number
        	// but for negative number, use >>=1 can not get all 1 value
             /**int bit = 1, j = 0;
             int n = nums[i];
             while (n != 0 && n!= -1) {
                 bits[j] += bit & n;
                 n >>= 1;
                 System.out.println("n: " + n);
                 j++;
             }*/
            int bit = 1;
            for (int j = 0; j < 32; j++) {
            	// get the value in position j
            	// for negative the result of (nums[i] & bit) is negative so check if it is 0 rather than if it is larger than 0
                // bits[j] += (nums[i] & (bit << j)) != 0 ? 1 : 0;
                bits[j] += (nums[i] & bit) != 0 ? 1 : 0;
                bit <<= 1;
            }
        }
        // for (int i = 31; i >= 0; i--) {
        //     System.out.print(bits[i]);
        // }
        // System.out.println(" ");

        // translate binary to decimal
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            // System.out.println("i: " + i);
            // System.out.println("bits[i]: " + bits[i]);
        	// handle the +/-
            res <<= 1;
            res += bits[i] % 3;
            // System.out.println("res: " + res);
        }
        return res;
    }
    
	/**
	 * store the nums and the time it appears in map
	 * if a num appears 3 times, remove it
	 * return the only left key after cheak all items in nums
	 * 
	 * @param nums
	 * @return
	 */
    public int singleNumberByMap(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = counts.getOrDefault(nums[i], 0);
            if (count == 2) {
                counts.remove(nums[i]);
            } else {
                counts.put(nums[i], count + 1);
            }
        }
        for (Integer num : counts.keySet()) {
            return num;
        }
        return 0;
    }
}
