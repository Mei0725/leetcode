package leetcode_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ReconstructItinerary {

	/**
	 * solve by dfs.
	 * 
	 * create map to link the departs and destinations, and sort the destinations in lexical order.
	 * then for every possible destination, check if there is a possible rout, if there isn't, remove the previous rout and try the next one.
	 * 
	 * @param tickets
	 * @return
	 */
    public List<String> findItinerary(List<List<String>> tickets) {
    	// get the link of airports in lexical order
        Map<String, List<String>> places = new HashMap<>();
        for (List<String> ticket : tickets) {
            List<String> dis = places.getOrDefault(ticket.get(0), new ArrayList<>());
            dis.add(ticket.get(1));
            places.put(ticket.get(0), dis);
        }
        for (String depart : places.keySet()) {
            List<String> dis = places.get(depart);
            Collections.sort(dis);
        }
//         System.out.println(places);
        List<String> res = new ArrayList<>();
        res.add("JFK");
        return findItinerary(places, "JFK", res);
    }

    public List<String> findItinerary(Map<String, List<String>> places, String depart, List<String> res) {
        if (!places.containsKey(depart)) {
        	// check if it is a valid rout
            return places.isEmpty() ? res : null;
        }
        List<String> dis = places.get(depart);
        // since in the loop, list dis may be changed, 
        // copy it firstly and use it to check every destination
        List<String> disCopy = dis.stream().collect(Collectors.toList());
//        System.out.println("depart:" + depart + ", disUpdate:" + dis);
        for (String place : disCopy) {
        	// try to put this destination into rout
            dis.remove(place);
            if (dis.isEmpty()) {
                places.remove(depart);
            }
            res.add(place);
            if (findItinerary(places, place, res) != null) {
            	// it means there is a valid rout
                break;
            }
            // it means there is not a valid rout, recovery this map
            res.remove(res.size() - 1);
            dis.add(place);
            places.put(depart, dis);
        }
        // make sure in this case there is a valid rout, 
        // do not go to this code because it uses out all possible destinations
        return places.isEmpty() ? res : null;
    }
    
    /**
     * also use dfs but simplify it.
     * since there must be valid output, it find out the possible rout firstly and add the airports into output from end to start.
     * and this solution add possible destinations in smallest lexical order to make sure the previous rout are in the smallest lexical.
     * so it save the time in changing lists in map-places
     * 
     * @param airport
     * @param graph
     * @param itinerary
     */
    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> nextAirports = graph.get(airport);
        while (nextAirports != null && !nextAirports.isEmpty()) {
            dfs(nextAirports.poll(), graph, itinerary);
        }
        itinerary.addFirst(airport);
    }
}
