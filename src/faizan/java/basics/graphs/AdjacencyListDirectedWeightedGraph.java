package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListDirectedWeightedGraph extends DirectedWeightedGraph{
	Map<Integer,Set<List<Integer>>> graph;
	public AdjacencyListDirectedWeightedGraph() {
		// TODO Auto-generated constructor stub
		graph=new HashMap<>();
	}
	@Override
	public void addEdge(int u, int v, int w) {
		// TODO Auto-generated method stub
		Set<List<Integer>> adjacent=graph.getOrDefault(u, new HashSet<>());
		List<Integer> pair=new ArrayList<>();
		pair.add(v);
		pair.add(w);
		adjacent.add(pair);
		graph.put(u, adjacent);
	}

	@Override
	public boolean pathExists(int u, int v) {
		// TODO Auto-generated method stub
		Set<List<Integer>> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		for(List<Integer> pair:adjacent) {
			if(pair.get(0)==v)
				return true;
		}
		return false;
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub
		System.out.println("Adjacency List Directed Weighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

	@Override
	public int getWeight(int u, int v) {
		// TODO Auto-generated method stub
		Set<List<Integer>> adjacent=graph.get(u);
		if(adjacent==null)
			return 0;
		for(List<Integer> pair:adjacent) {
			if(pair.get(0)==v)
				return pair.get(1);
		}
		return 0;
	}

}
