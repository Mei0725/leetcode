package leetcode_test;

public class BestTimeToBuyAndSellStockIV {

	/**
	 * use dp to solve problem.
	 * use 2 int[], keep and pro, keep[i] and pro[i] means the pro in i-th transactions.
	 * then there are: keep[i] = Math.max(pro[i - 1] - prices[j], keep[i])
	 *                 pro[i] = Math.max(keep[i] + prices[i], pro[i]);
	 * and to avoid the previous value changed, we should check it for end to start.
	 * 
	 * @param k
	 * @param prices
	 * @return
	 */
    public int maxProfit(int k, int[] prices) {
        int[] keep = new int[k], pro = new int[k];
        keep[0] = -prices[0];
        if (prices.length > 1) {
            pro[0] = keep[0] + prices[1];
        }
        // init keep and pro to simple the check in following operation
        // otherwise, we should check if it is the first item for j-th transaction
        for (int i = 1; i < Math.min(k, prices.length / 2); i++){
            keep[i] = pro[i - 1] - prices[i * 2];
            pro[i] = keep[i] + prices[i * 2 + 1];
        }

        for (int i = 1; i < prices.length; i++) {
        	// handle the case that prices[i] can not support k transactions
            for (int j = Math.min(k - 1, i / 2); j >= 0; j--) {
            	// handle the case that prices[i] can not support k transactions
            	// in this case, pro[j] will be set once in 2 times
                if (2 * j + 1 <= i) {
                    pro[j] = Math.max(pro[j], keep[j] + prices[i]);
                }
                if (j != 0) {
                    keep[j] = Math.max(keep[j], pro[j - 1] - prices[i]);
                } else {
                	// handle the keep[0]
                    keep[j] = Math.max(keep[j], -prices[i]);
                }
            }
        }

        // check the pro in all times transaction, and find out the max one
        int max = 0;
        for (int i = 0; i < k; i++) {
            max = Math.max(pro[i], max);
        }
        return max;
    }
}
