package faizan.java.basics.graphs;

public abstract class DirectedUnweightedGraph implements Directed,Unweighted {
	public abstract void addEdge(int u,int v);
	public abstract boolean pathExists(int u,int v);
	public abstract void printGraph();
	public abstract void topologicalSort();
	public abstract void topologicalSortRecursive();
	public abstract void depthFirstTraverse(int start);
	public abstract void depthFirstTraverse();
	public abstract void depthFirstTraverseRecursive(int start);
	public abstract void depthFirstTraverseRecursive();
}
