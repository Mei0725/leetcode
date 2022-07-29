package leetcode_test;

import java.util.List;

public class HouseRobber1 {

	public static void main(String[] args) {
		int[] input = {1,2,3,1};
//		String input = "  -42";
//		String input = "words and 987";
		int output = -1;
		try {
			output = rob(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * 对于每N个元素，结果为取 (N值 + (N-2)结果) 与 (N-1)结果 的最大值。
	 * 除迭代外，可直接用两个 int 储存 (N-2)结果 与 (N-1)结果，从而极大减少 时间/空间消耗。
	 * 
	 * @param nums
	 * @return
	 */
    public static int rob(int[] nums) {
    	/*int num = nums.length - 1;
        return robN(nums, num);*/
    	int robN = nums[0];
    	int maxNde2 = 0, maxNde1 = robN;
    	for (int i = 1; i < nums.length; i++) {
    		robN = Math.max(maxNde1, maxNde2 + nums[i]);
    		
    		maxNde2 = maxNde1;
    		maxNde1 = robN;
    	}
    	return robN;
    }
    
    /**
     * 使用迭代解决，将因为时间复杂度巨大而超时。
     * 对于每N个元素，结果为取 (N值 + (N-2)结果) 与 (N-1)结果 的最大值。
     * 
     * @param nums 输入列表
     * @param n 获取前 n 个数据的最大值
     * @return 前 n 个数据的最大值
     */
    public static int robN(int[] nums, int n) {
    	if (n == 0) {
    		return nums[0];
    	} else if (n < 0) {
    		return 0;
    	}
    	
    	int robN = nums[n] + robN(nums, n - 2);
    	int robNo = robN(nums, n - 1);
        return Math.max(robN, robNo);
    }
}
