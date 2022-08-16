package leetcode_test;

public class RegularExpressionMath {

	public static void main(String[] args) {
//		String s = "aa";
//		String p = "a";
//		String s = "ab";
//		String p = ".*c";
		String s = "aaa";
		String p = "ab*a*c*a";
//		String s = "a";
//		String p = "ab*";
		boolean output = true;
		try {
			output = isMatch(s, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * this solution deal with the problem by Dynamic Programming
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
    public boolean isMatchDP(String s, String p) {
    	// the arrays save the match result about s[i:] and p[j:]
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--){
            for (int j = p.length() - 1; j >= 0; j--){
                boolean first_match = (i < s.length() &&
                                       (p.charAt(j) == s.charAt(i) ||
                                        p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
	
	/**
	 * this solution deal with the problem by Recursion
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		// if p is empty, then t must be empty
        if (p.isEmpty()) 
        	return s.isEmpty();
        // when t is not empty, we can check if the first char of t and p is match
        boolean first_match = (!s.isEmpty() &&
                               (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        // when the first 2 chars of p is ?*, there are two match cases
        // 1.the ?* in p is not match to any char of t, then just remove ?* for p
        // 2.the ?* can match the first char of t(first_match is true), then we should check the string started by the second char of t
        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        } else {
        	// in this case, we should check the string started by the second char of t and p
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
	
    /*public static boolean isMatch(String s, String p) {
    	int indexS = 0, indexP = 0;
    	while (indexS < s.length() && indexP < p.length()) {
	    	char temp = p.charAt(indexP);
    		char pNext = '.';
	    	if (indexP != p.length() - 1) {
	    		pNext = p.charAt(indexP + 1);
	    	}
    		if (pNext == '*') {
				String subP = p.substring(indexP + 2);
    			if (temp == '.') {
    				if (subP.length() == 0) {
    					return true;
    				} else{
    					char subP0 = subP.charAt(0);
    					if (subP0 == '.') {
    						while (indexS < s.length()) {
        						if (isMatch(s.substring(indexS), subP)) {
        							return true;
        						}
        						indexS++;
    						}
        					return dealWithFinalP(p, indexP);
    					}
    					int subStart = indexS;
    					int newStartS = s.indexOf(subP0, subStart);
    					while (newStartS != -1) {
    						String subS = s.substring(newStartS);
    						if (isMatch(subS, subP)) {
    							return true;
    						}
    						subStart = newStartS + 1;
    						newStartS = s.indexOf(subP0, subStart);
    					}
    					return dealWithFinalP(p, indexP);
    				}
    			} else {
    		    	while (indexS < s.length() && s.charAt(indexS) == temp) {
    		    		if (isMatch(s.substring(indexS), subP)) {
    		    			return true;
    		    		}
    		    		indexS++;
    		    	}
    			}
		    	indexP +=2;
    		} else if (s.charAt(indexS) == temp || temp == '.') {
    			indexS++;
    			indexP++;
    		} else {
    			return false;
    		}
    	}

    	if (indexS == s.length()) {
    		if (indexP == p.length()) {
    			return true;
    		} else {
    			return dealWithFinalP(p, indexP);
    		}
    	} else {
    		return false;
    	}
    }
    
    public static boolean dealWithFinalP(String p, int indexP) {
		int tmpChar = 1;
		while (indexP < p.length()) {
			if (tmpChar % 2 == 0 && p.charAt(indexP) != '*') {
				return false;
			}
			indexP++;
			tmpChar++;
		}
		return tmpChar % 2 == 1;
    }*/
}
