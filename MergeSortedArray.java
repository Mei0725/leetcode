package leetcode_test;

public class MergeSortedArray {

	public static void main(String[] args) {
		int[] nums1 = {1,2,3,0,0,0};
		int[] nums2 = {2,5,6};
		int m = 3;
		int n = 3;
		try {
			merge(nums1, m, nums2, n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: ");
		for (int i = 0; i < nums1.length; i++) {
			System.out.print(nums1[i] + ", ");
		}
	}

	/**
	 * to avoid change nums1 before move the items into right position, deal with the problem from the last item into the first
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int i = m - 1;
    	int j = n - 1;
    	int index = m + n - 1;
    	// since we need add nums2 into nums1, if all items in nums2 are moved, then it can be break
        while (j >= 0) {
        	// i is not checked before enter the cycle, so make sure i>=0 before compare the value of nums1 and nums2
        	if (i >= 0 && nums1[i] > nums2[j]) {
        		nums1[index] = nums1[i];
        		i--;
        	} else {
        		nums1[index] = nums2[j];
        		j--;
        	}
    		index--;
        }
    }
}
