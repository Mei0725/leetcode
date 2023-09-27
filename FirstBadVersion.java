package leetcode_test;

public class FirstBadVersion {
	// the function isBadVersion() is defined in the extends class
	// this function is just a example to avoid show error
	public boolean isBadVersion(int input) {
		return true;
	}
	
	/**
	 * take care of out of range of Integer
	 * 
	 * @param n
	 * @return
	 */
    public int firstBadVersion(int n) {
        int min = 1, max = n;
        int minBad = n;
        // if we use tmp=(max+min)/2, since the max value of max is Integer.MAX_VALUE-1, 
        // it may out of range and then make the function become overtime(get the loop value of tmp)
        // there are 2 way to handle this case:
        //       1.tmp=min+(max-min)/2
        //       2.identify tmp as long, but because the input and output is both int, 
        //         translation of int and long may spend more time(16ms->19ms) and more space(38.9MB->39.9MB)
        int tmp = min + (max - min) / 2;
        while (min <= max) {
            tmp = min + (max - min) / 2;
            if (!isBadVersion(tmp)) {
                if (minBad == tmp + 1) {
                    return minBad;
                }
                min = tmp + 1;
            } else {
                minBad = Math.min(minBad, tmp);
                max = tmp - 1;
            }
        }
        return tmp;
    }
}
