package leetcode_test;

public class FindTheHighestAltitude {

	/**
	 * use high to store the current altitude and max to store the highest altitude
	 * 
	 * @param gain
	 * @return
	 */
    public int largestAltitude(int[] gain) {
        int high = 0;
        int max = high;
        for (int i = 0; i < gain.length; i++) {
            high += gain[i];
            max = Math.max(high, max);
        }
        return max;
    }
}
