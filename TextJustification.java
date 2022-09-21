package leetcode_test;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public static void main(String[] args) {
//		String[] input = {"This", "is", "an", "example", "of", "text", "justification."};
//		int maxWidth = 16;
//		String[] input = {"This", "is", "an", "of", "text"};
//		int maxWidth = 6;
		String[] input = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
		int maxWidth = 16;
		List<String> output = null;
		try {
			output = fullJustify(input, maxWidth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        
        // index is the current location of words[]
        // wordLength is the total length of words in a line
        // word is the number of words in a line
        int index = 0, wordLength, word;
        while(index < words.length) {
        	wordLength = 0;
        	word = 0;
        	// find out the words that can be added into this line
        	while (index < words.length && 
        			wordLength + words[index].length() + word <= maxWidth) {
        		wordLength += words[index].length();
        		word++;
        		index++;
        	}

        	// build the line
        	String line = "";
        	// handle the case that there is only 1 word in a line or this is the last line
        	if (index == words.length || word < 2) {
        		// must pay attention to deal with the case like "a b" is the last line
        		line = line + words[index - word];
            	for (int i = index - word + 1; i < index; i++) {
        			line = line + " ";
            		line = line + words[i];
            	}
            	while (line.length() < maxWidth) {
            		line = line + " ";
            	}
        	} else {
        		// handle the general case
//        		System.out.println("words[i]: " + words[index]);
            	int space = maxWidth - wordLength;
            	int interval = space / (word - 1), remainder = space % (word - 1);
            	for (int i = index - word; i < index; i++) {
            		line = line + words[i];
            		if (i != index - 1) {
                		int tmp = interval;
                		while (tmp > 0) {
                			line = line + " ";
                			tmp--;
                		}
                		if (remainder > 0) {
                			line = line + " ";
                			remainder--;
                		}
            		}
            	}
        	}
        	result.add(line);
        }
        return result;
    }
}
