package leetcode_test;

import java.util.Arrays;

public class RotateImage {

	public static void main(String[] args) {
//		int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] input = {{1}};
		try {
			rotate(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: "); 
		for (int i = 0; i < input.length; i++) {
			System.out.print("["); 
			for (int j = 0; j < input.length; j++) {
				System.out.print(input[i][j] + ","); 
			}
			System.out.print("]"); 
		}
	}

	public static void rotate(int[][] matrix) {
        int board = 0;
        // solve the problem from outer layer to inner layer
        while (board * 2 + 2 <= matrix.length) {
        	int end = matrix.length - 1 - board;
        	// store the original value of row to avoid they change after the 1st step
        	int[] tmp1 = Arrays.copyOfRange(matrix[board], board, end);
        	int[] tmp2 = Arrays.copyOfRange(matrix[end], board + 1, end + 1);
        	// 1st step: handle the rows, pay attention to the 4 corners should be changed once
        	for (int i = board; i < end; i++) {
        		matrix[board][i] = matrix[matrix.length - 1 - i][board];
        		matrix[end][i + 1] = matrix[matrix.length - 1 - i - 1][end];
        	}
        	// 2nd step: handle the columns
        	for (int i = board; i < end; i++) {
        		matrix[i + 1][board] = tmp2[i - board];
        		matrix[i][end] = tmp1[i - board];
        	}
        	board++;
        }
    }
}
