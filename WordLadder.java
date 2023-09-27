package leetcode_test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {

	/**
	 * use BFS to solve the problem.
	 * add all possible pair word into queue, and use used[i] to store the count that can arrive to wordList(i)
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int i2 = wordList.indexOf(endWord);
        if (i2 == -1) {
            return 0;
        }

        int i1 = wordList.size();
        int[] used = new int[wordList.size() + 1];
        wordList.add(beginWord);
        used[i1] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i1);
        while (!queue.isEmpty()) {
            int word = queue.poll();
            String s = wordList.get(word);
            // check all words if it is pair to s
            for (int i = 0; i < wordList.size() - 1; i++) {
                if (i == word || used[i] != 0) {
                    continue;
                }
                if (checkPair(s, wordList.get(i))) {
                    if (i == i2) {
                        return used[word] + 1;
                    } else {
                        used[i] = used[word] + 1;
                        queue.offer(i);
                    }
                }
            }
            // System.out.println("queue: " + queue);
        }
        return 0;
    }

    /**
     * check if s1 and s2 are pairs
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkPair(String s1, String s2) {
        if (s1.length() != s2.length() || s1.equals(s2)) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff >= 1) {
                    return false;
                }
                diff++;
            }
        }
        return diff == 1;
    }
    
    /**
     * a way to check to string
     * translate string to long in 26-bit, and check if there is only one different position
     * but it is not helpful to save time.
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkPair(long s1, long s2) {
        int diff = 0;
        while (s1 > 0 || s2 > 0) {
            if (s1 % 26 != s2 % 26) {
                diff++;
                if (diff >= 2) {
                    return false;
                }
            }
            s1 /= 26;
            s2 /= 26;
        }
        return diff == 1;
    }

    /**
     * a solution also based on BFS but spend less time(481ms->53ms)
     * it may because that it change wordList to Set and use it to find out pair.
     * (if wordList is still a list and use indexOf then it will become overtime)
     * 
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
	public int ladderLengthLessTime(String beginWord, String endWord, List<String> wordList) {
		Queue<String> queue = new LinkedList<>();
		HashSet<String> dict = new HashSet<>(wordList);
		if (!dict.contains(endWord)) {
			return 0;
		}
		if (beginWord.equals(endWord)) {
			return 1;
		}

		queue.add(beginWord);
		int count = 1; // 1 for beginWord
		while (queue.size() > 0) {
			int size = queue.size();
			// for all words at current level
			for (int i = 0; i < size; i++) {
				char[] word = queue.poll().toCharArray();
				// we will replace each char one by one with [a,z] and check if it exists in our dictionary
				for (int j = 0; j < word.length; j++) {
					char tmp = word[j];
					for (char c = 'a'; c <= 'z'; c++) {
						word[j] = c;
						String newWord = new String(word); // next word after replacing jth character
						if (dict.contains(newWord)) {
							if (newWord.equals(endWord)) {
								return count + 1;
							}
							queue.add(newWord);
							dict.remove(newWord);
						}
					}
					word[j] = tmp; // resetting to actual word for next iteration of inner for loop.
				}
			}
			// we have checked for all next words reachable from current queue, Hence
			count += 1;
		}
		// reaching here means we have not found endWord, yet return 0
		return 0;
	}
}
