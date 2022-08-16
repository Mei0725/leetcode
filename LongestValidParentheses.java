package leetcode_test;

import java.util.Stack;

public class LongestValidParentheses {

	public static void main(String[] args) {
//		String input = ")()())";
		String input = "()(()";
//		String input = "()(())";
//		String input = ")()())()()(";
		int output = -1;
		try {
			output = longestValidParenthesesDP(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * solve by Dynamic Programming
	 * use dp[] to store the longest valid parentheses num from this substring
	 * for example, '()(()' can be stored as [0 2 0 0 2], the second 2 means in the substring i = 2->5, the longest result is 2
	 * 
	 * to get the right result, we should only store result in the position related to )
	 * if there is (), then dp[i] must +2
	 * if there is )), then if the (i - dp[i - 1] - 1) is '(', it means that substring from (i - dp[i - 1] - 1)->i is valid,
	 * so the result of dp[i] is (dp[i - 1] + 2){substring from (i - dp[i - 1] - 1)->i} + dp[i - dp[i - 1] - 2]{the linked valid substring if it existed}
	 * 
	 * the total result is the max one in dp[]
	 * 
	 * @param s
	 * @return
	 */
	public static int longestValidParenthesesDP(String s) {
		if (s.length() < 2) {
			return 0;
		}
		
		int result = 0;
		int[] dp = new int[s.length()];
		if (s.charAt(0) == '(' && s.charAt(1) == ')') {
			dp[1] = 2;
		}
		for(int i = 2; i < s.length(); i++) {
			if(s.charAt(i) == ')' && s.charAt(i - 1) == '(') {
				dp[i] = dp[i - 2] + 2;
			} else if (s.charAt(i) == ')' && s.charAt(i - 1) == ')') {
				System.out.println("test " + (i - dp[i - 1] - 1));
				if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					if (i - dp[i - 1] - 1 > 0) {
						dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
					} else {
						dp[i] = dp[i - 1] + 2;
					}
				} else {
					dp[i] = 0;
					result = Math.max(result, dp[i - 1]);
				}
			} else {
				dp[i] = 0;
				result = Math.max(result, dp[i - 1]);
			}
		}
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println("");
		return Math.max(result, dp[s.length() - 1]);
	}
	/**
	 * solve the problem by stack
	 * use a stack to store the position that cut s, so the result is the max difference of all position
	 * 
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses(String s) {
        Stack<Character> chars = new Stack<Character>();
        Stack<Integer> positions = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
        	if (chars.isEmpty()) {
        		chars.push(s.charAt(i));
        		positions.push(i);
        		continue;
        	}
        	
        	char topCh = chars.peek();
        	if (topCh == '(' && s.charAt(i) == ')') {
        		chars.pop();
        		positions.pop();
        	} else {
        		chars.push(s.charAt(i));
        		positions.push(i);
        	}
        }
        
        if (positions.isEmpty()) {
        	return s.length();
        }
        int rightP = s.length();
        int result = 0;
        while (!positions.isEmpty()) {
        	int leftP = positions.pop();
        	result = Math.max(rightP - leftP - 1, result);
        	rightP = leftP;
        }
        return Math.max(rightP, result);
    }
}
