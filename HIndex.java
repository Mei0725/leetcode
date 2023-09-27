package leetcode_test;

import java.util.Arrays;

public class HIndex {

	/**
	 * H-Index means the max value that there are h items >= h;
	 * 
	 * sort arrays, then check from its max length to 0 if it is H-Index.
	 * we should check the number of items instead of value because the result may not contain in value.
	 * 
	 * @param citations
	 * @return
	 */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        
        // i is the possible max result, 
        // if it can find h items larger than h, then it is the real result.
        int l = citations.length, i = l;
        while (i > 0 && citations[l - i] < i) {
            i--;
        }
        return i;
    }
}
