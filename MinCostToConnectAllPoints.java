package leetcode_test;

public class MinCostToConnectAllPoints {
	
	/**
	 * solve by minimum spanning tree.
	 * 
	 * start from 0, and find out the min distance of points-in-result-union and points-out-result-union
	 * and add the points has min distance into result-union.
	 * 
	 * the time complexity is O(N^3)
	 * 
	 * @param points
	 * @return
	 */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, count = 1, res = 0;
        // dis[i][j] store the dis between i,j
        int[][] dis = new int[n][n];
        boolean[] mark = new boolean[n];
        mark[0] = true;
        while (count < n) {
            int min = -1, minDis = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (mark[i]) {
                    continue;
                }
                int tmpMin = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (!mark[j]) {
                        continue;
                    }
                    if (dis[i][j] == 0) {
                        dis[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    }
                    tmpMin = Math.min(tmpMin, dis[i][j]);
                }
                if (tmpMin < minDis) {
                    minDis = tmpMin;
                    min = i;
                }
            }
            res += minDis;
            mark[min] = true;
            count++;
        }
        return res;
    }

    /**
     * solve by Prim's Algorithm.
     * 
     * the solution is similar to minCostConnectPoints, but use disArr[] to save time to O(N^2)
     * 
     * @param points
     * @return
     */
    public int minCostConnectPointsByPrims(int[][] points) {
        int len = points.length;
        // array that keep track of the shortest distance from mst to each node
        int[] disArr = new int[len];
        for (int i = 1; i < len; ++i) {
            disArr[i] = Integer.MAX_VALUE;
        }
        // visited[node] == true if node in mst
        boolean[] visited = new boolean[len];
        visited[0] = true;
        
        int numEdge = 0;
        // current node, used to update the disArr
        int cur = 0;
        // result
        int res = 0;
        
        while (numEdge++ < len - 1) {
            int minEdge = Integer.MAX_VALUE;
            int next = -1;
            for (int i = 0; i < len; ++i) {
                // if the node i is not in mst
                if (!visited[i]) {
                    // find the distance between cur and i
                    int dis = Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1]);
                    // update distance array
                    disArr[i] = Math.min(dis, disArr[i]);
                    // find the shortest edge
                    // if disArr[i] is updated in the previous loops, it will also be found in this check and put i as next
                    // even through i and curr may not connect
                    if (disArr[i] < minEdge) {
                        minEdge = disArr[i];
                        next = i;
                    }
                }
            }
            cur = next;
            visited[cur] = true;
            res += minEdge;
        }
        
        return res;
    }
}
