package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListUndirectedWeightedGraph extends UndirectedWeightedGraph{
	List<Set<List<Integer>>> graph;
	public AdjacencyListUndirectedWeightedGraph(int size) {
		graph=new ArrayList<>(size);
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u, int v, int w) {
		Set<List<Integer>> uAdjacent=graph.get(u);
		List<Integer> uPair=new ArrayList<>();
		uPair.add(v);
		uPair.add(w);
		uAdjacent.add(uPair);
		Set<List<Integer>> vAdjacent=graph.get(v);
		List<Integer> vPair=new ArrayList<>();
		vPair.add(u);
		vPair.add(w);
		vAdjacent.add(vPair);
	}

	@Override
	public boolean areConnected(int u, int v) {
		Set<List<Integer>> adjacent=graph.get(u);
		for(List<Integer> pair:adjacent) {
			if(pair.get(0)==v)
				return true;
		}
		return false;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency List Undirected Weighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

	@Override
	public int getWeight(int u, int v) {
		Set<List<Integer>> adjacent=graph.get(u);
		for(List<Integer> pair:adjacent) {
			if(pair.get(0)==v)
				return pair.get(1);
		}
		return 0;
	}

}