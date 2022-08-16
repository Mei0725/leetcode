package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

	public static void main(String[] args) {
		int input = 9;
		List<Integer> output = null;
		try {
			output = grayCode(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * for any input n, we can get the result according to the result of (n - 1)
	 * we can get the first add item by changing the first char of the last item of result (n - 1) from 0 to 1
	 * then the second add item can be created by changing the first char of the second last item of result (n - 1) from 0 to 1
	 * and so on
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> grayCode(int n) {
		List<Integer> result = new ArrayList<>();
		result.add(0);
		for (int i = 1; i <= n; i++) {
			int length = (int)Math.pow(2, i);
			int addVal = length / 2;
			for (int j = length / 2; j < length; j++) {
				result.add(addVal + result.get(length - 1 - j));
			}
		}
		return result;
	}
}
