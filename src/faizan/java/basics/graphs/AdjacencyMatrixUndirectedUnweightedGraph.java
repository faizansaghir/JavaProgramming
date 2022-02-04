package faizan.java.basics.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AdjacencyMatrixUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	int[][] graph;
	AdjacencyMatrixUndirectedUnweightedGraph(int size){
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u,int v) {
		graph[u][v]=1;
		graph[v][u]=1;
	}
	@Override
	public boolean areConnected(int u,int v) {
		return graph[u][v]==1;
	}
	@Override
	public void printGraph() {
		System.out.println("Adjacency Matrix Undirected Unweighted Graph");
		for(int[] node:graph) {
			for(int adjacency:node) {
				System.out.print(adjacency+" ");
			}
			System.out.println();
		}
	}
	@Override
	public boolean isCyclic() {	
		int v=graph.length;
		int[] parents=new int[v];
		for(int i=0;i<v;i++) {
			parents[i]=i;
		}
		Set<String> edgesConsidered=new HashSet<>();
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[v];
		nexts.add(0);
		visited[0]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			int[] weights=graph[source];
			for(int i=0;i<weights.length;i++) {
				int weight=weights[i];
				if(weight==0 || i==source)
					continue;
				int destination=i;
				int lesser=source<destination?source:destination;
				int greater=source<destination?destination:source;
				String key=lesser+" "+greater;
				if(edgesConsidered.contains(key)) {
					continue;
				}
				else {
					edgesConsidered.add(key);
				}
				if(!visited[destination]) {
					visited[destination]=true;
					nexts.add(destination);
				}
				if(getParent(source,parents)==getParent(destination,parents))
					return true;
				else
					union(source,destination,parents);
			}
		}
		return false;
	}
	private void union(int source,int dest,int[] parent) {
		int sParent=getParent(source,parent);
		int dParent=getParent(dest,parent);
		if(sParent==dParent)
			return;
		parent[dParent]=sParent;		
	}
	private int getParent(int vertex,int[] parents) {
		if(parents[vertex]!=vertex)
			parents[vertex]=getParent(parents[vertex],parents);
		return parents[vertex];
	}
	@Override
	public void breadthFirstTraverse(int start) {
		System.out.println("Breadth first traversal");
		boolean[] visited=new boolean[graph.length];
		Queue<Integer> nexts=new LinkedList<>();
		visited[start]=true;
		nexts.add(start);
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			System.out.print(source+" ");
			int[] adjacentNode=graph[source];
			for(int dest=0;dest<graph.length;dest++) {
				int weight=adjacentNode[dest];
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
		boolean[] visited=new boolean[graph.length];
		Queue<Integer> nexts=new LinkedList<>();
		for(int i=0;i<graph.length;i++) {
			if(visited[i])
				continue;
			visited[i]=true;
			nexts.add(i);
			while(!nexts.isEmpty()) {
				int source=nexts.poll();
				System.out.print(source+" ");
				int[] adjacentNode=graph[source];
				for(int dest=0;dest<graph.length;dest++) {
					int weight=adjacentNode[dest];
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void depthFirstTraverse() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void depthFirstTraverseRecursive() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void depthFirstTraverseRecursive(int start) {
		// TODO Auto-generated method stub
		
	}
}
