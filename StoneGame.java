package leetcode_test;

public class StoneGame {
	
	/**
	 * solve this problem by dp.
	 * scoreA stores the best result for A, and scoreB stores the best result for B
	 * calculate the result from length 1 to pile.length
	 * 
	 * @param piles
	 * @return
	 */
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        // scoreA's length is length + 1 so that when length is 1 it will not cause error
        int[] scoreA = new int[length + 1], scoreB = new int[length];
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                //calculate scoreB, for every sub array, get the best result for length-i
                for (int j = 0; j + i < length; j++) {
                    scoreB[j] = Math.min(scoreA[j] - piles[j + i], scoreA[j + 1] - piles[j]);
                }
            } else {
                //calculate scoreA, for every sub array, get the best result for length-i
                for (int j = 0; j + i < length; j++) {
                    scoreA[j] = Math.max(scoreB[j] + piles[j + i], scoreB[j + 1] + piles[j]);
                }
            }
        }
        return scoreA[0] > 0;
    }
}
