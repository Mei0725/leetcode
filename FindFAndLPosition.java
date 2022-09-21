package leetcode_test;

public class FindFAndLPosition {

	public static void main(String[] args) {
//		int[] input = {5,7,7,8,8,10};
//		int target = 8;
//		int[] input = {};
//		int target = 8;
//		int[] input = {1,2,8,8,8};
//		int target = 1;
		int[] input = {8,8};
		int target = 8;
		int[] output = {};
		try {
			output = searchRange(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i] + ",");
		}
	}

	/**
	 * binary search
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
    public static int[] searchRange(int[] nums, int target) {
    	int start = -1, end = -1;
    	
    	int i = 0;
    	int j = nums.length - 1;
    	while (i < j) {
//			System.out.println("nums[i]:" + nums[i]);
//			System.out.println("i:" + i);
			int mid = (i + j) / 2;
			if ((mid == 0 || nums[mid - 1] < target) && nums[mid] == target) {
				i = mid;
				break;
			} else if (nums[mid] >= target) {
				// change mid to avoid infinite loop
    			j = mid - 1;
    		} else {
    			i = mid + 1;
    		}
			System.out.println("j:" + j);
    	}
    	
    	if (nums.length == 0 || nums[i] != target) {
    		return new int[] {start, end};
    	}
    	start = i;
//		System.out.println("start:" + nums[start]);
    	
    	// the way to find end is as same as start, except the judgment about =
    	j = nums.length - 1;
    	while (i < j) {
			int mid = (i + j) / 2;
//			System.out.println("mid:" + mid);
//			System.out.println("i:" + i);
//			System.out.println("j:" + j);
    		if ((mid == nums.length - 1 || nums[mid + 1] > target) && nums[mid] == target) {
    			i = mid;
				break;
			} else if (nums[mid] <= target) {
    			i = mid + 1;
    		} else {
    			j = mid - 1;
    		}
    	}
    	end = i;
//		System.out.println("end:" + nums[end]);
        return new int[] {start, end};
    }
}
