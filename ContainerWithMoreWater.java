package leetcode_test;

public class ContainerWithMoreWater {

	public static void main(String[] args) {
//		int[] input = {1,8,6,2,5,4,8,3,7};
		int[] input = {1,2,3,4,5,25,24,3,4};
//		int[] input = {0, 2};
		int output = -1;
		try {
			output = maxArea(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int maxArea(int[] height) {
    	int result = 0;
    	int width = height.length - 1;
    	if (width == 0) {
    		return 0;
    	}
    	
    	int maxHeight = height[0];
    	for (int i = 0; i < width; i++) {
    		if (height[i] > maxHeight)
    			maxHeight = height[i];
    		System.out.println("maxHeight: " + maxHeight);
    	}
		System.out.println("maxHeight: " + maxHeight);
    	while (width > 0 && (result == 0 || width > result / maxHeight)) {
    		for (int i = 0; i < height.length - width; i++) {
    			int temp = width * Math.min(height[i], height[i + width]);
    			if (temp > result) {
    				result = temp;
    			}
    		}
    		width--;
    		System.out.println("width: " + width);
    		System.out.println("result: " + result);
    	}
    	return result;
    }

}
