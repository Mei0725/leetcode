package leetcode_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class Pair{
	int index, height;

	public Pair(int index, int height) {
		this.index = index;
		this.height = height;
	}
	
}

public class LargestRectangleInHistogram {

	public static void main(String[] args) {
//		int[] input = {2,1,5,6,2,3,2,14};
//		int[] input = {2,1,5,6,2,3};
//		int[] input = {2,4};
//		int[] input = {5,4,1,2};
//		int[] input = {4,2,0,3,2,5};
//		int[] input = {3,6,5,7,4,8,1,0};
		int[] input = {1,0,0,1,0,1,0,0,0,1};
		int output = -1;
		try {
			output = largestRectangleArea(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    /**
     * this solution is an optimized solution of largestRectangleArea
     * the stack sorts the start index and height of every area, and avoid extra pop of same value
     * 
     * @param heights
     * @return
     */
	public static int largestRectangleArea0(int[] heights) {
		int maxArea = 0;
		
		Stack<Pair> st = new Stack<>();
		
		for(int i = 0; i<heights.length; i++) {
			int start = i;
			// when the last heights is larger than this one, the max area of last heights can be calculated
			while(!st.isEmpty() && st.peek().height>heights[i]) {
					Pair p = st.pop();
					int index = p.index;
					int height = p.height;
					maxArea = Math.max(maxArea, height*(i - index));
					// since the pop pair's value is larger than this one, the start of this one can be their start
					start = index;
			}
			st.add(new Pair(start, heights[i]));
		}
		
		for(Pair p : st) {
			maxArea = Math.max(maxArea, p.height*(heights.length-p.index));
		}
		
		return maxArea;
	}
	
	/**
	 * this solution work by stack, which sorts the board of every area
	 * 
	 * @param heights
	 * @return
	 */
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

	/**
	 * solve this problem by cutting the array by its min value
	 * the time complexity is O(n^2), which would cost very long time(1227ms)
	 * 
	 * @param heights
	 * @return
	 */
    public static int largestRectangleArea1(int[] heights) {
    	return largestRectangleArea(0, heights.length - 1, heights);
    }

    public static int largestRectangleArea(int left, int right, int[] heights) {
    	if (left == right) {
    		return heights[left];
    	}
    	List<Integer> minIndex = findMin(left, right, heights);
    	if (minIndex.isEmpty()) {
//    		System.out.println("0: left: " + left + ", right: " + right);
    		return 0;
    	}
    	
    	int area = heights[minIndex.get(0)] * (right - left + 1);
//		System.out.println("area: " + area + ", left: " + left + ", right: " + right);
    	int leftNext = left, rightNext = minIndex.get(0) - 1;
		int tmp = largestRectangleArea(leftNext, rightNext, heights);
//		System.out.println("tmp0: " + tmp + ", leftNext: " + leftNext + ", rightNext: " + rightNext);
    	area = Math.max(area, tmp);
    	for (int i = 1; i < minIndex.size(); i++) {
    		leftNext = minIndex.get(i - 1) + 1;
    		rightNext = minIndex.get(i) - 1;
    		tmp = largestRectangleArea(leftNext, rightNext, heights);
//    		System.out.println("tmp: " + tmp + ", leftNext: " + leftNext + ", rightNext: " + rightNext);
        	area = Math.max(area, tmp);
    	}
    	leftNext = minIndex.get(minIndex.size() - 1) + 1;
    	tmp = largestRectangleArea(leftNext, right, heights);
//		System.out.println("tmp9: " + tmp + ", leftNext: " + leftNext + ", right: " + right);
    	area = Math.max(area, tmp);
    	return area;
    }
    
    public static List<Integer> findMin(int left, int right, int[]heights) {
    	List<Integer> result = new ArrayList<>();
    	if (right < left) {
    		return result;
    	}
    	
    	int min = left;
    	result.add(min);
    	for (int i = left + 1; i <= right; i++) {
    		if (heights[i] < heights[min]) {
    			result = new ArrayList<>();
    			result.add(i);
    			min = i;
    		} else if (heights[i] == heights[min]) {
    			result.add(i);
    		}
    	}
    	return result;
    }
}
