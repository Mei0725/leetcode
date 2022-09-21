package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		String[] input = {"eat","tea","tan","ate","nat","bat"};
//		String[] input = {"a","aa"};
		List<List<String>> output = null;
		try {
			output = groupAnagrams(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("result: "); 
		for (List<String> result : output) {
			System.out.print(result + ","); 
		}
	}

	/**
	 * resort chars in every str, and then store the sorted-str as key into a map
	 * 
	 * @param strs
	 * @return
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> result = new HashMap<>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String sortStr = String.valueOf(chars);
			// String.valueOf can save time(19ms->12ms)
			/*StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(chars);
			String sortStr = strBuilder.toString();*/
			// use StringBuilder can save time(45ms->19ms)
			/*String sortStr = "";
			for (char ch : chars) {
				sortStr = sortStr + ch;
			}*/
			if (result.containsKey(sortStr)) {
				result.get(sortStr).add(str);
			} else {
				List<String> added = new ArrayList<>();
				added.add(str);
				result.put(sortStr, added);
			}
		}
		return new ArrayList<>(result.values());
	}

	public static List<List<String>> groupAnagramsOverTime(String[] strs) {
		Map<int[], List<String>> result = new HashMap<>();
		for (String str : strs) {
			int[] strCh = new int[128];
			for (char ch : str.toCharArray()) {
				strCh[ch]++;
			}
			int[] match = null;
			for (int[] key : result.keySet()) {
				char ch;
				for (ch = 'a'; ch <= 'z'; ch++) {
					if (key[ch] != strCh[ch]) {
						break;
					}
				}
				if (ch == 'z' + 1) {
					match = key;
					break;
				}
			}
			if (match == null) {
				List<String> added = new ArrayList<>();
				added.add(str);
				result.put(strCh, added);
			} else {
				result.get(match).add(str);
			}
		}
		return new ArrayList<>(result.values());
	}

}
