package leetcode_test;

public class MaximizeTheConfusionOfAnExam {

	/**
	 * solve by sliding window.
	 * create a window with start and end, when the number of F and T are both larger than k, 
	 * move the position of start.
	 * 
	 * @param answerKey
	 * @param k
	 * @return
	 */
    public int maxConsecutiveAnswers(String answerKey, int k) {
    	// init the position of start and end of sliding window
        int start = 0, end = 0;
        // countF is the number of F, then number of T can be calculated by start,end,countF
        int countF = 0, max = 0;
        while (end < answerKey.length()) {
            if (answerKey.charAt(end) == 'F') {
                countF++;
            }
            // adjust the window so it can only have the same letter by change less than k times 
            while (countF > k && end - start + 1 - countF > k) {
                if (answerKey.charAt(start++) == 'F') {
                    countF--;
                }
            }
            max = Math.max(max, end - start + 1);
            end++;
        }
        return max;
    }
}
