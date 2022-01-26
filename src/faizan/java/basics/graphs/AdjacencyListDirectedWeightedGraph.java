package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AdjacencyListDirectedWeightedGraph extends DirectedWeightedGraph{
	List<Set<GraphWeightedNode>> graph;
	public AdjacencyListDirectedWeightedGraph(int size) {
		graph=new ArrayList<>();
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u, int v, int w) {
		Set<GraphWeightedNode> adjacent=graph.get(u);
		GraphWeightedNode pair=new GraphWeightedNode(v,w);
		adjacent.add(pair);
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<GraphWeightedNode> adjacent=graph.get(u);
		for(GraphWeightedNode node:adjacent) {
			if(node.index==v)
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
		Set<GraphWeightedNode> adjacent=graph.get(u);
		if(adjacent==null)
			return 0;
		for(GraphWeightedNode pair:adjacent) {
			if(pair.index==v)
				return pair.weight;
		}
		return 0;
	}
	public void getShortestPrathTree(int source) {
		PriorityQueue<GraphWeightedNode> remainingNodes=new PriorityQueue<>();
		int[] distances=new int[graph.size()];
		for(int i=0;i<graph.size();i++) {
			distances[i]=Integer.MAX_VALUE;
		}
		distances[source]=0;
		remainingNodes.add(new GraphWeightedNode(source, 0));
		while(remainingNodes.size()>0){
			GraphWeightedNode node=remainingNodes.poll();
			int ind=node.index;
			Set<GraphWeightedNode> adjacentNodes=graph.get(ind);
			for(GraphWeightedNode adjacentNode:adjacentNodes) {
				int adjacentIndex=adjacentNode.index;
				int adjacentWeight=adjacentNode.weight;
				if(distances[adjacentIndex]>distances[ind]+adjacentWeight) {
					distances[adjacentIndex]=distances[ind]+adjacentWeight;
					remainingNodes.add(new GraphWeightedNode(adjacentIndex,distances[adjacentIndex]));
				}
					
			}
		}
		System.out.println("Shortest path tree from node indexed "+source);
		for(int i=0;i<distances.length;i++) {
			System.out.printf("[%d,%d] ",i,distances[i]);
		}
		System.out.println();
	}
}
