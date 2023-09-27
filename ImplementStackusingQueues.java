package leetcode_test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * use queue to implement basic operation of stack.
 * store results in queue, and for every pop/top, poll all items in queue and restore it until the last one.
 * 
 */
public class ImplementStackusingQueues {

	/**
	 * queue is used to store the items, and tmp is used in pop/top to temporarily restore items
	 */
    Queue<Integer> queue, tmp;

    public ImplementStackusingQueues() {
        this.queue = new LinkedList<>();
        this.tmp = new LinkedList<>();
    }
    
    public void push(int x) {
        queue.offer(x);
    }
    
    public int pop() {
    	// error check
        if (this.empty()) {
            return -1;
        }
        
        this.tmp = new LinkedList<>();
        int res = this.queue.poll();
        while (!this.queue.isEmpty()) {
            this.tmp.offer(res);
            res = this.queue.poll();
        }
        this.queue = this.tmp;
        return res;
    }
    
    public int top() {
    	// the operation top can be seen as ((pop) + (push the last item))
        int res = this.pop();
        this.push(res);
        return res;
    }
    
    public boolean empty() {
        return this.queue.isEmpty();
    }
}
