package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinWindowSubstring {

	public static void main(String[] args) {
//		String s = "ADOBECODEBANC";
//		String t = "ABC";
		String s = "aaa";
		String t = "aaa";
//		String s = "bba";
//		String t = "ab";
//		String s = "adobecodebancbbcaa";
//		String t = "abc";
		String output = "";
		try {
			output = minWindowOpti(s, t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * use the map[] to reduce the time and space complexity
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
    public static String minWindowOpti(String s, String t) {
        int start = 0;
        int end = 0;
        // use int[] to replace Map chNum
        int[] map = new int[128];
        int counter = 0;
        for(char ch:t.toCharArray()) {
            map[ch]++;
            counter++;
        }
		System.out.println("map: " + map);
		
        int minLen = Integer.MAX_VALUE;
        String str = "";
        // remove the end as the window's right
        while(end<s.length()) {
            char ch = s.charAt(end);
            if(map[ch]>0) {
                counter--;
            }
            // if ch does not contains in t, map[ch] will become < 0
            map[ch]--;
            if(counter == 0) {
            	// map[ch] == 0 means ch contains in t, so find out the first ch as the start
                while(start<=end && map[s.charAt(start)] <0) {
                	// when the ch remove for window, map[s.charAt(start)]++
                    map[s.charAt(start)]++;
                    start++;
                }
                // to check if it is the best result
                if(end-start+1<minLen) {
                    minLen = end - start + 1;
                    str = s.substring(start, end+1);
                }
            }
            end++;
        }
        return str;
    }

    /**
     * Sliding Window Solution.
     * Use a window to contain all chars in t.
     * When the substring does not contain all chars, remove the right of window
     * When the substring contains too much chars, remove the left of the window
     * 
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        String result = "";
    	if (t.length() <= 1) {
    		if (s.contains(t)) {
    			return t;
    		}
    		return result;
    	}
    	
    	// chId to store chars' position in the substring
    	// chNum to store the chars in t
        Map<Character, List<Integer>> chId = new HashMap<>();
        Map<Character, Integer> chNum = new HashMap<>();
        for (char ch : t.toCharArray()) {
        	chId.put(ch, new ArrayList<>());
        	chNum.put(ch, chNum.getOrDefault(ch, 0) + 1);
        }
		System.out.println("chId1: " + chId);
		System.out.println("chNum: " + chNum);
        
        List<Integer> chList = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if (chNum.containsKey(ch)) {
    			System.out.println("ch: " + ch);
    			System.out.println("chId1: " + chId);
        		if (chId.get(ch).size() < chNum.get(ch)) {
        			// when ch is not enough in windows, just put it into windows
        			chId.get(ch).add(i);
        		} else if (chId.get(ch).size() == chNum.get(ch)) {
        			// when ch is too much in windows, remove the first ch in chList to make sure when the window's left remove, its position can lead to the min substring
        			List<Integer> tmp = chId.get(ch);
        			chList.remove(tmp.get(0));
        			tmp.remove(0);
        			tmp.add(i);
        			chId.put(ch, tmp);
        		}
        		chList.add(i);
    			System.out.println("chId: " + chId);
    			System.out.println("chList: " + chList);
        		
    			// if the substring contains all chars in t, check if it is the result
        		if (chList.size() == t.length()) {
        			if (result.length() == 0 || i - chList.get(0) + 1 < result.length()) {
        				result = s.substring(chList.get(0), i + 1);
        			}
        			// no matter if this substring is the best result, remove the window's left to find the next substring
        			char ch0 = s.charAt(chList.get(0));
        			List<Integer> tmp = chId.get(ch0);
        			tmp.remove(0);
        			chId.put(ch0, tmp);
        			chList.remove(0);
        			System.out.println("chId2: " + chId);
        		}
        	}
			System.out.println("========");
        }
        return result;
    }
}
