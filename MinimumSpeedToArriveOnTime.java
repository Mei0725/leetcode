package leetcode_test;

public class MinimumSpeedToArriveOnTime {

	/**
	 * solve by binary search.
	 * find out min and max possible speed, and than find out the min available speed.
	 * 
	 * @param dist
	 * @param hour
	 * @return
	 */
    public int minSpeedOnTime(int[] dist, double hour) {
    	// since every trains will cost at least 1 hour, so in this case it can return -1
        if (Math.ceil(hour) < dist.length) {
            return -1;
        }

        double dis = 0;
        int max = dist[0];
        for (int i = 0; i < dist.length; i++) {
            dis += dist[i];
            max = Math.max(max, dist[i]);
        }
        // actually, it should be Math.ceil, but double's / operation may cause some problem so use Math.floor
        int min = Math.max((int)Math.floor(dis/ hour), 1);
        // the max value should be the min one of 10000000 and the speed of the (max distant / min time)
        max = Math.min(10000000, max * 100);
        int minSpeed = max;
            // System.out.println("min: " + min);
            // System.out.println("max: " + max);
        // find out the result by binary search
        while (min <= max) {
            int speed = (min + max) / 2;
            // System.out.println("speed: " + speed);
            double time = timeOnSpeed(speed, dist);
            // System.out.println("time: " + time);
            if (time <= hour) {
                minSpeed = Math.min(minSpeed, speed);
                max = speed - 1;
            } else {
            	// if speed is not available but minSpeed is available, then it should be the result
                if (speed + 1 == minSpeed) {
            // System.out.println("result: " + minSpeed);
                    break;
                }
                min = speed + 1;
            }
        }
            // System.out.println("min: " + min);
            // System.out.println("max: " + max);
            // System.out.println("minSpeed: " + minSpeed);
        return minSpeed;
    }

    /**
     * get the time in the input speed.
     * 
     * @param speed
     * @param dist
     * @return
     */
    public double timeOnSpeed(int speed, int[] dist) {
        double time = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            time += (int)Math.ceil(((double)dist[i]) / speed);
        }
        // the final dist should be handled specially
        return time + ((double)dist[dist.length - 1]) / speed;
    }
}
