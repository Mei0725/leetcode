package leetcode_test;

public class SearchInsertPosition {

	public static void main(String[] args) {
		int[] input = {1,3,5,6};
		int target = 5;
		int output = -1;
		try {
			output = searchInsert(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}

	/**
	 * binary search
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
    public static int searchInsert(int[] nums, int target) {
    	int start = 0, end = nums.length - 1;
    	while (start < end) {
    		int mid = (start + end) / 2;
    		if (nums[mid] == target) {
    			start = mid;
    			break;
    		} else if (nums[mid] < target) {
    			start = mid + 1;
    		} else {
    			end = mid - 1;
    		}
//    		System.out.println("mid:" + mid);
//    		System.out.println("start:" + start);
//    		System.out.println("end:" + end);
    	}
    	return nums[start] >= target ? start : start + 1;
    }

}
