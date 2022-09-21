package leetcode_test;

public class FirstMissingPositive {

	public static void main(String[] args) {
//		int[] input = {1,2,3};
//		int[] input = {1,2,0};
		int[] input = {3,4,-1,7};
		int output = -1;
		try {
			output = firstMissingPositive(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}

	/**
	 * use int[] to store existed nums, then find out the first index whose value is 0
	 * the length of int[] should not less than nums.length+1, in which case all nums are consecutive positive integers starting from 1
	 * 
	 * @param nums
	 * @return
	 */
    public static int firstMissingPositive(int[] nums) {
    	int[] count = new int[nums.length + 1];
    	for (int num : nums) {
    		if (num <= nums.length && num > 0) {
    			count[num]--;
    		}
    	}
    	for (int i = 1; i < count.length; i++) {
    		if (count[i] == 0) {
    			return i;
    		}
    	}
        return count.length;
    }
}
