package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule2 {

	/**
	 * solve by topological sort
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
    public int[] findOrderByTopologicalSort(int numCourses, int[][] prerequisites) {
    	// store the courses have prerequisites
        Set<Integer> as = new HashSet<>();
        // store the relationship between b-key to as-value
        Map<Integer, Set<Integer>> courses = new HashMap<>();
        // store the number of unfinished previous courses
        Map<Integer, Integer> aTob = new HashMap<>();
        // init as, courses and aTob
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0], b = prerequisites[i][1];
            as.add(a);
            Set<Integer> tmp = courses.getOrDefault(b, new HashSet<>());
            tmp.add(a);
            courses.put(b, tmp);
            aTob.put(a, aTob.getOrDefault(a, 0) + 1);
        }
        // store courses can be token immediately
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (as.contains(i)) continue;
            queue.add(i);
        }
        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int b = queue.poll();
            result[index++] = b;
            if (courses.containsKey(b)) {
                for (Integer a : courses.get(b)) {
                    int num = aTob.get(a);
                    if (num == 1) {
                        queue.add(a);
                    }
                    aTob.put(a, num - 1);
                }
            }
        }
        // System.out.println("index: " + index);
        // return result;
        // check if there is any unfinished course
        return index == numCourses ? result : new int[0];
    }
}
