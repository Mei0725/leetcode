package leetcode_test;

/**
 * solve by arrays.
 * 
 * use items to store all items, i is the next item's index, and min is the index of min value.
 * 
 * for every pop(), it should if the poped one is the min value and if it is, find out a new one,
 * so its time complexity is O(N)
 * and time complexity of all other functions are O(1)
 *
 */
public class MinStack {

    int[] items;
    int i, min;

    public MinStack() {
        items = new int[30000];
        i = 0; 
        min = -1;
    }
    
    public void push(int val) {
        if (min == -1 || val < items[min]) {
            min = i;
        }
        items[i++] = val;
    }
    
    public void pop() {
        i--;
        if (i == 0) {
            min = -1;
        } else if (min == i) {
            min = 0;
            for (int tmp = 1; tmp < i; tmp++) {
                if (items[tmp] < items[min]) {
                    min = tmp;
                }
            }
        }
    }
    
    public int top() {
        return items[i - 1];
    }
    
    public int getMin() {
        return items[min];
    }
}

/**
 * solve by stack.
 * 
 * for every item, store the pair(val, min), min is the min value from start to item,
 * since items are added in order, the last item's min is the min value for current stack.
 * 
 * time complexity of all functions are O(1), 
 * but the real time(5ms->6ms) and space(45.6MB->47.4MB) are bigger than the previous class
 * (do not know the reason)
 */
/*class MinStack {

    Stack<Pair<Integer, Integer>> stack;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.add(new Pair<>(val, val));
        } else {
            int min = Math.min(stack.peek().getValue(), val);
            stack.add(new Pair<>(val, min));
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().getKey();
    }
    
    public int getMin() {
        return stack.peek().getValue();
    }
}*/
