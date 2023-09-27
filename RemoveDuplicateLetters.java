package leetcode_test;

import java.util.HashMap;
import java.util.Stack;

public class RemoveDuplicateLetters {

	/**
	 * solve by Monotonic Stack
	 * 
	 * @param s
	 * @return
	 */
	public String removeDuplicateLetters(String s) {
		// store the result
		Stack<Character> stack = new Stack<>();
		// mark if letter('a'+i) exist in stack
		int[] mark = new int[26];
		// store the last index of letter
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), i);
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// if c exist in the stack, then the result in stack is the best one, continue
			if (mark[c - 'a'] == 1) {
				continue;
			}

			// remove all letters which are less than c and are not the last one of stack
			while (!stack.isEmpty() && c < stack.peek() && i < map.get(stack.peek())) {
				char tmp = stack.pop();
				mark[tmp - 'a'] = 0;
			}
			stack.add(c);
			mark[c - 'a'] = 1;
		}

		/*String output = "";
		for (char ch : stack) {
			output = ch + output;
		}
		return output;*/
		// use StringBuilder help to save time and space
		StringBuilder sb = new StringBuilder();   
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
	}
    
	/**
	 * when every add a new letter into stack, check if the new word is smaller the previous one
	 * if it is, update to the new word
	 * 
	 * can not solve the test case "bcabc"->"abc"(the wrong output is "bac")
	 * 
	 * @param s
	 * @return
	 */
	public String removeDuplicateLettersError(String s) {
		Stack<Character> stack = new Stack<Character>();
		boolean[] mark = new boolean[26];
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (!mark[c - 'a']) {
				mark[c - 'a'] = true;
				stack.add(c);
				continue;
			}
			String s1 = "";
			while (stack.peek() != c) {
				s1 = s1 + stack.pop();
			}
			if (s1.length() == 0) {
				continue;
			} else if (s1.charAt(0) - c > 0) {
				stack.pop();
			}
			for (char ch : s1.toCharArray()) {
				stack.add(ch);
			}
			if (s1.charAt(0) - c > 0) {
				stack.add(c);
			}
		}

		String output = "";
		while (stack.size() > 0) {
			output = output + stack.pop();
		}
		return output;
	}
}
