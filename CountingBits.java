package leetcode_test;

public class CountingBits {

	/**
	 * solve by bit manipulation and dp.
	 * for count[i], the result is the count[i>>1](first n-1 bit) + final-bit-value.
	 * use (int fin 0 or 1) to instead of (i%2) can save space but spend more time.
	 * 
	 * @param n
	 * @return
	 */
    public int[] countBits(int n) {
        int[] count = new int[n + 1];
        if (n == 0) {
            return count;
        }
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            count[i] = count[i >> 1] + (i % 2);
            // can use this fin to instead of (i%2), but it will spend more time
            // fin = (fin + 1) % 2;
        }
        return count;
    }
}
