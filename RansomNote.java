package leetcode_test;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

	/**
	 * solve by map.
	 * store all letters into map and get it when checking ransomNote.
	 * 
	 * @param ransomNote
	 * @param magazine
	 * @return
	 */
    public boolean canConstructByMap(String ransomNote, String magazine) {
        Map<Character, Integer> chars = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            chars.put(ch, chars.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            Integer num = chars.get(ransomNote.charAt(i));
            if (num == null || num == 0) {
                return false;
            }
            chars.put(ransomNote.charAt(i), num - 1);
        }
        return true;
    }

    /**
     * solve by array.
     * store letter into array instead of map.
     * it can help to save time(20ms->3ms) and space(44.3MB->43.9MB)
     * 
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstructByArray(String ransomNote, String magazine) {
        char[] chars = new char[26];
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            chars[ch - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            if (chars[index] < 1) {
                return false;
            }
            chars[index]--;
        }
        return true;
    }
}
