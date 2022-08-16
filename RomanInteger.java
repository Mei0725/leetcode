package leetcode_test;

import java.util.Map;
import java.util.LinkedHashMap;

public class RomanInteger {

	public static void main(String[] args) {
//		int input = 1994;
//		int input = 58;
		int input = 3;
		String output = "";
		try {
			output = intToRoman(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static String intToRoman(int num) {
        String result = "";
        Map<String, Integer> symbols = new LinkedHashMap<>();
        symbols.put("M", 1000);
        symbols.put("D", 500);
        symbols.put("C", 100);
        symbols.put("L", 50);
        symbols.put("X", 10);
        symbols.put("V", 5);
        symbols.put("I", 1);
        String proKey = "";
        
        for (String key : symbols.keySet()) {
        	// the value start by 5(5/50/500) can be handled in the next item(1/10/100)
        	if (symbols.get(key).toString().startsWith("5")) {
        		proKey = key;
        		continue;
        	}
        	
        	int quotient = num / symbols.get(key);
        	if (quotient == 4) {
        		if (num > 100) {
        			result = result + "CD";
        		} else if (num > 10) {
        			result = result + "XL";
        		} else {
        			result = result + "IV";
        		}
        	} else if (quotient == 9) {
        		if (num > 100) {
        			result = result + "CM";
        		} else if (num > 10) {
        			result = result + "XC";
        		} else {
        			result = result + "IX";
        		}
        	} else {
        		if (quotient >= 5) {
        			result = result + proKey;
        			quotient -= 5;
        		}
        		while (quotient > 0) {
        			result = result + key;
        			quotient--;
        		}
        	}
        	num = num % symbols.get(key);
        }
        
        return result;
    }
}
