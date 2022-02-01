package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MapDirectedWeightedGraph extends DirectedWeightedGraph {
	Map<Integer,Set<GraphWeightedNode>> graph;
	public MapDirectedWeightedGraph(int size) {
		graph=new HashMap<>();
		for(int i=0;i<size;i++) {
			graph.put(i, new HashSet<>());
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
		if(adjacent==null)
			return false;
		for(GraphWeightedNode pair:adjacent) {
			if(pair.index==v)
				return true;
		}
		return false;
	}

	@Override
	public void printGraph() {
		System.out.println("Map Directed Weighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}

	@Override
	public int getWeight(int u, int v) {
		Set<GraphWeightedNode> adjacent=graph.get(u);
		for(GraphWeightedNode pair:adjacent) {
			if(pair.index==v)
				return pair.weight;
		}
		return 0;
	}
	@Override
	public void topologicalSort() {
		int[] indegree=new int[graph.size()];
		for(int nodeIndex:graph.keySet()) {
			Set<GraphWeightedNode> outgoing=graph.get(nodeIndex);
			for(GraphWeightedNode destinationNode:outgoing) {
				indegree[destinationNode.index]++;
			}
		}
		Queue<Integer> zeroDegreeNode=new LinkedList<>();
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0)
				zeroDegreeNode.add(i);
		}
		System.out.println("Topological sort using Kahn's algorithm");
		List<Integer> result=new LinkedList<>();
		while(!zeroDegreeNode.isEmpty()) {
			int currNode=zeroDegreeNode.poll();
			Set<GraphWeightedNode> adjacentNodes=graph.get(currNode);
			result.add(currNode);
			for(GraphWeightedNode adjacentNode:adjacentNodes) {
				indegree[adjacentNode.index]--;
				if(indegree[adjacentNode.index]==0) {
					zeroDegreeNode.add(adjacentNode.index);
				}
			}
		}
		if(result.size()!=graph.size()) {
			System.out.println("No topological sort possible");
		}
		else {
			System.out.println(result);
		}
	}
	@Override
	public void topologicalSortRecursive() {
		boolean[] visited=new boolean[graph.size()];
		Stack<Integer> result=new Stack<>();
		int[] indegree=new int[graph.size()];
		for(int index:graph.keySet()) {
			Set<GraphWeightedNode> outgoing=graph.get(index);
			for(GraphWeightedNode destinationNode:outgoing) {
				indegree[destinationNode.index]++;
			}
		}
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) {
				topologicalSort(i,visited,result);				
				break;
			}
		}
		System.out.println("Topological sort using Depth First Traverse algorithm");
		if(result.size()!=graph.size()) {
			System.out.println("No topological sort possible");
		}
		else {
			while(!result.isEmpty())
				System.out.print(result.pop()+" ");
			System.out.println();
		}
	}
	private void topologicalSort(int index,boolean[] visited,Stack<Integer> result) {
		Set<GraphWeightedNode> adjacentNodes=graph.get(index);
		visited[index]=true;
		for(GraphWeightedNode adjacentNode:adjacentNodes) {
			if(!visited[adjacentNode.index]) {
				topologicalSort(adjacentNode.index,visited,result);
			}
		}
		result.add(index);
	}
}
