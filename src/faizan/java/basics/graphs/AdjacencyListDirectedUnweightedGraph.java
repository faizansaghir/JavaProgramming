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
		System.out.println("Depth first traversal");
		boolean[] visited=new boolean[graph.size()];		
		Stack<Integer> nexts=new Stack<>();
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.pop();
			System.out.print(source+" ");
			Set<Integer> destinations=graph.get(source);
			for(int destination:destinations) {
				if(!visited[destination]) {
					visited[destination]=true;
					nexts.add(destination);
				}
			}
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverse() {
		System.out.println("Depth first traversal modified");
		boolean[] visited=new boolean[graph.size()];		
		Stack<Integer> nexts=new Stack<>();
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				nexts.add(i);
				visited[i]=true;
			}
			while(!nexts.isEmpty()) {
				int source=nexts.pop();
				System.out.print(source+" ");
				Set<Integer> destinations=graph.get(source);
				for(int destination:destinations) {
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
	public void topologicalSort() {
		System.out.println("Topological sort using Kahn's algorithm");
		int[] inDegree=new int[graph.size()];
		for(Set<Integer> outgoingNodes:graph) {
			for(int outgoingNode:outgoingNodes) {
				inDegree[outgoingNode]++;
			}			
		}
		Queue<Integer> nexts=new LinkedList<>();
		for(int i=0;i<graph.size();i++) {
			if(inDegree[i]==0) {
				nexts.add(i);
				break;
			}
		}
		List<Integer> result=new ArrayList<>();
		while(!nexts.isEmpty()) {
			int currNode=nexts.poll();
			result.add(currNode);
			Set<Integer> outgoingNodes=graph.get(currNode);
			for(int outgoingNode:outgoingNodes) {
				inDegree[outgoingNode]--;
				if(inDegree[outgoingNode]==0) {
					nexts.add(outgoingNode);
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
		System.out.println("Topological sort using Depth First Traverse algorithm");
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
}
