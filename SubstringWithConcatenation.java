package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringWithConcatenation {

	public static void main(String[] args) {
//		String s = "barfoothefoobarman";
//		String[] words = {"foo","bar"};
//		String s = "barfoofoobarthefoobarman";
//		String[] words = {"foo","bar","the"};
//		String s = "wordgoodgoodgoodbestword";
//		String[] words = {"word","good","best","word"};
//		String s = "ababababab";
//		String[] words = {"ababa","babab"};
//		String s = "ababaab";
//		String[] words = {"ab","ba","ba"};
		String s = "thethethethe";
		String[] words = {"foo","foo","the","man"};
		List<Integer> output = null;
		try {
			output = findSubstring(s, words);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	// sort the number of every word
    private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    // string's length
    private int n;
    // every word's length
    private int wordLength;
    // result substring's length
    private int substringSize;
    // the length of arrays words
    private int k;
    
    public List<Integer> findSubstringSlidingWindows(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
        	// in this function, it checks index i, i + wordLength * 1, i + wordLength * 2 ...
        	// so the number of wordLength cycle is enough to check the total string
            slidingWindow(i, s, answer);
        }
        
        return answer;
    }

    private void slidingWindow(int left, String s, List<Integer> answer) {
    	// to store the words from left to right
        HashMap<String, Integer> wordsFound = new HashMap<>();
        // the num of meeting words from left to right
        int wordsUsed = 0;
        // if the num of same words is bigger than it contains in arrays words
        boolean excessWord = false;
        
        // Do the same iteration pattern as the previous approach - iterate
        // word_length at a time, and at each iteration we focus on one word
        for (int right = left; right <= n - wordLength; right += wordLength) {
            
            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                // Mismatched word - reset the window
                wordsFound.clear();
                wordsUsed = 0;
                excessWord = false;
                left = right + wordLength;
            } else {
                // If we reached max window size or have an excess word
            	// the total length from left to right is (right - left + wordLength), so when (right - left == substringSize), it is oversize
            	// when excessWord is true, it means the num of same words is bigger than it contains in arrays words, so the excess word must be removed
                while (right - left == substringSize || excessWord) {
                    String leftmostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(leftmostWord, wordsFound.get(leftmostWord) - 1);

                    if (wordsFound.get(leftmostWord) <= wordCount.get(leftmostWord)) {
                        // This word was an excess word
                        excessWord = false;
                    } else {
                        // Otherwise we actually needed it
                        wordsUsed--;
                    }
                }
                
                // Keep track of how many times this word occurs in the window
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    // Found too many instances already
                    excessWord = true;
                }
                
                if (wordsUsed == k && !excessWord) {
                    // Found a valid substring
                    answer.add(left);
                }
            }
        }
    }
    
	/**
	 * get the total length of the result substring, and then check every possible substring
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring(String s, String[] words) {
		if (words.length == 0) {
        	return new ArrayList<>();
        }
		
		List<Integer> result = new ArrayList<>();
		int oneLength = words[0].length();
		int totalLength = words.length * words[0].length();
		for (int i = 0; i <= s.length() - totalLength; i++) {
			List<String> subS = new ArrayList<>();
			// since the chars in words may be overlapping, we shouldn't simply replace word to ""
			// e.g: "ababaab" {"ab","ba","ba"}
			for (int j = i; j < i + totalLength; j+= oneLength) {
				subS.add(s.substring(j, j + oneLength));
			}
			for (String word : words) {
				if (!subS.remove(word))
					break;
			}
			if (subS.size() == 0) {
				result.add(i);
			}
		}
		return result;
	}
	
	/**
	 * this solution would lead to Time Limit Exceeded when words is single char and s is long enough
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstringOvertime(String s, String[] words) {
        if (words.length == 0) {
        	return new ArrayList<>();
        }
        
        List<List<Integer>> indexs = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
        	List<Integer> tmpId = new ArrayList<>();
        	int id = s.indexOf(words[i]);
        	while (id != -1) {
        		tmpId.add(id);
        		id = s.indexOf(words[i], id + 1);
        	}
        	if (tmpId.isEmpty()) {
        		return new ArrayList<>();
        	}
        	indexs.add(tmpId);
        }

//		System.out.println("indexs: " + indexs);
        Set<Integer> result = new HashSet<>();
        int wordsLength = words[0].length();
        boolean end = false;
        while (!end) {
        	Set<Integer> remove = new HashSet<>();
        	int start = Integer.MAX_VALUE, id = -1;;
            for (int i = 0; i < indexs.size(); i++) {
            	if (indexs.get(i).get(0) < start) {
            		start = indexs.get(i).get(0);
            		id = i;
            	}
            }
            remove.add(id);
            int min = start;
//    		System.out.println("start0: " + start);
            while (remove.size() < indexs.size() && start + wordsLength + wordsLength <= s.length()) {
            	start += wordsLength;
            	boolean found = false;
            	for (int i = 0; i < indexs.size(); i++) {
            		if (remove.contains(i)) {
            			continue;
            		}
            		for (Integer j : indexs.get(i)) {
                    	if (j == start) {
                    		remove.add(i);
                    		found = true;
                    		break;
                    	}
                    }
            		if (found) {
            			break;
            		}
                }
        		if (!found) {
        			break;
        		}
            }
            if (remove.size() == indexs.size()) {
            	result.add(min);
            }
//    		System.out.println("result: " + result);
            if (indexs.get(id).size() == 1) {
            	break;
            }
            indexs.get(id).remove(0);
//    		System.out.println("indexs: " + indexs);
        }
        return new ArrayList<>(result);
	}
	
	
	/**
	 * this solution is too difficult to make sure which word should be moved.
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstringErr(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) {
        	return result;
        }
        
        Map<Integer, String> index = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
        	int tmpId = s.indexOf(words[i]);
        	while (index.containsKey(tmpId) && tmpId != -1) {
        		tmpId = s.indexOf(words[i], tmpId + 1);
        	}
        	if (tmpId == -1) {
            	return result;
        	}
        	index.put(tmpId, words[i]);
        }
        
        int wordsLength = words[0].length();
        List<Integer> ids = new ArrayList<>(index.keySet());
        ids.sort(Integer :: compareTo);
		System.out.println("ids: " + ids);
		while (ids.size() > 0 && ids.get(0) > -1) {
			boolean removeFirst = false, removeLast = false;
			int i = 1;
			for (i = 1; i < ids.size(); i++) {
				if (ids.get(i) - ids.get(i - 1) > wordsLength) {
					removeFirst = true;
					break;
				} else if (ids.get(i) - ids.get(i - 1) < wordsLength) {
					removeLast = true;
					break;
				}
			}

			if (!removeFirst && !removeLast) {
				result.add(ids.get(0));
				removeFirst = true;
			}
			
			int oldId;
			String tmpWord;
			if (removeFirst) {
				oldId = ids.get(0);
				tmpWord = index.get(oldId);
			} else {
				oldId = ids.get(i);
				tmpWord = index.get(oldId);
			}
			int newId = s.indexOf(tmpWord, oldId + 1);
        	while (index.containsKey(newId) && newId != -1) {
        		newId = s.indexOf(tmpWord, newId + 1);
        	}
        	if (newId == -1) {
        		break;
        	}
			index.put(newId, tmpWord);
			index.remove(oldId);
			ids = new ArrayList<>(index.keySet());
	        ids.sort(Integer :: compareTo);
			System.out.println("ids: " + ids);
		}
        return result;
    }

}
