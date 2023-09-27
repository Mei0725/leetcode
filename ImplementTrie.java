package leetcode_test;

import java.util.HashMap;

public class ImplementTrie {
	
	public class TrieNode {
	    private HashMap<Character, TrieNode> children;
	    private boolean isWord;
	    
	    public TrieNode() {
	    	children = new HashMap<>();
	    }
	    
	    public HashMap<Character, TrieNode> getChildren() {
	    	return children;
	    }
	    
	    public void setEndOfWord(boolean isWord) {
	    	this.isWord = isWord;
	    }
	}

	private TrieNode root;
	
    public ImplementTrie() {
    	root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (char l: word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }
    
    public boolean search(String word) {
        TrieNode current = root;
        for (char l: word.toCharArray()) {
            current = current.getChildren().get(l);
            if (current == null) {
            	return false;
            }
        }
        return current.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char l: prefix.toCharArray()) {
            current = current.getChildren().get(l);
            if (current == null) {
            	return false;
            }
        }
        return true;
    }

}
