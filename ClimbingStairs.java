package leetcode_test;

public class ClimbingStairs {

	public static void main(String[] args) {
		int input = 2;
		int output = -1;
		try {
			output = climbStairs(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by DP.
	 * for every stairs n, the result should be (result[n - 1] + result[n - 2])
	 * there are result[n - 1] ways that if we add 1 step can arrive at n, and result[n - 2] ways that if we add 2 step can arrive at n
	 * 
	 * @param n
	 * @return
	 */
    public static int climbStairs(int n) {
        if (n == 1) {
        	return 1;
        } else if (n == 2) {
        	return 2;
        }
        
        int[] ways = new int[n];
        ways[0] = 1;
        ways[1] = 2;
        for (int i = 2; i < n; i++) {
        	ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n - 1];
    }
}
