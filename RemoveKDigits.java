package leetcode_test;

import java.util.Stack;

public class RemoveKDigits {

	/**
	 * use monotonic stack to solve this problem.
	 * the digits must be removed from start to the end, they must be only descending ones
	 * so use stack to store digits which should be left, and when a new digit is added,
	 * remove all digits which are larger than it
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
    public String removeKdigits(String num, int k) {
        int length = num.length();
        Stack<Character> stack = new Stack<>();
        // mark the current last digit, only the new one larger than it can be added into stack
        char ch = '0';
        // since when k digits are removed, there may some unchecked digits in the last of num
        // use i to mark the length of left digits then we can add them into the end of result
        int i = 0;
        for (i = 0; i < length; i++) {
            char tmp = num.charAt(i);
            if (tmp >= ch) {
                stack.add(tmp);
            } else {
            	// remove all digits larger than tmp, unless all needed digits are removed
                while (k > 0 && !stack.isEmpty() && stack.peek() > tmp) {
                    stack.pop();
                    k--;
                }
                stack.add(tmp);
                // if k digits are removed, then break
                if (k == 0) {
                    break;
                }
            }
            ch = tmp;
        }
        // System.out.println("stack: " + stack);
        // System.out.println("i: " + i);

        //after last part of function, there are 2 case:
        //1.all digits are checked and there are k or less digits are removed, then all result digits are stored in stack
        //2.one a part of digits are checked but k digits are removed, then we should put stack and unchecked part of num together
        StringBuilder sb = new StringBuilder();
        // get all legal digits
        while (stack.size() > 0) {
            char tmp = stack.pop();
            // handle the case1, especially when there are less k digits removed
            if (k > 0) {
                k--;
                continue;
            }
            // even when tmp is '0' can not break
            // since it in the case2, there may some '0' left in the end of stack
            sb.append(tmp);
        }
        // since stack are pop for end to start, it should reverse to get the real result
        sb.reverse();
        
        //handle the case2
        if (i < length) {
            sb.append(num.substring(i + 1));
        }
        // handle the case that the start of result is 0
        String result = sb.toString().replaceFirst("^0*", "");
        // handle the case that all digits in result is 0 or no digit is left
        return result.length() != 0 ? result : "0";
    }
}
