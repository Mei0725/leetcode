package leetcode_test;

public class WildcardMatch {

	public static void main(String[] args) {
//		String s = "aaa";
//		String p = "a*";
		String s = "aaa";
		String p = "a?*a";
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
	 * deal with the problem by DP
	 * get the result of result[][] from the end, when both s and p is empty, the result should be true
	 * for every point in result[i][j], it means if the result of s.substring(i, end) and p.substring(j, end) is true
	 * if p.charAt(j) is '*', then if result[i][j + 1] is true(* march no) or result[i + 1][j] is true(* march any chars), it is true
	 * if i + 1 > s.length()(s is empty), it can only march to p.charAt(j) is '*' and result[i][j + 1] is true(the rest of j are all '*')
	 * else, it should check if the local position is true(p.charAt(j) is '*' || s.charAt(i) equal to p.charAt(j) || p.charAt(j) is '?'),
	 *       if local position is true and result[i + 1][j + 1] is true(the rest of s and p are matched), it is true
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
    public static boolean isMatch(String s, String p) {
    	boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
    	result[s.length()][p.length()] = true;
    	
    	for(int i = s.length(); i >= 0; i--) {
    		for(int j = p.length() - 1; j >= 0; j--) {
    			if(i + 1 <= s.length() && p.charAt(j) == '*') {
    				result[i][j] = result[i][j + 1] || result[i + 1][j];
    			} else if (i + 1 > s.length()) {
    				result[i][j] = p.charAt(j) == '*' && result[i][j + 1];
    			} else {
        			boolean localMatch = p.charAt(j) == '*' || 
        					(i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'));
    				result[i][j] = localMatch && result[i + 1][j + 1];
    			}
    		}
    	}
        return result[0][0];
    }
}
