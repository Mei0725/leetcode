package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

	public static void main(String[] args) {
//		String input = "25525511135";
//		String input = "0000";
		String input = "101023";
		List<String> output = null;
		try {
			output = restoreIpAddresses(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restoreIpAddresses(s, "", 1, result);
        return result;
    }
    
    /**
     * the dp solution
     * 
     * @param rest  The rest part of string
     * @param currStr  The current ip string
     * @param port  How many port the current ip have
     * @param result List to sort the result ip
     */
    public static void restoreIpAddresses(String rest, String currStr, int port, List<String> result) {
//		System.out.println("rest: " + rest);
//		System.out.println("currStr: " + currStr);
    	if (port == 4) {
    		if (rest.length() > 1 && rest.charAt(0) == '0') {
    			return;
    		}
    		int tmp = Integer.valueOf(rest);
    		if (tmp <= 255) {
    			result.add(currStr + "." + rest);
    		}
			return;
    	}
    	
    	if (rest.length() > (5 - port) * 3 || rest.length() < 5 - port) {
    		return;
    	}
    	int index = 0;
    	int tmp = rest.charAt(index) - '0';
    	StringBuilder sb = new StringBuilder();
    	if (!currStr.isEmpty()) {
    		sb.append(currStr);
    		sb.append(".");
    	}
		sb.append(tmp);
//		System.out.println("currStr: " + sb.toString());
    	if (tmp == 0) {
    		restoreIpAddresses(rest.substring(index + 1), sb.toString(), port + 1, result);
    	} else {
        	while (tmp <= 255 && index < rest.length() - 1) {
        		restoreIpAddresses(rest.substring(index + 1), sb.toString(), port + 1, result);
        		index++;
        		tmp = tmp * 10 + rest.charAt(index) - '0';
        		sb.append(rest.charAt(index));
        	}
    	}
    }
}
