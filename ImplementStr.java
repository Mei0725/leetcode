package leetcode_test;

public class ImplementStr {

	public static void main(String[] args) {
//		String haystack = "hello";
//		String needle = "ll";
		String haystack = "a";
		String needle = "";
		int output = -1;
		try {
			output = strStr(haystack, needle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int strStr(String haystack, String needle) {
    	if (needle.isEmpty()) {
    		return 0;
    	}
    	
    	for (int i = 0; i <= haystack.length() - needle.length(); i++) {
    		if (needle.equals(haystack.substring(i, i + needle.length()))) {
    			return i;
    		}
    	}
    	return -1;
//    	return haystack.indexOf(needle);
    }

}
