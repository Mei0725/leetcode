package leetcode_test;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
/*
 * https://leetcode.com/problems/dota2-senate/description/
 * 
In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:

Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".

Input: senate = "RD"
Output: "Radiant"

Input: senate = "RDD"
Output: "Dire"

Input: senate = "RRDDD"
Output: "Radiant"

Input: senate = "DRRDRDRDDR"
Output: "Radiant"
 */
	/**
	 * solve by greedy
	 * for every item i, it should ban the nearest following opposite item
	 * and there may be more than one round
	 * 
	 * @param senate
	 * @return
	 */
    public String predictPartyVictory(String senate) {
        int length = senate.length();
        // mark the disuse items
        boolean[] disuse = new boolean[length];
        String result = predictPartyVictory(senate, disuse);
        // handle the case that there are more than one round
        while (result == null) {
            result = predictPartyVictory(senate, disuse);
        }
        // System.out.println("result: " + result);
        return result;
    }

    /**
     * handle every round
     * 
     * @param senate    the input String
     * @param disuse    the disuse status of every senators
     * @return
     */
    public String predictPartyVictory(String senate, boolean[] disuse) {
    	//these 2 queues are used to store all available senators
    	//so it will save some time when debtR/debtD are left
        Queue<Integer> Rs = new LinkedList<>(), Ds = new LinkedList<>();
        // debtR/debtD is the debt number of senators because of the opposite's ban
        int debtR = 0, debtD = 0;
        for (int i = 0; i < senate.length(); i++) {
            if (disuse[i]) {
                continue;
            }
            switch(senate.charAt(i)) {
                case 'R':
                    if (debtR > 0) {
                        disuse[i] = true;
                        debtR--;
                    } else {
                        debtD++;
                        Rs.add(i);
                    }
                    break;
                case 'D':
                    if (debtD > 0) {
                        disuse[i] = true;
                        debtD--;
                    } else {
                        debtR++;
                        Ds.add(i);
                    }
                    break;
                default:
                    break;
            }
        }
        // handle the left debt senators, they will prioritize the first members
        while (debtR > 0 && Rs.size() > 0) {
            disuse[Rs.poll()] = true;
            debtR--;
        }
        while (debtD > 0 && Ds.size() > 0) {
            disuse[Ds.poll()] = true;
            debtD--;
        }

        // if there are senators left both in RS and DS, then another round is needed
        if (Rs.size() > 0 && Ds.size() > 0) {
            return null;
        } else if (Rs.size() > 0) {
            return "Radiant";
        } else {
            return "Dire";
        }
    }
}
