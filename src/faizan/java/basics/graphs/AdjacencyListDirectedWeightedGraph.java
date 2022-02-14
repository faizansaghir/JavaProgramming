package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	@Override
	public void topologicalSort() {
		int[] indegree=new int[graph.size()];
		for(Set<GraphWeightedNode> edges:graph) {
			for(GraphWeightedNode edge:edges) {
				indegree[edge.index]++;
			}
		}
		Queue<Integer> nexts=new LinkedList<>();
		for(int i=0;i<graph.size();i++) {
			if(indegree[i]==0) {
				nexts.add(i);
			}
		}
		List<Integer> result=new ArrayList<>();
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			result.add(source);
			Set<GraphWeightedNode> edges=graph.get(source);
			for(GraphWeightedNode edge:edges) {
				int destination=edge.index;
				indegree[destination]--;
				if(indegree[destination]==0)
					nexts.add(destination);
			}
		}
		System.out.println("Topological sort using Kahn's algorithm");
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
		for(Set<GraphWeightedNode> outgoing:graph) {
			for(GraphWeightedNode edge:outgoing) {
				indegree[edge.index]++;
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
