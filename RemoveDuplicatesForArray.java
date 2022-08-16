package leetcode_test;

public class RemoveDuplicatesForArray {

	public static void main(String[] args) {
		int[] input = {0,0,1,1,1,2,2,3,3,4};
//		int[] input = {1,1,2};
		int output = -1;
		try {
			output = removeDuplicates(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
	
    public static int removeDuplicates(int[] nums) {
    	int remove = 0;
    	int localNum = Integer.MIN_VALUE;
    	int diffNum = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (localNum != nums[i]) {
    			localNum = nums[i];
    			diffNum++;
        		nums[i - remove] = nums[i];
    		} else {
    			remove++;
    		}
    	}
        return diffNum;
    }
}
