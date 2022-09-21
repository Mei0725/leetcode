package leetcode_test;

public class Search2DMatrix {

	public static void main(String[] args) {
//		int[][] input = {{1,3,5,7},{10,11,16,20}};
		int[][] input = {{1},{10}};
		int target = 10;
		boolean output = false;
		try {
			output = searchMatrix(input, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * binary search is used in both row and column, which can save some time(1ms->0ms)
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
    public static boolean searchMatrix(int[][] matrix, int target) {
    	int width = matrix[0].length, height = matrix.length;
    	int minY = 0, maxY = height - 1;
    	while (minY <= maxY) {
    		int middleY = (minY + maxY) / 2;
    		if (matrix[middleY][0] > target) {
    			maxY = middleY - 1;
    		} else if (matrix[middleY][width - 1] < target) {
    			minY = middleY + 1;
    		} else {
    			int minX = 0, maxX = width - 1;
        		while (minX <= maxX) {
        			int middle = (minX + maxX) / 2;
        			if (matrix[middleY][middle] < target) {
        				minX = middle + 1;
        			} else if (matrix[middleY][middle] > target) {
        				maxX = middle - 1;
        			} else {
        				return true;
        			}
        		}
        		return false;
    		}
    	}
    	return false;
    }

	/**
	 * binary search only used in row
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
    public static boolean searchMatrixRow(int[][] matrix, int target) {
    	int width = matrix[0].length, height = matrix.length;
    	for (int i = 0; i < height; i++) {
    		if (matrix[i][width - 1] < target) {
    			continue;
    		} else if (matrix[i][0] > target){
    			return false;
    		} else {
    			int min = 0, max = width - 1;
        		while (min <= max) {
        			int middle = (min + max) / 2;
        			if (matrix[i][middle] < target) {
        				min = middle + 1;
        			} else if (matrix[i][middle] > target) {
        				max = middle - 1;
        			} else {
        				return true;
        			}
        		}
    		}
    	}
        return false;
    }
}
