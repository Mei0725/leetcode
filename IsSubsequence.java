package leetcode_test;

public class IsSubsequence {

	/**
	 * solve by two pointers.
	 * 
	 * use index to store the index of s and i as the index of t, when index and i are the same, move to the next.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        
        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(index) == t.charAt(i)) {
                index++;
                if (index == s.length()) {
                    break;
                }
            }
        }
        return index == s.length();
    }
}
