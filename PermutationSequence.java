package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

	public static void main(String[] args) {
		int input = 3;
		int k = 3;
//		int input = 4;
//		int k = 9;
		String output = null;
		try {
			output = getPermutationList(input, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * for n digit of the result, every starting num have (n - 1)! items
	 * according to this rule, calculate every digit's value
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
    public static String getPermutationList(int n, int k) {
    	// nums to store all nums
    	List<Integer> nums = new ArrayList<>();
    	String result = "";
    	// numLength store the length of every starting num, which equal to (n - 1)!
    	int numLength = 1;
    	for (int i = 1; i <= n; i++) {
    		numLength *= i;
    		nums.add(i);
    	}
    	// since the result is started from 0, k - 1 is the real index
    	k -= 1;
    	
    	while (result.length() < n) {
    		numLength /= (n - result.length());
    		// num is the value in this digit
    		int num = k / numLength;
    		k = k % numLength;
//    		System.out.println("num: " + num);
//    		System.out.println("k: " + k);
    		result = result + nums.get(num);
    		nums.remove(num);
    	}
    	return result;
    }

    /**
     * this solution is as same as getPermutationList, but sort nums in int[], which spends more time(14ms VS 3ms)
     * 
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
    	boolean[] nums = new boolean[n];
    	
    	String result = "";
    	k -= 1;
    	int numLength = 1;
    	for (int i = 2; i <= n; i++) {
    		numLength *= i;
    	}
    	while (result.length() < n) {
    		numLength /= (n - result.length());
    		int num = k / numLength;
    		k = k % numLength;
//    		System.out.println("num: " + num);
//    		System.out.println("k: " + k);
    		
    		int index = 0, length = 0;
    		while (length <= num) {
    			if (!nums[index]) {
    				length++;
    			}
    			index++;
    		}
    		result = result + (index);
    		nums[index - 1] = true;
//    		System.out.println("index - 1: " + (index - 1));
    	}
    	return result;
    }
}
