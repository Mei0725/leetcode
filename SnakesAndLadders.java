package leetcode_test;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {

	/**
	 * solved by Breadth-First Search.
	 * store next items should become the start int queue, check queue from start to the end.
	 * because we check items from low depth to high depth, whenever there is a path to the final cell, return immediately
	 * 
	 * @param board
	 * @return
	 */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // to store the moves to i+1, use it to avoid repeat operation
        int[] counts = new int[n * n];
        // to store the all cell can see as the start
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        
        while (!queue.isEmpty()) {
            int val = queue.poll();
            boolean end = false;
            // check all cells can be arrived
            for (int count = Math.min(6, n * n - val); count >= 1; count--) {
            	// get the real cell this operation can be arrived
                int newVal = getRealCeil(val + count, n, board);
                if (newVal == n * n) {
                	// it means it can arrive the final cell, since it is BFS, it must be the least result
                    // System.out.println("newVal: " + newVal);
                    // System.out.println("count: " + count);
                    // System.out.println("counts[val - 1]: " + counts[val - 1]);
                    counts[newVal - 1] = counts[val - 1] + 1;
                    end = true;
                    break;
                } else if (newVal == 1 || (counts[newVal - 1] > 0 && counts[newVal - 1] <= counts[val - 1] + 1)) {
                	// it means this cell has been checked before
                	// we must check if it is the first item, the counts[first]=0 and can not be changed
                    continue;
                }
                // update moves to newVal and take it as a new start
                counts[newVal - 1] = counts[val - 1] + 1;
                queue.offer(newVal);
            }
            if (end) {
                break;
            }
        }
        return counts[n * n - 1] == 0 ? -1 : counts[n * n - 1];
    }

    /**
     * find out the real cell val can be arrived after snakes and ladders
     * 
     * @param val
     * @param n
     * @param board
     * @return
     */
    public int getRealCeil(int val, int n, int[][] board) {
        // System.out.println("val: " + val);
        int[] p = getPosition(val, n);
        // System.out.println("i: " + p[0]);
        // System.out.println("j: " + p[1]);
        // check if there is any snake or ladder
        if (board[p[0]][p[1]] != -1) {
            return board[p[0]][p[1]];
        }
        return val;
    }

    /**
     * find out the position for val
     * 
     * @param val
     * @param n
     * @return
     */
    public int[] getPosition(int val, int n) {
        int column = val % n, row = val / n;
        if (column == 0) {
            row -= 1;
            column = n;
        }
        int i, j = n - 1 - row;
        if (j % 2 != n % 2) {
            i = column - 1;
        } else {
            i = n - column;
        }
        return new int[]{j, i};
    }

    
    /**
     * solved by DFS. and this solution will be overtime and over memory.
     * 
     * for question to get smallest depth, BFS is better than DFS.
     * it is because DFS will only go back after arrive the end of search, most of the time it is not necessary.
     * 
     * @param board
     * @return
     */
    public int snakesAndLaddersByDFS(int[][] board) {
        int n = board.length;
        int[] times = new int[n * n];
        return snakesAndLadders(board, 1, times, 0);
    }

    public int snakesAndLadders(int[][] board, int val, int[] times, int current) {
        int n = board.length;
        if (val >= n * n) {
            times[val - 1] = current;
            return times[val - 1];
        } else if (times[val - 1] > 0 && times[val - 1] <= current) {
            return times[val - 1];
        }

        int res = n * n, count = Math.min(6, res - val);
        while (count >= 1) {
            int tmp = getRealCeil(val + count, n, board);
            if (tmp == -1) {
                count--;
                continue;
            }
            res = Math.min(res, snakesAndLadders(board, tmp, times, current + 1));
            count--;
        }
        if (res == n * n) {
            times[val - 1] = -1;
        } else {
            times[val - 1] = res;
        }
//        System.out.println("res: " + val + ", res:" + times[val - 1]);
        return times[val - 1];
    }
}
