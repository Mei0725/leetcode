package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

	public static void main(String[] args) {
		String input = "";
		List<String> output = null;
		try {
			output = letterCombinations(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, String> digitsToLetter = new HashMap<>();
        digitsToLetter.put("2", "abc");
        digitsToLetter.put("3", "def");
        digitsToLetter.put("4", "ghi");
        digitsToLetter.put("5", "jkl");
        digitsToLetter.put("6", "mno");
        digitsToLetter.put("7", "pqrs");
        digitsToLetter.put("8", "tuv");
        digitsToLetter.put("9", "wxyz");
        
        for (int i = 0; i < digits.length(); i++) {
        	char digit = digits.charAt(i);
        	String letters = digitsToLetter.get(String.valueOf(digit));
        	List<String> tmpResult = new ArrayList<>();
        	
        	for (int j = 0; j < letters.length(); j++) {
        		char letter = letters.charAt(j);
        		if (result.size() == 0) {
        			tmpResult.add(String.valueOf(letter));
        		} else {
                	for (String item : result) {
                		tmpResult.add(item + String.valueOf(letter));
                	}
        		}
        	}
        	result = tmpResult;
        }
        return result;
    }
}
