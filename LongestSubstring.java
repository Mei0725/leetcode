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
        
        // �ж��Ƿ����ظ�
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
        
        // ���ظ����޷�����ǰ����ʹ�ò�ͬ�ַ��ķָ�
        int result = 0;
        for (String repectChar : repectList) {
            String[] splitStr = s.split(repectChar, -1);
            // ���⴦�� splitStr Ϊ�գ��� s ��ֻ����һ���ַ�ʱ����
            if (splitStr.length == 0) {
                break;
            }
            // ���⴦�����ַ����������ϲ�����
            if (splitStr.length == 1) {
                result = splitStr[0].length();
                break;
            }
            for (int i = 0; i < splitStr.length - 1; i++) {
                // ��ֹ�ڲ��������ַ��ظ�
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
        // ����˼·1���������ж��Ƿ����ظ��ַ���������ȡ�õ����ַ���������ȡ�����ַ�����Ƭ�е����ֵ��
        // ����ԭ���޷����� abcdefaga ���ظ��ַ�����ַ����м�������
        // ����˼·2��������ȡ�ظ��ַ��������ظ��ַ��з֣���ȡ�зֺ�������������ֵ���账���зֽ����Ӻ��µ��ظ��ַ���
        // ����ԭ���޷����� ohcomm ����ַ���ǰ���ַ���һ�µ������
        
        
        HashMap<String, Integer> charList = new HashMap<>();
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            // ��ǰ�б��д����ظ��ַ�
            if (charList.containsKey(str)) {
                // �жϵ�ǰ�Ѵ��������Ƿ�ΪĿǰ��֪�
                int length1 = charList.size();
                result = result > length1 ? result : length1;
                
                // �������У��޳��ظ��ַ�ǰ�������Թ������ַ���
                int startNum = charList.get(str);
                // ��ʱδ֪���õĴ�����
                // �޷�ʹ�� entrySet().iterator() ����������ԭ����ֱ�� remove �ᱨ��
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
        // ��ֹ��������������
        return result > charList.size() ? result : charList.size();
    }
}
