package leetcode_test;

public class CountPrimes {

	public static void main(String[] args) {
		int input = 10;
		int output = -1;
		try {
			output = countPrimes(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * mark all numbers<=Math.sqrt(n) and its multiple, which will not included in the result
	 * since all numbers>Math.sqrt(n) must have been marked before, there is no need to check them
	 * 
	 * @param n
	 * @return
	 */
    public static int countPrimes(int n) {
        boolean[] mark = new boolean[n];
        int result = 0;
        int maxCheck = (int)Math.sqrt(n);
        // the min num which should be checked is Math.sqrt(n)
        for (int i = 2; i <= maxCheck; i++) {
        	// if i is marked, then all its multiples must be marked in the loop which marked it
            if (mark[i]) {
                continue;
            }
            // count some result in this loop, instead of outside of this whole loop,
            // can reduce some time(139ms->111ms) and space(68.8MB->46.6MB)
            result++;
            int tmp = i + i;
            while (tmp < n) {
                mark[tmp] = true;
                tmp += i;
            }
        }
        // count all results bigger than maxCheck
        for (int i = maxCheck + 1; i < n; i++) {
            if (!mark[i]) {
                result++;
            }
        }
        return result;
    }
}
