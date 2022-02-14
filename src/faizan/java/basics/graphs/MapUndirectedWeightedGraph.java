package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MapUndirectedWeightedGraph extends UndirectedWeightedGraph{
	Map<Integer,Set<GraphWeightedNode>> graph;
	public MapUndirectedWeightedGraph(int size) {
		graph=new HashMap<>();
		for(int i=0;i<size;i++) {
			graph.put(i, new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u, int v, int w) {
		Set<GraphWeightedNode> uAdjacent=graph.get(u);
		GraphWeightedNode uPair=new GraphWeightedNode(v,w);
		uAdjacent.add(uPair);
		Set<GraphWeightedNode> vAdjacent=graph.get(v);
		GraphWeightedNode vPair=new GraphWeightedNode(u,w);
		vAdjacent.add(vPair);
	}

	@Override
	public boolean areConnected(int u, int v) {
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
		System.out.println("Map Undirected Weighted Graph");
		for(int vertex:graph.keySet()) {
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
	@Override
	public boolean isCyclic() {
		
		int v=graph.size();
		int[] parents=new int[v];
		for(int i=0;i<v;i++) {
			parents[i]=i;
		}
		Set<String> edgesConsidered=new HashSet<>();
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[v];
		nexts.add(0);
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			Set<GraphWeightedNode> adjacentNodes=graph.get(source);
			for(GraphWeightedNode adjacentNode:adjacentNodes) {
				int destination=adjacentNode.index;
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
		int[] parent=new int[graph.size()];
		int[] distance=new int[graph.size()];
		PriorityQueue<int[]> heap=new PriorityQueue<>((a1,a2)->a1[1]-a2[1]);
		for(int i=0;i<graph.size();i++) {
			parent[i]=i;
			distance[i]=Integer.MAX_VALUE;
			heap.add(new int[] {i,Integer.MAX_VALUE});
		}
		distance[source]=0;
		Set<Integer> inSPT=new HashSet<>();
		heap.add(new int[] {source,0});
		while(inSPT.size()!=graph.size()) {
			int[] curr=heap.poll();
			int u=curr[0];
			int dist=curr[1];
			if(inSPT.contains(u))
				continue;
			inSPT.add(u);
			Set<GraphWeightedNode> adjacentNodes=graph.get(u);
			for(GraphWeightedNode adjacentNode:adjacentNodes) {
				int v=adjacentNode.index;
				int edgeWeight=adjacentNode.weight;
				if(inSPT.contains(v))
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
		for(int i=0;i<graph.size();i++) {
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
		for(int i=0;i<graph.size();i++) {
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