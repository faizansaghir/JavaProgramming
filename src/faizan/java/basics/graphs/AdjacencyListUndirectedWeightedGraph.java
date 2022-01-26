package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListUndirectedWeightedGraph extends UndirectedWeightedGraph{
	Map<Integer,Set<List<Integer>>> graph;
	public AdjacencyListUndirectedWeightedGraph() {
		// TODO Auto-generated constructor stub
		graph=new HashMap<>();
	}
	@Override
	public void addEdge(int u, int v, int w) {
		// TODO Auto-generated method stub
		Set<List<Integer>> uAdjacent=graph.getOrDefault(u, new HashSet<>());
		List<Integer> uPair=new ArrayList<>();
		uPair.add(v);
		uPair.add(w);
		uAdjacent.add(uPair);
		graph.put(u, uAdjacent);
		Set<List<Integer>> vAdjacent=graph.getOrDefault(v, new HashSet<>());
		List<Integer> vPair=new ArrayList<>();
		vPair.add(u);
		vPair.add(w);
		vAdjacent.add(vPair);
		graph.put(v, vAdjacent);
	}

	@Override
	public boolean areConnected(int u, int v) {
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
		System.out.println("Adjacency List Undirected Weighted Graph");
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
