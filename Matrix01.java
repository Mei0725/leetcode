package leetcode_test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {

	/**
	 * solve by BFS.
	 * 
	 * start from item 0, and check all neighbors of them, update its dis if it is the best dis, 
	 * and then check its neighbors, until all cells update
	 * 
	 * @param mat
	 * @return
	 */
    public int[][] updateMatrixByBFS(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dis = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // init dis, and get all items 0
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE - 1);
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dis[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // check from items 0, and then update the dis of their neighbors
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int i = position[0], j = position[1];
            if (i > 0 && dis[i - 1][j] > dis[i][j] + 1) {
                dis[i - 1][j] = dis[i][j] + 1;
                queue.offer(new int[]{i - 1, j});
            }
            if (i < m - 1 && dis[i + 1][j] > dis[i][j] + 1) {
                dis[i + 1][j] = dis[i][j] + 1;
                queue.offer(new int[]{i + 1, j});
            }
            if (j > 0 && dis[i][j - 1] > dis[i][j] + 1) {
                dis[i][j - 1] = dis[i][j] + 1;
                queue.offer(new int[]{i, j - 1});
            }
            if (j < n - 1 && dis[i][j + 1] > dis[i][j] + 1) {
                dis[i][j + 1] = dis[i][j] + 1;
                queue.offer(new int[]{i, j + 1});
            }
        }
        return dis;
    }
    

    /**
     * solve by DFS.
     * 
     * for every items 0, check all its neighbors, and then update their dis until the end of this path.
     * compare to BFS, a path may be updated several times so this solution will spend more time.
     * 
     * @param mat
     * @return
     */
    public int[][] updateMatrixByDFS(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	// only check items 0 to reduce time
                if (mat[i][j] != 0) {
                    continue;
                }
                countDis(mat, dis, i, j, Integer.MAX_VALUE);
            }
        }
        return dis;
    }

    /**
     * for mat[i][j], update the dis value of its 1-neighbors
     * 
     * @param mat
     * @param dis
     * @param i
     * @param j
     * @param path
     */
    private void countDis(int[][] mat, int[][] dis, int i, int j, int path) {
        if (dis[i][j] != -1 && dis[i][j] <= path) {
        	// if there is a better path, return
            return;
        } else if (mat[i][j] == 0) {
        	// handle the items 0
            dis[i][j] = 0;
        } else {
        	// handle the items 1
            dis[i][j] = path;
        }

        if (i > 0 && mat[i - 1][j] != 0) {
            countDis(mat, dis, i - 1, j, dis[i][j] + 1);
        }
        if (i < mat.length - 1 && mat[i + 1][j] != 0) {
            countDis(mat, dis, i + 1, j, dis[i][j] + 1);
        }
        if (j > 0 && mat[i][j - 1] != 0) {
            countDis(mat, dis, i, j - 1, dis[i][j] + 1);
        }
        if (j < mat[0].length - 1 && mat[i][j + 1] != 0) {
            countDis(mat, dis, i, j + 1, dis[i][j] + 1);
        }
        return;
    }
}
