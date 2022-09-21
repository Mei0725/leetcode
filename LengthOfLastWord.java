package leetcode_test;

public class LengthOfLastWord {

	public static void main(String[] args) {
		String input = "   fly me   to   the moon  ";
//		String input = "";
//		String input = "a";
		int output = -1;
		try {
			output = lengthOfLastWord(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * a solution use less time(0ms), search from the last char of s to find the first ' ' which contains 1 word
	 * 
	 * @param s
	 * @return
	 */
    public static int lengthOfLastWord(String s) {
    	int length = 0;
    	for (int i = s.length() - 1; i >= 0; i--) {
    		if (s.charAt(i) == ' ') {
    			if (length == 0) {
    				continue;
    			} else {
    				break;
    			}
    		}
    		length++;
    	}
    	return length;
    }

    /**
     * split string by ' ' and get the length of last item(1ms)
     * 
     * @param s
     * @return
     */
    public static int lengthOfLastWordSplit(String s) {
    	String[] split = s.split(" ");
    	return split.length > 0 ? split[split.length - 1].length() : 0;
    }
}
