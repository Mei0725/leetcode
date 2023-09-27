package leetcode_test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int output = -1;
		try {
			output = majorityElement(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * simple solution, put nums into map to count
	 * 
	 * @param nums
	 * @return
	 */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int majority = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) + 1 > majority) {
                return nums[i];
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return nums[0];
    }

    /**
     * since the number of result is larger than nums.length/2, for sorted arrays, nums[nums.length/2] must be result
     * so we can sort nums firstly and then get the nums[nums.length/2]
     * 
     * @param nums
     * @return
     */
    public static int majorityElementBacktracking(int[] nums) {
        partition(nums,0,nums.length-1);
        return nums[nums.length/2];
    }

    /**
     * sort array by separating it into 2 sorted parts and merge them
     * 
     * @param nums
     * @param p
     * @param r
     */
	public static void partition(int[] nums, int p, int r) {
		int q;
		if (p < r) {
			q = (p + r) / 2;
			partition(nums, p, q);
			partition(nums, q + 1, r);
			merge(nums, p, q, r);
		}
	}

	/**
	 * meger 2 arrays, and every array is sorted
	 * 
	 * @param nums
	 * @param p
	 * @param q
	 * @param r
	 */
	public static void merge(int[] nums, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int i, j, k;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (i = 0; i < n1; i++) {
			L[i] = nums[p + i];
		}
		for (j = 0; j < n2; j++) {
			R[j] = nums[q + j + 1];
		}

		i = 0;
		j = 0;
		k = p;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				nums[k] = L[i];
				i = i + 1;
			} else {
				nums[k] = R[j];
				j = j + 1;
			}
			k++;
		}

		while (i < n1) {
			nums[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			nums[k] = R[j];
			j++;
			k++;
		}
	}

	/**
	 * this solution is similar to majorityElementBacktracking, but sort nums by Arrays.sort
	 * 
	 * @param nums
	 * @return
	 */
    public static int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
