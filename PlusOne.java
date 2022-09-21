package leetcode_test;

import java.util.Arrays;

public class PlusOne {

	public static void main(String[] args) {
//		int[] input = {1,2,3};
		int[] input = {9};
		int[] output = null;
		try {
			output = plusOne(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: ");
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i] + ",");
		}
	}
	
    public static int[] plusOne(int[] digits) {
    	int[] result = new int[digits.length + 1];
    	boolean added = true;
    	
    	for (int i = digits.length - 1; i >= 0; i--) {
    		int tmp = digits[i] + (added ? 1 : 0);
    		result[i + 1] = tmp % 10;
    		added = tmp > 9;
    	}
    	if (added) {
    		result[0] = 1;
    		return result;
    	}
    	return Arrays.copyOfRange(result, 1, result.length);
    }

}
