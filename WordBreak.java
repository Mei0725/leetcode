package leetcode_test;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

	public static void main(String[] args) {
		String s = "leetcode";
		List<String> input = Arrays.asList("leet", "code");
		boolean output = false;
		try {
			output = wordBreak(s, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static class Tire {
        char ch;
        boolean isWord = false;
        // use Tire[] instead of Map can help to reduce time
        Tire[] children;

        public Tire(char c) {
            this.ch = c;
            this.isWord = false;
            this.children = new Tire[24];
        }
    }

    // used[n] to store if there are possible result of s.substring(0,n)
    // it is useful to reduce time(overtime -> 4ms) for case like 'aaaaaaaaaaaaaaaaaaaaaaaaaaaab',['a','aa','aaa'...]
    public static boolean[] used;
    /**
     * use tire to solve problem.
     * create a tire to store all wordDict and then check s by tire
     * 
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
    	// create tire as dictionary
        Tire root = new Tire('0');
        Tire local = root;
        for (String word : wordDict) {
            local = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (local.children[i] == null) {
                    local.children[i] = new Tire(ch);
                }
                local = local.children[i];
            }
            local.isWord = true;
        }
        // check s with tire
        used = new boolean[s.length()];
        return check(s.toCharArray(), 0, root, root);
    }

    /**
     * use backtracking to check s with dictionary
     * 
     * @param chars   use char[] instead of String.substring and String.charAt to reduce time
     * @param index   the location of string
     * @param root    the root of dictionary, is used to arrive at root when there is the end of a word
     * @param local   the location of dictionary
     * @return
     */
    public static boolean check(char[] chars, int index, Tire root, Tire local) {
        if (local == null) {
            // System.out.println("case1:" + index);
            return false;
        } else if (chars.length <= index) {
        	// usually, it will return when chars.length==index
            // System.out.println("case2:" + local.isWord);
            return local.isWord;
        } else if(local.isWord && !used[index]){
        	// to mark that the string.sub(0,index) has possible result
        	// so that the string following it will not be rechecked
            used[index] = true;
            if (check(chars, index, root, root)) {
            // System.out.println("case3:" + index);
                return true;
            }
        }
        char ch = chars[index];
        int i = ch - 'a';
        local = local.children[i];
        return check(chars, index + 1, root, local);
    }
}
