package leetcode_test;

public class RegularExpressionMath {

	public static void main(String[] args) {
		String s = "aa";
		String p = "a";
		boolean output = true;
		try {
			output = isMatch(s, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static boolean isMatch(String s, String p) {
    	int indexS = 0, indexP = 0;
    	for (indexP = 0; indexP < p.length(); indexP++) {
    		switch(p.charAt(indexP)) {
    		    case '.':
    			    indexS++;
    			    continue;
    		    case '*':
    		    	char temp = s.charAt(indexP - 1);
    		    	while (s.charAt(indexS) == temp) {
    		    		indexS++;
    		    	}
    		    	continue;
    		    default:
    		    	if (s.charAt(indexS) != p.charAt(indexP)) {
    		    		return false;
    		    	}
    		}
    	}
    	return true;
    }
}
