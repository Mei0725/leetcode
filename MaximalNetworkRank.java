package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximalNetworkRank {

	/**
	 * store the connect information in a map
	 * then find out the 2 cities which connect to the most cities.
	 * if there are several most-connected cities, find out if there are 2 not connect to each other.
	 * 
	 * @param n
	 * @param roads
	 * @return
	 */
    public int maximalNetworkRank(int n, int[][] roads) {
    	// store the cities connect informations
        Map<Integer, Set<Integer>> con = new HashMap<>();
        for (int i = 0; i < roads.length; i++) {
            int c1 = roads[i][0], c2 = roads[i][1];
            Set<Integer> cities1 = con.getOrDefault(c1, new HashSet<>());
            Set<Integer> cities2 = con.getOrDefault(c2, new HashSet<>());
            cities1.add(c2);
            cities2.add(c1);
            con.put(c1, cities1);
            con.put(c2, cities2);
        }
        // System.out.println("con: " + con);

        // to find out the 2 cities which connect to more other cities
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((q1, q2) -> (q2[1] - q1[1]));
        for (Integer c : con.keySet()) {
            queue.offer(new int[]{c, con.get(c).size()});
        }
        // if there is less then 1 path, return 0;
        if (queue.size() < 2) {
            return 0;
        }

        int[] city0 = queue.poll();
        int[] city1 = queue.poll();
        // System.out.println("city0[0]: " + city0[0]);
        // System.out.println("city0[1]: " + city0[1]);
        if (!con.get(city0[0]).contains(city1[0])) {
        	// if the 2 cities do not connect to each other, it is the result
            return city0[1] + city1[1];
        }
        // otherwise, try to find out if there are 2 valid no-connected cities
        int res = city0[1] + city1[1] - 1;
        if (city0[1] != city1[1]) {
        	// handle the case that there is a city connect to most other cities, 
        	// and several cities connect to the second most cities
            while (!queue.isEmpty() && queue.peek()[1] == city1[1]) {
                int[] tmp = queue.poll();
                if (!con.get(city0[0]).contains(tmp[0])) {
                    res = city0[1] + tmp[1];
                    break;
                }
            }
        } else {
        	// handle the case that there is several cities connect to most other cities
            List<Integer> list = new ArrayList<>();
            list.add(city0[0]);
            list.add(city1[0]);
            while (!queue.isEmpty() && queue.peek()[1] == city1[1]) {
                int[] tmp = queue.poll();
                for (Integer i : list) {
                    if (!con.get(tmp[0]).contains(i)) {
                        return tmp[1] * 2;
                    }
                }
                list.add(tmp[0]);
            }
        }
        return res;
    }
}
