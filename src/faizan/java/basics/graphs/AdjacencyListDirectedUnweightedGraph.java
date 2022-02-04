package faizan.java.basics.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AdjacencyListDirectedUnweightedGraph extends DirectedUnweightedGraph {
	List<Set<Integer>> graph;
	public AdjacencyListDirectedUnweightedGraph(int size) {
		graph=new ArrayList<>(size);
		for(int i=0;i<size;i++) {
			graph.add(new HashSet<>());
		}
		
	}
	@Override
	public void addEdge(int u, int v) {
		Set<Integer> adjacent=graph.get(u);
		adjacent.add(v);
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<Integer> adjacent=graph.get(u);
		return adjacent.contains(v);
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency List Directed Unweighted Graph");
		for(int i=0;i<graph.size();i++) {
			int vertex=i;
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
	@Override
	public void topologicalSort() {
		int[] indegree=new int[graph.size()];
		for(Set<Integer> outgoing:graph) {
			for(int destinationNode:outgoing) {
				indegree[destinationNode]++;
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
			Set<Integer> adjacentNodes=graph.get(currNode);
			result.add(currNode);
			for(int adjacentNode:adjacentNodes) {
				indegree[adjacentNode]--;
				if(indegree[adjacentNode]==0) {
					zeroDegreeNode.add(adjacentNode);
				}
			}
		}
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
		for(Set<Integer> outgoing:graph) {
			for(int destinationNode:outgoing) {
				indegree[destinationNode]++;
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
		Set<Integer> adjacentNodes=graph.get(index);
		visited[index]=true;
		for(int adjacentNode:adjacentNodes) {
			if(!visited[adjacentNode]) {
				topologicalSort(adjacentNode,visited,result);
			}
		}
		result.add(index);
	}
	@Override
	public void breadthFirstTraverse(int start) {
		System.out.println("Breadth first traversal");
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[graph.size()];
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.poll();
			System.out.print(source+" ");
			Set<Integer> adjacentNodes=graph.get(source);
			for(int adjacentNode:adjacentNodes) {
				if(!visited[adjacentNode]) {
					visited[adjacentNode]=true;
					nexts.add(adjacentNode);
				}
			}
		}
		System.out.println();
	}
	@Override
	public void breadthFirstTraverse() {
		System.out.println("Breadth first traversal modified");
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[graph.size()];
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				visited[i]=true;
				nexts.add(i);
			}
			while(!nexts.isEmpty()) {
				int source=nexts.poll();
				System.out.print(source+" ");
				Set<Integer> adjacentNodes=graph.get(source);
				for(int adjacentNode:adjacentNodes) {
					if(!visited[adjacentNode]) {
						visited[adjacentNode]=true;
						nexts.add(adjacentNode);
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
	public void depthFirstTraverseRecursive(int start) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void depthFirstTraverseRecursive() {
		// TODO Auto-generated method stub
		
	}

}
