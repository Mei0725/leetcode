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
    	// ��򵥰취��ֱ�ӹ�����ά���飬�����ȡ�������
    	// ʱ��/�ռ临�Ӷȶ��ܸ�
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
    	// ֱ�Ӽ��� index ��ȡ���ݣ�һ���л�ȡ
    	// ÿһ�е����ݼ��㷽��Ϊ j * loopSize * 2 + i�������ַ��� �� (j + 1) * loopSize * 2 - i��б���ַ���
    	// �����⴦�� ��һ/���һ�� ��ֹ��ʹ������
    	// ��һ��ʵ�֣����� string ������ÿ���ַ�������һ�������� ArrayList<String>�����ÿһ�����
    	int loopSize = numRows - 1;
    	// ���⴦��ֻ��һ�е��������������
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
    			// ��ȡ�����ַ������ж���ӦΪָ���ַ���λ���Ƿ������ֵ�����ж�string�Ƿ��㹻����
    			if (firstIndex < s.length()) {
//    				System.out.println("firstIndex: " + s.charAt(firstIndex));
    				result = result + s.charAt(firstIndex);
    			}
    			// ��ȡб���ַ������ж���ӦΪָ���ַ���λ���Ƿ������ֵ�����ж�string�Ƿ��㹻����
    			// ���� ��һ/���һ�� �ַ��ᱻ����/б��ͬʱ��ȡ��������ֻ��Ҫȡһ��
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
