package leetcode_test;

import java.util.Stack;

public class AsteroidCollision {

	/**
	 * solve by stack.
	 * store asteroids into stack by order. if the previous one is positive and current one is negative,
	 * check the result of collisions, until there is no collision.
	 * 
	 * it can also solve by arrays, use index j, but the code will become complicated.
	 * 
	 * @param asteroids
	 * @return
	 */
    public int[] asteroidCollision(int[] asteroids) {
    	// store the survived asteroids
        Stack<Integer> survive = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (survive.isEmpty()) {
                survive.add(asteroids[i]);
                continue;
            }
            int tmp = asteroids[i];
            // only when the previous goes right and current one gose left can case collisions
            while (!survive.isEmpty() && survive.peek() > 0 && tmp < 0) {
                int pre = survive.pop();
                if (pre == -tmp) {
                	// both 2 explode, init tmp a value illegal
                    tmp = 2000;
                    break;
                } else if (pre > -tmp) {
                    tmp = pre;
                }
            }
            // if it is not the case both 2 explode, add the survived asteroid into stack
            if (tmp != 2000) {
                survive.add(tmp);
            }
            // System.out.println("survive: " + survive);
        }

        // put items in stack into result arrays
        int[] res = new int[survive.size()];
        for (int i = survive.size() - 1; i >= 0; i--) {
            res[i] = survive.pop();
        }
        return res;
    }
}
