package leetcode_test;

public class LongestPalindromicSub {

	public static void main(String[] args) {
		String input = "ababd";
		String output = "no result";
		try {
			output = longestPalindrome(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static String longestPalindrome(String s) {
    	// 复杂度 n^3 ，复杂度过高导致运算超时
    	// 三层循环：循环1：依次获取 String 中字符 i
    	//          循环2：倒叙获取获取 String 中尾端至 i 的字符 j
    	//          循环2：i j 两头缩进，判断 i j 中的字符串是否为回文
    	/*String result = s.substring(0, 1);
    	for (int i = 0; i < s.length(); i++) {
    		for (int j = s.length() - 1; j > i; j--) {
    			int m = i, n = j;
    			boolean meet = true;
    			while (m < n) {
    				if (s.charAt(m) != s.charAt(n)) {
    					meet = false;
    					break;
    				}
    				m++;
    				n--;
    			}
    			if (meet && result.length() < (j - i + 1)) {
//    				System.out.println("i: " + i);
//    				System.out.println("j: " + j);
    				result = s.substring(i, j + 1);
    			}
    		}
    	}
        return result;*/
    	// 复杂度 n^2
    	// 两层循环：循环1：依次获取 String 中字符 i 作为回文中间值
    	//          循环2：以 i 为中间值，取两侧 n m 延伸至不符合回文条件或到达字符串尽头
    	//                当 i 与 i+1 一样时需特殊处理回文长度为偶数的情况
    	// 可再优化：循环1中从中央开始获取 i ，分别向两头延伸，当两头剩余字符长度小于当前 result 一半时停止
    	String result = s.substring(0, 1);
    	int i = 0;
    	// 优化：当i取至剩余字符串长度绝对不可能长于当前 result 时可直接返回
//    	for (int i = 0; i < s.length(); i++) {
    	while (i < s.length() - result.length() / 2) {
    		int m, n;
    		String longestSub1, longestSub2;
    		
    		// 获取 i 为中心字符的回文，该情况的回文一定存在
			m = i - 1;
			n = i + 1;
			longestSub1 = checkLongestSub(s, m, n);

    		// 当 i 与 i+1 一样时需特殊处理回文长度为偶数的情况
			// 即使满足该条件同样需要获取回文中心为 i 的情况，处理例如 aaa
			longestSub2 = "";
    		if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
    			m = i;
    			n = i + 1;
    			longestSub2 = checkLongestSub(s, m, n);
    		}
    		
    		if (result.length() < longestSub1.length() && longestSub2.length() < longestSub1.length()) {
    			result = longestSub1;
    		} else if (result.length() < longestSub2.length() && longestSub1.length() < longestSub2.length()) {
    			result = longestSub2;
    		}
    		// 优化：当 result 为 String 本身时必定为最优解，可直接返回
    		if (result.length() == s.length()) {
    			return result;
    		}
    		i++;
    	}
        return result;
    }
    
    /**
     * 在起点终点分别为 m 和 n 的前提下向两头延伸，获取字符串 s 中的最大回文
     * 
     * @param s 初始字符串
     * @param m 起点序号
     * @param n 终点序号
     * @return 最长回文
     */
    public static String checkLongestSub(String s, int m, int n) {
		while (m >= 0 && n <s.length()) {
			if (s.charAt(m) != s.charAt(n)) {
				m++;
				n--;
				break;
			}
			m--;
			n++;
		}
		if (m < 0 || n >= s.length()) {
			m++;
			n--;
		}
		System.out.println("m2: " + m);
		System.out.println("n2: " + n);
		return s.substring(m, n + 1);
    }
}
