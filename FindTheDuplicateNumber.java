package leetcode_test;

public class FindTheDuplicateNumber {

	/**
	 * solve by two pointers.
	 * 
	 * there are multiple items, it means there are loop in this nums, then it can use slow-fast to find out the start of this loop.
	 * the explanation of slow-fast can be see at: https://leetcode.com/problems/linked-list-cycle-ii/solutions/1701128/c-java-python-slow-and-fast-image-explanation-beginner-friendly/
	 * 
	 * @param nums
	 * @return
	 */
    public int findDuplicate(int[] nums) {
    	// x is the distance between start and the start of loop
    	// y is the distance between start of loop and the palce slow and fast meet
    	// c is the distance the loop contains
    	// then there is 2(x+y)-(x+y)=N*c
        int slowP = nums[nums[0]];
        int fastP = nums[slowP];
        while (slowP != fastP) {
            slowP = nums[slowP];
            fastP = nums[nums[fastP]];
        }
//        System.out.println("0slow:" + slowP + ", fast:" + fastP);

        // when there are x from start and the start of loop
        // for fast, it moves (y+x)=y+(N*c-y)=N*c for the start of loop, so it will return to the start
        // so this case x and y will meet at start of loop
        slowP = nums[0];
        while(slowP != fastP){
//        System.out.println("slow:" + slowP + ", fast:" + fastP);
            slowP = nums[slowP];
            fastP = nums[fastP];
        }
        return fastP;
    }
    
    /**
     * solve by mark the appeared num and return the num which appears 2 times.
     * 
     * it use a boolean[] to mark the appeared items so it spend a lot of space.
     * 
     * @param nums
     * @return
     */
    public int findDuplicateByMark(int[] nums) {
        boolean[] mark = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (mark[nums[i]]) {
                return nums[i];
            }
            mark[nums[i]] = true;
        }
        return -1;
    }
}
