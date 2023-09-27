package leetcode_test;

public class SurroundedRegions {

	public static void main(String[] args) {
		char[][] input = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		try {
			solve(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("result: " + output);
	}
	
	/**
	 * solve by depth-first search
	 * find out every box that should not be flipped, and then find out all 'O' boxes adjacent to it
	 * finally change all other boxes into 'X'
	 * 
	 * @param board
	 */
    public static void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        // use mark to mark all boxes should not be flipped
        boolean[][] mark = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                mark[i][0] = true;
                check(board, mark, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                mark[i][n - 1] = true;
                check(board, mark, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                mark[0][j] = true;
                check(board, mark, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                mark[m - 1][j] = true;
                check(board, mark, m - 1, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!mark[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void check(char[][] board, boolean[][] mark, int i, int j) {
        int m = board.length, n = board[0].length;
        // make sure that mark[next node] is false so it will not be checked more than one time and then lead to deadlock
        if (i > 0 && board[i - 1][j] == 'O' && !mark[i - 1][j]) {
            mark[i - 1][j] = true;
            check(board, mark, i - 1, j);
        }
        if (i < m - 1 && board[i + 1][j] == 'O' && !mark[i + 1][j]) {
            mark[i + 1][j] = true;
            check(board, mark, i + 1, j);
        }
        if (j > 0 && board[i][j - 1] == 'O' && !mark[i][j - 1]) {
            mark[i][j - 1] =  true;
            check(board, mark, i, j - 1);
        }
        if (j < n - 1 && board[i][j + 1] == 'O' && !mark[i][j + 1]) {
            mark[i][j + 1] = true;
            check(board, mark, i, j + 1);
        }
    }
    

    // create dir to make it easier to find out the adjacent boxes
    int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    
    /**
     * solve by union find
     * set all nodes which should not be flipped as the children of root(which is easy to reach but is not exist)
     * 
     * @param board
     */
    public void solveByUnionFind(char[][] board) {
        int m = board.length;
        if(board == null || board.length == 0) return;
        int n = board[0].length;
        int border = m * n;
        UnionFindSet uf = new UnionFindSet(border + 1);
        for(int x = 0; x < m; x++) {
            for(int y = 0; y < n; y++) {
                if(board[x][y] != 'O') continue;
                int cur = x * n + y;
                
                // set all 'O' boxes in the border's parent as the node border
                // make sure border's rank is the highest so that all boxes in border will be its children
                if(x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                    uf.union(border, cur);
                    continue;
                }
                
                for(int[] d: dir) {
                    int nextX = x + d[0];
                    int nextY = y + d[1];
                    // set the adjacent 'O' boxes's parent node[x][y]' as parent
                    // so that all 'O' adjacent to board's parent should be border
                    if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'O') {
                        int next = nextX * n + nextY;
                        uf.union(next, cur);
                    }
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
            	// when the node's parents is border, this means it links to 'O'-box in the border
                if(board[i][j] == 'O' && uf.find(i * n + j) != uf.find(border)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    class UnionFindSet {
        int[] parents;
        // ranks is used to mark the status of node
        // a node the nearer to border, its rank is higher
        int[] ranks;
        
        public UnionFindSet(int n) {
            parents = new int[n];
            ranks = new int[n];
            for(int i = 0; i < n; i++) parents[i] = i;
        }
        
        // find out the root of this tree
        private int find(int x) {
//            if(x != parents[x]) {
//            	x = find(parents[x]);
//            }
        	// change parents[x] can save some time(not sure, but theoretically it should be)
            while (x != parents[x]) {
            	parents[x] = parents[parents[x]];
            	x = parents[x];
            }
            return parents[x];
        }
        
        private boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px == py) return false;
            // set the node with higher rank as parent
            if(ranks[px] > ranks[py]) {
                parents[py] = px;
                ranks[px] ++;
            } else{
                parents[px] = py;
                ranks[py] ++;
            }
            return true;
        }
    }
}
