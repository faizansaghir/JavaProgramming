package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AdjacencyListUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	List<Set<Integer>> graph;
	AdjacencyListUndirectedUnweightedGraph(int size){
		graph=new ArrayList<>(size); 
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
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
		return adjacent.contains(v);
	}
	@Override
	public void printGraph() {
		System.out.println("Adjacency List Undirected Unweighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
	@Override
	public void breadthFirstTraverse(int start) {
		System.out.println("Breadth first traversal");
		boolean[] visited=new boolean[graph.size()];
		Queue<Integer> nexts=new LinkedList<>();
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int index=nexts.poll();
			System.out.print(index+" ");
			Set<Integer> adjacentNodes=graph.get(index);
			for(int adjacentNode:adjacentNodes) {
				if(!visited[adjacentNode]) {
					nexts.add(adjacentNode);
					visited[adjacentNode]=true;
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
				int index=nexts.poll();
				System.out.print(index+" ");
				Set<Integer> adjacentNodes=graph.get(index);
				for(int adjacentNode:adjacentNodes) {
					if(!visited[adjacentNode]) {
						nexts.add(adjacentNode);
						visited[adjacentNode]=true;
					}
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverse(int start) {
		System.out.println("Depth first traversal");
		Stack<Integer> nexts=new Stack<>();
		boolean[] visited=new boolean[graph.size()];
		nexts.add(start);
		visited[start]=true;
		while(!nexts.empty()) {
			int vertex=nexts.pop();
			System.out.print(vertex+" ");
			Set<Integer> adjacetNodes=graph.get(vertex);
			for(int adjacentNode:adjacetNodes) {
				if(!visited[adjacentNode]) {
					visited[adjacentNode]=true;
					nexts.add(adjacentNode);
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverse() {
		System.out.println("Depth first traversal modified");
		Stack<Integer> nexts=new Stack<>();
		boolean[] visited=new boolean[graph.size()];
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				nexts.add(i);
				visited[i]=true;
				while(!nexts.empty()) {
					int vertex=nexts.pop();
					System.out.print(vertex+" ");
					Set<Integer> adjacetNodes=graph.get(vertex);
					for(int adjacentNode:adjacetNodes) {
						if(!visited[adjacentNode]) {
							visited[adjacentNode]=true;
							nexts.add(adjacentNode);
						}
					}
				}				
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverseRecursive() {
		System.out.println("Depth first traversal recursive modified");
		boolean[] visited=new boolean[graph.size()];
		for(int i=0;i<graph.size();i++) {
			if(!visited[i])
				depthFirstTraverse(i,visited);			
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverseRecursive(int start) {
		System.out.println("Depth first traversal recursive");
		boolean[] visited=new boolean[graph.size()];
		depthFirstTraverse(start,visited);
		System.out.println();
	}
	private void depthFirstTraverse(int root,boolean[] visited) {
		System.out.print(root+" ");
		visited[root]=true;
		Set<Integer> adjacentNodes=graph.get(root);
		for(int adjacentNode:adjacentNodes) {
			if(!visited[adjacentNode]) {
				depthFirstTraverse(adjacentNode,visited);
			}
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
}
