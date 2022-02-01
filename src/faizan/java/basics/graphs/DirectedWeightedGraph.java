package faizan.java.basics.graphs;

public abstract class DirectedWeightedGraph {
	public abstract void addEdge(int u,int v,int w) ;
	public abstract boolean pathExists(int u,int v);
	public abstract void printGraph();
	public abstract int getWeight(int u,int v);
	public abstract void topologicalSort();
	public abstract void topologicalSortRecursive();
}
