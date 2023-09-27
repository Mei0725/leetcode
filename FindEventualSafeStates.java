package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {

	/**
	 * store the status of every node in arrays-res.
	 * check every node, and if it links to any unchecked-node, check that node firstly,
	 * do this operation until find a safe node/checked node/cycle.
	 * 
	 * @param graph
	 * @return
	 */
    public List<Integer> eventualSafeNodes(int[][] graph) {
    	// safe is used to store the result
        List<Integer> safe = new ArrayList<>();
        // res is used to store the status of every node
        int[] res = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            res[i] = checkSafeNode(i, res, graph);
            if (res[i] == 1) {
                safe.add(i);
            }
        }
        return safe;
        // return new ArrayList<>();
    }

    /**
     * check node-i's status.
     * 
     * the nodes' status are stored in arrays-res, and it has 4 status:
     * -1: unsafe node;
     * 0 : unchecked node;
     * 1 : safe node;
     * 2 : node is checking, it also means all checking nodes are in a cycle;
     * 
     * @param i
     * @param res
     * @param graph
     * @return
     */
    public int checkSafeNode(int i, int[] res, int[][] graph) {
        if (res[i] == 2) {
        	// i is in a cycle
        	// since safe node is a node without output or only link to safe node
        	// if it is in a cycle, it must be a unsafe node
            res[i] = -1;
            return res[i];
        } else if (res[i] != 0) {
        	// i has be checked before
            return res[i];
        } else if (graph[i].length == 0) {
        	// i do not have any output, it means it is a safe node
            res[i] = 1;
            return res[i];
        }

        // i is in checking
        res[i] = 2;
        for (int j = 0; j < graph[i].length; j++) {
            // System.out.println("graph[i][j]: " + graph[i][j]);
        	// if i link to any unsafe node, then it is an unsafe node too
            if (checkSafeNode(graph[i][j], res, graph) == -1) {
                res[i] = -1;
                break;
            }
        }
        // if all nodes i link to is safe, then i is safe node too
        if (res[i] == 2) {
            res[i] = 1;
        }
        return res[i];
    }
}
