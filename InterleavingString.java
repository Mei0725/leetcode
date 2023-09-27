package leetcode_test;

public class InterleavingString {

	public static void main(String[] args) {
//		String s1 = "aabcc";
//		String s2 = "dbbca";
//		String s3 = "aadbbcbcac";
//		String s1 = "aabcc";
//		String s2 = "dbbca";
//		String s3 = "aadbbbaccc";
//		String s1 = "aabcc";
//		String s2 = "dbbca";
//		String s3 = "aadbbbcacc";
//		String s1 = "aabc";
//		String s2 = "abad";
//		String s3 = "aabcabad";
//		String s1 = "aa";
//		String s2 = "ab";
//		String s3 = "aaba";
		String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
		String s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
		String s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
		boolean output = false;
		try {
			output = isInterleave(s1, s2, s3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve the problem by dp.
	 * use int[i][j] to store if the str s1.sub(0,i) and s2.sub(0,j) are interleave for s3.sub(0, int[i][j])
	 * for every s3.char(0, int[i][j]), if its char is equal to s1(i) and s2(j), then store it and check i+1 and j+1
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
    public static boolean isInterleave(String s1, String s2, String s3) {
    	if (s1.length() + s2.length() != s3.length()) {
    		return false;
    	}
    	
    	int[][] result = new int[s1.length() + 1][s2.length() + 1];
    	for (int i = 0; i <= s1.length(); i++) {
    		for (int j = 0; j <= s2.length(); j++) {
    			if (i == 0 && j == 0) {
    				continue;
    			} 
    			int tmp = 0;
    			if (j != 0 && s2.charAt(j - 1) == s3.charAt(result[i][j - 1])) {
    				tmp = Math.max(tmp, result[i][j - 1] + 1);
    			}
    			if (i != 0 && s1.charAt(i - 1) == s3.charAt(result[i - 1][j])) {
    				tmp = Math.max(tmp, result[i - 1][j] + 1);
    			}
    			result[i][j] = tmp;
    		}
    	}
    	return result[s1.length()][s2.length()] == s3.length();
    }
    
    /**
     * solve the problem by backtracking.
     * since isInterleaveOverTime is used too many time, it will overtime when s1 and s2 have many same chars and are long enough
     * 
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleaveOverTime(String s1, String s2, String s3) {
    	if (s1.length() + s2.length() != s3.length()) {
    		return false;
    	}
    	if (s3.isEmpty()) {
    		return true;
    	}
    	
    	if (!s1.isEmpty() && s1.charAt(0) == s3.charAt(0) && !s2.isEmpty() && s2.charAt(0) == s3.charAt(0)) {
    		return isInterleaveOverTime(s1.substring(1), s2, s3.substring(1)) || isInterleaveOverTime(s1, s2.substring(1), s3.substring(1));
    	} else if (!s1.isEmpty() && s1.charAt(0) == s3.charAt(0)) {
    		return isInterleaveOverTime(s1.substring(1), s2, s3.substring(1));
    	} else if (!s2.isEmpty() && s2.charAt(0) == s3.charAt(0)) {
    		return isInterleaveOverTime(s1, s2.substring(1), s3.substring(1));
    	}
    	return false;
    }

    /**
     * this solution can't handle case like "aa""ab""aaba","abab""abcd""ababcdab"
     * it is difficult to make sure which index should be move when i3=1 in the first case
     * 
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave0(String s1, String s2, String s3) {
    	if (s1.length() + s2.length() != s3.length()) {
    		return false;
    	}
    	
    	int i1 = 0, i2 = 0, i3 = 0;
    	while (i1 < s1.length() && i2 < s2.length()) {
    		char c1 = s1.charAt(i1), c2 = s2.charAt(i2), c3 = s3.charAt(i3);
    		if (c3 != c1 && c3 != c2) {
    			return false;
    		} else if (c3 == c1 && c3 == c2) {
    			int t1 = i1 + 1, t2 = i2 + 1;
    			i3++;
    			while (t1 < s1.length() && s3.charAt(i3) == s1.charAt(t1) &&
    					t2 < s2.length() && s3.charAt(i3) == s2.charAt(t2)) {
    				t1++;
    				t2++;
    				i3++;
    			}
    			if (t1 == s1.length() || s3.charAt(i3) == s1.charAt(t1)) {
    				i1 = t1;
    			} else {
    				i2 = t2;
    			}
    		} else if (c3 == c1) {
    			i1++;
    			i3++;
    		} else {
    			i2++;
    			i3++;
    		}
    	}

		while (i1 < s1.length()) {
			if (s1.charAt(i1) != s3.charAt(i3)) {
				return false;
			}
			i1++;
			i3++;
		}
		while (i2 < s2.length()) {
			if (s2.charAt(i2) != s3.charAt(i3)) {
				return false;
			}
			i2++;
			i3++;
		}
        return true;
    }
}
