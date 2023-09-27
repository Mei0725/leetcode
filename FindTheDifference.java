package leetcode_test;

public class FindTheDifference {

	/**
	 * use chs[] to store the number of letters in s, and then check letters in t.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
    public char findTheDifference(String s, String t) {
        char[] chs = new char[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            chs[ch - 'a']++;
        }
        
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (chs[ch - 'a'] == 0) {
                return ch;
            }
            chs[ch - 'a']--;
        }
        return '-';
    }
}
