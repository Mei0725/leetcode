package leetcode_test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	
	 public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }

	public static void main(String[] args) {
		ListNode[] input = new ListNode[2];
//		input[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
//		input[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
		input[0] = new ListNode(2);
		input[1] = new ListNode(1);
		ListNode output = null;
		try {
			output = mergeKListsByForce(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (output != null) {
			System.out.print(output.val + ",");
			output = output.next;
		}
		//System.out.println("result: " + output);
	}
	
    public static ListNode mergeKLists(ListNode[] lists) {
    	ListNode head = null;
    	ListNode tmp = head;
		int listHasItem = lists.length;
		while (listHasItem > 0) {
			int minIndex = -1;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i] != null) {
					if (minIndex == -1 || lists[minIndex].val > lists[i].val) {
						minIndex = i;
					}
				}
			}
			if (minIndex == -1) {
				return null;
			}
			
			if(head == null) {
    			head = lists[minIndex];
    			tmp = head;
    		} else {
    			tmp.next = lists[minIndex];
    			tmp = lists[minIndex];
    		}
			lists[minIndex] = lists[minIndex].next;
			if (lists[minIndex] == null) {
				listHasItem--;
			}
		}
    	return head;
    }

    public static ListNode mergeKListsByCreate(ListNode[] lists) {
    	int[] nums = new int[lists.length * 500];
    	int totalLength = 0;
    	for (int i = 0; i < lists.length; i++) {
    		while(lists[i] != null) {
    			totalLength++;
    			nums[totalLength - 1] = lists[i].val;
    			lists[i] = lists[i].next;
    		}
    	}
    	if (totalLength < 0) {
    		return null;
    	}
    	int[] allNums = Arrays.copyOfRange(nums, 0, totalLength);
    	Arrays.sort(allNums);
    	ListNode head = new ListNode(allNums[0]);
    	ListNode tmp = head;
    	for (int i = 1; i < totalLength; i++) {
    		ListNode nextNode = new ListNode(allNums[i]);
    		tmp.next = nextNode;
    		tmp = nextNode;
    	}
    	return head;
    }
    
    public static ListNode mergeKListsByForce(ListNode[] lists) {
    	ListNode head = null;
    	ListNode tmp = head;
    	for (int i = 0; i < lists.length; i++) {
    		tmp = head;
    		while (lists[i] != null) {
    			if (head == null) {
    				head = lists[i];
        			lists[i] = lists[i].next;
        			head.next = null;
    				tmp = head;
    			} else {
    				while(tmp.next != null && tmp.next.val < lists[i].val) {
    					tmp = tmp.next;
    				}
    				if (tmp == head && tmp.val > lists[i].val) {
						ListNode local = lists[i];
		    			lists[i] = lists[i].next;
		    			local.next = tmp;
		    			tmp = local;
		    			head = tmp;
    				} else if (tmp.next == null && tmp.val < lists[i].val) {
						tmp.next = lists[i];
		    			break;
    				} else {
						ListNode localT = tmp.next;
						ListNode localL = lists[i];
		    			lists[i] = lists[i].next;
						tmp.next = localL;
						localL.next = localT;
						tmp = localL;
    				}
    			}
    		}
    	}
    	return head;
    }

    /**
     * solve the problem by PriorityQueue
     * Override the function compare of PriorityQueue so that it will poll the smallest node of all queue
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val < o2.val)
                    return -1;
                else if(o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });
        
        ListNode head = new ListNode(0);
        ListNode point = head;
        
        for(ListNode node : lists) {
            if(node != null)
                queue.offer(node);
        }
        
        while(!queue.isEmpty()) {
            point.next = queue.poll();
            point = point.next;
            
            if(point.next != null)
                queue.offer(point.next);
        }
        return head.next;
    }
    
    /**
     * solve this problem by divide conquer
     * divide lists into 2 parts and if there are more than 2 lists in every part, continue dividing
     * compare to mergeKListsByForce, this way would reduce the times to traverse lists(n^2->n*log(n))
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKListsByDivideConquer(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        return reduce(map(lists, 0, lists.length / 2), map(lists, lists.length / 2 + 1, lists.length - 1));
    }
    private ListNode map(ListNode[] lists, int from, int to){
        if(from > to){
            return null;
        }
        if(from == to){
            return lists[from];
        }
        if(from + 1 == to){
            return reduce(lists[from], lists[to]);
        }
        return reduce(map(lists, from, (from + to) / 2), map(lists, (from + to) / 2 + 1, to));
    }
    
    private ListNode reduce(ListNode first, ListNode second){
        if(first == null){
            return second;
        }
        if(second == null){
            return first;
        }
        ListNode head, pre;
        if(first.val < second.val){
            head = first;
            pre = first;
            first = first.next;
        }else{
            head = second;
            pre = second;
            second = second.next;
        }
        while(true){
            if(first == null){
                pre.next = second;
                break;
            }
            if(second == null){
                pre.next = first;
                break;
            }
            if(first.val < second.val){
                pre.next = first;
                pre = pre.next;
                first = first.next;
            }else{
                pre.next = second;
                pre = pre.next;
                second = second.next;
            }
        }
        return head;
    }
}
