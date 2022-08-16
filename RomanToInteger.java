package leetcode_test;

import java.util.Map;
import java.util.LinkedHashMap;

public class RomanToInteger {

	public static void main(String[] args) {
		int output = 3;
		String input = "MCMXCIV";
		try {
			output = romanToInt(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int romanToInt(String s) {
        int result = 0;
        Map<String, Integer> symbols = new LinkedHashMap<>();
        symbols.put("M", 1000);
        symbols.put("CM", 900);
        symbols.put("D", 500);
        symbols.put("CD", 400);
        symbols.put("C", 100);
        symbols.put("XC", 90);
        symbols.put("L", 50);
        symbols.put("XL", 40);
        symbols.put("X", 10);
        symbols.put("IX", 9);
        symbols.put("V", 5);
        symbols.put("IV", 4);
        symbols.put("I", 1);
        int index = 0;
        
        while(index < s.length()) {
        	String ch = s.substring(index, index + 1);
        	String ch2 = null;
        	if (index < s.length() - 1) {
            	ch2 = s.substring(index, index + 2);
        	}
        	if (ch2 != null && symbols.get(ch2) != null) {
        		result += symbols.get(ch2);
        		index +=2;
        	} else {
        		result += symbols.get(ch);
        		index++;
        	}
        }
        
        return result;
    }
}
