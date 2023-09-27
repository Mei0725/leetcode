package leetcode_test;

public class ValidPalindrome {

	/**
	 * use 2 points check from start and end
	 * check if every 2 legal letters are equal.
	 * 
	 * @param s
	 * @return
	 */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        // can't simply check by Math.abs(a - b) because the digit is legal
        // and Math.abs('0' - 'P')==tmp
//        int tmp = Math.abs('a' - 'A');
        while (i < j) {
        	// find out the first legal letters
            while (i < s.length() && !checkIfLetter(s.charAt(i))) {
                i++;
            }
            while (j >= 0 && !checkIfLetter(s.charAt(j))) {
                j--;
            }
            // since i and j may change in the loop, before check if 2 letters are same,
            // check if they are legal firstly
            if (i < j && !checkIfSame(s.charAt(i), s.charAt(j))) {
                // System.out.println("i: " + i);
                // System.out.println("i: " + s.charAt(i));
                // System.out.println("j: " + s.charAt(j));
                return false;
            }
            // if (i < j) {
            //     System.out.println("i: " + s.charAt(i));
            //     System.out.println("j: " + s.charAt(j));
            // }
            i++;
            j--;
        }
        return true;
    }

    /**
     * check if ch is letter or digit.
     * 
     * @param ch
     * @return
     */
    public boolean checkIfLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')|| (ch >= '0' && ch <= '9');
    }

    /**
     * check if ch1 and ch2 are same, there are 2 cases:
     * 1.ch1==ch2;
     * 2.ch1 and ch2 are both letters and they can be lower-case or upper-case
     * 
     * @param ch1
     * @param ch2
     * @return
     */
    public boolean checkIfSame(char ch1, char ch2) {
        return (ch1 == ch2) || 
            (((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z')) 
                && ((ch2 >= 'a' && ch2 <= 'z') || (ch2 >= 'A' && ch2 <= 'Z')) 
                && Character.toLowerCase(ch1) == Character.toLowerCase(ch2));
    }
}
