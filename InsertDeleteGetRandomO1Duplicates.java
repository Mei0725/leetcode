package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandomO1Duplicates {

	Random r;
	// the value of map can also be List or PriorityQueue, but we need to find out the max value of this list 
	// otherwise there may be some value's index become out of range, which will spend more time
	HashMap<Integer, Set<Integer>> map;
	ArrayList<Integer> list;

	public InsertDeleteGetRandomO1Duplicates() {
		list = new ArrayList<>();
		map = new HashMap<>();
		r = new Random();
	}

	public boolean insert(int val) {
		list.add(val);
		if (map.containsKey(val)) {

			Set<Integer> set = map.get(val);
			set.add(list.size() - 1);

			return false;
		} else {
			Set<Integer> set = new HashSet<>();
			set.add(list.size() - 1);
			map.put(val, set);
			return true;
		}
	}

	public boolean remove(int val) {
		// use set to make sure there is no wrong in both the-last-item==val and the-last-item!=val
		if (map.containsKey(val)) {

			int vidx = map.get(val).iterator().next();
			int lidx = list.size() - 1;
			int lval = list.get(lidx);

			list.set(lidx, val);
			list.set(vidx, lval);

			list.remove(lidx);
			map.get(val).remove(vidx);

			if (map.get(val).size() == 0) {
				map.remove(val);
			}

			if (map.containsKey(lval)) {
				map.get(lval).remove(lidx);
				map.get(lval).add(vidx);
			}

			return true;
		} else {

			return false;
		}
	}
	
	// the function-remove if value of map is list
	/**
    public boolean remove(int val) {
        List<Integer> ids = maps.get(val);
        if (ids == null || ids.size() == 0) {
            return false;
        }

        int removeId = ids.get(ids.size() - 1);
        if (ids.contains(index - 1)) {
            removeId = index - 1;
        }
        int lastVal = list.get(index - 1);
        if (lastVal != val) {
            List<Integer> lastIds = maps.get(lastVal);
            // make sure the changeId is the max one in lastIds
            int changeId = lastIds.size() - 1;
            if (lastIds.contains(index - 1)) {
                changeId = lastIds.indexOf(index - 1);
            }
            lastIds.set(changeId, removeId);
            list.set(removeId, lastVal);
        }
        ids.remove(ids.size() - 1);
        // System.out.println("index: " + index);
        list.remove((int)(--index));
        // System.out.println("maps: " + maps);
        // System.out.println("list: " + list);
        return true;
    }**/

	public int getRandom() {
		int idx = r.nextInt(list.size());
		return list.get(idx);
	}
}

// identify a new class Collection to find out the max value of a List
/**class RandomizedCollection {

    Map<Integer, Collection> maps;
    List<Integer> list;
    Random rm;
    Integer index;

    public RandomizedCollection() {
        maps = new HashMap<>();
        list = new ArrayList<>();
        rm = new Random();
        index = 0;
    }
    
    public boolean insert(int val) {
        Collection ids = maps.get(val);
        if (ids != null && ids.size > 0) {
            ids.add(index++);
            list.add(val);
            return false;
        }
        ids = new Collection();
        ids.add(index++);
        maps.put(val, ids);
        list.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        // System.out.println("remove: " + val);
        Collection ids = maps.get(val);
        if (ids == null || ids.size == 0) {
            return false;
        }

        // System.out.println("index: " + (index - 1));
        if (ids.contains(index - 1)) {
            ids.remove(index - 1);
        } else {
            int removeId = ids.removeOne();
            int lastVal = list.get(index - 1);
            Collection lastIds = maps.get(lastVal);
            lastIds.replace(index - 1, removeId);
            list.set(removeId, lastVal);
        }
        list.remove((int)(--index));
        // System.out.println("index: " + index);
        // System.out.println("maps: " + maps);
        // System.out.println("list: " + list);
        return true;
    }
    
    public int getRandom() {
        return list.get(rm.nextInt(index));
    }
}

class Collection {

    List<Integer> list;
    Map<Integer, Integer> map;
    Integer size;
    
    public Collection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        size = 0;
    }

    public Boolean contains(int val) {
        return map.containsKey(val);
    }

    public void remove(int val) {
        // System.out.println("list: " + list);
        // System.out.println("map: " + map);
        int index = map.get(val);
        // System.out.println("index: " + index);
        if (index != size - 1) {
            int changeId = size - 1;
            int changeVal = list.get(changeId);
            list.set(index, changeVal);
            map.put(changeVal, index);
        }
        map.remove(val);
        list.remove((int)index);
        size--;
    }

    public Integer removeOne() {
        int index = size - 1;
        int val = list.get(index);
        map.remove(val);
        list.remove((int)index);
        size--;
        return val;
    }

    public void replace(int oldNum, int newNum) {
        int index = map.get(oldNum);
        map.remove(oldNum);
        map.put(newNum, index);
        list.set(index, newNum);
    }

    public void add(int val) {
        list.add(val);
        map.put(val, size++);
    }
}*/
