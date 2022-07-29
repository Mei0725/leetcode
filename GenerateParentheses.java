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
	 * 耗时非常大的做法。
	 * 当仅有两个元素时为()，否则在 n-1 个元素的全部结果上尝试在每个结果的每个字符间空隙加上()，
	 * 并使用 Set 排除重复字符串。
	 * @param n
	 * @return
	 */
	// 另一个相似的思路：将 n 个()分解为 x+y+1 ，那么 n 则为 ( + left + ) + right 的全部结果
	// 其中 left 为 x 个元素的全部结果，right 为 y 个元素的全部结果，x 和 y 均可为 0
	// 由于  ( + left + ) + right 这个式子不对称，不会出现重复的结果
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
     * 耗时非常短，可能是由于使用了 char[] ?
     * 利用：numberOfOpened 与 numberOfClosed 值都为 n ；在任意位置前 ( 数量必定大于等于 ) ；以上两点构建函数
     * 做法为：当 () 符号两数量相同时，写下 (;
     *        由于先写下的为 (，可判断 (数量> )数量，当(耗尽即数量为n时，后续只需添加)
     *        在()都有富裕的情况下，先在当前位置写入(，继续调用；后覆盖写入)，继续调用
     *        当位置处于2n，即(数量+ )数量=2n时，储存结果
     * 
     * @param listOfPairs 储存得到的结果 String
     * @param currentTxt 储存当前正在拼凑的半成品 String
     * @param index 当前的 char 位置
     * @param numberOfOpened ( 符号的数量
     * @param numberOfClosed ) 符号的数量
     * @param n (/)符号的最大数量
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
