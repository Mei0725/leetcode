package leetcode_test;

public class CountAndSay {

	public static void main(String[] args) {
		int input = 4;
		String output = "";
		try {
			output = countAndSay(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + output);
	}

	public static String countAndSay(int n) {
		String result = "1";
		
		for (int i = 2; i <= n ;i++) {
			char tmp = '.';
			int count = 0;
			StringBuilder str = new StringBuilder();
			for (char ch : result.toCharArray()) {
				if (ch == tmp) {
					count++;
				} else {
					if (tmp != '.') {
						str.append(count);
						str.append(tmp);
					}
					tmp = ch;
					count = 1;
				}
			}
			if (count != 0) {
				str.append(count);
				str.append(tmp);
			}
			result = str.toString();
//			System.out.println("result" + i + ":" + result);
		}
		return result;
    }
}
