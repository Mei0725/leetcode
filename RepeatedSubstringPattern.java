package leetcode_test;

public class RepeatedSubstringPattern {

	/**
	 * check every letter, if letter n equals to the first letter, check if substring[0,n) is repeated.
	 * 
	 * @param s
	 * @return
	 */
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(0) == s.charAt(i) && repeatedSubstringPattern(i, s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if substring[0,n-1] is the repeated substring.
     * 
     * @param n
     * @param s
     * @return
     */
    private boolean repeatedSubstringPattern(int n, String s) {
        int length = s.length();
        // in this case, it must not be repeated
        if (length % n != 0) {
            return false;
        }
        
        for (int i = n; i < length; i++) {
            if (s.charAt(i) != s.charAt(i % n)) {
                return false;
            }
        }
        return true;
    }
}
