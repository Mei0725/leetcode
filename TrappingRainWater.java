package leetcode_test;

import java.util.Stack;

public class TrappingRainWater {

	public static void main(String[] args) {
//		int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};//6
//		int[] input = {4,2,0,3,2,5};//9
//		int[] input = {0,1,2,0,3,0,1,2,0,0,4,2,1,2,5,0,1,2,0,2};//26
		int[] input = {9,2,4,0,3,4};//7
		int output = -1;
		try {
			output = trapDP(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}
	
	/**
	 * the time complexity is O(n)
	 * in this solution, find out the highest one in the array, 
	 * and then in its left, the only item that influence the water in position i is i's highest item in its left
	 * in the same case, in its right, we should only consider about i's highest item in its right
	 * 
	 * @param height
	 * @return
	 */
	public static int trapON(int[] height) {
        if(height.length < 3)
            return 0;

        int Max = height[0];
        int iMax = 0, n = height.length, water=0;
        for(int i=0; i<n; i++){
            if(Max <= height[i]){
                Max = height[i];
                iMax = i;
            }
        }
        // 0 to iMax; the reservoir is bounded by leftMax & Max
        int leftMax = height[0];
        for(int i=0; i<iMax; i++)
            if(leftMax <= height[i])
                leftMax = height[i];
            else
                water += leftMax - height[i];
        
        // iMax to n-1; the reservoir is bounded by Max & rightMax
        int rightMax = height[n-1];        
        for(int i = n-1; i> iMax; i--)
            if(rightMax < height[i])
                rightMax = height[i];
            else
                water += rightMax -height[i];
        
        return water;
	}
	
	/**
	 * for any position i, the water it traps depend on min(leftMax, rightMax)
	 * so we can just find out the leftMax and rightMax of every position, and update it if necessary
	 * since time complexity of the way to find out rightMax is O(n), the total time complexity should be O(n^2)
	 * 
	 * @param height
	 * @return
	 */
	public static int trapDP(int[] height) {
		if (height.length <= 2) {
			return 0;
		}
		int result = 0;
		int leftMax = height[0];
		int rightMax = findMax(1, height.length, height);
		for(int i = 1; i < height.length; i++) {
			if (height[i] == rightMax) {
				leftMax = height[i];
				rightMax = findMax(i + 1, height.length, height);
			} else if (height[i] > leftMax) {
				leftMax = height[i];
			}
			int localWater = Math.min(leftMax, rightMax) - height[i];
			if (localWater > 0) {
				result += localWater;
			}
//			System.out.println("i: " + i);
//			System.out.println("leftMax: " + leftMax);
//			System.out.println("rightMax: " + rightMax);
//			System.out.println("height[i]: " + height[i]);
//			System.out.println("result: " + result);
		}
		return result;
	}
	
	/**
	 * it is used by trapDP() to find out the highest item for arrays nums from start[ to end)
	 * 
	 * @param start
	 * @param end
	 * @param nums
	 * @return
	 */
	public static int findMax(int start, int end, int[] nums) {
		int result = 0;
		for(int i = start; i < end; i++) {
			if (nums[i] > result) {
				result = nums[i];
			}
		}
		return result;
	}

	/**
	 * in this solution, calculate the result from 0 to i for position i
	 * the for position i, after getting its result, the trap in front of it is trapped by added water
	 * 
	 * @param height
	 * @return
	 */
    public static int trap(int[] height) {
        if (height.length <= 2) {
        	return 0;
        }
        int result = 0;
        Stack<int[]> heights = new Stack<>();
        heights.push(new int[]{0, 1, height[0]});
        for (int i = 1; i < height.length; i++) {
        	int[] endPoint = heights.peek();
        	int tmpHeight = height[i];
        	if (tmpHeight <= endPoint[2]) {
        		heights.push(new int[] {i, i + 1, tmpHeight});
        	} else {
        		while (!heights.isEmpty()) {
        			endPoint = heights.pop();
        			if (endPoint[2] > tmpHeight) {
        				break;
        			}
        			result += (tmpHeight - endPoint[2]) * (endPoint[1] - endPoint[0]);
        		}
    			if (endPoint[2] > tmpHeight) {
    				heights.push(endPoint);
        			heights.push(new int[] {endPoint[1], i + 1, height[i]});
    			} else {
    				result -= (tmpHeight - endPoint[2]) * (i - endPoint[0]);
        			heights.push(new int[]{i, i + 1, height[i]});
    			}
        	}
//    		System.out.println("i: " + i);
//    		System.out.println("heights: " + heights);
//    		System.out.println("result: " + result);
        }
        return result;
    }

}
