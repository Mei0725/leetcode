package leetcode_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateParentheses {

	public static void main(String[] args) {
		int input = 3;
//		String input = "  -42";
//		String input = "words and 987";
		List<String> output = null;
		try {
			output = generateParenthesis(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * ��ʱ�ǳ����������
	 * ����������Ԫ��ʱΪ()�������� n-1 ��Ԫ�ص�ȫ������ϳ�����ÿ�������ÿ���ַ����϶����()��
	 * ��ʹ�� Set �ų��ظ��ַ�����
	 * @param n
	 * @return
	 */
	// ��һ�����Ƶ�˼·���� n ��()�ֽ�Ϊ x+y+1 ����ô n ��Ϊ ( + left + ) + right ��ȫ�����
	// ���� left Ϊ x ��Ԫ�ص�ȫ�������right Ϊ y ��Ԫ�ص�ȫ�������x �� y ����Ϊ 0
	// ����  ( + left + ) + right ���ʽ�Ӳ��Գƣ���������ظ��Ľ��
    /*public static List<String> generateParenthesis(int n) {
    	Set<String> result = new HashSet<>();
    	if (n <= 1) {
    		result.add("()");
    	} else {
    		List<String> temp = generateParenthesis(n - 1);
    		temp.forEach(item -> {
    			result.add("()" + item);
    			for (int i = 1; i < item.length(); i++) {
    				result.add(item.substring(0, i) + "()" + item.substring(i));
    			}
    			result.add(item + "()");
    		});
    	}
    	return new ArrayList<>(result);
    }*/

    public static List<String> generateParenthesis(int n) {
        List<String> listOfPairs = new ArrayList<String>();
        char[] currentTxt = new char[n*2];
        populateList(listOfPairs, currentTxt, 0, 0, 0, n);
        return listOfPairs;
    }
    
    /**
     * ��ʱ�ǳ��̣�����������ʹ���� char[] ?
     * ���ã�numberOfOpened �� numberOfClosed ֵ��Ϊ n ��������λ��ǰ ( �����ض����ڵ��� ) ���������㹹������
     * ����Ϊ���� () ������������ͬʱ��д�� (;
     *        ������д�µ�Ϊ (�����ж� (����> )��������(�ľ�������Ϊnʱ������ֻ�����)
     *        ��()���и�ԣ������£����ڵ�ǰλ��д��(���������ã��󸲸�д��)����������
     *        ��λ�ô���2n����(����+ )����=2nʱ��������
     * 
     * @param listOfPairs ����õ��Ľ�� String
     * @param currentTxt ���浱ǰ����ƴ�յİ��Ʒ String
     * @param index ��ǰ�� char λ��
     * @param numberOfOpened ( ���ŵ�����
     * @param numberOfClosed ) ���ŵ�����
     * @param n (/)���ŵ��������
     */
    private static void populateList(List<String> listOfPairs, char[] currentTxt, 
                              int index, int numberOfOpened, int numberOfClosed,
                              int n)
    {   
        if (index == currentTxt.length)
        { 
            listOfPairs.add(String.valueOf(currentTxt));
            System.out.println("listOfPairs: " + listOfPairs);
            return;
        }
        
        if (numberOfOpened == numberOfClosed)
        {
            currentTxt[index] = '(';
            populateList(listOfPairs, currentTxt,  index + 1,
                             numberOfOpened + 1, numberOfClosed, n);
        }
        else
        {
            if (numberOfOpened == n)
            {
                currentTxt[index] = ')';
                populateList(listOfPairs, currentTxt,  
                                 index + 1, numberOfOpened, numberOfClosed + 1, n);
            }
            else
            {
                currentTxt[index] = '(';
                populateList(listOfPairs, currentTxt, 
                                index + 1, numberOfOpened + 1, numberOfClosed, n);
                currentTxt[index] = ')';
                populateList(listOfPairs, currentTxt,  
                                index + 1, numberOfOpened, numberOfClosed + 1, n);
            }
        }
    }
}
