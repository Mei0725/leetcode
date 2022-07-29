package leetcode_test;

import java.util.List;

public class HouseRobber2 {

	public static void main(String[] args) {
		int[] input = {4,1,2,7,5,3,1};
//		int[] input = {1,2,3,1};
//		int[] input = {2,7,9,3,1};
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
	 * 处理两个情数组：必须取0，则必定不可取 i-1，判断i-1 + i-2结果【该数组的 i-2结果中必定不包含 i-2值】 与 i-1结果；
	 *                必须不取0，则可判断 i-1结果 与 i + i-2结果；
	 * 
	 * 简化思路：与1相比，0 与 n-1 只可二取一，因此可简化为获取 0->(n-2) 与 1->(n-1) 的最大结果。
	 * 
	 * @param nums
	 * @return
	 */
    public static int rob(int[] nums) {
    	int length = nums.length;
		int result = nums[0];
    	if (length <= 3) { 
    		for (int i = 1; i < length; i++) {
    			if (nums[i] > result) {
    				result = nums[i];
    			}
    		}
    		return result;
    	}
    	
    	int[] nocontain1 = new int[length];
    	int[] contain1 = new int[length];
    	nocontain1[0] = 0;
    	nocontain1[1] = nums[1];
    	nocontain1[2] = Math.max(nums[1], nums[2]);
    	contain1[0] = nums[0];
    	contain1[1] = nums[0];
    	contain1[2] = nums[0];
    	for (int i = 3; i < length; i++) {
    		nocontain1[i] = Math.max(nocontain1[i - 1], nums[i] + nocontain1[i - 2]);
    		contain1[i] = Math.max(contain1[i - 1], nums[i - 1] + contain1[i - 2]);
        	System.out.println("i: " + i);
        	System.out.println("nocontain1: " + nocontain1[i]);
        	System.out.println("contain1: " + contain1[i]);
    	}
    	return Math.max(nocontain1[length - 1], contain1[length - 1]);
    }
}
