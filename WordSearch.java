package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {

	public static void main(String[] args) {
		char[][] input = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCEDE";
		boolean output = false;
		try {
			output = exist(input, word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by backtracking.
	 * to save time, can use boolean[][] to store if board[][] is used, instead of List
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
    public static boolean exist(char[][] board, String word) {
    	char ch = word.charAt(0);
    	List<Integer> used = new ArrayList<>();
    	for (int x = 0; x < board.length; x++) {
    		for (int y = 0; y < board[0].length; y++) {
    			if (board[x][y] == ch) {
    				used.add(x * 10 + y);
    				if (exist(board, word, used, 1)) {
    					return true;
    				}
    	    		used.remove(used.size() - 1);
    			}
    		}
    	}
        return false;
    }

    public static boolean exist(char[][] board, String word, List<Integer> used, int index) {
    	if (index == word.length()) {
    		return true;
    	}
    	char ch = word.charAt(index);
    	int last = used.get(used.size() - 1);
    	int x = last / 10, y = last % 10;
    	if (x > 0 && board[x - 1][y] == ch && !used.contains(last - 10)) {
    		used.add(last - 10);
    		if (exist(board, word, used, index + 1)) {
    			return true;
    		}
    		used.remove(used.size() - 1);
    	}
    	if (y > 0 && board[x][y - 1] == ch && !used.contains(last - 1)) {
    		used.add(last - 1);
    		if (exist(board, word, used, index + 1)) {
    			return true;
    		}
    		used.remove(used.size() - 1);
    	}
    	if (x < board.length - 1 && board[x + 1][y] == ch && !used.contains(last + 10)) {
    		used.add(last + 10);
    		if (exist(board, word, used, index + 1)) {
    			return true;
    		}
    		used.remove(used.size() - 1);
    	}
    	if (y < board[0].length - 1 && board[x][y + 1] == ch && !used.contains(last + 1)) {
    		used.add(last + 1);
    		if (exist(board, word, used, index + 1)) {
    			return true;
    		}
    		used.remove(used.size() - 1);
    	}
        return false;
    }
}
