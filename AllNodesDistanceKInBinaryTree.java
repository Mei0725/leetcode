package leetcode_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllNodesDistanceKInBinaryTree {

	/**
	 * use queue to store the path to got to target.
	 * then for every node in path, check the result in the another side.
	 * 
	 * @param root
	 * @param target
	 * @param k
	 * @return
	 */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    	// find out the path to go to target
        Queue<TreeNode> path = new LinkedList<>();
        findNode(root, target, path);

        List<Integer> res = new ArrayList<>();
        // get result in target's leaves
        distanceK(target, null, k, 0, res);
        // System.out.println("res: " + res);
        // get result in parents' the other side
        // since when k=0, the current node will be added into result,
        // so we need this check to avoid to add target into result twice.
        if (k != 0) {
            distanceK(target, path, k, 1, res);
        }
        // System.out.println("res: " + res);
        return res;
    }

    /**
     * find out the path to go to target
     * 
     * @param root
     * @param target
     * @param path
     * @return
     */
    public boolean findNode(TreeNode root, TreeNode target, Queue<TreeNode> path) {
        if (root == null) {
            return false;
        } else if (root.val == target.val) {
        	// the target should not be added into path
            // path.add(root);
            return true;
        }

        // root is not target but is in the path to target
        if (findNode(root.left, target, path) || findNode(root.right, target, path)) {
            path.add(root);
            return true;
        }
        return false;
    }
    
    /**
     * find out all nodes that the distance to node is k.
     * 
     * @param node    the node
     * @param path    the path from root to node
     * @param k       the distance
     * @param side    0-only check node's leaves; 1-check node's parent and its the other leaf
     * @param res     the list to store all result nodes
     */
    public void distanceK(TreeNode node, Queue<TreeNode> path, int k, int side, List<Integer> res) {
    	// when side is 1, then k can be -1 when its parents is in res
        if (node == null || k < 0) {
            return;
        } else if (k == 0) {
            res.add(node.val);
            return;
        }
        
        if (side == 0) {
        	// check 2 leaves
            distanceK(node.left, null, k - 1, 0, res);
            distanceK(node.right, null, k - 1, 0, res);
        } else if (path != null && !path.isEmpty()) {
            TreeNode parent = path.poll();
            // System.out.println("parent: " + parent.val);
            // check its parents
            distanceK(parent, path, k - 1, 1, res);
            // check the other leaf of parent
            if (parent.left != null && parent.left.val == node.val) {
                distanceK(parent.right, null, k - 2, 0, res);
            } else if (parent.right != null && parent.right.val == node.val) {
                distanceK(parent.left, null, k - 2, 0, res);
            }
        }
        return;
    }
}
