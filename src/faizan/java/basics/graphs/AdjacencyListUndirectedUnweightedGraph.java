package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyListUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	AdjacencyListUndirectedUnweightedGraph(){
		graph=new HashMap<>(); 
	}
	public void addEdge(int u,int v) {
		Set<Integer> uAdjacent=graph.getOrDefault(u, new HashSet<>());
		uAdjacent.add(v);
		graph.put(u,uAdjacent);
		Set<Integer> vAdjacent=graph.getOrDefault(v, new HashSet<>());
		vAdjacent.add(u);
		graph.put(v,vAdjacent);
	}
	public boolean areConnected(int u,int v) {
		Set<Integer> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}
	public void printGraph() {
		System.out.println("Adjacency List Undirected Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
}
