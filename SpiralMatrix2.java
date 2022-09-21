package leetcode_test;

public class SpiralMatrix2 {

	public static void main(String[] args) {
		int input = 3;
		int[][] output = null;
		try {
			output = generateMatrix(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * sort the 4 edges of current cycle, and put the value into cycle from outer into inner
	 * 
	 * @param n
	 * @return
	 */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int num = 1;
        
        // since cycle must have the same width and height, we should only check its top and bottom edge
        while (top <= bottom) {
        	// when top == bottom, there is only the last point in this cycle, give it value and return
        	if (top == bottom) {
        		result[top][left] = num;
        		break;
        	}
        	
        	// build the top edge except the top right point
        	for (int i = left; i < right; i++) {
        		result[top][i] = num++;
        	}
        	// build the right edge except the bottom right point
        	for (int j = top; j < bottom; j++) {
        		result[j][right] = num++;
        	}
        	// build the bottom edge except the bottom left point
        	for (int i = right; i > left; i--) {
        		result[bottom][i] = num++;
        	}
        	// build the left edge except the top left point
        	for (int j = bottom; j > top; j--) {
        		result[j][left] = num++;
        	}
        	// move edges
        	top++;
        	bottom--;
        	left++;
        	right--;
        }
        
        return result;
    }
}
