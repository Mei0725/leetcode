package leetcode_test;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class GetSkyline {

	public static void main(String[] args) {
		int[][] input = {{0,2,3},{2,5,3}};
		List<List<Integer>> output = null;
		try {
			output = getSkyline(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result: " + output);
	}

	/**
	 * use 2 PriorityQueue to solve this problem.
	 * the PriorityQueue positions is used to get the next building, 
	 * and the PriorityQueue heights is used to get the highest building
	 * 
	 * this solution can also work when check index one by one, but it will be overtime when buildings are wide enough
	 * 
	 * @param buildings
	 * @return
	 */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> positions = new PriorityQueue<int[]>(buildings.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] < o2[0])
                    return -1;
                else if(o1[0] == o2[0])
                    return 0;
                else
                    return 1;
            }
        });
        PriorityQueue<int[]> heights = new PriorityQueue<int[]>(buildings.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] > o2[2])
                    return -1;
                else if(o1[2] == o2[2])
                    return 0;
                else
                    return 1;
            }
        });
        for (int[] building : buildings) {
            positions.offer(building);
        }
        
        //nextBuilding is the next building behind the index, currBuiliding is the highest building in this index
        int[] nextBuilding = positions.poll(), currBuilding = null;
        // index is the current index, max is used to store the previous height value
        int index = 0, max = 0;
        // if do not check currBuilding is null, it can also be handled after this loop :
        // if max is not null(handle the case that all buildings have the same end), add the index into result
        while (!positions.isEmpty() || !heights.isEmpty() || nextBuilding != null || currBuilding != null) {
            // System.out.println("index: " + index);
        	// add all buildings whose start before the index into height
            while (nextBuilding != null && nextBuilding[0] <= index) {
                heights.offer(nextBuilding);
                nextBuilding = positions.poll();
            }
            // get the highest building in this index and remove all invalid buildings
            // since the end of building will not show in the result, the currBuilding[1] == index should also be removed
            currBuilding = heights.poll();
            while (currBuilding != null && (currBuilding[1] <= index || currBuilding[0] > index)) {
                currBuilding = heights.poll();
            }
            // System.out.println("currBuilding: " + (currBuilding == null ? currBuilding : currBuilding[2]));
            // System.out.println("nextBuilding: " + (nextBuilding == null ? nextBuilding : nextBuilding[2]));
            // handle the end and the case that there are intervals between buildings
            if (currBuilding == null && (max != 0)) {
//                System.out.println("add1: ");
                max = 0;
                result.add(Arrays.asList(index, max));
            } else if (currBuilding != null && index <= currBuilding[1] && max != currBuilding[2]) {
                max = currBuilding[2];
                // the end of building will not show in the result
                // index-1 is to show the end of higher building
                if (max > currBuilding[2]) {
                // System.out.println("add2: ");
                    result.add(Arrays.asList(index - 1, max));
                } else {
                // System.out.println("add3: ");
                    result.add(Arrays.asList(index, max));
                }
            }
            // to get the next index, the min of start of nextBuilding and the end of currBuilding
            if (nextBuilding != null && currBuilding != null) {
                if (nextBuilding[0] <= currBuilding[1]) {
                    index = nextBuilding[0];
                } else {
                    index = currBuilding[1];
                }
            } else if (nextBuilding != null) {
                index = nextBuilding[0];
            } else if (currBuilding != null) {
                index = currBuilding[1];
            } else {
            	// only go to this if at the end
                index++;
            }
            // since currBuilidng may be used again if the next highest building is narrow
            // add the currBuilding into heights if it is valid
            if (currBuilding != null && currBuilding[1] > index) {
                heights.offer(currBuilding);
            }
        }
        return result;
    }
    
    /**
     * this solution is about divide and conquer
     * compare to getSkyline, it uses less time(47ms->9ms) but more space(50.6MB->58MB)
     * 
     * @param buildings
     * @return
     */
	public List<List<Integer>> getSkylineDivideConquer(int[][] buildings) {
		if (buildings.length == 0)
			return new ArrayList<>();
		return divide(buildings, 0, buildings.length - 1);
	}

	public List<List<Integer>> divide(int[][] buildings, int i, int j) {
		// store the result of every building into tmp
		if (i == j) {
			List<List<Integer>> tmp = new ArrayList<>();
			tmp.add(Arrays.asList(buildings[i][0], buildings[i][2]));
			tmp.add(Arrays.asList(buildings[i][1], 0));
			return tmp;
		}
		List<List<Integer>> left = divide(buildings, i, i + (j - i) / 2);
		List<List<Integer>> right = divide(buildings, i + (j - i) / 2 + 1, j);
		return merge(left, right);
	}

	public List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
		List<List<Integer>> ans = new ArrayList<>();
		// l1 is the location of list1, and l2 is the location of list2
		int l1 = 0;
		int l2 = 0;
		// curh1 is the height of current local of list1
		int curh1 = 0;
		int curh2 = 0;
		// curlocation is the height of the loop's location
		int curlocation = 0;
		//  sky line is used to mark the height of last point
		int skyline = 0;
		while (l1 < left.size() && l2 < right.size()) {
			List<Integer> cur1 = left.get(l1);
			List<Integer> cur2 = right.get(l2);

			if (cur1.get(0) < cur2.get(0)) {
				curh1 = cur1.get(1);
				curlocation = cur1.get(0);
				l1++;
			} else if (cur1.get(0) > cur2.get(0)) {
				curh2 = cur2.get(1);
				curlocation = cur2.get(0);
				l2++;
			} else {
				curh1 = cur1.get(1);
				curh2 = cur2.get(1);
				curlocation = cur1.get(0);
				l1++;
				l2++;
			}
			if (skyline != Math.max(curh1, curh2)) {
				skyline = Math.max(curh1, curh2);
				ans.add(Arrays.asList(curlocation, skyline));
			}

		}
		for (int i = l1; i < left.size(); i++)
			ans.add(left.get(i));
		for (int i = l2; i < right.size(); i++)
			ans.add(right.get(i));
		return ans;
	}
}
