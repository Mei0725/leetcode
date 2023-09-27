package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CanIWin {
	/**
	 * for every number smaller than desiredTotal, check if A can arrive at it no matter what B does
	 * if it can be arrived, then store it and the numbers have used as a possible start
	 * 
	 * this solution still has some mistakes and will be overtime when desiredTotal is large
	 * 
	 * @param maxChoosableInteger
	 * @param desiredTotal
	 * @return
	 */
    public boolean canIWinError(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        int total = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            total += i;
        }
        if (desiredTotal > total) {
            return false;
        }

        Map<Integer, List<Integer>> results = new HashMap<>();
        for (int i = 1; i <= maxChoosableInteger; i++) {
            results.put(i, Arrays.asList((1 << i)));
        }
        for (int i = maxChoosableInteger + 1; i <= desiredTotal; i++) {
            List<Integer> choiceForI = new ArrayList<>();
            boolean overI = false;
            for (int j = i - maxChoosableInteger * 2 + 1; j < i; j++) {
                if (j <= 0) {
                    continue;
                }
                Set<Integer> choiceForJ = new HashSet<>();
                for (int mark : results.get(j)) {
                    if (i - j <= maxChoosableInteger && (mark & (1 << (i - j))) == 0) {
                        continue;
                    }
                    int min = -1, max = -1;
                    for (int k = 1; k <= maxChoosableInteger; k++) {
                        if ((mark & (1 << k)) != 0) {
                            continue;
                        }
                        if (min == -1) {
                            min = k;
                            max = k;
                        } else if (k > max) {
                            max = k;
                        }
                        if (j - i - k <= maxChoosableInteger && (mark & (1 << (j - i - k))) == 0 ) {
                            int tmp = mark;
                            tmp |= (1 << k);
                            tmp |= (1 << (j - i - k));
                            choiceForJ.add(tmp);
                        }
                    }
                    if (min < max && min + max + j > i) {
                        overI = true;
                    }
                }
                choiceForI.addAll(choiceForJ);
            }
            if (i >= desiredTotal && (!choiceForI.isEmpty() || overI)) {
                return true;
            }
            results.put(i, choiceForI);
        }
        return false;
    }
    
    // store result of all possibilities
    // memo[0] is the result of player A(I) and meno[1] is the result of player B
    // about result:-1 - have not checked; 0 - can't win; 1 - can win
    int [][] memo;
    // why this solution does not consider about the different order of use?
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// check if desiredTotal is larger than total of all available numbers
		if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
			return false;
		// init all items in meno as -1
		memo = new int[2][1 << maxChoosableInteger];
		for (int[] x : memo) {
			Arrays.fill(x, -1);
		}
		return dp(0, 0, 0, desiredTotal, maxChoosableInteger) == 1;
    }

    /**
     * use dp to solve this problem
     * 
     * @param sum     the sum of current state
     * @param player  the player who can choose the number, it must be 0 or 1
     * @param mask    the current state of numbers
     * @param target  the desired total
     * @param limit   the max choosable integer
     * @return
     */
    public int dp(int sum, int player, int mask, int target, int limit){
		if (memo[player][mask] != -1)
			return memo[player][mask];
		else {
			for (int i = 1; i <= limit; i++) {
				// check if i-1 is used
				if (((1 << (i - 1)) & mask) == 0) {
					// there are 2 cases that player can win:
					// 1. he can arrive at target in 1 step;
					// 2. the other player can not arrive at target if he choose this number;
					if (sum + i >= target || dp(sum + i, 1 - player, mask | (1 << (i - 1)), target, limit) != 1)
						return memo[player][mask] = 1;
				}
			}
		}
		return memo[player][mask] = 0;
	}
}
