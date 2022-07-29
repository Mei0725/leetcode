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
    	// ���Ӷ� n^3 �����Ӷȹ��ߵ������㳬ʱ
    	// ����ѭ����ѭ��1�����λ�ȡ String ���ַ� i
    	//          ѭ��2�������ȡ��ȡ String ��β���� i ���ַ� j
    	//          ѭ��2��i j ��ͷ�������ж� i j �е��ַ����Ƿ�Ϊ����
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
    	// ���Ӷ� n^2
    	// ����ѭ����ѭ��1�����λ�ȡ String ���ַ� i ��Ϊ�����м�ֵ
    	//          ѭ��2���� i Ϊ�м�ֵ��ȡ���� n m �����������ϻ��������򵽴��ַ�����ͷ
    	//                �� i �� i+1 һ��ʱ�����⴦����ĳ���Ϊż�������
    	// �����Ż���ѭ��1�д����뿪ʼ��ȡ i ���ֱ�����ͷ���죬����ͷʣ���ַ�����С�ڵ�ǰ result һ��ʱֹͣ
    	String result = s.substring(0, 1);
    	int i = 0;
    	// �Ż�����iȡ��ʣ���ַ������Ⱦ��Բ����ܳ��ڵ�ǰ result ʱ��ֱ�ӷ���
//    	for (int i = 0; i < s.length(); i++) {
    	while (i < s.length() - result.length() / 2) {
    		int m, n;
    		String longestSub1, longestSub2;
    		
    		// ��ȡ i Ϊ�����ַ��Ļ��ģ�������Ļ���һ������
			m = i - 1;
			n = i + 1;
			longestSub1 = checkLongestSub(s, m, n);

    		// �� i �� i+1 һ��ʱ�����⴦����ĳ���Ϊż�������
			// ��ʹ���������ͬ����Ҫ��ȡ��������Ϊ i ��������������� aaa
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
    		// �Ż����� result Ϊ String ����ʱ�ض�Ϊ���Ž⣬��ֱ�ӷ���
    		if (result.length() == s.length()) {
    			return result;
    		}
    		i++;
    	}
        return result;
    }
    
    /**
     * ������յ�ֱ�Ϊ m �� n ��ǰ��������ͷ���죬��ȡ�ַ��� s �е�������
     * 
     * @param s ��ʼ�ַ���
     * @param m ������
     * @param n �յ����
     * @return �����
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
