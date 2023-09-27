package leetcode_test;

public class NumberOf1Bits {

	/**
	 * solve by bit manipulation.
	 * find out the final letter of n and keep >>1, until it becomes 0 or -1;
	 * 
	 * @param n
	 * @return
	 */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // System.out.println("init:" + n);
        int res = 0;
        if (n >= 0) {
            while(n != 0) {
                if (n % 2 == 1) {
                    res++;
                }
                n >>= 1;
            }
        } else {
        	// for minus, 2-bit value is the (inverse code of its abs)+1
        	// so the unchecked items are all 1, and we should minus the number of 0 letters
            res = 32;
            while (n != -1) {
                if (n % 2 == 0) {
                    res--;
                }
                n >>= 1;
            }
        }
        return res;
    }
}
