package leetcode_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * use a map(store the index of key) and a list(store the used key) to get LRU in O(1) time complexity
 * this solution will not spend much time(94ms), but the space complexity is very large(191.6MB)
 * 
 */
public class LRUCache {

	// cache is used to store the key and value
    Map<Integer, Integer> cache = new HashMap<>();
    // use only one list to store used key and maintain it by indexOf() and remove() will be overtime
    // List<Integer> used = new ArrayList<>();
    // used is used to store the index of key in usedList
    Map<Integer, Integer> used = new HashMap<>();
    // usedList is to store the used list, if a key is multiple used, the value of this key is -1, except the last one
    List<Integer> usedList = new ArrayList<>();
    // index is the index of the first valid item in used list 
    int index = 0;
    // max is the count of used time, it can be replaced by usedList.size()-1, but store it can save some time(156ms->94ms)
    int max = 0;
    int size = 0;

    public LRUCache(int capacity) {
        this.size = capacity;
        this.cache = new HashMap<>();
        // used = new ArrayList<>();
        this.used = new HashMap<>();
        this.usedList = new ArrayList<>();
        this.index = 0;
        this.max = 0;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            // int i = used.indexOf(key);
            // if (i != -1) {
            //     used.remove(i);
            // }
            // used.add(key);
		// System.out.println("key: " + key);
		// System.out.println("used: " + used);
		// System.out.println("usedList: " + usedList);
            usedList.set(used.get(key), -1);
            usedList.add(key);
            used.put(key, max++);
            return cache.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
		// System.out.println("key: " + key);
		// System.out.println("cache.size(): " + cache.size());
        if (cache.size() < size || cache.containsKey(key)) {
            cache.put(key, value);
            if (used.containsKey(key)) {
                usedList.set(used.get(key), -1);
            }
            usedList.add(key);
            used.put(key, max++);
            // int i = used.indexOf(key);
            // if (i != -1) {
            //     used.remove(i);
            // }
            // used.add(key);
            return;
        }

        // int lru = used.get(0);
		// System.out.println("lru: " + lru);
        // used.remove(0);
        while (usedList.get(index) < 0) {
            index++;
        }
        int lru = usedList.get(index);
        index++;
        used.remove(lru);
        usedList.add(key);
        used.put(key, max++);

        cache.remove(lru);
        cache.put(key, value);
		// System.out.println("cache: " + cache);
        // used.add(key);
    }
}

/*
// can alse create new class Node: to store the used list
class Node {
	Node prev;
	Node next;
	int key;
	int val;

	public Node(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

public class LRUCache {
	Map<Integer, Node> map = new HashMap<>();
	// head is linked to the recently used key and tail is linked to the lastly used one
	Node head = new Node(-1, -1);
	Node tail = new Node(-1, -1);
	// capacity is the max size of this cache
	int capacity;

	public LRUCache(int capacity) {
		join(head, tail);
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		Node node = map.get(key);
		remove(node);
		moveToHead(node);
		return node.val;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
		// update
			Node node = map.get(key);
			node.val = value;
			remove(node);
			moveToHead(node);
		} else {
		// add new key
			if (map.size() == capacity) {
			// remove the lastly used key
				if (tail.prev != head) {
					map.remove(tail.prev.key);
					remove(tail.prev);
				}
			}
			Node node = new Node(key, value);
			moveToHead(node);
			map.put(key, node);
		}
	}

	public void join(Node n1, Node n2) {
		n1.next = n2;
		n2.prev = n1;
	}

	public void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public void moveToHead(Node node) {
		Node next = head.next;
		join(head, node);
		join(node, next);
	}
}
*/
