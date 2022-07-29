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
    	// ͨ�� String �����ж����ֳ��ȣ�ͨ�� % ��ȡÿһλ�������ֳ��ȽӺϵõ�����ֵ
    	// ʵ�ַ���2��ÿһ�μ����µĸ�λ��ʱ��ԭ��� *10
    	// ʵ�ַ���3��ֱ�Ӵ��� String ��ͨ���� String.valueOf ���������ȡ�ַ��������ת��Ϊ����
    	
    	// ��ʵ�ʳ����жϷ����޸�Ϊ�� strX.contains("-") �޸�Ϊ Math.abs(x) ������˴���ʱ�䣨5ms->2ms��
    	// �²������� String �ĺ���������ӷ�ʱ��
    	String strX = String.valueOf(Math.abs(x));
    	int length = strX.length() - 1;
    	
    	int temp = x;
    	int result = 0;
    	try {
        	while (length >= 0) {
        		int num = temp % 10;
        		temp = temp / 10;
        		// Math �Դ����ж��Ƿ񳬹� int �߽�ĺ������糬�����׳� ArithmeticException �쳣
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
