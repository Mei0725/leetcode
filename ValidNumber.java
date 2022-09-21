package leetcode_test;;


public class ValidNumber {

	public static void main(String[] args) {
//		String input = "152e1.0";
//		String input = "+3.1";
		String input = "e";
		boolean output = false;
		try {
			output = isNumber(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * cut s into 2 parts by 'e', then check each part of s
	 * 
	 * @param s
	 * @return
	 */
    public static boolean isNumber(String s) {
    	s = s.toLowerCase();
    	int indexE = s.indexOf("e");
    	// cut s into 2 parts by 'e'
    	String s1 = s, s2 = "";
    	if (indexE != -1) {
        	s1 = s.substring(0, indexE);
        	s2 = s.substring(indexE + 1);
    	}
    	
    	// check s1 as decimal number
    	// s1 must exist
    	int indexDigit = s1.indexOf(".");
    	int digitNum = 0;
    	for (int i = 0; i < s1.length(); i++) {
    		char ch = s1.charAt(i);
    		if (ch == '+' || ch == '-') {
    			if (i != 0) {
    				return false;
    			} else {
    				continue;
    			}
    		} else if (i == indexDigit) {
    			continue;
    		} else if (ch - '0' < 0 || ch - '0' > 9) {
    			return false;
    		}
    		digitNum++;
    	}
    	// the decimal number must contain at least 1 digit
    	if (digitNum == 0) {
    		return false;
    	}
    	
    	// check s2 as integer
    	// check indexE but not s2.isEmpty to avoid invalid case like '2e'
    	if (indexE != -1) {
    		digitNum = 0;
    		for (int i = 0; i < s2.length(); i++) {
    			char ch = s2.charAt(i);
        		if (ch == '+' || ch == '-') {
        			if (i != 0) {
        				return false;
        			} else {
        				continue;
        			}
        		}
        		if (ch - '0' < 0 || ch - '0' > 9) {
        			return false;
        		}
        		digitNum++;
    		}
        	if (digitNum == 0) {
        		return false;
        	}
    	}
        return true;
    }
}
