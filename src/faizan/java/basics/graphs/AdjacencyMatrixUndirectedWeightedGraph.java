package faizan.java.basics.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	public void shortestPathTree(int source) {
		int[] parent=new int[graph.length];
		int[] distance=new int[graph.length];
		PriorityQueue<int[]> heap=new PriorityQueue<>((a1,a2)->a1[1]-a2[1]);
		for(int i=0;i<graph.length;i++) {
			parent[i]=i;
			distance[i]=Integer.MAX_VALUE;
			heap.add(new int[] {i,Integer.MAX_VALUE});
		}
		distance[source]=0;
		Set<Integer> inSPT=new HashSet<>();
		heap.add(new int[] {source,0});
		while(inSPT.size()!=graph.length) {
			int[] curr=heap.poll();
			int u=curr[0];
			int dist=curr[1];
			if(inSPT.contains(u))
				continue;
			inSPT.add(u);
			int[] adjacentNodes=graph[u];
			for(int i=0;i<graph.length;i++) {
				int v=i;
				int edgeWeight=adjacentNodes[i];
				if(edgeWeight==0 || inSPT.contains(v))
					continue;
				if(dist+edgeWeight<distance[v]) {
					distance[v]=dist+edgeWeight;
					parent[v]=u;
					heap.add(new int[] {v,distance[v]});
				}
			}
		}
		Set<Integer> unreachable=new HashSet<>();
		System.out.println("Shortest path for indices");
		for(int i=0;i<graph.length;i++) {
			System.out.print(i+" : ");
			Stack<Integer> path=new Stack<>();
			int curr=i;
			while(parent[curr]!=curr) {
				path.push(curr);
				curr=parent[curr];
			}
			if(curr!=source) {
				System.out.println("Unreachable");
				unreachable.add(i);
				continue;
			}
			System.out.print(curr);
			while(!path.isEmpty()) {
				System.out.print("->"+path.pop());
			}
			System.out.println();
		}
		System.out.println("Shortest path tree from node indexed "+source);
		for(int i=0;i<graph.length;i++) {
			if(unreachable.contains(i)) {
				System.out.printf("[%d,unreachable] ",i);				
			}
			else {
				System.out.printf("[%d,%d] ",i,distance[i]);				
			}
		}
		System.out.println();
	}
}
