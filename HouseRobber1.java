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
	 * ����ÿN��Ԫ�أ����Ϊȡ (Nֵ + (N-2)���) �� (N-1)��� �����ֵ��
	 * �������⣬��ֱ�������� int ���� (N-2)��� �� (N-1)������Ӷ�������� ʱ��/�ռ����ġ�
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
     * ʹ�õ������������Ϊʱ�临�ӶȾ޴����ʱ��
     * ����ÿN��Ԫ�أ����Ϊȡ (Nֵ + (N-2)���) �� (N-1)��� �����ֵ��
     * 
     * @param nums �����б�
     * @param n ��ȡǰ n �����ݵ����ֵ
     * @return ǰ n �����ݵ����ֵ
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
