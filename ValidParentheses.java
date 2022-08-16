package leetcode_test;

public class ValidParentheses {

	public static void main(String[] args) {
		String input = "([])";
		boolean output = true;
		try {
			output = isValid(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static boolean isValid(String s) {
    	int[] phType = new int[s.length()];
    	int location = 0;
    	
    	boolean left = false;
    	int locateType = 0;
    	for(char ch : s.toCharArray()) {
    		switch(ch) {
    		    case '(':
    		    	left = true;
    		    	locateType = 1;
    		    	break;
    		    case ')':
    		    	locateType = 1;
    	    		left = false;
    		    	break;
    		    case '[':
    		    	left = true;
    		    	locateType = 2;
    		    	break;
    		    case ']':
    		    	locateType = 2;
    	    		left = false;
    		    	break;
    		    case '{':
    		    	left = true;
    		    	locateType = 3;
    		    	break;
    		    case '}':
    		    	locateType = 3;
    	    		left = false;
    		    	break;
    		}
    		if (left) {
    			phType[location] = locateType;
    			location++;
    		} else {
    			location--;
    			if (location < 0 || phType[location] != locateType) {
    				return false;
    			}
    		}
    	}
        return location == 0;
    }
}
