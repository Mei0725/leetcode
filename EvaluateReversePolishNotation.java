package leetcode_test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateReversePolishNotation {

	/**
	 * use stack to store unchecked numbers,
	 * and whenever there is a operator, get the nearest 2 numbers and get a new res, which should be added to stack too.
	 * 
	 * @param tokens
	 * @return
	 */
    public int evalRPN(String[] tokens) {
    	// store the unchecked numbers
        Stack<Integer> tmp = new Stack<>();
        // store all operators make the check code look clearly
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        for (int i = 0; i < tokens.length; i++) {
        	// check if this item is a number
            if (!operators.contains(tokens[i])) {
                tmp.add(Integer.parseInt(tokens[i]));
                continue;
            }

            // get the calculated result
            int m = tmp.pop();
            int n = tmp.pop();
            int res = 0;
            switch(tokens[i]) {
                case "+":
                    res = m + n;
                    break;
                case "-":
                    res = n - m;
                    break;
                case "*":
                    res = m * n;
                    break;
                case "/":
                    res = n / m;
                    break;
                default:
                    break;
            }
            tmp.add(res);
        }
        return tmp.pop();
    }
}
