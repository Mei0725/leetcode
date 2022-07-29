package leetcode_test;

public class ReverseInteger {

	public static void main(String[] args) {
//		int input = -2147483646;
		int input = -123;
//		int input = 0;
		int output = 0;
		try {
			output = reverse(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
    public static int reverse(int x) {
    	// 通过 String 长度判断数字长度，通过 % 获取每一位，与数字长度接合得到最终值
    	// 实现方法2：每一次加入新的个位数时将原结果 *10
    	// 实现方法3：直接处理 String ，通过将 String.valueOf 函数倒序获取字符，最后将其转换为数字
    	
    	// 将实际长度判断方法修改为由 strX.contains("-") 修改为 Math.abs(x) 后减少了大量时间（5ms->2ms）
    	// 猜测是由于 String 的函数处理更加费时？
    	String strX = String.valueOf(Math.abs(x));
    	int length = strX.length() - 1;
    	
    	int temp = x;
    	int result = 0;
    	try {
        	while (length >= 0) {
        		int num = temp % 10;
        		temp = temp / 10;
        		// Math 自带的判断是否超过 int 边界的函数，如超出将抛出 ArithmeticException 异常
        		result = Math.addExact(result, (int) (num * (Math.pow(10, length))));
//        		System.out.println("result: " + result);
        		length--;
        	}
    	} catch (ArithmeticException e) {
//    		System.out.println("integer overflow");
    		return 0;
    	}
        return result;
    }

}
