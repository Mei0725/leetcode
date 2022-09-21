package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpiralMatrix {

	public static void main(String[] args) {
//		int[][] input = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
//		int[][] input = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//		int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
//		int[][] input = {{2,5},{8,4},{0,-1}};
		int[][] input = {{1},{5},{9}};
		List<Integer> output = null;
		try {
			output = spiralOrderArray(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output); 
	}

	/**
	 * use array, calculate matrix[i][j]'s index in array
	 * maybe because use Arrays.stream to transfer arrays to List, it will cost more time(0ms->1ms) than spiralOrder
	 * 
	 * @param matrix
	 * @return
	 */
    public static List<Integer> spiralOrderArray(int[][] matrix) {
    	int width = matrix[0].length, height = matrix.length;
    	int[] result = new int[width * height];
    	int edge = 0, start = 0;
    	
    	while (edge * 2 < width && edge * 2 < height) {
    		int currWidth = width - 2 * edge, currHeight = height - 2 * edge;
    		int rightStart = start + currWidth - 1;
    		int bottomEnd = start + currWidth * 2 + currHeight - 3;
    		int leftEnd = start + currWidth * 2 + currHeight * 2 - 4;
//    		System.out.println("start:" + start); 
//    		System.out.println("rightStart:" + rightStart); 
//    		System.out.println("bottomEnd:" + bottomEnd); 
//    		System.out.println("leftEnd:" + leftEnd); 
    		// calculate the top and bottom row's index
    		for (int j = 0; j < currWidth; j++) {
    			result[start + j] = matrix[edge][j + edge];
    			// when currHeight is 1, there is only 1 row in this cycle, so ignore the bottom row
    			if (currHeight != 1) {
        			result[bottomEnd - j] = matrix[height - edge - 1][j + edge];
    			}
    		}
    		// calculate the right and left column's index
    		for (int i = 1; i < currHeight - 1; i++) {
    			result[rightStart + i] = matrix[i + edge][width - edge - 1];
    			// when currWidth is 1, there is only 1 column in this cycle, so ignore the left row
    			if (currWidth != 1) {
        			result[leftEnd - i] = matrix[i + edge][edge];
    			}
    		}
    		edge++;
    		start = leftEnd;
    	}
    	return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    /**
     * add right matrix[i][j] into result list in order
     * 
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> result = new ArrayList<>();
    	int edge = 0;
    	int width = matrix[0].length, height = matrix.length;
    	
    	while (edge * 2 < width && edge * 2 < height) {
    		// handle the top
    		for (int i = edge; i < width - edge; i++) {
//    			System.out.println("add1:" + matrix[edge][i]); 
    			result.add(matrix[edge][i]);
    		}
    		// handle the right
    		for (int j = edge + 1; j < height - edge; j++) {
//    			System.out.println("add2:" + matrix[j][width - edge - 1]); 
    			result.add(matrix[j][width - edge - 1]);
    		}
    		// handle the bottom
    		if (edge != height - edge - 1) {
        		for (int i = width - edge - 2; i >= edge; i--) {
//        			System.out.println("add3:" + matrix[height - edge - 1][i]); 
        			result.add(matrix[height - edge - 1][i]);
        		}
    		}
    		// handle the left
    		if (width - edge - 1 != edge) {
        		for (int j = height - edge - 2; j >= edge + 1; j--) {
//        			System.out.println("add4:" + matrix[j][edge]); 
        			result.add(matrix[j][edge]);
        		}
    		}
    		edge++;
    	}
    	return result;
    }
}
