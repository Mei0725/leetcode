package leetcode_test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        if(points.length <= 0) return 0;
        if(points.length <= 2) return points.length;
        int result = 0;
        // for every point i, check all other points to get the max result which it contains in
        for(int i = 0; i < points.length; i++){
        	// store the value of k in y=kx+z
            Map<Double, Integer> hm = new HashMap<Double, Integer>();
            // store the points in line like x = z
            // this line can not be contained in hm
            int samex = 1;
            // store the totally same points
            int samep = 0;
            for(int j = 0; j < points.length; j++){
                if(j != i){
                    if((points[j][0] == points[i][0]) && (points[j][1] == points[i][1])){
                        samep++;
                        continue;
                    }
                    if(points[j][0] == points[i][0]){
                        samex++;
                        continue;
                    }
                    double k = (double)(points[j][1] - points[i][1]) / (double)(points[j][0] - points[i][0]);
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }

    /**
     * calculate every line's ax+by=c, then make "a+b+c" as key and store it into map
     * since DecimalFormat may spend lots of time, this solution can take much long time than maxPoints
     * 
     * @param points
     * @return
     */
    public int maxPoints0(int[][] points) {
        int length = points.length;
        // the union key value must store at lest 8-numbers after point
        DecimalFormat df = new DecimalFormat("0.00000000");
        // use set as value to avoid that a point is added more than one time
        Map<String, Set<Integer>> lines = new HashMap<>();
        int max = 1;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                double x = 1;
                double y = 1;
                double z = 0;
                if (points[j][0] - points[i][0] != 0) {
                    x = (points[i][1] - points[j][1]) / (double)(points[j][0] - points[i][0]);
                    z = points[i][1] + points[i][0] * x;
                } else {
                    y = 0;
                    z = points[i][0];
                }
                String tmp = df.format(x) + "+" + df.format(y) + "+" + df.format(z);
                if (lines.containsKey(tmp)) {
                    Set<Integer> pointSet = lines.get(tmp);
                    pointSet.add(i);
                    pointSet.add(j);
                    max = Math.max(max, pointSet.size());
                    // System.out.println("0: " + (points[i][1] - points[j][1]));
                    // System.out.println("0: " + (points[j][0] - points[i][0]));
                    // System.out.println("x: " + x);
                    // System.out.println("y: " + y);
                    // System.out.println("z: " + z);
                    // System.out.println("tmp: " + tmp);
                } else {
                    Set<Integer> pointSet = new HashSet<>();
                    pointSet.add(i);
                    pointSet.add(j);
                    lines.put(tmp, pointSet);
                    max = Math.max(max, 2);
                }
            }
        }
        return max;
    }
}
