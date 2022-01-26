package faizan.java.basics.graphs;

public abstract class UndirectedUnweightedGraph {
	public abstract void addEdge(int u,int v);
	public abstract boolean areConnected(int u,int v);
	public abstract void printGraph();
	public void breadthFirstTraverse(int start) {}
}
