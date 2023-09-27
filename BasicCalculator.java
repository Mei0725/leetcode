package leetcode_test;

import java.util.Stack;

public class BasicCalculator {

	/**
	 * solve by Recursion.
	 * use function calculate(String s, int i) to calcuate the result from i to the first ')'.
	 * whenever there is a '(' in i, then calculate i+1 to the first ')' as a new item
	 * 
	 * @param s
	 * @return
	 */
    public int calculate(String s) {
        int[] res = calculate(s, 0);
        return res[0];
    }

    /**
     * calcuate the result from i to the first ')' or the end.
     * return int[2]{res, new index};
     *  
     * @param s    the string
     * @param i    the start index
     * @return
     */
    public int[] calculate(String s, int i) {
    	// tmp is the result of previous number
        int res = 0, tmp = 0;
        // store the operation of the previous one
        boolean add = true;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            } else if (ch == '(') {
            	// get the result of the following ()
            	// and update the index
                int[] nums = calculate(s, i + 1);
                tmp = nums[0];
                i = nums[1];
            } else if (ch == ')' || ch == '+' || ch == '-') {
            	// it means it is the end of a number of ()
            	// calculate the result of the previous 2 numbers
                if (add) {
                    res += tmp;
                } else {
                    res -= tmp;
                }
                i++;
                // update the value of previous number
                // and store the operation for the next calculate
                tmp = 0;
                switch (ch) {
                    case '+':
                        add = true;
                        break;
                    case '-':
                        add = false;
                        break;
                    default:
                    	// it means it is the end of (), return to the outside loop
                        return new int[]{res, i};
                }
            } else {
            	// update the number's value
                tmp = tmp * 10 + (ch - '0');
                i++;
            }
            // System.out.println("tmp: " + tmp);
            // System.out.println("i: " + i);
            // System.out.println("res: " + res);
            // System.out.println("add: " + add);
            // System.out.println("---------------");
        }
        // calculate the last number
        return new int[]{add ? res + tmp : res - tmp, i};
    }

    /**
     * solve by stack. it will spend more time but less space.
     * 
     * store all numbers/+/-/( into stack, when there is a ), calculate the result from it to the nearest (
     * 
     * @param s
     * @return
     */
    public int calculateByStack(String s) {
        Stack<Character> chs = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch == ' ') {
                continue;
            } else if (ch == ')') {
            	// get the result in this ()
            	// and add the result into stack as a new item
                addToStack(chs, calculate(chs));
            } else {
                chs.add(ch);
            }
        }
        // System.out.println("final chs: " + chs);
        // calculate the result of a string without ()
        return calculate(chs);
    }

    /**
     * calculate the full string or to the nearest (
     * 
     * @param chs
     * @return
     */
    public int calculate(Stack<Character> chs) {
        int res = 0, num = 0;
        char tmp = ' ';
        while (!chs.isEmpty()) {
        	// use peek() instead of pop() so that the first number will not be removed
            tmp = chs.peek();
            if (tmp == '(') {
            	// it is the end of ()
                chs.pop();
                break;
            } else if (tmp == '+') {
            	// the following number should be added
                chs.pop();
                res += num;
                // avoid the last res += num; make wrong
                num = 0;
            } else if (tmp == '-') {
            	// the following number should be minus
                chs.pop();
                res -= num;
                // avoid the last res += num; make wrong
                num = 0;
            } else {
            	// get the nearest number
                num = getFirstNum(chs);
            }
        }
        res += num;
        // System.out.println("res: " + res);
        return res;
    }

    /**
     * get the last number in stack chs
     * 
     * @param chs
     * @return
     */
    public int getFirstNum(Stack<Character> chs) {
        int res = 0, count = 1;
        while (!chs.isEmpty()) {
            char ch = chs.peek();
            // System.out.println("ch: " + ch);
            // when ch is a number then it is a part of result number
            if (ch >= '0' && ch <= '9') {
                chs.pop();
                res += count * (ch - '0');
                count *= 10;
            } else {
                break;
            }
        }
        return res;
    }
    
    /**
     * add num to the end of chs.
     * 
     * @param chs
     * @param num
     */
    public void addToStack(Stack<Character> chs, int num) {
        Stack<Character> tmp = new Stack<>();
        // it should handle the case that num is negative
        boolean minus = false;
        if (num < 0) {
            minus = true;
            num = -num;
        }
        // make sure num is larger than 0
        while (num != 0) {
            tmp.add((char)('0' + (num % 10)));
            num /= 10;
        }
        // handle the case that num is negative
        // it means the previous +/- may be changed
        if (minus) {
            if (chs.isEmpty()) {
                chs.add('-');
            } else if (chs.peek() == '-') {
                chs.pop();
                chs.add('+');
            } else if (chs.peek() == '+') {
                chs.pop();
                chs.add('-');
            }
        }
        // add the letter of num into chs in the correct order
        while (!tmp.isEmpty()) {
            chs.add(tmp.pop());
        }
        // System.out.println("chs: " + chs);
    }
}
