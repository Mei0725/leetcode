package leetcode_test;

public class MedianOfTwoArray {

	public static void main(String[] args) {
//		int[] array1 = {1, 2};
//		int[] array2 = {3, 4};
//		int[] array1 = {1, 2, 3};
//		int[] array2 = {4};
//		int[] array1 = {1, 2, 4};
//		int[] array2 = {3};
//		int[] array1 = {1, 2, 3, 4, 6, 7, 8};
//		int[] array2 = {5};
		int[] array1 = {1, 2};
		int[] array2 = {3, 4, 5, 6, 7, 8};
		double output = 0;
		try {
			output = findMedianSortedArrays(array1, array2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	 public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 int length1 = nums1.length;
		 int length2 = nums2.length;
		 int[] array1, array2;
		 if (length1 < length2) {
			 length1 = nums2.length;
			 length2 = nums1.length;
			 array1 = nums2;
			 array2 = nums1;
		 } else {
			 array1 = nums1;
			 array2 = nums2;
		 }
		 
		 // ���⴦������һ���ַ���Ϊ�յ����
		 if (length2 == 0) {
			 return length1 % 2 == 0? (double)(array1[length1 / 2] + array1[length1 / 2 - 1]) / 2 : array1[length1 / 2];
		 }
		 
		 int a1Max = length1, a1Min = 0;
		 int iHalf = (length1 + length2 + 1) >> 1;
		 int i, j;
		 // ������ = ���Դ���λ�����һ���������
		 while (a1Min <= a1Max) {
			 // ��ע�ʹ�����һ��
//			 i = (int)Math.floor((a1Max + a1Min) / 2);
			 i = (a1Min+a1Max) >> 1;;
			 System.out.println("int i: " + i);
//			 j = (int)Math.ceil((double)(length1 + length2) / 2) - i;
			 j = iHalf - i;
			 System.out.println("int j: " + j);
			 // ����ͨ�����ֻ�ȡ i ֵ�����ܳ��� j ������Χ���������ʱֱ��ȡͷ/β��Ϊ���ս��
			 if (j < 0) {
				 j = 0;
				 i = iHalf - j;
			 } else if (j > length2) {
				 j = length2;
				 i = iHalf - j;
			 }
			 
			 if (i < length1 && j > 0 && array1[i] < array2[j - 1]) {
				 if (a1Min < i) {
					 a1Min = i;
				 } else {
					 // ���� length ��Сʱ a1Min �������ѭ��
					 a1Min++;
				 }
				 System.out.println("update1 min: " + a1Min);
			 } else if (i > 0 && j < length2 && array1[i - 1] > array2[j]) {
				 if (a1Max > i) {
					 a1Max = i;
				 } else {
					 a1Max--;
				 }
				 System.out.println("update2 max: " + a1Max);
			 } else {
				 // ��ȷ���ֽ�㣬��ʱ��λ���ڷֽ�㸽�� <= �ĸ�������ȡ
				 System.out.println("i: " + i + ", j: " + j);
				 // ��ȡ�������ȡ�����ֽ�����ĵ�һ���������⴦��߽�ֵ
				 int middle1;
				 if (i == length1 || j == 0) {
					 middle1 = array1[i - 1];
				 } else if (i == 0) {
					 middle1 = array2[j - 1];
				 } else {
					 middle1 = Math.max(array1[i - 1], array2[j - 1]);
				 }
				 System.out.println("middle1: " + middle1);
				 
				 // ��������Ϊ����
				 if ((length1 + length2) % 2 == 1) {
					 return middle1;
				 }
				 
				// ��ȡ�Ҳ�����ȡ�����ֽ���Ҳ�ĵ�һ���������⴦��߽�ֵ
				 int middle2;
				 if (j == length2) {
					 middle2 =array1[i];
				 } else if (i == length1) {
					 middle2 = array2[j];
				 } else {
					 middle2 = Math.min(array1[i], array2[j]);
				 }
				 System.out.println("middle2: " + middle2);
				 return (double)(middle1 + middle2) / 2;
			 }
		 }
		 // һ�㲻���ߵ��÷�֧
		 return 0;
	 }

}
