package leetcode_test;

public class StringToInteger {

	public static void main(String[] args) {
		String input = "-121";
//		String input = "  -42";
//		String input = "words and 987";
		int output = 0;
		try {
			output = myAtoi(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int myAtoi(String s) {
    	int result = 0;
    	char[] chars = s.toCharArray();
    	boolean start = false, negative = false;
    	try {
        	for(int i = 0; i < s.length(); i++) {
        		if (!start && chars[i] == ' ') {
//            		System.out.println("chars[i] is null");
        			continue;
        		} else if (!start && chars[i] == '-') {
//            		System.out.println("chars[i] is -: " + chars[i]);
        			negative = !negative;
        			start = true;
        		} else if (!start && chars[i] == '+') {
//            		System.out.println("chars[i] is +: " + chars[i]);
        			start = true;
        		} else if (!Character.isDigit(chars[i])) {
//            		System.out.println("chars[i] is not digit: " + chars[i]);
        			break;
        		} else if (Character.isDigit(chars[i])) {
//            		System.out.println("chars[i] is digit: " + chars[i]);
        			start = true;
        			result = Math.addExact(Math.multiplyExact(10, result), Character.getNumericValue(chars[i]));
        		}
        	}
    	} catch (ArithmeticException e) {
//    		System.out.println("result is out of range");
    		return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    	}
    	return negative ? -result : result;
    }
}
