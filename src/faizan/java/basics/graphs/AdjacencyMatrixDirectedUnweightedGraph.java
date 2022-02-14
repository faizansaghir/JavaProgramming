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
	@Override
	public void breadthFirstTraverse(int start) {
		System.out.println("Breadth first traversal");
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[graph.length];
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			System.out.print(source+" ");
			int[] adjacentNodes=graph[source];
			for(int dest=0;dest<adjacentNodes.length;dest++) {
				int weight=adjacentNodes[dest];
				if(weight==0)
					continue;
				if(!visited[dest]) {
					visited[dest]=true;
					nexts.add(dest);
				}
			}
		}
		System.out.println();
	}
	@Override
	public void breadthFirstTraverse() {
		System.out.println("Breadth first traversal modified");
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[graph.length];
		for(int i=0;i<graph.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				nexts.add(i);
			}
			while(!nexts.isEmpty()) {
				int source=nexts.poll();
				System.out.print(source+" ");
				int[] adjacentNodes=graph[source];
				for(int dest=0;dest<adjacentNodes.length;dest++) {
					int weight=adjacentNodes[dest];
					if(weight==0)
						continue;
					if(!visited[dest]) {
						visited[dest]=true;
						nexts.add(dest);
					}
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverse(int start) {
		boolean[] visited=new boolean[graph.length];
		Stack<Integer> nexts=new Stack<>();
		System.out.println("Depth first traversal");
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.pop();
			System.out.print(source+" ");
			int[] destinations=graph[source];
			for(int i=0;i<destinations.length;i++) {
				int weight=destinations[i];
				if(weight>0 && !visited[i]) {
					nexts.push(i);
					visited[i]=true;
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverse() {
		System.out.println("Depth first traversal modified");
		boolean[] visited=new boolean[graph.length];		
		Stack<Integer> nexts=new Stack<>();
		for(int i=0;i<graph.length;i++) {
			if(!visited[i]) {
				nexts.add(i);
				visited[i]=true;
			}
			while(!nexts.isEmpty()) {
				int source=nexts.pop();
				System.out.print(source+" ");
				int[] destinations=graph[source];
				for(int k=0;k<destinations.length;k++) {
					int weight=destinations[k];
					if(weight>0 && !visited[k]) {
						nexts.push(k);
						visited[k]=true;
					}
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverseRecursive(int start) {
		System.out.println("Depth first traversal recursive");
		boolean[] visited=new boolean[graph.length];
		visited[start]=true;
		depthFirstTraverse(start,visited);
		System.out.println();
	}
	private void depthFirstTraverse(int start,boolean[] visited) {
		System.out.print(start+" ");
		int[] destinations=graph[start];
		for(int i=0;i<destinations.length;i++) {
			int weight=destinations[i];
			if(weight>0 && !visited[i]) {
				visited[i]=true;
				depthFirstTraverse(i,visited);
			}
		}
	}
	@Override
	public void depthFirstTraverseRecursive() {
		System.out.println("Depth first traversal recursive modified");
		boolean[] visited=new boolean[graph.length];
		for(int i=0;i<graph.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				depthFirstTraverse(i, visited);
			}
		}
		System.out.println();
	}
}
