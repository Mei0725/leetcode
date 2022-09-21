package leetcode_test;

import java.util.Stack;

public class MaximalRectangle {

	public static void main(String[] args) {
		char[][] input = {{'1','1','0','1','0','1','0','0'},{'1','0','0','1','0','1','0','0'}};
		int output = -1;
		try {
			output = maximalRectangle(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
    
    /**
     * solve this problem by stack.
     * create a int[][] to find the max area in column, and then check each row to find out the largest rectangle area.
     * 
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        int result = 0;
        int x = matrix.length, y = matrix[0].length;
        int[][] yValue = new int[x][y];
    	for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
        		if (matrix[i][j] == '1') {
        			if (i == 0) {
        				yValue[i][j] = 1;
        			} else {
        				yValue[i][j] = yValue[i - 1][j] + 1;
        			}
        		}
        	}
        }
    	
    	for (int i = 0; i < x; i++) {
    		result = Math.max(result, largestRectangleArea(yValue[i]));
    	}
        return result;
    }
    
	public static int largestRectangleArea(int[] heights) {
    	int result = heights[0];
    	Stack<Integer> board = new Stack<>();
    	for (int i = 0; i < heights.length; i++) {
    		if (board.isEmpty() || heights[i] >= heights[board.peek()]) {
    			board.add(i);
    			continue;
    		}
    		while (!board.isEmpty() && heights[board.peek()] >= heights[i]) {
    			int x = board.pop();
    			// to deal with the same value
    			while (!board.isEmpty() && heights[x] == heights[board.peek()] ) {
    				board.pop();
    			}
    			// the values between x and the previous one of x must be smaller than heights[x]
    			// so the length of this area should be the value between the previous one and x
    			int length = i - (board.isEmpty() ? -1 : board.peek()) - 1;
    			result = Math.max(result, heights[x] * length);
    		}
			board.add(i);
    	}
    	while (!board.isEmpty()) {
			int x = board.pop();
			while (!board.isEmpty() && heights[x] == heights[board.peek()] ) {
				board.pop();
			}
			int length = heights.length - (board.isEmpty() ? -1 : board.peek()) - 1;
			result = Math.max(result, heights[x] * length);
    	}
    	return result;
	}
}
