package leetcode_test;

public class EditDistance {

	public static void main(String[] args) {
//		String word1 = "horse";
//		String word2 = "ros";
//		String word1 = "intention";
//		String word2 = "execution";
		String word1 = "mart";
		String word2 = "karma";
		int output = 0;
		try {
			output = minDistance(word1, word2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by DP
	 * create a int[][], which means the result of word1.sub(i->end) and word2.sub(j->sub)
	 * for every item int[i][j], if word1.i != word2.j, its result should be int[i + 1][j + 1]+1
	 * if word1.i == word2.j, then results should be int[i + 1][j + 1], and the items in the i row and j column can be calculated by this value if it is not null
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
    public static int minDistance(String word1, String word2) {
    	int length1 = word1.length(), length2 = word2.length();
    	int[][] results = new int[length1 + 1][length2 + 1];
    	results[length1][length2] = 0;
    	int tmp = 0;
    	for (int i = length1 - 1; i >= 0; i--) {
    		results[i][length2] = ++tmp;
    	}
    	tmp = 0;
    	for (int j = length2 - 1; j >= 0; j--) {
    		results[length1][j] = ++tmp;
    	}
    	
    	for (int i = length1 - 1; i >= 0; i--) {
    		for (int j = length2 - 1; j >= 0; j--) {
    			if (word1.charAt(i) == word2.charAt(j)) {
    				results[i][j] = results[i + 1][j + 1];
    				// column
    				for (int tmpi = i - 1; tmpi >= 0; tmpi--) {
    					if (results[tmpi][j] != 0) {
    						results[tmpi][j] = Math.min(results[tmpi][j], results[tmpi + 1][j] + 1);
//    						results[tmpi][j] = Math.min(results[tmpi][j], results[tmpi + 1][j + 1] + 1);
    					} else {
//    						results[tmpi][j] = Math.min(results[tmpi + 1][j + 1], results[tmpi + 1][j]) + 1;
    						results[tmpi][j] = results[tmpi + 1][j] + 1;
    					}
    				}
    				// row
    				for (int tmpj = j - 1; tmpj >= 0; tmpj--) {
    					if (results[i][tmpj] != 0) {
    						results[i][tmpj] = Math.min(results[i][tmpj], results[i][tmpj + 1] + 1);
//    						results[i][tmpj] = Math.min(results[i][tmpj], results[i + 1][tmpj + 1] + 1);
    					} else {
//    						results[i][tmpj] = Math.min(results[i][tmpj + 1], results[i + 1][tmpj + 1]) + 1;
    						results[i][tmpj] = results[i][tmpj + 1] + 1;
    					}
    				}
    				// can not simply break at this place, 
    				// because the last items in row and column may also have same chat, 
    				// which should be handled in this if-cycle
//    				break;
    			} else if (results[i][j] != 0) {
    				results[i][j] = Math.min(results[i][j], results[i + 1][j + 1] + 1);
    			} else {
    				results[i][j] = results[i + 1][j + 1] + 1;
    			}
    		}
    	}
    	
    	return results[0][0];
    }

}
