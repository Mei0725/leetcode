package leetcode_test;

public class RemoveElement {

	public static void main(String[] args) {
		int[] input = {0,1,2,2,3,0,4,2};
//		int[] input = {1,1,2};
		int val = 2;
		int output = -1;
		try {
			output = removeElement(input, val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
	
    public static int removeElement(int[] nums, int val) {
    	int remove = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (val != nums[i]) {
        		nums[i - remove] = nums[i];
    		} else {
    			remove++;
    		}
    	}
        return nums.length - remove;
    }
}
