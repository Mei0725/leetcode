package leetcode_test;

public class BestTimeToBuyAndSellStockWithTransactionFee {

	/**
	 * solve by dp.
	 * free is the array that do not have any stock to sell, and hold is array that have one stock to sell
	 * 
	 * @param prices
	 * @param fee
	 * @return
	 */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] free = new int[n], hold = new int[n];
        hold[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            free[i] = Math.max(free[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], free[i - 1] - prices[i]);
        }
        // for the last one, the array do not pay for stock must be larger
        return free[n - 1];
    }
    
    /**
     * this function is used for similar question but without fee.
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] free = new int[n], hold = new int[n];
        hold[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            free[i] = Math.max(free[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], free[i - 1] - prices[i]);
        }
        return free[n - 1];
    }
    
    /**
     * this function works when the buy-sell operation happens only once
     * 
     * @param prices
     * @return
     */
    public int maxProfitOnce(int[] prices) {
        int hold = -prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] + hold);
            hold = Math.max(hold, -prices[i]);
        }
        return profit;
    }
}
