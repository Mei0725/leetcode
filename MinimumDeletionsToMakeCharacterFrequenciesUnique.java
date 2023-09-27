package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

	/**
	 * use map to store the number of every letter, 
	 * and because the order to handle letters will not change the result, 
	 * when there are no good letters, delete the number of it until it is good or the number of it is 0.
	 * 
	 * @param s
	 * @return
	 */
    public int minDeletions(String s) {
    	// it can also use int[26] to store the number of letters, spend more space and less time
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        Set<Integer> counts = new HashSet<>();
        int res = 0;
        for (char ch : count.keySet()) {
            int tmp = count.get(ch);
            while (counts.contains(tmp) && tmp > 0) {
                tmp--;
                res++;
            }
            if (tmp != 0) {
                counts.add(tmp);
            }
        }
        return res;
    }
}
