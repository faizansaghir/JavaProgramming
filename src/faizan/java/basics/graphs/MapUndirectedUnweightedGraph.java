package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MapUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	MapUndirectedUnweightedGraph(int size){
		graph=new HashMap<>(); 
		for(int i=0;i<size;i++) {
			graph.put(i,new HashSet<>());
		}
	}
	@Override
	public void addEdge(int u,int v) {
		Set<Integer> uAdjacent=graph.get(u);
		uAdjacent.add(v);
		Set<Integer> vAdjacent=graph.get(v);
		vAdjacent.add(u);
	}
	@Override
	public boolean areConnected(int u,int v) {
		Set<Integer> adjacent=graph.get(u);
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}
	@Override
	public void printGraph() {
		System.out.println("Map Undirected Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
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
			Set<Integer> adjacentNodes=graph.get(source);
			for(int destination:adjacentNodes) {
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
		boolean[] visited=new boolean[graph.size()];
		Queue<Integer> nexts=new LinkedList<>();
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			System.out.print(source+" ");
			Set<Integer> adjacentNodes=graph.get(source);
			for(int destination:adjacentNodes) {
				if(!visited[destination]) {
					visited[destination]=true;
					nexts.add(destination);
				}
			}
		}
		System.out.println();
	}
	@Override
	public void breadthFirstTraverse() {
		System.out.println("Breadth first traversal modified");
		boolean[] visited=new boolean[graph.size()];
		Queue<Integer> nexts=new LinkedList<>();
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				nexts.add(i);
				visited[i]=true;
			}
			while(!nexts.isEmpty()) {
				int source=nexts.poll();
				System.out.print(source+" ");
				Set<Integer> adjacentNodes=graph.get(source);
				for(int destination:adjacentNodes) {
					if(!visited[destination]) {
						visited[destination]=true;
						nexts.add(destination);
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
