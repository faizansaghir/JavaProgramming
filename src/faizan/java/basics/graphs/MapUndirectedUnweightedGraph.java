package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	MapUndirectedUnweightedGraph(){
		graph=new HashMap<>(); 
	}
	@Override
	public void addEdge(int u,int v) {
		Set<Integer> uAdjacent=graph.getOrDefault(u, new HashSet<>());
		uAdjacent.add(v);
		graph.put(u,uAdjacent);
		Set<Integer> vAdjacent=graph.getOrDefault(v, new HashSet<>());
		vAdjacent.add(u);
		graph.put(v,vAdjacent);
	}
	@Override
	public boolean areConnected(int u,int v) {
		Set<Integer> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}
	@Override
	public void printGraph() {
		System.out.println("Map Undirected Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

}
