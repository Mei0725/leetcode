package leetcode_test;

import java.util.Stack;

public class DecodedStringAtIndex {

	/**
	 * use stack to store the information about s.
	 * 
	 * for example, the input "leet2code3" will be store as:
	 * 			{{sb:"leet", repeat:2, length:8},
	 * 			 {sb:"code", repeat:3, length:36}}
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
    public String decodeAtIndex(String s, int k) {
        Stack<SubString> letters = new Stack<>();
        // mark that for a lowercase English letter, if it should be added as a new SubString
        boolean add = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z'){
                SubString sub;
                if (add) {
                    sub = new SubString();
                    // the total string length should include the all previous substrings
                    if (!letters.isEmpty()) {
                        sub.length = letters.peek().length;
                    }
                } else {
                    sub = letters.pop();
                }
                sub.sb.append(ch);
                sub.length++;
                add = false;
                letters.add(sub);
            } else {
                int times = ch - '0';
                SubString sub = letters.peek();
                sub.repeat *= times;
                sub.length *= times;
                add = true;
            }

            if (letters.peek().length >= k) {
                break;
            }
        }

        long longK = (long)k;
        while (!letters.isEmpty()) {
            SubString sub = letters.pop();
            // System.out.println("length:" + sub.length + ", repeat:" + sub.repeat);
            long subLength = sub.length / sub.repeat;
            // beacause k is 1-indexed, it should handle the case 0
            longK = longK % subLength;
            if (longK == 0) {
                longK = subLength;
            }
            // check if the output is in current substring
            long index = sub.sb.length() - subLength + longK - 1;
            if (index >= 0) {
                return sub.sb.substring((int)index, (int)index + 1);
            }
        }
        return "";
    }
}

/**
 * use it to store the information of string.
 */
class SubString{
	// the string value of current substring
    StringBuilder sb;
    // the times of current substring repeat
    long repeat;
    // the length of total string
    long length;

    public SubString() {
        this.sb = new StringBuilder();
        this.repeat = 1;
        this.length = 0;
    }
}