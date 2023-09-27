package leetcode_test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int output = -1;
		try {
			output = longestConsecutiveBySort(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * solve this problem by sort nums.
	 * 
	 * @param nums
	 * @return
	 */
    public static int longestConsecutiveBySort(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        sort(nums, 0, nums.length - 1);
        // use Arrays.sort to sort will spend more time(35ms->43ms)
//        Arrays.sort(nums);
        int result = 1, count = 1, tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
		// System.out.println("i: " + i);
		// System.out.println("tmp: " + tmp);
		// System.out.println("nums[i]: " + nums[i]);
            if (nums[i] == tmp + 1) {
                count++;
            } else if (nums[i] == tmp) {
                continue;
            } else {
                result = Math.max(count, result);
                count = 1;
            }
            tmp = nums[i];
        }
        return Math.max(count, result);
    }

    /**
     * sort the nums by Merge sort
     * 
     * @param nums
     * @param left
     * @param right
     */
    public static void sort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(nums, left, mid);
            sort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    public static void merge(int[] nums, int left, int mid, int right) {
        int s1 = left, s2 = mid + 1, i = left;
        int[] tmp = Arrays.copyOfRange(nums, left, right + 1);
        while (s1 <= mid && s2 <= right) {
            if (tmp[s1 - left] < tmp[s2 - left]) {
                nums[i] = tmp[s1 - left];
                s1++;
            } else {
                nums[i] = tmp[s2 - left];
                s2++;
            }
            i++;
        }
        while (s1 <= mid) {
            nums[i] = tmp[s1 - left];
            i++;
            s1++;
        }
        while (s2 <= right) {
		// System.out.println("i: " + i);
            nums[i] = tmp[s2 - left];
            i++;
            s2++;
        }
    }
    
    /**
     * solve this problem by Union Find
     * 
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        final int length = nums.length;
        if (length <= 1) return length;
        
        // elementIndexMap is used to store the value and index of nums
        // it is easier to find out if there are consecutive value or handle the same value
        final Map<Integer, Integer> elementIndexMap = new HashMap<>();
        final UnionFind uf = new UnionFind(length);
        for (int p = 0; p < length; p++) {
            final int i = nums[p];
            if (elementIndexMap.containsKey(i)) continue;
            if (elementIndexMap.containsKey(i+1)) uf.union(p, elementIndexMap.get(i+1));
            if (elementIndexMap.containsKey(i-1)) uf.union(p, elementIndexMap.get(i-1));
            elementIndexMap.put(i, p);
        }
        return uf.getHighestRank();
    }
    
    private static class UnionFind {
    	// sequenceTree[i] is linked to the highest rank index which is consecutive to nums[i]
        final private int[] sequenceTree;
        // rank[i] is the longest rank for num[0]->num[i]
        final private int[] rank;
        // it is used to store the highest rank so that it is easy to get result
        private int highestRank;
        
        UnionFind(int length) {
            sequenceTree = new int[length];
            rank = new int[length];
            highestRank = 1;
            for (int i = 0; i < length; i++) {
                sequenceTree[i] = i;
                rank[i] = 1;
            }
        }
        
        /**
         * p and q is the index of 2 consecutive items
         * 
         * @param p
         * @param q
         */
        void union(int p, int q) {
            final int pId = find(p); final int qId = find(q);
            
            if (pId == qId) return;
            
            int localHighestRank = 1;
            // link the lower rank index to the higher one, and calculate the highest result of nums[q] and nums[p]
            if (rank[pId] < rank[qId]) {
                sequenceTree[pId] = qId;
                rank[qId] += rank[pId];
                localHighestRank = rank[qId];
            } else {
                sequenceTree[qId] = pId;
                rank[pId] += rank[qId];
                localHighestRank = rank[pId];
            }
            highestRank = Math.max(highestRank, localHighestRank);
        }
        
        /**
         * find the highest rank index which is consecutive to nums[p]
         * 
         * @param p
         * @return
         */
        int find(int p) {
        	// while it is not the highest rank index, change it to the higher rank index to make the next time to find the result easier
        	// and also move to the higher rank and check if it is the result
            while (p != sequenceTree[p]) {
                sequenceTree[p] = sequenceTree[sequenceTree[p]];
                p = sequenceTree[p];
            }
            return p;
        }
        
        int getHighestRank() { return highestRank; }
    }
}
