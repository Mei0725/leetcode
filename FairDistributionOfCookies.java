package leetcode_test;

public class FairDistributionOfCookies {

	/**
	 * solve this problem by force.
	 * 
	 * calculate the result of all cases, 
	 * it means, calculate the cases that the cookies are distributed to everyone.
	 * then store the min one of every case's max value.
	 * 
	 * @param cookies
	 * @param k
	 * @return
	 */
    public int distributeCookies(int[] cookies, int k) {
        int[] count = new int[k];
        return distributeCookies(cookies, count, 0);
    }

    /**
     * try to distribute cookies[index] to everyone
     * 
     * @param cookies
     * @param count
     * @param index
     * @return
     */
    public int distributeCookies(int[] cookies, int[] count, int index) {
    	// all cookies are distributed, then get the max number of cookies that belongs to one person
        if (index == cookies.length) {
            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < count.length; i++) {
                ans = Math.max(ans, count[i]);
            }
            return ans;
        }

        // try to distribute cookies[index] to every person,
        // then get the min result of all of these cases
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < count.length; i++) {
            count[i] += cookies[index];
            ans = Math.min(ans, distributeCookies(cookies, count, index + 1));
            count[i] -= cookies[index];
        }
        return ans;
    }
    
    /**
     * it is a optimization of distributeCookies.
     * when there is a person without any cookies, then this case can't be result.
     * this kind of case can be stopped in advance.
     * 
     * add a check like it can help to save lots of time(426ms->24ms)
     * 
     * @param cookies
     * @param k
     * @return
     */
    public int distributeCookiesAdvanced(int[] cookies, int k) {
        int[] distribute = new int[k];
        
        return dfs(0, distribute, cookies, k, k);
    }
    
    /**
     * it is similar to distributeCookies,
     * but this function use zeroCount to store the number of people who do not have any cookies,
     * so it can find the case that there is no enough cookies to distribute to everyone.
     * 
     * @param i
     * @param distribute
     * @param cookies
     * @param k
     * @param zeroCount
     * @return
     */
    private int dfs(int i, int[] distribute, int[] cookies, int k, int zeroCount) {
        // If there are not enough cookies remaining, return Integer.MAX_VALUE
        // as it leads to an invalid distribution.
        if (cookies.length - i < zeroCount) {
            return Integer.MAX_VALUE;   
        }

        // After distributing all cookies, return the unfairness of this
        // distribution.
        if (i == cookies.length) {
            int unfairness = Integer.MIN_VALUE;
            for (int value : distribute) {
                unfairness = Math.max(unfairness, value);
            }
            return unfairness;
        }
        
        // Try to distribute the i-th cookie to each child, and update answer
        // as the minimum unfairness in these distributions.
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < k; ++j) {
            zeroCount -= distribute[j] == 0 ? 1 : 0;
            distribute[j] += cookies[i];
            
            // Recursively distribute the next cookie.
            answer = Math.min(answer, dfs(i + 1, distribute, cookies, k, zeroCount));
            
            distribute[j] -= cookies[i];
            zeroCount += distribute[j] == 0 ? 1 : 0;
        }
        
        return answer;
    }
}
