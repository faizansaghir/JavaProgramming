package faizan.java.basics.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AdjacencyMatrixDirectedUnweightedGraph extends DirectedUnweightedGraph {
	int[][] graph;
	public AdjacencyMatrixDirectedUnweightedGraph(int size) {
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v) {
		graph[u][v]=1;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency Matrix Directed Unweighted Graph");
		for(int[] vertex:graph) {
			for(int weight:vertex) {
				System.out.print(weight+" ");
			}
			System.out.println();
		}
	}
	@Override
	public boolean pathExists(int u, int v) {
		return graph[u][v]>0;
	}
	@Override
	public void topologicalSort() {
		int[] indegree=new int[graph.length];
		for(int[] outgoing:graph) {
			for(int i=0;i<outgoing.length;i++) {
				if(outgoing[i]==1)
					indegree[i]++;
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
			int[] adjacentNodes=graph[currNode];
			result.add(currNode);
			for(int i=0;i<adjacentNodes.length;i++) {
				if(adjacentNodes[i]==1) {
					indegree[i]--;
					if(indegree[i]==0) {
						zeroDegreeNode.add(i);
					}
				}
					
			}
		}
		if(result.size()!=graph.length) {
			System.out.println("No topological sort possible");
		}
		else {
			System.out.println(result);
		}
	}
	@Override
	public void topologicalSortRecursive() {
		boolean[] visited=new boolean[graph.length];
		Stack<Integer> result=new Stack<>();
		
		int[] indegree=new int[graph.length];
		for(int[] outgoing:graph) {
			for(int i=0;i<outgoing.length;i++) {
				if(outgoing[i]==1)
					indegree[i]++;
			}
		}
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) {
				topologicalSort(i,visited,result);				
				break;
			}
		}
		System.out.println("Topological sort using Depth First Traverse algorithm");
		if(result.size()!=graph.length) {
			System.out.println("No topological sort possible");
		}
		else {
			while(!result.isEmpty())
				System.out.print(result.pop()+" ");
			System.out.println();
		}
	}
	private void topologicalSort(int index,boolean[] visited,Stack<Integer> result) {
		int[] adjacentNodes=graph[index];
		visited[index]=true;
		for(int i=0;i<adjacentNodes.length;i++) {
			if(adjacentNodes[i]==1 && !visited[i]) {
				topologicalSort(i,visited,result);
			}
		}
		result.add(index);
	}

}
