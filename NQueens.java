package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {
//		List<String> currQueens  = new ArrayList<>();
//		currQueens.add(".Q..");
//		currQueens.add("...Q");
//		currQueens.add("Q...");
//		currQueens.add("..Q.");
//		System.out.println("currQueens:" + currQueens);
//		System.out.println("test:" + validQueens(currQueens, 2));
		int input = 4;
		List<List<String>> output = null;
		try {
			output = solveNQueens(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (List<String> result : output) {
			System.out.print(result + ","); 
		}
	}

	/**
	 * use backtracking to solve this problem.
	 * create all possible lines to save time and space(there is no need to check row and column)
	 * using boolean[][] to replace List<List<String>> can save time and space
	 * 
	 * @param n
	 * @return
	 */
    public static List<List<String>> solveNQueens(int n) {
    	// create all possible lines
    	List<String> lineList = new ArrayList<>();
    	for (int i = 0; i < n; i++) {
    		char[] chars = new char[n];
    		Arrays.fill(chars, '.');
    		chars[i] = 'Q';
    		lineList.add(String.valueOf(chars));
    	}
    	
        List<List<String>> result = new ArrayList<>();
        createQueens(new ArrayList<>(), lineList, result, n);
        return result;
    }
    
    public static void createQueens(List<String> currQueens, List<String> lines, List<List<String>> result, int n) {
    	if (currQueens.size() == n) {
    		result.add(new ArrayList<>(currQueens));
    		return;
    	}
    	
    	List<String> tmpLines = new ArrayList<>(lines);
    	for (String line : tmpLines) {
    		currQueens.add(line);
    		lines.remove(line);
//			System.out.println("currQueens:" + currQueens); 
    		if (validQueens(currQueens, line.indexOf('Q'))) {
    			createQueens(currQueens, lines, result, n);
    		}
    		currQueens.remove(line);
    		lines.add(line);
    	}
    }
    
    /**
     * check if queens is valid for new line, whose 'Q' is in i
     * since the invalid possibility in row and column have avoided when create, we should only check diagonals
     * 
     * @param queens
     * @param i
     * @return
     */
    public static boolean validQueens(List<String> queens, int i) {
    	int col0 = i - 1;
    	int col1 = i + 1;
    	int row = queens.size() - 2;
    	while (row >= 0 && (col0 >= 0 || col1 < queens.get(0).length())) {
    		String rowLine = queens.get(row);
    		if ((col0 >= 0 && rowLine.charAt(col0) == 'Q') 
    				|| (col1 < rowLine.length() && rowLine.charAt(col1) == 'Q')) {
    			return false;
    		}
    		col0--;
    		col1++;
    		row--;
    	}
    	return true;
    }
}
