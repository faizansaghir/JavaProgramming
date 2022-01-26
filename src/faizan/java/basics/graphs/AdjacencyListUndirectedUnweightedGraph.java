package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	List<Set<Integer>> graph;
	AdjacencyListUndirectedUnweightedGraph(int size){
		graph=new ArrayList<>(size); 
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u,int v) {
		Set<Integer> uAdjacent=graph.get(u);
		uAdjacent.add(v);
		Set<Integer> vAdjacent=graph.get(v);
		vAdjacent.add(u);
	}
	@Override
	public boolean areConnected(int u,int v) {
		Set<Integer> adjacent=graph.get(u);
		return adjacent.contains(v);
	}
	@Override
	public void printGraph() {
		System.out.println("Adjacency List Undirected Unweighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
}
