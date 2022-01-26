package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListDirectedWeightedGraph extends DirectedWeightedGraph{
	List<Set<List<Integer>>> graph;
	public AdjacencyListDirectedWeightedGraph(int size) {
		graph=new ArrayList<>();
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u, int v, int w) {
		Set<List<Integer>> adjacent=graph.get(u);
		List<Integer> pair=new ArrayList<>();
		pair.add(v);
		pair.add(w);
		adjacent.add(pair);
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<List<Integer>> adjacent=graph.get(u);
		for(List<Integer> pair:adjacent) {
			if(pair.get(0)==v)
				return true;
		}
		return false;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency List Directed Weighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

	@Override
	public int getWeight(int u, int v) {
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