package leetcode_test;

import java.util.Arrays;

public class MatchsticksToSquare {

	public boolean makesquare(int[] matchsticks) {
		var sum = Arrays.stream(matchsticks).mapToLong(i -> i).sum();
		if (sum % 4 != 0)
			return false;
		var edge = sum / 4;
		// cache[mask] is true if square can be build from this state represented by bitmask.
		// use Boolean[] is because that Boolean can be null
		var cache = new Boolean[1 << matchsticks.length];
		var cur = 0L;
		return dfs(matchsticks, 0, cur, edge, 0, cache);
	}

	/**
	 * to find out the valid combination that can constitute 3 edges
	 * 
	 * @param m       the array
	 * @param mask    use binary number to store if the array number in every index is used
	 * @param cur     the length of current edge
	 * @param edge    the length of every edge
	 * @param cnt     the count of finished edge
	 * @param cache   to store if the array number is used
	 * @return
	 */
	boolean dfs(int[] m, int mask, long cur, long edge, int cnt, Boolean[] cache) {
		// since every time we check the array numbers from 0->end
		// there may be some repeated case
		// check it firstly can help to reduce the running time
		if (cache[mask] != null)
			return cache[mask];
		if (cur == edge) {
			cnt++;
			cur = 0;
			// when there are 3 finished edges, there is no need to check the final edge
			if (cnt == 3) {
				// return true
				return cache[mask] = true;
			}
		}
		for (int i = 0; i < m.length; i++) {
			// (mask&(1<<i))==0 is to check if m[i] is used(m[i] does not contains in mask)
			if ((mask & (1 << i)) == 0 && cur + m[i] <= edge) {
				// add i into mask
				mask |= 1 << i;
				if (dfs(m, mask, cur + m[i], edge, cnt, cache)) {
					return true;
				}
				// initialize mask
				mask &= ~(1 << i);
			}
		}
		// to mark that the use of arrays like input-mark is invalid
		// return false;
		return cache[mask] = false;
	}
}
