package leetcode_test;

public class LongestStrPre {

	public static void main(String[] args) {
		String[] input = {"flower","flow","flight"};
		String output = "MCMXCIV";
		try {
			output = longestCommonPrefix(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static String longestCommonPrefix(String[] strs) {
        String result = "";
        
        try {
            int index = 0;
            while(true) {
                boolean same = true;
                char ch1 = strs[0].charAt(index);
                int strNum = 0;
                while (strNum < strs.length) {
                    if (strs[strNum].charAt(index) != ch1) {
                        same = false;
                        break;
                    }
                    strNum++;
                }
                if (!same) {
                    break;
                }
                result = result + String.valueOf(ch1);
                index++;
            	System.out.println("index: " + index);
            }
        } catch (Exception e) {
        	System.out.println("Exception: " + e);
            // break
        }
        
        return result;
    }
}
