package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

	/**
	 * calculate all items for first line to last line.
	 * 
	 * @param numRows
	 * @return
	 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            line.add(1);
            // handle the first line specially
            if (i == 0) {
                res.add(line);
                continue;
            }

            List<Integer> pre = res.get(i - 1);
            for (int j = 1; j < i; j++) {
                line.add(pre.get(j - 1) + pre.get(j));
            }
            line.add(1);
            res.add(line);
        }
        return res;
    }
}
