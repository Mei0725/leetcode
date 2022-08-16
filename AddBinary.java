package leetcode_test;

public class AddBinary {

	public static void main(String[] args) {
//		String a = "11";
//		String b = "1";
		String a = "1010";
		String b = "1011";
		String output = "";
		try {
			output = addBinary(a, b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static String addBinary(String a, String b) {
    	String result = "";
    	int indexA = a.length() - 1, indexB = b.length() - 1;
    	boolean add = false;
    	while (indexA >= 0 || indexB >= 0) {
    		boolean chA = indexA >= 0 && a.charAt(indexA) == '1', chB = indexB >= 0 && b.charAt(indexB) == '1';
    		if (!add && !chA && !chB) {
    			add = false;
    			result = "0" + result;
    		} else if (add && chA && chB) {
    			add = true;
    			result = "1" + result;
    		} else if ((add && !chA && !chB) || (!add && chA && !chB) || (!add && !chA && chB)) {
    			add = false;
    			result = "1" + result;
    		} else {
    			add = true;
    			result = "0" + result;
    		}
    		indexA--;
    		indexB--;
    	}
    	
    	if (add) {
    		result = "1" + result;
    	}
    	return result;
    }
}
