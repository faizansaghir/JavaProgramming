package faizan.java.basics.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AdjacencyMatrixDirectedWeightedGraph extends DirectedWeightedGraph {
	int[][] graph;
	public AdjacencyMatrixDirectedWeightedGraph(int size) {
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v, int w) {
		graph[u][v]=w;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency Matrix Directed Weighted Graph");
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
	public boolean pathExists(int u, int v) {
		return graph[u][v]>0;
	}
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
	public void topologicalSort() {
		int[] indegree=new int[graph.length];
		for(int[] outgoing:graph) {
			for(int i=0;i<outgoing.length;i++) {
				if(outgoing[i]!=0)
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
				if(adjacentNodes[i]!=0) {
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
				if(outgoing[i]!=0)
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
			if(adjacentNodes[i]!=0 && !visited[i]) {
				topologicalSort(i,visited,result);
			}
		}
		result.add(index);
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
