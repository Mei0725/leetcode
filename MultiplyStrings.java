package leetcode_test;

public class MultiplyStrings {

	public static void main(String[] args) {
//		String num1 = "123";
//		String num2 = "456";
		String num1 = "9133";
		String num2 = "0";
//		String num1 = "3";
//		String num2 = "0";
		String output = "0";
		try {
			output = multiply(num1, num2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}

	/**
	 * get every bit's result, and then place then in the right sort
	 * get every bit's value by dividing nums into (100 + 20 + 3) * (400 + 50 + 6)
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
    public static String multiply(String num1, String num2) {
    	// to handle the case that one of num is 0
    	// it can also be handle in creating result string by remove 0 at the beginning of the result until the result is 0
    	// but handle 0 in this place would save some time, especially when the other num is very long
    	if (num1.equals("0") || num2.equals("0")) {
    		return "0";
    	}
    	
    	// to calculate every bit's value
    	// note: the bit in front of the num is bigger
    	int[] bitNum = new int[num1.length() + num2.length() - 1];
    	int length1 = num1.length() - 1, length2 = num2.length() - 1;
    	for (int i = 0; i < num1.length(); i++) {
    		for (int j = 0; j < num2.length(); j++) {
    			bitNum[i + j] += (num1.charAt(length1 - i) - '0') * (num2.charAt(length2 - j) - '0');
    		}
    	}
    	
    	String result = "";
    	int carry = 0;
    	for (int i = 0; i < bitNum.length; i++) {
    		int realBitNum = bitNum[i] + carry;
			result = (realBitNum % 10) + result;
			carry = realBitNum / 10;
//			System.out.println("result:" + result);
    	}
    	// do not forget to handle the final carry
    	if (carry != 0) {
    		result = carry + result;
    	}
        return result;
    }

}
