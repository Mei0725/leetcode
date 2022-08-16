package leetcode_test;

public class JumpGame2 {

	public static void main(String[] args) {
//		int[] input = {2,3,1,1,4};
		int[] input = {2,3,0,1,4,5};
		int output = -1;
		try {
			output = jump(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * for every element i, calculate the result from i -> (i+nums[i])
	 * 
	 * @param nums
	 * @return
	 */
    public static int jump(int[] nums) {
    	int[] dp = new int[nums.length];
    	for(int i = 0; i < nums.length - 1; i++) {
    		// if no element can get dp[i], just ignore dp[i]
    		if (i != 0 && dp[i] == 0) {
    			continue;
    		} else if (i + nums[i] > nums.length - 1) {
    			// if we have arrived at the end of nums, then break and return the result
    			return dp[i] + 1;
    		}
    		
    		for(int j = i + nums[i]; j > i; j--) {
    			// if dp[j] < dp[i] + 1, then the element from dp[i -> j] must have value < dp[i] + 1
    			// so just break cycle to save time
    			if (dp[j] != 0 && dp[j] < dp[i] + 1) {
    				break;
    			}
				dp[j] = dp[i] + 1;
    		}
    	}
        return dp[nums.length - 1];
    }
}
