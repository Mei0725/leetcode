package leetcode_test;

public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] input = {{'1'},{'1'}};;
		int output = -1;
		try {
			output = numIslandsUnionFind(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * solve by union find.
	 * use UnionSet to store all '1'-boxes
	 * whenever find a '1'-box, assume it as a new island, result+1
	 * and when there is a union operation, result-1
	 * 
	 * @param grid
	 * @return
	 */
    public static int numIslandsUnionFind(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // System.out.println("grid: " + grid[m - 1][n - 1]);
        // System.out.println("m: " + m);
        // System.out.println("n: " + n);
        UnionSet unionSet = new UnionSet(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                int index = i * n + j;
                // System.out.println("i: " + i);
                // System.out.println("j: " + j);
                unionSet.add(index);
                // since we check all boxes from up to down, left to right
                // the left and up boxes have been checked before this operation
                if (i < m - 1 && grid[i + 1][j] == '1') {
                    unionSet.add(index + n);
                    unionSet.union(index, index + n);
                }
                if (j < n - 1 && grid[i][j + 1] == '1') {
                    unionSet.add(index + 1);
                    unionSet.union(index, index + 1);
                }
            }
        }
        return unionSet.getNum();
    }

    public static class UnionSet {
    	// the root of every node
        int[] parents;
        // the size of island
        int[] size;
        // the count of island
        int num;

        public UnionSet(int n) {
            parents = new int[n];
            size = new int[n];
            num = 0;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public boolean add(int n) {
        	// check if this '1'-box is added
            if (size[n] > 0) {
                return false;
            }
            size[n] = 1;
            num++;
            return true;
        }

        public int find(int x) {
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int i1 = find(x);
            int i2 = find(y);
            if (i1 == i2) return;

            if (size[i1] < size[i2]) {
                parents[i1] = i2;
                size[i2] += size[i1];
            } else {
                parents[i2] = i1;
                size[i1] +=size[i2];
            }
            num--;
        }

        public int getNum() {
            return num;
        }
    }

    /**
     * solve by depth-first search
     * use boolean[][] check to store if the grid is checked before
     * and whenever find a '1'-box, check all boxes around it until find out all boxes in this island
     * 
     * @param grid
     * @return
     */
    public int numIslandsByDepthFirstSearch(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] check = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !check[i][j]) {
                    result++;
                    check[i][j] = true;
                    checkSurrounded(grid, check, i, j);
                }
            }
        }
        return result;
    }

    public void checkSurrounded(char[][] grid, boolean[][] check, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i > 0 && grid[i - 1][j] == '1' && !check[i - 1][j]) {
            check[i - 1][j] = true;
            checkSurrounded(grid, check, i - 1, j);
        }
        if (i < m - 1 && grid[i + 1][j] == '1' && !check[i + 1][j]) {
            check[i + 1][j] = true;
            checkSurrounded(grid, check, i + 1, j);
        }
        if (j > 0 && grid[i][j - 1] == '1' && !check[i][j - 1]) {
            check[i][j - 1] = true;
            checkSurrounded(grid, check, i, j - 1);
        }
        if (j < n - 1 && grid[i][j + 1] == '1' && !check[i][j + 1]) {
            check[i][j + 1] = true;
            checkSurrounded(grid, check, i, j + 1);
        }
    }
    
    /**
     * this solution is a improvement of numIslandsByDepthFirstSearch
     * which reduce time(11ms->3ms) and space(57.6MB->50.7MB)
     * 
     * there are 3 main changes:
     * 1. store the height and width of grid so that they should be gotten only once
     * 2. use dirs to reduce code lines about checking the surrounded boxes
     * 3. set every checked '1'-box as '0' so that there is no need to use boolean[][] to mark, and also reduce the check operation of finding surrounded boxes
     */
	int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	int maxLine;
	int maxCol;

	public int numIslands(char[][] grid) {
		int count = 0;
		if (grid == null)
			return count;
		maxLine = grid.length - 1;
		if (maxLine < 0)
			return count;
		maxCol = grid[0].length - 1;
		for (int i = 0; i <= maxLine; i++) {
			for (int j = 0; j <= maxCol; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					count++;
				}
			}
		}
		return count;
	}

	private void dfs(char[][] grid, int line, int col) {
		grid[line][col] = '0';
		for (int[] offset : dirs) {
			int tmpline = line + offset[0];
			int tmpcol = col + offset[1];
			if (0 <= tmpline && tmpline <= maxLine && 0 <= tmpcol && tmpcol <= maxCol && grid[tmpline][tmpcol] == '1') {
				dfs(grid, tmpline, tmpcol);
			}
		}
	}
}
