package leetcode_test;

public class DecodeWays {

	public static void main(String[] args) {
//		String input = "12";
//		String input = "7";
		String input = "06";
		int output = -1;
		try {
			output = numDecodings(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * solve this problem by DP.
	 * create result[][], result[0][i] means the num of s.sub(0,i)'s result that the last item is x, 
	 * result[1] means the num of s.sub(0,i)'s result that the last item is xx,
	 * so the total result of s.sub(0,i) is result[0][i]+result[1][i]
	 * 
	 * @param s
	 * @return
	 */
    public static int numDecodings(String s) {
    	int[][] result = new int[2][s.length()];
    	for (int i = 0; i < s.length(); i++) {
    		char ch = s.charAt(i);
    		// get the result[0][i]
    		// it depends on result of [i-1] when s.charAt(i) is not '0'
    		if (ch == '0') {
    			// when ch='0', it means ch can not be the last item
    			result[0][i] = 0;
    		} else if (i - 1 >= 0){
        		result[0][i] = result[0][i - 1] + result[1][i - 1];
    		} else {
    			// handle the case that i=0
    	    	result[0][i] = 1;
    		}
    		// get the result[1][i]
    		// it depends on result of [i-2] when the last two item is a valid num
    		if (i - 1 < 0) {
    			// handle the case that i=0
    			result[1][i] = 0;
    		} else {
    			char preCh = s.charAt(i - 1);
    			int num = (preCh - '0') * 10 + (ch - '0');
    			if (num < 10 || num > 26) {
    				result[1][i] = 0;
    			} else if (i - 2 >= 0){
    				result[1][i] = result[0][i - 2] + result[1][i - 2];
    			} else {
        			// handle the case that i=1
    				result[1][i] = 1;
    			}
    		}
    		// if both result[0][i] and result[1][i] is 0, it means the ch in i is invalid,
    		// which means the final result must be 0
    		if (result[0][i] == 0 && result[1][i] == 0) {
    			return 0;
    		}
    	}
        return result[0][s.length() - 1] + result[1][s.length() - 1];
    }

}
