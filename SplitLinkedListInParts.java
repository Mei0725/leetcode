package leetcode_test;

public class SplitLinkedListInParts {

	/**
	 * calculate the length of every item in ListNode, and then cut the input into correct size.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode tmp = head;
        // calculate the length of input
        int length = 0;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }

        // calculate the average size
        int part = length / k;
        // there are change-items left, they should be put in the first change items in List
        int change = length - part * k;
        ListNode[] res = new ListNode[k];
        tmp = head;
        for (int i = 0; i < k; i++) {
            res[i] = tmp;
            // check if a left item should be put into it
            // since there is a item(tmp), the count should minus 1
            int count = i < change ? part:part - 1;
            while (count > 0) {
                count--;
                tmp = tmp.next;
            }
            
            // store the next node and set the end as null 
            if (tmp != null) {
                ListNode nextNode = tmp.next;
                tmp.next = null;
                tmp = nextNode;
            }
        }
        return res;
    }
}
