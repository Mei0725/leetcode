package leetcode_test;

public class DesignAddAndSearchWordsDataStructure {

	/**
	 * solve by tire.
	 * create a tire and store its root
	 * 
	 * @author meiyb
	 *
	 */
	class WordDictionary {
	    
		// ch will not used in any function, but it can help to understand this tire
	    char ch;
	    WordDictionary[] children;
	    // if this letter is the end of word, through it is true, it may also follows some children in other word.
	    boolean end;

	    // init
	    public WordDictionary() {
	        this.ch = '0';
	        this.children = new WordDictionary[26];
	        this.end = false;
	    }
	    
	    // add a word into tire
	    public void addWord(String word) {
	        WordDictionary local = this;
	        for (char ch : word.toCharArray()) {
	            int i = ch - 'a';
	            if (local.children[i] == null) {
	                local.children[i] = new WordDictionary();
	                local.children[i].ch = ch;
	            }
	            local = local.children[i];
	        }
	        local.end = true;
	    }
	    
	    // search if a word contains in this tire
	    public boolean search(String word) {
	    	// it is used when word has '.' in the end
	    	// in this case, this is seen as '.'
	    	// so no matter what letter it is, it can be true if this is word's end
	        if (word.length() == 0) {
	            return this.end;
	        }

	        WordDictionary local = this;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            if (ch != '.') {
	            	// handle the special letter
	                local = local.children[ch - 'a'];
	                if (local == null) {
	                    return false;
	                }
	            } else {
	            	// when ch is '.', then local will be seen as it
	            	// then it should check every letter if it has child match the rest of word(tmp)
	                String tmp = word.substring(i + 1);
	                for (WordDictionary child : local.children) {
	                    if (child == null) {
	                        continue;
	                    }
	                    if (child.search(tmp)) {
	                        return true;
	                    }
	                }
	                // if there is no child can match tmp, then it will return false.
	                return false;
	            }
	        }
	        // will only go to this code when all letters are definite
	        return local.end;
	    }
	}
}
