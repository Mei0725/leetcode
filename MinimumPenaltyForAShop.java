package leetcode_test;

public class MinimumPenaltyForAShop {

	/**
	 * calculate the penalty when the close time is 0, and then calculate penalty in every time by its char
	 * output the time when the penalty is min.
	 * 
	 * @param customers
	 * @return
	 */
    public int bestClosingTime(String customers) {
    	// calculate penalty when close time is 0
        int penalty = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                penalty++;
            }
        }

        int time = 0, min = penalty;
        for (int i = 0; i < customers.length(); i++) {
        	// calculate when close time is (i+1)
            if (customers.charAt(i) == 'Y') {
                penalty--;
            } else {
                penalty++;
            }

            // System.out.println("i:" + i + ", pen:" + penalty);
            if (penalty < min) {
                min = penalty;
                time = i + 1;
            }
        }
        return time;
    }
	
    
    /**
     * a simplify of bestClosingTime.
     * 
     * since there is no need to output the penalty, we can assume that the penalty in 0 is 0, and then following penalty may be minus.
     * this solution saves the time to calculate penalty0, so it can help to save time(12ms->8ms) and space.
     * 
     * @param customers
     * @return
     */
    public int bestClosingTimeSimplify(String customers) {
        int penalty = 0, time = 0, minpen = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--;
                if (penalty < minpen) {
                    time = i + 1;
                    minpen = penalty;
                }
            } else {
                penalty++;
            }
        }
        return time;
    }
}
