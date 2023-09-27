package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class WordBreak2 {
	
	class TireNode {
	    boolean end = false;
	    TireNode[] leaves = new TireNode[24];

	    TireNode() {
	        this.end = false;
	        this.leaves = new TireNode[24];
	    }
	}
	
	/**
	 * use tire and backtracking to solve problem
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public List<String> wordBreak(String s, List<String> wordDict) {
    	// create tire
        TireNode root = createTire(wordDict);
        List<String> result = new ArrayList<>();
        checkBreak(result, root, root, "", s, 0);
        return result;
    }

    public TireNode createTire(List<String> wordDict) {
        TireNode root = new TireNode();
        for (String word : wordDict) {
            char[] chars = word.toCharArray();
            TireNode cur = root;
            for (int i = 0; i < chars.length; i++) {
                TireNode leaf = cur.leaves[chars[i] - 'a'];
                if (leaf == null) {
                    leaf = new TireNode();
                    cur.leaves[chars[i] - 'a'] = leaf;
                }
                cur = leaf;
            }
            cur.end = true;
        }
        return root;
    }

    /**
     * 
     * @param result  to store results
     * @param root    the root of tire, pass it so that it is easy to restart in the end of word 
     * @param cur     the current position of tire
     * @param word    the current result word
     * @param s       the given string
     * @param index   the index of s
     */
    public void checkBreak(List<String> result, TireNode root, TireNode cur, String word, String s, int index) {
        if (index >= s.length()) {
            if (cur.end)
                result.add(word);
            return;
        }

        char ch = s.charAt(index);
        int i = ch - 'a';
        if (cur.leaves[i] == null) {
            return;
        }
        cur = cur.leaves[i];
        word = word + ch;
        if (cur.end) {
            checkBreak(result, root, root, word + " ", s, index + 1);
        }
        checkBreak(result, root, cur, word, s, index + 1);
    }
}
