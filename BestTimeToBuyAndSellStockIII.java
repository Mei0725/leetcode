package leetcode_test;

public class BestTimeToBuyAndSellStockIII {

	/**
	 * solve by dp.
	 * use 4 int[] to store the profit of one-sell and two-sell, and return the max one of these two.
	 * 
	 * @param prices
	 * @return
	 */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 0 is the profix of one-sell
        // keep is the profix of one-buy but no-sell, pros is the profix of one-buy and one-sell
        int[] keep0 = new int[n], pros0 = new int[n];
        // 1 is the profix of two-sell
        int[] keep1 = new int[n], pros1 = new int[n];

        keep0[0] = -prices[0];
        if (n > 1) pros0[1] = keep0[0] + prices[1];
        // the two-sell well only exist when n>3
        if (n > 2) keep1[2] = pros0[1] - prices[2];
        if (n > 3) pros1[3] = keep1[2] + prices[3];

        for (int i = 1; i < n; i++) {
            keep0[i] = Math.max(-prices[i], keep0[i - 1]);
            if (i > 1) pros0[i] = Math.max(keep0[i - 1] + prices[i], pros0[i - 1]);
            if (i > 2) keep1[i] = Math.max(pros0[i - 1] - prices[i], keep1[i - 1]);
            if (i > 3) pros1[i] = Math.max(keep1[i - 1] + prices[i], pros1[i - 1]);
            // System.out.println("i:" + i + ", keep0:" + keep0[i] + ", pro:" + pros0[i]);
        }
        // System.out.println("0:" + pros0[n - 1] + ", 1:" + pros1[n - 1]);
        // check if no-sell is the best case
        if (pros0[n - 1] <= 0 && pros1[n - 1] <= 0) {
            return 0;
        }
        return Math.max(pros0[n - 1], pros1[n - 1]);
    }

    /**
     * it is a optimization of maxProfit
     * 
     * since we well only use the previous one keep and pros, 
     * we can change int[] into int to save time(8ms->4ms) and space(59.9MB->55.9MB)
     * 
     * @param prices
     * @return
     */
    public int maxProfitOptimization(int[] prices) {
        int n = prices.length;
        int keep0 = -prices[0], pros0 = 0, keep1 = 0, pros1 = 0;
        if (n > 1) pros0 = keep0 + prices[1];
        if (n > 2) keep1 = pros0 - prices[2];
        if (n > 3) pros1 = keep1 + prices[3];

        for (int i = 1; i < n; i++) {
        	// to avoid to use the wrong value, we should check 4 int in this order.
            if (i > 3) pros1 = Math.max(keep1 + prices[i], pros1);
            if (i > 2) keep1 = Math.max(pros0 - prices[i], keep1);
            if (i > 1) pros0 = Math.max(keep0 + prices[i], pros0);
            keep0 = Math.max(-prices[i], keep0);
            // System.out.println("i:" + i + ", keep0:" + keep0[i] + ", pro:" + pros0[i]);
        }
        // System.out.println("0:" + pros0[n - 1] + ", 1:" + pros1[n - 1]);
        if (pros0 <= 0 && pros1 <= 0) {
            return 0;
        }
        return Math.max(pros0, pros1);
    }
}
