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
