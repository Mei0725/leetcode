package leetcode_test;

public class Pow {

	public static void main(String[] args) {
//		double x = 2;
//		int n = -2;
		double x = -1;
		int n = -2147483648;
		double output = 0;
		try {
			output = myPow(x, n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output); 
	}
	
	/**
	 * use Recursion can save some time(2ms->1ms)
	 * maybe it's because it saves time to transfer n to 2-Binary String?
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPowRecursion(double x, int n) {
		if (n == 0)
			return 1;
		// (n = -n) can not handle Integer.MIN_VALUE, so deal with it individually
		if (n == Integer.MIN_VALUE) {
			x = 1 / x;
			return myPow(x * x, -(n / 2));
		}

		if (n < 0) {
			n = -n; // as 2^-2=1/(2^2)
			x = 1 / x;
		}
		if (n % 2 == 0)
			return myPow(x * x, n / 2); // as 2^4=(2^2)^2;
		return x * myPow(x * x, n / 2); // as 2^3=2*(2^2);
    }
	
	/**
	 * transfer n to 2-Binary String so that the number of cycles can be reduced
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow(double x, int n) {
		String bit2N;
		// Math.abs can not handle Integer.MIN_VALUE, so deal with it individually
		if (n == Integer.MIN_VALUE) {
			bit2N = "10000000000000000000000000000000";
		} else {
			bit2N = Integer.toBinaryString(Math.abs(n));
		}
		
		double result = 1;
		double tmp = n < 0 ? 1 / x : x;
		for (int i = bit2N.length() - 1; i >= 0; i--) {
			if (bit2N.charAt(i) == '1') {
				result *= tmp;
			}
			tmp *= tmp;
		}
		return result;
	}
	
	public static double myPowOvertime(double x, int n) {
		int i = 0;
		double result = 1;
		while (i < Math.abs(n)) {
			result *= x;
			i++;
		}
		return n < 0 ? 1 / result : result;
	}
}
