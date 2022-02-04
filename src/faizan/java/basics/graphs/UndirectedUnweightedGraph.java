package faizan.java.basics.graphs;

public abstract class UndirectedUnweightedGraph implements Undirected,Unweighted {
	public abstract void addEdge(int u,int v);
	public abstract boolean areConnected(int u,int v);
	public abstract void printGraph();
	public abstract void depthFirstTraverse(int start);
	public abstract void depthFirstTraverse();
	public abstract void depthFirstTraverseRecursive(int start);
	public abstract void depthFirstTraverseRecursive();
	public abstract boolean isCyclic();
	
}
