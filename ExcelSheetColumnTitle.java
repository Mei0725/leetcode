package leetcode_test;

public class ExcelSheetColumnTitle {

	/**
	 * this question is similar to transform decimal to Twenty hexadecimal.
	 * 
	 * @param columnNumber
	 * @return
	 */
    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            int tmp = columnNumber % 26;
            if (tmp == 0) {
            	// Z should be handled specially
                res.insert(0, 'Z');
                columnNumber = columnNumber / 26 - 1;
            } else {
                res.insert(0, (char)('A' + tmp - 1));
                columnNumber = columnNumber / 26;
            }
        }
        return res.toString();
    }
}
