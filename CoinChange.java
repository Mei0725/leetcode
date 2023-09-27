package leetcode_test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CoinChange {

	/**
	 * solve by DP.
	 * use a int[amount+1] to store the result of i,
	 * for every i, the result is the min value of nums[i-coins]+1.
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
    public int coinChangeByDP(int[] coins, int amount) {
        int[] num = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && num[i - coins[j]] != -1) {
                    min = Math.min(min, num[i - coins[j]] + 1);
                }
            }
            num[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return num[amount];
    }
    
    /**
     * solve by BFS.
     * instead find out result until all amount are used, 
     * check cases have the same number of coins firstly to save time.
     * 
     * it reduce time compare to coinChangeByDFS, but is still overtime.
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeByBFS(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Arrays.sort(coins);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{amount, 0, coins.length - 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = cur[2]; i >= 0; i--) {
                if (coins[i] == cur[0]) {
                    return cur[1] + 1;
                } else if (coins[i] < cur[0]) {
                    queue.add(new int[]{cur[0] - coins[i], cur[1] + 1, i});
                }
            }
            // System.out.println("queue: " + queue.size());
        }
        return -1;
    }

    /**
     * solve by DFS.
     * for every amount, cry to get every coin in possible value, and then get a new amount.
     * find out the min result of this amount.
     * 
     * but this solution will be overtime.
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeByDFS(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Arrays.sort(coins);
        int num = coinChange(coins, amount, 0, Integer.MAX_VALUE, coins.length - 1);
        return num == Integer.MAX_VALUE ? -1 : num;
    }

    /**
     * find out the result of amount
     * 
     * @param coins         coins list
     * @param amount        current amount
     * @param num           number of coins before amount become current amount
     * @param currentMin    current result, use it to reduce some cases
     * @param index         the max index of amount to check, use it to avoid to get same coins in different order
     * @return
     */
    public int coinChange(int[] coins, int amount, int num, int currentMin, int index) {
        if (currentMin <= num + 1) {
            return currentMin;
        }
        int min = Integer.MAX_VALUE;
        for (int i = index; i >= 0; i--) {
            if (coins[i] == amount) {
                return num + 1;
            } else if (coins[i] < amount) {
                min = Math.min(min, coinChange(coins, amount - coins[i], num + 1, min, i));
            }
        }
        return min;
    }
}
