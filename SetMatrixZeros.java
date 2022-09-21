package leetcode_test;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeros {

	public static void main(String[] args) {
		int[][] input = {{1,1,1},{1,0,1},{1,1,1}};
		try {
			setZeroes(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + input);
	}
	

	/**
	 * solve this problem by traversing only once.
	 * for any matrix[i][j] == 0, assign the left and top item to 0, and then mark its i and j
	 * for the later items, if its i or j is marked, assign it to 0
	 * 
	 * @param matrix
	 */
    public static void setZeroes(int[][] matrix) {
    	int x = matrix.length, y = matrix[0].length;
    	boolean[] markY = new boolean[y];
    	
    	for (int i = 0; i < x; i++) {
    		boolean rowZero = false;
    		for (int j = 0; j < y; j++) {
    			if (matrix[i][j] == 0) {
    				if (!rowZero) {
    					rowZero = true;
    					for (int tmpj = 0; tmpj < j; tmpj++) {
    						matrix[i][tmpj] = 0;
    					}
    				}
    				if (!markY[j]) {
    					markY[j] = true;
        				for (int tmpi = 0; tmpi < i; tmpi++) {
        					matrix[tmpi][j] = 0;
        				}
    				}
    			} else {
    				if (rowZero || markY[j]) {
    					matrix[i][j] = 0;
    				}
    			}
    		}
    	}
    }

	/**
	 * easy solution, but it would spend lots of time and space
	 * 
	 * @param matrix
	 */
    public static void setZeroes1(int[][] matrix) {
    	Set<Integer> zeroX = new HashSet<>(), zeroY = new HashSet<>();
    	int x = matrix.length, y = matrix[0].length;
    	for (int i = 0; i < x; i++) {
    		for (int j = 0; j < y; j++) {
    			if (matrix[i][j] == 0) {
    				zeroX.add(i);
    				zeroY.add(j);
    			}
    		}
    	}
    	
    	for (Integer i : zeroX) {
    		for (int j = 0; j < y; j++) {
    			matrix[i][j] = 0;
    		}
    	}
    	for (Integer j : zeroY) {
    		for (int i = 0; i < x; i++) {
    			matrix[i][j] = 0;
    		}
    	}
    }

}
