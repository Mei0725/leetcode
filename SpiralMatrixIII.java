package leetcode_test;

public class SpiralMatrixIII {

	/**
	 * store 4 edge points of the written area, even when they are negative
	 * then in every path, store all cells that included in the given arrays into result path
	 * 
	 * @param rows
	 * @param cols
	 * @param rStart
	 * @param cStart
	 * @return
	 */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] path = new int[rows * cols][2];
        // direction is used to show the current direction
        // 0-to right; 1-to bottom; 2-to left; 3-to top
        int direction = 0;
        // bl-light border; br-right border; bt-top border; bb-bottom border 
        int bl = cStart, br = cStart, bt = rStart, bb = rStart;
        // i is the index of result, x&y are the current position
        int i = 0, y = rStart, x = cStart;
        while (i < rows * cols) {
            switch (direction) {
            	// left to right
                case 0:
                	// move the real right border
                    br++;
                    // the right border in arrays
                    int right = Math.min(br, cols - 1);
                    // if top border is out of arrays, there will be no cell
                    if (bt >= 0) {
                    	// the left border in arrays
                        x = Math.max(0, bl);
                        // add all cells in arrays into path
                        // the last one will be handle in the next loop so there is no =
                        while (x < right) {
                            path[i++] = new int[] {y, x++};
                        }
                        // handle the case that the last one is out of arrays
                        // then the last one in arrays should be put into path
                        if (br >= cols) {
                            path[i++] = new int[] {y, x++};
                        }
                    }
                    x = right;
                    direction = 1;
                    break;
                // top to bottom
                case 1:
                    bb++;
                    int bottom = Math.min(bb, rows - 1);
                    if (br < cols) {
                        y = Math.max(0, bt);
                        while (y < bottom) {
                            path[i++] = new int[] {y++, x};
                        }
                        if (bb >= rows) {
                            path[i++] = new int[] {y++, x};
                        }
                    }
                    y = bottom;
                    direction = 2;
                    break;
                // right to left
                case 2:
                    bl--;
                    int left = Math.max(bl, 0);
                    if (bb < rows) {
                        x = Math.min(br, cols - 1);
                        while (x > left) {
                            path[i++] = new int[] {y, x--};
                        }
                        if (bl < 0) {
                            path[i++] = new int[] {y, x--};
                        }
                    }
                    x = left;
                    direction = 3;
                    break;
                // bottom to top
                default:
                    bt--;
                    int top = Math.max(bt, 0);
                    if (bl >= 0) {
                        y = Math.min(bb, rows - 1);
                        while (y > top) {
                            path[i++] = new int[] {y--, x};
                        }
                        if (bt < 0) {
                            path[i++] = new int[] {y--, x};
                        }
                    }
                    y = top;
                    direction = 0;
                    break;
            }
            // System.out.println("x: " + x);
            // System.out.println("y: " + y);
            // System.out.println("i: " + i);
            // System.out.println("direction: " + direction);
        }
        // System.out.println("-------------");
        return path;
    }
}
