package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class LongestSubstring {

	public static void main(String[] args) {
		int output = 0;
		try {
			output = lengthOfLongestSubstring(String.valueOf("abcabcbb"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(output);
	}
	
    public static int lengthOfLongestSubstring(String s) {
        /*HashSet<String> appeared = new HashSet<>();
        HashSet<String> repectList = new HashSet<>();
        
        // 判断是否有重复
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (appeared.contains(c)) {
                repectList.add(c);
            } else {
                appeared.add(c);
            }
        }
        if (repectList.size() < 1) {
            return s.length();
        }
        
        // 有重复，无法处理前后是使用不同字符的分割
        int result = 0;
        for (String repectChar : repectList) {
            String[] splitStr = s.split(repectChar, -1);
            // 特殊处理 splitStr 为空，当 s 中只存在一个字符时出现
            if (splitStr.length == 0) {
                break;
            }
            // 特殊处理单个字符串，理论上不存在
            if (splitStr.length == 1) {
                result = splitStr[0].length();
                break;
            }
            for (int i = 0; i < splitStr.length - 1; i++) {
                // 防止内部有其他字符重复
                String temp = splitStr[i] + splitStr[i + 1];
                appeared = new HashSet<>();
                boolean cancle = false;
                for (int j = 0; j < temp.length(); j++) {
                    String c = String.valueOf(temp.charAt(j));
                    if (appeared.contains(c)) {
                        cancle = true;
                        break;
                    }
                    appeared.add(c);
                }
                if (!cancle && (result < splitStr[i].length() + splitStr[i + 1].length())) {
                    result = splitStr[i].length() + splitStr[i + 1].length();
                }
            }
        }
        return result + 1;*/
        // 错误思路1：遍历，判断是否有重复字符，有则切取得到新字符串，最终取所有字符串切片中的最大值；
        // 错误原因：无法处理 abcdefaga 等重复字符在最长字符串中间的情况。
        // 错误思路2：遍历获取重复字符，根据重复字符切分，获取切分后结果两两相加最大值，需处理切分结果相加后导致的重复字符。
        // 错误原因：无法处理 ohcomm 等最长字符串前后字符不一致的情况。
        
        
        HashMap<String, Integer> charList = new HashMap<>();
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            // 当前列表中存在重复字符
            if (charList.containsKey(str)) {
                // 判断当前已存在数列是否为目前已知最长
                int length1 = charList.size();
                result = result > length1 ? result : length1;
                
                // 处理数列，剔除重复字符前的内容以构建新字符串
                int startNum = charList.get(str);
                // 暂时未知更好的处理方法
                // 无法使用 entrySet().iterator() 迭代器，在原数组直接 remove 会报错
                HashMap<String, Integer> tempMap = new HashMap<>();
    	        for (String temp : charList.keySet()) {
            		if (charList.get(temp) > startNum) {
            			tempMap.put(temp, charList.get(temp));
                    }
                }
    	        charList = tempMap;
            }
            charList.put(str, i);
        }
        // 防止最长数组在最后的情况
        return result > charList.size() ? result : charList.size();
    }
}
