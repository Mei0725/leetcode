package leetcode_test;

public class ChampagneTower {

	/**
	 * solve by dp.
	 * 
	 * use curr[] to store the champagne in current row and next[] for champagne in the next row.
	 * for the curr[], check every glass and calculate the champagne should flow to each side.
	 * for the next[], init it and do not consider about champagne flow to the next row.
	 * 
	 * @param poured
	 * @param query_row
	 * @param query_glass
	 * @return
	 */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] curr = new double[1];
        curr[0] = poured;
        for (int i = 0; i <= query_row; i++) {
            boolean fin = true;
            double[] next = new double[i + 2];
            for (int j = 0; j <= i; j++){
            	// if the result is in the midder of champagne tower, return
//                 System.out.println("i:"  + i + ", j:" + j + ",curr:" + curr[j]);
                if (i == query_row && j == query_glass) {
                    return curr[j] > 1 ? 1 : curr[j];
                }

                double flow = (curr[j] - 1) / 2;
                if (flow > 0) {
                    next[j] += flow;
                    next[j + 1] += flow;
                    fin = false;
                }
            }
            // check if it is the final row will flow to the next row, if it is, then the following glass will be 0
            // System.out.println("i:"  + i + ", fin:" + fin);
            if (fin) {
                break;
            }
            curr = next;
        }
        return 0;
    }
}
