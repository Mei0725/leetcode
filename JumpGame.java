package leetcode_test;

public class JumpGame {

	public static void main(String[] args) {
		int[] input = {2,3,1,1,4};
//		int[] input = {3,2,1,0,4};
		boolean output = true;
		try {
			output = canJump(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * Greedy. For this problem, greedy would spend less time(2ms VS 91ms)
	 * in this solution, assume the first element is in the path of result, and find out the farthest element it can get, then take it as the new start
	 * if the new start's value is 0, then check that if there is a element behind of it can arrive at any element back of it
	 * 
	 * @param nums
	 * @return
	 */
    public static boolean canJumpGreedy(int[] nums) {
        for(int i = 0; i < nums.length - 1; i = i + nums[i]){
            if(nums[i] == 0){
                int newStart = 0;
                for(newStart = i; newStart > 0; newStart--){                    
                    if(nums[newStart] + newStart > i){
                        break;
                    }
                }
                if(newStart == 0){
                    return false;
                }
                i = newStart;
            }
        }
        return true;
    }

    /**
     * Dynamic Programming.
     * using dp[] to store if we can arrive the element i, if we can, dp[i] is true
     * 
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
    	boolean[] dp = new boolean[nums.length];
    	dp[0] = true;
    	for(int i = 0; i < nums.length - 1; i++) {
    		if (!dp[i]) {
    			continue;
    		} else if (i + nums[i] >= nums.length - 1) {
    			return true;
    		}
    		
    		for(int j = i; j <= i + nums[i] && j < nums.length; j++) {
    			dp[j] = true;
    		}
    	}
        return dp[nums.length - 1];
    }
}
