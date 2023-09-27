package leetcode_test;

public class BuddyStrings {

	/**
	 * check all chars in s and goal, 
	 * there are 2 possible correct case:
	 * 1.for s and goal, there are 2 different char positions and they are swapping.
	 * 2.s equals to goal, and there are at least 2 same char so they can swap but do not change the string
	 * 
	 * @param s
	 * @param goal
	 * @return
	 */
    public boolean buddyStrings(String s, String goal) {
    	// if s and goal do not have the same length, it must be false;
        if (s.length() != goal.length()) {
            return false;
        }
        
        // the different position
        int diff = 0;
        // the first 2 different positions
        char[][] ch = new char[2][2];
        // the number of same chars
        int[] same = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == goal.charAt(i)) {
            	// store the number of every letter
                same[s.charAt(i) - 'a']++;
            } else {
            	// store the first 2 different letters
            	// and make sure the number of different letters is less than 2
                if (diff >= 2) {
                    return false;
                }
                ch[diff][0] = s.charAt(i);
                ch[diff][1] = goal.charAt(i);
                diff++;
            }
        }
        
        // check case 1
        if (diff == 1) {
            return false;
        } else if (diff == 2) {
            return ch[0][0] == ch[1][1] && ch[0][1] == ch[1][0];
        }
        
        //check case 2
        for (int i = 0; i < same.length; i++) {
            if (same[i] > 1) {
                return true;
            }
        }
        return false;
    }
}
