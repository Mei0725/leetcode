package leetcode_test;

import java.util.Stack;

public class SimplifyPath {

	public static void main(String[] args) {
//		String input = "/home/";
//		String input = "/../";
		String input = "/./";
		String output = "";
		try {
			output = simplifyPath(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

    public static String simplifyPath(String path) {
        String[] points = path.split("/");
        Stack<String> simplifyPath = new Stack<>();
        for (String point : points) {
//    		System.out.println("point: " + point);
        	if (point.isBlank() || point.equals(".")) {
        		continue;
        	} else if (point.equals("..")) {
        		if (!simplifyPath.isEmpty()) {
        			simplifyPath.pop();
        		}
        	} else {
        		simplifyPath.add(point);
        	}
        }
        
        if (simplifyPath.isEmpty()) {
        	return "/";
        }
        
        String result = "";
        while (!simplifyPath.isEmpty()) {
        	result = "/" + simplifyPath.pop() + result;
        }
        return result;
    }
}
