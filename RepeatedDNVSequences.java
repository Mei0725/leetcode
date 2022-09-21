package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNVSequences {

	public static void main(String[] args) {
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
//		String s = "AAAAAAAAAAA";
		List<String> output = null;
		try {
			output = findRepeatedDnaSequencesBit(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * use 4-bit result to sort identify substring, rather than string, to save space
	 * from 64.2 MB(findRepeatedDnaSequences) to 52.8 MB(findRepeatedDnaSequencesBit)
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> findRepeatedDnaSequencesBit(String s) {
	    if(s == null || s.length()==0) return new ArrayList<String>();
	    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	    map.put('A',0);
	    map.put('C',1);
	    map.put('G',2);
	    map.put('T',3);
	    HashSet<Integer> set = new HashSet<Integer>();
	    HashSet<String> twice = new HashSet<String>();
	    // Encode String to number
	    for(int i=0;i<s.length()-9;i++){
	        int code = 0;
	        for(int j=i;j<i+10;j++){
	        	// is same as (code = code << 2)
	        	// means moving code's 2-bit result to 2 position left (code = code * 4)
	            code <<= 2;
	            // |= reads the same way as +=
	            // in general, |= is the bit-wise OR operator
	            // e.g 001 | 010 = 011
	            code |= map.get(s.charAt(j));
	        }
    		System.out.println("codeRes: " + code);
	        if(set.contains(code)){
	            set.add(code);
	            twice.add(s.substring(i,i+10));
	        }else{
	            set.add(code);
	        }
	    }
	    return new ArrayList<String>(twice);
    }
	
	/**
	 * use set exist to sort the substring has appeared, and put the substring into result list if it has appeared before
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> findRepeatedDnaSequences(String s) {
    	if (s.length() < 10) {
    		return new ArrayList<>();
    	}
        Set<String> result = new HashSet<>();
        Set<String> exist = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
        	String tmpStr = s.substring(i, i + 10);
        	if (exist.contains(tmpStr)) {
        		result.add(tmpStr);
        	}
        	exist.add(tmpStr);
        }
        return new ArrayList<>(result);
    }
	
	/**
	 * may the function indexOf would spend too much time, this solution is overtime when s.length is big enough
	 * 
	 * @param s
	 * @return
	 */
    public static List<String> findRepeatedDnaSequencesOvertime(String s) {
    	if (s.length() < 10) {
    		return new ArrayList<>();
    	}
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
        	String tmpStr = s.substring(i, i + 10);
        	if (result.contains(tmpStr)) {
        		continue;
        	}
        	
        	String frontStr = s.substring(0, i), backStr = s.substring(i + 1);
//    		System.out.println("tmpStr: " + tmpStr);
//    		System.out.println("frontStr: " + frontStr);
//    		System.out.println("backStr: " + backStr);
        	if (frontStr.indexOf(tmpStr) != -1 || backStr.indexOf(tmpStr) != -1) {
        		result.add(tmpStr);
        	}
        }
        return result;
    }
}
