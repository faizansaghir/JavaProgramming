package faizan.java.basics.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AdjacencyMatrixUndirectedWeightedGraph extends UndirectedWeightedGraph{
	int[][] graph;
	public AdjacencyMatrixUndirectedWeightedGraph(int size) {
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v, int w) {
		graph[u][v]=w;
		graph[v][u]=w;
	}

	@Override
	public boolean areConnected(int u, int v) {
		return graph[u][v]>0;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency Matrix Undirected Weighted Graph");
		for(int[] vertex:graph) {
			for(int adjacent:vertex) {
				System.out.print(adjacent+" ");
			}
			System.out.println();
		}
	}
	@Override
	public int getWeight(int u, int v) {
		return graph[u][v];
	}
	@Override
	public void getShortestPrathTree(int source) {
		boolean[] sptSet=new boolean[graph.length];
		int[] distances=new int[graph.length];
		for(int i=0;i<graph.length;i++) {
			distances[i]=Integer.MAX_VALUE;
		}
		distances[source]=0;
		for(int i=0;i<graph.length-1;i++) {
			int ind=getMinimumDistanceNodeIndex(distances,sptSet);
			sptSet[ind]=true;
			int[] adjacentNodes=graph[ind];
			for(int j=0;j<adjacentNodes.length;j++) {
				if(adjacentNodes[j]!=0)
					distances[j]=Math.min(distances[j], distances[ind]+adjacentNodes[j]);
			}
		}
		System.out.println("Shortest path tree from node indexed "+source);
		for(int i=0;i<distances.length;i++) {
			System.out.printf("[%d,%d] ",i,distances[i]);
		}
		System.out.println();
	}
	private int getMinimumDistanceNodeIndex(int[] distances,boolean[] sptSet) {
		int min=Integer.MAX_VALUE;
		int index=-1;
		for(int i=0;i<distances.length;i++) {
			if(!sptSet[i] && distances[i]<=min) {
				index=i;
				min=distances[i];
			}
		}
		return index;
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
}
