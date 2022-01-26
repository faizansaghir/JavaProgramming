package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyListDirectedUnweightedGraph extends DirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	public AdjacencyListDirectedUnweightedGraph() {
		// TODO Auto-generated constructor stub
		graph=new HashMap<>();
	}
	@Override
	public void addEdge(int u, int v) {
		// TODO Auto-generated method stub
		Set<Integer> adjacent=graph.getOrDefault(u, new HashSet<>());
		adjacent.add(v);
		graph.put(u,adjacent);
	}

	@Override
	public boolean pathExists(int u, int v) {
		// TODO Auto-generated method stub
		Set<Integer> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub
		System.out.println("Adjacency List Directed Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

}
