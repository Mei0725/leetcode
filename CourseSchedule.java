package leetcode_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {

	/**
	 * solve by topological sort.
	 * try to create a directed acyclic graph which includes all courses, 
	 * if there is a DAG like this, then this function can return true.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
    public boolean canFinishByTopologicalSort(int numCourses, int[][] prerequisites) {
    	// store all courses that linked to other courses, which means it can not become the start of graph
        Set<Integer> nums = new HashSet<>();
        // store the number of courses that key-a linked to, there is no need to store the specific courses
        Map<Integer, Integer> aToB = new HashMap<>();
        // store the courses linking to b-key, to make it easier to update aToB
        Map<Integer, Set<Integer>> bToA = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0], b = prerequisites[i][1];
            nums.add(a);
            aToB.put(a, aToB.getOrDefault(a, 0) + 1);
            if (bToA.containsKey(b)) {
                bToA.get(b).add(a);
            } else {
                Set<Integer> as = new HashSet<>();
                as.add(a);
                bToA.put(b, as);
            }
        }
        // if all courses linked to other courses, then there must be loop in the graph
        if (nums.size() == numCourses) {
            return false;
        }

        // store the courses that can be start point
        Queue<Integer> check = new LinkedList<>();
        // store the number of courses have been in graph
        int checked = 0;
        // init check
        for (int i = 0; i < numCourses; i++) {
            if (nums.contains(i)) continue;
            check.add(i);
        }
        while (!check.isEmpty()) {
            int course = check.poll();
            checked++;
            // update map-aToB, when all a-key's linked course are added into graph, then a-key can become the start point
            if (bToA.containsKey(course)) {
                for (int a : bToA.get(course)) {
                    int num = aToB.get(a);
                    if (num == 1) {
                        check.add(a);
                    }
                    aToB.put(a, num - 1);
                }
            }
        }
        // check if the graph contains all courses
        return checked == numCourses;
    }
    
    // to store the relationship of b->a
    Map<Integer, Set<Integer>> courses = new HashMap<>();
    /**
     * solve by depth search.
     * use map-courses to store the relationship of b->a
     * then check for every b, if there is a loop when we follow its relationship
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishByDepthSearch(int numCourses, int[][] prerequisites) {
    	// init courses
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0], b = prerequisites[i][1];
            Set<Integer> as = courses.getOrDefault(b, new HashSet<>());
            as.add(a);
            courses.put(b, as);
        }
        // System.out.println("courses: " + courses);
        // checked[i] is to store if course-i is checked if there is a loop including i
        // tmp is used to store the results in current loop
        boolean[] checked = new boolean[numCourses], tmp = new boolean[numCourses];
        for (int b : courses.keySet()) {
            if (!checkCourse(checked, tmp, b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check that if course-b is in a loop
     * 
     * @param checked  make sure b is not checked in other place
     * @param tmp      store the results in current loop
     * @param b        the current course
     * @return
     */
    public boolean checkCourse(boolean[] checked, boolean[] tmp, int b) {
    	// when b is checked or b is the end of this line
        if (checked[b] || !courses.containsKey(b)) {
            return true;
        }
        tmp[b] = true;
        for (int a : courses.get(b)) {
        	// check if a is linked to the start of b or is in the loop
            if (tmp[a] || !checkCourse(checked, tmp, a)) {
                return false;
            }
        }
        tmp[b] = false;
        checked[b] = true;
        return true;
    }
}
