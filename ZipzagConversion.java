package leetcode_test;

public class ZipzagConversion {

	public static void main(String[] args) {
//		String input = "PAYPALISHIRING";
//		int rowNum = 3;
		String input = "PAYPALISHIRING";
		int rowNum = 4;
//		String input = "A";
//		int rowNum = 1;
		String output = "";
		try {
			output = convert(input, rowNum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static String convert(String s, int numRows) {
    	// 最简单办法：直接构建二维数组，按序获取结果数组
    	// 时间/空间复杂度都很高
    	/*int loopSize = numRows - 1;
    	if (loopSize == 0) {
    		return s;
    	}
    	
    	int numLists = ((int)Math.ceil((double)(s.length() / (loopSize * 2))) + 1) * loopSize;
		System.out.println("sSize: " + s.length());
		System.out.println("numLists: " + numLists);
    	char[][] map = new char[numLists][numRows];
    	for (int i = 0; i < s.length(); i++) {
    		int xPoint, yPoint;
    		int x = i / loopSize;
    		int y = i % loopSize;
    		if (x % 2 == 0) {
    			xPoint = (x / 2) * loopSize;
    			yPoint = y;
    		} else {
    			xPoint = (x / 2) * loopSize + y;
    			yPoint = numRows - 1 - y;
    		}
    		map[xPoint][yPoint] = s.charAt(i);
    	}

    	String result = "";
    	for (int i = 0; i < numRows; i++) {
    		for (int j = 0; j < numLists; j++) {
    			if (map[j][i] != '\0') {
    				result = result + map[j][i];
    			}
    		}
    	}
        return result;*/
    	// 直接计算 index 获取数据，一行行获取
    	// 每一行的数据计算方法为 j * loopSize * 2 + i【数列字符】 和 (j + 1) * loopSize * 2 - i【斜列字符】
    	// 需特殊处理 第一/最后一行 防止被使用两次
    	// 另一种实现：遍历 string ，计算每个字符属于哪一行来构建 ArrayList<String>，最后将每一行相加
    	int loopSize = numRows - 1;
    	// 特殊处理只有一行的情况，无需重排
    	if (loopSize == 0) {
    		return s;
    	}
    	int listIndex = (int)Math.ceil((double)(s.length() / (loopSize * 2))) + 1;
//		System.out.println("listIndex: " + listIndex);
    	
    	String result = "";
    	for (int i = 0; i < numRows; i++) {
    		for (int j = 0; j < listIndex; j++) {
//				System.out.println("i: " + i + ", j: " + j);
    			int firstIndex = j * loopSize * 2 + i;
    			int endIndex = (j + 1) * loopSize * 2 - i;
    			// 获取数列字符，需判断理应为指定字符的位置是否真的有值（即判断string是否足够长）
    			if (firstIndex < s.length()) {
//    				System.out.println("firstIndex: " + s.charAt(firstIndex));
    				result = result + s.charAt(firstIndex);
    			}
    			// 获取斜列字符，需判断理应为指定字符的位置是否真的有值（即判断string是否足够长）
    			// 由于 第一/最后一行 字符会被数列/斜列同时获取到，所以只需要取一次
    			if (i != 0 && i != numRows - 1) {
        			if (endIndex < s.length()) {
//        				System.out.println("endIndex: " + s.charAt(endIndex));
        				result = result + s.charAt(endIndex);
        			}
    			}
    		}
    	}
    	return result;
    }

}
