package leetcode_test;

public class CoinChangeII {

	/**
	 * solve by dp.
	 * store the possible way to go to i in res[i], and check every item coins[j]
	 * for every possible way to go to i, the number of ways to go to i+coins[j] should plus res[i]
	 * 
	 * @param amount
	 * @param coins
	 * @return
	 */
    public int changeByDP(int amount, int[] coins) {
        int[] res = new int[amount + 1];
        res[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (res[j] == 0) {
                    continue;
                }
                if (j + coins[i] <= amount) {
                    res[j + coins[i]] += res[j];
                }
            }
        }
        return res[amount];
    }

    /**
     * solve by dfs.
     * check every coins[i], only use i and items before i to add to amount, 
     * then try to fix the rest of amount by items after i.
     * 
     * compared to solution in dp, it spends too much time so it will become overtime.
     * 
     * @param amount
     * @param coins
     * @return
     */
    public int changeByDFS(int amount, int[] coins) {
        return change(amount, coins, 0);
    }

    /**
     * try to fix amount for item index, to avoid use coins in different order.
     * 
     * @param amount
     * @param coins
     * @param index
     * @return
     */
    public int change(int amount, int[] coins, int index) {
        if (amount == 0) {
            return 1;
        }

        int res = 0;
        for (int i = index; i < coins.length; i++) {
            int rest = amount - coins[i];
            while (rest >= 0) {
                res += change(rest, coins, i + 1);
                rest -= coins[i];
            }
        }
        return res;
    }
}
