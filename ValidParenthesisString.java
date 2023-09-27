package leetcode_test;

public class ValidParenthesisString {

	/**
	 * solve by greedy.
	 * 
	 * use 2 int, lo and hi to store the min and max number of extra '('.
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkValidString(String s) {
		// lo is the min number of extra '('
		// hi is the max number of extra '('
       int lo = 0, hi = 0;
       for (char c: s.toCharArray()) {
    	   // if c is not '(', no matter it is ')' or '*', it can remove a '('
           lo += c == '(' ? 1 : -1;
           // if c is not ')', no matter it is '(' or '*', it can add a ')'
           hi += c != ')' ? 1 : -1;
           // it means there is no enough '(', even all '*' are seen as '('
           if (hi < 0) break;
           // the number can not be less than 0
           // since in any place lo can not be less than 0, if don't handle it in every c, there may be lo less than 0 firstly and then finally become valid
           lo = Math.max(lo, 0);
       }
       // because lo must equal to or larger than 0, just check if lo is 0.
       return lo == 0;
	}

	/**
	 * solve by dp.
	 * 
	 * use boolean[i][j] to store the result of s[i->j], then for * or () the boolean[][] is true.
	 * then check from size 3->length.
	 * 
	 * the time complexity is O(N^3), but actually it will not reach to N in the 2nd and 3rd loop, 
	 * so it will not be overtime.
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkValidStringByDp(String s) {
		int length = s.length();
		boolean[][] dp = new boolean[length][length];
		//init, for * or (), the result will be true.
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == '*') {
				dp[i][i] = true;
			}
			if (i < length - 1 && (s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(i + 1) == ')' || s.charAt(i + 1) == '*')) {
				dp[i][i + 1] = true;
			}
		}
		
		//check size from 3->length
		for (int size = 2; size < length; size++) {
			for (int i = 0; i + size < length; i++) {
				if (s.charAt(i) == '*' && dp[i + 1][i + size]) {
					// this case, ch[i] is seen as empty
					dp[i][i + size] = true;
				} else if (s.charAt(i) == '*' || s.charAt(i) == '(') {
					// this case, ch[i] is seen '('
					// then we should check from i+1->i+size, to find out a ch ')'or'*' as the other part of ch[i],
					// and the 2 part divided by this ch are both valid
					for (int j = i + 1; j <= i + size; j++) {
						if ((s.charAt(j) == ')' || s.charAt(j) == '*') && 
								(j == i + 1 || dp[i + 1][j - 1]) && 
								(j == i + size || dp[j + 1][i + size])) {
							dp[i][i + size] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][length - 1];
	}
	
	/**
	 * solve by stack.
	 * 
	 * store the number of extra left parenthesis in stack(can be stack or int),
	 * then for every *, check 3 case: empty(same stack), '('(stack+1), ')'(stack-1),
	 * if any of these case can return to true, the final result is true.
	 * 
	 * since its time complexity is O(N^3), this solution will be overtime.
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkValidStringByStack(String s) {
		return checkParenthesis(s, 0, 0);
	}

	public boolean checkParenthesis(String s, int index, int stack) {
		if (stack < 0) {
			return false;
		} else if (index == s.length()) {
			return stack == 0;
		}
		
		for (int i = index; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack++;
				continue;
			} else if (s.charAt(i) == '*') {
				return checkParenthesis(s, index + 1, stack) || checkParenthesis(s, index + 1, stack + 1) || checkParenthesis(s, index + 1, stack - 1);
			} else if (stack == 0) {
				return false;
			}
			stack--;
		}
		return stack == 0;
	}
}
