package leetcode_test;

public class DivideTwoIntegers {

	public static void main(String[] args) {
		int dividend = 7;
		int divisor = -3;
//		int dividend = -1;
//		int divisor = -1;
//		int dividend = -2147483647;
//		int divisor = -2147483648;
//		int dividend = -2147483648;
//		int divisor = -1109186033;
		int output = -1;
		try {
			output = divide(dividend, divisor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	public static int divideBit(int dividend, int divisor) {
		if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE ;
        boolean ifNegative = dividend < 0 ^ divisor < 0  ;
        int quotient = 0 ;
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        while(dividend - divisor >= 0)
        {
            int j = 0 ;
            while(dividend - (divisor << j << 1) >= 0)
                j++;
            
            quotient += 1 << j ;
            dividend -= divisor << j ;
        }
        return ifNegative ? -quotient : quotient ;
	}
	
	public static int divide(int dividend, int divisor) {
    	if (dividend == Integer.MIN_VALUE && divisor == -1) {
    		return Integer.MAX_VALUE;
    	}
    	
		String binDividend = Integer.toBinaryString(Math.abs(dividend));
		String resultStr = "";
    	if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
    		resultStr = "-";
    	}
    	int absDivisor = -Math.abs(divisor);
		int remainder = 0;
		System.out.println("binDividend: " + binDividend);
		System.out.println("divisor: " + divisor);
		for (int i = 0; i < binDividend.length(); i++) {
			remainder += remainder;
			if (binDividend.charAt(i) == '1') {
				remainder -= 1;
			}
			System.out.println("remainder: " + remainder);
			if (remainder <= absDivisor) {
				resultStr = resultStr + "1";
				remainder -= absDivisor;
			} else {
				resultStr = resultStr + "0";
			}
		}
		System.out.println("resultStr: " + resultStr);
		return Integer.valueOf(resultStr, 2);
	}
	
	/**
	 * solve this problem by translating * to +, but it is time limit exceeded when the input is Integer.MAX and 1
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    public static int divideTimeout(int dividend, int divisor) {
    	if (dividend == Integer.MIN_VALUE && divisor == -1) {
    		return Integer.MAX_VALUE;
    	}
    	
    	boolean negative = false;
    	if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
    		negative = true;
    	}
    	
    	int abs = 0;
    	int total = 0;
    	if (dividend < 0) {
    		total = negative ? total - divisor : total + divisor;
    		int oversize = Integer.MIN_VALUE + Math.abs(divisor);
        	while (total >= dividend && total >= oversize) {
        		abs = negative ? abs - 1 : abs + 1;
        		total = negative ? total - divisor : total + divisor;
        	}
        	if (total < oversize) {
        		abs = negative ? abs - 1 : abs + 1;
        	}
    	} else {
    		total = negative ? total - divisor : total + divisor;
    		int oversize = Integer.MAX_VALUE - Math.abs(divisor);
        	while (total <= dividend && total <= oversize) {
        		abs = negative ? abs - 1 : abs + 1;
        		total = negative ? total - divisor : total + divisor;
        	}
        	if (total > oversize) {
        		abs = negative ? abs - 1 : abs + 1;
        	}
    	}
    	
		return abs;
    }

}
