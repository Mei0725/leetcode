package leetcode_test;

import java.util.HashSet;
import java.util.Set;

public class MaximumLengthOfRepeatedSubarray {

	// the largest value in arrays is 100 so take PRIME as 101
    private static final int PRIME = 101;
    private static final int MOD = 1_000_000_007;
    
    /**
     * solve the problem by rolling hash
     * 
     * @param A
     * @param B
     * @return
     */
    public int findLengthByRollingHash(int[] A, int[] B) {
        if (A.length < B.length) return findLengthByRollingHash(B, A);
        
        // 1.) Check result for length 1  <==>  single character
        Set<Integer> set = new HashSet<>();
        // check if B contains chars in A, if not, return 0
        boolean lenZero = false;
        
        for (int a : A) {
        	set.add(a);
        }
        for (int b : B) {
            if (!set.contains(b)) continue;
            lenZero = true;
            break;
        }
        if (!lenZero) return 0;
        
        
        // 2). Now the result lies in [1, min(A.length, B.length)], perform binary serach
        int left = 1, right = Math.min(A.length, B.length);
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (check(mid, A, B)) left = mid;
            else right = mid - 1;
        }
        return check(right, A, B) ? right : left;
    }
    
    /**
     * check every subarray with length mid
     * 
     * 
     * @param mid  the length of common subarray
     * @param A    the int[] nums1, the longer one
     * @param B    the int[] nums2, the shorter one
     * @return
     */
    private boolean check(int mid, int[] A, int[] B) {
        Set<Integer> set = new HashSet<>();  
        
        // 1). Adding hash values of subarrays of B into set
        // calculate the init hash value of B[0]->B[mid-1] and add it into set
        // use B[j]+1 is to make sure the added one is larger than 0 
        long hash = 0;
        for (int j = 0; j < mid; j++) {
            hash = hash * PRIME + (B[j] + 1);
            hash %= MOD;
        }
        set.add((int) hash);
        
        // calculate the power, which usually should be Math.pow(PRIME, mid-1)
        // take modulus with MOD is to make sure it is in long's value
        long power = 1;
        for (int i = 0; i < mid-1; i++) {
            power = (power * PRIME) % MOD;
        }
        
        // calculate the hash value of B
        for (int i = 0, j = mid; j < B.length; i++, j++) {
        	// add MOD finally is to make sure hash is larger than 0
            hash = hash - ((B[i] + 1) * power % MOD) + MOD;
            hash = hash * PRIME + (B[j] + 1);
            hash %= MOD;
            set.add((int) hash);
        }
        
        // 2.) Check the hash values of subarrays of A
       // calculate the hash value of A[0]->A[mid-1] and add it into set
        hash = 0;
        for (int j = 0; j < mid; j++) {
            hash = hash * PRIME + (A[j] + 1);
            hash %= MOD;
        }
        if (set.contains((int)hash)) return true;
        
        for (int i = 0, j = mid; j < A.length; i++, j++) {
            hash = hash - ((A[i] + 1) * power % MOD) + MOD;
            hash = hash * PRIME + (A[j] + 1);
            hash %= MOD;
            if (set.contains((int)hash)) return true;
        }
        
        return false;
    }
    
    /**
     * it is the most common dp solution.
     * use int[i][j] to store the longest subarray which contains nums1[i] and nums2[j]
     * and then use max to store the longest result for all checked subarray
     * for every i-and-j, if nums[i]==nums[j], then result[i][j]=result[i-1][j-1]
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthByDP1(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int max = 0;
        int[][] result = new int[length1][length2];
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (nums1[i] == nums2[j]) {
                    result[i][j] = (i == 0 || j == 0) ? 1 : result[i - 1][j - 1] + 1;
                    max = Math.max(result[i][j], max);
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return max;
    }
    
    
    /**
     * it is an optimization of findLengthByDP1
     * since the only usable value of result[i][j] is result[i-1][j-1],
     * we can use int[] to store the last line of int[][] to save space(51.8MB->42.1MB)
     * and maybe because the change of reading-from-int[][] to int[], it alse save some time(44ms->26ms)
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthByDP2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        // set the shorter array as nums2 so that the result[] will be smaller
        if (length1 < length2) {
            return findLengthByDP2(nums2, nums1);
        }

        int max = 0;
        // only to store the last line
        int[] result = new int[length2];
        for (int i = 0; i < length1; i++) {
            for (int j = length2 - 1; j > 0; j--) {
                if (nums1[i] == nums2[j]) {
                    result[j] = (i == 0 || j == 0) ? 1 : result[j - 1] + 1;
                    max = Math.max(result[j], max);
                } else {
                    result[j] = 0;
                }
            }
            result[0] = (nums1[i] == nums2[0]) ? 1 : 0;
            max = Math.max(result[0], max);
            // the largest result if length2, so this check can save some time.
            // but the improvement is not so obvious(27ms->26ms)
            if (max == length2) {
                break;
            }
        }
        return max;
    }
}
