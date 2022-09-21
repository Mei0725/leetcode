package leetcode_test;

public class MaxSubarray {

	public static void main(String[] args) {
//		int[] input = {-2,1,-3,4,-1,2,1,-5,4};
//		int[] input = {1};
		int[] input = {5,4,-1,7,8};
		int output = 0;
		try {
			output = maxSubArray(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result:" + output); 
	}
	
	/**
	 * DP
	 * 
	 * @param nums
	 * @return
	 */
    public static int maxSubArray(int[] nums) {
    	// include[] to store the max result if num[i] is included in the result
    	// noInclude[] to stort the max result if nums[i] is not included in the result
        int[] include = new int[nums.length];
        int[] noInclude = new int[nums.length];
        include[0] = nums[0];
        // make the noInclude[0] = Integer.MIN_VALUE to handle the case that nums.length is 1
        noInclude[0] = Integer.MIN_VALUE;
        
        for (int i = 1; i < nums.length; i++) {
        	include[i] = Math.max(nums[i], nums[i] + include[i - 1]);
        	noInclude[i] = Math.max(include[i - 1], noInclude[i - 1]);
        }
        return Math.max(include[nums.length - 1], noInclude[nums.length - 1]);
    }
}
