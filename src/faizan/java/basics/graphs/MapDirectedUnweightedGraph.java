package faizan.java.basics.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MapDirectedUnweightedGraph extends DirectedUnweightedGraph {
	Map<Integer,Set<Integer>> graph;
	public MapDirectedUnweightedGraph(int size) {
		graph=new HashMap<>();
		for(int i=0;i<size;i++) {
			graph.put(i, new HashSet<>());
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
		if(adjacent==null)
			return false;
		return adjacent.contains(v);
	}

	@Override
	public void printGraph() {
		System.out.println("Map Directed Unweighted Graph");
		for(int vertex:graph.keySet()) {
			System.out.println(vertex+" -> "+graph.get(vertex));
		}
	}
	@Override
	public void breadthFirstTraverse(int start) {
		System.out.println("Breadth first traversal");
		Queue<Integer> nexts=new LinkedList<>();
		boolean[] visited=new boolean[graph.size()];
		visited[start]=true;
		nexts.add(start);
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
		boolean[] visited=new boolean[graph.size()];
		Stack<Integer> nexts=new Stack<>();
		System.out.println("Depth first traversal");
		nexts.add(start);
		visited[start]=true;
		while(!nexts.isEmpty()) {
			int source=nexts.pop();
			Set<Integer> destinations=graph.get(source);
			System.out.print(source+" ");
			for(int destination:destinations) {
				if(!visited[destination]) {
					nexts.add(destination);
					visited[destination]=true;
				}
			}
		}			
		System.out.println();
	}
	@Override
	public void depthFirstTraverse() {
		boolean[] visited=new boolean[graph.size()];
		Stack<Integer> nexts=new Stack<>();
		System.out.println("Depth first traversal modified");
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				nexts.add(i);
				visited[i]=true;
			}
			while(!nexts.isEmpty()) {
				int source=nexts.pop();
				Set<Integer> destinations=graph.get(source);
				System.out.print(source+" ");
				for(int destination:destinations) {
					if(!visited[destination]) {
						nexts.add(destination);
						visited[destination]=true;
					}
				}
			}			
		}
		System.out.println();
	}
	@Override
	public void depthFirstTraverseRecursive(int start) {
		boolean[] visited=new boolean[graph.size()];
		System.out.println("Depth first traversal recursive");
		visited[start]=true;
		depthFirstTraverse(start, visited);
		System.out.println();
	}
	private void depthFirstTraverse(int start,boolean[] visited) {
		System.out.print(start+" ");
		Set<Integer> destinations=graph.get(start);
		for(int destination:destinations) {
			if(!visited[destination]) {
				visited[destination]=true;
				depthFirstTraverse(destination, visited);
			}
		}
	}
	@Override
	public void depthFirstTraverseRecursive() {
		boolean[] visited=new boolean[graph.size()];
		System.out.println("Depth first traversal recursive modified");
		for(int i=0;i<graph.size();i++) {
			if(!visited[i]) {
				visited[i]=true;
				depthFirstTraverse(i, visited);				
			}
		}
		System.out.println();
	}
	@Override
	public void topologicalSort() {
		System.out.println("Topological sort using Kahn's algorithm");
		int[] indegree=new int[graph.size()];
		for(int nodeIndex:graph.keySet()) {
			Set<Integer> outgoing=graph.get(nodeIndex);
			for(int destinationNode:outgoing) {
				indegree[destinationNode]++;
			}
		}
		Queue<Integer> zeroDegreeNode=new LinkedList<>();
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) {
				zeroDegreeNode.add(i);
				break;
			}
		}
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
		System.out.println("Topological sort using Depth First Traverse algorithm");
		boolean[] visited=new boolean[graph.size()];
		Stack<Integer> result=new Stack<>();
		int[] indegree=new int[graph.size()];
		for(int index:graph.keySet()) {
			Set<Integer> outgoing=graph.get(index);
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
