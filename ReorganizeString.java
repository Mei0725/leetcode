package leetcode_test;

import java.util.Arrays;

public class ReorganizeString {

	/**
	 * solve by greedy.
	 * firstly, check from the start to the end, and try to add a different letter into 2 same letters;
	 * then, do the same operation from end to start.
	 * 
	 * @param s
	 * @return
	 */
    public String reorganizeString(String s) {
        int l = s.length();
        // mark if letters are used
        boolean[] used = new boolean[l];
        StringBuilder sb = new StringBuilder();
        char pre = s.charAt(0);
        sb.append(pre);
        for (int i = 1; i < l; i++) {
            if (used[i]) {
                continue;
            }
            char tmp = s.charAt(i);
            if (pre == tmp) {
            	// try to find out a different and unused letter
                for (int j = i + 1; j < l; j++) {
                    if (s.charAt(j) == tmp || used[j]) {
                        continue;
                    }
                    sb.append(s.charAt(j));
                    used[j] = true;
                    break;
                }
            }
            sb.append(tmp);
            used[i] = true;
            pre = tmp;
        }

        // to handle the case that we can find a different letter before
        s = sb.toString();
        // System.out.println("result: " + s);
        sb = new StringBuilder();
        pre = s.charAt(l - 1);
        sb.append(pre);
        Arrays.fill(used, false);
        for (int i = l - 2; i >= 0; i--) {
            if (used[i]) {
                continue;
            }

            char tmp = s.charAt(i);
            if (pre == tmp) {
                boolean exist = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (s.charAt(j) == tmp || used[j]) {
                        continue;
                    }
                    // use append(char) instead of insert(0,char) can help to save space
                    sb.append(s.charAt(j));
                    used[j] = true;
                    exist = true;
                    break;
                }
                // in this step, if there is no valid letter, there will never be a valid letter in this string
                if (!exist) {
                    return "";
                }
            }
            sb.append(tmp);
            used[i] = true;
            pre = tmp;
        }
        return sb.toString();
    }
}
