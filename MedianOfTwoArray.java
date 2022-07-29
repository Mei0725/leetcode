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
		 
		 // 特殊处理其中一个字符串为空的情况
		 if (length2 == 0) {
			 return length1 % 2 == 0? (double)(array1[length1 / 2] + array1[length1 / 2 - 1]) / 2 : array1[length1 / 2];
		 }
		 
		 int a1Max = length1, a1Min = 0;
		 int iHalf = (length1 + length2 + 1) >> 1;
		 int i, j;
		 // 必须有 = 用以处理定位到最后一个数的情况
		 while (a1Min <= a1Max) {
			 // 与注释处理结果一样
//			 i = (int)Math.floor((a1Max + a1Min) / 2);
			 i = (a1Min+a1Max) >> 1;;
			 System.out.println("int i: " + i);
//			 j = (int)Math.ceil((double)(length1 + length2) / 2) - i;
			 j = iHalf - i;
			 System.out.println("int j: " + j);
			 // 由于通过二分获取 i 值，可能出现 j 超出范围的情况，此时直接取头/尾即为最终结果
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
					 // 处理 length 过小时 a1Min 不变的死循环
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
				 // 已确定分界点，此时中位数在分界点附近 <= 四个数据中取
				 System.out.println("i: " + i + ", j: " + j);
				 // 获取左侧数，取两个分界点左侧的第一个数，特殊处理边界值
				 int middle1;
				 if (i == length1 || j == 0) {
					 middle1 = array1[i - 1];
				 } else if (i == 0) {
					 middle1 = array2[j - 1];
				 } else {
					 middle1 = Math.max(array1[i - 1], array2[j - 1]);
				 }
				 System.out.println("middle1: " + middle1);
				 
				 // 总数据量为奇数
				 if ((length1 + length2) % 2 == 1) {
					 return middle1;
				 }
				 
				// 获取右侧数，取两个分界点右侧的第一个数，特殊处理边界值
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
		 // 一般不会走到该分支
		 return 0;
	 }

}
