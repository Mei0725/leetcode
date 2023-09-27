package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {

	public static void main(String[] args) {
//		String s1 = "rgeat";
//		String s2 = "great";
//		String s1 = "abcde";
//		String s2 = "caebd";
//		String s1 = "abcdbdacbdac";
//		String s2 = "bdacabcdbdac";
//		String s1 = "abb";
//		String s2 = "bba";
//		String s1 = "abc";
//		String s2 = "acb";
		String s1 = "eebaacbcbcadaaedceaaacadccd";
		String s2 = "eadcaacabaddaceacbceaabeccd";
		boolean output = false;
		try {
			output = isScramble(s1, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * this solution is an optimization of isScrambleOverTime
	 * there are 2 optimizations:
	 * 1.replace the map to int[] to sort chars
	 * 2.add 4 number to mark the checked chars, and the next check behavior will start from mark to reduce the repeat operations
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
    public static boolean isScramble(String s1, String s2) {
//		System.out.println("s1: " + s1);
//		System.out.println("s2: " + s2);
    	if (s1.equals(s2)) {
    		return true;
    	} else if (s1.length() == 1) {
    		return false;
    	}
    	
    	int[] charsLeft = new int[26];
    	int[] charsRight = new int[26];
		int diffLeft = 0, diffRight = 0;
		int mark1 = 0, mark2 = s1.length(), mark3 = 0, mark4 = s2.length();
    	for (int i = 0; i < s1.length() - 1; i++) {
//    		System.out.println("i: " + i);
    		char ch1 = s1.charAt(i), ch2 = s2.charAt(i), ch3 = s2.charAt(s2.length() - 1 - i);
//    		System.out.println("ch1: " + ch1 + ", ch2: " + ch2 + ", ch3: " + ch3);
    		int i1 = ch1 - 'a', i2 = ch2 - 'a', i3 = ch3 - 'a';
    		if (ch1 != ch2 && diffLeft <= (s1.length() - 1 - i) * 2) {
        		if (charsLeft[i1] >= 0) {
        			diffLeft++;
        		} else {
        			diffLeft--;
        		}
        		charsLeft[i1]++;
        		if (charsLeft[i2] <= 0) {
        			diffLeft++;
        		} else {
        			diffLeft--;
        		}
        		charsLeft[i2]--;
    		}
//    		System.out.println("diffLeft: " + diffLeft);
    		if (diffLeft == 0) {
    			if (mark1 <= i + 1 && 
    					isScramble(s1.substring(mark1, i + 1), s2.substring(mark1, i + 1))) {
    				mark1 = i + 1;
    			}
    			if (mark2 >= i + 2 &&
    					isScramble(s1.substring(i + 1, mark2), s2.substring(i + 1, mark2))) {
    				mark2 = i + 1;
    			}
    			if (mark1 >= mark2) {
    				return true;
    			}
    		}

    		if (ch1 != ch3 && diffRight <= (s2.length() - 1 - i) * 2) {
        		if (charsRight[i1] >= 0) {
        			diffRight++;
        		} else {
        			diffRight--;
        		}
        		charsRight[i1]++;
        		if (charsRight[i3] <= 0) {
        			diffRight++;
        		} else {
        			diffRight--;
        		}
        		charsRight[i3]--;
    		}
//    		System.out.println("diffRight: " + diffRight);
    		if (diffRight == 0) {
    			if (mark3 <= i + 1 && isScramble(s1.substring(mark3, i + 1), 
    					s2.substring(s2.length() - 1 - i, s2.length() - mark3))) {
    				mark3 = i + 1;
    			}
    			if (mark4 >= i + 1 && isScramble(s1.substring(i + 1, mark4), 
    					s2.substring(s2.length() - mark4, s2.length() - 1 - i))) {
    				mark4 = i + 1;
    			}
    			if (mark3 >= mark4) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    /**
     * for every char in s1 and s2, check if the chars in [0,i] and [length-i,length] are same.
     * if they are same, it means the substring have the same chars may be scrambled,
     * then check the substring in the same way.
     * this solution is overtime because when s1 and s2 is long enough, the same substring would be checked many times
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScrambleOverTime(String s1, String s2) {
//		System.out.println("s1: " + s1);
//		System.out.println("s2: " + s2);
    	if (s1.equals(s2)) {
    		return true;
    	} else if (s1.length() == 1) {
    		return false;
    	}
    	
    	// use int[] to instead of map is still overtime
    	Map<Character, Integer> charsLeft = new HashMap<>();
    	Map<Character, Integer> charsRight = new HashMap<>();
    	for (int i = 0; i < s1.length() - 1; i++) {
//    		System.out.println("i: " + i);
    		char ch1 = s1.charAt(i), ch2 = s2.charAt(i), ch3 = s2.charAt(s2.length() - 1 - i);
    		// check the start of s1 and start of s2
    		if (ch1 != ch2) {
        		if (charsLeft.containsKey(ch1)) {
        			int val = charsLeft.get(ch1) + 1;
        			if (val == 0) {
        				charsLeft.remove(ch1);
        			} else {
        				charsLeft.put(ch1, val);
        			}
        		} else {
        			charsLeft.put(ch1, 1);
        		}
        		if (charsLeft.containsKey(ch2)) {
        			int val = charsLeft.get(ch2) - 1;
        			if (val == 0) {
        				charsLeft.remove(ch2);
        			} else {
        				charsLeft.put(ch2, val);
        			}
        		} else {
        			charsLeft.put(ch2, -1);
        		}
    		}
    		if (charsLeft.isEmpty() 
    				&& isScramble(s1.substring(0, i + 1), s2.substring(0, i + 1)) 
    				&& isScramble(s1.substring(i + 1), s2.substring(i + 1))) {
    			return true;
    		}

    		// check the start of s1 and end of s2
    		if (ch1 != ch3) {
        		if (charsRight.containsKey(ch1)) {
        			int val = charsRight.get(ch1) + 1;
        			if (val == 0) {
        				charsRight.remove(ch1);
        			} else {
        				charsRight.put(ch1, val);
        			}
        		} else {
        			charsRight.put(ch1, 1);
        		}
        		if (charsRight.containsKey(ch3)) {
        			int val = charsRight.get(ch3) - 1;
        			if (val == 0) {
        				charsRight.remove(ch3);
        			} else {
        				charsRight.put(ch3, val);
        			}
        		} else {
        			charsRight.put(ch3, -1);
        		}
    		}
    		if (charsRight.isEmpty() 
    				&& isScramble(s1.substring(0, i + 1), s2.substring(s2.length() - 1 - i)) 
    				&& isScramble(s1.substring(i + 1), s2.substring(0, s2.length() - 1 - i))) {
    			return true;
    		}
    	}
    	return false;
    }
}
