package leetcode_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Comparator;

public class TheSkylineProblem {
	/**
	 * use heap to get the front building and highest height
	 * 
	 * @param buildings
	 * @return
	 */
    public List<List<Integer>> getSkylineByHeap(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        // store all position of buildings so that it is easy to get the front building
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
        // store the heights of current buildings so that is is easy to get the heightest building
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
        int[] nextBuilding = positions.poll(), currBuilding = null;
        // index is the current position, and max is the current highest height
        int index = 0, max = 0;
        while (!positions.isEmpty() || !heights.isEmpty() || nextBuilding != null || currBuilding != null) {
            // System.out.println("index: " + index);
        	// check if we should add the nextbuilding into current heights
            while (nextBuilding != null && nextBuilding[0] <= index) {
                heights.offer(nextBuilding);
                nextBuilding = positions.poll();
            }
            // remove all buildings in front of index
            currBuilding = heights.poll();
            while (currBuilding != null && (currBuilding[1] <= index || currBuilding[0] > index)) {
                currBuilding = heights.poll();
            }
            // System.out.println("currBuilding: " + (currBuilding == null ? currBuilding : currBuilding[2]));
            // System.out.println("nextBuilding: " + (nextBuilding == null ? nextBuilding : nextBuilding[2]));
            // handle the end
            if (currBuilding == null && (max != 0)) {
                // System.out.println("add1: ");
                max = 0;
                result.add(Arrays.asList(index, max));
            } else if (currBuilding != null && index <= currBuilding[1] && max != currBuilding[2]) {
            	// handle the update of that current building
                max = currBuilding[2];
                if (max > currBuilding[2]) {
                // System.out.println("add2: ");
                    result.add(Arrays.asList(index - 1, max));
                } else {
                // System.out.println("add3: ");
                    result.add(Arrays.asList(index, max));
                }
            }
            // find out the next position
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
                index++;
            }
            // remove all buildings in front of index
            if (currBuilding != null && currBuilding[1] > index) {
                heights.offer(currBuilding);
            }
        }
        return result;
    }
    

	/**
	 * divide input array into 2 parts and calculate every part's result
	 * and then merge these 2 parts
	 * 
	 * @param buildings  the input
	 * @param i          the left part of result
	 * @param j          the right part of result
	 * @return
	 */
	public List<List<Integer>> getSkylineByDivideAndConquer(int[][] buildings) {
		if (buildings.length == 0)
			return new ArrayList<>();
		return divide(buildings, 0, buildings.length - 1);
	}
	
	public List<List<Integer>> divide(int[][] buildings, int i, int j) {
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
		// the index of every part
		int l1 = 0;
		int l2 = 0;
		// the right edge of every part
		int curh1 = 0;
		int curh2 = 0;
		// the current index
		int curlocation = 0;
		// the current highest height
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
		// handle the rest result of every part
		for (int i = l1; i < left.size(); i++)
			ans.add(left.get(i));
		for (int i = l2; i < right.size(); i++)
			ans.add(right.get(i));
		return ans;
	}
	

	/**
	 * use TreeSet to store buildings at current x
	 * 
	 * @param buildings
	 * @return
	 */
    public List<List<Integer>> getSkylineByTreeSet(int[][] buildings) {
        // Create points from building start/end points and sort them by X coord
        List<Point> points = new ArrayList<>();
        for (int bi=0; bi<buildings.length; bi++) {
            int[] b = buildings[bi];
            points.add(new Point(b[0], b[2], bi, true));
            points.add(new Point(b[1], b[2], bi, false));
        }
        // sort all points by x
        Collections.sort(points, new PointComparator());
        
        List<List<Integer>> res = new ArrayList<>();
		
		// Keep track of current skyline height
        int currH = 0;
        
        // TreeSet contains building indices and ts.first() returns tallest building
        TreeSet<Integer> ts = new TreeSet<>(new BIComparator(buildings));
        
        for (int i=0; i<points.size(); i++) {
            Point p = points.get(i);            
            if (p.start) {
                ts.add(p.bi);
            } else {
                ts.remove(p.bi);
            }            
            
			// Make sure we process all points at the same X coord 
			// before we check whether height has changes
            if (i+1<points.size() && points.get(i+1).x == p.x) continue;                
            
            // get the highest y in current x
            int newH = ts.isEmpty() ? 0 : buildings[ts.first()][2];
            // use != to mark sure all changes will be added into res
            if (newH != currH) {
                res.add(Arrays.asList(p.x, newH));
                currH = newH;
            }
        }
        return res;
        
    }
    
    // Compare building indices by building height (i.e. highest BI comes first)
    static class BIComparator implements Comparator<Integer> {
        
        private int[][] buildings;
        
        BIComparator(int[][] buildings) {
            this.buildings = buildings;
        }
        
        // first() can get the highest building in this set
        // if the height is same, then return the front one
        public int compare(Integer bi1, Integer bi2) {
            int cmp = ((Integer)buildings[bi2][2]).compareTo(buildings[bi1][2]);
            if (cmp != 0) return cmp;
            return bi1.compareTo(bi2);
        }        
    }
    
    // Compare points based on X coordinate (i.e. smaller X comes first)
    static class PointComparator implements Comparator<Point> {
        
        public int compare(Point p1, Point p2) {
            return ((Integer)p1.x).compareTo(p2.x);
        }
        
    }
    
    static class Point {        
        int x;
        int y;
        int bi;
        boolean start;
        
        Point(int x, int y, int bi, boolean start) {
            this.x = x;
            this.y = y;
            this.bi = bi;
            this.start = start;
        }       
    }
}
