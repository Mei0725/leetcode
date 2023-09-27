package leetcode_test;

public class NumberOfProvinces {

	/**
	 * solve by depth-first search.
	 * use check[] to mark the handled cities, and for every non-checked city, mark all connected cities
	 * 
	 * @param isConnected
	 * @return
	 */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] check = new boolean[n];

        int num = 0;
        for (int i = 0; i < n; i++) {
            if (check[i]) {
                continue;
            }
            markAllNode(i, isConnected, check);
            // all cities in this circle are marked, this is one circle
            num++;
        }
        return num;
    }

    /**
     * for every city i, make every connected city i check[i] become true, and then handle all city connected to i
     * 
     * @param i
     * @param con
     * @param check
     */
    public void markAllNode(int i, int[][] con, boolean[] check) {
        if (check[i]) {
            return;
        }

        check[i] = true;
        for (int j = 0; j < con.length; j++) {
            if (con[i][j] == 1) {
                markAllNode(j, con, check);
            }
        }
        return;
    }
}
