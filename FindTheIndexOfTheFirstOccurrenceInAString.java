package leetcode_test;

public class FindTheIndexOfTheFirstOccurrenceInAString {

	/**
	 * solve the problem by rolling hash
	 */
	// PRIME should be larger than any element, since there are only characters in haystack and needle
	// the min value of it should be 27(see 'a' as 1)(every element is larger than 0)
    private static final int PRIME = 32;
    // is used to make sure the hash value is in the range of long
    private static final int MOD = 1_000_000_007;

    public int strStrByRollingHash(String haystack, String needle) {
        long resHash = 0, tmpHash = 0;
        char[] ch1 = needle.toCharArray(), ch2 = haystack.toCharArray();
        // make sure haystack is longer than or similar to needle
        if (ch1.length > ch2.length) {
            return -1;
        }
        
        // calculate the hash value of needle and the first needle.length chars in haystack
        for (char i = 0; i < ch1.length; i++) {
            resHash = resHash * PRIME + (ch1[i] - 'a' + 1);
            resHash %= MOD;
            tmpHash = tmpHash * PRIME + (ch2[i] - 'a' + 1);
            tmpHash %= MOD;
        }
        if (tmpHash == resHash) {
            return 0;
        }
        
        // power is used in every loop, so get and store it in advance
        long power = 1;
        for (int i = 0; i < ch1.length - 1; i++) {
            power = (power * PRIME) % MOD;
        }
        
        for (int i = 0, j = ch1.length; j < ch2.length; i++, j++) {
        	// minus the hash value of the first char
            tmpHash = tmpHash - ((ch2[i] - 'a' + 1) * power % MOD) + MOD;
            tmpHash = tmpHash * PRIME + (ch2[j] - 'a' + 1);
            tmpHash %= MOD;
            if (tmpHash == resHash) {
                return i + 1;
            }
        }
    	return -1;
    }
    
    /**
     * solve the problem by check every substring
     * this solution will spend more time(1ms->0ms) and more space(42.5MB->40.3MB)
     * the space and time is similar to String.indexOf
     * 
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrBySubstring(String haystack, String needle) {
    	if (needle.isEmpty()) {
    		return 0;
    	}
    	
    	for (int i = 0; i <= haystack.length() - needle.length(); i++) {
    		if (needle.equals(haystack.substring(i, i + needle.length()))) {
    			return i;
    		}
    	}
    	return -1;
    }

}
