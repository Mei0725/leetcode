package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PathWithMaximumProbability {

	/**
	 * if any node of edges can link to start, update prob[node] as the max probability
	 * repeat this loop until there is no change in prob
	 * 
	 * @param n
	 * @param edges
	 * @param succProb
	 * @param start
	 * @param end
	 * @return
	 */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] maxProb = new double[n];
        maxProb[start] = 1;

        while (true) {
            boolean hasUpdate = false;
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                double pathProb = succProb[j];
                if (maxProb[u] * pathProb > maxProb[v]) {
                    maxProb[v] = maxProb[u] * pathProb;
                    hasUpdate = true;
                }
                if (maxProb[v] * pathProb > maxProb[u]) {
                    maxProb[u] = maxProb[v] * pathProb;
                    hasUpdate = true;
                }
            }
            if (!hasUpdate) {
                break;
            }
        }

        return maxProb[end];
    }
    
    /**
     * solve by breadth first search.
     * use map nodes to store the relationship between nodes and succProb
     * then check nodes from start by breadth first search, when there is a update in double[] prob
     * update all following nodes by add them into queue
     * 
     * depth first search can also solve this problem, but it is easier to become overtime
     * since there are more prob change operations
     * 
     * even through it still spends more time and space than maxProbability
     * 
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbabilityByQueue(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Set<Integer> tmp0 = nodes.getOrDefault(edges[i][0], new HashSet<>()), tmp1 = nodes.getOrDefault(edges[i][1], new HashSet<>());
            tmp0.add(i);
            tmp1.add(i);
            nodes.put(edges[i][0], tmp0);
            nodes.put(edges[i][1], tmp1);
        }
        
        double[] prob = new double[n];
        Queue<Integer> queue = new LinkedList<>();
        prob[start] = 1;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int sNode = queue.poll();
            Set<Integer> node = nodes.get(sNode);
            if (node == null) {
                continue;
            }
            for (Integer tmp : node) {
                int eNode = edges[tmp][0];
                if (eNode == sNode) {
                    eNode = edges[tmp][1];
                }
                
                if (prob[sNode] * succProb[tmp] > prob[eNode]) {
                    prob[eNode] = prob[sNode] * succProb[tmp];
                    queue.offer(eNode);
                }
        }
        }
        return prob[end];
    }
}
