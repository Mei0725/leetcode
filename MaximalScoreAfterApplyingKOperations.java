package leetcode_test;

import java.util.Arrays;

public class MaximalScoreAfterApplyingKOperations {
	
	/**
	 * Since int[] is sorted, then the changed values are sorted too
	 * so just use int[] array to store the changed values, and find out the max one for nums and array
	 * to save memory, we can use Queue to instead of int[] array
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public long maxKelements(int[] nums, int k) {
        int[] array = new int[k];
        long score = 0;
        Arrays.sort(nums);
        int i1 = nums.length - 1, i2 = 0, i3 = 0;
        while (k > 0) {
            if (i1 >= 0 && nums[i1] >= array[i2]) {
                score += nums[i1];
                array[i3++] = (int)Math.ceil((double)nums[i1--] / 3);
            } else{
                score += array[i2];
                array[i3++] = (int)Math.ceil((double)array[i2++] / 3);
            }
            k--;
        }
        return score;
    }

	/**
	 * Create a binary tree to store int[]
	 * in this way, it is easy to find the max num and change the value
	 * but when int[] is sorted, the tree will not be efficient and it will be overtime when int[].length is big enough
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
    public long maxKelementsOverTime(int[] nums, int k) {
        Node root = createTree(nums);
        long score = 0;
        while (k > 0) {
            Node max = findMax(root);
            // System.out.println("max: " + max.val);
            score += max.val;
            root = changeVal(root, max);
            k--;
        }
        return score;
    }

    public Node createTree(int nums[]) {
        Node root = null;
        for (int i = 0; i < nums.length; i++) {
            root = addNode(root, nums[i]);
        }
        return root;
    }

    public Node addNode(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        Node tmp = root;
        Node parent = null;
        boolean left = true;
        while (tmp != null && value != tmp.val) {
            parent = tmp;
            if (value < tmp.val) {
                tmp = tmp.left;
                left = true;
            } else {
                tmp = tmp.right;
                left = false;
            }
        }
        if (tmp == null) {
            tmp = new Node(value);
            tmp.parent = parent;
            if (left) {
                parent.left = tmp;
            } else {
                parent.right = tmp;
            }
        } else {
            tmp.num++;
        }
        return root;
    }

    public Node findMax(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public Node changeVal(Node root, Node node) {
        int newVal = node.val % 3 == 0 ? node.val / 3 : (node.val / 3 + 1);
        if (newVal == node.val) {
            return root;
        }

        if (node.num == 1) {
            if (node.left != null) {
                node.left.parent = node.parent;
            }
            if (node.parent == null) {
                root = node.left;
            } else {
                node.parent.right = node.left;
            }
        } else {
            node.num--;
        }
        addNode(root, newVal);
        return root;
    }

    public class Node {
        int num = 1;
        int val = 0;
        Node left = null, right = null, parent = null;

        public Node(int val) {
            this.num = 1;
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}
