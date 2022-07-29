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
	 * ����ÿN��Ԫ�أ����Ϊȡ (Nֵ + (N-2)���) �� (N-1)��� �����ֵ��
	 * �������⣬��ֱ�������� int ���� (N-2)��� �� (N-1)������Ӷ�������� ʱ��/�ռ����ġ�
	 * �������������飺����ȡ0����ض�����ȡ i-1���ж�i-1 + i-2������������ i-2����бض������� i-2ֵ�� �� i-1�����
	 *                ���벻ȡ0������ж� i-1��� �� i + i-2�����
	 * 
	 * ��˼·����1��ȣ�0 �� n-1 ֻ�ɶ�ȡһ����˿ɼ�Ϊ��ȡ 0->(n-2) �� 1->(n-1) ���������
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
