package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdjacencyListDirectedUnweightedGraph extends DirectedUnweightedGraph {
	List<Set<Integer>> graph;
	public AdjacencyListDirectedUnweightedGraph(int size) {
		graph=new ArrayList<>(size);
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
		
	}
	@Override
	public void addEdge(int u, int v) {
		Set<Integer> adjacent=graph.get(u);
		adjacent.add(v);
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<Integer> adjacent=graph.get(u);
		return adjacent.contains(v);
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency List Directed Unweighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

}
