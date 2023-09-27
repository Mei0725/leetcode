package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearch2 {
	
	public static class TrieNode {
		// use TrieNode[] instead of Map can save time(2204ms->685ms) and space(144.8MB->118.2MB)
//	    private Map<Character, TrieNode> children;
	    private TrieNode[] children = new TrieNode[26];
	    private boolean isWord = false;
	    private boolean isAdd = false;
	    
	    public TrieNode() {
	    	Arrays.fill(children, null);
	    }
	    
	    public TrieNode[] getChildren() {
	    	return children;
	    }
	    
	    public boolean getEndOfWord() {
	    	return isWord;
	    }
	    
	    public void setEndOfWord(boolean isWord) {
	    	this.isWord = isWord;
	    }
	    
	    public boolean isAdded() {
	    	return this.isAdd;
	    }
	    
	    public void setAdded(boolean isAdd) {
	    	this.isAdd = isAdd;
	    }
	}

	public static void main(String[] args) {
		char[][] input = {{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}};
		String[] word = {"abccs", "abcc"};
		List<String> output = null;
		try {
			output = findWords(input, word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static List<String> result = new ArrayList<>();
    
    /**
     * solve this problem by trie
     * 
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {
    	TrieNode trie = new TrieNode();
    	// create trie
    	for (String word : words) {
    		TrieNode current = trie;
    		for (char ch : word.toCharArray()) {
//    			if (!current.getChildren().containsKey(ch)) {
//    				current.getChildren().put(ch, new TrieNode());
//    			}
//				current = current.getChildren().get(ch);
    			int index = ch - 'a';
    			if (current.getChildren()[index] == null) {
    				current.getChildren()[index] = new TrieNode();
    			}
    			current = current.getChildren()[index];
    		}
    		current.setEndOfWord(true);
    	}

    	for (int x = 0; x < board.length; x++) {
    		for (int y = 0; y < board[0].length; y++) {
    			// if want to save space, we can change used board[x][y] to '$' and change it back after searching
    	    	boolean[][] used = new boolean[board.length][board[0].length];
    	    	exist(board, trie, used, x, y, "");
    	    	// it can break cycle earlier(but it is almost not used
				if (result.size() == words.length) {
					break;
				}
    		}
			if (result.size() == words.length) {
				break;
			}
    	}
        return result;
    }

	public static void exist(char[][] board, TrieNode trie, boolean[][] used, int x, int y, String word) {
		int index = board[x][y] - 'a';
		// save time in use cycle to find out board[x][y] in children
		if (trie.getChildren()[index] == null) {
			return;
		}
		used[x][y] = true;
		word = word + board[x][y];
		TrieNode current = trie.getChildren()[index];
		if (current.getEndOfWord() && !current.isAdded()) {
			current.setAdded(true);
			result.add(word);
			// if use map, remove the added trie to reduce time
//    		if (current.getChildren().isEmpty()) {
//    			remove.add(ch);
//    		}
//    	} else if (current.getChildren().isEmpty() && !current.getEndOfWord()) {
//    		remove.add(ch);
//    		continue;
		}
		// the follow steps should run even through word+ch is added to handle the case
		// such as 'app && apple'
		if (x > 0 && !used[x - 1][y]) {
			exist(board, current, used, x - 1, y, word);
		}
		if (y > 0 && !used[x][y - 1]) {
			exist(board, current, used, x, y - 1, word);
		}
		if (x < board.length - 1 && !used[x + 1][y]) {
			exist(board, current, used, x + 1, y, word);
		}
		if (y < board[0].length - 1 && !used[x][y + 1]) {
			exist(board, current, used, x, y + 1, word);
		}
		used[x][y] = false;
	}
    
    /**
     * this solution is as same as WordSearch, but it is time limit exceeded.
     * search the words one by one would spend more time.
     * 
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWordsOvertime(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
    	boolean[][][] used = new boolean[words.length][board.length][board[0].length];
    	for (int x = 0; x < board.length; x++) {
    		for (int y = 0; y < board[0].length; y++) {
    			for (int i = 0; i < words.length; i++) {
    				if (result.contains(words[i])) {
    					continue;
    				}
    				char ch = words[i].charAt(0);
    				if (ch == board[x][y]) {
        				used[i][x][y] = true;
        				if (exist(board, words[i], used[i], 1, x, y)) {
        					result.add(words[i]);
        				}
        				used[i][x][y] = false;
    				}
    			}
    		}
    	}
        return result;
    }

    public static boolean exist(char[][] board, String word, boolean[][] used, int index, int x, int y) {
    	if (index == word.length()) {
    		return true;
    	}
    	char ch = word.charAt(index);
    	if (x > 0 && board[x - 1][y] == ch && !used[x - 1][y]) {
    		used[x - 1][y] = true;
    		if (exist(board, word, used, index + 1, x - 1, y)) {
    			return true;
    		}
    		used[x - 1][y] = false;
    	}
    	if (y > 0 && board[x][y - 1] == ch && !used[x][y - 1]) {
    		used[x][y - 1] = true;
    		if (exist(board, word, used, index + 1, x, y - 1)) {
    			return true;
    		}
    		used[x][y - 1] = false;
    	}
    	if (x < board.length - 1 && board[x + 1][y] == ch && !used[x + 1][y]) {
    		used[x + 1][y] = true;
    		if (exist(board, word, used, index + 1, x + 1, y)) {
    			return true;
    		}
    		used[x + 1][y] = false;
    	}
    	if (y < board[0].length - 1 && board[x][y + 1] == ch && !used[x][y + 1]) {
    		used[x][y + 1] = true;
    		if (exist(board, word, used, index + 1, x, y + 1)) {
    			return true;
    		}
    		used[x][y + 1] = false;
    	}
        return false;
    }
}
