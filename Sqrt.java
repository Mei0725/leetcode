package leetcode_test;

public class Sqrt {

	public static void main(String[] args) {
//		int input = 8;
		int input = 2147483647;
		int output = -1;
		try {
			output = mySqrt(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve the problem by binary search
	 * 
	 * @param x
	 * @return
	 */
    public static int mySqrt(int x) {
    	// j = (int)Math.sqrt(Integer.MAX_VALUE)
        int i = 0, j = 46340;
        // generally, we will take the min value i as result
        // so deal with the case that the max value j should be the result firstly
        if (x >= j * j) {
        	return j;
        }
        
        // binary search
        while (j - i > 1) {
//    		System.out.println("i: " + i);
//    		System.out.println("j: " + j);
        	int tmp = (i + j) / 2;
        	int pow = tmp * tmp;
//    		System.out.println("tmp: " + tmp);
        	if (x > pow) {
        		i = tmp;
        	} else if (x < pow) {
        		j = tmp;
        	} else {
        		return tmp;
        	}
        }
        return i;
    }
}
