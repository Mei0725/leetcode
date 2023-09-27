package leetcode_test;

import java.util.Arrays;

public class BeautifulArrangement {
	/**
	 * use dp and bitmask to solve problem.
	 * @param n
	 * @return
	 */
    public int countArrangement0(int n) {
        return countArr(1, n, 0);
    }

    /**
     * 
     * @param index  the index of current position of array
     * @param n      the max number
     * @param mark   mark the use of all numbers
     * @return
     */
    public int countArr(int index, int n, int mark) {
    	// if it is the end of array, it means this order is valid
        if (index > n) {
            return 1;
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
        	// check if i is used
            if ((mark & (1 << i)) != 0) {
                // System.out.println("continue: ");
                continue;
            }
            // check if i follows the rule, if it is, then put it into array and check the next index
            if (i % index == 0 || index % i == 0) {
                result += countArr(index + 1, n, mark | (1 << i));
            }
        }
        return result;
    }

    /**
     * compare to 0, use results[m] to mark the result of numbers m to save time(133ms->22ms)
     * 
     * why this solution dose not consider about the order?
     * 
     * @param n
     * @return
     */
    public int countArrangement1(int n) {
        int[] results = new int[1 << (n + 1)];
        Arrays.fill(results, -1);
        return countArr(1, n, 0, results);
    }

    public int countArr(int index, int n, int mark, int[] results) {
        if (index > n) {
        	// since set results[mark]=result in the end, there is no need to calculate total-result here
        	// this operation also help to save a little time
//            results[mark]++;
            return 1;
        } else if (results[mark] != -1) {
        	// this operation will help to save time
        	// init results as -1 so that the calculated-0 and unhandled-0 will not confuse,
        	// can help to save time(22ms->10ms)
            return results[mark];
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if ((mark & (1 << i)) != 0) {
                // System.out.println("continue: ");
                continue;
            }
            if (i % index == 0 || index % i == 0) {
                result += countArr(index + 1, n, mark | (1 << i), results);
            }
        }
        return results[mark] = result;
    }
}
