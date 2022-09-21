package leetcode_test;

public class RemoveDuplicates {

	public static void main(String[] args) {
		int[] input = {1,1,1,2,2,3};
//		int[] input = {1};
		int output = -1;
		try {
			output = removeDuplicates(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static int removeDuplicates(int[] nums) {
        int result = 0;
        int i = 0;
        while (i < nums.length) {
        	// maybe because the operation + would spend extra time
        	// if find the next index in this way, it would cost 2ms, but the solution calculate end only cost 0ms
        	/*int count = 1;
        	while (i + count < nums.length && nums[i] == nums[i + count]) {
        		count++;
        	}
    		nums[result++] = nums[i];
        	if (count >= 2) {
        		nums[result++] = nums[i];
        	}
        	i += count;*/
        	int end = i + 1;
        	while (end < nums.length && nums[end] == nums[i]) {
        		end++;
        	}
    		nums[result++] = nums[i];
        	if (end - i >= 2) {
        		nums[result++] = nums[i];
        	}
        	i = end;
//    		System.out.println("end: " + end);
        }
        return result;
    }
}
