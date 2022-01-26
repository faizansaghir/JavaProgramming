package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapDirectedUnweightedGraph extends DirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	public MapDirectedUnweightedGraph() {
		graph=new HashMap<>();
	}
	@Override
	public void addEdge(int u, int v) {
		Set<Integer> adjacent=graph.getOrDefault(u, new HashSet<>());
		adjacent.add(v);
		graph.put(u,adjacent);
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<Integer> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}

	@Override
	public void printGraph() {
		System.out.println("Map Directed Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
}
