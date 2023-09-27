package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class SubstringXORQueries {

	/**
	 * since value=first^res, then res=value^first. We can get res firstly.
	 * maybe because of the spend time of toBinaryString, this solution will be overtime when s is very long,
	 * or when there is no result.
	 * 
	 * @param s
	 * @param queries
	 * @return
	 */
    public int[][] substringXorQueriesOvertime1(String s, int[][] queries) {
        int[][] result = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int first = queries[i][0], value = queries[i][1];
            String tmpStr = Integer.toBinaryString(value ^ first);
            int cut = 0;
            while (cut < tmpStr.length() && tmpStr.charAt(cut) == '0') {
                cut++;
            }
            if (cut == tmpStr.length()) {
                tmpStr = "0";
            } else {
                tmpStr = tmpStr.substring(cut);
            }
            int resLeft = s.indexOf(tmpStr);
            result[i][0] = resLeft;
            if (resLeft == -1) {
                result[i][1] = -1;
            } else {
                result[i][1] = resLeft + tmpStr.length() - 1;
            }
        }
        return result;
    }
    
    /**
     * use sliding window to solve this problem.
     * when s is very long and there is no result, this solution will be overtime
     * 
     * @param s
     * @param queries
     * @return
     */
    public int[][] substringXorQueriesOverTime2(String s, int[][] queries) {
        int[][] result = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int first = queries[i][0], value = queries[i][1];
            int resLeft = -1, resRight = -1;
            int left = 0, right = left + 1;
            int length = 1, max = Math.max(first, value);
            while (max >> 1 != 0) {
                length++;
                max >>= 1;
            }
            // System.out.println("length: " + length);
            while(right <= s.length()) {
                String tmpStr = s.substring(left, right);
                int tmpNum = Integer.parseUnsignedInt(tmpStr, 2);
                int tmpRes = tmpNum ^ first;
                if (tmpRes == value) {
                    if (resLeft == -1 || right - left < resRight - resLeft) {
                        resRight = right;
                        resLeft = left;
                    }
                    left++;
                    right = left + 1;
                } else if (right - left > length || (value >= first && tmpRes > value) || (value < first && tmpRes < value)) {
                    left++;
                    right = left + 1;
                } else {
                    right++;
                }
            }
            result[i][0] = resLeft;
            result[i][1] = resRight == -1 ? -1 : resRight - 1;
        }
        return result;
    }
    

    /**
     * store all result in a map, spend more space to save time.
     * calculate result by first^value, and check if there are possible result in the map
     * 
     * @author meiyb
     *
     */
    class SubString {
        int length = 0;
        int start = 0;

        public SubString(int l, int s) {
            this.length = l;
            this.start = s;
        }
    }

    public int[][] substringXorQueries(String s, int[][] queries) {
        int[][] result = new int[queries.length][2];
        Map<Integer, SubString> store = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
        	// handle the case that the result is 0
            if (chars[i] == '0') {
                if (!store.containsKey(0)) {
                    store.put(0, new SubString(1, i));
                }
                continue;
            }
            // since the largest value is 10^9, the result's length must be smaller than 30
            // find out the max value and take its binary length as max length may help to save time
            int value = 0;
            for (int j = 1; j <= 30; j++) {
                if (i + j > s.length()) {
                    break;
                }
                value = value * 2 + (chars[i + j - 1] == '1' ? 1 : 0);
                if (store.containsKey(value)) {
                	// check if this result should be update
                    if (j < store.get(value).length) {
                        store.put(value, new SubString(j, i));
                    }
                } else {
                    store.put(value, new SubString(j, i));
                }
            } 
        }
        for (int i = 0; i < queries.length; i++) {
            int first = queries[i][0], value = queries[i][1];
            int tmp = value ^ first;
            SubString res = store.get(tmp);
            if (res != null) {
                result[i][0] = res.start;
                result[i][1] = res.start + res.length - 1;
            } else {
                result[i][0] = -1;
                result[i][1] = -1;
            }
        }
        return result;
    }
}
